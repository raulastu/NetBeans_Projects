package gcj;

// BEGIN CUT HERE
// END CUT HERE
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.*;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

public class TheProduct {

    static String fileName = "C:/A";
    Scanner sc;
    PrintWriter pw;

    public void solve() throws Exception {
        sc = new Scanner(new File(fileName + ".in"));
//        pw = new PrintWriter(new File(fileName + ".sol"));
        boolean[][] a = new boolean[10][10];
        String aa[] = {"xxd", "aax", "aaa"};
        Integer[][] dp = {{1, 2, 3}, {1, 2, 33}, {2, 2, 333}};
        dp[1][1]=1;
        print("XX", "xxx", aa, a);
        printm(aa);
//        printm(dp);
    }

    public static void main(String[] args) throws Exception {
        new TheProduct().solve();
        // BEGIN CUT HERE
        StringBuffer comment = new StringBuffer();
        System.err.println(
                CompareTwoFiles2.sonIguales(new Scanner((new File("C:sample.sol"))),
                new Scanner((new File(fileName + ".sol"))), comment));
        System.err.println(comment);
    // END CUT HERE
    }
// BEGIN CUT HERE

    private static void print(Object... rs) {
        System.err.println(deepToString(rs));
    }

    private static void printm(Object... rs) {
        for (int i = 0; i < rs.length; i++) {
            String x = deepToString((Object[]) rs[i]);
            System.err.println(x);
        }
    }

//    private static void printm(String[] a) {
//        for (int i = 0; i < a.length; i++) {
//            System.err.println("[" + a[i] + "]");
//        }
//    }
//
//    private static void printm(char[][] a) {
//        for (int i = 0; i < a.length; i++) {
//            System.err.println("[" + new String(a[i]) + "]");
//        }
//    }
// END CUT HERE
    }
