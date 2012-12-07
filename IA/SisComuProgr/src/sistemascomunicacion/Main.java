package sistemascomunicacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.lang.Math;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Main {

    public Main() {
        try {
            pw = new PrintWriter(new File("leto.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int primeFact(int n) {
        int a = 0;
        n--;
        while (n > 0) {
            n /= 2;
            a++;
        }
        return a;
    }

    private static int getMask(int integer) {
        int t = 0;
        for (int i = 7; i >= 8 - integer; i--) {
            t += (int) Math.pow(2, i);
        }
        return t;
    }

    public ArrayList<String[]> getRango(int[] ip, int nroIps, int minRedes) {
//        int ipsPerRed = (int) Math.pow(2, n - primeFact(n));
        int bitsUtilizadosParaRed = primeFact(minRedes);
        out("mask: ." + getMask(bitsUtilizadosParaRed) + " - " + "CIDR: /" + (32 - (8 - primeFact(minRedes))));
//        System.out.println("bytes:" + Integer.getStringRep(bitsUtilizadosParaRed));
        int ipsPerRed = (int) Math.pow(2, (8 - bitsUtilizadosParaRed));
        out("host per net:" + ipsPerRed + " (utilizables: " + (ipsPerRed - 2));
        ArrayList<String[]> res = new ArrayList<String[]>();
        for (int i = 0; i < 256; i += ipsPerRed) {
            String rangoRed = "" + ip[0] + "." + ip[1] + "." + ip[2] + "." + i;
            String ip1 = ip[0] + "." + ip[1] + "." + ip[2] + "." + (i + 1);
            String ip2 = "" + ip[0] + "." + ip[1] + "." + ip[2] + "." + (i + ipsPerRed - 2);
            String broadCast = ip[0] + "." + ip[1] + "." + ip[2] + "." + (i + ipsPerRed - 1);
            res.add(new String[]{rangoRed, ip1, ip2, broadCast});
        }
        return res;
    }
    PrintWriter pw;

    public void go(int nRedes) throws FileNotFoundException {

        out("deseo " + nRedes + " subredes");
        out("bits prestados " + primeFact(nRedes));
        out("tengo que dividir en " + Math.pow(2, primeFact(nRedes)) + " redes");
        ArrayList<String[]> res = getRango(new int[]{192, 168, 1, 1}, 256, nRedes);

        int i = 1;
        for (String[] strings : res) {
            out("subred " + i++ + ": " + strings[0] + " \t\t" + strings[1] + " - " + strings[2] + "\t" + strings[3]);
        }
        pw.close();
    }

    private void out(String str) {
        System.out.println(str);
        pw.println(str);
    }
    String data;

    public static void main(String[] args) throws FileNotFoundException {
        int r = Integer.parseInt("" + JOptionPane.showInputDialog("cuantas redes kieres"));
        new Main().go(r);
    }
}
