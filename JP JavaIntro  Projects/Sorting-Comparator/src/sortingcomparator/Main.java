package sortingcomparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Main {
    
    public static void main(String[] args) {
        
        // Create an ArrayList object and add items to it.
        ArrayList a1 = new ArrayList();
        a1.add("Boston");
        a1.add("New York");
        a1.add("Seoul");
        a1.add("Tokyo");
        a1.add("London");
        a1.add("Bangkok");
        System.out.println("Before sorting = " + a1);
        
        // Get String Comparator object and sort the list
        Comparator comp = Comparators.stringComparator();
        Collections.sort(a1, comp);
        
        // Display the sorted list
        System.out.println("Sorted list using String Comparator = " + a1);
        
        // Create an ArrayList object and add items to it.
        ArrayList a2 = new ArrayList();
        a2.add(new Integer(33));
        a2.add(new Integer(17));
        a2.add(new Integer(45));
        a2.add(new Integer(100));
        a2.add(new Integer(3));
        System.out.println("Before sorting = " + a2);
        
        // Get Integer Comparator object and sort the list
        Comparator comp2 = Comparators.integerComparator();
        Collections.sort(a2, comp2);
        
        // Display the sorted list
        System.out.println("Sorted list using Integer Comparator = " + a2);
    }
}
class Comparators {
    
    // String Comparator object
    public static Comparator stringComparator() {
        
        return new Comparator() {
            
            public int compare(Object o1, Object o2) {
                String s1 = (String)o1;
                String s2 = (String)o2;
                int len1 = s1.length();
                int len2 = s2.length();
                int n = Math.min(len1, len2);
                char v1[] = s1.toCharArray();
                char v2[] = s2.toCharArray();
                int pos = 0;
                
                while (n-- != 0) {
                    char c1 = v1[pos];
                    char c2 = v2[pos];
                    if (c1 != c2) {
                        return c1 - c2;
                    }
                    pos++;
                }
                return len1 - len2;
            }
        };
    }
    
    // Integer Comparator object
    public static Comparator integerComparator() {
        return new Comparator() {
            
            public int compare(Object o1, Object o2) {
                int val1 = ((Integer)o1).intValue();
                int val2 = ((Integer)o2).intValue();
                return (val1<val2 ? -1 : (val1==val2 ? 0 : 1));
            }
        };
    }
    
    // Date  Comparator object
    public static Comparator dateComparator() {
        return new Comparator() {
            
            public int compare(Object o1, Object o2) {
                long val1 = ((Date)o1).getTime();
                long val2 = ((Date)o2).getTime();
                return (val1<val2 ? -1 : (val1==val2 ? 0 : 1));
            }
        };
    }
}

