package sethashsetfinddup2;

import java.util.HashSet;
import java.util.Set;

public class Main {
    
    public static void main(String[] args) {
        
        // Set up test data
        String name[] = {
            new String("Sang"),
            new String("Shin"),
            new String("Boston"),
            new String("Shin")
        };
        
        Set uniques = new HashSet();
        Set dups = new HashSet();
        
        for (int i=0; i<name.length; i++)
            if (!uniques.add(name[i]))
                dups.add(name[i]);
        
        // Remove items that duplicates 
        uniques.removeAll(dups);  
        
        System.out.println("Unique words:    " + uniques);
        System.out.println("Duplicate words: " + dups);
    }
    
}
