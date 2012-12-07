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

public class Soccer {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileNameIn = "soccer.in";
        String path = "E:/Coder/practice/";
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path + fileNameIn)));
        String fileNameOut = fileNameIn.substring(0, fileNameIn.indexOf(".in")) + ".out";
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + fileNameOut)));
        int nroCasos = Integer.parseInt(in.readLine());
        for (int i = 1; i <= nroCasos; i++) {
            int[] ints = getInts(in.readLine(), 4);
            int xi = ints[0];
            int yi = ints[1];
            int xf = ints[2];
            int yf = ints[3];
            int nPlayers = Integer.parseInt(in.readLine());
            int nRemaining = 0;
            for (int j = 0; j < nPlayers; j++) {
                int[] position = getInts(in.readLine(), 2);
                int currentX = position[0];
                int currentY = position[1];
                boolean xReady = false;
                boolean yReady = false;
                if (xi < xf) {
                    if (xi <= currentX && currentX <= xf) {
                        xReady = true;
                    }
                } else {
                    if (xf <= currentX && currentX <= xi) {
                        xReady = true;
                    }
                }
                if (yi < yf) {
                    if (yi <= currentY && currentY <= yf) {
                        yReady = true;
                    }
                } else {
                    if (yf <= currentY && currentY <= yi) {
                        yReady = true;
                    }
                }
                if(yReady && xReady){
                    nRemaining++;
                }
            }
            out.write(nRemaining + "");
            out.newLine();
        }
        in.close();
        out.close();
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
}
