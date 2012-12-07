
import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class E {

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

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("E:/ConcursoProgramacion/ProblemsPending/XhallaYFishMan/XhallaYFishMan.in"));
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int n = parseInt(sc.nextLine());
        int c = 0;
        while (c++ < n) {
            String[] line = sc.nextLine().split(" ");
            int r = parseInt(line[0]);
            int col = parseInt(line[1]);
            char[][] maze = new char[r][c];
            int cc = 0;
            int startRow = 0, startCol = 0;
            int endRow = 0, endCol = 0;
            while (cc < r) {
                String sss = sc.nextLine();
                maze[cc] = sss.toCharArray();
                if (sss.contains("X")) {
                    startRow = cc;
                    startCol = sss.indexOf("X");
                }
                if (sss.contains("@")) {
                    endRow = cc;
                    endCol = sss.indexOf("@");
                }
                cc++;
            }
            boolean memo[][] = new boolean[r][col];
            memo[startRow][startCol] = true;
            Queue<ArrayList<Node>> q = new LinkedList<ArrayList<Node>>();
            ArrayList<Node> al = new ArrayList<Node>();
            al.add(new Node(startRow, startCol));
            q.add(al);
            int[] di = {-1, 0, 1, 0, 1, -1,1,-1}; //urld
            int[] dj = {0, 1, 0, -1, 1, 1,-1,-1};
            String res = "FML";
//            printm(maze);
//            System.err.println(startRow+" "+startCol);
            while (!q.isEmpty()) {
                ArrayList<Node> l = q.poll();
                Node p = l.get(l.size() - 1);
                if (p.x == endRow && p.y == endCol) {
                    res = (l.size() - 1) + "";
                    break;
                }
                for (int i = 0; i < di.length; i++) {
                    int X = p.x + di[i];
                    int Y = p.y + dj[i];
                    if (X >= 0 && X < memo.length && Y >= 0 && Y < memo[X].length && !memo[X][Y] && maze[X][Y] != '#') {
                        memo[X][Y] = true;
                        ArrayList<Node> wl = new ArrayList<Node>(l);
                        wl.add(new Node(X, Y));
                        q.add(wl);
                    }
                }
            }
            System.err.println(res);
            pw.println("Caso #" + (c) + ": " + res);
        }
        pw.close();
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
