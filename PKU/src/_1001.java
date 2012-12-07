
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class _1001 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String ab[] = sc.nextLine().split(" +");
            String a = ab[0];
            int n = parseInt(ab[1]);
            BigDecimal bd = new BigDecimal(a);
            String res = bd.pow(n).toPlainString().replaceAll("\\A0+", "");
            res = res.replaceAll("0+$", "");
            if (res.indexOf(".") == res.length() - 1) {
                res = res.substring(0, res.length() - 1);
            }
            System.out.println(res);
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
