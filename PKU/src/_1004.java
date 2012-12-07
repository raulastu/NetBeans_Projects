
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class _1004 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = parseInt(sc.nextLine());
        int c = 0;
        double[] radious = new double[1000];
        for (int i = 0; i < radious.length; i++) {
            radious[i] = sqrt((i + 1) * 100 / PI);
        }
        while (c++ < n) {
            String[] two = sc.nextLine().split(" ");
            double x = parseDouble(two[0]);
            double y = parseDouble(two[1]);
            double rad = sqrt(x * x + y * y);
            for (int i = 0; i < radious.length; i++) {
                if (rad <= radious[i]) {
                    System.out.println("Property " + c + ": This property will begin eroding in year " + (i + 1) + ".");
                    break;
                }
            }
        }
        System.out.println("END OF OUTPUT.");

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
