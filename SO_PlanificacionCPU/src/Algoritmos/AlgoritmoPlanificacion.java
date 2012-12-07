/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import so_planificacioncpu.*;
import java.util.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class AlgoritmoPlanificacion implements Runnable {

    protected Estado estado;
    protected PlanificacionGUI planificacionGUI;
    public ArrayList<Proceso> colaProcesos;
    protected int indexActualProceso;
    protected int factor = 1;
    Thread t;
    protected int tiempoTranscurrido;

    public AlgoritmoPlanificacion(String nombreAlgo) {
        this.planificacionGUI = new PlanificacionGUI(nombreAlgo);
        colaProcesos = new ArrayList<Proceso>();
        t = new Thread(this);
        tiempoTranscurrido = 0;
        indexActualProceso = 0;
    }

    public int getTiempoEspera(int ix) {
        int tiempoEsperaAnteriores = 0;
        for (int i = 0; i<ix; i++) {
            tiempoEsperaAnteriores += colaProcesos.get(i).getDuracion();
        }
        return tiempoEsperaAnteriores-colaProcesos.get(ix).getTiempoLlegada();
    }

    public double getTiempoEsperaMedio() {
        int t = 0;
        HashSet<Integer> unicos = new HashSet<Integer>();
        for (Proceso proceso : colaProcesos) {
            unicos.add(proceso.getIdProceso());
            t += proceso.getTiempoEspera();
        }
        return t*1./colaProcesos.size();
//        return t*1./unicos.size();
    }

    public double getTiempoRetornoMedio(){
        int tiempoRetorno = 0;
        HashSet<Integer> ids = new HashSet<Integer>();
        for (Proceso proceso : colaProcesos) {
            ids.add(proceso.getIdProceso());
            tiempoRetorno += proceso.getTiempoRetorno();
        }
        return tiempoRetorno*1./ids.size();
//        return tiempoRetorno*1./colaProcesos.size();
    }

    public void agregarProceso(int nombre, int duracion,int prioridad) {
        
    }

    public void start() {
        t.start();
    }

    public void run() {
        while (true) {
            if (todosLosProcesosTerminados()) {
                estado = Estado.Esperando;
            } else {
                estado = Estado.Procesando;
                indexActualProceso = colaProcesos.get(indexActualProceso).
                        procesar(tiempoTranscurrido);
                tiempoTranscurrido++;                
                System.err.println("Procesando"+indexActualProceso);
                if (indexActualProceso==colaProcesos.size()) {
                    //termino todos los procesos;
                }
                getGui().setTiempoTranscurrido(tiempoTranscurrido);
                getGui().setTiempoRetornoMedio(getTiempoRetornoMedio());
                System.out.println(colaProcesos);
            }
            planificacionGUI.setEstado(estado);
            try {
                Thread.sleep(1000/factor);
            } catch (InterruptedException ex) {
                Logger.getLogger(AlgoritmoPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean todosLosProcesosTerminados() {
        for (Proceso proceso : colaProcesos) {
            if (proceso.getRemaining()>0) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return the gui
     */
    public PlanificacionGUI getGui() {
        return planificacionGUI;
    }
}
