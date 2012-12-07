
import java.util.Scanner;

public class ToTheMax {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] ar = new int[n][n];
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                ar[i][j] = sc.nextInt();
            }
        }
        int s = -1;
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {

                for (int k = i; k < ar.length; k++) {
                    for (int l = j; l < ar[k].length; l++) {

                        int ss = 0;
                        for (int m = i; m <= k; m++) {
                            for (int o = j; o <= l; o++) {
                                ss += ar[m][o];
                            }
                        }
                        s = Math.max(ss, s);
                    }
                }
            }
        }
        System.out.println(s);

    }
}
