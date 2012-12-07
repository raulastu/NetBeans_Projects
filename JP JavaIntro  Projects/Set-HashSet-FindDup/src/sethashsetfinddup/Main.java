package sethashsetfinddup;

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
        String myownnames[]={"raul","raul","raul","raul"};        
        Set sra=new HashSet();
        for(String i:myownnames)
            sra.add(i);
        System.out.println(sra);
        // Create HashSet object instance and 
        // assign it to a variable of Set type.
        Set s = new HashSet();
        for (int i=0; i<name.length; i++)
            if (!s.add(name[i]))
                System.out.println("Duplicate detected: "+name[i]);
        
        System.out.println(s.size()+" distinct words detected: "+s);
    }
}
