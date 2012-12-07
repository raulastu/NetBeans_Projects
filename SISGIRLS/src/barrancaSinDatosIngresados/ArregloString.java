package barrancaSinDatosIngresados;

public class ArregloString {

    String[][] datos;
    int numPasajeros;

    public ArregloString() {
        datos = new String[6][2];
        datos[0][0] = "123";
        datos[0][1] = "Milagros";
        datos[1][0] = "122";
        datos[1][1] = "mercedes";
        datos[2][0] = "121";
        datos[2][1] = "Erika";
        datos[3][0] = "113";
        datos[3][1] = "Milagros2";
        datos[4][0] = "223";
        datos[4][1] = "mercedes2";
        datos[5][0] = "133";
        datos[5][1] = "Analisa";
        numPasajeros = 6;
    }

//    public String buscarQUeComienzenA(String pre) {
//        int pos = -1;
//        String nombre = "";
//        for (int i = 0; i < numPasajeros; i++) {
//            if (datos[i][1].startsWith(pre)) {
//                nombre = datos[i][1];
//                break;
//            }
//        }
//        return nombre;
//    }
    public String buscarQUeComienzenA() {
        String nombre = "";
        for (int i = 0; i < numPasajeros; i++) {
            if (datos[i][1].startsWith("A")) {
                nombre = datos[i][1];
                break;
            }
        }
        return nombre;
    }

    public int buscarCod(String codABuscar) {
        int pos = -1;
        for (int i = 0; i < numPasajeros; i++) {
            if (codABuscar.equals(datos[i][0])) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public static void main(String[] args) {
        ArregloString ob = new ArregloString();
        String nombre = ob.buscarQUeComienzenA();
        System.err.println("NOMBRE:"+nombre);
////        String str = "asjdklaj";
////        System.out.println(str.startsWith("a"));// startsWith COMIENZACON
//        //VERIFICAR SI EL STRING COMIENZA CON 'A'
//        String str2 = "asjdklaj";
//        if (str2.charAt(0) == 'a') {
//            System.err.println(true);
//        }
//        System.out.println(str2.charAt(0));// startsWith COMIENZACON
    }
}
