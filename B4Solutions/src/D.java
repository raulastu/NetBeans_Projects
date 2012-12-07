
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

public class D {

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(new File("C:/in.txt"));
        PrintWriter pw = new PrintWriter(new File("C:/out.txt"));
        int n = parseInt(sc.nextLine());
        int c = 0;
        while(c++<n){
            String line = sc.nextLine();
            String res = "";
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                int d=0;
                if(ch>='A' && ch<='M')d = 13;
                if(ch>='N' && ch<='Z')d = 'A'-'N';//lo mismo que 13
                if(ch>='a' && ch<='m')d = +13;
                if(ch>='n' && ch<='z')d = -13;
                if(ch>='0' && ch<='4')d = 5;
                if(ch>='5' && ch<='9')d = -5;
                res+=((char)(ch+d));
            }
            System.err.println(res);
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
