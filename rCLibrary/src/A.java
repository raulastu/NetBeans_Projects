

import java.awt.geom.*;
import java.io.*;
import java.math.BigInteger;

public class A {

    static boolean TEST = false;
    BufferedReader in;
    BufferedWriter out;
    String fileNameIn = "saving.in";
    String path = "E:/Coder/practice/";

    public A() throws Exception {
        String fileNameOut = getClass().getSimpleName() + ".out";
        String thisFile = this.getClass().getCanonicalName();
        File file = new File(thisFile);
        if (TEST) {
            in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Test: ");
        } else {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(path + fileNameIn)));
            System.out.println("Mode Writing");
        }
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + fileNameOut)));

    }
    public void start() throws Exception {
        
        int nroCasos = Integer.parseInt(in.readLine());
        for (int i = 0; i < nroCasos; i++) {
            
            String outLine = "Case #" + (i+1) + ": ";
            if (TEST) {
                System.out.println(outLine);
            } else {
                out.write(outLine);                
                out.newLine();
            }
        }
        if (!TEST) {
            out.flush();
            out.close();
            in.close();
        }
    }
    
    public static void main(String[] args) throws Exception {
        new A().start();
    }

    public static void showArr(int[] integers) {
        for (int i = 0; i < integers.length; i++) {
            System.out.print(integers[i] + " ");
        }
        System.out.println();
    }

    private static String[] parseStringArr(String line) {
        String[] strings = line.split(" ");
        return strings;
    }

    private static double[] parseDoublesArr(String line) {
        String[] ss = line.split(" ");
        double[] doubles = new double[ss.length];        
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = Double.parseDouble(ss[i]);
        }
        return doubles;
    }

    private static int[] parseIntsArr(String line) {
        String[] ss = line.split(" ");
        int integers[] = new int[ss.length];
        for (int i = 0; i < ss.length; i++) {
            integers[i] = Integer.parseInt(ss[i]);
        }
        return integers;
    }

    private static BigInteger[] parseBigIntegersArr(String line) {
        String[] ss = line.split(" ");
        BigInteger integers[] = new BigInteger[ss.length];
        for (int i = 0; i < ss.length; i++) {
            integers[i] = new BigInteger(ss[i]);
        }
        return integers;
    }

    public static void sortAsc(int[] array) {
        for (int j = 0; j < array.length; j++) {
            for (int k = 0; k < array.length; k++) {
                if (array[j] < array[k]) {
                    int temp = array[j];
                    array[j] = array[k];
                    array[k] = temp;
                }
            }
        }
    }

    public static void sortDesc(int[] array) {
        for (int j = 0; j < array.length; j++) {
            for (int k = 0; k < array.length; k++) {
                if (array[j] > array[k]) {
                    int temp = array[j];
                    array[j] = array[k];
                    array[k] = temp;
                }
            }
        }
    }
}
