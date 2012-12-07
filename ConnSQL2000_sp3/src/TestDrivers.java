
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDrivers {

    static void insertRows(int numRows, Connection cnn) throws Exception {
        Statement stmt = cnn.createStatement();

        while (numRows-- > 0) {
            stmt.executeUpdate("INSERT INTO Students(STAGE) VALUES(50)");
        }
        stmt.close();
        System.out.println("Inserted ");
    }

    public static void main(String[] args) throws Exception {
        Connection dbConn = null;
        System.out.println("Loading underlying JDBC driver...");
        try {
            //Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            //Class.forName("net.sourceforge.jtds.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // 2. Establish a database connection.

        try {
            //dbConn = DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost:1433;databaseName=TANTA_FACT;user=sa;password=sa;");
            dbConn = DriverManager.getConnection("jdbc:odbc:dsnTantaFact");
            //dbConn = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433/TANTA_FACT;user=sa;password=sa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long start = System.currentTimeMillis();
        insertRows(50000, dbConn);
        start = System.currentTimeMillis() - start;
        System.out.println(start);
        dbConn.close();
    }
}
