import java.io.*;
import java.util.*;

public class newRCProblem1 {
    
    newRCProblem1() throws Exception{
        BufferedReader in = new BufferedReader(new FileReader(new File("C:/newRCProblem1.in")));
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("C:/newRCProblem1.out")));
        

        in.close();
        out.close();
    }

    int[] intArr(String[] st){int ints[]=new int[st.length];
        for(int i=0;i<st.length;i++)ints[i]=Integer.parseInt(st[i]);return ints;}
    double[] doubleArr(String[] st){double ints[]=new double[st.length];
        for(int i=0;i<st.length;i++)ints[i]=Double.parseDouble(st[i]);return ints;}
    public static void main(String[]args) throws Exception{
        new newRCProblem1();
    }
}
//rC template