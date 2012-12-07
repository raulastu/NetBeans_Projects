package framerelaymaker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    ArrayList<Point> list;
    String outputPath;
    PrintWriter fw;
    int start3rdIPad = 4;
    HashMap<String, String> ipMap = new HashMap<String, String>();
    HashMap<String, String> pairMap = new HashMap<String, String>();

    public Main(String outputPath) {
        this.outputPath = outputPath;

        list = new ArrayList<Point>();
    }

    public void add(String name, String ip) {
        list.add(new Point(name, ip));
    }

    public void addPairIp(String dlci, String ip) {
        ipMap.put(dlci, ip);
    }

    public void makeScripts() {
        try {
            fw = new PrintWriter(new File(outputPath));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < list.size(); i++) {
            fw.println("#######" + list.get(i).name + " Configuration");
            fw.println("");
            fw.println("interface serial " + list.get(i).serialPort);
            fw.println("encapsulation frame-relay ietf");
            fw.println("frame-relay lmi-type ansi");
            fw.println("description Circtuit #FML");
            fw.println("no shutdown");
            fw.println("clock rate 4000000");
            fw.println("exit");
            ArrayList<String> ipsRelated = new ArrayList<String>();
            for (int j = 0; j < list.size(); j++) {
                if (i == j) {
                    continue;
                }
                String dlci = (i + 1) + "0" + (j + 1);
                String rdlci = (j + 1) + "0" + (i + 1);
                String ip = i < j ? "1" : "2";
                String ipad = "";
                if (!ipMap.isEmpty()) {
                    if (!ipMap.containsKey(dlci)) {
                        System.err.println("OMFG FALTA" + dlci);
                    }
                    ipad = ipMap.get(dlci);
                } else {
                    if (pairMap.containsKey(rdlci)) {
                        ipad = pairMap.get(rdlci);
                        ipsRelated.add(ipad + ".0");
                        ipad = pairMap.get(rdlci) + "." + ip + " 255.255.255.0";

                    } else {
                        ipad = "192.168." + (start3rdIPad++);
                        ipsRelated.add(ipad + ".0");
                        pairMap.put(dlci, ipad);
                        ipad += "." + ip + " 255.255.255.0";
                    }
                }
                fw.println("interface serial " + list.get(i).serialPort + "." + dlci + " point-to-point");
                fw.println("description PVC " + list.get(i).name + " to " + list.get(j).name + ", DLCI " + dlci + ", Circuit #IDK");
                fw.println("ip address " + ipad);
                fw.println("frame-relay interface-dlci " + dlci);
                fw.println("exit");
            }
            fw.println("");
            fw.println("router eigrp 100");
            for (String string : ipsRelated) {
                fw.println("network " + string);
            }
            fw.println("exit");
            fw.println("exit");
            fw.println("write");
            fw.println("");
        }
        fw.close();
    }

    public static void main(String[] args) {
        Main m = new Main("C:/LADYGAGA_frame-relay-rc.txt");
        m.add("LADY", "");
        m.add("GAGA", "");
        m.add("XD", "");
//        m.add("Z", "");
//        m.addPairIp("102", "192.168.1.1 255.255.255.252");
//        m.addPairIp("201", "192.168.1.2 255.255.255.252");
//        m.addPairIp("103", "192.168.1.5 255.255.255.252");
//        m.addPairIp("301", "192.168.1.6 255.255.255.252");
//        m.addPairIp("203", "192.168.1.9 255.255.255.252");
//        m.addPairIp("302", "192.168.1.10 255.255.255.252");
        m.makeScripts();
    }
}
