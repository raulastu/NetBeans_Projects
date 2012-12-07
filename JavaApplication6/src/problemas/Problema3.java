package problemas;

import biblioteca.*;

public class Problema3 {

    public static void main(String[] args) {
        int nroAlumnos = LE.leerInt("Ingrese el nro de alumnos");
        String[][] arreglo = new String[nroAlumnos][3];
        double promedio = 0;
        for (int i = 0; i < nroAlumnos; i++) {
            arreglo[i][0] = LE.leerString("Ingrese el nombre del alumno");
            arreglo[i][1] = LE.leerString("Ingrese el código del alumno");
            int nota = -1;
            String msg = "Ingrese la nota del alumno";
            while (nota < 0 || nota > 20) {
                nota = LE.leerInt(msg);
                msg = "Ingrese la nota correctamente porfavor entre 0 y 20";
            }
            arreglo[i][2] = nota + "";
            promedio += nota;
        }
        promedio = promedio / (double) nroAlumnos;
        int maxNota = -1;
        int minNota = Integer.MAX_VALUE;
        String s = "";
        for (int i = 0; i < arreglo.length; i++) {
            int nota = Integer.parseInt(arreglo[i][2]);
            if (nota < promedio) {
                s += "cod:" + arreglo[i][1] + "\t nombre:" + arreglo[i][0] + "\t nota de practicas:" + arreglo[i][2] + " \n";
                maxNota = Math.max(maxNota, nota);
                minNota = Math.min(minNota, nota);
            }
        }
        s = "Mayor nota: " + maxNota + " Menor nota:" + minNota + "\n\n" + s;
        LE.mostrarInformacion(s);
    }
}
