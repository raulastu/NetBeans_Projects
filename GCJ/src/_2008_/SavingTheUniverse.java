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

public class SavingTheUniverse {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:A-large-practice.in"));
//        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File("C:/myOutput.txt"));
        int nn = Integer.parseInt(sc.nextLine());
        int c = 0;
        while (c++ < nn) {
            int nEngines = Integer.parseInt(sc.nextLine());
            HashSet<String> set = new HashSet<String>();
            for (int i = 0; i < nEngines; i++) {
                set.add(sc.nextLine());
            }
            HashSet<String> set2 = new HashSet<String>();
            set2.addAll(set);
            int nQ = Integer.parseInt(sc.nextLine());
            String queries [] = new String[nQ];
            for (int i = 0; i < nQ; i++) {
                queries[i]=sc.nextLine();
            }
            int res = 0;
            for (int i = 0; i < queries.length; i++) {
                set2.remove(queries[i]);
                if(set2.isEmpty()){
                    res++;
                    set2.addAll(set);
                    set2.remove(queries[i]);
                }
            }
            String resToShow = "Case #" + c + ": "+res;
            System.err.println(resToShow);
            pw.println(resToShow);
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
    private static void printm(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.err.println("[" + a[i][j] + "]");
            }
        }
    }
}
