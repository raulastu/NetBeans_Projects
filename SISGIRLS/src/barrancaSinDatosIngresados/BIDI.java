package barrancaSinDatosIngresados;

import biblioteca.LE;

public class BIDI {

    int[] notas;
    int numNot;

    public BIDI() {
        notas = new int[10];
        notas[0] = 15;
        notas[1] = 10;
        notas[2] = 11;
        notas[3] = 12;
        notas[4] = 8;
        notas[5] = 12;
        notas[6] = 13;
        notas[7] = 15;
        notas[8] = 15;
        notas[9] = 20;
        numNot = 10;
    }
 
    public int buscar(int X) {
        int pos = -1;        
        for (int i = 0; i < numNot; i++) {
            if (X == notas[i]) {
                pos=i;
                break;
            }
        }
        return pos;
    }

    public void calcularNotaMaxima() {
        int nMaximo = 0;
        for (int i = 0; i < numNot; i++) {
            if (notas[i] > nMaximo) {
                nMaximo = notas[i];
            }
        }
        LE.mostrarInformacion("NOTA MAXIMA:" + nMaximo);
    }

    public void calcularNotaMinima() {
        int nMinimo = 1000;
//        nMinimo = Math.min(nMinimo, notas[0]); // nMinimo = 15
//        nMinimo = Math.min(nMinimo, notas[1]); // nMinimo = 10
//        nMinimo = Math.min(nMinimo, notas[2]); //nMinimo = 10
//        nMinimo = Math.min(nMinimo, notas[3]);  //nMinimo = 0
//        nMinimo = Math.min(nMinimo, notas[4]); //nMinimo = 0
//        nMinimo = Math.min(nMinimo, notas[5]); //nMinimo = 0
//        nMinimo = Math.min(nMinimo, notas[6]); //nMinimo = 0
//        nMinimo = Math.min(nMinimo, notas[7]); //nMinimo = 0
//        nMinimo = Math.min(nMinimo, notas[8]);//nMinimo = 0
//        nMinimo = Math.min(nMinimo, notas[9]); //nMinimo = 0
        for (int i = 0; i < numNot; i++) {
            nMinimo = Math.min(nMinimo, notas[i]);
        }
        LE.mostrarInformacion("NOTA MINIMA:" + nMinimo);
    }

    public void calcularPromedio() {
        double promedio = 0;
        for (int i = 0; i < numNot; i++) {
            promedio = promedio + notas[i];
        }
        LE.mostrarInformacion("EL PROMEDIO ES " + promedio / numNot);
    }

    public static void main(String[] args) {
        BIDI b = new BIDI();
        b.calcularPromedio();
    }
}
