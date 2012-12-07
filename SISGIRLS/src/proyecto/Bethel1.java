package proyecto;

import biblioteca.LE;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bethel1 {

    String[][] datos;
    String[] asistencia;
    String[] estado;
    int numMiembros;

    public Bethel1() {
        datos = new String[10000][5]; // COD, NOMBRES, FECHANAC, DIRECCION, FECHA DE BAUTIZO
        asistencia = new String[10000];
        // PRIMER MIEMBRO
        datos[0][0] = "123";
        datos[0][1] = "MILAGROS";
        datos[0][2] = "23 12 1995";  // fecha de nac.
        datos[0][3] = "PARAMONGA 329";
        datos[0][4] = "--";  // fecha de bautizo.
        asistencia[0] = "";
        // 2do MIEMBRO
        datos[1][0] = "122";
        datos[1][1] = "MERCEDES";
        datos[1][2] = "12 1 1999"; // fecha de nac.
        datos[1][3] = "PARAMONGA 23";
        datos[1][4] = "--"; // fecha de bautizo.
        asistencia[1] = "";
        // 3er MIEMBRO
        datos[2][0] = "121";
        datos[2][1] = "ERIKA";
        datos[2][2] = "22 12 1990";  // fecha de nac.
        datos[2][3] = "PARAMONGA 111";
        datos[2][4] = "--";  // fecha de bautizo
        asistencia[2] = "";
        numMiembros = 3;
    }

    public static void main(String[] args) {
        Bethel1 ob = new Bethel1();
        ob.menu();
    }

    public void menu() {
        String menu = "IGLESIA BETHEL PARAMONGA \n" +
                "-------------------------\n" +
                "[1] INGRESAR MIEMBRO\n" +
                "[2] ELIMINAR MIEMBRO\n" +
                "[3] MODIFICAR DATOS DE MIEMBRO\n" +
                "[4] CAMBIAR A ESTADO BAUTIZADO\n" +
                "[5] MARCAR ASISTENCIA\n" +
                "[6] VER ASISTENCIAS\n" +
                "[7] MOSTRAR MIEMBROS\n" +
                "[100] GUARDAR EN BASE DE DATOS\n" +
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
                    cambiarAEstadoBautizado();
                    break;
                case 5:
                    marcarAsistencia();
                    break;
                case 6:
                    mostrarMiembros();
                    break;
                case 100:
                    guardarEnBD();
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
        datos[numMiembros][4] = "--";
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

    public void cambiarAEstadoBautizado() {
        String codigo;
        int pos = 0;
        do {
            codigo = LE.leerString("Ingresar Codigo de Miembro para Cambiar su estado BAUTIZADO");
            pos = buscarCod(codigo);
            if (pos == -1) {
                LE.mostrarError("Codigo Inexistente, intente otra vez");
            }
        } while (pos == -1);
        String nombre = datos[pos][1];
        String fechaBautizo = LE.leerString("Ingrese Fecha de Bautizo para " + nombre + " (dd mm aaaa)");
        datos[pos][4] = fechaBautizo;

    }

    public void marcarAsistencia() {
        String codigo;
        int pos = 0;
        do {
            codigo = LE.leerString("Ingresar Codigo de Miembro a Marcar Asistencia");
            pos = buscarCod(codigo);
            if (pos == -1) {
                LE.mostrarError("Codigo Inexistente, intente otra vez");
            }
        } while (pos == -1);
        String nombre = datos[pos][1];
        String fechaAsistencia = LE.leerString("Agrege la fecha que asistió " + nombre + " (dd mm aaaa)");
        int posFecha = buscarSiExisteFechaAsistencia(pos, fechaAsistencia);
        if (posFecha == -1) { // posFecha==-1 quiere decir que no existe la fecha en las asistencias del miembro, entonces podemos agregarla
            asistencia[pos] = asistencia[pos] + "," + fechaAsistencia;
            LE.mostrarInformacion("Asistencia Registrada para " + nombre);
        } else {// si fecha!=-1 es que existe la fecha entonces no se agrega, y se termina
            LE.mostrarError("Ya se ha registrado esta asistencia para este miembro");
        }
    }

    /*
     *
     * METODO PARA BUSCAR UNA FECHA INGRESADA EN LA LISTA DE FECHAS DE UN MIEMBRO (pos)
     */
    public int buscarSiExisteFechaAsistencia(int pos, String fechaAsistenciaABuscar) {
        String[] asistencias = asistencia[pos].split(",");
        int posFecha = -1;
        if (asistencias.length != 0) {
            for (int i = 0; i < asistencias.length; i++) {
                if (fechaAsistenciaABuscar.equals(asistencias[i])) {
                    posFecha = i;
                    break;
                }
            }
        }
        return posFecha;
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
        int mesDeMiembro = Integer.parseInt(spl[1]);
        int año = Integer.parseInt(spl[2]);
        System.err.println();
        Date dat = new Date();
        int esteMes = dat.getMonth();
        int hoyDia = dat.getDay();
        int edad = (dat.getYear() + 1900) - año - 1; // dat.getYear() retorna los años transcurridos desde 1900 hasta la actualidad
        if (esteMes < mesDeMiembro) {
            edad++;
        } else if (esteMes == mesDeMiembro) {
            if (dia >= hoyDia) {
                edad++;
            }
        }
        return edad;
    }

    public void guardarEnBD() {
        try {
            Connection con = Conexion.loadConexion();
            Statement st = con.createStatement();
            st.execute("DELETE FROM miembros");
            st.close();
            for (int i = 0; i < numMiembros; i++) {
                Statement stIngresar = con.createStatement();
                String query = "INSERT INTO MIEMBROS(codigo, nombres, fecha_nac, direccion, fecha_bautizo, asistencia) VALUES " +
                        "('" + datos[i][0] + "','" + datos[i][1] + "','" + datos[i][2] + "','" + datos[i][3] + "','" + datos[i][4] + "','" + asistencia[i] + "');";
                stIngresar.execute(query);
                stIngresar.close();
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bethel1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
