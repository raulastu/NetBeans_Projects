
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Problema02 {

    static class Alumno {

        String CODIGO;
        String nombre;
        String apellidos;
        String fechaNac;
        String sexo;
        int n1;
        int n2;
        int n3;
        int n4;
        double promedio;

        public Alumno(String CODIGO, String nombre, String apellidos, String fechaNac, String sexo, int n1, int n2, int n3, int n4, double promedio) {
            this.CODIGO = CODIGO;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.fechaNac = fechaNac;
            this.sexo = sexo;
            this.n1 = n1;
            this.n2 = n2;
            this.n3 = n3;
            this.n4 = n4;
            this.promedio = promedio;
        }

        
        
    }

    public static void main(String[] args) {
        System.out.println("Ingrese nro de alumnos a evaluar");
        int n = Math2.leerNumeroEntero();
        Alumno[] alumnos = new Alumno[n];
        for (int i = 0; i < alumnos.length; i++) {
            System.out.println("Ingrese Nombres de alumno " + i+": ");
            String nombres = Math2.leerCadena();
            System.out.println("Ingrese Apellidos de alumno " + i+": ");
            String apellidos = Math2.leerCadena();
            System.out.println("Ingrese fecha de nacimiento de alumno (dd/mm/yyyy) " + i+": ");
            String fechaNac = Math2.leerCadena();
            System.out.println("Ingrese Sexo de alumno (M/F) " + i+": ");
            String sexo = Math2.leerCadena();
            System.out.println("Ingrese nota 1 de alumno " + i+": ");
            int n1 = Math2.leerNumeroEntero();
            System.out.println("Ingrese nota 2 de alumno " + i+": ");
            int n2 = Math2.leerNumeroEntero();
            System.out.println("Ingrese nota 3 de alumno " + i+": ");
            int n3 = Math2.leerNumeroEntero();
            System.out.println("Ingrese nota 4 de alumno " + i+": ");
            int n4 = Math2.leerNumeroEntero();
            int edad = Calendar.getInstance().get(Calendar.YEAR) - new Date(fechaNac).getYear()-1900;
            String CODIGO = nombres.trim().substring(0, 6).toUpperCase() +
                    apellidos.trim().substring(apellidos.length() - 6, apellidos.length()).toUpperCase() +
                    edad +sexo+
                    "2008";
            double min=Math.min(Math.min(Math.min(n1, n2),n3),n4);
            double promedio3Notas = (n1+n2+n3+n4-min)/(double)3;
            alumnos[i] = new Alumno(CODIGO,nombres, apellidos, fechaNac, sexo,
                    n1,n2,n3,n4,promedio3Notas);
            System.out.println(CODIGO);
        }
        
        for(int i=0;i<alumnos.length;i++){
            BigDecimal decimal = new BigDecimal(alumnos[i].promedio);
            decimal = decimal.setScale(2,BigDecimal.ROUND_HALF_UP);
            System.out.println("alumno "+i+" codigo "+alumnos[i].CODIGO +" promedio3Notas: "+decimal);
        }
    }
}
