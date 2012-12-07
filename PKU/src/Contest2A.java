
import java.math.BigInteger;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class Contest2A {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ii = 0;
        BigInteger[] bi = new BigInteger[101];
        fill(bi, BigInteger.ZERO);
        bi[0] = BigInteger.ONE;
        bi[1] = BigInteger.ONE;
        bi[2] = new BigInteger("2");
        BigInteger two = new BigInteger("2");
        for (int i = 3; i < bi.length; i++) {
            BigInteger omg = BigInteger.ZERO;
            for (int j = i - 1; j >= i / 2; j--) {
                int dif = (i - 1) - j;
                BigInteger addbi = BigInteger.ZERO;
                if (dif * 2 == i - 1) {
                    addbi = bi[j].multiply(bi[dif]);
                } else {
                    addbi = bi[j].multiply(two).multiply(bi[dif]);
                }
                omg = omg.add(addbi);
            }
            bi[i] = omg;
        }
        while ((ii = sc.nextInt()) != -1) {
            System.out.println(bi[ii]);
        }
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
