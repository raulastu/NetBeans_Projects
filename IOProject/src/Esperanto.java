import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Esperanto {

    Esperanto() throws Exception{
        BufferedReader in = new BufferedReader(new FileReader(new File("D:/A-asd.in")));
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("D:/Esperanto.out")));
        int n=Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            out.write(nameThisNumber(Integer.parseInt(in.readLine())));
            out.newLine();
        }
        in.close();
        out.close();
    }
    public String nameThisNumber(int x) {
        String [] n = {"","unu", "du", "tri", "kvar", "kvin", "ses", "sep", "ok", "nau", "dek"};
        String [] nn=new String[101];
        for (int i = 1; i < n.length; i++) {
            nn[i]=n[i];
            nn[i+10]="dek "+n[i];
        }
        for (int i = 2; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                nn[i*10+j] = (n[i]+"dek "+n[j]).trim();
            }
        }
        return nn[x];
    }

    int[] intArr(String x){String st[]=x.split(" ");int ints[]=new int[st.length];
        for(int i=0;i<st.length;i++)ints[i]=Integer.parseInt(st[i]);return ints;}
    double[] doubleArr(String x){String st[]=x.split(" ");double d[]=new double[st.length];
        for(int i=0;i<st.length;i++)d[i]=Double.parseDouble(st[i]);return d;}

    public static void main(String[]args) throws Exception{
        new Esperanto();
    }
}
//rC template