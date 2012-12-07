import java.io.*;
import java.util.*;

public class Og {
    
    Og() throws Exception{
        BufferedReader in = new BufferedReader(new FileReader(new File("C:/B-Og.in")));
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("C:/Og.out")));
        String line;
        for (;;) {
            int nros[]=intArr(in.readLine());
            if(nros[0]==0)
                break;
            out.write(""+(nros[0]+nros[1]));
            out.newLine();
        }
        in.close();
        out.close();
    }

    int[] intArr(String x){String st[]=x.split(" ");int ints[]=new int[st.length];
        for(int i=0;i<st.length;i++)ints[i]=Integer.parseInt(st[i]);return ints;}
    double[] doubleArr(String x){String st[]=x.split(" ");double d[]=new double[st.length];
        for(int i=0;i<st.length;i++)d[i]=Double.parseDouble(st[i]);return d;}

    public static void main(String[]args) throws Exception{
        new Og();
    }
}
//rC template