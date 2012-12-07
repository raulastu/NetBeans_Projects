package domApli;

import biblioteca.LE;

public class PrgVideos {

    String peliculas[][];
    int alquileres[][];
    int numPel;

    public PrgVideos() {
        peliculas = new String[10][2];
        alquileres = new int[10][4];
        numPel = 0;
    }

    public static void main(String[] args) {
        PrgVideos x = new PrgVideos();
        x.menu();
    }

    public void menu() {
        int opc, sw = 1;
        String menu = "---Ingrese una de las opciones---\n" + "----------------\n" + "[1]Ingresar datos\n" + "[2]Mostrar datos\n" + "[3]Alquileres de videos\n" + "[4]Devoluci�n de videos\n" + "[5]Calcular utilidad total\n" + "[6]Pel�cula m�s pedida\n" + "[0]Finalizar\n" + "-----------------------";
        do {
            opc = LE.leerInt(menu);
            switch (opc) {
                case 1:
                    ingresar();
                    sw = 2;
                    break;

                case 2:
                    if (sw == 1) {
                        LE.mostrarError("Error...Ingrese primero los datos");
                    } else {
                        mostrar();
                    }
                    break;


                case 3:
                    if (sw == 1) {
                        LE.mostrarError("Error...Ingrese primero los datos");
                    } else {
                        alquiler();
                    }
                    break;

                case 4:
                    if (sw == 1) {
                        LE.mostrarError("Error...Ingrese primero los datos");
                    } else {
                        devolucion();
                    }
                    break;

                case 5:
                    if (sw == 1) {
                        LE.mostrarError("Error...Ingrese primero los datos");
                    } else {
                        utilidadTotal();
                    }
                    break;

                case 6:
                    if (sw == 1) {
                        LE.mostrarError("Error...Ingrese primero los datos");
                    } else {
                        masPedida();
                    }
                    break;

                case 0:
                    finalizar();
                    break;

            }
        } while (opc != 0);
    }

    public void ingresar() {
        String texto;
        int rpta;
        do {
            if (numPel == peliculas.length) {
                aumentar();
            }

            peliculas[numPel][0] = LE.leerString("Ingrese Codigo de la pelicula" + (numPel + 1));

            peliculas[numPel][1] = LE.leerString("Ingrese nombre de la pelicula " + peliculas[numPel][0]);
            texto = "Codigo de la pelicula:" + peliculas[numPel][0] + "\n";
            texto += "Nombre de la pelicula:" + peliculas[numPel][1] + "\n";
            alquileres[numPel][0] = LE.leerInt(texto + "Ingrese el costo de alquileres");
            alquileres[numPel][1] = LE.leerInt(texto + "Ingrese la cantidad de copias");
            alquileres[numPel][2] = 0;
            alquileres[numPel][3] = 0;
            numPel++;
            rpta = LE.mostrarPreguntaOpcion2("�Desea ingresar datos de otro video?");
        } while (rpta == 0);
    }

    public void aumentar() {
        String pel[][] = new String[peliculas.length + 5][2];
        int alq[][] = new int[peliculas.length + 5][4];
        for (int i = 0; i < numPel; i++) {
            pel[i][0] = peliculas[i][0];
            pel[i][1] = peliculas[i][1];
            alq[i][0] = alquileres[i][0];
            alq[i][1] = alquileres[i][1];
            alq[i][2] = alquileres[i][2];
            alq[i][3] = alquileres[i][3];
        }
        peliculas = pel;
        alquileres = alq;
    }

    public void mostrar() {

        String datos = "Datos de Pel�culas\n";
        datos += "Código/Nombre/Precio/Stock/Alquiladas\n\n";
        for (int i = 0; i < numPel; i++) {
            datos += peliculas[i][0] + "\t/" + peliculas[i][1] + "\t/" + alquileres[i][0] +
                    "\t/" + alquileres[i][1] + "\t/" + alquileres[i][2] + "\n";

        }
        LE.mostrarInformacion(datos);
    }

    public void alquiler() {
        int solicitadas, disponibles, opc;
        String texto;
        String codigo = LE.leerString("Ingrese el codigo de la pel�cula que desea alquilar");
        int pos = buscarCodigo(codigo);

        if (pos != -1) {
            if (alquileres[pos][1] - alquileres[pos][2] == 0) {
                LE.mostrarInformacion("En este momento todas las copias est�n alquiladas");
            } else {
                solicitadas = LE.leerInt("�Cu�ntas copias desea alquilar?");
                disponibles = alquileres[pos][1] - alquileres[pos][2];
                if (solicitadas > disponibles) {
                    LE.mostrarError("No se cuenta con la cantidad solicitada");
                } else {

                    texto = "Pelicula:" + peliculas[pos][0] + "\n";
                    texto += "C�digo:" + peliculas[pos][1] + "\n";
                    texto += "Total de copias solicitadas:" + solicitadas + "\n";
                    texto = "El monto total a pagar por las copias es de:";
                    texto += (solicitadas * alquileres[pos][0]) + "soles\n\n";
                    texto += "�Desea realizar el alquiler?";
                    opc = LE.mostrarPreguntaOpcion2(texto);

                    if (opc == 0) {
                        alquileres[pos][2] += solicitadas;
                        alquileres[pos][3] += solicitadas;
                        texto = "Se ha alquilado" + solicitadas + "video(s) a un costo de";
                        texto += solicitadas * alquileres[pos][0] + "soles\n";
                        LE.mostrarInformacion(texto);

                    }
                }
            }
        } else {
            LE.mostrarError("C�digo de la pel�cula no existe!!!");
        }
    }

    public int buscarCodigo(String cod) {
        for (int i = 0; i < numPel; i++) {
            if (peliculas[i][0].equalsIgnoreCase(cod)) {
                return i;
            }
        }
        return -1;
    }

    public void devolucion() {
        int devueltas, total;
        String texto;
        String codigo = LE.leerString("Ingrese c�digo de la pel�cula que desea devolver");
        int pos = buscarCodigo(codigo);

        if (pos != -1) {
            devueltas = LE.leerInt("Cu�ntas copias de la pel�cula va a devolver?");
            total = alquileres[pos][2] - devueltas;
            if (total < 0) {
                LE.mostrarError("No se puede devolver la cantidad de copias indicada");
            } else {
                alquileres[pos][2] -= devueltas;
                texto = "Se ha registrado la devoluci�n de" + devueltas + "copias\n";
                texto += "de la pelicula" + peliculas[pos][0] + "con c�digo" + codigo;
                LE.mostrarInformacion(texto);
            }
        } else {
            LE.mostrarError("C�digo de la pel�cula no existe");
        }
    }

    public void utilidadTotal() {
        String dato = "La utilidad es de" + "\n";
        int utilidad = 0;
        for (int i = 0; i < numPel; i++) {
            utilidad += (alquileres[i][0] * alquileres[i][3]);
        }
        LE.mostrarResultado(dato + utilidad + "sol(es).");
    }

    public void masPedida() {
        int maxPedidos = 0;
        for (int i = 0; i < numPel; i++) {
            if (alquileres[i][3] > maxPedidos) {
                maxPedidos = alquileres[i][3];
            }
        }
        String masPedidos = "MAS PEDIDOS (con " + maxPedidos + " alquileres)\n ";
        for (int i = 0; i < numPel; i++) {
            if (alquileres[i][3] == maxPedidos) {
                masPedidos += peliculas[i][0] + " \n";
            }
        }
        LE.mostrarInformacion(masPedidos);
    }

    public void finalizar() {
        LE.mostrarInformacion("Usted ha finalizado el programa");
    }
}
