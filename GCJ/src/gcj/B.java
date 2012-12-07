/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gcj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Integer.*;

/**
 *
 * @author rC
 */
public class B {

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
    }

    static class Arr extends ArrayList<Node> {

        String lab = "-1";

        public Arr(Arr a, int dec) {
            super(a);
        }

        public Arr() {
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    static void flood(int x, int y, int[][] mat, char[][] res, boolean[][] memo, Arr list) {
        list.add(new Node(x, y));
        int XX = x, YY = y;
        int min = mat[x][y];
        for (int i = 0; i < di.length; i++) {
            int X = x + di[i];
            int Y = y + dj[i];
            if (X >= 0 && X < mat.length && Y >= 0 && Y < mat[X].length) {
                if (mat[X][Y] < min) {
                    min = mat[X][Y];
                    XX = X;
                    YY = Y;
                }
            }
        }
        if (XX == x && YY == y) {
            list.lab = "sink";
        } else {
            if (memo[XX][YY]) {
                list.lab = res[XX][YY] + "";
            } else {
//            memo[XX][YY] = true;
                flood(XX, YY, mat, res, memo, list);
            }
        }
    }
    static int[] di = {-1, 0, 0, 1}; //urdl
    static int[] dj = {0, -1, 1, 0};

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/B-large.in"));
        PrintWriter pw = new PrintWriter(new File("C:/B-Large-out.txt"));
        int ncases = parseInt(sc.nextLine());
        for (int i = 0; i < ncases; i++) {
            String[] dim = sc.nextLine().split(" ");
            int n = parseInt(dim[0]);
            int m = parseInt(dim[1]);
            int[][] mat = new int[n][m];
            char[][] res = new char[n][m];
            boolean[][] memo = new boolean[n][m];
            for (int j = 0; j < n; j++) {
                String[] line = sc.nextLine().split(" ");
                for (int k = 0; k < line.length; k++) {
                    mat[j][k] = parseInt(line[k]);
                }
            }
//            print(mat);
            char label = 'a';
            for (int j = 0; j < mat.length; j++) {
                for (int k = 0; k < mat[j].length; k++) {
                    if (!memo[j][k]) {
                        Arr list = new Arr();
                        flood(j, k, mat, res, memo, list);
//                        System.err.println(list);
                        if (list.lab.equals("sink")) {
                            for (Node object : list) {
                                memo[object.x][object.y] = true;
                                res[object.x][object.y] = (char) label;
                            }
                            label = (char) (label + 1);
                        } else {
                            for (Node object : list) {
                                memo[object.x][object.y] = true;
                                res[object.x][object.y] = list.lab.charAt(0);
                            }
                        }
                    }
                }
            }
//            printm(res);
            pw.println("Case #" + (i + 1) + ":");
            for (int j = 0; j < mat.length; j++) {
                String lineres = "";
                for (int k = 0; k < mat[j].length; k++) {
                    lineres += res[j][k] + " ";
                }
                pw.println(lineres.substring(0, lineres.length() - 1));
            }
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
}
