package gcj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import static java.lang.Integer.*;


import java.util.Scanner;

public class C11 {

    static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) throws FileNotFoundException {
//        System.err.println(10 % 5);
//        System.err.println(2 ^ 26);
        int[] v = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int n = 5;
        for (int mask = 0; mask < 1 << n; mask++) {
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    System.err.print(v[i]+" ");
                }
            }
            System.err.println("");
        }
        if (true) {
            return;
        }
//        Scanner sc = new Scanner(new File("C:C-large.in"));
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int nn = Integer.parseInt(sc.nextLine());
        int c = 0;
        int[] pri = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        while (c++ < nn) {
            String[] aa = sc.nextLine().split(" ");
            int[] ia = new int[aa.length - 1];
            for (int i = 1; i < aa.length; i++) {
                ia[i - 1] = parseInt(aa[i]);
            }
            Arrays.sort(ia);
            long d = ia[1] - ia[0];
            for (int i = 1; i < ia.length - 1; i++) {
                d = gcd(d, ia[i + 1] - ia[i]);
                System.err.println("d" + d);
            }
            long r = ia[1] % d;
            System.err.println(r);
            long res = 0;
            if (r != 0) {
                res = d - r;
            }
            String rres = "Case #" + c + ": " + res;
            System.err.println(rres);
            pw.println(rres);
        }
        pw.close();
    }
}
