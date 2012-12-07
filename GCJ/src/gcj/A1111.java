package gcj;

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

public class A1111 {

    static class Node {

        int x, y;
        boolean reach;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }

        @Override
        public boolean equals(Object obj) {
            Node b = (Node) obj;
            return b.x == x && b.y == y;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:B-large-practice.in"));
//        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int nn = Integer.parseInt(sc.nextLine());
        int c = 0;
        while (c++ < nn) {
            String par[] = sc.nextLine().split(" ");
            int N = parseInt(par[0]);
            int K = parseInt(par[1]);
            long B = parseLong(par[2]);
            int T = parseInt(par[3]);
            String[] pos = sc.nextLine().split(" ");
            String[] v = sc.nextLine().split(" ");
            ArrayList<Node> nodes = new ArrayList<Node>();
            int h = 0;
            int k = 0;
            for (int i = 0; i < pos.length; i++) {
                Node n = new Node(pos.length - i - 1, i);
                long l = parseInt(pos[i]) + T * parseInt(v[i]);
//                System.err.println(l);
                if (l >= B) {
                    n.reach = true;
                } else {
                    n.reach = false;
                }
                nodes.add(n);
            }
            for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
                Node node = it.next();
                if (!node.reach) {
                    it.remove();
                }
            }

            String res = "Case #" + c + ":";
            if (nodes.size() < K) {
                res += " IMPOSSIBLE";
            } else {

                Collections.sort(nodes, new Comparator() {

                    public int compare(Object o1, Object o2) {
                        Node n1 = (Node) o1;
                        Node n2 = (Node) o2;
                        return n1.x - n2.x;
                    }
                });
                int ix = 1;
                System.err.println(nodes);
                System.err.println(K);
                for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
                    it.next();
                    if (ix > K) {
                        it.remove();
                    }
                    ix++;
                }
                System.err.println(nodes);
                int cc = 0;
                int coun = 0;
                for (int i = 0; i < nodes.size(); i++) {
                    if (i == K) {
                        break;
                    }
                    if (nodes.get(i).x == coun) {
                        coun++;
                    } else {
                        cc += (nodes.get(i).x - coun) * (nodes.size() - i);
                        coun = nodes.get(i).x + 1;
                    }

                }
                res = "Case #" + c + ": " + cc;
            }
            System.err.println(res);
            pw.println(res);
        }
        pw.close();
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
