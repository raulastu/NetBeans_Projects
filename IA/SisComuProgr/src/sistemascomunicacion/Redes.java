/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemascomunicacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class Redes {

    HashMap<Integer, Integer> masks = new HashMap<Integer, Integer>();

    public Redes() {
        masks.put(1, 128);
        masks.put(2, 128 + 64);
        masks.put(3, 128 + 64 + 32);
        masks.put(4, 128 + 64 + 32 + 16);
        masks.put(5, 128 + 64 + 32 + 16 + 8);
        masks.put(6, 128 + 64 + 32 + 16 + 8 + 4);
        masks.put(7, 128 + 64 + 32 + 16 + 8 + 4 + 2);
        masks.put(8, 128 + 64 + 32 + 16 + 8 + 4 + 2 + 1);
    }
    int subredes = 1;
    int maxHosts = 1;

    static Red getRed(NetType type, String ip, int nroBits) {
        int sr = -1;
        int hosts = -1;
        switch( type ){
            case A:
                int prestados = (nroBits - 8);
                sr = (int) Math.pow(2, prestados);
                hosts = (int) Math.pow(2, 24 - prestados);
                break;
            case B:
                int prestados1 = (nroBits - 16);
                sr = (int) Math.pow(2, prestados1);
                hosts = (int) Math.pow(2, 16 - prestados1);
                break;
            case C:
                int prestados2 = (nroBits - 24);
                sr = (int) Math.pow(2, prestados2);
                hosts = (int) Math.pow(2, 8 - prestados2);
                break;
        }
        System.err.println(sr + " " + hosts);
        return getRed(sr, hosts - 2, ip);
    }

    static Red getRed(int subredes, int minhosts, String ipName) {

        ArrayList<Red> redes = new ArrayList<Red>();
        //C
        for (int i = 0; i < 8; i++) {
            int sr = (int) Math.pow(2, i);
            int hosts = (int) Math.pow(2, 8 - i) - 2;
            if (sr >= subredes && hosts >= minhosts) {
                redes.add(new Red(sr, hosts, NetType.C, ipName));
//                return new Red(sr, hosts, NetType.C);
            }
        }
        //B
        for (int i = 0; i < 16; i++) {
            int sr = (int) Math.pow(2, i);
            int hosts = (int) Math.pow(2, 16 - i) - 2;
            if (sr >= subredes && hosts >= minhosts) {
                redes.add(new Red(sr, hosts, NetType.B, ipName));
//                return new Red(sr, hosts, NetType.C);
            }
        }
        //A
        for (int i = 0; i < 24; i++) {
            int sr = (int) Math.pow(2, i);
            int hosts = (int) Math.pow(2, 24 - i) - 2;
            if (sr >= subredes && hosts >= minhosts) {
                redes.add(new Red(sr, hosts, NetType.A, ipName));
//                return new Red(sr, hosts, NetType.C);
            }
        }
        Collections.sort(redes);
        redes.get(0).requiredSubRedes = subredes;
        redes.get(0).makeSubRedes();
        System.err.println(redes.get(0).getSubRedes());
        return redes.get(0);
    }

    static String intToMask(int n) {
        String mask = "";
        int c = 0;
        for (int i = 0; i < n; i++) {
            c += 1 << (7 - (i % 8));
            System.err.print(7 - (i % 8));
            System.err.println(" " + i % 8);
            if (c == 255) {
                mask += (c) + ".";
                c = 0;
            }
        }
        mask += c + ".";
        return mask.substring(0, mask.length() - 1);
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.err.println(new Redes().masks);
        String msg = "ingreseOpt(1 sr nh ipName) (2 NetType ipName nBits)";
        String res = JOptionPane.showInputDialog(msg);
        PrintWriter pw = new PrintWriter(new File("OMG.TXT"));
        String[] s = res.split(" ");
        if (Integer.parseInt(s[0]) == 1) {
            int sr = Integer.parseInt(s[1]);
            int nh = Integer.parseInt(s[2]);
            String ipName = s[3];
            Red a = getRed(sr, nh, ipName);

            a.requiredSubRedes = sr;
            a.makeSubRedes();
            pw.println(new Redes().masks);
            pw.println(a.getSubRedes());
            pw.println(a);
        } else {
            char typeIn = s[1].charAt(0);
            String ipName = s[2];
            int nBits = Integer.parseInt(s[3]);
            NetType type = typeIn == 'a' ? NetType.A : (typeIn == 'b' ? NetType.B : NetType.C);
            Red a = getRed(type, ipName, nBits);
            a.makeSubRedes();
            pw.println(new Redes().masks);
            pw.println(a.getSubRedes());
            pw.println(a);
        }
        pw.close();
    }

    static void la9() {
        Red red = getRed(NetType.A, "110.0.0.0", 22);
        int prestados = (22 - 8);
        int sr = (int) Math.pow(2, prestados);
//        Red red = get(sr, (int) Math.pow(2, 24 - prestados) - 2, "");
//        System.err.println(red);
        System.err.println(red.getSubRed(14));
        System.err.println(red.getSubRed(15));
        System.err.println(red.getSubRed(21));
        System.err.println(red.getSubRed(29));
        System.err.println(red.getSubRed(16384));
        System.err.println(red.getSubRed(13));
        System.err.println(red.getSubRed(19));
        System.err.println(red.getSubRed(27));
    }
}
