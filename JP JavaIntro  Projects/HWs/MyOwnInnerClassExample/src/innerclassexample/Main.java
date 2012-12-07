package innerclassexample;


public class Main {
    
    public static void main(String[] args) {
        
        // Create an object instance of a class that contains
        // an inner class - we will call it outer class.
        OuterClass oc = new OuterClass();
        
        // Create an object instance of an inner class.
        OuterClass.InnerClass ic = oc.new InnerClass();
        
        // Create an object instance of an inner class.
        OuterClass.InnerClass.InnerInnerClass iic = ic.new InnerInnerClass();        
        
        // Display data from both outer class and inner class.
        System.out.println("Access data from outer class = " + oc.data);
        System.out.println("Access data2 from inner class = " + ic.data2);
        System.out.println("Access data3 from innerinner class = " + iic.data3);
        
        // Invoke a method from an inner class
        ic.method();
        iic.method2();
    }
    
}