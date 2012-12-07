package datamanipulating;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        
        // Set up test data
        String n[] = {
            new String("Boston"),
            new String("New York"),
            new String("Seoul"),
            new String("Shanghai")
        };
        
        // Create a List from an array
        List l = Arrays.asList(n);
        
        // Perform the sorting operation
        Collections.sort(l);
        System.out.println("Sorted list = " + l);
        
        // Reverse the list
        Collections.reverse(l);
        System.out.println("Reversed list = " + l);
        
    }
    
}