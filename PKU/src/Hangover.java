
import java.util.Scanner;

public class Hangover {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double d = 0.00;
        while ((d = sc.nextDouble()) != 0.00) {
            double dd = 0;
            for (int i = 1; true; i++) {
                dd += 1 / (double) (i + 1);
                if (d <= dd) {
                    System.out.println(i + " card(s)");
                    break;
                }
            }
        }
    }
}
