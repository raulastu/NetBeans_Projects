package Algoritmos;

import so_planificacioncpu.Proceso;

public class Prioridades extends AlgoritmoPlanificacion {

    int prioridad;

    public Prioridades(String name) {
        super(name);
    }

    @Override
    public void agregarProceso(int id, int duracion, int prioridad) {
        Proceso proc = new Proceso(colaProcesos.size(), id, duracion, tiempoTranscurrido);
        colaProcesos.add(proc);// FIFO
        proc.setTiempoEspera(getTiempoEspera(colaProcesos.size()-1));
        getGui().setTiempoEsperaMedio(getTiempoEsperaMedio());
    }
}
