/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Filosofos;

import gui.Estado;
import java.util.*;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class Ciclico extends Filosofo implements Runnable {

    public Ciclico(int factor, Estado estado, int tiempoPensar, int tiempoEsperar, int tiempoComer) {
        super(factor, estado, tiempoPensar, tiempoEsperar, tiempoComer);
        System.out.println("ciclicoc");
    }

    @Override
    public void processPensar() {
        if (pensar==tiempoPensar) {
            pensar = 0;
            if (tomarTenedorDerecho()) {
                estado = Estado.ESPERANDO;
                processEsperar();
            }
        } else {
            pensar++;
            gui.setCurrent(gui.prPensando);
        }
        gui.prPensando.setValue(pensar);
    }

    @Override
    void processEsperar() {
        if (turn) {
            esperar = 0;
            estado = Estado.COMIENDO;
            processComer();
        } else {
            if (esperar==tiempoEsperar) {
                esperar = 0;
            } else {
                esperar++;
                gui.setCurrent(gui.prEsperando);
            }
        }
        gui.prEsperando.setValue(esperar);
    }

    @Override
    void processComer() {
        if (comer==tiempoComer) {
            comer = 0;
            soltarTenedorDerecho();
            izq.soltarTenedorDerecho();
            estado = Estado.PENSANDO;
            turn = false;
            der.turn = true;
            processPensar();
        } else {
            comer++;
            gui.setCurrent(gui.prComiendo);
        }
        gui.prComiendo.setValue(comer);
    }
}
