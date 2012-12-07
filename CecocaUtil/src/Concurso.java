
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class Concurso {

    DB database;

    Concurso(DB database) throws Exception {
        this.database = database;
    }

    void test() throws SQLException {
        ingresarSedes(3);
        ingresarModalidades(3);
        ingresarProcesos(1);
        crearConcurso(1, 1, 1, 1);
    }

    public void ingresarSedes(int n) throws SQLException {
        for (int i = 1; i<=n; i++) {
            PreparedStatement ps = database.getConn().prepareStatement(
                    "INSERT INTO sede(nominacion) values (?)");
            ps.setString(1, "Sede "+i);
            ps.executeUpdate();
            ps.close();
        }
        System.err.println("ingresarSedes ("+n+") Completed");
    }

    public void ingresarModalidades(int n) throws SQLException {
        for (int i = 1; i<=n; i++) {
            PreparedStatement ps = database.getConn().prepareStatement(
                    "INSERT INTO modalidad(nombre) values (?)");
            ps.setString(1, "Modalidad "+i);
            ps.executeUpdate();
            ps.close();
        }
        System.err.println("ingresarModalidades ("+n+") Completed");
    }

    public void ingresarProcesos(int n) throws SQLException {
        for (int i = 1; i<=n; i++) {
            PreparedStatement ps = database.getConn().prepareStatement(
                    "INSERT INTO proceso"+
                    "(descripcion, designacion, formula_ptje_min) "+
                    "values (?,?,?)");
            ps.setString(1, "Proceso "+i);
            ps.setString(2, "Proceso "+i);
            ps.setString(3, "promedio - 1.0 DS");
            ps.executeUpdate();
            ps.close();
        }
        System.err.println("ingresarProcesos ("+n+") Completed");
    }

    public void crearConcurso(int n, int id_proceso, int id_modal, int id_sede) throws SQLException {
        for (int i = 1; i<=n; i++) {
            PreparedStatement ps = database.getConn().prepareStatement(
                    "INSERT INTO concurso"+
                    "(id_proceso, id_modalidad, id_sede) "+
                    "values (?,?,?)");
            ps.setInt(1, id_proceso);
            ps.setInt(2, id_modal);
            ps.setInt(3, id_sede);
            ps.executeUpdate();
            ps.close();
        }
        System.err.println("crearConcurso ("+n+") Completed");
    }

    public static void main(String[] args) throws Exception {
        DB database = new DB();
        new Concurso(database).test();
    }
}
