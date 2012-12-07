package domApli;

import biblioteca.LE;

public class Barranquita2 {

    String[][] datos;
    int[] ruta;
    int numPas;
    int contaMen;

    public Barranquita2() {
        datos = new String[10][2];
        ruta = new int[10];
        datos[0] = new String[]{"123", "niño"};
        datos[1] = new String[]{"124", "adulto"};
        datos[2] = new String[]{"125", "niñoasd"};
        datos[3] = new String[]{"126", "niño"};
        datos[4] = new String[]{"127", "adulto"};
        datos[5] = new String[]{"128", "niñoasd"};
        datos[6] = new String[]{"129", "niño"};
        datos[7] = new String[]{"130", "adulto"};
        datos[8] = new String[]{"131", "niñoasd"};
        datos[9] = new String[]{"132", "niño"};
        ruta[0] = 1;
        ruta[1] = 1;
        ruta[2] = 1;
        ruta[3] = 2;
        ruta[4] = 3;
        ruta[5] = 3;
        ruta[6] = 4;
        ruta[7] = 5;
        ruta[8] = 5;
        ruta[9] = 6;

        numPas = 10;
        contaMen = 1000;
    }

    public static void main(String[] args) {
        Barranquita2 bus = new Barranquita2();
        bus.menu();
    }

    public void menu() {
        String menu = "Barranquita SRL \n" +
                "---------------------------\n" +
                "[1] Ingreso de datos de los pasajeros\n" +
                "[2] Modificar datos de los pasajeros \n" +
                "[3] Eliminar datos de pasajeros \n" +
                "[4] Calcular datos de pasajeros \n" +
                "[5] Buscar datos de pasajeros \n" +
                "[6] Ver ganancia total por Ruta\n" +
                "[7] Ver pasajeros por Ruta\n" +
                "[8] Imprimir boleta\n" +
                "[10] Finalizar\n" +
                "------------------------\n" +
                "INGRESE UNA OPCION";
        int opc;
        do {
            opc = LE.leerInt(menu);
            switch (opc) {
                case 1:
                    ingresarDatos();
                    break;
                case 2:
                    modificar();
                case 3:
                    elminarDatos();
                    break;
                case 6:
                    gananciaTotalXRuta();
                    break;
                case 7:
                    pasajeroXRuta();
                    break;
                case 8:
                    imprimerBoleta();
                    break;
            }
        } while (opc != 10);
    }

    public void ingresarDatos() {
        int opc;
        do {
            if (ruta.length == numPas) {
                aumentar();
            }
            int rpta = LE.leerInt("Ingrese 1 si es mayor de edad , en caso contrario ingrese 0");
            if (rpta == 1) {
                do {
                    datos[numPas][0] = LE.leerString("Ingrse DNI del pasajero");
                    if (buscarDNI(datos[numPas][0])) {
                        LE.mostrarInformacion("DNI ingresado ya existe... verifique");
                    }
                } while (buscarDNI(datos[numPas][0]));
            } else {
                do {
                    datos[numPas][0] = LE.leerString("Ingrse partida de nacimiento  del menor");
                    if (buscarDNI(datos[numPas][0])) {
                        LE.mostrarInformacion("Documento ingresado ya existe... verifique");
                    }
                } while (buscarDNI(datos[numPas][0]));
            }
            datos[numPas][1] = LE.leerString("Ingrse nombre del pasajero");

            ruta[numPas] = LE.leerInt("Ingresa la ruta \n" +
                    "[1] LIMA-HUACHO S/10 \n" +
                    "[2] LIMA-BARRANCA S/15 \n" +
                    "[3] HUACHO-BARRANCA S/5 \n" +
                    "[4] BARRANCA-HUACHO S/5 \n" +
                    "[5] BARRANCA-LIMA S/15 \n" +
                    "[6] HUACHO-LIMA S/10 \n");
            opc = LE.mostrarPreguntaOpcion2("Deseas Continuar?\n");
        } while (opc == 0);
    }

    public boolean buscarDNI(String dni) {
        for (int i = 0; i < numPas; i++) {
            if (datos[i][0].equals(dni)) {
                return true;
            }
        }
        return false;
    }

    public void aumentar() {
        String dat[][] = new String[ruta.length + 5][2];
        int rut[] = new int[ruta.length + 5];
        for (int i = 0; i < numPas; i++) {
            dat[i][0] = datos[i][0];
            dat[i][1] = datos[i][1];
            rut[i] = ruta[i];
        }
        datos = dat;
        ruta = rut;
    }

    public void modificar() {
        int pos;
        int rpta = LE.leerInt("Ingrese 1 si es mayor de edad, caso contrario 0");
        if (rpta == 1) {
            String dni = LE.leerString("Ingrese el DNI");
            pos = buscarDNIPos(dni);
        } else {
            String nom = LE.leerString("Ingrese el nombre del menor de edad");
            pos = buscarNomPos(nom);
        }
        if (pos == -1) {
            LE.mostrarInformacion("DNI o nombre de menor no encontrado");
        } else {
            rpta = LE.mostrarPreguntaOpcion2(
                    "Deseas elimniar los datos del pasajero " + datos[pos][1] + "?");
            String nuevoNombre = LE.leerString("Ingrese el nuevo nombre ");
            datos[pos][1] = nuevoNombre;
        }
    }

    public void elminarDatos() {
        int pos;
        int rpta = LE.leerInt("Ingrese 1 si es mayor de edad, caso contrario 0");
        if (rpta == 1) {
            String dni = LE.leerString("Ingrese el DNI");
            pos = buscarDNIPos(dni);
        } else {
            String nom = LE.leerString("Ingrese el nombre del menor de edad");
            pos = buscarNomPos(nom);
        }
        if (pos == -1) {
            LE.mostrarInformacion("DNI o nombre de menor no encontrado");
        } else {
            rpta = LE.mostrarPreguntaOpcion2(
                    "Deseas elimniar los datos del pasajero " + datos[pos][1] + "?");
            if (rpta == 1) {
                for (int i = pos; i < numPas - 1; i++) {
                    datos[i][0] = datos[i + 1][0];
                    datos[i][1] = datos[i + 1][0];
                    ruta[i] = ruta[i + 1];
                }
                numPas--;
            }
        }
    }

    public int buscarDNIPos(String dni) {
        for (int i = 0; i < numPas; i++) {
            if (datos[i][0].equals(dni)) {
                return i;
            }
        }
        return -1;
    }

    public int buscarNomPos(String nombre) {
        for (int i = 0; i < numPas; i++) {
            if (datos[i][1].equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public void gananciaTotal() {
        double ganancia = 0;
        double gananciaTotal = 0;
        for (int i = 0; i < numPas; i++) {
            switch (ruta[i]) {
                case 1:
                case 6:
                    if (i < 10) {
                        ganancia = 10 * 0.9;
                    } else {
                        ganancia = 10;
                    }
                    break;
                case 2:
                case 5:
                    if (i < 10) {
                        ganancia = 15 * 0.9;
                    } else {
                        ganancia = 15;
                    }
                    break;
                case 3:
                case 4:
                    if (i < 10) {
                        ganancia = 5 * 0.9;
                    } else {
                        ganancia = 5;
                    }
                    break;
            }
            gananciaTotal = gananciaTotal + ganancia;
        }
        LE.mostrarInformacion("la ganancia total es: " + gananciaTotal);
    }

    public void gananciaTotalXRuta() {
        int ganancia = 0;
        long gananciaRuta1 = 0, gananciaRuta2 = 0, gananciaRuta3 = 0;
        long gananciaRuta4 = 0, gananciaRuta5 = 0, gananciaRuta6 = 0;
        long[] precios = {10, 15, 5, 5, 15, 10};
        long[] ganancias = new long[6];
        for (int i = 0; i < numPas; i++) {
            if (i < 10) {
                ganancias[ruta[i] - 1] += (int) (precios[ruta[i] - 1] * 0.9);
            } else {
                ganancias[ruta[i] - 1] += precios[ruta[i] - 1];
            }
        }
        String msg = "La ganancia por ruta es :\n";
        for (int i = 0; i < ganancias.length; i++) {
            msg += "ruta " + (i + 1) + " : " + ganancias[i] + "\n";
        }
        LE.mostrarInformacion(msg);
    }

    public void pasajeroXRuta() {
        int pasRuta1 = 0, pasRuta2 = 0, pasRuta3 = 0, pasRuta4 = 0, pasRuta5 = 0, pasRuta6 = 0;
        for (int i = 0; i < numPas; i++) {
            switch (ruta[i]) {
                case 1:
                    pasRuta1++;
                    break;
                case 2:
                    pasRuta2++;
                    break;
                case 3:
                    pasRuta3++;
                    break;
                case 4:
                    pasRuta4++;
                    break;
                case 5:
                    pasRuta5++;
                    break;
                case 6:
                    pasRuta6++;
                    break;
            }
        }
        LE.mostrarInformacion(" El total de pasajeros por ruta es:\n" +
                "Ruta 1:" + pasRuta1 + "\n" +
                "Ruta 2:" + pasRuta2 + "\n" +
                "Ruta 3:" + pasRuta3 + "\n" +
                "Ruta 4:" + pasRuta4 + "\n" +
                "Ruta 5:" + pasRuta5 + "\n" +
                "Ruta 6:" + pasRuta6 + "\n" +
                "-------------------------" +
                "EL Total de pasajeros es " + numPas);
    }

    public void imprimerBoleta() {
        String cod = LE.leerString("Ingresar doc de pasajero");
        int pos = buscarDNIPos(cod);
        String boleta = "EMPRESA DE TRANSPORTES BARRANQUITA\n" +
                "--------------------------------------------\n";
        if (pos != -1) {
            boleta += "Nombre de pasajero: " + datos[pos][1] + " \n" +
                    "DNI : " + datos[pos][0] + "\n";
            switch (ruta[pos]) {
                case 1:
                    boleta += "ORIGEN: LIMA DESTINO: HUACHO \n" +
                            "COSTO PASAJE: S/" + 10 + "\n" +
                            "DESCUENTO : S/" + descuento(pos, 10) + "\n";
                    break;
                case 2:
                    boleta = boleta + "ORIGEN: LIMA DESTINO: BARRANCA\n" +
                            "COSTO PASAJE: S/" + 15 + "\n" +
                            "DESCUENTO : S/" + descuento(pos, 15) + "\n";
                    break;
                case 3:
                    boleta = boleta + "ORIGEN: HUACHO DESTINO: BARRANCA \n" +
                            "COSTO PASAJE: S/" + 5 + "\n" +
                            "DESCUENTO : S/" + descuento(pos, 5) + "\n";
                    break;
                case 4:
                    boleta = boleta + "ORIGEN: BARRANCA DESTINO: HUACHO \n" +
                            "COSTO PASAJE: S/" + 5 + "\n" +
                            "DESCUENTO : S/" + descuento(pos, 5) + "\n";
                    break;
                case 5:
                    boleta = boleta + "ORIGEN: BARRANCA DESTINO: LIMA \n" +
                            "COSTO PASAJE: S/" + 15 + "\n" +
                            "DESCUENTO : S/" + descuento(pos, 15) + "\n";
                    break;
                case 6:
                    boleta = boleta + "ORIGEN: HUACHO DESTINO: LIMA \n" +
                            "COSTO PASAJE: " + 10 + "\n" +
                            "DESCUENTO : S/" + descuento(pos, 10) + "\n";
                    break;
            }
            boleta += "---------------------------------------------";
            LE.mostrarInformacion(boleta);
        } else {
            LE.mostrarError("No existe documento");
        }
    }

    public int descuento(int nroPasajero, int monto) {
        if (nroPasajero < 10) {
            return (int) (monto * 0.1);
        }
        return 0;
    }
}
