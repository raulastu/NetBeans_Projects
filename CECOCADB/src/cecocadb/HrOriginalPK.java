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
public class HrOriginalPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_concurso")
    private int idConcurso;
    @Basic(optional = false)
    @Column(name = "correlativo")
    private int correlativo;
    public static void main(String[]args) throws Exception{
        new HrOriginalPK();
    }

    public HrOriginalPK() throws Exception {
    }

    public HrOriginalPK(int idConcurso, int correlativo) {
        this.idConcurso = idConcurso;
        this.correlativo = correlativo;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    public int getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idConcurso;
        hash += (int) correlativo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrOriginalPK)) {
            return false;
        }
        HrOriginalPK other = (HrOriginalPK) object;
        if (this.idConcurso!=other.idConcurso) {
            return false;
        }
        if (this.correlativo!=other.correlativo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cecocadb.HrOriginalPK[idConcurso="+idConcurso+", correlativo="+correlativo+"]";
    }
}
