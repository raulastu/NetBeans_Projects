public class ClassAndObject {
    
    public static void main(String[] args) {
        
        // Create an instance of a class by using a Java language keyword "new".
        // For example, in order to create an object instance of a String class,
        // you will do the following
        String strInstance1 = new String("I am object instance of a String class");
        System.out.println("value of strIntance1 = " + strInstance1);
        
        // String class a special class for which you can create an instance
        // of it by assigning a string literal.  No other class allows this form of object creation.
        String strInstance2 = "I am object instance of a String class";
        System.out.println("value of strIntance2 = " + strInstance2);
        
        // Create an object instance of Integer class
        Integer intInstance1 = new Integer(20);
        System.out.println("value of intInstance1 = " + intInstance1);

        // Create another object instance of Integer class
        Integer intInstance2 = new Integer(30);
        System.out.println("value of intInstance2 = " + intInstance2);
    }
    
}