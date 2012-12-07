
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

public class B {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:/in.txt"));
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int n = parseInt(sc.nextLine());
        int c = 0;
        while (c++ < n) {
            String line = sc.nextLine();
            String num = "";
            String res = "";
            for (int i = 0; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i))) { // si es dijito voy acumulando en el string num
                    num += line.charAt(i);
                } else {
                    if (num.length() > 2) { // si el num acumulado tiene mas de dos digitos obvio TOO LONG
                        res = "TOO LONG";
                        break;
                    } else if (num.length() == 0) { // si no hay nada acumulado agrego el char nomas
                        res += line.charAt(i);
                    } else { // en caso num sea 1 o 2 de length.
                        for (int j = 0; j < parseInt(num); j++) {
                            res += line.charAt(i);
                        }
                    }
                    if (res.length() > 50) { // despues que agrega veo si el string resultante excede los 50
                        res = "TOO LONG";
                        break;
                    }
                    num = ""; //reseteo mi acumulador
                }
            }
            String ss = "Caso #" + (c) + ": " + res;
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
