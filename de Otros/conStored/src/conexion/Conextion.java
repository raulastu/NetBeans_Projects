/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VAN BASTEM
 */
public class Conextion {


 public static  Connection getConexion(String driver,String url,String user, String psw){
        try {

            Class.forName(driver);
            return DriverManager.getConnection(url,user,psw);

       } catch (SQLException ex) {
            Logger.getLogger(Conextion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conextion.class.getName()).log(Level.SEVERE, null, ex);
        }
           return null;
    }

}
