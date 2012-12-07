
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Bridge {

    public static void main(String[] args) throws Exception {        
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con = DriverManager.getConnection("jdbc:odbc:dsnTantaFact");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from t_VentaDetalle");
        long i = System.currentTimeMillis();
        while (rs.next()) {
        //System.out.println(rs.getString(1));
        }
        rs.close();
        st.close();
        con.close();
        System.out.println(new Date().getTime() - i);
    }
}
