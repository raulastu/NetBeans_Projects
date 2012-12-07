import javax.swing.JOptionPane;
/*
 * OwnJavaArray.java
 *
 * Created on 17 de octubre de 2007, 07:57 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Administrado
 */
public class OwnJavaArray {
    
    /** Creates a new instance of OwnJavaArray */
    public OwnJavaArray() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Get the first name from a name string using split() instance (non-static) method
        String person1NameInstance =JOptionPane.showInputDialog("Please enter full name (first-name last-name)");
        String person2NameInstance =JOptionPane.showInputDialog("Please enter full name (first-name last-name)");
        String person3NameInstance =JOptionPane.showInputDialog("Please enter full name (first-name last-name)");
        
        String[] nameArrayForPerson1 = person1NameInstance.split(" ");
        String[] nameArrayForPerson2 = person2NameInstance.split(" ");
        String[] nameArrayForPerson3 = person3NameInstance.split(" ");
    
        // Get the lengths of strings using length() instance (non-static) method
        int lengthOfFirstNameOfPerson1 = nameArrayForPerson1[0].length();
        int lengthOfFirstNameOfPerson2 = nameArrayForPerson2[0].length();
        int lengthOfFirstNameOfPerson3 = nameArrayForPerson3[0].length();
        

        // Compare the lengths of the first names between person1 and person2
        String longestFirstName="";
        int max=0;
        
        if(lengthOfFirstNameOfPerson1 > lengthOfFirstNameOfPerson2){
            longestFirstName=person1NameInstance;
            max=lengthOfFirstNameOfPerson1;
        }
        else{
            longestFirstName=person2NameInstance;
            max=lengthOfFirstNameOfPerson2;
        }
        
        if(lengthOfFirstNameOfPerson3>max){
            longestFirstName=person3NameInstance;   
        }
        System.out.println(longestFirstName+ " has the longest first name");        
    }
    
}
