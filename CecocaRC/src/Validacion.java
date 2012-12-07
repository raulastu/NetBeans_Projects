
import cecocadb.DB;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

/**
 *
 * @author rC
 */
public class Validacion {

    /**
     * @param args the command line arguments
     */
    DB database;

    public Validacion(DB database) {
        this.database = database;
    }

    public Object[] checkDuplicados(int id_concurso) throws SQLException {
        String[] ar = getHI_CodiPostulantes(id_concurso).toArray(new String[0]);
        /*
         * String = codi_postulante
         * Integer[] ocurrencias de codi_postulante
         */
        HashMap<String, Integer[]> map = new HashMap();
        for (int i = 0; i<ar.length; i++) {
            Integer[] d = map.get(ar[i]);
            if (d==null) {
                map.put(ar[i], new Integer[]{i});
            } else {
                Integer[] newArr = new Integer[d.length+1];
                newArr[newArr.length-1] = i;
                System.arraycopy(d, 0, newArr, 0, d.length);
                map.put(ar[i], newArr);
            }
        }
        for (Iterator it = map.keySet().iterator(); it.hasNext();) {
            Object object = it.next();
            Integer[] x = map.get(object);
            if (x.length==1) {
                it.remove();
            }
        }
        return new Object[]{map.size()==0, map};
    }

    /**
     * Método que verifica la consistencia HI-HR, esto es si existen
     * Hojas de Identificacion que no tengan su correspondiente Hoja de Respuestas ó viceversa 
     * (estas dos relacionadas por el <tt>litocodigo</tt>)
     *
     * @param id_concurso
     * @return un arreglo de objetos (de tamaño fijo 5) con los datos de la consistencia HI -> HR
     * <tt>{ok, alhi.size(), alhr.size(), hiSobrantes, hrSobrantes}</tt>. <p/>
     * Donde <tt>ok</tt> es verdadero si y solo si existe inconsistencia,
     * es decir si existe una Hoja de Identificacion que no tiene su correspondiente Hoja de Respuestas o viceversa.<p/>
     * <tt>alhi.size()</tt> y <tt>alhr.size()</tt> son el total de HIs y HRs correspondientemente.<p/>
     * <tt>hiSobrantes</tt> es un <tt>ArrayList&lt<>String[]<>&gt</tt> el arreglo de strings contiene siempre los siguientes datos
     * {correlativo+"", lcodigoHI, codi_postulante, aula}
     * <p><tt>hrSobrantes</tt> es un <tt>ArrayList&lt<>String<>&gt</tt>, el string contiene litocodigo ... INCOMPLETE
     *
     * @throws java.sql.SQLException
     */
    public Object[] checkConsistenciaHI_HR(int id_concurso) throws SQLException {
        if (false) {
            checkConsistenciaHI_HR(id_concurso); //checking jDoc
        }
        boolean ok = true;
        ArrayList<String> alhi = getHI_Litocodigos(id_concurso);
        ArrayList<String> alhr = getHR_Litocodigos(id_concurso);
        ArrayList<String[]> hiSobrantes = new ArrayList<String[]>();
        ArrayList<String> hrSobrantes = new ArrayList<String>();
        for (int i = 0; i<alhi.size(); i++) {
            String lcodigoHI = alhi.get(i);
            if (!alhr.contains(lcodigoHI)) {
                ResultSet rs = database.crSt().executeQuery(
                        "SELECT correlativo, codi_postulante from hi_original "+
                        "WHERE litocodigo = '"+lcodigoHI+"' AND "+
                        "correlativo = '"+(i+1)+"'");
                boolean chk = rs.next();
                assert chk;
                int correlativo = rs.getInt(1);
                String codi_postulante = rs.getString(2);
                rs.close();
                CallableStatement cs = database.getConn().prepareCall(
                        "call getAula('"+codi_postulante+"');");
                ResultSet rsAula = cs.executeQuery();
                String aula = rsAula.next() ? aula = rsAula.getString(1) : "Código no valido"; //default
                rsAula.close();
                hiSobrantes.add(new String[]{correlativo+"", lcodigoHI, codi_postulante, aula});
                ok = false;
            }
        }
        for (String lc : alhr) {
            if (!alhi.contains(lc)) {
                hrSobrantes.add(lc);
                ok = false;
            }
        }
        Object[] result = {ok, alhi.size(), alhr.size(), hiSobrantes, hrSobrantes};
        return result;
    }

    private ArrayList<String> getHI_Litocodigos(int id_concurso) throws SQLException {
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

    ArrayList<String> getHR_Litocodigos(int id_concurso) throws SQLException {
        ResultSet rs = database.crSt().executeQuery(
                "SELECT litocodigo from HR_original "+
                "WHERE id_concurso = "+id_concurso);
        ArrayList<String> al = new ArrayList<String>();
        while (rs.next()) {
            al.add(rs.getString(1));
        }
        rs.close();
        return al;
    }

    private ArrayList<String> getHI_CodiPostulantes(int id_concurso) throws SQLException {
        ResultSet rs = database.crSt().executeQuery(
                "SELECT codi_postulante from HI_original "+
                "WHERE id_concurso = "+id_concurso+"  ORDER BY NULL ");
        ArrayList<String> al = new ArrayList<String>();
        while (rs.next()) {
            al.add(rs.getString(1));
        }
        rs.close();
        return al;
    }

   
    static int guiones = 50;

    static String fillDashes(
            String t, int max) {
        char[] fill = new char[max-t.length()];
        fill(fill, '-');
        return (t+new String(fill));
    }

    public static void testHI_HR(DB database, int id_concurso) throws SQLException {
        Object[] results = new Validacion(database).checkConsistenciaHI_HR(id_concurso);
        if ((Boolean) results[0]) {
            System.err.println(fillDashes("Passed HI-HR test", guiones));
        } else {
            System.err.println(fillDashes("Failed HI-HR test", guiones));
            ArrayList<String[]> al = (ArrayList<String[]>) results[3];
            System.err.println("litocodigos en HI sobrantes: ");
            for (int i = 0; i<al.size(); i++) {
                System.err.println("\t"+deepToString(al.get(i)));
            }
            System.err.println("litocodigos en HR sobrantes: "+results[4]);
            System.err.println(fillDashes("-", guiones));
        }
        System.err.println("size of HIs: "+results[1]);
        System.err.println("size of HRs: "+results[2]);
    }

    public static void testDuplicados(DB database, int id_concurso) throws SQLException {
        Object[] obDup = new Validacion(database).checkDuplicados(id_concurso);
        if ((Boolean) obDup[0]) {
            System.err.println("Passed Duplicados test");
        } else {
            System.err.println(fillDashes("Failed Duplicados test", guiones));
            HashMap<String, Integer[]> map = (HashMap<String, Integer[]>) obDup[1];
            for (String string : map.keySet()) {
                System.err.println(string+" "+Arrays.deepToString(map.get(string)));
            }

            System.err.println(fillDashes("-", guiones));
        }
    }

    public static void main(String[] args) throws SQLException {
        DB database = new DB();
        long a = System.currentTimeMillis();
//        testHI_HR(database, 1);
//        testDuplicados(database, 1);
//        testAledaños(database, "omg");
        database.closeDB();
        System.err.println(System.currentTimeMillis()-a);
//        long b = System.currentTimeMillis();
//        DB db2 = new DB();
//        System.out.println(new Validacion(db2).HI_HR());
//        db2.closeDB();
//        System.out.println(System.currentTimeMillis()-b);
    }
}
