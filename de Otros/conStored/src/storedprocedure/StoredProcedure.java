package storedprocedure;


import Gui.Acceso;
import com.sun.rowset.CachedRowSetImpl;
import table.Table;
import table.Column;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

public class StoredProcedure {

    public static ArrayList<String> getProcedure(){
        ArrayList<String> data = new ArrayList<String>();
        try {
            DatabaseMetaData dbmd = Acceso.cn.getMetaData();
            ResultSet rs = dbmd.getProcedures(Acceso.basedato,"dbo",null);
            while (rs.next()) 
                data.add(rs.getString(3));

        } catch (SQLException ex) {
            Logger.getLogger(StoredProcedure.class.getName()).log(Level.SEVERE, null, ex);
        }

            return data;
    }
    public static String executeProcedure(String sentencia){
        String mensaje=null;
        try {
            CachedRowSet crs = new CachedRowSetImpl();
            crs.setCommand(sentencia);
            crs.execute(Acceso.cn);

        } catch (SQLException ex) {
            mensaje=ex.getMessage();
            Logger.getLogger(StoredProcedure.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;

    }
    public static ArrayList<Table> readTables() {

        ArrayList<Table> data = new ArrayList<Table>();

        try {
            DatabaseMetaData dbmd =Acceso.cn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, null, new String[]{"TABLE"});


            while (rs.next()) {
                String nameTable = rs.getString(3);
                data.add(new Table(nameTable, readColumn(nameTable)));
            }

            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(StoredProcedure.class.getName()).log(Level.SEVERE, null, ex);
        }


        return data;
    }

    public static ArrayList<Column> readColumn(String table) {

        ArrayList<Column> tabl = new ArrayList<Column>();
        String tam = "";

        try {
            Statement st = Acceso.cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM " + table);
            ResultSetMetaData mdrs = rs.getMetaData(); // Obtener Metadatos de consulta
            int cantColumn = mdrs.getColumnCount(); // Obtener número de columnas

            for (int i = 1; i <= cantColumn; i++) {
                Column t = new Column();
                t.setNameColumn(mdrs.getColumnLabel(i));
                t.setTypeSql(mdrs.getColumnTypeName(i));

                String typeColumn = mdrs.getColumnTypeName(i).toLowerCase();

                if (typeColumn.equalsIgnoreCase("Numeric") || typeColumn.equalsIgnoreCase("Decimal")||typeColumn.equalsIgnoreCase("Double")) {
                    tam = "("+mdrs.getPrecision(i) + "," + mdrs.getScale(i)+")";
                }
                else if (typeColumn.contains("char"))// para ltodos los tipos de datos k terminen en Char ...
                     tam = "("+mdrs.getColumnDisplaySize(i)+")";
                else if (typeColumn.contains("int")){
                    tam ="";
                }
                 else if (typeColumn.contains("date") ){
                    tam ="";
                }
                else {
                    tam = String.valueOf(mdrs.getColumnDisplaySize(i));
                }
                t.setColumnSize(tam);
                if (mdrs.isAutoIncrement(i)) {
                    t.setIsAutoIncrement(true);
                } else {
                    t.setIsAutoIncrement(false);
                }

                tabl.add(t);

            }

            st.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(StoredProcedure.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tabl;
    }

    public static String readParam(String table) {
        StringBuilder param = new StringBuilder("\n");
        Table d = getTable(table);
        ArrayList<Column> at = (ArrayList<Column>) d.getColumn();
        for (int i = 0; i < at.size(); i++) {
            Column t = (Column) at.get(i);
            param.append("\t" + Acceso.simbol.concat(t.getNameColumn()).concat(" ").concat(t.getTypeSql()));
            param.append(" " + t.getColumnSize());
            param.append((at.size() == i + 1) ? " " : " ,\n");
        }
        return new String(param);

    }

    public static String insertTable(String table) {

        StringBuilder insert = new StringBuilder("insert into " + table + "( ");
        StringBuilder param = new StringBuilder(" values( ");
        Table d = getTable(table);
        ArrayList<Column> at = (ArrayList<Column>) d.getColumn();
        for (int i = 0; i < at.size(); i++) {
            Column t = (Column) at.get(i);
            if (!t.isIsAutoIncrement()) {
                insert.append(t.getNameColumn());
                param.append(Acceso.simbol + t.getNameColumn());
                if (at.size() == i + 1) {
                    insert.append(")\n\t ");
                    param.append(") "); }
                else {
                    insert.append(", ");
                    param.append(", ");}
            }
        }
        return new String(insert.append(param).append(";\n"));
    }

    public static String updateTable(String table) {

        StringBuilder update = new StringBuilder("update " + table + " set\n");
        Table d = getTable(table);
        ArrayList<Column> at = (ArrayList<Column>) d.getColumn();
        for (int i = 0; i < at.size(); i++) {
            Column t = (Column) at.get(i);
            if (!isPrimarykey(t.getNameColumn(), table)) {
                update.append("\t" + t.getNameColumn().concat("=").concat(Acceso.simbol.concat(t.getNameColumn())));
                update.append((at.size() == i + 1) ? " " : " ,\n");
            }
        }
        return new String(update).concat(where(table))+"\n";

    }

     public static String deletLogicTable(String table) {

        StringBuilder updateLogic = new StringBuilder("update " + table + " set  ");
        Table d = getTable(table);
        ArrayList<Column> at = (ArrayList<Column>) d.getColumn();
        if(isColumnEstado(at)==true)
            updateLogic.append("estado=false ");
        else
               return "/*No se puede realizar borrado logico\n x k no existe una columna estado*/\n";
        return new String(updateLogic).concat("\t"+where(table))+"\n";

    }


      public static String deleteTable(String table) {

        return "delete from " + table + "   ".concat(where(table))+"\n";

    }


    

    public static String where(String table) {
        StringBuilder where = new StringBuilder("\nwhere ");
        ArrayList<String> key = getPrimaryKey(table);
        if(key.size()==0)
            return ";/*Esta Tabla no tiene Primary Key*/";
        for (int i = 0; i < key.size(); i++) {
            where.append(key.get(i).toString().concat("=").concat(Acceso.simbol.concat(key.get(i).toString())));
            where.append((key.size() == (i + 1)) ? ";" : " and ");
            }
        return new String(where);
    }

    public static ArrayList<String> getPrimaryKey(String nameTable) {
        ArrayList<String> key = new ArrayList<String>();
        try {
           DatabaseMetaData dbmd =  Acceso.cn.getMetaData();
            ResultSet rs = dbmd.getPrimaryKeys(null, null, nameTable);
            for (; rs.next();) 
                key.add(rs.getString(4));
        } catch (SQLException ex) {
            Logger.getLogger(StoredProcedure.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }

    public static boolean isPrimarykey(String field, String table) {
        ArrayList<String> key = getPrimaryKey(table);
        for (String s : key) 
            if (s.equals(field)) 
                return true;
        return false;
    }

    public static Table getTable(String table) {
        ArrayList<Table> data = readTables();
        for (Table d : data) 
            if (d.getTable().equals(table))
                return d;
        return null;
    }

    public static boolean isColumnEstado( ArrayList<Column> col){

     for (int i = 0; i < col.size(); i++) {
            Column t = (Column) col.get(i);
            if(t.getNameColumn().equalsIgnoreCase("estado")||t.getNameColumn().toLowerCase().contains("ESTA_".toLowerCase()))
            {
                return true;
           }
        }
       return false;
    }
}

