
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(sc.readLine());
        int c = 0;
        HashSet<String> set = new HashSet<String>();
        while (c++ < n) {
            set.add(sc.readLine());
        }
        TreeMap<Integer, ArrayList<String>> map = new TreeMap<Integer, ArrayList<String>>();
//        bf.
        int i = 1;
        ArrayList<String> hiset = new ArrayList<String>();
        sc.readLine();
        while (sc.ready()) {
            String s = sc.readLine();
            if (s.equals("-1")) {
                map.put(i, hiset);
                hiset = new ArrayList<String>();
                i++;
            } else {
                if (!set.contains(s)) {
                    hiset.add(s);
                }
            }
        }
        String r = "";
        for (Integer intx : map.keySet()) {
            if (map.get(intx).size() == 0) {
                String rx = "Email " + intx + " is spelled correctly.";
                System.out.println(rx);
            } else {
                String rx = "Email " + intx + " is not spelled correctly.";
                System.out.println(rx);
                for (String string : map.get(intx)) {
//                    r += string + "\n";
                    System.out.println(string);
                }
            }
        }
//        System.err.println("\n".equals(""));
//        r += ;
        System.out.println("End of output");

        sc.close();
    }
}
