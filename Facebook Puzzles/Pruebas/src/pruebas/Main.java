package pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Main {

    public static Alumno getAlumno(String id) throws ClassNotFoundException, SQLException {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con = DriverManager.getConnection("jdbc:odbc:nnn", "sa", "");
        String query = "SELECT * FROM t_alumno WHERE id_alumno = '" + id + "'";
        ResultSet rs = con.createStatement().executeQuery(query);
        rs.next();
        Alumno x = new Alumno();
        x.id_alumno = rs.getInt("id_alumno");
        x.id_escuela = rs.getInt("id_escuela");
        x.cod_alumno = rs.getString(3);
        x.ape_pat = rs.getString("ape_paterno");
        x.nombres = rs.getString("nombres");
        return x;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Conexion
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con = DriverManager.getConnection("jdbc:odbc:nnn", "sa", "");


        //Obtener Conjunto de Resultados (Osea las filas)
        ResultSet rs1 = con.createStatement().executeQuery("SELECT COUNT(*) FROM t_alumno");
        rs1.next();
        int cantidad = rs1.getInt(1);

        Alumno[] arrayDeAlumnos = new Alumno[cantidad];

        int i = 0;

        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM t_alumno");
        while (rs.next()) {
            arrayDeAlumnos[i] = new Alumno();
            arrayDeAlumnos[i].id_alumno = rs.getInt(1);
            arrayDeAlumnos[i].id_escuela = rs.getInt(2);
            arrayDeAlumnos[i].cod_alumno = rs.getString(3);
            arrayDeAlumnos[i].ape_pat = rs.getString(4);
            arrayDeAlumnos[i].ape_mat = rs.getString(5);
            arrayDeAlumnos[i].nombres = rs.getString(6);
            i++;
        }

//        for (int j = 0; j < arrayDeAlumnos.length; j++) {
//            System.err.println(arrayDeAlumnos[j]);
//        }
//        JOptionPane.showMessageDialog(null, arrayDeAlumnos[0].nombres);
//
//
//        JOptionPane.showMessageDialog(null, arrayDeAlumnos[1].cod_alumno);

        Alumno algo = getAlumno("1");
        System.err.println(algo);

    }
}

