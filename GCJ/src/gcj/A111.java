package gcj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import static java.lang.Integer.*;


import java.util.Scanner;

public class A111 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:A-large-practice (1).in"));
//        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int nn = Integer.parseInt(sc.nextLine());
        int c = 0;
        while (c++ < nn) {
            String NM[] = sc.nextLine().split(" ");
            int N = parseInt(NM[0]);
            int M = parseInt(NM[1]);
            m = new HashSet<String>();
            for (int i = 0; i < N; i++) {
                m.add(sc.nextLine());
            }
            int r = 0;
            for (int i = 0; i < M; i++) {
                String s = sc.nextLine();
                r += go(s);
            }
            String res = "Case #" + c + ": " + r;
            System.err.println(res);
            pw.println(res);
        }
        pw.close();
    }
    static HashSet<String> m;

    private static int go(String s) {
        if (m.contains(s) || s.length() <= 1) {
            return 0;
        } else {
            m.add(s);
            String sx = s.substring(0, s.lastIndexOf("/"));
            int r = 1;
            r += go(sx);
            return r;
        }
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
