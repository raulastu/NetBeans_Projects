package inexistentes;

import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class Real {

    private String codiPostulante;
    private String aula;
    private String nombres;
    private ArrayList<Inexistente> candidatos;
    private Inexistente pareja;

    public void setPareja(Inexistente pareja) {
        this.pareja = pareja;
    }

    public Inexistente getPareja() {
        return pareja;
    }

    public String getCodiPostulante() {
        return codiPostulante;
    }

    public String getNombres() {
        return nombres;
    }

    public Real(String codiPostulante, String aula, String nombres) {
        this.codiPostulante = codiPostulante;
        this.aula = aula;
        this.nombres = nombres;
        candidatos = new ArrayList<Inexistente>();
    }

    /**
     * @return the aula
     */
    public String getAula() {
        return aula;
    }

    /**
     * @return the candidatos
     */
    public ArrayList<Inexistente> getCandidatos() {
        return candidatos;
    }

    public String header() {
        return "cp\taula\tnombres\t";
    }

    @Override
    public String toString() {
        return codiPostulante+"\t"+aula+"\t"+nombres;
    }
}
