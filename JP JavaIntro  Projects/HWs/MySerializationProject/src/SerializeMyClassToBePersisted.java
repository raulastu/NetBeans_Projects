import java.io.*;

public class SerializeMyClassToBePersisted {    
    
    public SerializeMyClassToBePersisted() {
    }
    
    public static void main(String[]args)throws IOException{
        MyClassToBePersisted a = new MyClassToBePersisted("Raúl",22,"Play Soccer","FBC",2000);               
        System.out.println(a);
        
        System.out.println("Serializing...");
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("storage.rc"));
        oos.writeObject(a);
    }
    
}
