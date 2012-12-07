package cecocadb;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rCuser
 */
public class DB {

    private Connection con = null;
    private Statement stat;

    public DB() {
        loadConexion();
    }

    public Connection getConn() {
        return con;
    }

    public Statement crSt() throws SQLException {
        return con.createStatement();
    }

    public ResultSet call(String procName, String... args) throws SQLException {
        StringBuffer nsings = new StringBuffer("");
        for (int i = 0; i<args.length; i++) {
            nsings.append("?,");
        }
        nsings.deleteCharAt(nsings.length()-1);
        PreparedStatement ps = getConn().prepareCall("call "+procName+" ("+nsings+")");
        for (int i = 0; i<args.length; i++) {
            ps.setString(i+1, args[i]);
        }
        return ps.executeQuery();
    }

    public void loadConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cecoca", "root", "root");
            stat = con.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeDB() {
        try {
            stat.close();
            con.close();
            System.out.println("database closed");
        } catch (Exception ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
