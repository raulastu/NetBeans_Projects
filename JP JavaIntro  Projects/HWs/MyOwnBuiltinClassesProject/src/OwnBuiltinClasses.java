/*
 * OwnBuiltinClasses.java
 *
 * Created on 17 de octubre de 2007, 10:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Administrado
 */
public class OwnBuiltinClasses {
    
    /** Creates a new instance of OwnBuiltinClasses */
    public OwnBuiltinClasses() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length<3 && args.length>6){
            System.out.println("Enter between 3 and 6 names");
            System.exit(0);
        }
        //display the names
        
        for(int i=0;i<args.length;i++){
            System.out.println(args[i]);
        }
        //display the generated New Name
        System.out.print("The name generated : ");        
        System.out.println(OwnBuiltinClasses.generateNewName(args));
        
    }
    public static String generateNewName(String[] arr){
        String newname="";
        for(int i=0;i<arr.length;i++){
            newname=newname+arr[i].charAt(1);
        }     
        return newname;
    }
    
}
