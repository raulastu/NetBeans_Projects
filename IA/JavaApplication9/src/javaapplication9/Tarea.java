package javaapplication9;

import sun.applet.Main;

/**
 *
 * @author Tania
 */
public class Tarea {

    public Tarea() {
    }

    public void op4int(int a, int b) {
        System.out.println("SUM= " + (a + b));
        System.out.println("SUBTRACTION= " + (a - b));
        System.out.println("MULTIPLICATION= " + (a * b));
        System.out.println("DIVISION= " + (a / b));
    }

    public void op4doub(double x, double y) {

        System.out.println("SUM= " + (x + y));
        System.out.println("SUBTRACTION= " + (x - y));
        System.out.println("MULTIPLICATION= " + (x * y));
        System.out.println("DIVISION= " + (x / y));
        System.out.println("POTENCIA= " + Math.pow(x, y));
    }

    public String concat(String a, String b) {
        return a + b;
    }

    public int sum(int x, int y) {
        return x + y;
    }

    public int sm1(int s[]) {
        int m = 0;
        for (int i = 0; i < s.length; i++) {
            m = s[i] + m;
        }
        return m;
    }

    public double prom2(int e[]) {
        double p = sm1(e);
        return p / e.length;
    }

    public double prom(int e[]) {
        double p = 0;
        for (int i = 0; i < e.length; i++) {
            p = e[i] + p;
        }
        return p / e.length;
    }

    public int max(int a[]) {
        int max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    public int min(int a[]) {
        int min = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            }
        }
        return min;
    }

    public void r(int t[]) {
        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i]);
        }
        System.out.println();
    }

    public int[] dv(int l, int x) {
        int a[] = new int[l];
        for (int i = 0; i < a.length; i++) {
            a[i] = x;
        }
        return a;
    }

    public String stringmax(String s[]) {
        int max = s[0].length();
        String j=s[0];
        for (int i = 0; i < s.length; i++) {
            if (s[i].length() > max) {
                max = s[i].length();
                j=s[i];
            }
        }

        return j;
    }

    public static void main(String[] args) {
        Tarea main = new Tarea();

        String d[]={"fd","fdf","jjjgghfgfg","kjdd"};
        String k=main.stringmax(d);
        System.out.println(k);
        //int d[] = {1,2,8};
        //System.out.println(main.min(d));
        //main.r(d);

        /*int b[] = main.dv(5, 1);
        main.r(b);

        /*double f = main.prom2(d);
        System.out.println(f);*/

        //int t = main.sm1(d);
        //System.out.println(t);

    }
}

