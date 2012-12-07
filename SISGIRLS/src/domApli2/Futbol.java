package domApli2;

import biblioteca.*;

public class Futbol {

    int tabla[][], fechas;
    String equipos[];

    public Futbol() {
        equipos = new String[6];
        tabla = new int[6][6];
        equipos[0] = "ALIANZA";
        equipos[1] = "UNIVERSITA";
        equipos[2] = "CRISTAL";
        equipos[3] = "SAN MARTIN";
        equipos[4] = "JOSEFA";
        equipos[5] = "MYASS";
        fechas = 1;
    }

    public static void main(String[] args) {
        Futbol mundial = new Futbol();
        mundial.menu();
    }

    public void menu() {
        int menu, fechas = 1;
        String nombret;

        do {
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("------------");
            System.out.println("1.- Ingresar equipo");
            System.out.println("2.- Modificar Equipo");
            System.out.println("3.- Eliminar Equipo");
            System.out.println("4.- Ingresar resultados de la fecha");
            System.out.println("5.- Mostrar tabla");
            System.out.println("6.- Salir\n");
            do {
                System.out.println("Elección[1-6]");
                menu = LE.leerInt("Ingresar opcion");
                if (menu > 6 || menu < 1) {
                    System.out.println("Eleccion incorrecta vuelva a ingresar los datos\n");
                }
            } while (menu > 6 || menu < 1);
            switch (menu) {
                case 1:
                    ingresar();
                    break;
                case 2:
                    if (equipos.length == 0) {
                        System.out.println("No existen datos..");
                    } else {
                        modificar();
                    }
                    break;
                case 3:
                    if (equipos.length == 0) {
                        System.out.println("No existen datos...");
                    } else {
                        eliminar();
                    }
                    break;
                case 4:
                    if (equipos.length == 0) {
                        System.out.println("No existen datos...");
                    } else {
                        resultadosFecha();
                    }
                    break;
                case 5:
                    if (equipos.length == 0) {
                        System.out.println("No existen datos..");
                    } else {
                        mostrarTabla2();
                    }
                    break;
                case 6:
                    break;
            }
        } while (menu != 6);
    }

    public void ingresar() {
        String nombre, tmpEquipos[];
        int tmpDatos[][];
        System.out.println("Enter (intro) para finalizar el ingreso de nombres");
        do {
            nombre = LE.leerString("INgresa el nombre del equipos");
            nombre = nombre.trim();
            if (nombre.equals("")) {
                break;
            }
            tmpEquipos = new String[equipos.length + 1];
            tmpDatos = new int[equipos.length + 1][6];

            for (int i = 0; i < equipos.length; i++) {
                tmpEquipos[i] = equipos[i];
                tmpDatos[i] = tabla[i];
            }
            tmpEquipos[tmpEquipos.length - 1] = nombre;
            equipos = tmpEquipos;
            tabla = tmpDatos;
        } while (!nombre.equals(""));
    }

    public void modificar() {
        String nombre;
        int numEquipo;
        System.out.println("\n\nModificar nombre de equipo");
        System.out.println("-----------");
        for (int i = 0; i < equipos.length; i++) {
            System.out.println((i + 1) + ".-" + equipos[i]);
        }
        System.out.println("0.- Salir sin modificar");
        do {
            numEquipo = LE.leerInt("Elige un equipo");
        } while (numEquipo < 0 || numEquipo > equipos.length);

        if (numEquipo != 0) {
            numEquipo--;
            System.out.println("Antiguo nombre =" + equipos[numEquipo]);
            do {
                nombre = LE.leerString("Nuevo Nombre");
            } while (nombre.equals(""));
            equipos[numEquipo] = nombre;
        }
    }

    public void eliminar() {
        String tmpEquipos[];
        int tmpDatos[][], numEquipo, aux;
        char opc;
        System.out.println("\n\nEliminar un equipo");
        System.out.println("-------------");
        for (int i = 0; i < equipos.length; i++) {
            System.out.println((i + 1) + ".-" + equipos[i]);
        }
        System.out.println("0.- Salir sin Eliminar");
        do {
            numEquipo = LE.leerInt("Elige un equipo");
        } while (numEquipo < 0 || numEquipo > equipos.length);

        if (numEquipo != 0) {
            numEquipo--;
            opc = LE.leerChar("Seguro que desea eliminar el equipo" +
                    equipos[numEquipo] + "(s/n)=");
            opc = Character.toLowerCase(opc);
            if (opc == 's') {
                tmpEquipos = new String[equipos.length - 1];
                tmpDatos = new int[equipos.length - 1][6];
                aux = 0;
                for (int i = 0; i < equipos.length; i++) {
                    if (i != numEquipo) {
                        tmpEquipos[aux] = equipos[i];
                        tmpDatos[aux++] = tabla[i];
                    }
//                    equipos = tmpEquipos;        ERROR POR PONERLO AQUI
//                    tabla = tmpDatos;
                }
                equipos = tmpEquipos;
                tabla = tmpDatos;
            }
        }
    }

    public void resultadosFecha() {
        if (equipos.length % 2 == 0) {
            boolean reg[] = new boolean[equipos.length];
            // a = EQUIPO LOCAL b= EQUIPO VISITANTE;
            // ga = Goles equipo local gb = Goles EquipoVisitante.
            int a, b, ga, gb;
            int aux;
            System.out.println("\n\nResultados de la fehca" + fechas);
            System.out.println("----------------");
            aux = 0;
            do {
                System.out.println("\nPartido" + ++aux);
                for (int i = 0; i < equipos.length; i++) {
                    if (!reg[i]) {
                        System.out.println((i + 1) + ".-" + equipos[i]);
                    }
                }
                do {
                    a = LE.leerInt("EQUIPO LOCAL");
                } while (a > equipos.length || a < 1 || reg[a - 1]);
                reg[a - 1] = true;

                do {
                    b = LE.leerInt("EQUIPO VISITANTE");
                } while (b > equipos.length || b < 1 || reg[b - 1]);
                reg[b - 1] = true;

                ga = LE.leerInt("Goles que metió" + equipos[a - 1]);
                gb = LE.leerInt("Goles que metió" + equipos[b - 1]);

                if (ga > gb) {
                    tabla[a - 1][1]++;
                    tabla[a - 1][5] += 3;
                }
                if (gb > ga) {
                    tabla[b - 1][1]++;
                    tabla[b - 1][5] += 3;
                }
                if (ga == gb) {
                    tabla[a - 1][1]++;
                    tabla[a - 1][5]++;
                    tabla[b - 1][1]++;
                    tabla[b - 1][5]++;
                }
                tabla[a - 1][0]++;
                tabla[a - 1][3] += ga;
                tabla[a - 1][4] += gb;

                tabla[b - 1][0]++;
                tabla[b - 1][3] += gb;
                tabla[b - 1][4] += ga;
            } while (aux < (equipos.length / 2));
            fechas++;
        } else {
            System.out.println("Ingrese un numero par de equipos");
        }
    }

    public void mostrarTabla() {
        int aux;
        boolean cambio = false;
        String tmpEquipo;
        int tmpDatos[] = new int[6];

        do {
            aux = LE.leerInt("\n\nIndique Ordenar (1=nombre, 2=puntos)");
        } while (aux < 1 || aux > 2);
        for (int i = 0; i < equipos.length; i++) {
            for (int j = equipos.length - 1; j > i; j--) {
                switch (aux) {
                    case 1:
                        if (equipos[i].compareTo(equipos[j]) > 0) {
                            cambio = true;
                        }
                        break;
                    case 2:
                        if (tabla[i][5] < tabla[j][5]) {
                            cambio = true;
                        }
                        break;
                }
                if (cambio) {
                    tmpEquipo = equipos[j];
                    equipos[j] = equipos[i];
                    equipos[i] = tmpEquipo;

                    tmpDatos = tabla[j];  //// PORKE UN ARRAY BIDI ?!??!?! ERRORRRRRRRRRRRRRR
                    tabla[j] = tabla[i];
                    tabla[i] = tmpDatos;

                    cambio = false;
                }
            }
        }
        int tamaño = nombreLongitudMayor();
        String espacios = espaciosEnBlanco(30);
        String nombreEquipo;
        System.out.println("\n\nTABLA DE POSICIONES");
        System.out.println("----------------");
        String texto = ("Equipo" + espacios).substring(0, tamaño + 2);
        System.out.println(texto + "\tPJ\tPG\tPE\tGF\tGC\tP");
        for (int i = 0; i < equipos.length; i++) {
            nombreEquipo = (equipos[i] + espacios).substring(0, tamaño + 2);
            System.out.println(nombreEquipo + "\t" + tabla[i][0] + "\t" +
                    tabla[i][1] + "\t" + tabla[i][2] + "\t" +
                    tabla[i][3] + "\t" + tabla[i][4] + "\t" +
                    tabla[i][5]);
        }
    }

    public void mostrarTabla2() {
        int aux;
        do {
            aux = LE.leerInt("\n\nIndique Ordenar (1=nombre, 2=puntos)");
        } while (aux < 1 || aux > 2);
        for (int i = 0; i < equipos.length; i++) {
            for (int j = equipos.length - 1; j > i; j--) {
                switch (aux) {
                    case 1:
                        if (equipos[i].compareTo(equipos[j]) > 0) {
                            cambiar(i, j);
                        }
                        break;
                    case 2:
                        if (tabla[i][5] < tabla[j][5]) {
                            cambiar(i, j);
                        }
                        break;
                }
            }
        }
        int tamaño = nombreLongitudMayor();
        String espacios = espaciosEnBlanco(30);
        String nombreEquipo;
        System.out.println("\n\nTABLA DE POSICIONES");
        System.out.println("----------------");
        String texto = ("Equipo" + espacios).substring(0, tamaño + 2);
        System.out.println(texto + "\tPJ\tPG\tPE\tGF\tGC\tP");
        for (int i = 0; i < equipos.length; i++) {
            nombreEquipo = (equipos[i] + espacios).substring(0, tamaño + 2);
            System.out.println(nombreEquipo + "\t" + tabla[i][0] + "\t" +
                    tabla[i][1] + "\t" + tabla[i][2] + "\t" +
                    tabla[i][3] + "\t" + tabla[i][4] + "\t" +
                    tabla[i][5]);
        }
    }

    private int nombreLongitudMayor() {
        int tamaño = 0;
        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i].length() > tamaño) {
                tamaño = equipos[i].length();
            }
        }
        return tamaño;
    }

    private void cambiar(int i, int j) {
        String tempEquipo = equipos[j];
        equipos[j] = equipos[i];
        equipos[i] = tempEquipo;

        int[] dataTemp = tabla[j];
        tabla[j] = tabla[i];
        tabla[i] = dataTemp;
    }

    private int nombreLongitudMayor2() {
        int tamaño = 0;
        for (String equipo : equipos) {
            if (equipo.length() > tamaño) {
                tamaño = equipo.length();
            }
        }
        return tamaño;
    }

    private String espaciosEnBlanco(int total) {
        String espacios = "";
        for (int i = 0; i < total; i++) {
            espacios += " "; ////////// FAIL!!!!!! ERROR ""
        }
        return espacios;
    }
}
