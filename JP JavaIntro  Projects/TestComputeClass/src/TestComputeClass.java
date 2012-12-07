import junit.framework.*;

public class TestComputeClass extends TestCase {
    // Initialize common test data
    int x;
    int y;
    
    protected void setUp() {
        System.out.println("setUp - Intialize common test data");
        x = 7;
        y = 5;
    }
    
    protected void tearDown(){
        System.out.println("tearDown - Clean up");
    }
    
    public TestComputeClass(String name) {
        super(name);
    }
    
     /**
     * Test of add method, of class ComputeClass.
     */
    public void testAdd() {
        System.out.println("add");
        ComputeClass instance = new ComputeClass();
        
        int expResult = 12;
        int result = instance.add(x, y);
        assertEquals(expResult, result);
        
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
    
    // Create a TestSuite
    public static Test createTestSuite() {
        TestSuite testSuite = new TestSuite("AllTests");
        testSuite.addTest(new TestComputeClass("testAdd"));
        testSuite.addTest(new TestComputeClass("testMultiply"));
        testSuite.addTest(new TestComputeClass("testSubtract"));
        return testSuite;
    }
    
    public static void main(String[] args){
        
        
        System.out.println("Running the test through runTest()...");
        TestCase test = new TestComputeClass("compute"){
            public void runTest() {
                testAdd();
                testMultiply();
                testSubtract();
            }
        };
        TestResult tresult = test.run();
        System.out.println("Number of failures = " + tresult.failureCount());
        java.util.Enumeration e1 = tresult.failures();
        

        
    }
}