/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.io.File;
import java.util.Scanner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rC
 */
public class CompareTwoFiles2Test {

    public CompareTwoFiles2Test() {
    }

    /**
     * Test of sonIguales method, of class CompareTwoFiles2.
     */
    @Test
    public void exactCorrectSolution() throws Exception {
        System.out.println("exactCorrectSolution");
        Scanner rightAnswer = new Scanner(new File("test/admin/A_correctSolution.sol"));
        Scanner yourAnswer = new Scanner(new File("test/admin/A_yourExactCorrectSolution.sol"));
        StringBuffer comment = new StringBuffer();
        boolean expResult = true;
        boolean result = CompareTwoFiles2.sonIguales(rightAnswer, yourAnswer, comment);
        System.out.println(comment);
        assertEquals(expResult, result);
    }

    @Test
    public void trailingLinesCorrectSolution() throws Exception {
        System.out.println("trailingLinesCorrectSolution");
        Scanner rightAnswer = new Scanner(new File("test/admin/A_correctSolution.sol"));
        Scanner yourAnswer = new Scanner(new File("test/admin/A_yourTrailingLinesCorrectSolution.sol"));
        StringBuffer comment = new StringBuffer();
        boolean expResult = true;
        boolean result = CompareTwoFiles2.sonIguales(rightAnswer, yourAnswer, comment);
        System.out.println(comment);
        assertEquals(expResult, result);
    }

    @Test
    public void trailingSpacesCorrectSolution() throws Exception {
        System.out.println("trailingSpacesCorrectSolution");
        Scanner rightAnswer = new Scanner(new File("test/admin/A_correctSolution.sol"));
        Scanner yourAnswer = new Scanner(new File("test/admin/A_yourTrailingSpacesCorrectSolution.sol"));
        StringBuffer comment = new StringBuffer();
        boolean expResult = true;
        boolean result = CompareTwoFiles2.sonIguales(rightAnswer, yourAnswer, comment);
        System.out.println(comment);
        assertEquals(expResult, result);
    }

    @Test
    public void trailingCasesCorrectSolution() throws Exception {
        System.out.println("trailingCasesCorrectSolution");
        Scanner rightAnswer = new Scanner(new File("test/admin/A_correctSolution.sol"));
        Scanner yourAnswer = new Scanner(new File("test/admin/A_yourTrailingSpacesCorrectSolution.sol"));
        StringBuffer comment = new StringBuffer();
        boolean expResult = true;
        boolean result = CompareTwoFiles2.sonIguales(rightAnswer, yourAnswer, comment);
        System.out.println(comment);
        assertEquals(expResult, result);
    }

    /**
     *
     * Incorrect Solution BEGIN
     */
    @Test
    public void incorrectSolution() throws Exception {
        System.out.println("incorrectSolution");
        Scanner rightAnswer = new Scanner(new File("test/admin/A_correctSolution.sol"));
        Scanner yourAnswer = new Scanner(new File("test/admin/A_yourIncorrectSolution.sol"));
        StringBuffer comment = new StringBuffer();
        boolean expResult = false;
        boolean result = CompareTwoFiles2.sonIguales(rightAnswer, yourAnswer, comment);
        System.out.println(comment);
        assertEquals(expResult, result);
    }

    @Test
    public void emptySolution() throws Exception {
        System.out.println("emptySolution");
        Scanner rightAnswer = new Scanner(new File("test/admin/A_correctSolution.sol"));
        Scanner yourAnswer = new Scanner(new File("test/admin/A_yourEmptySolution.sol"));
        StringBuffer comment = new StringBuffer();
        boolean expResult = false;
        boolean result = CompareTwoFiles2.sonIguales(rightAnswer, yourAnswer, comment);
        System.out.println(comment);
        assertEquals(expResult, result);
    }

    @Test
    public void incompleteSolution() throws Exception {
        System.out.println("incompleteSolution");
        Scanner rightAnswer = new Scanner(new File("test/admin/A_correctSolution.sol"));
        Scanner yourAnswer = new Scanner(new File("test/admin/A_yourIncompleteSolution.sol"));
        StringBuffer comment = new StringBuffer();
        boolean expResult = false;
        boolean result = CompareTwoFiles2.sonIguales(rightAnswer, yourAnswer, comment);
        System.out.println(comment);
        assertEquals(expResult, result);
    }
}