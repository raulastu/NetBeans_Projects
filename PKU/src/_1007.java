
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class _1007 {

    static class Node {

        int order, inversions;
        String val;

        public Node(int order, int inversions, String val) {
            this.order = order;
            this.inversions = inversions;
            this.val = val;
        }

        @Override
        public String toString() {
            return order + " " + inversions;
        }

        @Override
        public boolean equals(Object obj) {
            Node b = (Node) obj;
            return b.order == order && b.inversions == inversions;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Node> al = new ArrayList<Node>();
        int n = parseInt(sc.nextLine().split(" ")[1]);
        int c = 0;
        while (c++ < n) {
            String line = sc.nextLine();
            al.add(new Node(c, inv(line), line));
        }
        sort(al, new Comparator() {

            public int compare(Object o1, Object o2) {
                Node a = (Node) o1;
                Node b = (Node) o2;
                if (a.inversions == b.inversions) {
                    return a.order - b.order;
                } else {
                    return a.inversions - b.inversions;
                }
            }
        });
        for (Node node : al) {
            System.out.println(node.val);
        }
    }

    static int inv(String a) {
        int res = 0;
        for (int i = 0; i < a.length(); i++) {
            int x = 0;
            for (int j = i + 1; j < a.length(); j++) {
                if (a.charAt(i) > a.charAt(j)) {
                    x++;
                }
            }
            res += x;
        }
        return res;
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
