package round1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import static java.lang.Integer.*;

public class B {

    static class Node {

        int x, y, t;

        public Node(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }

        @Override
        public String toString() {
            return x + " " + y + " " + t;
        }
    }
//    int[] di = {-1, 0, 1, 0, 2, 0, -2, 0}; //urdl
//    int[] dj = {0, 1, 0, -1, 0, 2, 0, -2};
    int[] di = {-1, 0, 1, 0}; //urdl
    int[] dj = {0, 1, 0, -1};

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:C-large-practice.in"));
        PrintWriter pw = new PrintWriter(new File("C:/B-out.txt"));
        int ncases = parseInt(sc.nextLine());

        for (int i = 0; i < ncases; i++) {
            String a[] = sc.nextLine().split(" ");
            int N = Integer.parseInt(a[0]);
            int M = Integer.parseInt(a[1]);
            long[][][] row = new long[N][M][3];
            for (int j = 0; j < row.length; j++) {
                String[] line = sc.nextLine().split(" ");
                for (int k = 0; k < M; k++) {
                    row[j][k][0] = parseInt(line[3 * k]);
                    row[j][k][1] = parseInt(line[3 * k + 1]);
                    row[j][k][2] = parseInt(line[3 * k + 2]);
                }
            }
            for (int j = 0; j < row.length; j++) {
                for (int k = 0; k < row[j].length; k++) {
                    System.err.println(row[j][k][0] + " " + row[j][k][1] + " " + row[j][k][2]);
                }
            }
//memo[][] boolean = new boolean[X][X];
            Queue<ArrayList<Node>> q = new LinkedList<ArrayList<Node>>();
            ArrayList<Node> li = new ArrayList<Node>();
            li.add(new Node(0, 0, 0));
            q.add(li);
            int res = -1;
            boolean[][][] memo = new boolean[2 * N][2 * M][10];
            while (!q.isEmpty()) {
                ArrayList<Node> l = q.poll();
                Node p = l.get(l.size() - 1);
                print(l);
                if (p.x == N * 2 - 1 && p.y == M * 2 - 1) {
                    res = p.t;
                    break;
                }
                int[] di = {-1, 0, 1, 0}; //urdl
                int[] dj = {0, 1, 0, -1};
                if (p.x % 2 == 0 && p.y % 2 == 0) {
                    di = new int[]{-2, 0, 1, 0, 0}; //urdl
                    dj = new int[]{0, 1, 0, -2, 0};
                } else if (p.x % 2 == 0 && p.y % 2 != 0) {
                    di = new int[]{-2, 0, 1, 0, 0};
                    dj = new int[]{0, 2, 0, -1, 0};
                } else if (p.x % 2 != 0 && p.y % 2 == 0) {
                    di = new int[]{-1, 0, 2, 0, 0};
                    dj = new int[]{0, 1, 0, -2, 0};
                } else {
                    di = new int[]{-1, 0, 2, 0, 0};
                    dj = new int[]{0, 2, 0, -1, 0};
                }
                print(di);
                print(dj);
                for (int j = 0; j < 4; j++) {
                    int X = p.x + di[j];
                    int Y = p.y + dj[j];
                    if (X >= 0 && X < 2 * N && Y >= 0 && Y < 2 * M) {
                        System.err.println(X + " " + Y);
                        long S = row[X / 2][Y / 2][0];
                        long W = row[X / 2][Y / 2][1];
                        long T = row[X / 2][Y / 2][2];
                        long up = S % (S + W) + T % (S + W);
                        long low = up - S;
                        int max = Math.max(Math.abs(di[j]), Math.abs(dj[j]));
                        int myt = (int) (p.t % (S + W) + T % (S + W));
                        if (!memo[X][Y][myt]) {
                            if (max == 1) {
                                if (low <= myt && myt < up) {
                                    ArrayList<Node> wl = new ArrayList<Node>(l);
                                    memo[X][Y][myt] = true;
                                    wl.add(new Node(X, Y, p.t + 1));
                                    q.add(wl);
                                } else {
                                    memo[X][Y][myt] = true;
                                    ArrayList<Node> wl = new ArrayList<Node>(l);
                                    wl.add(new Node(X, Y, p.t + 1));
                                    q.add(wl);
                                }
                            } else {
                                memo[X][Y][myt] = true;
                                ArrayList<Node> wl = new ArrayList<Node>(l);
                                wl.add(new Node(X, Y, p.t + 2));
                                q.add(wl);
                            }
                        }
                    }
                }
            }
            String s = "Case #" + (i + 1) + ": " + res;
            System.err.println(s);
            pw.println(s);
        }
        pw.close();
    }

    private static void print(Object... rs) {
        print("", rs);
    }

    private static void print(String msg, Object... rs) {
        String x = Arrays.deepToString(rs);
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

    private static void printm(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            String line = "";
            for (int j = 0; j < a[i].length; j++) {
                line += a[i][j];
            }
            System.err.println("[" + line + "]");
        }
    }
}
