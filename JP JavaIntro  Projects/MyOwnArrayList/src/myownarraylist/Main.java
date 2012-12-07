

package myownarraylist;
import java.util.*;

public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    public static void main(String[] args) {
        ArrayList array=new ArrayList(2);
        array.add("x");
        array.add("y");
        array.add(new MyOwnClassObject());
        array.add(2);
        array.add(new Integer(3));
        array.add(new Integer(1));
        ListIterator li=array.listIterator();
        while(li.hasNext())
            System.out.println(li.next());
        Object [] a=array.toArray();
        for(Object obj:a)
            System.out.println(obj);
    }    
}
class MyOwnClassObject{}