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
public class NotasColegioPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "grado")
    private short grado;
    @Basic(optional = false)
    @Column(name = "codi_postulante")
    private String codiPostulante;
    public static void main(String[]args) throws Exception{
        new NotasColegioPK();
    }

    public NotasColegioPK() throws Exception {
    }

    public NotasColegioPK(short grado, String codiPostulante) {
        this.grado = grado;
        this.codiPostulante = codiPostulante;
    }

    public short getGrado() {
        return grado;
    }

    public void setGrado(short grado) {
        this.grado = grado;
    }

    public String getCodiPostulante() {
        return codiPostulante;
    }

    public void setCodiPostulante(String codiPostulante) {
        this.codiPostulante = codiPostulante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) grado;
        hash += (codiPostulante!=null ? codiPostulante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotasColegioPK)) {
            return false;
        }
        NotasColegioPK other = (NotasColegioPK) object;
        if (this.grado!=other.grado) {
            return false;
        }
        if ((this.codiPostulante==null&&other.codiPostulante!=null)||(this.codiPostulante!=null&&!this.codiPostulante.equals(other.codiPostulante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cecocadb.NotasColegioPK[grado="+grado+", codiPostulante="+codiPostulante+"]";
    }
}
