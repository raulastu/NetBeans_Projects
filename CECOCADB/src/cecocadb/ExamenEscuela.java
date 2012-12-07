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
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "examen_escuela")
@NamedQueries({@NamedQuery(name = "ExamenEscuela.findAll", query = "SELECT e FROM ExamenEscuela e"), @NamedQuery(name = "ExamenEscuela.findByIdEscuela", query = "SELECT e FROM ExamenEscuela e WHERE e.examenEscuelaPK.idEscuela = :idEscuela"), @NamedQuery(name = "ExamenEscuela.findByIdConcurso", query = "SELECT e FROM ExamenEscuela e WHERE e.examenEscuelaPK.idConcurso = :idConcurso"), @NamedQuery(name = "ExamenEscuela.findByVacantes", query = "SELECT e FROM ExamenEscuela e WHERE e.vacantes = :vacantes"), @NamedQuery(name = "ExamenEscuela.findByNroPostulantes", query = "SELECT e FROM ExamenEscuela e WHERE e.nroPostulantes = :nroPostulantes"), @NamedQuery(name = "ExamenEscuela.findByPromedio", query = "SELECT e FROM ExamenEscuela e WHERE e.promedio = :promedio"), @NamedQuery(name = "ExamenEscuela.findByPuntajeMinimo", query = "SELECT e FROM ExamenEscuela e WHERE e.puntajeMinimo = :puntajeMinimo"), @NamedQuery(name = "ExamenEscuela.findByNroFaltantes", query = "SELECT e FROM ExamenEscuela e WHERE e.nroFaltantes = :nroFaltantes"), @NamedQuery(name = "ExamenEscuela.findByNroIngresantes", query = "SELECT e FROM ExamenEscuela e WHERE e.nroIngresantes = :nroIngresantes")})
public class ExamenEscuela implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExamenEscuelaPK examenEscuelaPK;
    @Column(name = "vacantes")
    private Integer vacantes;
    @Column(name = "nro_postulantes")
    private Integer nroPostulantes;
    @Column(name = "promedio")
    private BigDecimal promedio;
    @Column(name = "puntaje_minimo")
    private BigDecimal puntajeMinimo;
    @Column(name = "nro_faltantes")
    private Integer nroFaltantes;
    @Column(name = "nro_ingresantes")
    private Integer nroIngresantes;
    @JoinColumn(name = "id_concurso", referencedColumnName = "id_concurso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Concurso concurso;
    @JoinColumn(name = "id_escuela", referencedColumnName = "id_escuela", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Escuela escuela;
    public static void main(String[]args) throws Exception{
        new ExamenEscuela();
    }

    public ExamenEscuela() throws Exception {
    }

    public ExamenEscuela(ExamenEscuelaPK examenEscuelaPK) {
        this.examenEscuelaPK = examenEscuelaPK;
    }

    public ExamenEscuela(String idEscuela, int idConcurso) {
        this.examenEscuelaPK = new ExamenEscuelaPK(idEscuela, idConcurso);
    }

    public ExamenEscuelaPK getExamenEscuelaPK() {
        return examenEscuelaPK;
    }

    public void setExamenEscuelaPK(ExamenEscuelaPK examenEscuelaPK) {
        this.examenEscuelaPK = examenEscuelaPK;
    }

    public Integer getVacantes() {
        return vacantes;
    }

    public void setVacantes(Integer vacantes) {
        this.vacantes = vacantes;
    }

    public Integer getNroPostulantes() {
        return nroPostulantes;
    }

    public void setNroPostulantes(Integer nroPostulantes) {
        this.nroPostulantes = nroPostulantes;
    }

    public BigDecimal getPromedio() {
        return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
        this.promedio = promedio;
    }

    public BigDecimal getPuntajeMinimo() {
        return puntajeMinimo;
    }

    public void setPuntajeMinimo(BigDecimal puntajeMinimo) {
        this.puntajeMinimo = puntajeMinimo;
    }

    public Integer getNroFaltantes() {
        return nroFaltantes;
    }

    public void setNroFaltantes(Integer nroFaltantes) {
        this.nroFaltantes = nroFaltantes;
    }

    public Integer getNroIngresantes() {
        return nroIngresantes;
    }

    public void setNroIngresantes(Integer nroIngresantes) {
        this.nroIngresantes = nroIngresantes;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (examenEscuelaPK!=null ? examenEscuelaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenEscuela)) {
            return false;
        }
        ExamenEscuela other = (ExamenEscuela) object;
        if ((this.examenEscuelaPK==null&&other.examenEscuelaPK!=null)||(this.examenEscuelaPK!=null&&!this.examenEscuelaPK.equals(other.examenEscuelaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cecocadb.ExamenEscuela[examenEscuelaPK="+examenEscuelaPK+"]";
    }
}
