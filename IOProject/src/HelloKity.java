
import java.io.*;
import java.util.*;
import java.util.regex.*;
import org.omg.CORBA.WCharSeqHelper;

public class HelloKity {

    HelloKity() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("C:/A-HelloKity.in")));
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("C:/HelloKity.out")));
        for (;;) {
            String wn = in.readLine();
            if (wn.equals(".")) {
                break;
            }
            String w = wn.split(" ")[0];
            int n = Integer.parseInt(wn.split(" ")[1]);
            String col = "";
            String w2 = w;
            do {
                String rs = "";
                for (int i = 0; i < n; i++) {
                    rs += w2;
                }
                col += w2.charAt(0);
                System.out.println(rs);
                out.write(rs);
                out.newLine();
                w2 = w2.substring(1, w2.length()) + w2.charAt(0);
            } while (!col.equals(w));
        }
        in.close();
        out.close();
    }

    int[] intArr(String x) {
        String st[] = x.split(" ");
        int ints[] = new int[st.length];
        for (int i = 0; i < st.length; i++) {
            ints[i] = Integer.parseInt(st[i]);
        }
        return ints;
    }

    double[] doubleArr(String x) {
        String st[] = x.split(" ");
        double d[] = new double[st.length];
        for (int i = 0; i < st.length; i++) {
            d[i] = Double.parseDouble(st[i]);
        }
        return d;
    }

    public static void main(String[] args) throws Exception {
        new HelloKity();
    }
}
//rC template