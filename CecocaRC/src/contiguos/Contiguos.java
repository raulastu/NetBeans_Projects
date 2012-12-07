/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contiguos;

import cecocadb.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Contiguos {

    DB database;

    public Contiguos(DB database) {
        this.database = database;
    }

    /**
     *
     * @param codiPostulante codigo del postulante del cual se buscará sus contiguos
     * @return {@code String[2][2]} donde [x][0] es el codiPostulante, y [x][1] es el nroAula del x
     *          donde x puede ser 0 (Anterior) o 1 (Siguiente)
     * @throws java.sql.SQLException
     */
    public String[][] getContiguosValidos(String codiPostulante) throws SQLException {
        if (false) {
            getContiguosValidos(codiPostulante); //jdoc
        }
        String query = "select correlativo from HI_original " +
                "where codi_postulante = '" + codiPostulante + "'";
        ResultSet rs = database.crSt().executeQuery(query);
        boolean ok = rs.next();
        System.out.println(query);
        assert ok;
        int current = rs.getInt(1);
        rs.close();

        Statement st2 = database.crSt();
        ResultSet rsCount = st2.executeQuery(
                "select count(*) from HI_Original");
        ok = rsCount.next();
        assert ok;
        long total = rsCount.getInt(1);
        System.out.println(total);
        rsCount.close();
        st2.close();
        //
        String[][] contiguos = new String[2][2];
        Arrays.fill(contiguos, new String[]{"-", "-"});

        int backward = current - 1;
        while (backward > 0) {
            ResultSet rs2 = database.crSt().executeQuery(
                    "select " +
                    "p.codi_postulante, u.numero_aula " +
                    "from Postulante p, ubicacion u, hi_original hi " +
                    "where u.id_ubicacion = p.id_ubicacion AND " +
                    "hi.codi_postulante = p.codi_postulante AND " +
                    "hi.correlativo = " + backward);
            if (rs2.next()) {
                contiguos[0][0] = rs2.getString(1);
                contiguos[0][1] = rs2.getString(2);
                break;
            } else {
                backward--;
            }
            rs2.close();
        }
        int forward = current + 1;
        while (forward <= total) {
            ResultSet rs3 = database.crSt().executeQuery(
                    "select p.codi_postulante, u.numero_aula " +
                    "from Postulante p, ubicacion u, hi_original hi " +
                    "where u.id_ubicacion = p.id_ubicacion AND " +
                    "hi.codi_postulante = p.codi_postulante AND " +
                    "hi.correlativo = " + forward);
            if (rs3.next()) {
                contiguos[1] = new String[]{rs3.getString(1), rs3.getString(2)};
                break;
            } else {
                forward--;
            }
            rs3.close();
        }
        return contiguos;
    }

    public static void testContiguos(DB database, String codi_postulante) throws SQLException {
        Contiguos val = new Contiguos(database);
        String[][] map = val.getContiguosValidos(codi_postulante);
        System.err.print("Anterior: ");
        System.err.println("{codi_postulante " + map[0][0] + " aula: " + map[0][1]);
        System.err.print("Siguiente: ");
        System.err.println("{codi_postulante " + map[1][0] + " aula: " + map[1][1]);
    }

    public static void main(String[] args) throws Exception {
        DB database = new DB();
        long a = System.currentTimeMillis();
        testContiguos(database, "0-0 150");
        System.out.println(System.currentTimeMillis() - a);
        database.closeDB();
    }
}
