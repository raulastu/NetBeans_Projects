
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Escuela {
    private DB database;

    public Escuela(DB database) {
        this.database = database;
    }

    public void ingresarEscuelas(int n) throws SQLException {
        ResultSet rs = database.crSt().executeQuery("SELECT id_facultad FROM facultad");
        ArrayList<String> arrl = new ArrayList<String>();
        while (rs.next()) {
            arrl.add(rs.getString(1));
        }
        int nEscFac = n/arrl.size();
        int res = n%arrl.size();
        for (int i = 0; i<arrl.size(); i++) {
            if (res>0) {
                nEscFac++;
                res--;
            }else nEscFac = n/arrl.size();
            for (int j = 0; j<nEscFac; j++) {
                String q = "INSERT INTO Escuela ()VALUES "+
                        "(?,?,?)";
                PreparedStatement ps = new DB().getConn().prepareStatement(q);
                ps.setString(1, j+"-"+arrl.get(i));
                ps.setString(2, arrl.get(i));
                ps.setString(3, "Escuela "+j+"-"+arrl.get(i));
                ps.executeUpdate();
                ps.close();
            }
        }
        System.err.println("ingresarEscuelas ("+n+") Completed");
    }

    public void ingresarFacultades(int n) throws SQLException {
        for (int i = 0; i<n; i++) {
            String q = "INSERT INTO Facultad (id_facultad, nombre) VALUES "+
                    "(?,?)";
            PreparedStatement ps = database.getConn().prepareStatement(q);
            ps.setString(1, ""+i);
            ps.setString(2, "Facultad "+i);
            ps.executeUpdate();
            ps.close();
        }
        System.err.println("ingresarFacultades ("+n+") Completed");
    }
    public int getN() throws SQLException{
        ResultSet rs = database.crSt().executeQuery("SELECT COUNT(*) FROM escuela");
        rs.next();
        return rs.getInt(1);
    }
    public ArrayList<String> getIDs() throws SQLException{
        ArrayList<String> arrl = new ArrayList();
        ResultSet rs = database.crSt().executeQuery("SELECT id_escuela FROM Escuela");
        while(rs.next()){
            arrl.add(rs.getString(1));
        }
        rs.close();
        return arrl;
    }
    public static void main(String[] args) throws Exception {
        DB database = new DB();
        new Escuela(database).ingresarFacultades(3);
        new Escuela(database).ingresarEscuelas(10);
    }
}
