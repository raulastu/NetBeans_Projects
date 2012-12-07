package huahcoding_util;

import java.io.*;
import java.util.*;

public class newRCJavaClass_IO {

    String path = "E:/ConcursoProgramacion/ProblemsPending/MegaOg/";
    String problemName = "MegaOg";
    int [] generado;
    newRCJavaClass_IO() throws Exception {
        PrintWriter in = new PrintWriter((new File(path + problemName + ".in")));
        PrintWriter out = new PrintWriter((new File(path + problemName + ".sol")));
        int nroCasos = 48;

        for (int i = 1; i <= 2; i++) {
            String line = "";
            int res = 0;
            int max=0;
            for (int j = 0; j < 10; j++) {
                if(i>max){
                    max=i;
                    res=j;
                }
                line+=i+" ";
            }
            line = line.trim();
            in.println(line);
            out.println("Caso #"+i+": "+res);
        }
        for (int i = 3; i <= 50; i++) {
            String line = "";
            int res = 0;
            int max=0;
            for (int j = 0; j < i; j++) {
                int n =Math.abs((int)(1+(Math.random()*99)));
                if(n>max){
                    max=n;
                    res=j;
                }
                line+=n+" ";
            }
            line = line.trim();
            in.println(line);
            out.println("Caso #"+i+": "+res);
        }
        for (int i = 51; i <= 100; i++) {
            int nro = (int) (1+ Math.random() * 50);
//            System.err.println(nro);
            String line = "";
            int max = 0;
            int res = 0;
            for (int j = 0; j < nro; j++) {
                int n =Math.abs((int)((Math.random()*100)));
                 if(n>max){
                    max=n;
                    res=j;
                }
                line+=n+" ";
            }
            line = line.trim();
            in.println(line);
            out.println("Caso #"+i+": "+res);
        }        
        in.close();
        out.close();
    }
    int sol(int [] a){
        int index=0;
        int max=a[0];
        for (int i = 1; i < a.length; i++) {
            if(a[i]>max){
                max=a[i];
                index=0;
            }
        }
        return index;
    }

    int[] intArr(String[] st) {
        int ints[] = new int[st.length];
        for (int i = 0; i < st.length; i++) {
            ints[i] = Integer.parseInt(st[i]);
        }
        return ints;
    }

    double[] doubleArr(String[] st) {
        double ints[] = new double[st.length];
        for (int i = 0; i < st.length; i++) {
            ints[i] = Double.parseDouble(st[i]);
        }
        return ints;
    }

    public static void main(String[] args) throws Exception {
        new newRCJavaClass_IO();
    }
}
//rC template

