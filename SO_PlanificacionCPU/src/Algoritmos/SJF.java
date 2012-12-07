package Algoritmos;

import so_planificacioncpu.*;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class SJF extends AlgoritmoPlanificacion implements Runnable {

    public SJF(String title) {
        super(title);
    }

    @Override
    public void agregarProceso(int idProceso, int duracion,int prioridad) {
        Proceso proc = new Proceso(colaProcesos.size(), idProceso, duracion, tiempoTranscurrido);
        colaProcesos.add(proc);// SJF

        for (int i = indexActualProceso+1; i<colaProcesos.size(); i++) {
            int lastId = colaProcesos.size()-1;
            if (colaProcesos.get(i).getDuracion()>duracion) {
                colaProcesos.get(i).setIdProceso(lastId);
                colaProcesos.get(lastId).setIdProceso(i);
                Collections.swap(colaProcesos, i, lastId);
                proc.setTiempoEspera(getTiempoEspera(colaProcesos.size()-1));
                break;
            }
        }
        for (int j = 0; j<colaProcesos.size(); j++) {
            colaProcesos.get(j).setTiempoEspera(getTiempoEspera(j));
        }
        getGui().setTiempoEsperaMedio(getTiempoEsperaMedio());
        getGui().setTiempoRetornoMedio(getTiempoRetornoMedio());
    }
}
