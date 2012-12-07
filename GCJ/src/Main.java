
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int cc = 0;
        for (int i = 2; i < n; i++) {
            int nn = n;
            int r = 0;
            while (nn / i >= 1) {
                cc += nn % i;
                nn /= i;
            }
            cc += nn % i;
        }
        System.out.println(cc + "/" + (n - 2));
    }
}
