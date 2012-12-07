/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import java.util.*;
import java.util.regex.*;
import so_planificacioncpu.Proceso;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class FIFO extends AlgoritmoPlanificacion {

    public FIFO(String title) {
        super(title);
    }

    @Override
    public void agregarProceso(int idProceso, int duracion,int prioridad) {
        Proceso proc = new Proceso(colaProcesos.size(), idProceso, duracion, tiempoTranscurrido);
        colaProcesos.add(proc);// FIFO
        proc.setTiempoEspera(getTiempoEspera(colaProcesos.size()-1));
        getGui().setTiempoEsperaMedio(getTiempoEsperaMedio());        
    }
}
