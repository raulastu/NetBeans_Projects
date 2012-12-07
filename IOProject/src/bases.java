import java.io.*;
import java.util.*;
import java.util.regex.*;

public class bases {

    bases() throws Exception{
        BufferedReader in = new BufferedReader(new FileReader(new File("C:/B-baseComparator.in")));
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("C:/bases.out")));
        for (int i = 0; in.ready(); i++) {
            String[] numbers = in.readLine().split(" ");
            String r="xx";
            if(get(numbers[0],numbers[1])<get(numbers[2],numbers[3])){
                r="<";
            }else if(get(numbers[0],numbers[1])>get(numbers[2],numbers[3])){
                r=">";
            }else if(get(numbers[0],numbers[1])==get(numbers[2],numbers[3])){
                r="=";
            }
            System.out.println(r);
            out.write(r);
            out.newLine();
        }

        in.close();
        out.close();
    }
    public int get(String n, String base){
       char[] digitsA = String.valueOf(n).toCharArray();
       int xx=0;
        for (int j = 0; j < digitsA.length; j++) {
                int d = Integer.parseInt(""+digitsA[j]);
                xx+= d * Math.pow(Integer.parseInt(base), digitsA.length-1-j);
            }
       return xx;
    }

    int[] intArr(String x){String st[]=x.split(" ");int ints[]=new int[st.length];
        for(int i=0;i<st.length;i++)ints[i]=Integer.parseInt(st[i]);return ints;}
    long[] longArr(String x){String st[]=x.split(" ");long longs[]=new long[st.length];
        for(int i=0;i<st.length;i++)longs[i]=Long.parseLong(st[i]);return longs;}
    double[] doubleArr(String x){String st[]=x.split(" ");double d[]=new double[st.length];
        for(int i=0;i<st.length;i++)d[i]=Double.parseDouble(st[i]);return d;}


    public static void main(String[]args) throws Exception{
        new bases();
    }
}
//rC template