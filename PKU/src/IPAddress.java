
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class IPAddress {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = 0;
        while (c++ < n) {
            String s = sc.next();
            String res = "";
            for (int i = 0; i < 8 * 4; i += 8) {
                String oc = s.substring(i, i + 8);
                res += Integer.valueOf(oc, 2) + ".";
            }
            System.out.println(res.substring(0, res.length() - 1));
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
