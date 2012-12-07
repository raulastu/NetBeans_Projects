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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "area")
@NamedQueries({@NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a"), @NamedQuery(name = "Area.findByIdArea", query = "SELECT a FROM Area a WHERE a.idArea = :idArea"), @NamedQuery(name = "Area.findByDesignacion", query = "SELECT a FROM Area a WHERE a.designacion = :designacion"), @NamedQuery(name = "Area.findByDesde", query = "SELECT a FROM Area a WHERE a.desde = :desde"), @NamedQuery(name = "Area.findByHasta", query = "SELECT a FROM Area a WHERE a.hasta = :hasta")})
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_area")
    private Integer idArea;
    @Column(name = "designacion")
    private String designacion;
    @Basic(optional = false)
    @Column(name = "desde")
    private short desde;
    @Basic(optional = false)
    @Column(name = "hasta")
    private short hasta;
    @JoinColumn(name = "id_concurso", referencedColumnName = "id_concurso")
    @ManyToOne(optional = false)
    private Concurso idConcurso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    private Collection<GrupoArea> grupoAreaCollection;
    public static void main(String[]args) throws Exception{
        new Area();
    }

    public Area() throws Exception {
    }

    public Area(Integer idArea) {
        this.idArea = idArea;
    }

    public Area(Integer idArea, short desde, short hasta) {
        this.idArea = idArea;
        this.desde = desde;
        this.hasta = hasta;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getDesignacion() {
        return designacion;
    }

    public void setDesignacion(String designacion) {
        this.designacion = designacion;
    }

    public short getDesde() {
        return desde;
    }

    public void setDesde(short desde) {
        this.desde = desde;
    }

    public short getHasta() {
        return hasta;
    }

    public void setHasta(short hasta) {
        this.hasta = hasta;
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
        hash += (idArea!=null ? idArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.idArea==null&&other.idArea!=null)||(this.idArea!=null&&!this.idArea.equals(other.idArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cecocadb.Area[idArea="+idArea+"]";
    }
}
