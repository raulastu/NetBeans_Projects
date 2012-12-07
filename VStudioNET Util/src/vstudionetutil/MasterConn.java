package vstudionetutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MasterConn {

    public static Connection getConnection() throws SQLException {

        Connection conn;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn = DriverManager.getConnection("jdbc:odbc:mat", "", "");
        return conn;
    }

    public static Connection getConnection(String dsName, String user, String pass) throws SQLException {

        Connection conn;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MasterConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn = DriverManager.getConnection("jdbc:odbc:" + dsName, user, pass);
        return conn;
    }
}
