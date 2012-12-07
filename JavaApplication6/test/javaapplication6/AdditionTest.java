/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication6;

import problemas.Addition;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rC
 */
public class AdditionTest {

    public AdditionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of sum method, of class Addition.
     */
    @Test
    public void testSum() {
        System.out.println("sum");
        int a = 0;
        int b = 0;
        Addition instance = new Addition();
        int expResult = 0;
        int result = instance.sum(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.        
    }

    /**
     * Test of multiply method, of class Addition.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        int a = 324;
        int b = 234;
        Addition instance = new Addition();
        int expResult = a*b;
        int result = instance.multiply(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.        
    }

}