

package myhashset;

import java.util.HashSet;

public class Main {
    public Main() {
    }
    public static void main(String[] args) {
      HashSet hs=new HashSet(5);
      hs.add("Raul");
      hs.add("Ramirez");
      hs.add(new MyOwnClass());
      hs.add(new MyOwnClass());      
      hs.add(2);
      hs.add(3);
      System.out.println(hs);
    }
    
}
class MyOwnClass{
    
}

