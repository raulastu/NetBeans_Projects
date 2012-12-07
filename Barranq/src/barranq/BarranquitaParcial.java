package barranq;

import biblioteca.LE;

public class BarranquitaParcial {

    String datos[][];
    int[] ruta;
    double descuento[];
    int numPas;

    public BarranquitaParcial() {
        datos = new String[10][3];
        ruta = new int[10];
        descuento = new double[10];
        numPas = 0;
    }

    public static void main(String[] args) {
        BarranquitaParcial obBar = new BarranquitaParcial();
        obBar.menu();
    }

    public void menu() {
        String menu = "BARRANQUITA \n" +
                "-------------------\n" +
                "[1] Ingresar \n" +
                "[2] Mostrar \n" +
                "[3] Ganancia x Ruta\n" +
                "[4] Cantidad Pass x Ruta \n" +
                "[5] Ganacia Total \n" +
                "[6] Modificar Datos \n" +
                "[7] Eliminar Datos \n" +
                "[8] Ordenar Datos \n" +
                "[9] Buscar Datos" +
                "[0] Salir \n" +
                "";
        int opc;
        do {
            opc = LE.leerInt(menu);
            switch (opc) {
                case 1:
                    ingresar();
                    break;
                case 2:
                    ;
                    break;
                case 3:
                    ;
                    break;
                default:
                    break;
            }
        } while (opc != 0);
    }

    public void ingresar() {
        String documento;
        int poz;
        do {
            documento = LE.leerString("Ingrese el documento");
            poz = buscarDocumento(documento);
            if (poz != -1) {
                LE.mostrarError("Codigo ya Existe, Ingrese Otro");
            }
        } while (poz != -1);
        datos[numPas][0] = documento;
        datos[numPas][1] = LE.leerString("Ingrese Nombre");
        datos[numPas][2] = LE.leerString("Ingrese Direccion");
        String menu = "Ingrese RUTA\n" +
                "[1]Lima-Huacho S/.8\n" +
                "[2]LIma-Barranca 13 Leks\n" +
                "[3] Huacho - Barranca 4 \n" +
                "[4] Barranca - huacho 4 \n" +
                "[5]Barranca - Lima 8 \n" +
                "[6] Huacho - Lima 12\n";
        int opc2;
        double des = 0;
        do {
            opc2 = LE.leerInt(menu);
            switch (opc2) {
                case 1:
                    des = 5;
                    break;
                case 2:
                    des = 6;
                    break;
                case 3:
                    des = 1;
                    break;
                case 4:
                    des = 1;
                    break;
                case 5:
                    des = 5;
                    break;
                case 6:
                    des = 3;
                    break;
            }
        } while (opc2 < 1 || opc2 > 6);
        ruta[numPas] = opc2;
        descuento[numPas] = des;
        numPas++;
    }

    public int buscarDocumento(String doc) {
        int pos = -1;
        for (int i = 0; i < numPas; i++) {
            if (doc.equals(datos[i][0])) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public void mostrar() {
        String datosAMostrar = "BARRANKITA DATOS \n" +
                "------------------------\n" +
                "doc / binbre / direccion / ruta / descuento\n";
        for (int i = 0; i < numPas; i++) {
            datosAMostrar += datos[i][0] + " / " + datos[i][1] + " / " + datos[i][2] +
                    " / " + ruta[i] + " / " + descuento[i];
        }
        LE.mostrarInformacion(datosAMostrar);
    }

    public void gananciaXRuta() {
        String menu = "Ingrese RUTA\n" +
                "[1]Lima-Huacho S/.8\n" +
                "[2]LIma-Barranca 13 Leks desc 2\n" +
                "[3] Huacho - Barranca 4 \n" +
                "[4] Barranca - huacho 4 \n" +
                "[5]Barranca - Lima 8 \n" +
                "[6] Huacho - Lima 12\n" +
                "*------" +
                "INGRESE RUTA A MOSTRAR SU GANANCIA";
        int opc;
        double gananciaTotalXRuta = 0;
        do {
            opc = LE.leerInt(menu);
            switch (opc) {
                case 1:
                    for (int i = 0; i < numPas; i++) {
                        if (ruta[i] == 1) {
                            gananciaTotalXRuta += 8 - descuento[i];
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < numPas; i++) {
                        if (ruta[i] == 2) {
                            gananciaTotalXRuta += 13 - descuento[i];
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < numPas; i++) {
                        if (ruta[i] == 3) {
                            gananciaTotalXRuta += 4 - descuento[i];
                        }
                    }
                    break;
                case 4:
                    for (int i = 0; i < numPas; i++) {
                        if (ruta[i] == 4) {
                            gananciaTotalXRuta += 4 - descuento[i];
                        }
                    }
                case 5:
                    for (int i = 0; i < numPas; i++) {
                        if (ruta[i] == 5) {
                            gananciaTotalXRuta += 8 - descuento[i];
                        }
                    }
                    break;
                case 6:
                    for (int i = 0; i < numPas; i++) {
                        if (ruta[i] == 6) {
                            gananciaTotalXRuta += 12 - descuento[i];
                        }
                    }
                    break;
                default:
                    break;
            }
        } while (opc < 1 || opc > 6);
        LE.mostrarInformacion("GANANCIA DE RUTA " + opc + " = " + gananciaTotalXRuta);
    }

    public void aumentar() {
        String dat[][] = new String[datos.length + 5][3];
        int[] rut = new int[ruta.length + 5];
        double[] desc = new double[descuento.length + 5];
        for (int i = 0; i < numPas; i++) {
            dat[i] = datos[i];
            rut[i] = ruta[i];
            desc[i] = descuento[i];
        }
        datos = dat;
        ruta = rut;
        descuento = desc;
    }

    public void cantidadPasajerosXRuta() {
        String menu = "Ingrese RUTA\n" +
                "[1]Lima-Huacho S/.8\n" +
                "[2]LIma-Barranca 13 Leks desc 2\n" +
                "[3] Huacho - Barranca 4 \n" +
                "[4] Barranca - huacho 4 \n" +
                "[5]Barranca - Lima 8 \n" +
                "[6] Huacho - Lima 12\n" +
                "*------" +
                "INGRESE RUTA A MOSTRAR SU GANANCIA";
        int opc;
        double cantidad = 0;
        do {
            opc = LE.leerInt(menu);
            switch (opc) {
                case 1:
                    for (int i = 0; i < numPas; i++) {
                        if (ruta[i] == 1) {
                            cantidad++;
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < numPas; i++) {
                        if (ruta[i] == 2) {
                            cantidad++;
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < numPas; i++) {
                        if (ruta[i] == 3) {
                            cantidad++;
                        }
                    }
                    break;
                case 4:
                    for (int i = 0; i < numPas; i++) {
                        if (ruta[i] == 4) {
                            cantidad++;
                        }
                    }
                case 5:
                    for (int i = 0; i < numPas; i++) {
                        if (ruta[i] == 5) {
                            cantidad = cantidad + 1;
                        }
                    }
                    break;
                case 6:
                    for (int i = 0; i < numPas; i++) {
                        if (ruta[i] == 6) {
                            cantidad++;
                        }
                    }
                    break;
                default:
                    break;
            }
        } while (opc < 1 || opc > 6);
        LE.mostrarInformacion("CANTIDAD DE PASAJEROS DE LA RUTA " + opc + " = " + cantidad);
    }

    public void gananciaTotal() {
        double gananciaT = 0;
        for (int i = 0; i < numPas; i++) {
            switch (ruta[i]) {
                case 1:
                    gananciaT += 8 - descuento[i];
                    break;
                case 2:
                    gananciaT += 13 - descuento[i];
                    break;
                case 3:
                    gananciaT += 4 - descuento[i];
                    break;
                case 4:
                    gananciaT += 4 - descuento[i];
                    break;
                case 5:
                    gananciaT += 8 - descuento[i];
                    break;
                case 6:
                    gananciaT += 12 - descuento[i];
                    break;
            }
        }
        LE.mostrarInformacion("GANANCIA TOTAL =  " + gananciaT);
    }

    public void modificarDatos() {
        String documento;
        int poz;
        do {
            documento = LE.leerString("Ingrese el documento");
            poz = buscarDocumento(documento);
            if (poz == -1) {
                LE.mostrarError("Codigo no Existe!!, Intente otra vez, (NO PUEDO MODIFICAR ALGO KE NO EXISTE!!!)");
            }
        } while (poz == -1);

        datos[poz][1] = LE.leerString("Ingrese nuevo nombre");
        datos[poz][2] = LE.leerString("Ingrese nueva direccion");
        String menu = "Ingrese NUEVA RUTA\n" +
                "[1]Lima-Huacho S/.8\n" +
                "[2]LIma-Barranca 13 Leks\n" +
                "[3] Huacho - Barranca 4 \n" +
                "[4] Barranca - huacho 4 \n" +
                "[5]Barranca - Lima 8 \n" +
                "[6] Huacho - Lima 12\n";
        int opc2;
        double des = 0;
        do {
            opc2 = LE.leerInt(menu);
            switch (opc2) {
                case 1:
                    des = 5;
                    break;
                case 2:
                    des = 6;
                    break;
                case 3:
                    des = 1;
                    break;
                case 4:
                    des = 1;
                    break;
                case 5:
                    des = 5;
                    break;
                case 6:
                    des = 3;
                    break;
            }
        } while (opc2 < 1 || opc2 > 6);
        ruta[poz] = opc2;
        descuento[poz] = des;
    }

    public void eliminar() {
        String documento;
        int poz;
        do {
            documento = LE.leerString("Ingrese el documento");
            poz = buscarDocumento(documento);
            if (poz == -1) {
                LE.mostrarError("Codigo no Existe!!, Intente otra vez, (NO PUEDO MODIFICAR ALGO KE NO EXISTE!!!)");
            }
        } while (poz == -1);

        for (int i = poz; i < numPas; i++) {
            datos[i] = datos[i + 1];
        }
        numPas--;
    }

    public void ordenar() {
        for (int i = 0; i < numPas; i++) {
            for (int j = i + 1; j < numPas; j++) {
                if (datos[i][1].compareToIgnoreCase(datos[j][1]) < 0) {
                    String[] temp = datos[i];
                    datos[i] = datos[j];
                    datos[j] = temp;

                    int rutaTemp = ruta[i];
                    ruta[i] = ruta[j];
                    ruta[j] = rutaTemp;

                    double descTemp = descuento[i];
                    descuento[i] = descuento[j];
                    descuento[j] = descTemp;
                }
            }
        }
    }
}
