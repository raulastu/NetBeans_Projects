/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vstudionetutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rcuser
 */
public class Main {

    public static void executeScript(Connection con, String query) throws SQLException {
        try {
            con.createStatement().execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(query);
            throw ex;
        }
    }

    public static void executeBatch(Connection con, String[] scripts) throws SQLException {
        Statement st = con.createStatement();
        for (String queries : scripts) {
            st.addBatch(queries);
        }
        int a[] = st.executeBatch();
        st.clearBatch();
        st.close();
        for (int i = 0; i < a.length; i++) {
            System.out.println("updated " + a[i] + " rows");
        }
    }

    public static String loadScript(String path) {
        String res = "";
        try {
            Scanner sc = new Scanner(new File(path));
            while (sc.hasNext()) {
                res += sc.nextLine() + "\n";
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public static void runScripFile(Connection con, String file) throws SQLException {
        String[] ubigeoPeru = null;
        try {
            ubigeoPeru = loadScriptToArray(file);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (String string : ubigeoPeru) {
            executeScript(con, string);
        }
    }

    public static String[] loadScriptToArray(String path) throws IOException {
        ArrayList<String> al = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            System.err.println(path);
            while (br.ready()) {
                String line = br.readLine();
//                System.err.println("XX");
                al.add(line);
//                System.err.println(al.get(al.size() - 1));
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al.toArray(new String[al.size()]);
    }
    static String[] matriculaTables = {
        //batista
        "universidad",
        "facultad",
        "escuela",
        "plan_curricular",
        //paco
        "ciclo",
        "curso",
        "tipo_docente",
        "docente",
        "carga_lectiva",
        "tipo_carga_lectiva",
        "prerequisitos",
        //rc        
        "departamento",
        "provincia",
        "distrito",
        "alumno",
        "usuario",
        "sancionado",
        "tipo_sancion",
        "matricula",
        "matricula_detalle",
        //wilfer
        "tipo_pago",
        "pago",
        "semestre_academico",
        "wall",
        "usuario_docente",
        "administrativo",
        "tipo_administrativo"
    };

    public static void nextSteps(Connection con) throws Exception {
        String tablePrefix = "t_";
        String schema = "";
        String spPrefix = "stp_";
        String path = "E:/Matricula/DB/";
        GenMNT_Procedures ob = new GenMNT_Procedures(
                con, "Matricula", matriculaTables, spPrefix,
                path, "maintenance_procedures.sql", tablePrefix);
        String[] mantainProceduresScript = ob.makeMntProcedure(false).split("\\bGO\\b");
        int x = 0;
//        for (String tsql : mantainProceduresScript) {
//            x++;
//            executeScript(con, tsql);
//        }
        System.out.println("Created maintenance Procedures");

        // CREATE GET PROCEDURES
        GenSEL_Procedures getProcedures = new GenSEL_Procedures(
                con, "Matricula", matriculaTables, schema, path, "SEL_PROCEDURES.sql", tablePrefix);
        String[] getProceduresScripts = getProcedures.makeSelProcedures(false).toLowerCase().split("\\bgo\\b");
//        for (String tsql : getProceduresScripts) {
//            executeScript(con, tsql);
//        }
        PopulatingMatricula.populateALL();

        System.out.println("Database Populated");

        GenCSClasses classes = new GenCSClasses(con,
                "Matricula", matriculaTables, tablePrefix, spPrefix);
        classes.createBEClasses("Matricula", "E:/Matricula/Classes/BEL/");
        classes.createDAClasses("Matricula", "E:/Matricula/Classes/DAL/");
    }

    public static String[] recreateDatabase(String databaseName) {
        String dropDatabase[] = {"USE master",
            "if db_id('" + databaseName + "') is not null \n" + "ALTER DATABASE " + databaseName + " SET SINGLE_USER \n" + "WITH ROLLBACK IMMEDIATE",
            "if db_id('" + databaseName + "') is not null " +
            "DROP DATABASE " + databaseName,
            "CREATE DATABASE " + databaseName,
            "USE " + databaseName};
        return dropDatabase;
    }

    public static void runMatricula() throws Exception {
        Connection con = MasterConn.getConnection();
        String[] sqlScript = recreateDatabase("Matricula");
        // DROPING AND RECREATING THE DATABASE
        for (String string : sqlScript) {
            executeScript(con, string);
        }
        System.out.println("Database Recreated");

        // LOADING PHYSICAL MODEL
        String[] fisico = loadScript(
                "E:/Matricula/DB/ModeloFisico_Matricula.sql").toLowerCase().split("\\bgo\\b");
        for (String string : fisico) {
            executeScript(con, string);
//            System.out.println(string);
        }
        String[] otrosProc = loadScript(
                "E:/Matricula/DB/OTROS_PROCEDURES.SQL").toLowerCase().split("\\bgo\\b");
        for (String string : otrosProc) {
            executeScript(con, string);
        }
        runScripFile(con, "E:/Matricula/DB/ubigeoPeruInserts.sql");
        runScripFile(con, "E:/Matricula/DB/0 CICLOS.SQL");
        runScripFile(con, "E:/Matricula/DB/1 UNIV_FACULTADES_ESCUELAS.SQL");
        runScripFile(con, "E:/Matricula/DB/2 PLAN CURRICULAR 04 INFORMATICA.SQL");
        runScripFile(con, "E:/Matricula/DB/4 DOCENTES_TIPO_CARGA_LECTIVA.SQL");
        runScripFile(con, "E:/Matricula/DB/5 ALUMNO_MATRICULA.SQL");
        runScripFile(con, "E:/Matricula/DB/6 ADMINS.SQL");
        //runScripFile(con, "E:/Matricula/DB/SQLQuery1.sql");


        System.out.println("Database Tables created");
        nextSteps(con);

//        executeBatch(con, q);
    }

    public static void runAlmacenTienda() throws Exception {
        Connection con = MasterConn.getConnection();
        String[] sqlScript = recreateDatabase("AlmacenTienda");
        for (String string : sqlScript) {
            executeScript(con, string);
        }
        String[] fisico = loadScript("E:/Visual Studio Projects/Almacen Tienda/AlmacenTiendaFisico.sql").split("\\bgo\\b");
        for (String string : fisico) {
            executeScript(con, string);
        }
        String[] almacenTiendaTables = {
            "almacen",
            "inventario",
            "producto",
            "gasto_mensual",
            "transferencia",
            "tienda"
        };
        String prefijo = "t_";
        String procedurePrefix = "mnt_";
        GenMNT_Procedures ob = new GenMNT_Procedures(
                con, "AlmacenTienda", almacenTiendaTables, procedurePrefix,
                "E:/Visual Studio Projects/Almacen Tienda/", "Almacen_Tienda_mnt_procedures.sql", prefijo);
        String mnt[] = ob.makeMntProcedure(false).split("\\bGO\\b");
        for (String string : mnt) {
            executeScript(con, string);
        }
        String schema = "";
        GenSEL_Procedures ob2 = new GenSEL_Procedures(MasterConn.getConnection(),
                "AlmacenTienda", almacenTiendaTables, schema,
                "E:/Visual Studio Projects/Almacen Tienda/", "AlmacenTienda_GET_procedures.sql", prefijo);
        String[] get = ob2.makeSelProcedures(false).split("\\bGO\\b");
        for (String string : get) {
            executeScript(con, string);
        }
    }

    public static void runMaritza() throws SQLException, IOException {
        Connection conn = MasterConn.getConnection("Maritza", "", "");
        String[] tables = {
            "Pedido_Detalle",
            "Pedido",
            "Usuario_Tienda",
            "Productos_derivados"};
        String dbName = "Almacen";

        GenMNT_Procedures gen = new GenMNT_Procedures(conn, "Almacen", tables,
                "stp_", "E:/inteka/BD", "Martiza_MNT_PROCEDURES.sql", "");
        gen.makeMntProcedure(true);
        GenSEL_Procedures genSel = new GenSEL_Procedures(conn, dbName, tables,
                "", "E:/inteka/BD", "SEL_PROCS.sql", "");
        genSel.makeSelProcedures(false);
        GenCSClasses classes = new GenCSClasses(conn, "Almacen", tables, "", "stp_");
        classes.version = Version.v2005;
        classes.createBEClasses("Maritza", "E:/inteka/BD/BEL/");
        classes.createDAClasses("Maritza", "E:/inteka/BD/DAL/");
        GenASPXpages asp = new GenASPXpages(conn, "Almacen", tables, "", "E:/inteka/BD/WL/", "Maritza", "");
        asp.version = Version.v2005;
        asp.genPage();
    }

    public static void main(String[] args) throws Exception {
//        runMatricula();
        Connection con = MasterConn.getConnection();
        nextSteps(con);
//        runMaritza();
    //        runAlmacenTienda();
    }
}
