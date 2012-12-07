package Algoritmos;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import so_planificacioncpu.Estado;
import so_planificacioncpu.Proceso;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class RoundRobin extends AlgoritmoPlanificacion {

    int quantum = 2;

    private int counter = 0;

    public RoundRobin(String nombre) {
        super(nombre);
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    @Override
    public void agregarProceso(int idProceso, int duracion, int prioridad) {
        Proceso proc = new Proceso(colaProcesos.size(), idProceso, duracion, tiempoTranscurrido);
        colaProcesos.add(proc);// FIFO
        proc.setTiempoEspera(getTiempoEspera(colaProcesos.size()-1));
        getGui().setTiempoEsperaMedio(getTiempoEsperaMedio());
    }

    @Override
    public void run() {
        while (true) {
            if (todosLosProcesosTerminados()) {
                estado = Estado.Esperando;
            } else {
                estado = Estado.Procesando;
                counter++;
//                int duracion = colaProcesos.get(indexActualProceso).getDuracion();
                int index = indexActualProceso;
                indexActualProceso = colaProcesos.get(indexActualProceso).
                        procesar(tiempoTranscurrido);
                tiempoTranscurrido++;                
                if (indexActualProceso==index) {
                    if (counter>=quantum) {
                        int faltante = colaProcesos.get(indexActualProceso).cortarProceso();
                        agregarProceso(colaProcesos.get(indexActualProceso).getIdProceso(), faltante, 0);
                        indexActualProceso++;
                        counter = 0;
//                        tiempoTranscurrido--;
                    }
                }else{
                    counter=0;
                }                
                System.out.println(colaProcesos);
                System.err.println("Procesando"+indexActualProceso);                
                getGui().setTiempoTranscurrido(tiempoTranscurrido);
                getGui().setTiempoRetornoMedio(getTiempoRetornoMedio());                
            }
            planificacionGUI.setEstado(estado);
            try {
                Thread.sleep(1000/factor);
            } catch (InterruptedException ex) {
                Logger.getLogger(AlgoritmoPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
