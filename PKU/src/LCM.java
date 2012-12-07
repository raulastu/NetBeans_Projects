
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rcuser
 */
public class LCM {

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine());
        long[] m = new long[1000001];
        System.err.println(lcm(1,6));
        System.err.println(lcm(2,6));
        System.err.println(lcm(3,6));
        System.err.println(lcm(4,6));
        System.err.println(lcm(5,6));
        System.err.println(lcm(6,6));
//        for (int j = 1; j <= 1000000; j++) {
//            m[j] = lcm(j, x);
//        }
//        for (int i = 2; i < n; i++) {
//            int x = Integer.parseInt(br.readLine());
//            long res = 0;
//            for (int j = 0; j <= x; j++) {
//                x[j] = j;
//            }
//            System.out.println(res);
//        }
    }
}
