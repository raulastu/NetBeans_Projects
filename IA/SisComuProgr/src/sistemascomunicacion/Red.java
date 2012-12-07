package sistemascomunicacion;

import java.util.ArrayList;
import java.util.HashMap;

public class Red implements Comparable {

    int sr;
    int hosts;
    NetType netType;
    int requiredSubRedes;
    ArrayList<SubRed> subredes;
    String name;
    int mask;
    String maskString;
    HashMap<Integer, Integer> masks = new HashMap<Integer, Integer>();

    public Red(int sr, int hosts, NetType netType, String name) {
        this.sr = sr;
        this.hosts = hosts;
        this.netType = netType;
        this.name = name;
        switch( netType ){
            case A:
                mask = (int) Math.sqrt(hosts + 2) + 8;
                break;
            case B:
                mask = (int) Math.sqrt(hosts + 2) + 16;
                break;
            case C:
                mask = (int) Math.sqrt(hosts + 2) + 24;
                break;
        }
        masks.put(1, 128);
        masks.put(2, 128 + 64);
        masks.put(3, 128 + 64 + 32);
        masks.put(4, 128 + 64 + 32 + 16);
        masks.put(5, 128 + 64 + 32 + 16 + 8);
        masks.put(6, 128 + 64 + 32 + 16 + 8 + 4);
        masks.put(7, 128 + 64 + 32 + 16 + 8 + 4 + 2);
        masks.put(8, 128 + 64 + 32 + 16 + 8 + 4 + 2 + 1);
    }

    @Override
    public String toString() {
        return netType + " sr: " + sr + " h: " + hosts + " " + " " +
                mask + " " + masks.get((mask));
    }

    public int compareTo(Object o) {
        Red a = (Red) o;
        return hosts - a.hosts;
    }

    String getSubRedes() {
        String res = "";
        for (SubRed subRed : subredes) {
            res += subRed + "\n";
        }
        return res;
    }

    SubRed getSubRed(int i) {
        i = i - 1;
        String desde = "";
        String hasta = "";
        String broadcast = "";
//                 from = (i * (hosts + 2));
        long from = -1;
        String myName = this.name;
        if (myName.equals("")) {
            switch( netType ){
                case C:
                    myName = "192.168.10.0";
                    break;
                case B:
                    myName = "128.0.0.0";
                    break;
                case A:
                    myName = "10.0.0.0";
                    break;
            }
        }
        from = (ipToInt(myName) + (hosts + 2) * i);
        myName = intToIp(from);
        desde = intToIp(from + 1);
        hasta = intToIp(from + hosts);
        broadcast = intToIp(from + hosts + 1);
//            subredes.add(new SubRed(netType, name, desde, hasta, broadcast));
        return new SubRed(netType, myName, desde, hasta, broadcast);
    }

    void makeSubRedes() {
        subredes = new ArrayList<SubRed>();
        for (int i = 1; i <= requiredSubRedes; i++) {
            SubRed red = getSubRed(i);
            subredes.add(red);
        }
    }

    public static String intToIp(long i) {
        return ((i >> 24) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                (i & 0xFF);
    }

    public static Long ipToInt(String addr) {
        String[] addrArray = addr.split("\\.");

        long num = 0;
        for (int i = 0; i < addrArray.length; i++) {
            int power = 3 - i;

            num += ((Integer.parseInt(addrArray[i]) % 256 * Math.pow(256, power)));
        }
        return num;
    }
}
