package gcj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static java.lang.Integer.*;


import java.util.Scanner;

public class A1 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:A-large.in"));
        String[] ns = sc.nextLine().split(" ");
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int len = parseInt(ns[0]);
        int n = parseInt(ns[1]);
        int cases = parseInt(ns[2]);
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLine();
        }
        for (int ncase = 0; ncase < cases; ncase++) {
            String s = sc.nextLine();
            boolean[][] mm = new boolean[n][len];
            int nchar = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '(') {
                    int last = s.indexOf(")", j);
                    String likelies = s.substring(j, last);
                    for (int k = 0; k < likelies.length(); k++) {
                        char c = likelies.charAt(k);
                        for (int l = 0; l < a.length; l++) {
                            if (a[l].charAt(nchar) == c) {
                                mm[l][nchar] = true;
                            }
                        }
                    }
                    j += likelies.length();
                } else {
                    char c = s.charAt(j);
                    for (int l = 0; l < a.length; l++) {
                        if (a[l].charAt(nchar) == c) {
                            mm[l][nchar] = true;
                        }
                    }
                }
                nchar++;
            }
            int c = 0;
            for (int i = 0; i < mm.length; i++) {
                boolean gotit = true;
                for (int j = 0; j < mm[i].length; j++) {
                    if (!mm[i][j]) {
                        gotit = false;
                        break;
                    }
                }
                if (gotit) {
                    c++;
                }
            }
            String res = "Case #" + (ncase + 1) + ": " + c;
            pw.println(res);
//            System.err.println(res);
        }
        pw.close();
    }
}
