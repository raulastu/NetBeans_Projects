
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LostParentheses {

    public int minResult(String e) {
        String[] nums = e.split("[+-]");
        String[] signs = e.split("[0-9]+");
        int res = 0;
        boolean f = true;
        if(nums.length==1)return Integer.parseInt(nums[0]);
        for (int i = 1; i <= signs.length; i++) {
            if (f) {
                res += Integer.parseInt(nums[i - 1]);
            } else {
                res -= Integer.parseInt(nums[i - 1]);
            }
            if (i < signs.length && signs[i].equals("-")) {
                boolean isP = true;
                for (int j = i; j < nums.length && isP; j++) {
                    i++;
                    res -= Integer.parseInt(nums[j]);
                    isP = j + 1 < nums.length && signs[j + 1].equals("+");
                }
                f = isP;
            }
        }
//        print(nums);
//        print(signs);
        return res;
    }

//    private static void print(Object... rs) {
//        System.err.println(Arrays.deepToString(rs));
//    }
//
//    private static void printm(String[] a) {
//        for (int i = 0; i < a.length; i++) {
//            System.err.println("[" + a[i] + "]");
//        }
//    }
//
//    private static void printm(char[][] a) {
//        for (int i = 0; i < a.length; i++) {
//            System.err.println("[" + new String(a[i]) + "]");
//        }
//    }
    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(new LostParentheses().minResult(s));
//        System.out.println(new LostParentheses().minResult("55-50+40"));
//        System.err.println(new LostParentheses().minResult("10+20+30+40"));
//        System.err.println(new LostParentheses().minResult("00009-00009"));
    }
}
