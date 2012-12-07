package mappolymorphism;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    
    public static void main(String[] args) {
        
        // Set up testing data
        String names[] = {
            new String("Sang"),
            new String("Shin"),
            new String("Boston"),
            new String("Passion"),
            new String("Shin")
        };
        
        Map m = new HashMap();
        MyOwnUtilityClass.checkDuplicate(m, names);
        
        m = new TreeMap();
        MyOwnUtilityClass.checkDuplicate(m, names);
        
        m = new LinkedHashMap();
        MyOwnUtilityClass.checkDuplicate(m, names);
    }
    
}
class MyOwnUtilityClass {
    
    private static final Integer ONE = new Integer(1);
    
    public static void checkDuplicate(Map m, String[] names){
        
        for (int i=0; i<names.length; i++) {
            Integer freq = (Integer) m.get(names[i]);
            
            // Adding an entry to the HashMap
            m.put(names[i], (freq==null ? ONE :
                new Integer(freq.intValue() + 1)));
        }
        System.out.println("Map type = " + m.getClass().getName() + " " + m);
    }
}
