package sales;

import java.sql.*;

public class rC {
    
    public static Object[][] toArrObjForCliente(ResultSet rs,int rows,int cols, Class<?>[] types) throws SQLException{
        Object[][] obj=new Object[rows][cols];
        boolean go=rs.next();
        int i=0;
        while(go){
            for(int j=0;j<cols;j++){
                if(types[j].getName().equals("java.lang.Double"))
                    obj[i][j]=rs.getDouble(j+1);
                else if(types[j].getName().equals("java.lang.Integer"))
                    obj[i][j]=rs.getInt(j+1);
                else
                    obj[i][j]=rs.getString(j+1);
            }        
            /*obj[i][0]=rs.getString(1);
            obj[i][1]=rs.getString(2);
            obj[i][2]=rs.getDouble(3);
            obj[i][3]=rs.getInt(4);*/
            i++;
            go=rs.next();
        }
        rs.close();
        return obj;        
    }
    
    
    public rC() {
    }
    public static void showQuery(ResultSet rs) throws SQLException{
        boolean seguir = rs.next();
        //System.out.println("\nEMPLOYEE_ID\tFIRST_NAME" + "\tLASTNAME ");
        //sentencia.execute("begin sp_insert_producto('cacapedo2',12,100);end;");
        while(seguir){
                //System.out.println(rs.getString("idproducto"));
                System.out.println(rs.getString(1));
                seguir=rs.next();
        }
        //rs.close();
    }
    public static void main(String[] args) throws Exception {
        Connection cn = null;
        Statement sentencia = null;
        ResultSet rs = null;
        
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        String url = "jdbc:odbc:salesman";
        cn = DriverManager.getConnection(url);
        cn.setAutoCommit(true);
        sentencia = cn.createStatement();
        Console.run(new JFrameLogin(sentencia));        
    }
    
    
}
