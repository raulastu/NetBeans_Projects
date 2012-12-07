package _2010_round1C;

import com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator.HeapNode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import static java.lang.Integer.*;


import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class C {

    static class Node {

        int val, x, y;

        public Node(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return val + " " + x + " " + y;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:C-large-practice (1).in"));
//        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int nn = Integer.parseInt(sc.nextLine());
        int c = 0;
        Comparator cp = new Comparator<Node>() {

            public int compare(Node o1, Node o2) {
                if (o1.val == o2.val) {
                    if (o1.x == o2.x) {
                        return o1.y - o2.y;
                    } else {
                        return o1.x - o2.x;
                    }
                } else {
                    return o2.val - o1.val;
                }
            }
        };
        while (c++ < nn) {
            String[] line = sc.nextLine().split(" ");
            int M = parseInt(line[0]);
            int N = parseInt(line[1]);
            char[][] grid = new char[M][N];
            for (int i = 0; i < M; i++) {
                char[] cc = sc.nextLine().toCharArray();
                String s = "";
                for (int j = 0; j < cc.length; j++) {
                    int cx = cc[j] - '0';
                    if (cc[j] >= 'A' && cc[j] <= 'F') {
                        cx = 10 + (cc[j] - 'A');
                    }
                    String st = Integer.toBinaryString(cx);
                    int l = st.length();
                    for (int k = 0; k < 4 - l; k++) {
                        st = "0" + st;
                    }
                    s += st;
                }
                grid[i] = s.toCharArray();
            }
            int[][] mark = new int[M][N];
            TreeSet<Node> l = new TreeSet<Node>(cp);
            for (int i = 0; i < mark.length; i++) {
                for (int j = 0; j < mark[i].length; j++) {
                    int val = 1;
                    mark[i][j] = 1;
                    if (i == 0) {
                    } else if (j == 0) {
                    } else if (grid[i][j] == grid[i - 1][j - 1] &&
                            grid[i - 1][j] == grid[i][j - 1] &&
                            grid[i][j] != grid[i - 1][j]) {
                        mark[i][j] = 1 + Math.min(mark[i - 1][j - 1],
                                Math.min(mark[i][j - 1],
                                mark[i - 1][j]));
                        val = mark[i][j];
                        l.add(new Node(val, i - val + 1, j - val + 1));
                    }
                }
            }
            String rrr = "";
            boolean[][] memo = new boolean[M][N];
            int[] res = new int[510];
            int tot = 0;
            while (!l.isEmpty()) {
                Node node = l.first();
                l.remove(node);
                res[node.val]++;
                if (node.val != 1) {
                    tot += node.val * node.val;
                }
                int x1 = node.x;
                int y1 = node.y;
                int x2 = node.x + node.val - 1;
                int y2 = node.y + node.val - 1;
                for (Iterator<Node> it = l.iterator(); it.hasNext();) {
                    Node node1 = it.next();
                    boolean tlInside = overl(node1.x, node1.y, x1, y1, x2, y2);
                    boolean brInside = overl(node1.x + node1.val - 1, node1.y + node1.val - 1, x1, y1, x2, y2);
                    boolean trInside = overl(node1.x, node1.y + node1.val - 1, x1, y1, x2, y2);
                    boolean blInside = overl(node1.x + node1.val - 1, node1.y, x1, y1, x2, y2);
                    boolean isInside = tlInside || brInside || trInside || blInside;
                    if (isInside) {
                        it.remove();
                    }
                }
                for (int i = x1; i <= x2; i++) {
                    for (int j = y1; j <= y2; j++) {
                        mark[i][j] = 0;
                        memo[i][j] = true;
                    }
                }
                for (int i = x1; i <= x2 + node.val; i++) {
                    for (int j = y1; j <= y2 + node.val; j++) {
                        if ((i > 0 && j > 0 && i < mark.length && j < mark[i].length) && (i > x2 || j > y2)) {
                            if (memo[i][j]) {
                                continue;
                            }
                            if ((grid[i][j] == grid[i - 1][j - 1] &&
                                    grid[i - 1][j] == grid[i][j - 1] &&
                                    grid[i][j] != grid[i - 1][j])) {
                                mark[i][j] = 1 + Math.min(mark[i][j - 1],
                                        Math.min(mark[i - 1][j], mark[i - 1][j - 1]));
                                l.add(new Node(mark[i][j], i - mark[i][j] + 1, j - mark[i][j] + 1));
                            }
                        }
                    }
                }
            }
            int ccc = 0;
            res[1] = M * N - tot;
            for (int i = res.length - 1; i > 0; i--) {
                if (res[i] > 0) {
                    ccc++;
                    tot += res[i];
                    rrr += i + " " + res[i] + "\n";
                }
            }
            rrr = rrr.length() >= 1 ? rrr.substring(0, rrr.length() - 1) : rrr;
            String ress = "Case #" + c + ": " + ccc + "\n" + rrr;
            pw.println(ress);
        }
        pw.close();
    }

    static boolean overl(int xx, int yy, int x, int y, int x1, int y1) {
        boolean a1 = xx >= x && xx <= x1;
        boolean a2 = yy >= y && yy <= y1;
        return a1 && a2;
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

    private static void printm(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            String l = "";
            for (int j = 0; j < a[i].length; j++) {
                l += a[i][j] + " ";
            }
            System.err.println("[" + l + "]");
        }
    }
}
