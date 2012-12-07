package proyecto;

import biblioteca.LE;
import java.util.Calendar;
import java.util.Date;

public class Bethel {

    String[][] datos;
    int numMiembros;

    public Bethel() {
        datos = new String[10000][4]; // COD, NOMBRES, FECHANAC, DIRECCION
        // PRIMER MIEMBRO
        datos[0][0] = "123";
        datos[0][1] = "MILAGROS";
        datos[0][2] = "23 12 2000";
        datos[0][3] = "PARAM";
        // 2do MIEMBRO
        datos[1][0] = "122";
        datos[1][1] = "MERCEDES";
        datos[1][2] = "12 1 2000";
        datos[1][3] = "PARAM2";
        // 3er MIEMBRO
        datos[2][0] = "121";
        datos[2][1] = "ERIKA";
        datos[2][2] = "22 12 1999";
        datos[2][3] = "PARAM2";
        numMiembros = 3;
    }

    public static void main(String[] args) {
        Bethel ob = new Bethel();
        ob.menu();
    }

    public void menu() {
        String menu = "IGLESIA BETHEL PARAMONGA \n" +
                "-------------------------\n" +
                "[1] INGRESAR MIEMBRO\n" +
                "[2] ELIMINAR MIEMBRO\n" +
                "[3] MODIFICAR DATOS DE MIEMBRO\n" +
                "[4] MOSTRAR MIEMBROS\n" +
                "[0] Salir";
        int opc = -1;
        do {
            opc = LE.leerInt(menu);
            switch (opc) {
                case 1:
                    ingresarMiembro();
                    break;
                case 2:
                    eliminarMiembro();
                    break;
                case 3:
                    modificar();
                    break;
                case 4:
                    mostrarMiembros();
                    break;
                default:
                    break;
            }
        } while (opc != 0);
    }

    public void ingresarMiembro() {
        String codigo;
        do {//VERIFICAR SI EXISTE EL CODIGO, SI EXISTE NO SE PUEDE INGRESAR
            codigo = LE.leerString("Ingresar Codigo");
            if (buscarCod(codigo) != -1) {
                LE.mostrarError("Codigo Existente, pruebe otra vez");
            }
        } while (buscarCod(codigo) != -1);

        String nombres = LE.leerString("Ingrese Nombres del Nuevo Miembro");
        String fechaNacimiento = LE.leerString("Ingrese Fecha de Nacimiento (dd mm aaaa)");
        String direccion = LE.leerString("Ingrese dirección");
        datos[numMiembros][0] = codigo;
        datos[numMiembros][1] = nombres;
        datos[numMiembros][2] = fechaNacimiento;
        datos[numMiembros][3] = direccion;
        numMiembros++;
    }

    private int buscarCod(String codABuscar) { // METODO BUSCAR
        int pos = -1;
        for (int i = 0; i < numMiembros; i++) {
            if (datos[i][0].equals(codABuscar)) { // CODACTUAL.equal(CODABUSCAR)
                pos = i;
                break;
            }
        }
        return pos;
    }

    public void eliminarMiembro() {
        String codigo;
        int pos = 0;
        do {
            codigo = LE.leerString("Ingresar Codigo de Miembro a Eliminar");
            pos = buscarCod(codigo);
            if (pos == -1) {
                LE.mostrarError("Codigo Inexistente, intente otra vez");
            }
        } while (pos == -1);

        for (int i = pos; i < numMiembros; i++) {
            datos[i] = datos[i + 1];
        }

        numMiembros--;
        LE.mostrarInformacion("Eliminado Correctamente");
    }

    public void modificar() {
        String codigo;
        int pos = 0;
        do {
            codigo = LE.leerString("Ingresar Codigo de Miembro a Modificar");
            pos = buscarCod(codigo);
            if (pos == -1) {
                LE.mostrarError("Codigo Inexistente, intente otra vez");
            }
        } while (pos == -1);

        String nombres = LE.leerString("Ingrese Nuevo Nombres del Nuevo Miembro (Anterior:" + datos[pos][1] + ")");
        String fechaNacimiento = LE.leerString("Ingrese Fecha de Nacimiento (Fecha Anterior:" + datos[pos][2] + ")");
        String direccion = LE.leerString("Ingrese dirección (Anterior dirección:" + datos[pos][3] + ")");
        datos[pos][0] = codigo;
        datos[pos][1] = nombres;
        datos[pos][2] = fechaNacimiento;
        datos[pos][3] = direccion;
        LE.mostrarInformacion("Modificado Correctamente");
    }

    public void mostrarMiembros() {
        String miembros =
                "Miembros de la Iglesia Bethel - Paramonga\n" +
                "-------------------------------------------\n";
        for (int i = 0; i < numMiembros; i++) {
            miembros += datos[i][0] + " / " + datos[i][1] + " / " + datos[i][2] + " / " + datos[i][3] + " / edad=" + obtenerEdad(datos[i][2]) + "\n";
        }
        LE.mostrarInformacion(miembros);
    }

    int obtenerEdad(String fechaNac) {
//        System.err.println(fechaNac);
        String spl[] = fechaNac.split(" ");  // separamos el string en un arreglo de strings separados por el espacio en blanco
        int dia = Integer.parseInt(spl[0]);
        int mes = Integer.parseInt(spl[1]);
        int año = Integer.parseInt(spl[2]);
        System.err.println();
        Date dat = new Date();
        int edad = (dat.getYear() + 1900) - año; // dat.getYear() retorna los años trans curridos desde 1900 hasta la actualidad
        return edad;
    }
}
