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
@Table(name = "sede")
@NamedQueries({@NamedQuery(name = "Sede.findAll", query = "SELECT s FROM Sede s"), @NamedQuery(name = "Sede.findByIdSede", query = "SELECT s FROM Sede s WHERE s.idSede = :idSede"), @NamedQuery(name = "Sede.findByNominacion", query = "SELECT s FROM Sede s WHERE s.nominacion = :nominacion")})
public class Sede implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sede")
    private Integer idSede;
    @Column(name = "nominacion")
    private String nominacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSede")
    private Collection<Concurso> concursoCollection;
    public static void main(String[]args) throws Exception{
        new Sede();
    }

    public Sede() throws Exception {
    }

    public Sede(Integer idSede) {
        this.idSede = idSede;
    }

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public String getNominacion() {
        return nominacion;
    }

    public void setNominacion(String nominacion) {
        this.nominacion = nominacion;
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
        hash += (idSede!=null ? idSede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sede)) {
            return false;
        }
        Sede other = (Sede) object;
        if ((this.idSede==null&&other.idSede!=null)||(this.idSede!=null&&!this.idSede.equals(other.idSede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cecocadb.Sede[idSede="+idSede+"]";
    }
}
