package myaccessmodifierexampleproject;

public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    public static void main(String[] args) {
        DummyClass t = new DummyClass();
        
        // Compiler error expected
        System.out.println("s1 = " + t.s1);  // accessing private variable of DummayClass class
        
        // No compile error expected
        System.out.println("s2 = " + t.s2);  // accessing protected variable
        System.out.println("s3 = " + t.s3);  // accessing public variable
        System.out.println("s4 = " + t.s4);  // accessing default access modifier variable
        
        // Compiler error expected
        t.method1();  // calling private method of DummyClass class
        
        // No compile error expected
        t.method2();  // calling protected method
        t.method3();  // calling public method
        t.method4();  // calling default access modifier method
    }
    
}
 
