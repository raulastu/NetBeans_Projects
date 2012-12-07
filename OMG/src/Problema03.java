
public class Problema03 {
    public static void main(String[]args){
        
        int nroSociosMayores =0;
        int nroMujeresMenores=0;
        int nroSociosComienzaConR=0;
        for(;;){
            System.out.println("Ingrese nombre de socio: ");
            String nombreSocio=Math2.leerCadena();
            System.out.println("Ingrese sexo de socio: ");
            String sexo=Math2.leerCadena();
            System.out.println("Ingrese edad de socio: ");
            int edad = Math2.leerNumeroEntero();            
            if(sexo.toUpperCase().equals("M")){
                if(edad>=18){
                    nroSociosMayores++;
                }
                if(nombreSocio.charAt(1)=='R'){
                    nroSociosComienzaConR++;
                }
            }else if(sexo.equals("F")){
                if(edad<18){
                    nroMujeresMenores++;
                }
            }
            System.out.println("Desea continuar? (Y/N)");
            String rpta=Math2.leerCadena();
            if(rpta.equals("N")){
                break;
            }
        }
        System.out.println("nroSociosMayores : "+ nroSociosMayores);
        System.out.println("nroMujeresMenores : "+ nroMujeresMenores);
        System.out.println("nroSociosComienzaConR : "+ nroSociosComienzaConR);
    }
}
