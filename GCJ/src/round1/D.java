package round1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Integer.*;

public class D {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("C:E-SImpleCoincidenciaADN.in"));
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int ncases = parseInt(sc.nextLine());
        for (int i = 0; i < ncases; i++) {
            int res = 0;
            String[] s = sc.nextLine().split(" ");
//            int intx [] = new int[s.length];
            System.err.println(Arrays.deepToString(s));
            int max = 0;
            for (int j = 0; j < s[0].length(); j++) {
                for (int k = j; k <= s[0].length(); k++) {
                    String sub = s[0].substring(j, k);
                    if (s[1].contains(sub)) {
                       max = Math.max(max,sub.length());
                    }
                }
            }
            String ss = "Caso #" + (i + 1) + ": " + max;
            System.err.println(ss);
            pw.println(ss);
        }
        pw.close();
    }

    public static int calculate(String input) {
        if (input.contains("-")) {
            String[] a = input.split("\\-");
            int x = parseInt(a[0]);
            int y = parseInt(a[1]);
            return x - y;
        } else if (input.contains("/")) {
            String[] a = input.split("\\/");
            int x = parseInt(a[0]);
            int y = parseInt(a[1]);
            return x / y;
        } else if (input.contains("*")) {
            String[] a = input.split("\\*");
            int x = parseInt(a[0]);
            int y = parseInt(a[1]);
            return x * y;
        } else {
            String[] a = input.split("\\+");
            int x = parseInt(a[0]);
            int y = parseInt(a[1]);
            return x + y;
        }
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
