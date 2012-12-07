package util;

import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class startTemplate {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileNameIn = "soccer1.in";
        String path = "E:/Coder/practice/";
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path + fileNameIn)));
        String fileNameOut = fileNameIn.substring(0, fileNameIn.indexOf(".in")) + ".out";
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + fileNameOut)));
        int nroCasos = Integer.parseInt(in.readLine());
        for (int i = 1; i < nroCasos; i++) {


            out.write("");
            out.newLine();
        }
        in.close();
        out.close();
    }

    private static String[] getStrings(String line, int nroItemsXLine) {
        String[] strings = new String[nroItemsXLine];
        int index = 0;
        String val = "";
        for (int i = 0; i < line.length(); i++) {
            char charAt = line.charAt(i);
            if (charAt == ' ') {
                strings[index++] = val;
                val = "";
            } else {
                val = val + charAt;
            }
        }
        strings[index++] = val;
        return strings;
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

    public static String parsePrecision(double value, int precision) {
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
        //double rpta = ((double) entero + (double) decimal);
        String enteroStr = String.valueOf(entero);
        
        String decimalStr = String.valueOf(decimal);
        
        return enteroStr.substring(0, enteroStr.length() - 2) + decimalStr.substring(2, decimalStr.length()) + ceros;
    }
}
