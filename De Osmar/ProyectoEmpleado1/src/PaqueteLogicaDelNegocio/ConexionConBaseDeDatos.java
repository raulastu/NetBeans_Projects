package PaqueteLogicaDelNegocio; 
import java.sql.Connection;
import java.sql.DriverManager;
public class ConexionConBaseDeDatos 
  {          
    public static Connection obtenerConexion() 
      {
        Connection connection=null;
        try 
          {
            String driver = "com.mysql.jdbc.Driver";
            String url    = "jdbc:mysql://localhost:3306/dbempleado";
            String user   = "root";
            String psw    = "12345";

            Class.forName(driver);                
            connection = DriverManager.getConnection(url,user,psw);                
           } 
        catch (Exception e) 
          {
                System.out.println("Error:"+e);
           }                                   
         return connection;
      }    
  }
