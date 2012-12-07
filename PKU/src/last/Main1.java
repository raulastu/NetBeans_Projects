package last;


import .*;
import java.util.Scanner;

public class Main1 {

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int n = Integer.parseInt(sc.nextLine());
        int c = 0;
        while (true) {
//            int nrows = Integer.parseInt(sc.nextLine().trim());
            String[] four = sc.nextLine().trim().split(" ");
            int X1 = Integer.parseInt(four[0]);
            int Y1 = Integer.parseInt(four[1]);
            int X2 = Integer.parseInt(four[2]);
            int Y2 = Integer.parseInt(four[3]);
            if (X1 + Y1 + X2 + Y2 == 0) {
                break;
            }
            int a1 = flood(X1, Y1, X2, Y2);
            int a2 = flood(X1, Y1, Y2, X2);
            int a3 = flood(Y1, X1, X2, Y2);
            int a4 = flood(Y1, X1, Y2, X2);
            int min = Math.max(a1, Math.max(a2, Math.max(a3, a4)));
            if (min >= 100) {
                min = 100;
            }
            System.out.println(min + "%");


//            System.out.println("%" + min);

//            System.out.println(max);
        }
        sc.close();
    }

    public static int flood(int X1, int Y1, int X2, int Y2) {
        double f1 = (X2 / (double) X1);
        int p = 1;
        if (f1 * Y1 <= Y2) {
            p = (int) Math.floor(f1 * 100);
        }
        return p;
    }
}
