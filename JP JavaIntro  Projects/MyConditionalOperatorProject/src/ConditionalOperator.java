/*
 * ConditionalOperator.java
 *
 * Created on 15 de octubre de 2007, 06:41 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Administrado
 */
public class ConditionalOperator {
    
    /** Creates a new instance of ConditionalOperator */
    public ConditionalOperator() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Declare and initialize two variables, one String type variable
        // called status and the other int primitive type variable called 
        // grade.
        String status = "";
        int salary = 100000;

        // Get status of the student.
        status = (salary >= 50000)?"You are rich!":"You are poor!";

        // Print status.
        System.out.println( status );
    }
    
}
