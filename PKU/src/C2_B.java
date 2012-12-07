
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class C2_B {

    static class Node {

        int x, y;

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

    public static void main(String[] args) {
        String s = "5\n" +
                "5 -4 1 -3 1\n" +
                "6\n" +
                "-1000 -1000 -1000 1000 1000 1000\n" +
                "0\n";
        Scanner sc = new Scanner(s);

        while (sc.hasNextLine()) {
            int n = parseInt(sc.nextLine());
            if (n == 0) {
                break;
            }
            String h[] = sc.nextLine().split(" ");
            int[] hab = new int[h.length];
            int[][] pos = new int[hab.length][2];
            int posi = 0;
            int[][] neg = new int[hab.length][2];
            int negi = 0;
            int res = 0;
            ArrayList<Node> al = new ArrayList<Node>();
            int acc = 0;
            for (int i = 0; i < h.length; i++) {
                int c = parseInt(h[i]);
                System.err.println(al);
                if (acc * c < 0) {
                    if (abs(acc) < abs(c)) {
                        int m = abs(acc);
                        while (m > 0 || al.size() > 0) {
                            int r = abs((al.get(0).x));
                            if (r < m) {
                                res += (i - al.get(0).y) * r;
                                m -= r;
                                al.remove(0);
                            } else if (m > r) {
                                res += (i - al.get(0).y) * m;
                                m = 0;
                            } else {
                                res += (i - al.get(0).y) * m;
                                m = 0;
                                al.remove(0);
                            }
                        }
                    } else {
                        if (m > 0) {
                            al.add(new Node(c, i));
                        }
                    }
                } else {
                    al.add(new Node(c, i));
                    acc += c;
                }
                System.err.println(al);
            }
//            sort(hab);
            int nn = 0;
//            for (int i = 0; i < posi; i++) {
//                int ix = pos[i][0];
//                if (ix == 0) {
//                    continue;
//                }
//                for (int j = nn; j < negi; j++) {
//                    int jx = neg[j][0];
//                    if (ix > 0 && jx < 0) {
//                        int r = ix + jx;
//                        res += abs(pos[i][1] - neg[j][1]) * min(abs(ix), abs(jx));
//                        if (r > 0) {
//                            pos[i][0] = r;
//                            neg[j][0] = 0;
//                            nn++;
//                            ix = r;
//                        } else if (r < 0) {
//                            neg[j][0] = r;
//                            pos[i][0] = 0;
//                            break;
//                        } else {
//                            neg[j][0] = 0;
//                            pos[i][0] = 0;
//                            nn++;
//                            break;
//                        }
//
//                    }
//                }
//            }
            System.out.println(res);
        }
        sc.close();
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
