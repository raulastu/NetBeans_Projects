package vstudionetutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    static String paramPrefix = "p_";
    /**
     *
     */
    HashMap<String, Type> mapsTypes;
    static private HashSet<String> noNeedPrecisionSet;
    static private HashSet<String> needScale = new HashSet<String>();
    private static String dropDownListPrefix = "ddl";
    private static String textBoxPrefix = "txt";

    class Type {

        String csType;
        String SqlDbType;
        String csTypeNonPrimitive;

        public Type(String csType, String SqlDbType) {
            this.csType = csType;
            this.SqlDbType = SqlDbType;
        }

        public Type(String csType, String SqlDbType, String csTypeNonPrimitive) {
            this.csType = csType;
            this.SqlDbType = SqlDbType;
            this.csTypeNonPrimitive = csTypeNonPrimitive;
        }

        @Override
        public String toString() {
            return csType + " " + SqlDbType;
        }
    }

    public Util() {
        mapsTypes = new HashMap();
        noNeedPrecisionSet = new HashSet<String>();

        mapsTypes.put("int", new Type("int", "SqlDbType.Int", "Int32"));
        mapsTypes.put("int identity", new Type("int", "SqlDbType.Int", "Int32"));
        mapsTypes.put("tinyint", new Type("int", "SqlDbType.TinyInt", "Int16"));
        mapsTypes.put("varchar", new Type("String", "SqlDbType.VarChar"));
        mapsTypes.put("datetime", new Type("DateTime", "SqlDbType.DateTime"));
        mapsTypes.put("decimal", new Type("Decimal", "SqlDbType.Decimal"));
        mapsTypes.put("bit", new Type("Boolean", "SqlDbType.Bit"));
        mapsTypes.put("char", new Type("String", "SqlDbType.Char"));
        mapsTypes.put("money", new Type("Decimal", "SqlDbType.Decimal"));
        mapsTypes.put("float", new Type("float", "SqlDbType.Float", "Float"));

        noNeedPrecisionSet.add("int");
        noNeedPrecisionSet.add("int identity");
        noNeedPrecisionSet.add("bit");
        noNeedPrecisionSet.add("datetime");
        noNeedPrecisionSet.add("tinyint");
        noNeedPrecisionSet.add("money");

        needScale.add("decimal");
        needScale.add("numeric");
    }

    public ArrayList<Node> getFields(String table, Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("select * from " + table + " where 2 = 1");
        int count = rs.getMetaData().getColumnCount();

        ArrayList<Node> cols = new ArrayList<Node>();
        HashMap<String, String[]> fks = getForeignKeys(table);
        LinkedList<String> pks = getPrimaryKeys(table, conn);

        for (int i = 1; i <= count; i++) {
            String sqlName = rs.getMetaData().getColumnLabel(i);
            String type = rs.getMetaData().getColumnTypeName(i);

            String sqlType = "";
            sqlType = type.replace(" identity", "");

            System.err.println(type + " " + mapsTypes.get(type) +
                    " precision=" + !noNeedPrecisionSet.contains(type));

            String aspType = fks.containsKey(sqlName) ? dropDownListPrefix : textBoxPrefix;
            String csName = Sql2CSPatt(sqlName);
            String labelCaption = sqlToLabelCap(sqlName);

            Node nod = new Node(sqlName, csName, labelCaption,
                    sqlType, mapsTypes.get(type).csType,
                    aspType, mapsTypes.get(type).SqlDbType, "@" + paramPrefix + sqlName);
            nod.precision = rs.getMetaData().getPrecision(i);
            nod.scale = rs.getMetaData().getScale(i);
            if (!noNeedPrecisionSet.contains(type)) {//si necesita precision
                nod.precisioNeededInParam = true;
                if (needScale.contains(type)) {
                    nod.ScaleNeededInParam = true;
                }
            }

            if (mapsTypes.get(type).csTypeNonPrimitive != null) {
                nod.csTypeNonPrimitive = mapsTypes.get(type).csTypeNonPrimitive;
            }
            if (type.equals("int identity")) {
                nod.insertable = false;
            }
            if (pks.contains(sqlName)) {
                nod.isPK = true;
            }
//                insertCode += fixedColName + " " + sqlType;
            if (rs.getMetaData().isNullable(i) == ResultSetMetaData.columnNullable) {
                nod.isNullable = true;
            }
            cols.add(nod);
        }
        return cols;
    }

    public static String Sql2CSPatt(String sqlField) {
        String words[] = sqlField.split("_");
        String colLabel = words[0];
        for (int j = 1; j < words.length; j++) {
//                    System.out.println(words[i]);
            colLabel += words[j].substring(0, 1).toUpperCase() +
                    words[j].substring(1, words[j].length());
        }
        return colLabel;
    }

    public static String sqlToLabelCap(String sqlField) {
        String[] ar = sqlField.split("_");
        String res = "";
        for (String x : ar) {
            res += cap(x) + " ";
        }
        return res.substring(0, res.length() - 1);
    }

    public static LinkedList<String> getPrimaryKeys(String table, Connection conn) {
        LinkedList<String> res = new LinkedList<String>();
        try {
            ResultSet rs = conn.createStatement().executeQuery(
                    " SELECT A.TABLE_NAME, A.CONSTRAINT_NAME, B.COLUMN_NAME " +
                    "FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS A, INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE B " +
                    "WHERE CONSTRAINT_TYPE = 'PRIMARY KEY' AND A.CONSTRAINT_NAME = B.CONSTRAINT_NAME " +
                    "AND A.TABLE_NAME = '" + table + "'");
            while (rs.next()) {
                res.add(rs.getString(3) + "");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    static String cap(String st) {
        return (st.charAt(0) + "").toUpperCase() + st.substring(1, st.length());
    }

    static LinkedHashMap<String, String[]> getForeignKeys(String table) throws SQLException {
        ResultSet rs = MasterConn.getConnection().createStatement().executeQuery("SELECT f.name AS ForeignKey, " +
                "OBJECT_NAME(f.parent_object_id) AS TableName, " +
                "COL_NAME(fc.parent_object_id, " +
                "fc.parent_column_id) AS ColumnName, " +
                "OBJECT_NAME (f.referenced_object_id) AS ReferenceTableName, " +
                "COL_NAME(fc.referenced_object_id, " +
                "fc.referenced_column_id) AS ReferenceColumnName " +
                "FROM sys.foreign_keys AS f " +
                "INNER JOIN sys.foreign_key_columns AS fc " +
                "ON f.OBJECT_ID = fc.constraint_object_id " +
                "WHERE OBJECT_NAME(f.parent_object_id) = '" + table + "'");
        LinkedHashMap<String, String[]> fks = new LinkedHashMap<String, String[]>();
        while (rs.next()) {
            fks.put(rs.getString(3), new String[]{rs.getString(4), rs.getString(5)});
        }
        return fks;
    }
}
