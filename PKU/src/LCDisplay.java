
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

public class LCDisplay {

    public static void top(char x[][], int mark, int size) {
        for (int j = mark + 1; j <= mark + size; j++) {
            x[0][j] = '-';
        }
    }

    public static void bot(char x[][], int mark, int size) {
        for (int j = mark + 1; j <= size + mark; j++) {
            x[x.length - 1][j] = '-';
        }
    }

    public static void mid(char x[][], int mark, int size) {
        for (int j = mark + 1; j <= size + mark; j++) {
            x[x.length / 2][j] = '-';
        }
    }

    public static void f1(char x[][], int mark, int size) {
        for (int j = 1; j < x.length / 2; j++) {
            x[j][mark] = '|';
        }
    }

    public static void f2(char x[][], int mark, int size) {
        for (int j = 1; j < x.length / 2; j++) {
            x[j][mark + size + 1] = '|';
        }
    }

    public static void f3(char x[][], int mark, int size) {
        for (int j = x.length / 2 + 1; j < x.length - 1; j++) {
            x[j][mark] = '|';
        }
    }

    public static void f4(char x[][], int mark, int size) {
        for (int j = x.length / 2 + 1; j < x.length - 1; j++) {
            x[j][mark + size + 1] = '|';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;
        while (!(line = sc.nextLine()).equals("0 0")) {
            String[] ss = line.split(" ");
            int s = parseInt(ss[0]);
            String n = ss[1];
            char[][] x = new char[(s * 2 + 3)][(s + 2) * n.length()];
            for (int i = 0; i < x.length; i++) {
                fill(x[i], ' ');
            }
            int smark = 0;
            for (int i = 0; i < n.length(); i++) {
                char ch = n.charAt(i);
                switch( ch ){
                    case '1':
                        f2(x, i * (s + 2), s);
                        f4(x, i * (s + 2), s);
                        break;
                    case '2':
                        top(x, i * (s + 2), s);
                        mid(x, i * (s + 2), s);
                        bot(x, i * (s + 2), s);
                        f2(x, i * (s + 2), s);
                        f3(x, i * (s + 2), s);
                        break;
                    case '3':
                        top(x, i * (s + 2), s);
                        mid(x, i * (s + 2), s);
                        bot(x, i * (s + 2), s);
                        f2(x, i * (s + 2), s);
                        f4(x, i * (s + 2), s);
                        break;
                    case '4':
                        f1(x, i * (s + 2), s);
                        f2(x, i * (s + 2), s);
                        mid(x, i * (s + 2), s);
                        f4(x, i * (s + 2), s);
                        break;
                    case '5':
                        top(x, i * (s + 2), s);
                        mid(x, i * (s + 2), s);
                        bot(x, i * (s + 2), s);
                        f1(x, i * (s + 2), s);
                        f4(x, i * (s + 2), s);
                        break;
                    case '6':
                        top(x, i * (s + 2), s);
                        mid(x, i * (s + 2), s);
                        bot(x, i * (s + 2), s);
                        f1(x, i * (s + 2), s);
                        f3(x, i * (s + 2), s);
                        f4(x, i * (s + 2), s);
                        break;
                    case '7':
                        top(x, i * (s + 2), s);
                        f2(x, i * (s + 2), s);
                        f4(x, i * (s + 2), s);
                        break;
                    case '8':
                        top(x, i * (s + 2), s);
                        mid(x, i * (s + 2), s);
                        bot(x, i * (s + 2), s);
                        f1(x, i * (s + 2), s);
                        f2(x, i * (s + 2), s);
                        f3(x, i * (s + 2), s);
                        f4(x, i * (s + 2), s);
                        break;
                    case '9':
                        top(x, i * (s + 2), s);
                        mid(x, i * (s + 2), s);
                        bot(x, i * (s + 2), s);
                        f1(x, i * (s + 2), s);
                        f2(x, i * (s + 2), s);
                        f4(x, i * (s + 2), s);
                        break;
                    case '0':
                        top(x, i * (s + 2), s);
                        bot(x, i * (s + 2), s);
                        f1(x, i * (s + 2), s);
                        f2(x, i * (s + 2), s);
                        f3(x, i * (s + 2), s);
                        f4(x, i * (s + 2), s);
                        break;
                }
            }
            for (int i = 0; i < x.length; i++) {
                String res = "";
                for (int j = 0; j < x[i].length; j++) {
                    if (j % (2 + s) == 0 && j != 0 && j != x[i].length) {
                        res += " ";
                    }
                    res += x[i][j];
                }
                System.out.println(res);
            }
            System.out.println();
        }
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
