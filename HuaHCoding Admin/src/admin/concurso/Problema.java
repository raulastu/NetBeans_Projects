package admin.concurso;

import java.util.ArrayList;

public class Problema {

    private String nombre;
    private int puntos;
    private ArrayList<IOPair> ioList;

    public Problema(String nombre, int puntos, ArrayList<IOPair> ioList) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.ioList = ioList;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public ArrayList<IOPair> getIoList() {
        return ioList;
    }
}
