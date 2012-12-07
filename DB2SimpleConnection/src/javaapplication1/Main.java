package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Main {

//    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
//        <property name="driverClassName"><value>COM.ibm.db2.jdbc.app.DB2Driver</value></property>
//        <property name="url"><value>jdbc:db2:SCTBD</value></property>
//        <property name="username"><value>db2admin</value></property>
//        <property name="password"><value>db2admin</value></property>
//
    public static String url = "jdbc:db2:MYDB";
    public static String dbdriver = "COM.ibm.db2.jdbc.app.DB2Driver";
    public static String username = "db2admin";
    public static String password = "db2admin";
    /**/
//    <!--bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
//        <property name="driverClassName"><value>com.ibm.as400.access.AS400JDBCDriver</value></property>
//        <property name="url"><value>jdbc:as400://MAQUI02</value></property>
//        <property name="username"><value>USR_SCT</value></property>
//        <property name="password"><value>SCT_USR</value></property>
//    </bean-->
    public static String dbdriver2 = "com.ibm.db2.jcc.DB2Driver";
    public static String url2 = "jdbc:db2://192.168.1.150:50000/MYDB";

    public static void appConn() {
        try {

            Class.forName(dbdriver);
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM AS400S41.COLOR");
            while (rs.next()) {
                String r = rs.getString(2);
                System.err.println(r);
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void driverConn() {
        try {
            Class.forName(dbdriver2);
            Connection conn = DriverManager.getConnection(url2, username, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM AS400S41.COLOR");
            while (rs.next()) {
                String r = rs.getString(2);
                System.err.println(r);
            }
            rs.close();
            st.close();

            {
                Statement st2 = conn.createStatement();
                ResultSet rs2 = st2.executeQuery("SELECT * FROM AS400S41.COLOR");
                while (rs2.next()) {
                    String r = rs2.getString(2);
                    System.err.println(r);
                }
                rs2.close();
                st2.close();
            }
            {
                Statement st2 = conn.createStatement();
                ResultSet rs2 = st2.executeQuery("SELECT * FROM AS400S41.COLOR");
                while (rs2.next()) {
                    String r = rs2.getString(2);
                    System.err.println(r);
                }
                rs2.close();
                st2.close();
            }
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Main() {
//        appConn();
        driverConn();
    }

    public static void main(String[] args) {
        new Main();
    }
}
