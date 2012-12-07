package sethashsetadd;

import java.util.HashSet;

public class Main {
    

    public static void main(String[] args) {
        
        // Create HashSet object
        HashSet hs = new HashSet(5, 0.5f);
        System.out.println(hs.add("one"));
        System.out.println(hs.add("two"));
        System.out.println(hs.add("three"));
        System.out.println(hs.add("four"));
        System.out.println(hs.add("five"));
        
        // Print out the HashSet object
        System.out.println(hs);
        
        // Add a duplicate item to the HashSet
        Boolean b = hs.add("one");
        System.out.println("Duplicate item allowed = " + b);
        System.out.println(hs);   
        
    }
    
}