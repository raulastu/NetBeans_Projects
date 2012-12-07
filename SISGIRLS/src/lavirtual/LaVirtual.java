package lavirtual;

import biblioteca.LE;

public class LaVirtual {

    String[] nombres;
    String[] areas;
    double[] sueldos;
    int numEmp;

    public LaVirtual() {
        nombres = new String[5];
        areas = new String[5];
        sueldos = new double[5];
        numEmp = 0;
    }

    public void menu() {
        String mensaje = "LA VIRTUAL =)\n" +
                "---------------------------------------\n" +
                "[1] Ingresar\n" +
                "[2] Modificar Datos\n" +
                "[3] Aumentar\n" +
                "[4] Mostrar Información\n" +
                "[5] SALIR";
        int opc = 0;
        do {
            opc = LE.leerInt(mensaje);
            switch (opc) {
                case 1:
                    ingresar();
                    break;
                case 2:
                    actualizar();
                    break;
                case 3:
                    aumentarSueldo();
                    break;
                case 4:
                    mostrar();
                    break;
            }

        } while (opc != 5);
    }

    public void ingresar() {

        nombres[numEmp] = LE.leerString("Ingrese el nombre del empleado");
        String opcionesDeArea = "SELECCIONE EL AREA AL QUE PERTENECE \n" + "[1]Aulas Virtuales\n" + "[2]Second Life\n" + "[3]Web Docente";
        int opc2 = LE.leerInt(opcionesDeArea);

        do {
            switch (opc2) {
                case 1:
                    areas[numEmp] = "Aulas Virtuales";
                    sueldos[numEmp] = 900;
                    break;
                case 2:
                    areas[numEmp] = "Second Life";
                    sueldos[numEmp] = 800;
                    break;
                case 3:
                    areas[numEmp] = "Web Docente";
                    sueldos[numEmp] = 850;
                    break;
                default:
                    LE.mostrarError("Elija una opcion valida");
            }
        } while (opc2 < 1 && 3 < opc2);
        numEmp++;
    }

    public void aumentar() {
        String[] nomb = new String[nombres.length + 5];
        String[] ar = new String[areas.length + 5];
        double[] sueld = new double[sueldos.length + 5];
        for (int i = 0; i < nomb.length; i++) {
            nomb[i] = nombres[i];
            ar[i] = areas[i];
            sueld[i] = sueldos[i];
        }
        nombres = nomb;
        areas = ar;
        sueldos = sueld;
    }

    public void actualizar() {
        String nomb = LE.leerString("Ingrese nombre a buscar");
        int pos = buscarPorNombre(nomb);
        if (pos == -1) {
            LE.mostrarError("No existe empleado con ese nombre");
        } else {
            String opcionesDeArea = "SELECCIONE LA NUEVA AREA AL QUE PERTENECE " +
                    nomb +
                    " (ANTERIOR AREA:" + areas[pos] + ") \n" +
                    "[1]Aulas Virtuales\n" +
                    "[2]Second Life\n" +
                    "[3]Web Docente";
            int opc2 = LE.leerInt(opcionesDeArea);
            do {
                switch (opc2) {
                    case 1:
                        areas[pos] = "Aulas Virtuales";
                        sueldos[pos] = 900;
                        break;
                    case 2:
                        areas[pos] = "Second Life";
                        sueldos[pos] = 800;
                        break;
                    case 3:
                        areas[pos] = "Web Docente";
                        sueldos[pos] = 850;
                        break;
                    default:
                        LE.mostrarError("Elija una opcion valida");
                }
            } while (opc2 < 1 && 3 < opc2);
        }
    }

    public int buscarPorNombre(String nombreABuscar) {
        int pos = -1;
        for (int i = 0; i < numEmp; i++) {
            if (nombres[i].equalsIgnoreCase(nombreABuscar)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public void mostrar() {
        String mensaje = "NOMBRES / AREA / SUELDO\n";
        for (int i = 0; i < numEmp; i++) {
            mensaje = mensaje + nombres[i] + "/" + areas[i] + "/" + sueldos[i] + "\n";
        }
        LE.mostrarInformacion(mensaje);
    }

    public void aumentarSueldo() {
        String msg = "SELECCIONE EL AREA PARA INCREMENTAR SU SUELDO \n" + "[1]Aulas Virtuales\n" + "[2]Second Life\n" + "[3]Web Docente";
        int area = LE.leerInt(msg);
        double porcentaje = LE.leerDouble("Ingrese el porcentaje a aumentar");
        double sueld = 0;
        switch (area) {
            case 1:
                for (int i = 0; i < numEmp; i++) {
                    if (areas[i].equals("Aulas Virtuales")) {
                        sueld = sueld + 900;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < numEmp; i++) {
                    if (areas[i].equals("Second Life")) {
                        sueld = sueld + 800;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < numEmp; i++) {
                    if (areas[i].equals("Web Docente")) {
                        sueld = sueld + 850;
                    }
                }
                break;
        }
        double s = sueld;
        String resultado = "Sueldos antes del aumento " + sueld + "\n";
        sueld = sueld + (porcentaje / 100) * sueld;
        resultado = resultado + "Sueldos despues del aumento " + sueld + "\n";
        resultado += "DIFERENCIA:" + (sueld - s) + "";
        LE.mostrarInformacion(resultado);

    }

    public static void main(String[] args) {
        LaVirtual ob = new LaVirtual();
        ob.menu();

    }
}
