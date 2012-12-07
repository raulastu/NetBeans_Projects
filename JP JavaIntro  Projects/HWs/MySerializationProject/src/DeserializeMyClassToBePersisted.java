import java.io.*;
public class DeserializeMyClassToBePersisted {    
   
    public DeserializeMyClassToBePersisted() {
    }
    
    public static void main(String[]args)throws IOException,ClassNotFoundException{
        MyClassToBePersisted b;
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("storage.rc"));
        
        b=(MyClassToBePersisted)ois.readObject();
        System.out.println(b);
        
    }    
}
