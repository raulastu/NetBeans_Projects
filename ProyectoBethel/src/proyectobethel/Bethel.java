package proyectobethel;

import biblioteca.LE;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Bethel {

    String[][] datos;
    String[][] estado;
    int[] asistencia;
    int numMiembros;

    public Bethel() {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//        }
        datos = new String[10000][4]; // COD, NOMBRES, FECHANAC, DIRECCION
        estado = new String[10000][2];
        asistencia = new int[10000];
        // PRIMER MIEMBRO
        datos[0][0] = "123";
        datos[0][1] = "MILAGROS";
        datos[0][2] = "23 12 1995";  // fecha de nac.
        datos[0][3] = "PARAMONGA 329";
        estado[0][0] = "Bautizado";   // estado bautizado
        estado[0][1] = "23 12 2005";  // fecha de bautizo
        asistencia[0] = 0;
        // 2do MIEMBRO
        datos[1][0] = "122";
        datos[1][1] = "MERCEDES";
        datos[1][2] = "12 1 1999"; // fecha de nac.
        datos[1][3] = "PARAMONGA 23";
        estado[1][0] = "Visitante";
        estado[1][1] = "";
        asistencia[1] = 0;
        // 3er MIEMBRO
        datos[2][0] = "121";
        datos[2][1] = "ERIKA";
        datos[2][2] = "22 12 1990";  // fecha de nac.
        datos[2][3] = "PARAMONGA 111";
        estado[2][0] = "Visitante";
        estado[2][1] = "";
        asistencia[2] = 0;
        numMiembros = 3;
        cargarDeBD();
    }

    public static void main(String[] args) {
        JDialog.setDefaultLookAndFeelDecorated(true);
        Bethel ob = new Bethel();
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
                "----------MOSTRAR-------------\n" +
                "[10] MOSTRAR TODOS LOS MIEMBROS \n" +
                "[11] MOSTRAR MIEMBROS BAUTIZADOS\n" +
                "[12] MOSTRAR MIEMBROS (en tabla)\n" +
                "-------CARGAR Y GUARDAR------- \n" +
                "[99] CARGAR DE LA BASE DE DATOS\n" +
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
                case 10:
                    mostratTodosLosMiembros();
                    break;
                case 11:
                    mostrarMiembrosBautizados();
                    break;
                case 12:
                    mostrarMiembrosEnTabla();
                    opc = 0;
                    break;
                case 99:
                    cargarDeBD();
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
        estado[numMiembros][0] = "Visitante";
        estado[numMiembros][1] = "";
        asistencia[numMiembros] = 0;
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
        String fechaNacimiento = LE.leerString("Ingrese Nueva Fecha de Nacimiento (Fecha Anterior:" + datos[pos][2] + ")");
        String direccion = LE.leerString("Ingrese dirección (Anterior dirección:" + datos[pos][3] + ")");
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
        estado[pos][0] = "Bautizado";
        estado[pos][1] = fechaBautizo;
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
        asistencia[pos] = asistencia[pos] + 1;
        LE.mostrarInformacion("Asistencia Registrada para " + nombre);
    }

    public void mostratTodosLosMiembros() {
        String miembros =
                "Todos los Miembros de la Iglesia Bethel - Paramonga\n" +
                "codigo / nombres / fecha de nacimiento / direccion / edad / estado / fecha de bautizo / asistencias \n" +
                "-------------------------------------------\n";
        for (int i = 0; i < numMiembros; i++) {
            miembros += datos[i][0] + " / " + datos[i][1] + " / " + datos[i][2] + " / " + datos[i][3] + " / edad=" + obtenerEdad(datos[i][2]) + " / " +
                    estado[i][0] + " / " + estado[i][1] + " / " + asistencia[i] + "\n";
        }
        LE.mostrarInformacion(miembros);
    }

    public void mostrarMiembrosBautizados() {
        String miembros =
                "Miembros BAUTIZADOS de la Iglesia Bethel - Paramonga\n" +
                "-------------------------------------------\n";
        for (int i = 0; i < numMiembros; i++) {
            if (estado[i][0].equals("Bautizado")) {
                miembros += datos[i][0] + " / " + datos[i][1] + " / " + datos[i][2] + " / " + datos[i][3] + " / edad=" + obtenerEdad(datos[i][2]) + " / " +
                        estado[i][0] + " / " + estado[i][1] + " / " + asistencia[i] + "\n";
            }
        }
        LE.mostrarInformacion(miembros);
    }

    public void mostrarMiembrosEnTabla() {
        new FormularioMostrarMiembros().setVisible(true);
    }

    int obtenerEdad(String fechaNac) { // "21 12 1999"   -> {"21","12","1999"}
//        System.err.println(fechaNac);
        String spl[] = fechaNac.split(" ");  // separamos el string en un arreglo de strings separados por el espacio en blanco
        int dia = Integer.parseInt(spl[0]);
        int mesDeMiembro = Integer.parseInt(spl[1]);
        int año = Integer.parseInt(spl[2]);
        Date dat = new Date();
        dat.getYear(); // retorna el año actual - 1900
        int esteMes = dat.getMonth(); // entre 0 y 11
        int hoyDia = dat.getDay();
        int edad = dat.getYear() + 1900 - año - 1;
        System.err.println(hoyDia + " " + esteMes);
//        int edad = (dat.getYear() + 1900) - año - 1; // dat.getYear() retorna los años transcurridos desde 1900 hasta la actualidad
        if (esteMes > mesDeMiembro) {
            edad++;
        } else if (esteMes + 1 == mesDeMiembro) {
            if (dia <= hoyDia) { // SI SU DIA DE NAC. es MENOR O IGUAL QUE HOY, YA CUMPLIO
                edad++;
            }
        }
        return edad;
    }

    // GUARDA LOS DATOS DE LOS ARREGLOS EN LA BASE DE DATOS
    public void cargarDeBD() {
        try {
            Connection con = Conexion.obtenerConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM MIEMBROS");
            int contador = 0;
            while (rs.next()) {
                datos[contador][0] = rs.getString(1);
                datos[contador][1] = rs.getString(2);
                datos[contador][2] = rs.getString(3);
                datos[contador][3] = rs.getString(4);
                estado[contador][0] = rs.getString(5);
                estado[contador][1] = rs.getString(5);
                asistencia[contador] = rs.getInt(6);
                contador++;
            }
            numMiembros = contador;
            rs.close();
            st.close();
            con.close();
            LE.mostrarInformacion("Los Datos se Cargaron Satisfactoriamente");
        } catch (SQLException ex) {
            Logger.getLogger(Bethel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // CARGA DESDE LA BASE DE DATOS HACIA EL ARRAY (LOS LLENA)

    public void guardarEnBD() {
        try {
            Connection con = Conexion.obtenerConexion();
            Statement st = con.createStatement();
            st.execute("DELETE FROM miembros");
            st.close();
            for (int i = 0; i < numMiembros; i++) {
                Statement stIngresar = con.createStatement();
                String query = "INSERT INTO MIEMBROS(codigo, nombres, fecha_nac, direccion, estado, fecha_bautizo, asistencia) VALUES " +
                        "('" + datos[i][0] + "','" + datos[i][1] + "','" + datos[i][2] + "','" + datos[i][3] + "','" +
                        estado[i][0] + "','" + estado[i][1] + "','" + asistencia[i] + "');";
                stIngresar.execute(query);
                stIngresar.close();
            }
            con.close();
            LE.mostrarInformacion("Los Datos se guardaron Satisfactoriamente");
        } catch (SQLException ex) {
            Logger.getLogger(Bethel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
