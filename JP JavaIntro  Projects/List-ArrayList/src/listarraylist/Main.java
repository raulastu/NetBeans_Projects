package listarraylist;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {
    
    public static void main(String[] args) {
        
        // Create ArrayList object with capacity of 2 elements
        ArrayList al = new ArrayList(2);
        System.out.println(al+", size = "+al.size());
        
        // Add items to the ArrayList
        al.add("R");
        al.add("U");
        al.add("O");
        al.add(new String("x"));
        al.add(2, new Integer(10));
        System.out.println(al+", size = " + al.size());
        
        // Remove item
        al.remove("U");
        System.out.println(al+", size = " + al.size());
        
        // Check if the list contains the specified element
        Boolean b = al.contains("x");
        System.out.println("The list contains x = " + b);
        b = al.contains("p");
        System.out.println("The list contains p = " + b);
        b = al.contains(new Integer(10));
        System.out.println("The list contains Integer of 10 = " + b);
        
        // Create ListIterator and iterate entries in it
        ListIterator li = al.listIterator();
        while (li.hasNext())
            System.out.println("From ListIterator = " + li.next());
        
        // Create Object array from ArrayList
        Object a[] = al.toArray();
        for (int i=0; i<a.length; i++)
            System.out.println("From an Array = " + a[i]);
    }
}
