import java.io.*;
import java.util.Date;
/*
 * Testing.java
 *
 * Created on 17 de octubre de 2007, 08:48 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Administrado
 */
public class Testing {
    
    /** Creates a new instance of Testing */
    public Testing() {
    }
    
    /**
     * @param args the command line arguments
     */
  public static void main(String[] args) {
      int[]arr=new int[10];
      
      for(int j :arr){
          //int j=i;
          System.out.print(j);
      }      
      System.out.println();
      for(int i=0;i<arr.length;i++){          
          int j=arr[i];
          System.out.print(j);
      }
  }

}
