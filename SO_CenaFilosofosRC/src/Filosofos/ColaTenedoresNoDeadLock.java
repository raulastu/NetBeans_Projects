/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Filosofos;

import gui.Estado;

public class ColaTenedoresNoDeadLock extends Filosofo implements Runnable {

    public ColaTenedoresNoDeadLock(int factor, Estado estado, int tiempoPensar, int tiempoEsperar, int tiempoComer) {
        super(factor, estado, tiempoPensar, tiempoEsperar, tiempoComer);
        System.out.println("ColaTenedores 2 ");
    }

    @Override
    void processPensar() {
        if (pensar==tiempoPensar) {
            pensar = 0;
            gui.prPensando.setValue(0);
            if (tomarTenedorDerecho()) {
                estado = Estado.ESPERANDO;
                processEsperar();
            }
        } else {
            pensar++;
            gui.prPensando.setValue(pensar);
            gui.setCurrent(gui.prPensando);
        }
    }

    @Override
    void processEsperar() {
        if (izq.tomarTenedorDerecho()) {
            esperar = 0;
            estado = Estado.COMIENDO;
            processComer();
        } else if (esperar==tiempoEsperar) {
            esperar = 0;
            soltarTenedorDerecho();
            estado = Estado.PENSANDO;
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
