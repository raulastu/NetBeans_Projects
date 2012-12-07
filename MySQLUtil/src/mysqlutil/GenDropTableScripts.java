package mysqlutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class GenDropTableScripts {

    DB database;

    public GenDropTableScripts(DB database) {
        this.database = database;
    }

    /**
     * this method is useful to make droping scripts, finding all tables which 
     * don't have children, so the process to create a droping script could be:
     *  - finding the firsts tables with no childs
     *  - copy that list in the script and executing the script (deleting them).
     *  - run this method again to find the newer tables which don't have childs.
     *  - and so on to find the last(s) root table.
     *  
     * @param schema schema which own the table 
     * @return a set of tables which don't have any child
     * @throws java.sql.SQLException
     */
    public HashSet<String> getNoChildrenTables(String schema) throws SQLException {
        ResultSet rs1 = database.crSt().executeQuery(
                "select table_name from information_schema.tables "+
                "where table_schema='"+schema+"'");
        HashSet<String> tableSet = new HashSet<String>();
        while (rs1.next()) {
            tableSet.add(rs1.getString(1));
        }
        rs1.close();
        HashSet<String> tablesNoChildrenSet = new HashSet<String>();
        for (String table : tableSet) {
            ResultSet rs2 = database.crSt().executeQuery(
                    "SELECT c.table_schema, u.table_name, u.column_name, u.referenced_column_name "+
                    "FROM information_schema.table_constraints AS c "+
                    "INNER JOIN information_schema.key_column_usage AS u "+
                    "USING( constraint_schema, constraint_name ) "+
                    "WHERE c.constraint_type = 'FOREIGN KEY' "+
                    "AND u.referenced_table_schema='"+schema+"' "+
                    "AND u.referenced_table_name = '"+table+"' "+
                    "ORDER BY c.table_schema,u.table_name;");
            if (!rs2.next()) {
                tablesNoChildrenSet.add(table);
            }
        }
        return tablesNoChildrenSet;
    }

    public String getDroppingScript(String schema) throws SQLException {
        HashSet<String> set = null;
        String res = "";
        do {
            set = getNoChildrenTables(schema);
            for (String table : set) {
                String dropScript = "DROP TABLE IF EXISTS `"+schema+"`"+".`"+table+"`;\n";
                database.crSt().executeUpdate(
                        dropScript);
//                dropTable(schema, table);
                res += dropScript;
            }
        } while (set.size()>0);
        return res;
    }

    public void dropTable(String schema, String table) throws SQLException {
        int r = database.crSt().executeUpdate(
                "DROP TABLE IF EXISTS `"+schema+"`.`"+table+"`;");
    }

    public static void main(String[] args) throws SQLException {
        DB database = new DB();
        System.out.println(new GenDropTableScripts(database).getDroppingScript("cecoca"));
        database.closeDB();
    }
}
