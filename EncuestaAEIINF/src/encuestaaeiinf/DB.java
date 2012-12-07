package encuestaaeiinf;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    public Connection getConnection() {
        Connection cn = null;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String db = "E:/NetBeans Projects/EncuestaAEIINF/Encuesta_2007-2.mdb";
//            getClass().getResource("/imgs/=O!.JPG")
            String url = "jdbc:odbc:MS Access Database;DBQ=" + db;            
            cn = DriverManager.getConnection(url, "", "");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cn;
    }
}
