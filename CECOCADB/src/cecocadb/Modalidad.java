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
@Table(name = "modalidad")
@NamedQueries({@NamedQuery(name = "Modalidad.findAll", query = "SELECT m FROM Modalidad m"), @NamedQuery(name = "Modalidad.findByIdModalidad", query = "SELECT m FROM Modalidad m WHERE m.idModalidad = :idModalidad"), @NamedQuery(name = "Modalidad.findByNombre", query = "SELECT m FROM Modalidad m WHERE m.nombre = :nombre")})
public class Modalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_modalidad")
    private Integer idModalidad;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModalidad")
    private Collection<Concurso> concursoCollection;
    public static void main(String[]args) throws Exception{
        new Modalidad();
    }

    public Modalidad() throws Exception {
    }

    public Modalidad(Integer idModalidad) {
        this.idModalidad = idModalidad;
    }

    public Modalidad(Integer idModalidad, String nombre) {
        this.idModalidad = idModalidad;
        this.nombre = nombre;
    }

    public Integer getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(Integer idModalidad) {
        this.idModalidad = idModalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idModalidad!=null ? idModalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modalidad)) {
            return false;
        }
        Modalidad other = (Modalidad) object;
        if ((this.idModalidad==null&&other.idModalidad!=null)||(this.idModalidad!=null&&!this.idModalidad.equals(other.idModalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cecocadb.Modalidad[idModalidad="+idModalidad+"]";
    }
}
