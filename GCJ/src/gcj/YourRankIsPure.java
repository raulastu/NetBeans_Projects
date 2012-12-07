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
import sun.misc.Sort;

public class YourRankIsPure {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner sc = new Scanner(new File("C:C-small-practice.in"));
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int nn = Integer.parseInt(sc.nextLine());
        int c = 0;
        int[] memos = new int[26];
        Arrays.fill(memos, -1);
        while (c++ < nn) {
            int n = parseInt(sc.nextLine());
            n = n - 2;
            int r = 0;
            if (memos[n] != -1) {
                r = memos[n];
            } else {
                for (int i = 0; i < (1 << n); i++) {
                    ArrayList<Integer> al = new ArrayList<Integer>();
                    for (int j = 0; j < n; j++) {
                        if ((i & (1 << j)) > 0) {
                            al.add(j + 2);
                        }
                    }
//                print(al);
                    Integer[] axl = al.toArray(new Integer[al.size()]);
                    if (isok(axl, 1, n + 1)) {
//                        print(axl);
                        r += 1;
                        r = r % 100003;
                    }
                }
                memos[n]=r;
            }
            String res = "Case #" + c + ": " + r;
            System.err.println(res);
            pw.println(res);
        }
        pw.close();
    }

    static boolean isok(Integer[] cx, int i, int x) {
        if (i - 1 == cx.length) {
            return true;
        } else if (i - 1 >= cx.length) {
            return false;
        }
        return isok(cx, cx[i - 1], x);
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
