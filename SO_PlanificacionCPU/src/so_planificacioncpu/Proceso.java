/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package so_planificacioncpu;

import java.awt.Dimension;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class Proceso {

    private int idProceso;
    private int tiempoLlegada;
    private int tiempoEspera;
    private int tiempoAtencion;
    private int duracion;
    private int remaining;
    private int tiempoRetorno;
    private ProcesoGUI gui;
    private Thread t;
    private int indexProceso;
    private int prioridad;

    public int getIdProceso() {
        return idProceso;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Proceso(int indexProceso, int idProceso, int duracion, int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
        this.indexProceso = indexProceso;
        this.idProceso = idProceso;
        this.duracion = duracion;
        this.remaining = duracion;
        gui = new ProcesoGUI(this);
        gui.jProgressBar1.setString("p"+idProceso);
        gui.jProgressBar1.setMaximum(duracion);
        gui.setPreferredSize(new Dimension(duracion*15, 65));
//        setTiempoLlegada(1);
    }

    public int cortarProceso() {
        int transcurrido = duracion-remaining;
        gui.setPreferredSize(new Dimension(transcurrido*15, 65));
        this.duracion = transcurrido;
        setTiempoRetorno(tiempoAtencion+duracion-tiempoLlegada);
//        tiempoRetorno = tiempoAtencion+duracion-tiempoLlegada;
        gui.jProgressBar1.setMaximum(transcurrido);
        int res = remaining;
        remaining = 0;
        return res;
    }

    public void setIdProceso(int idProceso) {
        this.indexProceso = idProceso;
    }

    public int procesar(int currentTime) {
        if (remaining==duracion)//si comienza a procesar
        {
            setTiempoAtencion(currentTime);
        }
        remaining--;
        gui.jProgressBar1.setValue(duracion-remaining);
//        tiempoRetorno = tiempoAtencion+duracion-tiempoLlegada;        
        if (remaining<=0) {
            tiempoRetorno = tiempoAtencion+duracion-tiempoLlegada;
            setTiempoRetorno(tiempoRetorno);
            return indexProceso+1;
        } else {
            return indexProceso;
        }
    }

    public int getDuracion() {
        return duracion;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public int getTiempoRetorno() {
        return tiempoRetorno;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
        gui.labTEspera.setText(tiempoEspera+"");
    }

    public void setTiempoAtencion(int t) {
        tiempoAtencion = t;
        gui.labTtranscurrido.setText(""+tiempoAtencion);
    }

    public void setTiempoRetorno(int t) {
        tiempoRetorno = t;
        gui.labTRetorno.setText(""+t);
    }

    /**
     * @return the remaining
     */
    public int getRemaining() {
        return remaining;
    }

    public ProcesoGUI getProcesoGui() {
        return gui;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    @Override
    public String toString() {
        return idProceso+":"+tiempoRetorno+"-"+duracion;
    }
}
