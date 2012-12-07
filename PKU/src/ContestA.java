
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class ContestA {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int c = 1;
        int first = sc.nextInt();
        int cost1 = 0;
        ArrayList<Integer> al = new ArrayList<Integer>();
        al.add(first);
        while (c++ < n) {
            int curr = sc.nextInt();
            al.add(curr);
            if (first > curr) {
                cost1 += abs(first - curr);
            } else {
                first = curr;
            }
        }
//        System.out.println(cost1);
        reverse(al);
//        System.err.println(al);
        int cost2 = 0;
        first = al.get(0);
        for (int i = 1; i < al.size(); i++) {
            int curr = al.get(i);
            if (first > curr) {
                cost2 += abs(first - curr);
            } else {
                first = curr;
            }
        }
//        System.out.println(cost1);
        System.out.println(min(cost1, cost2));
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
