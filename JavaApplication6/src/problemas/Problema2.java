package problemas;

import biblioteca.*;

public class Problema2 {

    public static void main(String[] args) {
        double sueldo = 485;
        for (int i = 1; i <= 5; i++) {
            sueldo = sueldo + 0.18 * LE.leerDouble("Ingrese la venta nro " + i + " del mes");
        }
        LE.mostrarInformacion("El sueldo neto es " + sueldo);
    }
}
