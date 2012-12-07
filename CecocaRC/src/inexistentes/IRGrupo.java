package inexistentes;

import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class IRGrupo {

    int idGrupo = -1;
    private HashSet<String> aulas = new HashSet<String>();
    private boolean deDos = false;
    private ArrayList<Inexistente> inexistentes = new ArrayList<Inexistente>();
    private ArrayList<Real> reales = new ArrayList<Real>();
    private ArrayList<String> requerimientos = new ArrayList<String>();
    private ArrayList<Boolean> relacionAula = new ArrayList<Boolean>();

    public int getIdGrupo() {
        return idGrupo;
    }

    public int disminuirId() {
        idGrupo--;
        for (Inexistente inexistente : inexistentes) {
            inexistente.setIdGrupo(idGrupo);
        }
        return idGrupo;
    }

    public ArrayList<String> getRequerimientos() {
        return requerimientos;
    }

    public boolean checkUnicidadReales() {
        for (int i = 0; i<reales.size(); i++) {
            for (int j = i+1; j<reales.size(); j++) {
                if (reales.get(i)==reales.get(j)) {
                    System.out.println(reales.get(i));
                    return false;
                }
            }
        }
        return true;
    }

    public boolean cerrarGrupo() {
        assert checkUnicidadReales();
        int x = inexistentes.size();
        for (int i = 0; i<reales.size()-x; i++) {
            inexistentes.add(new Inexistente(EstadoEspecial.PENDIENTE));
        }
        return true;
    }

    public void refrescarRelaciones() {
        relacionAula.clear();
        boolean x = inexistentes.size()==reales.size();
        if (!x) {
            System.err.println(this);
        }
        assert inexistentes.size()==reales.size();
        for (int i = 0; i<reales.size(); i++) {
            if (inexistentes.get(i).getEstadoEspecial()==null) {
                boolean relacionados = inexistentes.get(i).getAnteriorAula().equals(reales.get(i).getAula())||
                        inexistentes.get(i).getSiguienteAula().equals(reales.get(i).getAula());
                relacionAula.add(relacionados);
            } else {
                relacionAula.add(true);
            }
        }
    }

    public int swapDownReal(int i) {
        if (i<reales.size()-1) {
            Collections.swap(reales, i, i+1);
            refrescarRelaciones();
            return i+1;
        } else {
            return i;
        }
    }

    public int swapUpReal(int i) {
        if (i>0) {
            Collections.swap(reales, i, i-1);
            refrescarRelaciones();
            return i-1;
        } else {
            return i;
        }
    }

    public ArrayList<Inexistente> getInexistentes() {
        return inexistentes;
    }

    public ArrayList<Real> getReales() {
        return reales;
    }

    public HashSet<String> getAulas() {
        return aulas;
    }

    public boolean isParejado() {
        return deDos;
    }

    IRGrupo(int id) {
        this.idGrupo = id;
    }

    public void setParejados() {
        this.deDos = true;
    }

    void addInex(Inexistente inex) {
        inex.setIdGrupo(idGrupo);
        aulas.add(inex.getAnteriorAula());
        if (inex.isMismaAula()) {
            requerimientos.add(inex.getAnteriorAula());
        } else {
            aulas.add(inex.getSiguienteAula());
        }
        inexistentes.add(inex);
//        nroInex++;
    }

    void addReal(Real real) {
        reales.add(real);
        if (requerimientos.contains(real.getAula())) {
            requerimientos.remove(real.getAula());
        }
        assert checkUnicidadReales();
//        nroReales++;
    }

    void addReal(int indx, Real real) {
        reales.add(indx, real);
        if (requerimientos.contains(real.getAula())) {
            requerimientos.remove(real.getAula());
        }
        assert checkUnicidadReales();
//        nroReales++;
    }

    void addPareja(Inexistente inex, Real real) {
        inex.setIdGrupo(idGrupo);
        aulas.add(inex.getAnteriorAula());
        aulas.add(inex.getSiguienteAula());
        reales.add(real);
        inexistentes.add(inex);
        assert checkUnicidadReales();
        setParejados();
    }

    @Override
    public String toString() {
//        for (int i = 0; i<inexistentes.size(); i++) {
//            System.out.println("");
//        }
        return toString2();
    }

    public String toString2() {
        int maxL = Math.max(reales.size(), inexistentes.size());
        String[] res = new String[maxL];
        System.err.println("reales.size()="+reales.size());
        System.err.println("inexistentes.size()="+inexistentes.size());
        fill(res, "");
        for (int i = 0; i<inexistentes.size(); i++) {
            res[i] += "["+idGrupo+"] "+inexistentes.get(i)+" <> ";
        }
        for (int i = 0; i<reales.size(); i++) {
            res[i] += reales.get(i);
        }
        String ret = "";
        for (int i = 0; i<res.length; i++) {
            ret += res[i]+"\n";
        }
        ret += "----------";
        return ret;
    }

    /**
     * @return the relacionAula
     */
    public ArrayList<Boolean> getRelacionAula() {
        return relacionAula;
    }
}
