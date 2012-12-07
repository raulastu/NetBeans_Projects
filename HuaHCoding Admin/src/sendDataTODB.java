
import admin.ConexionDB;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;



public class sendDataTODB {
     public static void main(String[] args) throws Exception{
          ConexionDB con = new ConexionDB();
          PreparedStatement statement = con.getConnection().prepareStatement(
                  "INSERT INTO CACA(omg) VALUES (?)");
          File file=new File("C:/RegexTestHarness.java");
          FileInputStream fis = new FileInputStream(file);
          byte[] caca = new byte[(int)file.length()];
          fis.read(caca);
          fis.close();
          System.out.println(new String(caca));
          statement.setBytes(1, caca);
          statement.executeUpdate();
          
          statement.close();
    }

}
