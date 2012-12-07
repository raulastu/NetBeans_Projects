/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package postgresqlconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rCUser
 */
public class PostgreSQLConn {

    public PostgreSQLConn() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/omfg", "postgres", "cacapedo");
//            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLConn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PostgreSQLConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        Connection con = PostgreSQLConn.getConnection();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM public.USER");
        rs.next();
        System.err.println(rs.getString(2));
    }
}
