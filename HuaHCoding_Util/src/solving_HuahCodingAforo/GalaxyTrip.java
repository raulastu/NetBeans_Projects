package solving_HuahCodingAforo;


import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;
import java.util.*;

public class GalaxyTrip {

    public int[] possibleValues(String[] dependencies) {
        deps = dependencies;
        ArrayList<Integer> s = new ArrayList<Integer>();
        int[] m = new int[31];
        for (int i = 0; i < m.length; i++) {
            m[i] = i;
        }
        for (int i = 0; i < dependencies.length; i++) {
            int g = getCoins(i);
            if (g != 0) {
                s.add(g);
            }
        }
        int[] coins = new int[s.size()];
        sort(s);
//        print(s);
        int c = 0;
        for (Integer i : s) {
            coins[c++] = i;
        }
        co = coins;
        ArrayList<Integer> res = new ArrayList<Integer>();

        for (int i = 0; i < memo2.length; i++) {
            fill(memo2[i], -1);
        }
        for (int i = 1; i < 31; i++) {
            if (coins(0, i) >= 1) {
                res.add(i);
            }
        }
        int[] rres = new int[res.size()];
        for (int i = 0; i < rres.length; i++) {
            rres[i] = res.get(i);
        }
//        print(res);
        return rres;
    }
    int[] co;
    private int[][] memo2 = new int[1000][31];

    int coins(int i, int g) {
        if (g == 0) {
            return 1;
        }
        if (i >= co.length || g < 0) {
            return 0;
        }
        if (memo2[i][g] != -1) {
            return memo2[i][g];
        }

        int r = (coins(i + 1, g - co[i])+ coins(i + 1, g));
        memo2[i][g] = r;
        return r;
    }

    String deps[];
    boolean[] memo = new boolean[31];

    int getCoins(int i) {
        if (memo[i]) {
            return 0;
        }
        memo[i] = true;
        if (deps[i].equals("")) {
            return 1;
        }
        String[] s = deps[i].split(" ");
        int r = 1;
        for (int j = 0; j < s.length; j++) {
            r += getCoins(parseInt(s[j]));
        }
        return r;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        try {
            eq(0, (new GalaxyTrip()).possibleValues(new String[]{"1 2", "0", "0", ""}), new int[]{1, 3, 4});
            eq(1, (new GalaxyTrip()).possibleValues(new String[]{"1 2", "0 2", "0 1"}), new int[]{3});
            eq(2, (new GalaxyTrip()).possibleValues(new String[]{"", "", "", ""}), new int[]{1, 2, 3, 4});
            eq(3, (new GalaxyTrip()).possibleValues(new String[]{"4 2", "3", "0 4", "1", "0 2", "6", "5"}), new int[]{2, 3, 4, 5, 7});
        } catch (Exception exx) {
            System.err.println(exx);
            exx.printStackTrace(System.err);
        }
    }

    private static void eq(int n, int a, int b) {
        if (a == b) {
            System.err.println("Case " + n + " passed.");
        } else {
            System.err.println("Case " + n + " failed: " + received + " " + a + " " + expected + " " + b + ".");
        }
    }

    private static void eq(int n, double a, double b) {
        if (a == b) {
            System.err.println("Case " + n + " passed.");
        } else {
            System.err.println("Case " + n + " failed: " + received + " '" + a + "' " + expected + " '" + b + "'.");
        }
    }

    private static void eq(int n, char a, char b) {
        if (a == b) {
            System.err.println("Case " + n + " passed.");
        } else {
            System.err.println("Case " + n + " failed: " + received + " '" + a + "' " + expected + " '" + b + "'.");
        }
    }

    private static void eq(int n, long a, long b) {
        if (a == b) {
            System.err.println("Case " + n + " passed.");
        } else {
            System.err.println("Case " + n + " failed: " + received + " '" + a + "L' " + expected + " '" + b + "L'.");
        }
    }

    private static void eq(int n, boolean a, boolean b) {
        if (a == b) {
            System.err.println("Case " + n + " passed.");
        } else {
            System.err.println("Case " + n + " failed: " + received + " '" + a + "' " + expected + " '" + b + "'.");
        }
    }

    private static void eq(int n, String a, String b) {
        if (a != null && a.equals(b)) {
            System.err.println("Case " + n + " passed.");
        } else {
            System.err.println("Case " + n + " failed: " + received + " \"" + a + "\" " + expected + " \"" + b + "\".");
        }
    }

    private static void eq(int n, int[] a, int[] b) {
        if (a.length != b.length) {
            System.err.println("Case " + n + " failed: different lengths");
            print(received + "<" + a.length + "> ", a);
            print(expected + "<" + b.length + "> ", b);
            return;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                System.err.println("Case " + n + " failed. " + received + " and " + expected + " array differ in position " + i);
                print(received + " ", a);
                print(expected + " ", b);
                return;
            }
        }
        System.err.println("Case " + n + " passed.");
    }

    private static void eq(int n, long[] a, long[] b) {
        if (a.length != b.length) {
            System.err.println("Case " + n + " failed: different lengths");
            print(received + "<" + a.length + "> ", a);
            print(expected + "<" + b.length + "> ", b);
            return;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                System.err.println("Case " + n + " failed. " + received + " and " + expected + " array differ in position " + i);
                print(received, a);
                print(expected, b);
                return;
            }
        }
        System.err.println("Case " + n + " passed.");
    }

    private static void eq(int n, double[] a, double[] b) {
        if (a.length != b.length) {
            System.err.println("Case " + n + " failed: different lengths");
            print(received + "<" + a.length + "> ", a);
            print(expected + "<" + b.length + "> ", b);
            return;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                System.err.println("Case " + n + " failed. " + received + " and " + expected + " array differ in position " + i);
                print(received, a);
                print(expected, b);
                return;
            }
        }
        System.err.println("Case " + n + " passed.");
    }

    private static void eq(int n, String[] a, String[] b) {
        if (a.length != b.length) {
            System.err.println("Case " + n + " failed: different lengths");
            print(received + "<" + a.length + "> ", (Object[]) a);
            print(expected + "<" + b.length + "> ", (Object[]) b);
            return;
        }
        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) {
                System.err.println("Case " + n + " failed. " + received + " and " + expected + " array differ in position " + i);
                print(received, (Object[]) a);
                print(expected, (Object[]) b);
                return;
            }
        }
        System.err.println("Case " + n + " passed.");
    }

    private static void print(Object... rs) {
        System.err.println(Arrays.deepToString(rs));
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
    static String expected = "  expe";
    static String received = "  rChi";
// END CUT HERE
}
