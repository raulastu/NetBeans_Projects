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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "hr_original")
@NamedQueries({@NamedQuery(name = "HrOriginal.findAll", query = "SELECT h FROM HrOriginal h"), @NamedQuery(name = "HrOriginal.findByIdConcurso", query = "SELECT h FROM HrOriginal h WHERE h.hrOriginalPK.idConcurso = :idConcurso"), @NamedQuery(name = "HrOriginal.findByCorrelativo", query = "SELECT h FROM HrOriginal h WHERE h.hrOriginalPK.correlativo = :correlativo"), @NamedQuery(name = "HrOriginal.findByLitocodigo", query = "SELECT h FROM HrOriginal h WHERE h.litocodigo = :litocodigo"), @NamedQuery(name = "HrOriginal.findByRespuestas", query = "SELECT h FROM HrOriginal h WHERE h.respuestas = :respuestas")})
public class HrOriginal implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HrOriginalPK hrOriginalPK;
    @Basic(optional = false)
    @Column(name = "litocodigo")
    private String litocodigo;
    @Column(name = "respuestas")
    private String respuestas;
    public static void main(String[]args) throws Exception{
        new HrOriginal();
    }

    public HrOriginal() throws Exception {
    }

    public HrOriginal(HrOriginalPK hrOriginalPK) {
        this.hrOriginalPK = hrOriginalPK;
    }

    public HrOriginal(HrOriginalPK hrOriginalPK, String litocodigo) {
        this.hrOriginalPK = hrOriginalPK;
        this.litocodigo = litocodigo;
    }

    public HrOriginal(int idConcurso, int correlativo) {
        this.hrOriginalPK = new HrOriginalPK(idConcurso, correlativo);
    }

    public HrOriginalPK getHrOriginalPK() {
        return hrOriginalPK;
    }

    public void setHrOriginalPK(HrOriginalPK hrOriginalPK) {
        this.hrOriginalPK = hrOriginalPK;
    }

    public String getLitocodigo() {
        return litocodigo;
    }

    public void setLitocodigo(String litocodigo) {
        this.litocodigo = litocodigo;
    }

    public String getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String respuestas) {
        this.respuestas = respuestas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hrOriginalPK!=null ? hrOriginalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrOriginal)) {
            return false;
        }
        HrOriginal other = (HrOriginal) object;
        if ((this.hrOriginalPK==null&&other.hrOriginalPK!=null)||(this.hrOriginalPK!=null&&!this.hrOriginalPK.equals(other.hrOriginalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cecocadb.HrOriginal[hrOriginalPK="+hrOriginalPK+"]";
    }
}
