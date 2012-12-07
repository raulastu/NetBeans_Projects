
import java.io.DataOutputStream;
import java.io.FileOutputStream;




public class Escribir {

    
    public static void main(String[] args)throws Exception {
        /*el DataOutputStream recibe por su 
         * constructor un OutputStream y este a su 
         * vez recibe la ruta del archivo a escribir
         * 
         */
         DataOutputStream salida = new DataOutputStream(new FileOutputStream("C:/RC.txt"));
         //Escribir enteros
         salida.writeInt(100);
         salida.writeInt(2008);
         salida.writeInt(30);
         salida.writeInt(4);
         salida.writeUTF("hola");
         //cerrar el flujo - siempre se tiene que cerrar el flujo despues de usarlo
         salida.close();
         
         System.out.println("Escrito correctamente");
    }

}
