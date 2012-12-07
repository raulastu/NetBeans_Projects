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

public class ContestDiningCows {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = 0;
        int[] x = new int[n];
        int n1s = 0;
        while (c < n) {
            x[c] = sc.nextInt();
            if (x[c++] == 1) {
                n1s++;
            }
        }
        int n2sLeft = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < x.length; i++) {
            res = min(n1s + n2sLeft, res);
            if (x[i] == 2) {
                n2sLeft++;
            } else {
                n1s--;
            }
        }
        res = min(n1s + n2sLeft, res);
        System.out.println(res);
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
