
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

public class C {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:/in.txt"));
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int n = parseInt(sc.nextLine());
        int c = 0;
        while (c++ < n) {
            String[] line = sc.nextLine().split("\\}\\{");
            String[] a = line[0].substring(1).split(" ");
            String[] b = line[1].substring(0, line[1].length() - 1).split(" ");
            ArrayList<Integer> al = new ArrayList<Integer>();
            for (int i = 0; i < b.length; i++) {
                int x= parseInt(a[i]);
                int y= parseInt(b[i]);
                if(x>=20 && x<=39 && y>=50 && y<=99 )
                    al.add(i);
            }
            String res ="";
            for (int i = 0; i < al.size(); i++) {
                res+=al.get(i)+" ";
            }
            String ss = "Caso #" + (c) + ": " + res.trim();
            System.err.println(ss);
            pw.println(ss);
        }
        pw.close();
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
