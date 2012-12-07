
import java.*;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class SQL2000 {

    private java.sql.Connection con = null;
    private final String url = "jdbc:microsoft:sqlserver://";
    private final String serverName = "localhost";
    private final String portNumber = "1433";
    private final String databaseName = "TANTA_FACT";
    private final String userName = "sa";
    private final String password = "sa";
    // Informs the driver to use server a side-cursor, 
    // which permits more than one active statement 
    // on a connection.
    private final String selectMethod = "cursor";

    // Constructor
    public SQL2000() {
    }

    private String getConnectionUrl() {
        return url + serverName + ":" + portNumber + ";databaseName=" + databaseName + ";selectMethod=" + selectMethod + ";";
    }

    private java.sql.Connection getConnection() {
        try {
            Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
            con = java.sql.DriverManager.getConnection(getConnectionUrl(), userName, password);
            if (con != null) {
                System.out.println("Connection Successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Trace in getConnection() : " + e.getMessage());
        }
        return con;
    }

    /*
    Display the driver properties, database details 
     */
    public void displayDbProperties() {
        java.sql.DatabaseMetaData dm = null;
        java.sql.ResultSet rs = null;
        try {
            con = this.getConnection();
            if (con != null) {
                dm = con.getMetaData();
                System.out.println("Driver Information");
                System.out.println("\tDriver Name: " + dm.getDriverName());
                System.out.println("\tDriver Version: " + dm.getDriverVersion());
                System.out.println("\nDatabase Information ");
                System.out.println("\tDatabase Name: " + dm.getDatabaseProductName());
                System.out.println("\tDatabase Version: " + dm.getDatabaseProductVersion());
                System.out.println("Avalilable Catalogs ");
                rs = dm.getCatalogs();
                while (rs.next()) {
                    System.out.println("\tcatalog: " + rs.getString(1));
                }
                rs.close();
                rs = null;
                closeConnection();
            } else {
                System.out.println("Error: No active Connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dm = null;
    }

    private void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
            con = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        
        Driver d = (Driver) Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();        
        java.sql.Connection con = DriverManager.getConnection(
                "jdbc:microsoft:sqlserver://localhost:1433;databaseName=TANTA_FACT;user=sa;password=sa");
        Statement st= con.createStatement();
        //Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM t_VentaDetalle");
        long i = System.currentTimeMillis();
        while (rs.next()) {
        //System.out.println(rs.getString(1));
        }
        st.close();
        rs.close();
        con.close();
        System.out.println(System.currentTimeMillis() - i);
    }
}
