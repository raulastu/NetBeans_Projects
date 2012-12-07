

import java.io.*;
import java.math.BigInteger;

public class rCIO {

    BufferedReader reader;
    BufferedWriter writer;

    public rCIO() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public rCIO(String filepath) throws Exception {
        reader = new BufferedReader(new FileReader(filepath));
        String path = filepath.substring(0, filepath.lastIndexOf("/") + 1);

        String fileNameIn = filepath.substring(filepath.lastIndexOf("/") + 1, filepath.length());
        String fileNameOut = fileNameIn.substring(0, fileNameIn.indexOf(".in")) + ".out";
        writer = new BufferedWriter(new FileWriter(path + fileNameOut));
    }

    public String readLine() throws IOException {
        return reader.readLine();
    }

    public int readInt() throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public double readDouble() throws IOException {
        return Double.parseDouble(reader.readLine());
    }

    public long readLong() throws IOException {
        return Long.parseLong(reader.readLine());
    }

    public void close() throws IOException {
        reader.close();
        if (writer != null) {
            writer.flush();
            writer.close();
        }
    }

    public void escribir(String strOut) throws Exception {
        if (writer != null) {
            writer.write(strOut);
            writer.newLine();
        } else {
            System.out.println(strOut);
        }
    }

    public String[] getStrings() throws IOException {
        String line = readLine();
        String[] strings = line.split(" ");
        return strings;
    }

    public double[] getDoubles() throws IOException {
        String line = reader.readLine();
        String[] ss = line.split(" ");
        double[] doubles = new double[ss.length];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = Double.parseDouble(ss[i]);
        }
        return doubles;
    }

    public int[] getInts() throws IOException {
        String line = reader.readLine();
        String[] ss = line.split(" ");
        int integers[] = new int[ss.length];
        for (int i = 0; i < ss.length; i++) {
            integers[i] = Integer.parseInt(ss[i]);
        }
        return integers;
    }

    public BigInteger[] getBigIntegers() throws IOException {
        String line = reader.readLine();
        String[] ss = line.split(" ");
        BigInteger integers[] = new BigInteger[ss.length];
        for (int i = 0; i < ss.length; i++) {
            integers[i] = new BigInteger(ss[i]);
        }
        return integers;
    }
}
