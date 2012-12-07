package collectioniterator;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.text.DateFormatSymbols;

public class OysterMonths {
    
    Collection  safeMonths;
   
    public Collection  filter(Collection c) {
        
        Collection  filteredCollection = new ArrayList();
        
        // Creator Iterator object from a Collection object
        // and do the iteration.
        for(Iterator i = c.iterator(); i.hasNext(); ) {            
            String s = (String) i.next();
            
            if (condition(s)) {
                filteredCollection.add(s);
            }
        }
        return filteredCollection;
    }
    
    
    public boolean condition(String s) {
         
        if (s.contains("r")) {
            return true;
        }
        return false;
    }
    
    
    public static void main(String[] args) {
        
        OysterMonths om = new OysterMonths();  
        
        DateFormatSymbols dfs = new DateFormatSymbols();       
        String[] monthArray = dfs.getMonths();    
        
        Collection  months = Arrays.asList(monthArray);
        
        om.safeMonths = om.filter(months);
        
        System.out.println("The following months are safe for oysters:");
        System.out.println(om.safeMonths);
    }
    
}