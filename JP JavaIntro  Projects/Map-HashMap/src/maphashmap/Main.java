package maphashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Main {
    
    private static final Integer ONE = new Integer(1);
    
    public static void main(String[] args) {
        Map map=new HashMap();
        map.put("1st","raul");
        map.put("2nd","raul");
        map.put("3rd",new MyOwnClass());
        map.put("4th",new MyOwnClass());
        map.put("5th",1);
        System.out.println(map);
        
        Map a=new TreeMap();
        a.putAll(map);
        System.out.println(a);
    }    
    
}
class MyOwnClass {}