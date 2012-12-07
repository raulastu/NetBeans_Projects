
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class Toss {
    static int n = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String ll = sc.nextLine();
            if (ll.equals("0")) {
                break;
            }
            String line[] = ll.split(" ");
            n = parseInt(line[0]);
            int catcher = parseInt(line[1]);
            boolean[] left = new boolean[n];
            HashSet<Integer> set = new HashSet<Integer>();
            for (int i = 2; i < line.length; i++) {
                if (line[i].equals("L")) {
                    left[i - 2] = true;
                }
            }
            int tosses = 1;
            int thrower = 0;
            set.add(thrower);
            catcher = catcher - 1;
            set.add(catcher);
            while (set.size() < n) {
//                System.err.println(catcher);
                tosses++;
                int t = thrower;
                thrower = catcher;
                if (left[catcher]) {
                    left[catcher] = false;
                    if (up(catcher) == t) {
                        catcher = down(catcher);
                    } else {
                        catcher = down(t);
                    }
                } else {
                    left[catcher] = true;
                    if (down(catcher) == t) {
                        catcher = up(catcher);
                    } else {
                        catcher = up(t);
                    }
                }
                set.add(catcher);
            }
            System.out.println("Classmate " + (catcher + 1) + " got the ball last after " + (tosses) + " tosses.");
        }
    }

    static int down(int x) {
        int leftTro = x - 1;
        if (leftTro < 0) {
            leftTro = n - 1;
        }
        return leftTro;
    }

    static int up(int x) {
        int ri = x + 1;
        if (ri == n) {
            ri = 0;
        }
        return ri;
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
