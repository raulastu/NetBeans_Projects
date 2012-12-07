/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huahcoding_util;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class ContarVocales {

    public ContarVocales() {
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:/ContarVocales.in"));
        PrintWriter pw = new PrintWriter(new File("C:/contarvocales.sol"));
        int s  = Integer.parseInt(sc.nextLine());
        int c=0;
        char []a ={'a','e','i','o','u','A','E','I','O','U'};
        while(sc.hasNextLine()){
            c++;
            String line = sc.nextLine();
            int res = 0;
            for (char d : line.toCharArray()) {
                for (int i = 0; i < a.length; i++) {
                    if(a[i]==d){
                        res++;break;
                    }
                }
            }
            String r = "Caso #"+c+": "+res;
            pw.println(r);
        }
        System.err.println(c);
        pw.close();
    }
}
