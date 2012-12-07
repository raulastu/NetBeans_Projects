/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class Hojas {

    DB database;

    public Hojas(DB database) {
        this.database = database;
    }

    public void test() throws SQLException {
    }

    public void ingresarHIs(int id_concurso) throws SQLException {
        String[] al = new Postulante(database).getIDsGroupedByAulaNoOrdenado().toArray(new String[0]);
//        duplicar(al, 2, 3);

        String[] litocodigos = generarLCodigos(8, al.length);
        for (int i = 0; i<al.length; i++) {
            PreparedStatement ps = database.getConn().prepareStatement(
                    "insert INTO HI_ORIGINAL"+
                    "(litocodigo, id_concurso, codi_postulante)"+
                    "values (?,?,?)");
            ps.setString(1, litocodigos[i]);
            ps.setInt(2, id_concurso);
            ps.setString(3, al[i]);
            ps.executeUpdate();
            ps.close();
        }
        System.err.println("ingresarHIs (id_concurso="+id_concurso+") Completed");
    }

    private String[] generarLCodigos(int nDigitosEnLitocodigo, int n) {
        HashSet<String> set = new HashSet<String>(nDigitosEnLitocodigo);

        for (int i = 0; set.size()<n; i++) {
            String gen = "";
            for (int j = 0; j<nDigitosEnLitocodigo; j++) {
                gen += (int) (Math.random()*10);
            }
            set.add(gen);
        }
        return set.toArray(new String[0]);
    }

    public void ingresarHRs(int id_concurso, int nPreguntas) throws SQLException {
        String[] lcodigos = getLitocodigosHI(id_concurso).toArray(new String[0]);
        char[] dispo = "ABCDE".toCharArray();
        for (int i = 0; i<lcodigos.length; i++) {
            StringBuffer respuesta = new StringBuffer();
            for (int j = 0; j<nPreguntas; j++) {
                respuesta.append(dispo[(int) (Math.random()*(dispo.length))]);
            }
            PreparedStatement ps = database.getConn().prepareStatement(
                    "insert INTO HR_ORIGINAL"+
                    "(id_concurso, litocodigo, respuestas) "+
                    "values (?,?,?)");
            ps.setInt(1, id_concurso);
            ps.setString(2, lcodigos[i]);
            ps.setString(3, respuesta.toString());
            ps.executeUpdate();
            ps.close();
        }
        System.err.println("ingresarHRs (id_concurso="+id_concurso+" Completed");
    }

    void duplicar(String[] al, int nCasos, int nDuplicados) throws SQLException {
//        String[] al = new Postulante(database).getIDs().toArray(new String[0]);
        for (int i = 0; i<nCasos; i++) {
            int selected = (int) (Math.random()*(al.length));
            for (int j = 0; j<nDuplicados; j++) {
                int dupSelected = (int) (Math.random()*(al.length));
                al[dupSelected] = al[selected];
            }
        }
    }

    private ArrayList<String> getLitocodigosHI(int id_concurso) throws SQLException {
        ResultSet rs = database.crSt().executeQuery(
                "SELECT litocodigo from HI_original "+
                "WHERE id_concurso = "+id_concurso+" "+
                "ORDER BY correlativo");
        ArrayList<String> al = new ArrayList<String>();
        while (rs.next()) {
            al.add(rs.getString(1));
        }
        rs.close();
        return al;
    }

    private Integer[] getNroAulas(int id_concurso) throws SQLException {
        ArrayList<Integer> al = new ArrayList<Integer>();
        ResultSet rs = database.crSt().executeQuery(
                "SELECT numero_aula FROM postulante p, ubicacion u "+
                "where p.id_ubicacion = u.id_ubicacion AND "+
                "u.id_concurso = "+id_concurso);
        while (rs.next()) {
            al.add(rs.getInt(1));
        }
        rs.close();
        return al.toArray(new Integer[0]);
    }

    public static void main(String[] args) throws Exception {
        DB database = new DB();
        new Hojas(database).ingresarHIs(2);
//        new Hojas(database).ingresarHRs(1, 120);
        database.closeDB();
    }
}
