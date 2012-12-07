package domApli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class NewClass1 {

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("C:/Fuuusion.in"));
        PrintWriter pw2 = new PrintWriter(new File("C:/Fuuusion.sol"));
        pw.println(200 + "");
        for (int i = 0; i < 200; i++) {
            int a = (int) (Math.random() * 100);
            int b = (int) (Math.random() * 100);
            int c = (int) (Math.random() * 100);
            int d = (int) (Math.random() * 100);
            int res = Math.max(a + b, c + d);
            pw.println(a + " " + b + " " + c + " " + d);
            pw2.println("Caso #" + (i + 1) + ": " + res);
        }
        pw.close();
        pw2.close();
    }
}
