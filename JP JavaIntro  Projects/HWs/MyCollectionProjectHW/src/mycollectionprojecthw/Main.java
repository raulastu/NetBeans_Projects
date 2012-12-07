package mycollectionprojecthw;

import java.util.*;

public class Main {
    
    public Main() {
    }
    public static void main(String[] args) {
        Set hs = new HashSet();
        hs.add("Raúl");
        hs.add(new String("Ramírez"));
        hs.add(new MyOwnClass("Diana",25));
        hs.add(new MyOwnClass("Dante",30));
        hs.add(12);
        hs.add(new Integer(31));
        hs.add(new Integer(23));
        System.out.println("Elements of HashSet: ");
        for(Iterator it=hs.iterator();it.hasNext();){            
            Object ob;
            if((ob=it.next()) instanceof MyOwnClass){                
                System.out.println("name = "+((MyOwnClass)ob).name
                                + ", age = "+((MyOwnClass)ob).age);  
            }else
                System.out.println(ob+" ");
        }
        /**LinkedHashSet*/
        Set lhs = new LinkedHashSet();
        lhs.add("Raúl");
        lhs.add(new String("Ramírez"));
        lhs.add(new MyOwnClass("David",100));
        lhs.add(new MyOwnClass("Presenta",99));
        lhs.add(12);
        lhs.add(new Integer(43));
        lhs.add(new Integer(31));        
        System.out.println("\nElements of LinkedHashSet: ");
        for(Iterator it=lhs.iterator();it.hasNext();){
           Object ob;
            if((ob=it.next()) instanceof MyOwnClass){                
                        System.out.println("name = "+((MyOwnClass)ob).name
                                + ", age = "+((MyOwnClass)ob).age);  
            }
            else
                System.out.println(ob+" ");
        }
        
        List arrlist = new ArrayList();
        arrlist.add("Raúl");
        arrlist.add(new String("Ramírez"));
        arrlist.add(new MyOwnClass("Yulia",34));
        arrlist.add(new MyOwnClass("Alexis",32));
        arrlist.add(11);
        arrlist.add(new Integer(433));
        arrlist.add(new Integer(431));        
        System.out.println("\nElements of ArrayList: ");
        
        for(Iterator it=arrlist.iterator();it.hasNext();){
            Object ob;
            if((ob=it.next()) instanceof MyOwnClass){                
                        System.out.println("name = "+((MyOwnClass)ob).name
                                + ", age = "+((MyOwnClass)ob).age);  
            }
            else
                System.out.println(ob+" ");
        }        
    }    
}
