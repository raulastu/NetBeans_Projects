
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

public class A {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:/B-ViejitosEnCola.in"));
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int n = parseInt(sc.nextLine());
        int c = 0;
        while (c++ < n) {
            String line[] = sc.nextLine().split(" ");
            int max = 0;
            int i=0;
            for (int j = 0; j < line.length; j++) {
                if(max<parseInt(line[j])){
                    i=j;max=parseInt(line[j]);
                }
            }

            String rr = "Caso #" + (c) + ": " + i;
            System.err.println(rr);
            pw.println(rr);
        }
        pw.close();
    }

    private static void print(Object... rs) {
        System.err.println(deepToString(rs));
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
