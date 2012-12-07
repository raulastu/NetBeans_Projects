package domApli2;

public class PrgAlumnos {

    double notas[];
    String datos[][];
    int numAlu;

    public PrgAlumnos() {
        notas = new double[10];
        datos = new String[10][3];
        numAlu = 0;
    }

//    public static void main(String[] args) {
//        PrgAlumnos x = new PrgAlumnos();
//        x.menu();
//    }

    public void menu() {
        String menu = "==== OPCIONES DEL MENU ====\n" +
                "[1] Ingreso de datos \n" +
                "[2] Visualizar datos" +
                "[3]Ordenar datos segun nombres y apellidos \n" +
                "[4] Eliminar\n" +
                "[5] nota mas alta\n";
    }

    public void ordenar() {
        String temp1, temp2, temp3;
        double temp4;
        for (int i = 0; i < numAlu - 1; i++) {
            for (int j = i + 1; j < numAlu; j++) {
                if (datos[i][0].compareToIgnoreCase(datos[j][0]) > 0) {
                    temp1 = datos[i][0];
                    datos[i][0] = datos[j][0];
                    datos[j][0] = temp1;

                    temp2 = datos[i][1];
                    datos[i][1] = datos[j][1];
                    temp2 = datos[j][1];

                    temp3 = datos[i][2];
                    datos[i][2] = datos[j][2];
                    datos[j][2] = temp3;

                    temp4 = notas[i];
                    notas[i] = notas[j];
                    notas[j] = temp4;
                }
            }
        }
    }

    public void ordenar2() {
        String temp1, temp2, temp3;
        double temp4;
        for (int i = 0; i < numAlu - 1; i++) {
            for (int j = i + 1; j < numAlu; j++) {
                if (datos[i][0].compareToIgnoreCase(datos[j][0]) > 0) {
                    temp1 = datos[i][0];
                    datos[i][0] = datos[j][0];
                    datos[j][0] = temp1;

                    temp2 = datos[i][1];
                    datos[i][1] = datos[j][1];
                    temp2 = datos[j][1];

                    temp3 = datos[i][2];
                    datos[i][2] = datos[j][2];
                    datos[j][2] = temp3;

                    temp4 = notas[i];
                    notas[i] = notas[j];
                    notas[j] = temp4;
                }
            }
        }
    }

    public void intercambiar(int i, int j) {
        String temp1, temp2, temp3;
        double temp4;
        temp1 = datos[i][0];
        datos[i][0] = datos[j][0];
        datos[j][0] = temp1;

        temp2 = datos[i][1];
        datos[i][1] = datos[j][1];
        datos[j][1] = temp2;

        temp3 = datos[i][2];
        datos[i][2] = datos[j][2];
        datos[j][2] = temp3;

        temp4 = notas[i];
        notas[i] = notas[j];
        notas[j] = temp4;
    }

    public void eliminar(int pos) {
        for (int i = 0; i < numAlu - 1; i++) {
            datos[i] = datos[i + 1];
            notas[i] = notas[i + 1];
        }
    }

    public String completarConBlancosXDer(String cadena, int total) {
        int longitud = cadena.length();
        if (longitud < total) {
            int falta = total - longitud;
            for (int i = 0; i < falta; i++) {
                cadena += "";
            }
        }
        return cadena;
    }
    String charlas[];
    double asistentes[][];
    int numChar;

    public void aumentarTamañoArreglo() {
        String charla[] = new String[charlas.length + 10];
        double asis[][] = new double[charlas.length + 10][5];
        for (int i = 0; i < numChar; i++) {
            charla[i] = charlas[i];
            asis[i] = asistentes[i];
        }
        charlas = charla;
        asistentes = asis;
    }

    public int buscar(String c) {
        for (int i = 0; i < numChar; i++) {
            if (c.equalsIgnoreCase(charlas[i])) {
                return i;
            }
        }
        return -1;
    }

    public void modificarDatos(int pos) {
        if (numChar == charlas.length) {
            aumentarTamañoArreglo();
        }
    }

    public void eliminarDatos(int pos) {
        for (int i = pos; i < numChar; i++) {
            charlas[i] = charlas[i + 1];
            asistentes[i] = asistentes[i + 1];
        }
        numChar--;
    }
    int numMesa;
//    double datos[][];
    String clientes[];

    public void ordenarPorMesa() {
        String cliente, dato;
        int temp1, temp2;
        for (int i = 0; i < numMesa - 1; i++) {
            temp1 = Integer.parseInt(datos[i][0]);
            for (int j = i + 1; j < numMesa; j++) {
                temp2 = Integer.parseInt(datos[j][0]);
                if (temp1 > temp2) {
                    cliente = clientes[i];
                    clientes[i] = clientes[j];
                    clientes[j] = cliente;
                    for (int k = 0; k < 3; k++) {
                        dato = datos[i][k];
                        datos[i][k] = datos[j][k];
                        datos[j][k] = dato;
                    }
                }
            }
        }
    }

    public void ordenarPorNombre() {
        String cliente;
        String dato[];
        for (int i = 0; i < numMesa - 1; i++) {
            for (int j = i + 1; j < numMesa; j++) {
                if (clientes[i].compareToIgnoreCase(clientes[j]) > 0) {
                    cliente = clientes[i];
                    clientes[i] = clientes[j];
                    clientes[i] = cliente;
                    dato = datos[i];
                    datos[i] = datos[j];
                    datos[j] = dato;
                }
            }
        }
    }

    public int buscarMesa(String mesa) {
        for (int i = 0; i < numMesa; i++) {
            if (mesa.equalsIgnoreCase(datos[i][0])) {
                return i;
            }
        }
        return -1;
    }
    String[] nombres;
    int numEqu;

    public void agrandarTamañoArreglo() {
        String nom[] = new String[nombres.length + 10];
        String dat[][] = new String[nombres.length + 10][3];
        for (int i = 0; i < numEqu; i++) {
            nom[i] = nombres[i];
            for (int j = 0; j < 3; j++) {
                dat[i][j] = datos[i][j];
            }
        }
        nombres = nom;
        datos = dat;
    }

    public int buscar2(String nom) {
        for (int i = 0; i < numEqu; i++) {
            if (nom.equalsIgnoreCase(nombres[i])) {
                return i;
            }
        }
        return -1;
    }

    public void eliminar2(int pos) {
        for (int i = pos; i < numEqu; i++) {
            nombres[i] = nombres[i + 1];
            datos[i] = datos[i + 1];
        }
    }

    public static String context(String nom) {
        return (char)(nom.charAt(0)-'A') + nom.substring(1).toLowerCase();
    }
    public static void main(String[] args) {
        System.err.println(context("asdad"));
    }
}
