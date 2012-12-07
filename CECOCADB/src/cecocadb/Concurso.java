/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cecocadb;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "concurso")
@NamedQueries({@NamedQuery(name = "Concurso.findAll", query = "SELECT c FROM Concurso c"), @NamedQuery(name = "Concurso.findByIdConcurso", query = "SELECT c FROM Concurso c WHERE c.idConcurso = :idConcurso"), @NamedQuery(name = "Concurso.findByFecha", query = "SELECT c FROM Concurso c WHERE c.fecha = :fecha"), @NamedQuery(name = "Concurso.findByClaves", query = "SELECT c FROM Concurso c WHERE c.claves = :claves"), @NamedQuery(name = "Concurso.findByRonda", query = "SELECT c FROM Concurso c WHERE c.ronda = :ronda"), @NamedQuery(name = "Concurso.findByPeso", query = "SELECT c FROM Concurso c WHERE c.peso = :peso")})
public class Concurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_concurso")
    private Integer idConcurso;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "claves")
    private String claves;
    @Column(name = "ronda")
    private Short ronda;
    @Column(name = "peso")
    private BigDecimal peso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConcurso")
    private Collection<Grupo> grupoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "concurso")
    private Collection<ExamenEscuela> examenEscuelaCollection;
    @JoinColumn(name = "id_proceso", referencedColumnName = "id_proceso")
    @ManyToOne(optional = false)
    private Proceso idProceso;
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede")
    @ManyToOne(optional = false)
    private Sede idSede;
    @JoinColumn(name = "id_modalidad", referencedColumnName = "id_modalidad")
    @ManyToOne(optional = false)
    private Modalidad idModalidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConcurso")
    private Collection<Area> areaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConcurso")
    private Collection<Ubicacion> ubicacionCollection;
    public static void main(String[]args) throws Exception{
        new Concurso();
    }

    public Concurso() throws Exception {
    }

    public Concurso(Integer idConcurso) {
        this.idConcurso = idConcurso;
    }

    public Integer getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(Integer idConcurso) {
        this.idConcurso = idConcurso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getClaves() {
        return claves;
    }

    public void setClaves(String claves) {
        this.claves = claves;
    }

    public Short getRonda() {
        return ronda;
    }

    public void setRonda(Short ronda) {
        this.ronda = ronda;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
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

    public Proceso getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Proceso idProceso) {
        this.idProceso = idProceso;
    }

    public Sede getIdSede() {
        return idSede;
    }

    public void setIdSede(Sede idSede) {
        this.idSede = idSede;
    }

    public Modalidad getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(Modalidad idModalidad) {
        this.idModalidad = idModalidad;
    }

    public Collection<Area> getAreaCollection() {
        return areaCollection;
    }

    public void setAreaCollection(Collection<Area> areaCollection) {
        this.areaCollection = areaCollection;
    }

    public Collection<Ubicacion> getUbicacionCollection() {
        return ubicacionCollection;
    }

    public void setUbicacionCollection(Collection<Ubicacion> ubicacionCollection) {
        this.ubicacionCollection = ubicacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConcurso!=null ? idConcurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Concurso)) {
            return false;
        }
        Concurso other = (Concurso) object;
        if ((this.idConcurso==null&&other.idConcurso!=null)||(this.idConcurso!=null&&!this.idConcurso.equals(other.idConcurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cecocadb.Concurso[idConcurso="+idConcurso+"]";
    }
}
