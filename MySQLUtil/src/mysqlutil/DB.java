package mysqlutil;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
        LoadConexion();
    }

    public Connection getConn() {
        return con;
    }
    public Statement crSt() throws SQLException {
        return con.createStatement();
    }

    public void LoadConexion() {
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
        } catch (Exception ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

        try {
//                //System.out.println(System.currentTimeMillis());
//                long l = System.currentTimeMillis();
//                PreparedStatement ps = new ConexionDB().getConnection().prepareStatement("INSERT INTO FILEF (INPUT) VALUES(?)");
//                File file = new File("C:/OMG.in");
//                fis = new FileInputStream(file);
//                //byte[] archivo = new byte[(int) file.length()];
//                //fis.read(archivo);
//                //ps.setBytes(1, archivo);
//                ps.setBinaryStream(1, fis, (int) file.length());
//                ps.executeUpdate();
//                System.out.println(System.currentTimeMillis() - l);
            ResultSet rs1 = new DB().getConn().createStatement().executeQuery("SELECT INPUT FROM FILEF");
            rs1.next();
            byte[] bytes = rs1.getBytes(1);
            rs1.close();
            FileOutputStream fos = new FileOutputStream(new File("C:/CACAPEDO.out"));
            fos.write(bytes);
            fos.close();

        } catch (IOException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
