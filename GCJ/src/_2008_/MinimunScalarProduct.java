package _2008_;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;
import java.util.*;


import java.util.Scanner;

public class MinimunScalarProduct {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:in.txt"));
//        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File("C:/myOutput.txt"));
        int nn = Integer.parseInt(sc.nextLine());
        int c = 0;
        while (c++ < nn) {
            int n = Integer.parseInt(sc.nextLine());
            long [] si = readLongArray(sc.nextLine());
            long [] si2 =readLongArray(sc.nextLine());
            sort(si);
            sort(si2);
            long res = 0;
            for (int i = 0; i < si2.length; i++) {
                res += si[i] * si2[si2.length - i - 1];
            }
            String resToShow = "Case #" + c + ": " + res;
            System.err.println(resToShow);
            pw.println(resToShow);
        }
        pw.close();
    }

    private static int[] readIntArray(String sx) {
        String[] s = sx.split(" ");
        int[] res = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            res[i] = parseInt(s[i]);
        }
        return res;
    }

    private static long [] readLongArray(String sx) {
        String[] s = sx.split(" ");
        long [] res = new long [s.length];
        for (int i = 0; i < s.length; i++) {
            res[i] = parseInt(s[i]);
        }
        return res;
    }

    private static void print(Object... rs) {
        System.err.println(Arrays.deepToString(rs));
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
            for (int j = 0; j < a[i].length; j++) {
                System.err.println("[" + a[i][j] + "]");
            }
        }
    }
}
