
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class Votes {

    static class Node {

        long A, B;
        int i;

        public Node(long A, long B, int i) {
            this.A = A;
            this.B = B;
            this.i = i;
        }

        @Override
        public String toString() {
            return i + " " + A + " " + B;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int N = parseInt(line[0]);
        int K = parseInt(line[1]);
        long[][] l = new long[2][50000];
        int c = 0;
        Node[] nodes = new Node[N];
        while (c < N) {
            String[] ss = sc.nextLine().split(" ");
            long A = Long.parseLong(ss[0]);
            long B = Long.parseLong(ss[1]);
            nodes[c] = new Node(A, B, c + 1);
            c++;
        }
        sort(nodes, new Comparator() {

            public int compare(Object o1, Object o2) {
                Node a = (Node) o1;
                Node b = (Node) o2;
                return (int) (b.A - a.A);
            }
        });
        int winner = 0;
        long mostVotes = 0;
//        print(nodes);
        for (int i = 0; i < K; i++) {
            if (nodes[i].B > mostVotes) {
                winner = nodes[i].i;
                mostVotes = nodes[i].B;
            }
        }
        System.out.println(winner);
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
