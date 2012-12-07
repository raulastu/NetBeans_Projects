import java.util.ArrayList;

public class MyOwnGenericClass {
    
    public static void main(String[] args) {
        
        Integer n22=22;
        Long l22=34L;
        Object obj=new Object();
        Boolean b=n22<l22;
        
        PairExtendedAgain <Integer, Long, Object, Boolean> homework
                = new  PairExtendedAgain<Integer, Long, Object, Boolean> (n22,l22,obj,b);
        
        System.out.println("first of PairExtendedAgain  = " + homework.getFirst());
        System.out.println("second of PairExtendedAgain  = " + homework.getSecond());
        System.out.println("third of PairExtendedAgain  = " + homework.getThird());
        System.out.println("fourth of PairExtendedAgain  = " + homework.getFourth());
    }
    
}
