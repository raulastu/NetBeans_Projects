package admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rC
 */
public class ConexionDB {

    private Connection con = null;

    public ConexionDB() {
        loadConexion();
    }

    public Connection getConnection() {
        return con;
    }

    public void loadConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/huahdb", "root", "root");            
        } catch (Exception ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeDB() {
        try {
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        try {
            ResultSet rs1 = new ConexionDB().getConnection().createStatement().executeQuery("SELECT INPUT FROM FILEF");
            rs1.next();
            byte[] bytes = rs1.getBytes(1);
            rs1.close();
            FileOutputStream fos = new FileOutputStream(new File("C:/CACAPEDO.out"));
            fos.write(bytes);
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
