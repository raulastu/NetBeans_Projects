
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

public class Fuuusion {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:/A-Fuuusion.in"));
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int n = parseInt(sc.nextLine());
        int c = 0;
        while (c++ < n) {
            String[] s = sc.nextLine().trim().split(" ");
            int a = parseInt(s[0]);
            int b = parseInt(s[1]);
            int cd = parseInt(s[2]);
            int d = parseInt(s[3]);
            String res = max(a+b,cd+d)+"";
            String rr = "Caso #" + (c) + ": " + res;
            System.err.println(rr);
            pw.println(rr);
        }
        pw.close();
    }

    private static void print(Object... rs) {
        String x = deepToString(rs);
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
