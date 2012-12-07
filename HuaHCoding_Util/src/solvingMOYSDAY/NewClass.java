/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solvingMOYSDAY;

import solving_HuahCodingAforo.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class NewClass {

    public static void main(String[] args) throws Exception {
        String e = "aaaaaaaaaaedsfsasddfeeeeeeaaaaaaaaa";
        String ee = e.replaceAll("([^aeiouAEIOU][aeiouAEIOU][^aeiouAEIOU])", "");
        System.err.println(ee);

        if (true) {
            return;
        }

        Scanner sc = new Scanner(new File("C:/MoysDay.in"));
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int c = Integer.parseInt(sc.nextLine());
        int nC = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] moylife = line.split(" ");
            int xc = 0;
            for (int i = 0; i < moylife.length; i++) {
                if (moylife[i].equals("JEEZ") || moylife[i].equals("HON") || moylife[i].equals("POKER")) {
                    xc++;
                }
            }
            nC++;
            String res = "Caso #" + nC + ": " + (xc != 0 ? xc : "FML");
            pw.println(res);
        }
        System.err.println(nC);
        pw.close();
    }

    private static void print(Object... rs) {
        System.err.println(Arrays.deepToString(rs));
    }
}
