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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "grupo")
@NamedQueries({@NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"), @NamedQuery(name = "Grupo.findByIdGrupo", query = "SELECT g FROM Grupo g WHERE g.idGrupo = :idGrupo"), @NamedQuery(name = "Grupo.findByDesginacion", query = "SELECT g FROM Grupo g WHERE g.desginacion = :desginacion")})
public class Grupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grupo")
    private Integer idGrupo;
    @Column(name = "desginacion")
    private String desginacion;
    @ManyToMany(mappedBy = "grupoCollection")
    private Collection<Escuela> escuelaCollection;
    @JoinColumn(name = "id_concurso", referencedColumnName = "id_concurso")
    @ManyToOne(optional = false)
    private Concurso idConcurso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupo")
    private Collection<GrupoArea> grupoAreaCollection;
    public static void main(String[]args) throws Exception{
        new Grupo();
    }

    public Grupo() throws Exception {
    }

    public Grupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getDesginacion() {
        return desginacion;
    }

    public void setDesginacion(String desginacion) {
        this.desginacion = desginacion;
    }

    public Collection<Escuela> getEscuelaCollection() {
        return escuelaCollection;
    }

    public void setEscuelaCollection(Collection<Escuela> escuelaCollection) {
        this.escuelaCollection = escuelaCollection;
    }

    public Concurso getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(Concurso idConcurso) {
        this.idConcurso = idConcurso;
    }

    public Collection<GrupoArea> getGrupoAreaCollection() {
        return grupoAreaCollection;
    }

    public void setGrupoAreaCollection(Collection<GrupoArea> grupoAreaCollection) {
        this.grupoAreaCollection = grupoAreaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupo!=null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.idGrupo==null&&other.idGrupo!=null)||(this.idGrupo!=null&&!this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cecocadb.Grupo[idGrupo="+idGrupo+"]";
    }
}
