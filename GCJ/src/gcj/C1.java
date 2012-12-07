package gcj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import static java.lang.Integer.*;


import java.util.Scanner;

public class C1 {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner sc = new Scanner(new File("C:C-large.in"));
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int nn = Integer.parseInt(sc.nextLine());
        int c = 0;
        int[] pri = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        while (c++ < nn) {
            String[] aa = sc.nextLine().split(" ");
            BigInteger[] bg = new BigInteger[aa.length];
            for (int i = 0; i < bg.length; i++) {
                bg[i] = new BigInteger(aa[i]);
            }
            BigInteger res = BigInteger.ONE;
            int pric = 0;
            int ndiv = 1;
            int ix = 0;
            while (ix < pri.length) {
                ndiv = 0;
                int nOnes = 0;
                for (int i = 0; i < bg.length; i++) {
                    if (!bg[i].equals(BigInteger.ONE)) {
                        if (bg[i].mod(new BigInteger(pri[ix] + "")).equals(BigInteger.ZERO)) {
                            bg[i] = bg[i].divide(new BigInteger(pri[ix] + ""));
                            ndiv++;
                        }
                    } else {
                        nOnes++;
                    }
                }
                res = res.multiply(new BigInteger(pri[ix] + ""));
                if(nOnes==bg.length){
                    break;
                }
                if (ndiv == 0) {
                    ix++;
                }                
            }
            String rres = "Case #" + c + ": " + res;
            System.err.println(rres);
            pw.println(rres);
        }
        pw.close();
    }
}
