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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "escuela")
@NamedQueries({@NamedQuery(name = "Escuela.findAll", query = "SELECT e FROM Escuela e"), @NamedQuery(name = "Escuela.findByIdEscuela", query = "SELECT e FROM Escuela e WHERE e.idEscuela = :idEscuela"), @NamedQuery(name = "Escuela.findByNombre", query = "SELECT e FROM Escuela e WHERE e.nombre = :nombre")})
public class Escuela implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_escuela")
    private String idEscuela;
    @Column(name = "nombre")
    private String nombre;
    @JoinTable(name = "grupo_escuela", joinColumns = {@JoinColumn(name = "id_escuela", referencedColumnName = "id_escuela")}, inverseJoinColumns = {@JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")})
    @ManyToMany
    private Collection<Grupo> grupoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "escuela")
    private Collection<ExamenEscuela> examenEscuelaCollection;
    @JoinColumn(name = "id_facultad", referencedColumnName = "id_facultad")
    @ManyToOne(optional = false)
    private Facultad idFacultad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEscuela")
    private Collection<Postulante> postulanteCollection;
    public static void main(String[]args) throws Exception{
        new Escuela();
    }

    public Escuela() throws Exception {
    }

    public Escuela(String idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(String idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Grupo> getGrupoCollection() {
        return grupoCollection;
    }

    public void setGrupoCollection(Collection<Grupo> grupoCollection) {
        this.grupoCollection = grupoCollection;
    }

    public Collection<ExamenEscuela> getExamenEscuelaCollection() {
        return examenEscuelaCollection;
    }

    public void setExamenEscuelaCollection(Collection<ExamenEscuela> examenEscuelaCollection) {
        this.examenEscuelaCollection = examenEscuelaCollection;
    }

    public Facultad getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Facultad idFacultad) {
        this.idFacultad = idFacultad;
    }

    public Collection<Postulante> getPostulanteCollection() {
        return postulanteCollection;
    }

    public void setPostulanteCollection(Collection<Postulante> postulanteCollection) {
        this.postulanteCollection = postulanteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEscuela!=null ? idEscuela.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Escuela)) {
            return false;
        }
        Escuela other = (Escuela) object;
        if ((this.idEscuela==null&&other.idEscuela!=null)||(this.idEscuela!=null&&!this.idEscuela.equals(other.idEscuela))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cecocadb.Escuela[idEscuela="+idEscuela+"]";
    }
}
