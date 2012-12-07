/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solving_HuahCodingAforo;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class NewClass {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:/in.txt"));
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int c = Integer.parseInt(sc.nextLine());
        int nC = 0;
        while (nC++ < c) {
            String line = sc.nextLine();
            String[] dependencies = line.substring(1, line.length() - 1).split("\\,",30);
            print(dependencies);
            int[] r = new GalaxyTrip().possibleValues(dependencies);
            String res = "Caso #"+nC+": ";
            for (int i = 0; i < r.length; i++) {
                res+=r[i]+" ";
            }
            res=res.trim();
            pw.println(res);
        }
pw.close();
    }

    private static void print(Object... rs) {
        System.err.println(Arrays.deepToString(rs));
    }
    static int[] co;
    static private int[][] memo2 = new int[1000][31];

    static int coins(int i, int g) {
        if (g == 0) {
            return 1;
        }
        if (i >= co.length || g < 0) {
            return 0;
        }
        if (memo2[i][g] != -1) {
            return memo2[i][g];
        }

        int r = (coins(i + 1, g - co[i]) + coins(i + 1, g));
        memo2[i][g] = r;
        return r;
    }
    static String deps[];
    static boolean[] memo = new boolean[31];

    static int getCoins(int i) {
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
            r += getCoins(Integer.parseInt(s[j]));
        }
        return r;
    }
}
