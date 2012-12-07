package round1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Integer.*;

public class BB {

    static int genSubSets(ArrayList al) {
        int c = 0;
        int len = al.size();
//        int n = ;
        for (int i = 0; i < (1 << al.size()); i++) {
            ArrayList subset = new ArrayList();
            for (int j = 0; j < al.size(); j++) {
                if ((i & (1 << j)) > 0) {
                    subset.add(al.get(j));
                }
            }
            c++;
        }
        return c;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:C-large-practice.in"));
        PrintWriter pw = new PrintWriter(new File("C:/C-out.txt"));
        int ncases = parseInt(sc.nextLine());
        for (int i = 0; i < ncases; i++) {
            int res = 0;
            String [] s = sc.nextLine().split("");
            ArrayList<Integer> al = new ArrayList<Integer>();
            for (int j = 0; j < s.length; j++) {
                al.add(parseInt(s[j]));
            }
            genSubSets(al);
//            String s = "Case #" + (i + 1) + ": " + res;
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
