package problems;

import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class OpticFiber {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileNameIn = "optic.in";
        String path = "E:/Coder/practice/";
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path + fileNameIn)));
        String fileNameOut = fileNameIn.substring(0, fileNameIn.indexOf(".in")) + ".out";
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + fileNameOut)));
        int nroCasos = Integer.parseInt(in.readLine());
        for (int i = 1; i <= nroCasos; i++) {
            double[] doubles = getDoubles(in.readLine(), 3);
            double h = doubles[0];
            double C = doubles[1];
            int percent = (int) doubles[2];
            int nRays = Integer.parseInt(in.readLine());
            String cms = "";
            HashSet<String> channels = new HashSet<String>();
            boolean noHayPar = true;
            for (int j = 0; j < nRays; j++) {
                double[] N2 = getDoubles(in.readLine(), 2);
                double angleStartingRay = N2[0];
                double k = N2[1];
                double Cj = C;
                double distanceR = 0.0;
                while (angleStartingRay > 0) {
                    double newAngle = 90 - angleStartingRay;
                    distanceR += h * (Math.sin(Math.toRadians(newAngle)) /
                            Math.cos(Math.toRadians(newAngle)));
                    Cj = Cj * (1 + (percent / (double) 100));
                    angleStartingRay -= k;
                }
                if (channels.add(getFirst3Digits(String.valueOf(Cj))) == false) {
                    noHayPar = false;
                }
                cms += "cm: " + toPrecision(distanceR, 2) + "\n";
            }
            //System.out.println(channels);
            if (noHayPar) {
                out.write("" + cms);
                out.write("Cable and angles are perfect.");
                out.newLine();
            } else {
                out.write("Try different angles.");
                out.newLine();
            }
            out.newLine();
        }
        in.close();
        out.close();
    }

    private static String getFirst3Digits(String doubleStr) {
        String returnStr = "";
        for (int i = 0; i < doubleStr.length(); i++) {
            char charAt = doubleStr.charAt(i);
            if (charAt != '.') {
                returnStr += charAt;
            }
            if (returnStr.length() >= 3) {
                break;
            }
        }
        return returnStr;
    }

    private static double[] getDoubles(String line, int nroItemsXLine) {
        double[] doubles = new double[nroItemsXLine];
        int index = 0;
        String val = "";
        for (int i = 0; i < line.length(); i++) {
            char charAt = line.charAt(i);
            if (charAt == ' ') {
                doubles[index++] = Double.parseDouble(val);
                val = "";
            } else {
                val = val + charAt;
            }
        }
        doubles[index++] = Double.parseDouble(val);
        return doubles;
    }

    private static int[] getInts(String line, int nroItemsXLine) {
        int[] integers = new int[nroItemsXLine];
        int index = 0;
        String val = "";
        for (int i = 0; i < line.length(); i++) {
            char charAt = line.charAt(i);
            if (charAt == ' ') {
                integers[index++] = Integer.parseInt(val);
                val = "";
            } else {
                val = val + charAt;
            }
        }
        integers[index++] = Integer.parseInt(val);
        return integers;
    }

    public static String toPrecision(double value, int precision) {
        int x = 0;
        double entero = Math.floor(value);
        double decimal = value - entero;
        decimal = decimal * Math.pow(10, precision);
        decimal = Math.round(decimal);
        decimal = decimal / Math.pow(10, precision);
        x = ((precision + 2) - String.valueOf(decimal).length());
        String ceros = "";
        for (int i = 0; i < x; i++) {
            ceros += "0";
        }
        return String.valueOf(entero + decimal) + ceros;
    }
}
