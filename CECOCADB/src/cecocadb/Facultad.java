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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "facultad")
@NamedQueries({@NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f"), @NamedQuery(name = "Facultad.findByIdFacultad", query = "SELECT f FROM Facultad f WHERE f.idFacultad = :idFacultad"), @NamedQuery(name = "Facultad.findByNombre", query = "SELECT f FROM Facultad f WHERE f.nombre = :nombre")})
public class Facultad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_facultad")
    private String idFacultad;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFacultad")
    private Collection<Escuela> escuelaCollection;
    public static void main(String[]args) throws Exception{
        new Facultad();
    }

    public Facultad() throws Exception {
    }

    public Facultad(String idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(String idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Escuela> getEscuelaCollection() {
        return escuelaCollection;
    }

    public void setEscuelaCollection(Collection<Escuela> escuelaCollection) {
        this.escuelaCollection = escuelaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacultad!=null ? idFacultad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.idFacultad==null&&other.idFacultad!=null)||(this.idFacultad!=null&&!this.idFacultad.equals(other.idFacultad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cecocadb.Facultad[idFacultad="+idFacultad+"]";
    }
}
