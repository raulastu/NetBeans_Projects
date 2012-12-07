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

public class ColaTenedores extends Filosofo implements Runnable {

    public ColaTenedores(int factor, Estado estado, int tiempoPensar, int tiempoEsperar, int tiempoComer) {
        super(factor, estado, tiempoPensar, tiempoEsperar, tiempoComer);
        System.out.println("ColaTenedores");
    }

    @Override
    void processPensar() {
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
        if (izq.tomarTenedorDerecho()) {
            esperar = 0;
            estado = Estado.COMIENDO;
            processComer();
        } else if (esperar==tiempoEsperar) {
            esperar = 0;
        } else {
            esperar++;
            gui.setCurrent(gui.prEsperando);
        }
        gui.prEsperando.setValue(esperar);
    }

    @Override
    void processComer() {
        if (comer==tiempoComer) {
            comer = 0;
            soltarTenedorDerecho();
            izq.soltarTenedorDerecho();
            gui.prComiendo.setValue(0);
            estado = Estado.PENSANDO;
            processPensar();
        } else {
            comer++;
            gui.prComiendo.setValue(comer);
            gui.setCurrent(gui.prComiendo);
        }
    }
}
