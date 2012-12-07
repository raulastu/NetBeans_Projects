import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author sang
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Create BufferedReader object from Standard input device.
        // Standard input device is typically a keyborad.
        BufferedReader dataIn = new BufferedReader(new
                InputStreamReader( System.in) );

        // Prompt a user to enter his/her name
        String name = "";
        System.out.println("Please Enter Your Name:");

        // Read data into name variable
        int x=0;
        try{
             x = Integer.parseInt(dataIn.readLine());
        }catch( IOException e ){
            System.out.println("Error!");
        }
        // Display the name
        System.out.println("Hello " + x +"!");
    }

}