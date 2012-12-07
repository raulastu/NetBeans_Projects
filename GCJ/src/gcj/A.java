package gcj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import static java.lang.Integer.*;


import java.util.Scanner;

public class A {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:A-large.in"));
//        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int nn = Integer.parseInt(sc.nextLine());
        int c = 0;
        while (c++ < nn) {
            String[] aa = sc.nextLine().split(" ");
            long n = Long.parseLong(aa[0]);
            long k = Long.parseLong(aa[1]);
            String s = k % Math.pow(2, n) == Math.pow(2, n) - 1 ? "ON" : "OFF";
            String res = "Case #" + (c) + ": " + s;
//            System.err.println(res);
            pw.println(res);
        }
        pw.close();
    }
}
