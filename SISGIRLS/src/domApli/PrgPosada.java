package domApli;

import biblioteca.LE;

public class PrgPosada {

    int datosPosada[][], numHab;

    public PrgPosada() {
        datosPosada = new int[9999][4];
        numHab = 0;
    }

    public static void main(String[] args) {
        PrgPosada x = new PrgPosada();
        x.menu();

    }

    public void menu() {
        String texto = "Posada\"Don Alfonso\"\n" + "...\n" + "[1]Ingreso de datos de las habitaciones\n" + "[2]Mostrar datos de las habitaciones\n" + "...\n" + "[3]Indicar el estado de una habitaci�n\n" + "[4]Alquilar habitaciones\n" + "[5]Desocupar habitaciones\n" + "[6]Calcular la ganancia\n" + "...\n" + "[7]Ordenar seg�n n�mero de habitaci�n\n" + "[8]Modificar seg�n n�mero de habitaci�n\n" + "[9]Eliminar seg�n n�mero de habitacion\n" + "...\n" + "[0]Finalizar\n" + "...\n" + "Ingrese su opcion:";
        int opc = 0, numCuarto, pos = -1;
        int sw = 1;
        do {
            opc = LE.leerInt(texto);
            switch (opc) {
                case 1:
                    ingresarDatos();
                    sw = 2;
                    break;

                case 2:
                    if (sw == 1) {
                        LE.mostrarError("Ingrese los datos");
                        break;
                    }
                    visualizarDatos();
                    break;

                case 3:
                    if (sw == 1) {
                        LE.mostrarError("Ingrese los datos");
                        break;
                    }
                    numCuarto = LE.leerInt("Ingrese el n�mero de habitaci�n que desea buscar");
                    pos = buscar(numCuarto);
                    indicarEstado(pos);
                    break;

                case 4:
                    if (sw == 1) {
                        LE.mostrarError("Ingrese los datos");
                        break;
                    }
                    numCuarto = LE.leerInt("Ingrese el n�mero de habitaci�n que desea alquilar");
                    pos = buscar(numCuarto);
                    alquilar(pos);
                    break;
                case 5:
                    if (sw == 1) {
                        LE.mostrarError("Ingrese los datos");
                        break;
                    }
                    numCuarto = LE.leerInt("Ingrese el n�mero de habitaci�n que desea desocupar");
                    pos = buscar(numCuarto);
                    desocupar(pos);
                    break;
                case 6:
                    if (sw == 1) {
                        LE.mostrarError("Ingrese los datos");
                        break;
                    }
                    calcularGanancia();
                    break;
                case 7:
                    if (sw == 1) {
                        LE.mostrarError("Ingrese los datos");
                        break;
                    }
                    ordenar();
                    break;
                case 8:
                    if (sw == 1) {
                        LE.mostrarError("Ingrese los datos");
                        break;
                    }
                    numCuarto = LE.leerInt("Ingrese el n�mero de la habitaci�n");
                    pos = buscar(numCuarto);
                    modificar(pos);
                    break;
                case 9:
                    if (sw == 1) {
                        LE.mostrarError("Ingrese los datos");
                        break;
                    }
                    numCuarto = LE.leerInt("Ingrese el n�mero de la habitaci�n");
                    pos = buscar(numCuarto);
                    eliminar(pos);
                    break;
                case 0:
                    finalizar();
                    break;
                default:
                    LE.mostrarError("Ingrese una opci�n v�lida");
            }
        } while (opc != 0);
    }

    public void ingresarDatos() {
        int rpta;
        do {

            int nroHabitacion = -1;
            do {
                nroHabitacion = LE.leerInt("Ingrese el n�mero de la habitacion");
                if (buscar(nroHabitacion) >= 0) {
                    LE.mostrarError("Numero de habitacion ya existe");
                }
            } while (buscar(nroHabitacion) >= 0);

            datosPosada[numHab][0] = nroHabitacion;
            datosPosada[numHab][1] = LE.leerInt("Ingrese el valor de alquiler");
            datosPosada[numHab][2] = 2;
            datosPosada[numHab][3] = 0;
            numHab++;

            rpta = LE.mostrarPreguntaOpcion2("�Desea continuar?");
        } while (rpta == 0);
    }

    public void visualizarDatos() {
        String texto = "", estado = "";
        for (int i = 0; i < numHab;
                i++) {
            if (datosPosada[i][2] == 1) {
                estado = "ocupada";
            } else {
                estado = "desocupada";
            }

            texto += "La habitacion " + datosPosada[i][0] + " cuesta " + datosPosada[i][1] + "soles\n" + "Se encuentra" + estado + "y ha sido alquilada" + datosPosada[i][3] + "veces\n";
        }

        LE.mostrarInformacion(texto);
    }

    public void indicarEstado(int p) {
        if (datosPosada[p][2] == 1) {
            LE.mostrarAdvertencia("La habitaci�n est� ocupada");
        } else {
            LE.mostrarAdvertencia("La habitaci�n est� desocupada");
        }

    }

    public void alquilar(int p) {
        if (datosPosada[p][2] == 2) {
            if (LE.mostrarPreguntaOpcion2("La habitaci�n" + datosPosada[p][0] + "cuesta" +
                    datosPosada[p][1] + "soles" + "\nDeses alquilarla?") == 0) {
                datosPosada[p][2] = 1;
                datosPosada[p][3]++;
                LE.mostrarAdvertencia("Alquiler exitoso,gracias");
            }

        } else {
            LE.mostrarError("La habitaci�n est� en uso");
        }

    }

    public void desocupar(int p) {
        if (datosPosada[p][2] == 1) {
            datosPosada[p][2] = 2;
            LE.mostrarAdvertencia("La habitaci� ha sido desocupada");
        } else {
            LE.mostrarError("La habitaci�n estaba desocupada");
        }

    }

    public void calcularGanancia() {
        String texto = "Ganancia por concepto de alquiler de habitaciones\n...\n";
        double total = 0;
        for (int i = 0; i < numHab; i++) {

            texto += "La habitaci�n" + datosPosada[i][0] + "ha tenido una ganancia de" + datosPosada[i][1] * datosPosada[i][3] + "soles\n";
            total =
                    total + datosPosada[i][1] * datosPosada[i][3];
        }

        texto = texto + "...\nLa ganancia total es:" + total;
        LE.mostrarInformacion(texto);
    }

    public void ordenar() {
        int temp, temp1, temp2, temp3;
        for (int i = 0; i < numHab - 1; i++) {
            for (int j = i + 1; j < numHab;
                    j++) {
                if (datosPosada[i][0] >= datosPosada[j][0]) {

                    temp = datosPosada[i][0];
                    datosPosada[i][0] = datosPosada[j][0];
                    datosPosada[j][0] = temp;


                    temp1 =
                            datosPosada[i][1];
                    datosPosada[i][1] = datosPosada[j][1];
                    datosPosada[j][1] = temp1;

                    temp2 =
                            datosPosada[i][2];
                    datosPosada[i][2] = datosPosada[j][2];
                    datosPosada[j][2] = temp2;

                    temp3 =
                            datosPosada[i][3];
                    datosPosada[i][3] = datosPosada[j][3];
                    datosPosada[j][3] = temp3;

                }

            }
        }
    }

    public void modificar(int pos) {
        if (pos != 1) {
            int rpta = LE.mostrarPreguntaOpcion2("�Desea modificar el precio?");
            if (rpta == 0) {
                datosPosada[pos][1] = LE.leerInt("Ingrese el nuevo costo del alquiler");
            }
        } else {
            LE.mostrarError("Habitaci�n no encontrada");
        }
    }

    public int buscar(int dato) {
        int pos = -1;
        for (int i = 0; i < numHab; i++) {
            if (datosPosada[i][0] == dato) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public void eliminar(int pos) {
        if (pos != -1) {
            int rpta = LE.mostrarPreguntaOpcion2("�Desea eliminar los datos?");
            if (rpta == 0) {
                for (int i = pos; i < numHab - 1; i++) {
                    datosPosada[i][0] = datosPosada[i + 1][0];
                    datosPosada[i][1] = datosPosada[i + 1][1];
                    datosPosada[i][2] = datosPosada[i + 1][2];
                    datosPosada[i][3] = datosPosada[i + 1][3];
                }
                numHab--;
            }
        } else {
            LE.mostrarError("Habitaci�n no encontrada");
        }

    }

    public void finalizar() {
        LE.mostrarInformacion("Usted ha finalizado el programa");
    }
}


















