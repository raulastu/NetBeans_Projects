package gcj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import static java.lang.Integer.*;


import java.util.Scanner;

public class A11 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:A-large-practice.in"));
//        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int nn = Integer.parseInt(sc.nextLine());
        int c = 0;
        while (c++ < nn) {
            String[] line = sc.nextLine().split(" ");
            int n = parseInt(line[0]);
            int k = parseInt(line[1]);
            String B = "";
            String R = "";
            for (int i = 0; i < k; i++) {
                B += "B";
                R += "R";
            }
            char[][] grid = new char[n][n];
            String[] s = new String[n];

            boolean r = false;
            boolean b = false;
            for (int i = 0; i < n; i++) {
                grid[i] = sc.nextLine().toCharArray();
                s[i] = new String(grid[i]);
                s[i] = s[i].replace(".", "");
                int l = s[i].length();
                String fill = "";
                for (int j = 0; j < n - l; j++) {
                    fill = "." + fill;
                }
                s[i] = fill + s[i];
                if (s[i].contains(R)) {
                    r = true;
                }
                if (s[i].contains(B)) {
                    b = true;
                }
            }
            for (int i = 0; i < s[0].length(); i++) {
                String sss = "";
                for (int j = 0; j < s.length; j++) {
                    sss += s[j].charAt(i);
                }
                if (sss.contains(R)) {
                    r = true;
                }
                if (sss.contains(B)) {
                    b = true;
                }
            }
            for (int i = 0; i < s.length * 2; i++) {
                String ss = "";
                for (int j = i, kk = 0; j >= 0; j--, kk++) {
                    if (j < s[0].length() && kk < s.length) {
                        ss += s[j].charAt(kk);
                    }
                }
                System.err.println(ss);
                if (ss.contains(R)) {
                    r = true;
                }
                if (ss.contains(B)) {
                    b = true;
                }
            }
            for (int i = 0; i < s.length * 2; i++) {
                String ss = "";
                for (int j = i, kk = s.length - 1; j >= 0; j--, kk--) {
                    if (j < s[0].length() && kk >= 0) {
                        ss += s[j].charAt(kk);
                    }
                }
                System.err.println(ss);
                if (ss.contains(R)) {
                    r = true;
                }
                if (ss.contains(B)) {
                    b = true;
                }
            }
            String rres = "";
            if (!r && !b) {
                rres = "Neither";
            } else if (r && b) {
                rres = "Both";
            } else if (r) {
                rres = "Red";
            } else if (b) {
                rres = "Blue";
            }
            String res = "Case #" + c + ": " + rres;
//            System.err.println(res);
            pw.println(res);

        }
        pw.close();
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
