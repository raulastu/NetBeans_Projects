package domApli;

import biblioteca.LE;

public class PrgAlumnos {

    String nombres[];
    long codigos[];
    double promedios[];
    int numAlu;

    public static void main(String[] args) {
        PrgAlumnos x = new PrgAlumnos();
        x.menu();
    }

    public PrgAlumnos() {
        nombres = new String[9999];
        codigos = new long[9999];
        promedios = new double[9999];
//        for (int i = 0; i < 3; i++) {
//            nombres[i] = "x";
//            codigos[i] = 123;
//            promedios[i] = 10 + i * .5;
//        }
        numAlu = 0;
    }

    public void menu() {
        String menu =
                "Curso de Algoritmo y Estructura de Datos \n" +
                "-----------------------------------------\n" +
                "1)Ingreso de datos de los alumnos \n" +
                "2)Mostrar datos de los alumnos \n" +
                "3)Porcentaje de alumnos aprobados \n" +
                "4)Porcentaje de alumnos desaprobados\n" +
                "5)Alumnos con el mayor promedio\n" +
                "6)Finalizar\n" +
                "---------------------------------------\n" +
                "Ingrese una opcion:\n";
        int opc;
        do {
            opc = LE.leerInt(menu);
            switch (opc) {
                case 1:
                    ingresar();
                    break;
                case 2:
                    mostrar();
                    break;
                case 3:
                    porcentajeAprobados();
                    break;
                case 4:
                    porcentajeDesaprobados();
                    break;
                case 5:
                    alumnosConMayorNota();
                    break;
                case 6:
//                    finalizar();
                    break;
                default:
                    LE.mostrarError("Error... Opcion desconocida");
            }
        } while (opc != 6);
    }

    public void ingresar() {
        int rpta;
        do {
            nombres[numAlu] = LE.leerString("Ingrese el nombre del alumno" + (numAlu + 1));
            codigos[numAlu] = LE.leerLong("Ingrese el código del alumno" + (numAlu + 1));
            do {
                promedios[numAlu] = LE.leerDouble("Ingrese el promedio del alumno" + (numAlu + 1));
                if (promedios[numAlu] < 0 || promedios[numAlu] > 20) {
                    LE.mostrarError("Promedio invalido, reingrese el promedio");
                }
            } while (promedios[numAlu] < 0 || promedios[numAlu] > 20);
            numAlu++;
            rpta = LE.mostrarPreguntaOpcion2("¿Desea continuar?");
        } while (rpta == 0);
    }

    private void mostrar() {
        String datos = "Los datos de los alumnos son los siguientes:\n" +
                "nombre/codigo/promedio\n\n";
        for (int i = 0; i < numAlu; i++) {
            datos += (i + 1) + "" + nombres[i] + "/" + codigos[i] + "/" + promedios[i] + "\n";
        }
        LE.mostrarInformacion(datos);
    }

    private double porApro() {
        double porApro = 0;
        int cont = 0;
        for (int i = 0; i < numAlu; i++) {
            if (promedios[i] >= 10.5) {
                cont++;
            }
        }
        porApro = (cont * 100) / (double) numAlu;
        porApro = Math.round(porApro * 100) / 100.0;
        return porApro;
    }

    private double porDesapro() {
        double porApro = 0;
        int cont = 0;
        for (int i = 0; i < numAlu; i++) {
            if (promedios[i] <= 10.5) {//ERROR: DEBERIA SER promedios[i] < 10.5
                cont++;
            }
        }
        porApro = (cont * 100) / (double) numAlu;
        porApro = Math.round(porApro * 100) / 100.0;
        return porApro;
    }

    private void porcentajeAprobados() {
        LE.mostrarResultado("Alumnos Aprobados:" + porApro() + "%");
    }

    private void porcentajeDesaprobados() {
        double porDesa = 100.0 - porApro(); // SE PODRIA
        LE.mostrarResultado("Alumnos Desaprobados:" + porDesa + "%");
    }

    private void alumnosConMayorNota() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
