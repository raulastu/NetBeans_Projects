package binarysearching;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        
        // Set up testing data
        String name[] = {
            new String("Sang"),
            new String("Shin"),
            new String("Boston"),
            new String("Passion"),
            new String("Shin"),
        };
        
        // Create a List object
        List l = Arrays.asList(name);
        
        // Perform binary search
        int position = Collections.binarySearch(l, "Boston");
        System.out.println("Position of the searched item = " + position);
    }
    
}