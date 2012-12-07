import javax.swing.JOptionPane;
/*
 * InputFromKeyboardJOptionPane.java
 *
 * Created on 17 de octubre de 2007, 05:27 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Administrado
 */
public class GetInputFromKeyboardJOptionPane {
    
    /** Creates a new instance of InputFromKeyboardJOptionPane */
    public GetInputFromKeyboardJOptionPane() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String name = "";
        name=JOptionPane.showInputDialog("Please enter your name");
        String msg = "Hello " + name;
        JOptionPane.showMessageDialog(null, msg);
        String age = "";
        age=JOptionPane.showInputDialog("Please enter your age");
        int ageint=Integer.parseInt(age);
        
        String msg2 = (ageint>100)?" you are old!":" you are young!";
        JOptionPane.showMessageDialog(null, msg+",  "+msg2);        

    }
    
}