package inexistentes;

import cecocadb.DB;
import contiguos.Contiguos;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Inexistencia {

    private DB database;
    private int idUltimoGrupo = 0;
    HashMap<Integer, IRGrupo> grupos;
    private boolean debugALot = true;
    String comment = "";

    public Inexistencia() {

    }

    public void start() throws SQLException {
        ArrayList[] list = checkCodiPostulanteInexistentes(1);
        ArrayList<Inexistente> inexistentes = list[0];
        ArrayList<Real> reales = list[1];
        processTestCase(inexistentes, reales);
    }

    public void checkNoRealesDuplicadosGrupos(HashMap<Integer, IRGrupo> grupos) {
        ArrayList<Real> temp = new ArrayList<Real>();
        for (Integer integer : grupos.keySet()) {
            assert Collections.disjoint(temp, grupos.get(integer).getReales());
            temp.addAll(grupos.get(integer).getReales());
        }
    }

    public void checkNoExistentesDuplicadosGrupos(HashMap<Integer, IRGrupo> grupos) {
        ArrayList<Inexistente> temp = new ArrayList<Inexistente>();
        for (IRGrupo grupo : grupos.values()) {
            assert Collections.disjoint(temp, grupo.getInexistentes());
            temp.addAll(grupo.getInexistentes());
        }
    }

    public void checkSizeOfGrupos(HashMap<Integer, IRGrupo> grupos, int nInex, int nReales) {
        int myInexs = 0, r = 0;
        for (IRGrupo grupo : grupos.values()) {
            for (Inexistente inexistente : grupo.getInexistentes()) {
                if (inexistente.getEstadoEspecial() == null) {
                    myInexs++;
                }
            }
            r += grupo.getReales().size();
        }
        assert nInex == myInexs : "nInex" + nInex + " = enGrupo" + myInexs;
        if (!debugALot) {
            System.err.println("Passed tamaño de Inexistentes: nInex" + nInex + " = enGrupo" + myInexs);
        }
        assert nReales == r : "nReales=" + nReales + " - grupoReales=" + r;
        if (!debugALot) {
            System.err.println("Passed tamaño de Reales: nReales=" + nReales + " - grupoReales=" + r);
        }
//        checkIgualdadInexReales(grupos);
    }

    public void checkIgualdadInexReales(HashMap<Integer, IRGrupo> grupos) {
        for (IRGrupo iRGrupo : grupos.values()) {
            assert iRGrupo.getInexistentes().size() == iRGrupo.getReales().size() : "[" + iRGrupo.getIdGrupo() +
                    "] inex=" + iRGrupo.getInexistentes() + " reales=" + iRGrupo.getReales().size();
            if (!debugALot) {
                System.err.println("Passed Igualdad de tamaños inex-reales: " + iRGrupo.getInexistentes().size() + " " + iRGrupo.getReales().size());
            }
        }
    }

    public boolean checkInexMayorReales(HashMap<Integer, IRGrupo> grupos, ArrayList[] ir) {
        boolean ok = true;
        ArrayList<Inexistente> list = new ArrayList<Inexistente>();
        ArrayList<Real> listr = new ArrayList<Real>();
        for (IRGrupo iRGrupo : grupos.values()) {
            if (iRGrupo.getInexistentes().size() > iRGrupo.getReales().size()) {
                ok = false;
                list.addAll(iRGrupo.getInexistentes());
                listr.addAll(iRGrupo.getReales());
            }
        }
        if (!ok) {
            comment = "Cantidad de Inexistentes mayor que Cantidad de Reales en un grupo.\n" +
                    "No existen opciones reales para los siguientes inexistentes \n" + list + "\n" +
                    "Posibles Causas : \n" +
                    "- Se ha asignado erroneamente a un postulante (en el padron) el estado de ausente";
            if (!debugALot) {
                JTextArea labMsg = new JTextArea(comment);
                labMsg.setEditable(false);
                PanVerInexReales ms = new PanVerInexReales(ir[0], ir[1], list, listr);
                ms.add(labMsg, BorderLayout.NORTH);
                showCheckingFailed(ms);
            }
        }
        return ok;
    }

    public boolean checkRequerimientos(HashMap<Integer, IRGrupo> grupos, ArrayList[] ir) {
        boolean ok = true;
        ArrayList<String> aulasInsatesfechas = new ArrayList<String>();
        ArrayList<Inexistente> inexIns = new ArrayList<Inexistente>();
        ArrayList<Real> realIns = new ArrayList<Real>();
        for (IRGrupo iRGrupo : grupos.values()) {
            if (iRGrupo.getRequerimientos().size() > 0) {
                ok = false;
                aulasInsatesfechas.addAll(iRGrupo.getRequerimientos());
                inexIns.addAll(iRGrupo.getInexistentes());
                realIns.addAll(iRGrupo.getReales());
            }
        }
        if (!ok) {
            comment = "No se satisfacen los requerimientos de los inexistentes.\n" +
                    "No existen opciones reales para algunos inexistentes \n" +
                    "Faltan Postulantes en Padron que dieron examen las aulas: " + aulasInsatesfechas + "\n" +
                    "Posibles Causas : \n" +
                    "- Se ha asignado erroneamente a un postulante (en el padron) el estado de ausente \n" +
                    "- Un postulante B ingreso el codigo de un Postulante A y este postulante A ingreso un codigo inexistente";
            if (!debugALot) {
                JTextArea labMsg = new JTextArea(comment);
                labMsg.setEditable(false);
                PanVerInexReales ms = new PanVerInexReales(ir[0], ir[1], inexIns, realIns);
                ms.add(labMsg, BorderLayout.NORTH);
                showCheckingFailed(ms);
            }
        }
        return ok;
    }

    public void showCheckingFailed(JPanel msg) {
        JOptionPane.showMessageDialog(null, msg, "Verificacion Falló", JOptionPane.ERROR_MESSAGE, null);
    }

    public void showCheckingFailed(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Verificacion Falló", JOptionPane.ERROR_MESSAGE, null);
    }

    public IRGrupo[] getArrayGrupos() {
        IRGrupo[] r = grupos.values().toArray(new IRGrupo[0]);
        Arrays.sort(r, new ComparatorGrupos());
        return r;
    }

    public Inexistencia(DB database) throws SQLException {
        this.database = database;
    }

    public int getIdGrupo() {
        return idUltimoGrupo;
    }

    static class ComparatorGrupos implements Comparator {

        public int compare(Object o1, Object o2) {
            IRGrupo inex1 = (IRGrupo) o1;
            IRGrupo inex2 = (IRGrupo) o2;
            return inex1.getReales().size() - inex2.getReales().size();
        }
    }

    /**
     * @param idConcurso id del concurso.
     * @return {@code ArrayList[2]} :<br/>
     *     {@code ArrayList[0] : ArrayList<Inexistente>}<br/>
     *      {@code ArrayList[1] : ArrayList<Real>}
     * 
     * @throws java.sql.SQLException
     */
    public ArrayList[] checkCodiPostulanteInexistentes(int idConcurso) throws SQLException {
        ArrayList<String> padron = getCodiPostulantes(idConcurso);
        TreeMap<Integer, String> hi = getHICodiPostulantes(idConcurso);

        ArrayList<Inexistente> inexistentes = new ArrayList<Inexistente>();
        ArrayList<Real> reales = new ArrayList<Real>();

        for (Integer correlativo : hi.keySet()) {
            String hiCodPost = hi.get(correlativo);
            if (!padron.contains(hiCodPost)) {
                String[][] contiguos = new Contiguos(database).getContiguosValidos(hiCodPost);
                String aulaAnt = contiguos[0][1];
                String aulaSig = contiguos[1][1];
                inexistentes.add(
                        new Inexistente(correlativo, hiCodPost,
                        aulaAnt, aulaSig));
            }
        }
        for (String codPostPadron : padron) {
            if (!hi.containsValue(codPostPadron)) {
                ResultSet rs = database.call("getNombreCompleto", codPostPadron, idConcurso + "");
                boolean ok = rs.next();
                assert ok;
                String nombreCompleto = rs.getString(1);
                rs.close();
                rs = database.call("getAula", codPostPadron, idConcurso + "");
                ok = rs.next();
                assert ok;
                reales.add(new Real(codPostPadron, rs.getString(1), nombreCompleto));
                rs.close();
            }
        }
        return new ArrayList[]{inexistentes, reales};
    }

    public void emparejarUnicoEnAula(ArrayList<Inexistente> inexistentes, ArrayList<Real> reales) {
        HashSet<Inexistente> uniques = new HashSet<Inexistente>();
        HashMap<String, ArrayList<Inexistente>> frec = new HashMap<String, ArrayList<Inexistente>>();
        for (Inexistente inex : inexistentes) {
            if (inex.isMismaAula() && inex.getPareja() == null) {
                String aula = inex.getAnteriorAula();
                ArrayList<Inexistente> hs = frec.get(aula);
                if (hs == null) {
                    ArrayList<Inexistente> nuevo = new ArrayList<Inexistente>();
                    nuevo.add(inex);
                    frec.put(aula, nuevo);
                } else {
                    hs.add(inex);
                }
            }
        }
        for (String string : frec.keySet()) {
            if (frec.get(string).size() == 1) {
                uniques.add(frec.get(string).get(0));
            }
        }
//        System.out.println("inex uniques"+uniques);
        for (Inexistente inexistente : uniques) {
            int c = 0;
            Real realPareja = null;
            for (Real real : reales) {
                if (real.getAula().equals(inexistente.getAnteriorAula())) {
                    c++;
                    realPareja = real;
                }
            }
            if (c == 1) {
                if (!debugALot) {
                    System.err.println("emparejarUnicoEnAula " + inexistente.getAnteriorAula());
                }
                assert realPareja != null;
                realPareja.getCandidatos().add(inexistente);
                realPareja.setPareja(inexistente);
                inexistente.setPareja(realPareja);
            }
        }
    }

    public void emparejarRealToInex(ArrayList<Inexistente> inexistentes, ArrayList<Real> reales, int nEmparejados) {
        for (Real real : reales) {
            if (real.getCandidatos().size() == 0) {
                for (Inexistente inexistente : inexistentes) {
                    if (inexistente.getPareja() == null) {
                        if (real.getAula().equals(inexistente.getAnteriorAula()) ||
                                real.getAula().equals(inexistente.getSiguienteAula())) {
                            real.getCandidatos().add(inexistente);
                            inexistente.getCandidatos().add(real);
                        }
                    }
                }
            }
        }
        int contador = 0;
        ArrayList<Real> realesConMasQueUnCandidato = new ArrayList<Real>();
        for (Real real : reales) {
            if (real.getPareja() == null &&
                    real.getCandidatos().size() == 1 &&
                    real.getCandidatos().get(0).getCandidatos().size() == 1) {
                contador++;
                real.getCandidatos().get(0).setPareja(real);
                real.setPareja(real.getCandidatos().get(0));
                if (!debugALot) {
                    System.err.println("emparejarRealToInex" + real);
                }
            } else if (real.getCandidatos().size() > 1) {
                realesConMasQueUnCandidato.add(real);
            } else {
//                System.err.println("real.getCandidatos().size()"+real.getCandidatos().size());
            }
        }
        if (contador > nEmparejados) {
            for (Real real : realesConMasQueUnCandidato) {
                real.getCandidatos().clear();
            }
            emparejarRealToInex(inexistentes, reales, contador);
        } else {
//            System.out.println("no mas");
            return;
        }
    }

    private HashMap<Integer, IRGrupo> crearGrupos(ArrayList<Inexistente> inexistentes,
            ArrayList<Real> reales) {
        HashMap<Integer, IRGrupo> myGrupos = new HashMap<Integer, IRGrupo>();
        ArrayList<Inexistente> visitados = new ArrayList<Inexistente>();
        /**
         * Agregar Inexistentes a grupos
         */
        for (Inexistente inexistente : inexistentes) {
            if (inexistente.getPareja() != null) {   // agregar parejas
                IRGrupo grupo = new IRGrupo(idUltimoGrupo);
                grupo.addPareja(inexistente, inexistente.getPareja());
                myGrupos.put(idUltimoGrupo++, grupo);
//                System.out.println("pareja creada en grupo"+inex+" - "+inex.getPareja());
                continue;
            } else {
                boolean nuevoGrupo = true;
                String ant = inexistente.getAnteriorAula();
                String sig = inexistente.getSiguienteAula();
                int nroGrupos = 0;
                TreeSet<Integer> idsGrupos = new TreeSet<Integer>();
                for (Inexistente visiInex : visitados) { // Revisar los anteriores visitados
                    if (ant.equals(visiInex.getAnteriorAula()) ||
                            ant.equals(visiInex.getSiguienteAula()) ||
                            sig.equals(visiInex.getAnteriorAula()) ||
                            sig.equals(visiInex.getSiguienteAula())) {
                        idsGrupos.add(visiInex.getIdGrupo());
                        nuevoGrupo = false;
                    }
                }
                /**
                 * unir dos grupos
                 */
                assert idsGrupos.size() <= 2 : "nroGrupos=" + nroGrupos;
                if (idsGrupos.size() == 2) {
                    String m = visitados.size() + "";
                    assert idsGrupos.first() < idsGrupos.last() : idsGrupos.first() + " " + idsGrupos.last() + " " + m;
                    IRGrupo grupoA = myGrupos.get(idsGrupos.first());
                    IRGrupo grupoB = myGrupos.get(idsGrupos.last());
                    assert grupoA != null;
                    assert grupoB != null : idsGrupos.last();
                    for (Inexistente inexB : grupoB.getInexistentes()) {//pouring Inexs tiene que ser por addInexs
                        grupoA.addInex(inexB);
                    }
                    for (Real realB : grupoB.getReales()) {//pouring Reales
                        grupoA.addReal(realB);
                    }
                    myGrupos.remove(idsGrupos.last());
                    // Reordenar el Map: todos los mayores que last disminuir en 1
                    HashMap<Integer, IRGrupo> temp = new HashMap<Integer, IRGrupo>();
                    for (Iterator it = myGrupos.values().iterator(); it.hasNext();) {
                        IRGrupo grupo = (IRGrupo) it.next();
                        if (grupo.idGrupo > idsGrupos.last()) {
                            int newIndex = grupo.disminuirId();
                            temp.put(newIndex, grupo);
                            it.remove();
                        }
                    }
                    myGrupos.putAll(temp);
                    idUltimoGrupo--;
                }
                if (nuevoGrupo) {
                    IRGrupo grupo = new IRGrupo(idUltimoGrupo);
                    grupo.addInex(inexistente);
                    myGrupos.put(idUltimoGrupo++, grupo);
                    checkNoExistentesDuplicadosGrupos(myGrupos);
                } else {
                    myGrupos.get(idsGrupos.first()).addInex(inexistente);
                    checkNoExistentesDuplicadosGrupos(myGrupos);
                    inexistente.setIdGrupo(idsGrupos.first());
                }
                visitados.add(inexistente);
            }
        }
        /**
         * agregar reales al grupo
         */
        for (Real real : reales) {
            if (real.getPareja() == null) {
                boolean sinGrupo = true;
                for (Integer integer : myGrupos.keySet()) {
                    IRGrupo grupo = myGrupos.get(integer);
                    if (!grupo.isParejado()) {
                        if (grupo.getAulas().contains(real.getAula())) {
                            sinGrupo = false;
                            grupo.addReal(real);
                        }
                    }
                }
                if (sinGrupo) {
                    IRGrupo grupo = new IRGrupo(idUltimoGrupo);
                    Inexistente estadoEspecial = new Inexistente(EstadoEspecial.AUSENTE);
                    grupo.addPareja(estadoEspecial, real);
                    myGrupos.put(idUltimoGrupo, grupo);
                    estadoEspecial.setIdGrupo(idUltimoGrupo++);
                }
            }
        }
        checkSizeOfGrupos(myGrupos, inexistentes.size(), reales.size());
        checkNoRealesDuplicadosGrupos(myGrupos);
        checkNoExistentesDuplicadosGrupos(myGrupos);
        boolean requer = checkRequerimientos(myGrupos, new ArrayList[]{inexistentes, reales});
        if (!requer) {
            System.err.println("case Requerimientos");
            return unSoloGrupo(myGrupos);
        }
        boolean inexGTReal = checkInexMayorReales(myGrupos, new ArrayList[]{inexistentes, reales});
        if (!inexGTReal) {
            System.err.println("case Inexistenes mayor que reales");
            return unSoloGrupo(myGrupos);
        }
        for (IRGrupo iRGrupo : myGrupos.values()) {
            iRGrupo.cerrarGrupo();
            iRGrupo.refrescarRelaciones();
        }
        return myGrupos;
    }

    public HashMap<Integer, IRGrupo> unSoloGrupo(HashMap<Integer, IRGrupo> myGrupos) {
        HashMap<Integer, IRGrupo> myGrupos2 = new HashMap<Integer, IRGrupo>();
        IRGrupo newIRGrupo = new IRGrupo(0);
        for (IRGrupo iRGrupo : myGrupos.values()) {
            for (Inexistente inexistente : iRGrupo.getInexistentes()) {
                inexistente.setPareja(null);
                newIRGrupo.addInex(inexistente);
            }
            for (Real real : iRGrupo.getReales()) {
                real.setPareja(null);
                newIRGrupo.getReales().add(real);
            }
        }
        newIRGrupo.cerrarGrupo();
        newIRGrupo.refrescarRelaciones();
        myGrupos2.put(0, newIRGrupo);
        return myGrupos2;
    }

    private TreeMap<Integer, String> getHICodiPostulantes(int id_concurso) throws SQLException {
        ResultSet rs = database.crSt().executeQuery(
                "SELECT correlativo, codi_postulante from HI_original " +
                "WHERE id_concurso = " + id_concurso);
        TreeMap<Integer, String> result = new TreeMap<Integer, String>();
        while (rs.next()) {
            result.put(rs.getInt(1), rs.getString(2));
        }
        rs.close();
        return result;
    }

    public ArrayList getCodiPostulantes(int idConcurso) throws SQLException {
        ArrayList<String> arrl = new ArrayList();
        ResultSet rs = database.crSt().executeQuery(
                "SELECT codi_postulante FROM postulante WHERE id_concurso = " + idConcurso);
        while (rs.next()) {
            arrl.add(rs.getString(1));
        }
        rs.close();
        return arrl;
    }

    public static void testExistentes(DB database, int id_concurso) throws SQLException {
        long a = System.currentTimeMillis();
        ArrayList[] obInexistentes = new Inexistencia(database).checkCodiPostulanteInexistentes(id_concurso);
        System.out.println("checkCodiPostulanteInexistentes time:" + (System.currentTimeMillis() - a));

        if (obInexistentes[1].size() == 0) {
            System.err.println("Passed Inexistentes test");
        } else {
            System.err.println("Failed Inexistentes test");
            System.err.println("Inexistentes:");
            ArrayList<Inexistente> inexistentes = (ArrayList<Inexistente>) obInexistentes[0];
            for (Inexistente inexistente : inexistentes) {
                System.err.println(inexistente);
            }
            System.err.println("Reales:");
            ArrayList<Real> reales = (ArrayList<Real>) obInexistentes[1];
            for (Real real : reales) {
                System.err.println(real);
                System.err.println(real.getCandidatos());
            }
        }
    }

    public ArrayList[] testCase1() throws SQLException {
        ArrayList<Inexistente> inexistentes = new ArrayList<Inexistente>();
        ArrayList<Real> reales = new ArrayList<Real>();
        inexistentes.add(new Inexistente(1, "cp", "1", "2"));
        inexistentes.add(new Inexistente(2, "cp", "5", "6"));
        inexistentes.add(new Inexistente(3, "cp", "5", "6"));
        inexistentes.add(new Inexistente(4, "cp", "2", "2"));
        inexistentes.add(new Inexistente(5, "cp", "2", "5"));
        reales.add(new Real("cp", "1", "a"));
        reales.add(new Real("cp", "2", "b"));
        reales.add(new Real("cp", "5", "c"));
        reales.add(new Real("cp", "6", "d"));
        reales.add(new Real("cp", "4", "e"));
        reales.add(new Real("cp", "5", "f"));
        processTestCase(inexistentes, reales);
        return new ArrayList[]{inexistentes, reales};
    }

    public ArrayList[] testCase2() throws SQLException {
        ArrayList<Inexistente> inexistentes = new ArrayList<Inexistente>();
        ArrayList<Real> reales = new ArrayList<Real>();
        inexistentes.add(new Inexistente(1, "cp", "1", "2"));//p0
        inexistentes.add(new Inexistente(2, "cp", "5", "6"));//1
        inexistentes.add(new Inexistente(3, "cp", "5", "6"));//1
        inexistentes.add(new Inexistente(4, "cp", "2", "4"));//2
        inexistentes.add(new Inexistente(5, "cp", "2", "2"));//3p
        inexistentes.add(new Inexistente(8, "cp", "15", "20"));//4 2
        inexistentes.add(new Inexistente(9, "cp", "15", "20"));//4 2
        inexistentes.add(new Inexistente(8, "cp", "15", "20"));//4 2
        inexistentes.add(new Inexistente(9, "cp", "15", "20"));//4 2
        inexistentes.add(new Inexistente(10, "cp", "20", "30"));//4 2
        inexistentes.add(new Inexistente(11, "cp", "20", "30"));//4 2
        inexistentes.add(new Inexistente(12, "cp", "4", "30"));//(2+4)2
        inexistentes.add(new Inexistente(13, "cp", "80", "50"));//5 4
        inexistentes.add(new Inexistente(14, "cp", "80", "50"));//5 4
        reales.add(new Real("cp", "1", "a"));
        reales.add(new Real("cp", "2", "b"));
        reales.add(new Real("cp", "5", "c"));
        reales.add(new Real("cp", "6", "d"));
        reales.add(new Real("cp", "4", "e"));
        reales.add(new Real("cp", "5", "f"));
        reales.add(new Real("cp", "15", "g"));
        reales.add(new Real("cp", "15", "h"));
        reales.add(new Real("cp", "20", "i"));
        reales.add(new Real("cp", "20", "j"));
        reales.add(new Real("cp", "20", "k"));
        reales.add(new Real("cp", "20", "l"));
        reales.add(new Real("cp", "30", "m"));
        reales.add(new Real("cp", "30", "n"));
        reales.add(new Real("cp", "50", "ñ"));
        reales.add(new Real("cp", "50", "o"));
        processTestCase(inexistentes, reales);
        return new ArrayList[]{inexistentes, reales};
    }

    public ArrayList[] testRandomCase(int totalInex, int posiblesSalones) {
        String code = "public ArrayList[] testCaseX() {\n" +
                "ArrayList<Inexistente> inexistentes = new ArrayList<Inexistente>();\n" +
                "ArrayList<Real> reales = new ArrayList<Real>();\n";
        ArrayList<Inexistente> inexistentes = new ArrayList<Inexistente>();
        ArrayList<Real> reales = new ArrayList<Real>();
        HashSet<String> setSalones = new HashSet<String>();
        for (int i = 0; setSalones.size() < posiblesSalones; i++) {
            setSalones.add(1 + (int) (Math.random() * posiblesSalones) + "");
        }
        String[] salones = setSalones.toArray(new String[0]);
        HashSet<String> salonesDeInex = new HashSet<String>();
        for (int i = 0; i < totalInex; i++) {
            int x = (int) (Math.random() * salones.length);
            int y = 0;
            if (x == salones.length - 1) {
                y = x;
            } else {
                y = x + (int) (Math.random() * 2);
            }
            inexistentes.add(new Inexistente(i + 1, "cp", salones[x], salones[y]));
            code += "inexistentes.add(new Inexistente(" + (i + 1) + ", \"cp\",\"" + salones[x] + "\",\"" + salones[y] + "\"));\n";
            salonesDeInex.add(salones[x]);
            salonesDeInex.add(salones[y]);
        }
        int totalRealesAusentes = (int) (Math.random() * 5);
        String[] salInex = salonesDeInex.toArray(new String[0]);
        for (int i = 0; i < totalInex; i++) {
            int x = (int) (Math.random() * salInex.length);
            reales.add(new Real("cp" + (i + 1), salInex[x], Character.toChars('a' + i)[0] + ""));
            code += "reales.add(new Real(\"" + "cp" + (i + 1) + "\",\"" + salInex[x] + "\",\"" + Character.toChars('a' + i)[0] + "" + "\"));\n";
        }

        for (int i = totalInex; i < totalRealesAusentes + totalInex; i++) {
            int x = (int) (Math.random() * salones.length);
            reales.add(new Real("cp" + (i + 1), salones[x], Character.toChars('a' + i)[0] + ""));
            code += "reales.add(new Real(\"" + "cp" + (i + 1) + "\",\"" + salones[x] + "\",\"" + Character.toChars('a' + i)[0] + "" + "\"));\n";
        }
        code += "processTestCase(inexistentes, reales);\n" +
                "return new ArrayList[]{inexistentes, reales};}";
        System.err.println(code);
//        System.err.println(inexistentes);
//        System.err.println(reales);
        processTestCase(inexistentes, reales);
        return new ArrayList[]{inexistentes, reales};
    }

    public ArrayList[] testCase3() {
        ArrayList<Inexistente> inexistentes = new ArrayList<Inexistente>();
        ArrayList<Real> reales = new ArrayList<Real>();
        inexistentes.add(new Inexistente(1, "cp", "11", "11"));
        inexistentes.add(new Inexistente(2, "cp", "4", "1"));
        inexistentes.add(new Inexistente(3, "cp", "4", "4"));
        inexistentes.add(new Inexistente(4, "cp", "4", "1"));
        inexistentes.add(new Inexistente(5, "cp", "1", "1"));
        reales.add(new Real("cp1", "4", "a"));
        reales.add(new Real("cp2", "1", "b"));
        reales.add(new Real("cp3", "4", "c"));
        reales.add(new Real("cp4", "11", "d"));
        reales.add(new Real("cp5", "4", "e"));
        reales.add(new Real("cp6", "1", "f"));
        reales.add(new Real("cp7", "11", "g"));
        reales.add(new Real("cp8", "11", "h"));
        processTestCase(inexistentes, reales);
        return new ArrayList[]{inexistentes, reales};
    }

    public ArrayList[] testCaseInexistentesSinReales() {
        ArrayList<Inexistente> inexistentes = new ArrayList<Inexistente>();
        ArrayList<Real> reales = new ArrayList<Real>();
        inexistentes.add(new Inexistente(1, "cp", "11", "11"));
        inexistentes.add(new Inexistente(2, "cp", "4", "4"));
        inexistentes.add(new Inexistente(3, "cp", "11", "11"));
        inexistentes.add(new Inexistente(4, "cp", "12", "4"));
        inexistentes.add(new Inexistente(5, "cp", "1", "1"));
        reales.add(new Real("cp9", "1", "i"));
        reales.add(new Real("cp7", "10", "g"));
        reales.add(new Real("cp1", "4", "a"));
        reales.add(new Real("cp2", "4", "b"));
        reales.add(new Real("cp3", "4", "c"));
        reales.add(new Real("cp4", "4", "d"));
        reales.add(new Real("cp5", "12", "e"));
        reales.add(new Real("cp6", "12", "f"));
        reales.add(new Real("cp8", "12", "h"));

        processTestCase(inexistentes, reales);
        return new ArrayList[]{inexistentes, reales};
    }

    public ArrayList[] testCaseMasInexistentesQueReales() {
        ArrayList<Inexistente> inexistentes = new ArrayList<Inexistente>();
        ArrayList<Real> reales = new ArrayList<Real>();
        inexistentes.add(new Inexistente(1, "cp", "10", "10"));
        inexistentes.add(new Inexistente(2, "cp", "10", "10"));
        inexistentes.add(new Inexistente(3, "cp", "10", "7"));
        inexistentes.add(new Inexistente(4, "cp", "2", "10"));
        inexistentes.add(new Inexistente(5, "cp", "7", "17"));
        reales.add(new Real("cp1", "2", "a"));
        reales.add(new Real("cp2", "10", "b"));
        reales.add(new Real("cp3", "17", "c"));
        reales.add(new Real("cp4", "2", "d"));
        reales.add(new Real("cp5", "17", "e"));
        reales.add(new Real("cp6", "2", "f"));
        processTestCase(inexistentes, reales);
        return new ArrayList[]{inexistentes, reales};
    }

    public ArrayList[] testCaseSatisfaceYNoSatisface() {
        ArrayList<Inexistente> inexistentes = new ArrayList<Inexistente>();
        ArrayList<Real> reales = new ArrayList<Real>();
        inexistentes.add(new Inexistente(1, "cp", "16", "16"));
        inexistentes.add(new Inexistente(2, "cp", "5", "16"));
        inexistentes.add(new Inexistente(3, "cp", "1", "1"));
        inexistentes.add(new Inexistente(4, "cp", "5", "5"));
        inexistentes.add(new Inexistente(5, "cp", "0", "0"));
        reales.add(new Real("cp1", "0", "a"));
        reales.add(new Real("cp2", "0", "b"));
        reales.add(new Real("cp3", "16", "c"));
        reales.add(new Real("cp4", "0", "d"));
        reales.add(new Real("cp5", "16", "e"));
        reales.add(new Real("cp6", "16", "f"));
        reales.add(new Real("cp7", "1", "g"));
        processTestCase(inexistentes, reales);
        return new ArrayList[]{inexistentes, reales};
    }

    public ArrayList[] testCaseUnionGrupos() {
        ArrayList<Inexistente> inexistentes = new ArrayList<Inexistente>();
        ArrayList<Real> reales = new ArrayList<Real>();
        inexistentes.add(new Inexistente(1, "cp", "8", "13"));//0
        inexistentes.add(new Inexistente(2, "cp", "11", "11"));//1 0
        inexistentes.add(new Inexistente(3, "cp", "14", "11"));//1 0
        inexistentes.add(new Inexistente(4, "cp", "14", "14"));//1 0
        inexistentes.add(new Inexistente(5, "cp", "13", "14"));//0
        inexistentes.add(new Inexistente(6, "cp", "3", "1"));//1
        inexistentes.add(new Inexistente(7, "cp", "13", "14"));//0
        inexistentes.add(new Inexistente(8, "cp", "14", "11"));//
        reales.add(new Real("cp1", "8", "a"));
        reales.add(new Real("cp2", "13", "b"));
        reales.add(new Real("cp3", "11", "c"));
        reales.add(new Real("cp4", "11", "d"));
        reales.add(new Real("cp5", "14", "e"));
        reales.add(new Real("cp6", "13", "f"));
        reales.add(new Real("cp7", "1", "g"));
        reales.add(new Real("cp8", "14", "h"));
        reales.add(new Real("cp9", "8", "i"));
        reales.add(new Real("cp10", "1", "j"));
        reales.add(new Real("cp11", "14", "k"));
        reales.add(new Real("cp12", "11", "l"));
        processTestCase(inexistentes, reales);
        return new ArrayList[]{inexistentes, reales};
    }

    public ArrayList[] testCaseUnionGrupos2() {
        ArrayList<Inexistente> inexistentes = new ArrayList<Inexistente>();
        ArrayList<Real> reales = new ArrayList<Real>();
        inexistentes.add(new Inexistente(1, "cp", "9", "16"));//0
        inexistentes.add(new Inexistente(2, "cp", "5", "5"));//1 0
        inexistentes.add(new Inexistente(3, "cp", "5", "5"));//1 0
        inexistentes.add(new Inexistente(4, "cp", "9", "9"));//p2 1
        inexistentes.add(new Inexistente(5, "cp", "7", "7"));//3 2
        inexistentes.add(new Inexistente(6, "cp", "7", "7"));//3 2
        inexistentes.add(new Inexistente(7, "cp", "10", "7"));//3 2
        inexistentes.add(new Inexistente(8, "cp", "5", "9"));//0
        reales.add(new Real("cp1", "10", "a"));
        reales.add(new Real("cp2", "5", "b"));
        reales.add(new Real("cp3", "5", "c"));
        reales.add(new Real("cp4", "5", "d"));
        reales.add(new Real("cp5", "10", "e"));
        reales.add(new Real("cp6", "7", "f"));
        reales.add(new Real("cp7", "10", "g"));
        reales.add(new Real("cp8", "9", "h"));
        reales.add(new Real("cp9", "2", "i"));
        reales.add(new Real("cp10", "5", "j"));
        reales.add(new Real("cp11", "10", "k"));
        processTestCase(inexistentes, reales);
        return new ArrayList[]{inexistentes, reales};
    }

    public ArrayList[] testCaseA() {
        ArrayList<Inexistente> inexistentes = new ArrayList<Inexistente>();
        ArrayList<Real> reales = new ArrayList<Real>();
        inexistentes.add(new Inexistente(1, "cp", "6", "5"));
        inexistentes.add(new Inexistente(2, "cp", "0", "0"));
        inexistentes.add(new Inexistente(3, "cp", "0", "6"));
        inexistentes.add(new Inexistente(4, "cp", "0", "0"));
        reales.add(new Real("cp1", "6", "a"));
        reales.add(new Real("cp2", "0", "b"));
        reales.add(new Real("cp3", "5", "c"));
        reales.add(new Real("cp4", "6", "d"));
        reales.add(new Real("cp5", "10", "e"));
        reales.add(new Real("cp6", "6", "f"));
        reales.add(new Real("cp7", "6", "g"));
        processTestCase(inexistentes, reales);
        return new ArrayList[]{inexistentes, reales};
    }

    public ArrayList[] testCaseX() {
        ArrayList<Inexistente> inexistentes = new ArrayList<Inexistente>();
        ArrayList<Real> reales = new ArrayList<Real>();
        inexistentes.add(new Inexistente(1, "cp", "4", "4"));
        inexistentes.add(new Inexistente(2, "cp", "6", "5"));
        inexistentes.add(new Inexistente(3, "cp", "3", "3"));
        inexistentes.add(new Inexistente(4, "cp", "4", "9"));
        inexistentes.add(new Inexistente(5, "cp", "1", "7"));
        inexistentes.add(new Inexistente(6, "cp", "1", "7"));
        inexistentes.add(new Inexistente(7, "cp", "2", "2"));
        reales.add(new Real("cp1", "1", "a"));
        reales.add(new Real("cp2", "3", "b"));
        reales.add(new Real("cp3", "4", "c"));
        reales.add(new Real("cp4", "5", "d"));
        reales.add(new Real("cp5", "7", "e"));
        reales.add(new Real("cp6", "6", "f"));
        reales.add(new Real("cp7", "2", "g"));
        reales.add(new Real("cp8", "2", "h"));
        reales.add(new Real("cp9", "1", "i"));
        reales.add(new Real("cp10", "7", "j"));
        processTestCase(inexistentes, reales);
        return new ArrayList[]{inexistentes, reales};
    }

    public void processTestCase(ArrayList<Inexistente> inexistentes, ArrayList<Real> reales) {
        assert inexistentes.size() <= reales.size() : "inexistentes.size()>reales.size()";
        emparejarUnicoEnAula(inexistentes, reales);
        emparejarRealToInex(inexistentes, reales, 0);
        grupos = crearGrupos(inexistentes, reales);
        if (grupos != null) {
            System.err.println("case ADN!");
        }
//        checkNoRealesDuplicadosGrupos();

    }

    public void showGrupos(HashMap<Integer, IRGrupo> grupos) {
        for (IRGrupo iRGrupo : grupos.values()) {
            System.err.println(iRGrupo);
        }
    }

    public static void main(String[] args) throws Exception {
        DB database = new DB();
        long a = System.currentTimeMillis();
//        testExistentes(database, 1);
        for (int i = 0; i < 100000; i++) {
            int nroInex = (int) (Math.random() * 20);
            int totalAulas = 1 + (int) (Math.random() * 10);
            new Inexistencia(database).testRandomCase(nroInex, totalAulas);
//            System.err.println("----------------------------");
        }
        System.out.println(System.currentTimeMillis() - a);
        database.closeDB();
    }
}