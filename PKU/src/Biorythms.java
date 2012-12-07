
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Biorythms {

    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = 0;
        while (true) {
            x++;
            String s = br.readLine();
            if (s.trim().equals("-1 -1 -1 -1")) {
                break;
            }
            String[] nums = s.split(" ");
            int a = Integer.parseInt(nums[0]);
            int b = Integer.parseInt(nums[1]);
            int c = Integer.parseInt(nums[2]);
            int d = Integer.parseInt(nums[3]);
            int i;
            for (i = Math.max(a, Math.max(b, c)) + 1; true; i++) {
                if ((i - a) % 23 == 0 && (i - b) % 28 == 0 && (i - c) % 33 == 0) {
                    System.out.println("Case " + x + ": the next triple peak occurs in " + (i - d) + " days.");
                    break;
                }
            }
        }
//        System.out.println(new LostParentheses().minResult(s));
//        System.out.println(new LostParentheses().minResult("55-50+40"));
//        System.err.println(new LostParentheses().minResult("10+20+30+40"));
//        System.err.println(new LostParentheses().minResult("00009-00009"));
    }
}
