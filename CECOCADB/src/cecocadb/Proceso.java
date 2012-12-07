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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "proceso")
@NamedQueries({@NamedQuery(name = "Proceso.findAll", query = "SELECT p FROM Proceso p"), @NamedQuery(name = "Proceso.findByIdProceso", query = "SELECT p FROM Proceso p WHERE p.idProceso = :idProceso"), @NamedQuery(name = "Proceso.findByDescripcion", query = "SELECT p FROM Proceso p WHERE p.descripcion = :descripcion"), @NamedQuery(name = "Proceso.findByDesignacion", query = "SELECT p FROM Proceso p WHERE p.designacion = :designacion"), @NamedQuery(name = "Proceso.findByFormulaPtjeMin", query = "SELECT p FROM Proceso p WHERE p.formulaPtjeMin = :formulaPtjeMin"), @NamedQuery(name = "Proceso.findByEstado", query = "SELECT p FROM Proceso p WHERE p.estado = :estado")})
public class Proceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proceso")
    private Integer idProceso;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "designacion")
    private String designacion;
    @Basic(optional = false)
    @Column(name = "formula_ptje_min")
    private String formulaPtjeMin;
    @Column(name = "estado")
    private Short estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProceso")
    private Collection<Concurso> concursoCollection;
    public static void main(String[]args) throws Exception{
        new Proceso();
    }

    public Proceso() throws Exception {
    }

    public Proceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public Proceso(Integer idProceso, String designacion, String formulaPtjeMin) {
        this.idProceso = idProceso;
        this.designacion = designacion;
        this.formulaPtjeMin = formulaPtjeMin;
    }

    public Integer getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDesignacion() {
        return designacion;
    }

    public void setDesignacion(String designacion) {
        this.designacion = designacion;
    }

    public String getFormulaPtjeMin() {
        return formulaPtjeMin;
    }

    public void setFormulaPtjeMin(String formulaPtjeMin) {
        this.formulaPtjeMin = formulaPtjeMin;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public Collection<Concurso> getConcursoCollection() {
        return concursoCollection;
    }

    public void setConcursoCollection(Collection<Concurso> concursoCollection) {
        this.concursoCollection = concursoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProceso!=null ? idProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proceso)) {
            return false;
        }
        Proceso other = (Proceso) object;
        if ((this.idProceso==null&&other.idProceso!=null)||(this.idProceso!=null&&!this.idProceso.equals(other.idProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cecocadb.Proceso[idProceso="+idProceso+"]";
    }
}
