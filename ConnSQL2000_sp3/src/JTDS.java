
import java.sql.*;

public class JTDS {

    public static void main(String[] args) throws Exception {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:jtds:sqlserver://localhost:1433/TANTA_FACT;user=sa;password=sa");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from t_VentaDetalle");
        long i = System.currentTimeMillis();
        while (rs.next()) {
        //System.out.println(rs.getString(1));
        }
        rs.close();
        st.close();
        con.close();
        System.out.println(System.currentTimeMillis() - i);

    }
}
