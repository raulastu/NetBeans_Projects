
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

/**
 *
 * @author rC
 */
public class Postulante {

    int MIN = 12;
    private DB database;

    public Postulante(DB database) {
        this.database = database;
    }

    public void test() throws SQLException {
        database = new DB();
//        new Escuela().ingresarFacultad(3);
//        new Escuela().ingresar(10);
//        ingresar(10000);
        setAulas(26, 1, 1); //enviar alumnos por aula
        shufflePostulantes();
        database.closeDB();
    }

    public void ingresar(int idConcurso, int n) throws SQLException {
        int nroEscuelas = new Escuela(database).getN();
        if (n/MIN<nroEscuelas) {
            System.err.println("debe ingresar mas postulantes");
            System.out.println("MIN = "+MIN);
            System.out.println("nroEscuelas = "+nroEscuelas);
            return;
        }
        int totalMinimos = MIN*nroEscuelas;
        ArrayList<String> arrl = new Escuela(database).getIDs();
        Collections.shuffle(arrl);
        LinkedHashMap<String, Integer> mapEscuelas = new LinkedHashMap();
        int indecisos = n-totalMinimos;
        int[] omg = new int[arrl.size()];
        fill(omg, 0);
        for (int i = 0; i<indecisos; i++) {
            omg[(int) (Math.random()*(arrl.size()))]++;
        }
        int t = 0;
        for (int i = 0; i<arrl.size(); i++) {
            mapEscuelas.put(arrl.get(i), MIN+omg[i]);
            t += MIN+omg[i];
        }
        assert t==n : "n = "+n+" t="+t;
        System.out.println("t = "+t);
        System.out.println(mapEscuelas);
        for (String i : mapEscuelas.keySet()) {
            int nroPostulantes = mapEscuelas.get(i);
            for (int j = 0; j<nroPostulantes; j++) {
                String q = "INSERT INTO Postulante "+
                        "(id_concurso, codi_postulante, id_escuela, ape_paterno, "+
                        "ape_materno, nombres, sexo, prom_colegio)"+
                        "values (?,?,?,?,?,?,?,?)";
                PreparedStatement s = database.getConn().prepareStatement(q);
                s.setInt(1, idConcurso);
                s.setString(2, i+" "+j);
                s.setString(3, i+"");
                s.setString(4, "ap"+i+" "+j);
                s.setString(5, "am"+i+" "+j);
                s.setString(6, "n"+i+" "+j);
                s.setInt(7, (int) (Math.random()*2));
                s.setDouble(8, (11+Math.random()*9));
                s.executeUpdate();
                s.close();
            }
        }
        System.err.println("Ingresar "+n+" Postulantes Completed");
    }

    public int getN() throws SQLException {
        ResultSet rs = database.crSt().executeQuery("select count(*) from postulante");
        rs.next();
        int r = rs.getInt(1);
        rs.close();
        return r;
    }

    public ArrayList<String> getIDs() throws SQLException {
        ArrayList<String> arrl = new ArrayList();
        ResultSet rs = database.crSt().executeQuery("SELECT codi_postulante FROM postulante");
        while (rs.next()) {
            arrl.add(rs.getString(1));
        }
        rs.close();
        return arrl;
    }

    public ArrayList<String> getIDsOrderByAulaAsc() throws SQLException {
        ArrayList<String> arrl = new ArrayList();
        ResultSet rs = database.crSt().executeQuery(
                "SELECT codi_postulante FROM postulante p, ubicacion u "+
                "where p.id_ubicacion = u.id_ubicacion "+
                "ORDER BY numero_aula ASC");
        while (rs.next()) {
            arrl.add(rs.getString(1));
        }
        rs.close();
        return arrl;
    }

    public ArrayList<String> getIDsGroupedByAulaNoOrdenado() throws SQLException {
        ArrayList<String> aulas = new ArrayList<String>();
        ResultSet rs2 = database.crSt().executeQuery(
                "SELECT numero_aula from ubicacion;");
        while (rs2.next()) {
            aulas.add(rs2.getString(1));
        }
        rs2.close();
        Collections.shuffle(aulas);

        ArrayList<String> ids = new ArrayList<String>();
        for (int i = 0; i<aulas.size(); i++) {
            ResultSet rs = database.crSt().executeQuery(
                    "SELECT codi_postulante FROM postulante p, ubicacion u "+
                    "where p.id_ubicacion = u.id_ubicacion AND "+
                    "numero_aula = '"+aulas.get(i)+"'");
            while (rs.next()) {
                ids.add(rs.getString(1));
            }
            rs.close();
        }
        return ids;
    }

    public void setAulas(int alumnosAula, int id_concurso, int id_pabellon) throws SQLException {
        int nroPostulantes = getN();
        if (nroPostulantes<alumnosAula) {
            System.err.println("Alumnos por aula mayor que total postulantes");
            return;
        }
        //gen aulas        
        TreeMap<Integer, Integer> map = new TreeMap();
        int nroAulas = nroPostulantes/alumnosAula;
        for (int i = 1; i<=nroAulas; i++) {
            map.put(i, alumnosAula);
        }
        int rest = nroPostulantes%alumnosAula;
        for (int x = 1, i = 0; i<rest; i++, x++) {
            Integer au = map.get(x);
            map.put(x, au+1);
        }
        //todos en el pabellon 1
        for (Integer integer : map.keySet()) {
            int capacidad = map.get(integer);
            PreparedStatement ps = database.getConn().prepareStatement(
                    "INSERT INTO Ubicacion(numero_aula, id_pabellon, capacidad, id_concurso)"+
                    " values (?,?,?,?);");
            ps.setInt(1, integer);
            ps.setInt(2, id_pabellon);
            ps.setInt(3, capacidad);
            ps.setInt(4, id_concurso);
            ps.executeUpdate();
            ps.close();
        }
        System.err.println("setAulas Completed");
//        System.out.println("nro Aulas = "+map.size()+" aulas :"+map);
    }

    public void shufflePostulantes() throws SQLException {
        ResultSet rs = database.crSt().executeQuery(
                "SELECT id_ubicacion, capacidad from ubicacion");
        HashMap<Integer, Integer> mapAulas = new HashMap();
        while (rs.next()) {
            mapAulas.put(rs.getInt(1), rs.getInt(2));
        }
        rs.close();
        ArrayList<String> arrl = getIDs();
        Collections.shuffle(arrl);
//        String[] ar=arrl.toArray(new String[0]);
        int x = 0;
        for (Integer idUbicacion : mapAulas.keySet()) {
            int capacidad = mapAulas.get(idUbicacion);
            for (int i = 0; i<capacidad; i++) {
                String codiPost = arrl.get(x++);
                PreparedStatement ps = database.getConn().prepareStatement(
                        "UPDATE postulante "+
                        "SET id_ubicacion = ? "+
                        "WHERE codi_postulante  = ?");
                ps.setInt(1, idUbicacion);
                ps.setString(2, codiPost);
                ps.executeUpdate();
                ps.close();
            }
        }
        System.out.println(arrl.size()-x);
        //Start Checking assignments
//        for (Integer string : mapAulas.keySet()) {
//            ResultSet rs2 = new DB().crSt().executeQuery(""+
//                    "SELECT COUNT(*) from postulante where id_Ubicacion = "+string);
//            rs2.next();
//            int totalDB = rs2.getInt(1);
//            assert mapAulas.get(string)==totalDB : "arr ="+mapAulas.get(string)+" bd ="+totalDB;
//        }
        //End Checking assignments
        System.err.println("sorteo de Postulantes Completed");
    }

    public void insertarPabellones(int n) throws SQLException {
        for (int i = 0; i<n; i++) {
            PreparedStatement ps1 = database.getConn().prepareStatement(
                    "INSERT INTO Pabellon(nombre) values (?);");
            ps1.setString(1, "Pabellon "+i);
            ps1.executeUpdate();
            ps1.close();
        }
    }

    public static void main(String[] args) throws Exception {
        DB database = new DB();
        new Postulante(database).test();
        database.closeDB();
    }
}
