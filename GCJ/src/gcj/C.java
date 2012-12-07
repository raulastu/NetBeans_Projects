/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gcj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Integer.*;

/**
 *
 * @author rC
 */
public class C {

    static String format(long c) {
        String s = "";
        for (int i = 0; i < 4 - (c + "").length(); i++) {
            s += "0";
        }
        return s + c;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("C:C-large-practice.in"));
//        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File("C:/C-large-practice-out.txt"));
        int ncases = parseInt(sc.nextLine());
        char[] wel = "welcome to code jam".toCharArray();
        for (int i = 0; i < ncases; i++) {
            char[] line = sc.nextLine().toCharArray();
            int[][] dp = new int[20][line.length + 1];
            Arrays.fill(dp[0], 1);
            for (int j = 1; j < dp.length; j++) {
                for (int k = 0; k < line.length; k++) {
                    if (line[k] == wel[j - 1]) {
                        dp[j][k + 1] = dp[j - 1][k + 1] + dp[j][k];
                        dp[j][k + 1] = dp[j][k + 1] % 10000;
                    } else {
                        dp[j][k + 1] = dp[j][k];
                    }
                }
            }
//            printm(dp);
            String s = "Case #" + (i + 1) + ": " + format(dp[19][line.length]);
            System.err.println(s);
            pw.println(s);
        }
        pw.close();
    }

    private static void print(Object... rs) {
        print("", rs);
    }

    private static void print(String msg, Object... rs) {
        String x = Arrays.deepToString(rs);
        if (x.indexOf("[[") == 0) {
            x = x.substring(1, x.length() - 1);
        }
        System.err.println(msg + " " + x);
    }

    private static void printm(String[] a) {
        for (int i = 0; i < a.length; i++) {
            System.err.println("[" + a[i] + "]");
        }
    }

    private static void printm(char[][] a) {
        for (int i = 0; i < a.length; i++) {
            System.err.println("[" + new String(a[i]) + "]");
        }
    }

    private static void printm(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            String line = "";
            for (int j = 0; j < a[i].length; j++) {
                line += a[i][j];
            }
            System.err.println("[" + line + "]");
        }
    }
}
