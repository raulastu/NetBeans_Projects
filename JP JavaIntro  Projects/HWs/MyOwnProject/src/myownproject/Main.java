/*
 * Main.java
 *
 * Created on 18 de octubre de 2007, 09:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myownproject;

import java.util.Properties;

/**
 *
 * @author Administrado
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Properties myProperties=new Properties();
        myProperties.setProperty("mykey40","myvalue40");
        myProperties.setProperty("mykey30","myvalue30");
        myProperties.setProperty("mykey20","myvalue20");
        
        
        myProperties.list(System.out);
    }
    
}
