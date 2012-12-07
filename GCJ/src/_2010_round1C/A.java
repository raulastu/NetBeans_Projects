package _2010_round1C;

import gcj.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import static java.lang.Integer.*;
import static java.lang.Long.*;


import java.util.Scanner;

public class A {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:A-large.in"));
//        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int nn = Integer.parseInt(sc.nextLine());
        int c = 0;
        while (c++ < nn) {
            int n = parseInt(sc.nextLine());
            int[] l = new int[n];
            int[] r = new int[n];
            for (int i = 0; i < n; i++) {
                String ll[] = sc.nextLine().split(" ");
                l[i] = parseInt(ll[0]);
                r[i] = parseInt(ll[1]);
            }
            int rr = 0;
            for (int i = 0; i < r.length; i++) {
                for (int j = i; j < r.length; j++) {
                    if ((l[i] < l[j] && r[i] > r[j]) ||
                            (l[i] > l[j] && r[i] < r[j])) {
                        rr++;
                    }
                }
            }
            String rres = rr + "";
            String res = "Case #" + c + ": " + rres;
            System.err.println(res);
            pw.println(res);
        }
        pw.close();
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
}
