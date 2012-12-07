
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class RUN {

    static int m=2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String[] a = sc.nextLine().split(" ");
//        int n = parseInt(a[0]);
//        m = parseInt(a[1]);
//        int[] mins = new int[n];
//        for (int i = 0; i < n; i++) {
//            mins[i] = parseInt(sc.nextLine());
//        }

        int[] mins = new int[10000];
        for (int i = 0; i < mins.length; i++) {
            mins[i] = i+1;
        }

        System.out.println(go(mins, 0, 0, 0, true, new ArrayList<Node>()));
    }

    static class Node {

        int x, y;
        boolean a;

        public Node(int x, int y, boolean a) {
            this.x = x;
            this.y = y;
            this.a = a;
        }

        @Override
        public String toString() {
            return x + " " + a;
        }

        @Override
        public boolean equals(Object obj) {
            Node b = (Node) obj;
            return b.x == x && b.y == y;
        }
    }
    static int max = 0;

    static int go(int[] mins, int i, int D, int factor, boolean runn, ArrayList<Node> al) {
        ArrayList<Node> al2 = new ArrayList<Node>(al);
        ArrayList<Node> al3 = new ArrayList<Node>(al);
        if (mins.length == i) {
            if (factor <= 0) {
                System.err.print(al);
                System.err.println(" " + D);
                return D;
            }
            return 0;
        }
        if(mins.length-i<factor)
            return 0;

        al2.add(new Node(i, 0, false));
        int a = go(mins, i + 1, D, factor - 1, false, al2);
        int b = 0;
        if ((factor <= m && runn) || (!runn && factor == 0)) {
            al3.add(new Node(i, 0, true));
            b = go(mins, i + 1, D + mins[i], factor + 1, true, al3);
        }
        return max(a, b);
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
