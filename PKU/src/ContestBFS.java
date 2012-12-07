
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;
import java.util.Scanner;

public class ContestBFS {

    class Node {

        int x, y, c;

        public Node(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public String toString() {
            return x + " " + y + " " + c;
        }
    }

    public void solve() {
        Scanner sc = new Scanner(System.in);
        int n = parseInt(sc.nextLine());
        int c = 0;
        int[] di = {-1, 0, 1, 0}; //urld
        int[] dj = {0, 1, 0, -1};
        boolean[][] map = new boolean[302][302];
        int[][] bombs = new int[302][302];
        for (int i = 0; i < bombs.length; i++) {
            fill(bombs[i], Integer.MAX_VALUE);
        }
        while (c++ < n) {
            String[] s = sc.nextLine().split(" ");
            int t = parseInt(s[2]);
            int x = parseInt(s[0]);
            int y = parseInt(s[1]);
            map[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int X = x + di[i];
                int Y = y + dj[i];
                if (X >= 0 && Y >= 0) {
                    map[X][Y] = true;
                    bombs[X][Y] = min(bombs[X][Y], t);
                }
            }
        }
        boolean memo[][] = new boolean[302][302];
        Queue<Node> q = new LinkedList<Node>();
        q.add(new Node(0, 0, 0));
        String res = "";
        if (bombs[0][0] == 0) {
            res = "-1";
        } else {
            while (!q.isEmpty()) {
                Node p = q.poll();
//                Node p = l.get(l.size() - 1);
                if (!map[p.x][p.y]) {
                    res = p.c + "";
//                    System.err.println(l);
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int X = p.x + di[i];
                    int Y = p.y + dj[i];
                    if (X >= 0 && X < memo.length && Y >= 0 && Y < memo[X].length &&
                            !memo[X][Y]) {
                        if (p.c + 1 < bombs[X][Y]) {
                            memo[X][Y] = true;
//                            ArrayList<Node> wList = new ArrayList<Node>(l);
//                            wList.add();
                            q.add(new Node(X, Y, p.c + 1));
                        }
                    }
                }
            }
        }
        if (res.equals("")) {
            System.out.println("-1");
        } else {
            System.out.println(res);
        }
//        System.err.println(bomb);

    }

    public static void main(String[] args) {
        new ContestBFS().solve();

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
