/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cecocadb;

import java.io.Serializable;
import java.util.*;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExamenEscuelaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_escuela")
    private String idEscuela;
    @Basic(optional = false)
    @Column(name = "id_concurso")
    private int idConcurso;
    public static void main(String[]args) throws Exception{
        new ExamenEscuelaPK();
    }

    public ExamenEscuelaPK() throws Exception {
    }

    public ExamenEscuelaPK(String idEscuela, int idConcurso) {
        this.idEscuela = idEscuela;
        this.idConcurso = idConcurso;
    }

    public String getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(String idEscuela) {
        this.idEscuela = idEscuela;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEscuela!=null ? idEscuela.hashCode() : 0);
        hash += (int) idConcurso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenEscuelaPK)) {
            return false;
        }
        ExamenEscuelaPK other = (ExamenEscuelaPK) object;
        if ((this.idEscuela==null&&other.idEscuela!=null)||(this.idEscuela!=null&&!this.idEscuela.equals(other.idEscuela))) {
            return false;
        }
        if (this.idConcurso!=other.idConcurso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cecocadb.ExamenEscuelaPK[idEscuela="+idEscuela+", idConcurso="+idConcurso+"]";
    }
}
