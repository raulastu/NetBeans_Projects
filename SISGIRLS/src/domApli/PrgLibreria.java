package domApli;

import biblioteca.LE;
import org.omg.CORBA.INTERNAL;

public class PrgLibreria {

    String nombres[];
    int cantidad[], numArt;
    double precios[][];

    public static void main(String[] args) {
        PrgLibreria x = new PrgLibreria();
        x.menu();
    }

    public PrgLibreria() {
        numArt = 0;
        nombres = new String[9999];
        cantidad = new int[9999];
        precios = new double[9999][2];

    }

    public void menu() {
        String menu = "Libreria Boligrafito S.A.\n" + "-----------------------------------\n" + "1)Ingreso de datos de art�culos\n" + "2)Mostrar datos de los art�culos\n" + "3)Calculo de la utilidad al vender todo\n" + "4)Ordenar por cantidad de art�culos\n" + "5)Finalizar\n" + "-----------------------------------\n" + "Ingrese una opcion:\n";
        int opc;
        boolean sw = false;
        do {
            opc = LE.leerInt(menu);
            switch (opc) {
                case 1:
                    ingresar();
                    sw = true;
                    break;
                case 2:
                    if (sw) {
                        mostrar();
                    } else {
                        LE.mostrarError("Ingrese primero la opci�n 1");
                        break;
                    }
                case 3:
                    if (sw) {
                        utilidadObtenida();
                    } else {
                        LE.mostrarError("Ingrese primero la opci�n 1");
                        break;
                    }

                case 4:
                    if (sw) {
                        ordenar();
                        mostrar();
                    } else {
                        LE.mostrarError("Ingrese primero la opci�n 1");
                        break;
                    }

                case 5:
                    finalizar();
                    break;

                default:
                    LE.mostrarError("Opci�n inv�lida");
            }
        } while (opc != 5);
    }

    public void aumentar() {
        String nNombres[] = new String[nombres.length + 5];
        int ncantidad[] = new int[cantidad.length + 5];
        double nprecios[][] = new double[precios.length + 5][2];
        for (int i = 0; i < numArt; i++) {
            nNombres[i] = nombres[i];
            ncantidad[i] = cantidad[i];
            nprecios[i][0] = precios[i][0];
            nprecios[i][1] = precios[i][1];
        }
    }

    public void ingresar() {
        int rpta;
        do {
            nombres[numArt] = LE.leerString("Ingrese el nombre del art�culo" + (numArt + 1));
            cantidad[numArt] = LE.leerInt("Ingrese la cantidad del art�culo" + (numArt + 1));
            // VALIDACION PARA QUE EL PRECIO DE COMPRA SEA MAYOR QUE EL PRECIOVENTA
            double precioVenta = 0;
            double precioCompra = 0;
            do {
                precioVenta = LE.leerDouble("Ingrese el precio de venta del articulo " + (numArt + 1));
                precioCompra = LE.leerDouble("Ingrese el precio de compra del articulo " + (numArt + 1));
                if (precioVenta < precioCompra) {
                    LE.mostrarError("El precio de compra debe ser menor al precio de venta, ingrese denuevo");
                }
            } while (precioVenta < precioCompra);
            // FIN VALIDACION
            precios[numArt][0] = precioVenta;
            precios[numArt][1] = precioCompra;
            numArt++;
            rpta = LE.mostrarPreguntaOpcion2("�Desea continuar?");
        } while (rpta == 0);
    }

    public void utilidadObtenida() {
        String datosVenta = "";
        double utilidad = 0;
        for (int i = 0; i < numArt; i++) {
            utilidad = (precios[i][0] - precios[i][1]) * cantidad[i];
            utilidad = Math.round(utilidad * 100) / 100.0;
            datosVenta += "\n" + "La utilidad al vender todo el stock del producto" + nombres[i] + "es de:" + utilidad;
        }
        LE.mostrarResultado("El resultado de la venta general es:" + datosVenta);
    }

    public void ordenar() {
        int temp1;
        String temp;
        double temp2, temp3;
        for (int i = 0; i < numArt - 1; i++) {
            for (int j = i + 1; j < numArt; j++) {
                if (cantidad[i] >= cantidad[j]) {

                    temp1 = cantidad[i];
                    cantidad[i] = cantidad[j];
                    cantidad[j] = temp1;

                    temp = nombres[i];
                    nombres[i] = nombres[j];
                    nombres[j] = temp;

                    temp2 = precios[i][0];
                    precios[i][0] = precios[j][0];
                    precios[j][0] = temp2;

                    temp3 = precios[i][1];
                    precios[i][1] = precios[j][1];
                    precios[j][1] = temp3;

                }
            }
        }
    }

    public void mostrar() {
        String datos = "Los datos de los art�culos son los siguientes:\n";
        datos += "nombre/cantidad/precioVenta/precioCompra\n\n";

        for (int i = 0; i < numArt; i++) {
            datos += (i + 1) + "" + nombres[i] + "/" + cantidad[i] + "/" + precios[i][0] + "/" + precios[i][1] + "\n";
        }

        LE.mostrarInformacion(datos + "\n");
    }

    public void finalizar() {
        LE.mostrarInformacion("Usted ha finalizado el programa");
    }
}
