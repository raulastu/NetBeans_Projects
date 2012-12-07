package round2;

import _2010_round1C.*;
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
        Scanner sc = new Scanner(new File("C:A-small-practice.in"));
//        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int nn = Integer.parseInt(sc.nextLine());
        int c = 0;
        while (c++ < nn) {
            int k = parseInt(sc.nextLine());
            String[] d = new String[k * 2 - 1];
            int[] minToB = new int[k * 2 - 1];
            int fMax = 0;
            int maxId = 0;
            for (int i = 0; i < d.length; i++) {
                d[i] = sc.nextLine().replaceAll(" +", "");
//                System.err.println(d[i]);
                int minRe = makePal(d[i]).length() - d[i].length();
                if (fMax < minRe) {
                    fMax = minRe;
                    maxId = i;
                }else if(fMax==minRe){
                    if(d[i].length()>d[maxId].length()){
                        maxId=i;
                    }
                }
                minToB[i] = minTOB(d[i]);
            }
            System.err.println(fMax);
            if (fMax > 0) {
                int smax = fMax;
                boolean beat = false;
                for (int i = 0; i < minToB.length; i++) {
                    if (i != maxId) {                        
                        if (minToB[i] > smax) {
                            beat = true;
                            smax = minToB[i];
                        }
                    }
                }
                if (beat) {
                    smax = Math.max(smax, d[maxId].length() - 1);
                }
                System.err.println(smax);
                int res = smax * d.length + (smax * (smax + 1));
                String r = "Case #" + c + ": " + res;
                System.err.println(r);
                pw.println(r);
            } else {
                String r = "Case #" + c + ": " + 0;
                System.err.println(r);
                pw.println(r);
            }
        }
        pw.close();
    }

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    static boolean isPal(String s) {
        return new StringBuffer(s).reverse().toString().equals(s);
    }

    static String makePal(String s) {
        String sx = "";
        for (int i = 0; i < s.length(); i++) {
            String sub = new StringBuffer(s.substring(0, i)).reverse().toString();
            if (isPal(s + sub)) {
//                System.err.println("xx"+s+sub);
                return s + sub;
            }
        }
        System.err.println("neverhappen");
        return sx;
    }

    static int minTOB(String s) {
        String sx = "";
        if (s.replace(s.charAt(0) + "", "").length() == 0) {
            return 1;
        } else {
            return s.length() - 1;
        }
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
