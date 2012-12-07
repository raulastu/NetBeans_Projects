import java.io.*;
import java.util.*;

public class Feynman {

    Feynman() throws Exception{
        BufferedReader in = new BufferedReader(new FileReader(new File("C:/C-Feynman.in")));
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("C:/Feynman.out")));
        for(;;){
            int n=Integer.parseInt(in.readLine());
            if(n==0)
                break;
            int res=0;
            for (int i = 1; i <= n; i++) {
                res+=i*i;
            }
            out.write(""+res);
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
        new Feynman();
    }
}
//rC template