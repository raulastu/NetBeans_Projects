
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class PhoneNumbers {

    void solver(String a[]) {

        long milis = System.currentTimeMillis();
//        Scanner sc = new Scanner(System.in);
        int n = a.length;
        int i = 0;
        TreeMap<String, Integer> hm = new TreeMap<String, Integer>();
        while (++i < n) {
            String s = a[i].replace("-", "");
//            String s = sc.next().replace("-", "");
            String sx = "";
            for (int j = 0; j < s.length(); j++) {
                char x = s.charAt(j);
                if (Character.isLetter(x)) {
                    int ich = x - 'A';
                    if (ich >= 0 && ich <= 2) {
                        sx += 2;
                    } else if (ich >= 3 && ich <= 5) {
                        sx += 3;
                    } else if (ich >= 6 && ich <= 8) {
                        sx += 4;
                    } else if (ich >= 9 && ich <= 11) {
                        sx += 5;
                    } else if (ich >= 12 && ich <= 14) {
                        sx += 6;
                    } else if (ich >= 15 && ich <= 18) {
                        sx += 7;
                    } else if (ich >= 19 && ich <= 21) {
                        sx += 8;
                    } else if (ich >= 22 && ich <= 25) {
                        sx += 9;
                    }
                } else {
                    sx += x;
                }
            }
            if (hm.containsKey(sx)) {
                hm.put(sx, hm.get(sx) + 1);
            } else {
                hm.put(sx, 1);
            }
        }
        boolean moreThanOnce = false;
        for (String string : hm.keySet()) {
            int tt = hm.get(string);
            if (tt >= 2) {
                moreThanOnce = true;
                System.out.println(new StringBuilder(string + "").insert(3, '-') +
                        " " + tt);
            }
        }
        if (!moreThanOnce) {
            System.out.println("No duplicates.");
        }
        System.err.println(System.currentTimeMillis() - milis);
    }

    public static void main(String[] args) {

        String[] a = new String[100000];
        Arrays.fill(a, "");
        char[] alp = "ABCDEFGHIJLMNOPRSTUVWX".toCharArray();
        Random d = new Random();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < 7; j++) {
                a[i] += alp[d.nextInt(alp.length)];
            }
        }
        new PhoneNumbers().solver(a);
    }
}
