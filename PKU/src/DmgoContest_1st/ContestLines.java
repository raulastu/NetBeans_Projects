package DmgoContest_1st;


import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class ContestLines {

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        int n = parseInt(sc.nextLine());
        int c = 0;
        int x[] = new int[n];
        int y[] = new int[n];
        while (c < n) {
            String[] points = sc.nextLine().split(" ");
            x[c] = parseInt(points[0]);
            y[c] = parseInt(points[1]);
            c++;
        }
        HashSet<String> set = new HashSet();
        for (int i = 0; i < x.length; i++) {
            for (int j = i + 1; j < x.length; j++) {
                int a = (y[i] - y[j]);
                int b = (x[i] - x[j]);
                int gcd = gcd(a, b);
                a /= gcd;
                b /= gcd;
                if (a < 0) {
                    a = -a;
                    b = -b;
                }
                set.add(a + " " + b);
            }
        }
//        System.err.println(al);
//        System.err.println(al2);
        System.out.println(set.size());
    }

    public static void main(String[] args) {
        new ContestLines().solve();
    }

    private static void print(Object... rs) {
        print("", rs);
    }

    private static void print(String msg, Object... rs) {
        String x = deepToString(rs);
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
}
