
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

public class C1 {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:/in.txt"));
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int n = parseInt(sc.nextLine());
        int cc = 0;
        while (cc++ < n) {
            String[] dic = sc.nextLine().split(" ");
            String word = sc.nextLine();
            int res = 0;
            int index = -1;
            for (int i = 0; i < dic.length; i++) {
                int t = 0;
                for (int j = 0; j < dic[i].length(); j++) {
                    if (dic[i].charAt(j) == word.charAt(j)) {
                        t++;
                    }
                }
                if (res < t) {
                    res = t;
                    index = i;
                }
            }
            String ss = "Caso #" + (cc) + ": " + index;
            System.err.println(ss);
            pw.println(ss);
        }
        pw.close();
    }

    static int go(int x) {
        if (x >= 50) {
            return x - 10;
        }
        return x;
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
