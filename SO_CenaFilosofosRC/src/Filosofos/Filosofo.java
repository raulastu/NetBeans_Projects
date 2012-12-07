package Filosofos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import gui.*;

public class Filosofo implements Runnable {

    boolean turn = false;
    int factor;
    public Filosofo izq,  der;
    private int ID;
    private String nombre;
    Thread t;
    int tiempoPensar;
    int tiempoComer;
    int tiempoEsperar;
    Estado estado;
    int comer = 0;
    int esperar = 0;
    int pensar = 0;
    boolean tenedorDerecho = true;
    public FilosofoGUI gui = new FilosofoGUI();

    public Filosofo(int factor, Estado estado, int tiempoPensar, int tiempoEsperar, int tiempoComer) {
        this.factor = factor;
        this.estado = estado;
        this.tiempoPensar = tiempoPensar;
        this.tiempoEsperar = tiempoEsperar;
        this.tiempoComer = tiempoComer;
        gui.prPensando.setMaximum(tiempoPensar);
        gui.prEsperando.setMaximum(tiempoEsperar);
        gui.prComiendo.setMaximum(tiempoComer);
        t = new Thread(this);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    void cenar() {
        t.start();
    }

    public void setEstadoInicial() {
        switch (estado) {
            case ESPERANDO:
                tomarTenedorDerecho();
                break;
            case COMIENDO:
                System.out.println("tomarTenedorDerecho()"+tomarTenedorDerecho());
                System.out.println("izq.tomar "+izq.tomarTenedorDerecho());
//                estado = Estado.ESPERANDO;
                break;
        }
    }

    synchronized public boolean tomarTenedorDerecho() {
        if (tenedorDerecho) {
            tenedorDerecho = false;
            return true;
        } else {
            return false;
        }
    }

    synchronized public boolean soltarTenedorDerecho() {
        tenedorDerecho = true;
        return true;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000/factor);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cena.class.getName()).log(Level.SEVERE, null, ex);
            }
            switch (estado) {
                case PENSANDO:
                    processPensar();
                    break;
                case ESPERANDO:
                    processEsperar();
                    break;
                case COMIENDO:
                    processComer();
                    break;
            }
        }
    }

    void processPensar() {
//        if (pensar==tiempoPensar) {
//            pensar = 0;
//            gui.prPensando.setValue(0);
//            if (tomarTenedorDerecho()) {
//                estado = Estado.ESPERANDO;
//                processEsperar();
//            }
//        } else {
//            pensar++;
//            gui.prPensando.setValue(pensar);
//            gui.setCurrent(gui.prPensando);
//        }
    }

    void processEsperar() {
//        if (izq.tomarTenedorDerecho()) {
//            esperar = 0;
//            gui.prEsperando.setValue(0);
//            if (Cena.alg==Algortimos.CICLICO) {
//                if (turn) {
//                    estado = Estado.COMIENDO;
//                }
//            } else {
//                estado = Estado.COMIENDO;
//            }
////            processComer();
//        } else if (esperar==tiempoEsperar) {
//            if (Cena.DEADLOCK) {
//                esperar = 0;
//                gui.prEsperando.setValue(0);
//            } else {
//                esperar = 0;
//                gui.prEsperando.setValue(0);
//                soltarTenedorDerecho();
//                estado = Estado.PENSANDO;
////                processPensar();
//            }
//        } else {
//            esperar++;
//            gui.prEsperando.setValue(esperar);
//            gui.setCurrent(gui.prEsperando);
//        }
    }

    void processComer() {
//        if (comer==tiempoComer) {
//            comer = 0;
//            soltarTenedorDerecho();
//            izq.soltarTenedorDerecho();
//            gui.prComiendo.setValue(0);
//            estado = Estado.PENSANDO;
//            if (Cena.alg==Algortimos.CICLICO) {
//                turn = false;
//                der.estado = Estado.COMIENDO;
//                der.turn = true;
//            }
////            processPensar();
//        } else {
//            comer++;
//            gui.prComiendo.setValue(comer);
//            gui.setCurrent(gui.prComiendo);
//        }
    }
}
