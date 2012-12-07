
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class Coprimos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = parseInt(sc.nextLine());
        int c = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                gcd[i][j] = gcd(i, j);
            }
        }
        while (c++ < n) {
            String[] line = sc.nextLine().split(" ");
            M = parseInt(line[0]);
            N = parseInt(line[1]);
            res = 0;
            System.out.println("Case " + (c) + ":");
            for (int i = 1; i <= M; i++) {
//                a[0] = i;
                go(i, 0, 0);
            }
//            System.err.println(res);
        }


    }
    static int res = 0;
    static int N;
    static int M;
    static int[][] gcd = new int[101][101];
    static int[] a = new int[31];

    static void go(int i, int sum, int n) {
        if (n == N) {
            if (sum == M) {
                String rr = "";
                for (int r = 0; r < n; r++) {
                    rr += (a[r] + " ");
                }
                System.out.println(rr.trim());
            }
            return;
        }
        for (int j = i; j <= M; j++) {
            if (sum + j > M) {
                break;
            }
            boolean cop = true;
            for (int ix = 0; ix < n; ix++) {
                if (gcd[a[ix]][j] != 1) {
                    cop = false;
                    break;
                }
            }
            if (cop) {
                a[n] = j;
                go(j, sum + j, n + 1);
            }
        }
        return;
    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
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
