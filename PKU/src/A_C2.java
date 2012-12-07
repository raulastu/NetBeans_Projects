
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class A_C2 {

    public static void main(String[] args) {
        String s = "V\n" +
                "AVV\n";
        Scanner sc = new Scanner(s);

        while (sc.hasNext()) {
            String line = sc.next();
            System.out.println("300 420 moveto");
            System.out.println("310 420 lineto");
            int x = 310;
            int y = 420;
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (i % 2 == 0) {
                    if (c == 'A') {
                        y -= 10;
                    } else {
                        y += 10;
                    }
                } else {
                    x += 10;
                }
                System.out.println(x + " " + y + " lineto");
            }
            System.out.println("stroke");
            System.out.println("showpage");
        }
        sc.close();
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
