
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class Coreis_A {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = parseInt(sc.nextLine());
        int c = 0;
        int ll[] = new int[10000000];
        fill(ll, -1);
        ArrayList<Integer> al = new ArrayList<Integer>();
        ArrayList<Integer> al2 = new ArrayList<Integer>();
        while (c++ < n) {
            int nn = parseInt(sc.nextLine());
            al.add(nn);
            al2.add(nn);
        }
        sort(al, Collections.reverseOrder());
        int nn = al.get(0);
        long r = 0;
        for (int x = 0; x * x <= nn; x++) {
            for (int y = 0; x * x + y * y <= nn; y++) {
                for (int z = 0; x * x + y * y + z * z <= nn; z++) {
                    for (int w = 0; x * x + y * y + z * z + w * w <= nn; w++) {
//                        if (x * x + y * y + z * z + w * w == nn) {
//                            r++;
//                        }
                        ll[x * x + y * y + z * z + w * w]++;
                    }
                }
            }
        }
//        sort(al);
        for (int i = 0; i < al2.size(); i++) {
            System.out.println(ll[al2.get(i)] + 1);
        }

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
