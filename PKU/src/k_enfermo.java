
import java.io.File;
import java.io.FileNotFoundException;
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

public class k_enfermo {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:/in.txt"));
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));

        int nroCasos = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < nroCasos; i++) {
            pw.println("Caso #" + (i + 1) + ": " + contar_dota(sc.nextLine()));
        }
        pw.close();
    }

    public static int contar_dota(String palabra) {
        int contador = 1;
        return contador;
    }

    private static void print(Object... rs) {
        print("", rs);
       String[] arg={"",""};
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
