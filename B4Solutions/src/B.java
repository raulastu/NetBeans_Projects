
import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class B {

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(new File("C:/in.txt"));
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int n = parseInt(sc.nextLine());
        int c = 0;
        while(c++<n){
            String line = sc.nextLine();
            String res = "NO";
            if(line.length()!=1)
            for (int i = 1; i <= line.length(); i++) {
                String a = line.substring(0,i);
                String b = line.substring(i,line.length());
                int ma = 1;int mb=1;
                for (int j = 0; j < a.length(); j++) {
                    ma*=a.charAt(j)-'0';
                }
                for (int j = 0; j < b.length(); j++) {
                    mb*=b.charAt(j)-'0';
                }
                if(ma==mb)res="YES";
            }            
            pw.println("Caso #"+(c)+": "+res);
        }
        pw.close();
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
