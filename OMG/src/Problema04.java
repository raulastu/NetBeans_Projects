
public class Problema04 {
    public static void main(String[]args){
        System.out.println("Ingrese cadena en minusculas");
        String cadena = Math2.leerCadena();
        for(int i=0;i<cadena.length();i++){
            System.out.println(cadena.substring(0, i).toUpperCase());
        }
        for(int i=0;i<cadena.length();i++){
            System.out.println(cadena.substring(0, cadena.length()-i).toUpperCase());
        }
    }
}
