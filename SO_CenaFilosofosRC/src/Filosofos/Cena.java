package Filosofos;

import gui.Estado;
import Filosofos.Filosofo;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class Cena {

    public Filosofo primero,  ultimo;
    int counter = 0;
    long espera = 1;
    long comer = 1;
    public static Algoritmo alg;

    public Cena() {
    }

    public void setEstadosIniciales() {
        Filosofo p = primero;
        do {
            p.setEstadoInicial();
            p = p.der;
        } while (p!=primero);
    }

    public void iniciarHilos() {
        setEstadosIniciales();
        if (alg==Algoritmo.CICLICO) {
            primero.turn = true;
        }
        Filosofo p = primero;
        do {
            p.cenar();
            p = p.der;
        } while (p!=primero);
    }

    /**
     *
     * @param estado estado inicial del comensal
     * @param pensar tiempo que se toma en pensar
     * @param espera tiempo dispuesto a esperar por comer
     * @param comer tiempo que demora en terminar el plato de tallarines
     */
    public void ingresar(Estado estadoInicial, int pensar, int espera, int comer) {
        int factor = 10;
        int p = pensar*factor;
        int e = espera*factor;
        int c = comer*factor;
        Filosofo f = null;
        switch (alg) {
            case CICLICO:
                f = new Ciclico(factor, estadoInicial, p, e, c);
                break;
            case COLA_TENEDORES:
                f = new ColaTenedores(factor, estadoInicial, p, e, c);
                break;
            case COLA_TENEDORES_NODEADLOCK:
                f = new ColaTenedoresNoDeadLock(factor, estadoInicial, p, e, c);break;
        }
//        Filosofo f = new Filosofo(factor, estadoInicial, p, e, c);
        if (primero==null) {
            primero = ultimo = f;
        } else {
            f.der = primero;
            ultimo.der = f;
            f.izq = ultimo;
            ultimo = f;
            primero.izq = ultimo;
        }
    }

    public int tamaño() {
        int size = 0;
        Filosofo p = primero;
        do {
            size++;
            p = p.der;
        } while (p!=primero);
        return size;
    }
}
