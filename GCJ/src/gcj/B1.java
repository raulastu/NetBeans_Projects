package gcj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import static java.lang.Integer.*;


import java.util.Scanner;

public class B1 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:C-large.in"));
//        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int nn = Integer.parseInt(sc.nextLine());
        int c = 0;
        while (c++ < nn) {
            String[] aa = sc.nextLine().split(" ");
            long R = Long.parseLong(aa[0]);
            long k = Long.parseLong(aa[1]);            
//            long n = Long.parseLong(aa[2]);
            String[] ss = sc.nextLine().split(" ");            
            int[] g = new int[ss.length];
            int[] memo = new int[ss.length];
            int[] mc = new int[ss.length];
            Arrays.fill(memo, -1);
            for (int i = 0; i < g.length; i++) {
                g[i] = parseInt(ss[i]);
            }
            int cc = 0;
            long r = 0;
            while (R-- > 0) {
                int ac = 0;                
                if (memo[cc % g.length] != -1) {
                    ac = memo[cc % g.length];
                    cc = mc[cc % g.length];
                } else {
//                    int ct=0;
                    int ct = cc % g.length;
                    int t = 0;
                    while (ac + g[cc % g.length] <= k && t < g.length) {
                        ac += g[cc % g.length];
                        t++;
                        cc++;   
                    }
//                    cc = cc % g.length;
                    memo[ct] = ac;
                    mc[ct] = cc % g.length;
                }
                r += ac;
                
            }
            String res = "Case #" + (c) + ": " + r;
//            System.err.println(res);
            pw.println(res);
        }
        pw.close();
    }
}
