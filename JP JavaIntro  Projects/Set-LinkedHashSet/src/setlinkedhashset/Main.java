package setlinkedhashset;

import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
    
    public static void main(String[] args) {
        
        Set ts = new LinkedHashSet();
        
        ts.add("one");
        ts.add("two");
        ts.add("three");
        ts.add("four");
        ts.add("two");
        
        System.out.println("Members from LinkedHashSet = " + ts);
        
        Set ts2 = new LinkedHashSet();
        
        ts2.add(2);
        ts2.add(1);
        ts2.add(3);
        ts2.add(3);
        
        System.out.println("Members from LinkedHashSet = " + ts2);
    }
    
}
