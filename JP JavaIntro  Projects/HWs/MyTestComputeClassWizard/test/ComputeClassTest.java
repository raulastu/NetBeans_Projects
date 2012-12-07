import junit.framework.*;
/*
 * ComputeClassTest.java
 * JUnit based test
 *
 * Created on 19 de diciembre de 2007, 11:56 AM
 */

/**
 *
 * @author Administrado
 */
public class ComputeClassTest extends TestCase {

    int x=0;
    int y=0;
    public ComputeClassTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        x=7;
        y=5;
    }

    protected void tearDown() throws Exception {
    }
    
    public void testAdd() {
        System.out.println("add");
        ComputeClass instance = new ComputeClass();
        
        int expResult = 12;
        int result = instance.add(x, y);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
       //fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class ComputeClass.
     */
    public void testMultiply() {
        System.out.println("multiply");
        
        ComputeClass instance = new ComputeClass();
        
        int expResult = 35;
        int result = instance.multiply(x, y);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class ComputeClass.
     */
    public void testSubtract() {
        System.out.println("subtract");
        
        ComputeClass instance = new ComputeClass();
        
        int expResult = 2;
        int result = instance.subtract(x, y);
        assertEquals(expResult, result);
    }
    public void testAddAndMultiplyBy3() {
        System.out.println("addAndMultiplyBy3");
        
        ComputeClass instance = new ComputeClass();
        
        int expResult = 36;
        int result = instance.addAndMultiplyBy3(x, y);
        assertEquals(expResult, result);
    }
    
}
