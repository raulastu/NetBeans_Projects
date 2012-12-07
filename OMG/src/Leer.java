
import java.io.DataInputStream;
import java.io.FileInputStream;


public class Leer {
    public static void main(String[]args)   throws Exception{       
        DataInputStream entrada= new DataInputStream(new FileInputStream("c:/RC.txt"));
        //Leer los numeros del archivo y guardarlo en variables
        int a=entrada.readInt();
        int b=entrada.readInt();
        int c=entrada.readInt();entrada.readInt();
        String x=entrada.readUTF();
        //cerrar el flujo - siempre se tiene que cerrar el flujo despues de usarlo
        entrada.close();
        //Imprimir en consola los numeros leídos del archivo
        System.out.println("A: "+a);
        System.out.println("B: "+b);
        System.out.println("C: "+c);
        System.out.println("C: "+x);        
    }
}
