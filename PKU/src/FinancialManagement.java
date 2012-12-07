
import java.util.Scanner;

public class FinancialManagement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 12;
        int c = 0;
        double dd = 0;
        while (c++ < n) {
            dd += sc.nextDouble();
        }
        dd /= n;
        int intt = (int) dd;
        double rest = dd - intt;
//        System.err.println(dd);
//        System.err.println(intt);
        rest = (Math.round((rest * 100))) / (double) 100;
//        System.err.println(rest);
        System.out.println("$" + (intt + rest));
    }
}
