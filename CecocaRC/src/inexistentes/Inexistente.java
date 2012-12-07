package inexistentes;

import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class Inexistente {

    private EstadoEspecial estadoEspecial;
    private ArrayList<Real> candidatos;

    public ArrayList<Real> getCandidatos() {
        return candidatos;
    }

    public EstadoEspecial getEstadoEspecial() {
        return estadoEspecial;
    }

    public void setEstadoEspecial(EstadoEspecial estadoEspecial) {
        this.estadoEspecial = estadoEspecial;
    }

    public boolean isMismaAula() {
        return getAnteriorAula().equals(getSiguienteAula());
    }

    public Inexistente(int cl, String cp, String antAula, String sigAula) {
        this.correlativoLectura = cl;
        this.codiPostulanteErrado = cp;
        this.anteriorAula = antAula;
        this.siguienteAula = sigAula;
        this.idGrupo = -111;
        this.candidatos = new ArrayList<Real>();
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getCodiPostErrado() {
        return codiPostulanteErrado;
    }

    public int getCorrelativoLectura() {
        return correlativoLectura;
    }

    public Real getPareja() {
        return pareja;
    }

    public void setPareja(Real pareja) {
        this.pareja = pareja;
    }

    public Inexistente(EstadoEspecial estadoEspecial) {
        this.estadoEspecial = estadoEspecial;
    }

    /**
     * @return the anteriorAula
     */
    public String getAnteriorAula() {
        return anteriorAula;
    }

    /**
     * @return the siguienteAula
     */
    public String getSiguienteAula() {
        return siguienteAula;
    }

    public String header() {
        return "CL\tcpInex\taulaAnt\taulaSig\tgrupo";
    }

    @Override
    public String toString() {
        return "["+correlativoLectura+"]"+" \t"+
                "["+codiPostulanteErrado+"] \t"+
                "["+anteriorAula+"] \t"+
                "["+siguienteAula+"]";
    }
    private int correlativoLectura;
    private String codiPostulanteErrado;
    private String anteriorAula = "-1";
    private String siguienteAula = "-1";
    private int idGrupo;
    private Real pareja;
}
