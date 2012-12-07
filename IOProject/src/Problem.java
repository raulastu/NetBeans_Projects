
import java.io.*;
import java.util.*;

public class Problem {
    int[] intArr(String[] st){int ints[]=new int[st.length];
        for(int i=0;i<st.length;i++)ints[i]=Integer.parseInt(st[i]);return ints;}
    public Problem() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("C:/he.in")));
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("C:/he.out")));
        for (;!in.readLine().equals("0 0");) {
            int[] attackDistances= intArr(in.readLine().split(" "));
            int[] defDistances= intArr(in.readLine().split(" "));
            Arrays.sort(attackDistances);
            Arrays.sort(defDistances);
            String res="N";
            if(attackDistances[0]<defDistances[1])
                res="Y";
            out.write(res);
            out.newLine();
        }
        in.close();
        out.close();
    }
    public static void main(String[] args) throws Exception {
        new Problem();
    }
}
