package settreeset;

import java.util.Set;
import java.util.TreeSet;

public class Main {
    
    public static void main(String[] args) {
        
        Set ts = new TreeSet();
        
        ts.add("one");
        ts.add("two");
        ts.add("three");
        ts.add("four");
        ts.add("three");
        
        System.out.println("Members from TreeSet = " + ts);
        
        Set ts2 = new TreeSet();
        
        ts2.add(1);
        ts2.add(2);
        ts2.add(3);
        ts2.add(4);
        ts2.add(2);
        
        System.out.println("Members from TreeSet = " + ts2);
    }
    
}
