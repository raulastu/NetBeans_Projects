import java.io.*;
import java.util.*;
import java.util.regex.*;

public class NEW {

    NEW() throws Exception{
        BufferedReader in = new BufferedReader(new FileReader(new File("C:/NEW.in")));
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("C:/NEW.out")));
        

        in.close();
        out.close();
    }

    int[] intArr(String x){String st[]=x.split(" ");int ints[]=new int[st.length];
        for(int i=0;i<st.length;i++)ints[i]=Integer.parseInt(st[i]);return ints;}
    double[] doubleArr(String x){String st[]=x.split(" ");double d[]=new double[st.length];
        for(int i=0;i<st.length;i++)d[i]=Double.parseDouble(st[i]);return d;}

    public static void main(String[]args) throws Exception{
        new NEW();
    }
}
//rC template