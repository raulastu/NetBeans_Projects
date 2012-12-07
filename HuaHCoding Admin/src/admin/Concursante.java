package admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Concursante {

    private Connection con;

    public Concursante(Connection con) {
        this.con = con;
    }

    public void registrarConcursante(String username, String pass, String name, String surname, int school, String email) throws SQLException {
        PreparedStatement st2 = con.prepareCall("CALL sp__registerConcursante(?,?,?,LOWER(?),?,?)");
        st2.setString(1, name);
        st2.setString(2, surname);
        st2.setInt(3, school);
        st2.setString(4, email);
        st2.setString(5, username);
        st2.setString(6, pass);
        st2.executeUpdate();
        st2.close();
    }    

    public boolean inscribirEnConcurso(String idUser, String idConcurso) {
        try {
            PreparedStatement call = con.prepareStatement(
                    "SELECT FC__enrollConcursante (?,?)");
            call.setString(1, idConcurso);
            call.setString(2, idUser);
            call.execute();
            call.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Concursante.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static void main(String[] args) {
        Connection con = new ConexionDB().getConnection();
        Concursante concursante = new Concursante(con);
    }
}
