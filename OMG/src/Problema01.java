
public class Problema01 {
    public static void main(String[]args)   throws Exception{
        
        System.out.println("Ingrese Primer Nombre: ");
        String nombre1=Math2.leerCadena();
        System.out.println("Ingrese Segundo Nombre: ");
        String nombre2=Math2.leerCadena();
        System.out.println("Ingrese Apellido Paterno: ");
        String apellido1=Math2.leerCadena();
        System.out.println("Ingrese Apellido Materno: ");
        String apellido2=Math2.leerCadena();
        String nombreCompleto = apellido1+" "+apellido2+", "+ nombre1+" "+nombre2;
        System.out.println("a) nombre completo:  "+nombreCompleto.toUpperCase());
        
        String nombreAlrevez="";
        for(int i=nombreCompleto.length()-1;i>=0;i--){
            nombreAlrevez+=nombreCompleto.charAt(i)+"";
        }
        nombreAlrevez = nombreAlrevez.replaceAll(" ", "#");
        System.out.println("a) nombre al revez#:  "+nombreAlrevez);
        String nuevoNombre="";
        for(int i=0;i<nombreCompleto.length();i++){
            char letra=nombreCompleto.charAt(i);
            char replace=' ';
            switch(letra){
                case 'a':replace='x';break;
                case 'e':replace='x';break;
                case 'i':replace='y';break;
                case 'o':replace='x';break;
                case 'u':replace='y';break;
                case 'A':replace='x';break;
                case 'E':replace='x';break;
                case 'I':replace='y';break;
                case 'O':replace='x';break;
                case 'U':replace='y';break;
                default: replace=letra;
            }
            nuevoNombre+=" "+replace;
        }            
        System.out.println("c) vocales x,y spaciado:  "+nuevoNombre);
        System.out.println("d) cantidad d caracteres:  "+nombreCompleto.length());
    }
}
