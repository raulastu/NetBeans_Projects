import java.lang.annotation.*;
import java.lang.reflect.Method;

public class Main {
    
    public void printAnnotations(){
        Method[] myMethods=new MyGooClass().getClass().getMethods();
        
        
        for(int i=0;i<myMethods.length;i++){
            Method m=myMethods[i];            
            Annotation anot[]=m.getAnnotations();            
            if(anot.length!=0){
                System.out.println("the method " + m.getName() + " has " + anot.length + " annotation(s) :");                
                for(Annotation n:anot){
                    System.out.println(n + " type " + n.annotationType().getName());                    
                    
                }
            }             
        }
        
    }
    public static void main(String[] args) {
        Main c=new Main();
        c.printAnnotations();
        
    }
    
}
