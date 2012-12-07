
import java.math.BigDecimal;
import java.util.Scanner;

public class Exponentiation {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            BigDecimal a = sc.nextBigDecimal();
            int b = sc.nextInt();
            System.out.println(a.pow(b));
        }
    }
}
