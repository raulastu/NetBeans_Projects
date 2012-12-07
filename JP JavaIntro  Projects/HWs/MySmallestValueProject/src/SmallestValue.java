/*
 * SmallestValue.java
 *
 * Created on 15 de octubre de 2007, 06:53 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Administrado
 */
public class SmallestValue {
    
    /** Creates a new instance of SmallestValue */
    public SmallestValue() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                 //declares the numbers
        int num1 = 10;
        int num2 = 23;
        int num3 = 5;
        int min = 0;
        
        //determines the highest number
        min = (num1<num2)?num1:num2;
        min = (min<num3)?min:num3;
        String comment = (min<10)?"The smallest number is less than 10!":"The smallest number is greather or equal to 10!";
        //prints the output on the screen
        System.out.println("number 1 = "+num1);
        System.out.println("number 2 = "+num2);
        System.out.println("number 3 = "+num3);
        System.out.println("The smallest number is = "+min);
        System.out.println(comment);
    }
    
}
