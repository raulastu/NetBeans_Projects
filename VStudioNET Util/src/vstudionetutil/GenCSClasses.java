package vstudionetutil;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import vstudionetutil.Util.Type;

public class GenCSClasses extends RCGen {

    String spPrefix = "stp_";
    String paramPrefix = "p_";
    Version version = Version.v2008;
    HashMap<String, Type> myMapsTypes;

    public GenCSClasses(Connection con, String databaseName,
            String[] tables, String tablePrefix, String spPrefix) {
        super(con, databaseName, tables, tablePrefix, "", "");
        myMapsTypes = new Util().mapsTypes;
        this.spPrefix = spPrefix;
    }

    void createBEClasses(String projectNamePrefix, String path) throws IOException, SQLException {
        for (String table : tables) {
            String[] word = table.split("_");
            String className = "";
            for (int i = 0; i < word.length; i++) {
                className += word[i].substring(0, 1).toUpperCase() +
                        word[i].substring(1, word[i].length());
            }
            className += "BE";

            PrintWriter pw = new PrintWriter(new FileWriter(path + className + ".cs"));
            String code = "using System;\n" +
                    "using System.Collections.Generic;\n" +
                    "using System.Text;\n" +
                    "\n" +
                    "namespace " + projectNamePrefix + "BEL\n" +
                    "{\n" +
                    "\tpublic class " + className + "\n" +
                    "\t{\n";

            ResultSet rs = conn.createStatement().executeQuery("select * from " + tablePrefix + table + " where 2 = 1");
            int count = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= count; i++) {
                String colLabel = rs.getMetaData().getColumnLabel(i);
                String csName = Util.Sql2CSPatt(colLabel);
                String thetype = rs.getMetaData().getColumnTypeName(i);
                String csharpType = "";

                System.err.println(thetype);
                if (myMapsTypes.get(thetype) == null) {
                    csharpType = thetype;
                } else {
                    csharpType = myMapsTypes.get(thetype).csType;
                }
                System.err.println(thetype + " -> " + csharpType);
//                if (isNullable == ResultSetMetaData.columnNullable) {
//                    csharpType = "Nullable<" + csharpType + ">";
//                }
                if (version == Version.v2005) {
                    code += "\t\tpublic " + csharpType + " v_" + csName + ";\n";

                    code += "\t\tpublic " + csharpType + " " + csName + "{\n" +
                            "\t\t\tget{return v_" + csName + ";}\n" +
                            "\t\t\tset{v_" + csName + " = value;}\n" +
                            "\t\t}\n";
                } else if (version == Version.v2008) {
                    code += "\t\tpublic " + csharpType + " " + csName + " {get;set;}\n";
                }

            }
            code += "\t}\n" +
                    "}";
//            System.out.println(code);
            pw.write(code);
            pw.close();
        }
    }

    void createDAClasses(String projectNamePrefix, String path) throws IOException, SQLException {
        for (String table : tables) {
            String[] word = table.split("_");
            String className = "";
            for (int i = 0; i < word.length; i++) {
                className += word[i].substring(0, 1).toUpperCase() +
                        word[i].substring(1, word[i].length());
            }

//            className += "DA";

            PrintWriter pw = new PrintWriter(
                    new FileWriter(path + className + "DA.cs"));
            String code = "using System;\n" +
                    "using System.Collections.Generic;\n" +
                    "using System.Text;\n" +
                    "using System.Data;\n" +
                    "using System.Data.SqlClient;\n" +
                    "using " + projectNamePrefix + "BEL" + ";" +
                    "\n\n" +
                    "namespace " + projectNamePrefix + "DAL" + "{\n\n" +
                    "\tpublic class " + className + "DA{\n" +
                    "\n";


            code += genInsertMethod(table);
            code += genUpdateMethod(table);
            code += genDeleteMethod(table);

            System.err.println(genInsertMethod(table));


            //######### START SEL METHOD

            code += "\t\tpublic static List<" + className + "BE> getList" + className + "(){\n" +
                    "\t\t\tList<" + className + "BE> list;\n" +
                    "\t\t\tSqlDataReader dr;\n" +
                    "\t\t\tusing(SqlConnection cn = ConnectionManager.getConnectionManager()){\n" +
                    "\t\t\t\tusing(SqlCommand command = new SqlCommand()){\n" +
                    "\t\t\t\t\tlist = new List<" + className + "BE>();\n" +
                    "\t\t\t\t\tcommand.Connection = cn;\n" +
                    "\t\t\t\t\tcommand.CommandType = System.Data.CommandType.StoredProcedure;\n" +
                    "\t\t\t\t\tcommand.CommandText = \"" + spPrefix + "sel_" + table + "\";\n" +
                    "\t\t\t\t\tdr = command.ExecuteReader();\n" +
                    "\t\t\t\t\twhile (dr.Read()){\n" +
                    "\t\t\t\t\t\t" + className + "BE ob = new " + className + "BE();\n";
            ArrayList<Node> fields = new Util().getFields(tablePrefix + table, conn);
            int i = 0;
            for (Node node : fields) {
                code += "\t\t\t\t\t\tob." + node.csName + " = dr.Get" + node.csTypeNonPrimitive + "(" + i++ + ");\n";
            }

            code += "\t\t\t\t\t\tlist.Add(ob);\n" +
                    "\t\t\t\t\t}\n" +
                    "\t\t\t\t\tdr.Close();\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t}\n" +
                    "\t\t\treturn list;\n" +
                    "\t\t}\n";
            code += "\t}\n" +
                    "}";
//            System.out.println(code);
            pw.write(code);
            pw.close();
        }
        PrintWriter pw = new PrintWriter(new FileWriter(path + "ConnectionManager.cs"));
        pw.write(genConnectionManagerClass(projectNamePrefix));

        pw.close();
    }

    public String genInsertMethod(String table) throws SQLException {
        String insertCode = "";
        insertCode += "\t\tpublic static void insert(";
        ResultSet rs = conn.createStatement().executeQuery("select * from " + tablePrefix + table + " where 2 = 1");
        int count = rs.getMetaData().getColumnCount();
        ArrayList<String> fields = new ArrayList();
        for (int i = 1; i <= count; i++) {
            String sqlField = rs.getMetaData().getColumnLabel(i);
            String javaField = Util.Sql2CSPatt(sqlField);
            String type = rs.getMetaData().getColumnTypeName(i);
            if (type.equals("int identity")) {
                continue;
            }
            String csharpType = new Util().mapsTypes.get(type) == null ? type : new Util().mapsTypes.get(type).csType;
            String vsDBType = new Util().mapsTypes.get(type) == null ? type : new Util().mapsTypes.get(type).SqlDbType;
            String toadd = sqlField + " " + vsDBType + " " + csharpType + " " + javaField;
            System.out.println(toadd);
            fields.add(toadd);
        }
        for (String string : fields) {
            insertCode += string.split(" ", 3)[2] + ", ";
        }
        insertCode = insertCode.substring(0, insertCode.length() - 2);

        insertCode += "){\n" +
                "\t\t\tusing (SqlConnection cn = ConnectionManager.getConnectionManager()) {\n" +
                "\t\t\t\tusing (SqlCommand command = new SqlCommand()) {\n" +
                "\t\t\t\t\tcommand.Connection = cn;\n" +
                "\t\t\t\t\tcommand.CommandType = System.Data.CommandType.StoredProcedure;\n" +
                "\t\t\t\t\tcommand.CommandText = \"" + spPrefix + "insert_" + table + "\";\n";

        for (String afield : fields) {
            String[] t = afield.split(" ");
            String sqlField = t[0];
            String vsDBType = t[1];
            String javaField = t[3];
            insertCode += "\t\t\t\t\tcommand.Parameters.Add(\"@" + paramPrefix + sqlField + "\", " + vsDBType + ");\n" +
                    "\t\t\t\t\tcommand.Parameters[\"@" + paramPrefix + sqlField + "\"].Value = " + javaField + ";\n";
        }
        insertCode += "\t\t\t\t\tcommand.ExecuteNonQuery();\n" +
                "\t\t\t\t\tcn.Close();\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n\n";
        return insertCode;
    }

    public String genUpdateMethod(String table ) throws SQLException {
        String updCode = "";
        updCode += "\t\tpublic static void update(";
        ResultSet rs = conn.createStatement().executeQuery("select * from " + tablePrefix + table + " where 2 = 1");
        int count = rs.getMetaData().getColumnCount();
        ArrayList<String> fields = new ArrayList();
        for (int i = 1; i <= count; i++) {
            String sqlField = rs.getMetaData().getColumnLabel(i);
            String javaField = Util.Sql2CSPatt(sqlField);
            String type = rs.getMetaData().getColumnTypeName(i);
            String csharpType = new Util().mapsTypes.get(type) == null ? type : new Util().mapsTypes.get(type).csType;
            String vsDBType = new Util().mapsTypes.get(type) == null ? type : new Util().mapsTypes.get(type).SqlDbType;
            String toadd = sqlField + " " + vsDBType + " " + csharpType + " " + javaField;
            System.out.println(toadd);
            fields.add(toadd);
        }
        for (String string : fields) {
            updCode += string.split(" ", 3)[2] + ", ";
        }
        updCode = updCode.substring(0, updCode.length() - 2);

        updCode += "){\n" +
                "\t\t\tusing (SqlConnection cn = ConnectionManager.getConnectionManager()) {\n" +
                "\t\t\t\tusing (SqlCommand command = new SqlCommand()) {\n" +
                "\t\t\t\t\tcommand.Connection = cn;\n" +
                "\t\t\t\t\tcommand.CommandType = System.Data.CommandType.StoredProcedure;\n" +
                "\t\t\t\t\tcommand.CommandText = \"" + spPrefix + "update_" + table + "\";\n";

        for (String afield : fields) {
            String[] t = afield.split(" ");
            String sqlField = t[0];
            String vsDBType = t[1];
            String javaField = t[3];
            updCode += "\t\t\t\t\tcommand.Parameters.Add(\"@" + paramPrefix + sqlField + "\", " + vsDBType + ");\n" +
                    "\t\t\t\t\tcommand.Parameters[\"@" + paramPrefix + sqlField + "\"].Value = " + javaField + ";\n";
        }
        updCode += "\t\t\t\t\tcommand.ExecuteNonQuery();\n" +
                "\t\t\t\t\tcn.Close();\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n\n";
        return updCode;
    }

    public String genDeleteMethod(String table) throws SQLException {
        String deleteCode = "\t\tpublic static void delete(";
        ArrayList<Node> fields = new Util().getFields(tablePrefix + table, conn);

        for (Node node : fields) {
            if (node.isPK) {
                deleteCode += node.csType + " " + node.csName + ", ";
            }
        }

        System.err.println("xxxxxxxxxxxxx");
        deleteCode = deleteCode.substring(0, deleteCode.length() - 2);
        deleteCode += "){\n" +
                "\t\t\tusing (SqlConnection cn = ConnectionManager.getConnectionManager()) {\n" +
                "\t\t\t\tusing (SqlCommand command = new SqlCommand()) {\n" +
                "\t\t\t\t\tcommand.Connection = cn;\n" +
                "\t\t\t\t\tcommand.CommandType = System.Data.CommandType.StoredProcedure;\n" +
                "\t\t\t\t\tcommand.CommandText = \"" + spPrefix + "delete_" + table + "\";\n";

        for (Node node : fields) {
            if (node.isPK) {
                deleteCode += "\t\t\t\t\tcommand.Parameters.Add(\"@" + paramPrefix + node.sqlName + "\", " + node.sqlDbType + ");\n" +
                        "\t\t\t\t\tcommand.Parameters[\"@" + paramPrefix + node.sqlName + "\"].Value = " + node.csName + ";\n";
            }
        }
        deleteCode += "\t\t\t\t\tcommand.ExecuteNonQuery();\n" +
                "\t\t\t\t\tcn.Close();\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n\n";
        return deleteCode;
    }

    public String genConnectionManagerClass(String projectNamePrefix) {
        String res = "using System;\n" +
                "using System.Collections.Generic;\n" +
                "using System.Text;\n" +
                "using System.Data.SqlClient;\n" +
                "\n" +
                "namespace " + projectNamePrefix + "DAL\n" +
                "{\n" +
                "    class ConnectionManager\n" +
                "    {\n" +
                "        public static SqlConnection getConnectionManager() {\n" +
                "            SqlConnection cn;\n" +
                "            cn = new SqlConnection(\"Data Source=(local);Initial Catalog=" + databaseName + ";Integrated Security=true\");\n" +
                "            cn.Open();\n" +
                "            return cn;\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
        return res;
    }

    /**
     * change from SQL to Java Pattern i.e id_user = idUser
     * @param words
     * @return
     */
    public static void runMatricula() throws Exception {
        String[] matriculaTables = {
//            //batista
//            "universidad",
//            "facultad",
//            "escuela",
//            "plan_curricular",
//            //paco
//            "ciclo",
//            "curso",
//            "tipo_docente",
//            "docente",
//            "carga_lectiva",
//            "tipo_carga_lectiva",
//            "prerequisitos",
//            //rc
//            "departamento",
//            "provincia",
//            "distrito",
//            "alumno",
//            "usuario",
//            "sancionado",
//            "tipo_sancion",
//            "matricula",
//            "matricula_detalle",
//            //wilfer
//            "tipo_pago",
//            "pago",
//            "semestre_academico",
//            "wall",
//            "usuario_docente",
//            "administrativo",
//            "tipo_administrativo",
            "files_upload"
        };

        String prefijo = "t_";
        String spPrefix = "stp_";
        GenCSClasses ob = new GenCSClasses(MasterConn.getConnection(),
                "Matricula", matriculaTables, prefijo, spPrefix);
        ob.createBEClasses("Matricula", "E:/Matricula/Classes/BEL/");
        ob.createDAClasses("Matricula", "E:/Matricula/Classes/DAL/");
    }

    public static void runAlmacenTienda() throws Exception {
        String[] almacenTiendaTables = {
            "almacen",
            "inventario",
            "producto",
            "gasto_mensual",
            "transferencia",
            "tienda"
        };
        String tablePrefix = "t_";
        String spPrefix = "stp_";
        Connection conn = MasterConn.getConnection();
        GenCSClasses ob = new GenCSClasses(conn,
                "AlmacenTienda", almacenTiendaTables, tablePrefix, spPrefix);
        ob.createBEClasses("AlmacenTienda",
                "E:/Visual Studio Projects/Almacen Tienda/BEL/");
        ob.createDAClasses("AlmacenTienda", "E:/Visual Studio Projects/Almacen Tienda/DAL/");
        GenASPXpages web = new GenASPXpages(conn, "AlmacenTienda",
                almacenTiendaTables, tablePrefix,
                "E:/Visual Studio Projects/Almacen Tienda/WL/",
                "AlmacenTienda", "AlmacenTienda");
        web.genPage();
    }

    public static void runEmpleado() throws Exception {
        String[] tables = {
            "departamento",
            "provincia",
            "distrito",
            "empleado"
        };
        String prefijo = "t_";
        Connection conn = MasterConn.getConnection();
        String spPrefix = "stp_";
        GenCSClasses ob = new GenCSClasses(conn,
                "Empleado", tables, prefijo, spPrefix);
        ob.createBEClasses("Empleado",
                "E:/Visual Studio Projects/Ramirez Alvarado Examen/BEL/");
        ob.createDAClasses("Empleado", "E:/Visual Studio Projects/Ramirez Alvarado Examen/DAL/");
        GenASPXpages web = new GenASPXpages(conn, "Empleado", tables, prefijo,
                "E:/Visual Studio Projects/Ramirez Alvarado Examen/WL/",
                "Empleado", "Empleado");
        web.genPage();
    }

    public static void main(String[] args) throws Exception {
//        runEmpleado();
//        runAlmacenTienda();
        runMatricula();
    }
}
