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
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "notas_colegio")
@NamedQueries({@NamedQuery(name = "NotasColegio.findAll", query = "SELECT n FROM NotasColegio n"), @NamedQuery(name = "NotasColegio.findByGrado", query = "SELECT n FROM NotasColegio n WHERE n.notasColegioPK.grado = :grado"), @NamedQuery(name = "NotasColegio.findByCodiPostulante", query = "SELECT n FROM NotasColegio n WHERE n.notasColegioPK.codiPostulante = :codiPostulante"), @NamedQuery(name = "NotasColegio.findByNotas", query = "SELECT n FROM NotasColegio n WHERE n.notas = :notas")})
public class NotasColegio implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotasColegioPK notasColegioPK;
    @Column(name = "notas")
    private String notas;
    @JoinColumn(name = "codi_postulante", referencedColumnName = "codi_postulante", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Postulante postulante;
    public static void main(String[]args) throws Exception{
        new NotasColegio();
    }

    public NotasColegio() throws Exception {
    }

    public NotasColegio(NotasColegioPK notasColegioPK) {
        this.notasColegioPK = notasColegioPK;
    }

    public NotasColegio(short grado, String codiPostulante) {
        this.notasColegioPK = new NotasColegioPK(grado, codiPostulante);
    }

    public NotasColegioPK getNotasColegioPK() {
        return notasColegioPK;
    }

    public void setNotasColegioPK(NotasColegioPK notasColegioPK) {
        this.notasColegioPK = notasColegioPK;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notasColegioPK!=null ? notasColegioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotasColegio)) {
            return false;
        }
        NotasColegio other = (NotasColegio) object;
        if ((this.notasColegioPK==null&&other.notasColegioPK!=null)||(this.notasColegioPK!=null&&!this.notasColegioPK.equals(other.notasColegioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cecocadb.NotasColegio[notasColegioPK="+notasColegioPK+"]";
    }
}
