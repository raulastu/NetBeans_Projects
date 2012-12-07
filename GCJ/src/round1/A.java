package round1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import static java.lang.Integer.*;

public class A {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("C:A-large.in"));
        PrintWriter pw = new PrintWriter(new File("C:/A-large-out.txt"));
        int ncases = parseInt(sc.nextLine());

        String abc = "abcdefghijklmnopqrstuvwxyz";
        String[] va = new String[10 + "abcdefghijklmnopqrstuvwxyz".length()];
        va[0] = "1";
        va[1] = "0";
        for (int j = 2; j <= 9; j++) {
            va[j] = j + "";
        }
        for (int j = 0; j < abc.length(); j++) {
            va[j + 10] = abc.charAt(j) + "";
        } 
        long a = 1000000000000000000L;
        print(va);
        for (int i = 0; i < ncases; i++) {
            int res = 0;
            String n = sc.nextLine();
            System.err.println(n);
            HashMap<Character, String> map = new HashMap();
            int cc = 0;
            for (int j = 0; j < n.length(); j++) {
                if (!map.containsKey(n.charAt(j))) {
                    map.put(n.charAt(j), va[cc++]);
                }
            }
            String nr = "";
            for (int j = 0; j < n.length(); j++) {
                nr += map.get(n.charAt(j));
            }
            n = nr;
            if (cc == 1) {
                cc++;
            }
            System.err.println(n + " base " + cc);
//            System.err.println(n + " " + cc);
            long rres = Long.parseLong(n, cc);
            res = parseInt(n, cc);
            BigInteger bi = new BigInteger(nr, cc);
//            bi.
            String ss = "Case #" + (i + 1) + ": " + bi.toString();
            System.err.println(ss);
            pw.println(ss);
        }
        pw.close();
    }

    private static void print(Object... rs) {
        Arrays.deepToString(rs);
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
