package codejam08.qualification;


import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FlySwatter {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileNameIn = "C-small.in";
        String path = "E:/codejam/FlySwatter/";
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path + fileNameIn)));
        String fileNameOut = fileNameIn.substring(0, fileNameIn.indexOf(".in")) + ".out";
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + fileNameOut)));
        int nroCasos = Integer.parseInt(in.readLine());
        for (int i = 1; i <= nroCasos; i++) {
            double f, R, t, r, g;
            double[] values = getValues(in.readLine());
            f = values[0];
            R = values[1];
            t = values[2];
            r = values[3];
            g = values[4];
            double areaEnCirculosCompletos = Math.pow(g - 2 * f, 2);
            System.out.println(f + " " + R + " " + t + " " + r + " " + g + " ");
            System.out.println(areaEnCirculosCompletos);
            GeneralPath gp = new GeneralPath();
            Area area = new Area();
        }
        in.close();
        out.close();
    }

    private static double getArea(GeneralPath gp) {
        double[] x = new double[2];
        double[] y = new double[2];

        PathIterator pi = gp.getPathIterator(null);
        int i = 0;
        double[] pt = new double[2];

        while (!pi.isDone()) {
            if (i == x.length) {
                double[] tx = new double[x.length * 2];
                double[] ty = new double[tx.length];

                for (int j = 0; j < i; j++) {
                    tx[j] = x[j];
                    ty[j] = y[j];
                }
                x = tx;
                y = ty;
            }
            pi.currentSegment(pt);
            x[i] = pt[0];
            y[i] = pt[1];
            i++;
            pi.next();
        }
        int n = i;
        double area = 0;
        for (i = 0; i < n; i++) {
            int j = (i + 1) % n;

            area += (x[i] * y[j] - x[j] * y[i]);
        }
        if (area < 0) {
            area = -area;
        }
        return area / 2;
    }

    private static double parseValues(String line) {
        double hours = Double.parseDouble(line.substring(0, line.indexOf(":")));
        double minutes = Double.parseDouble(line.substring(line.indexOf(":") + 1, line.length()));
        return hours + minutes / 60;
    }

    private static double[] getValues(String line) {
        double[] NANB = new double[5];
        int index = 0;
        NANB[0] = Double.parseDouble(line.substring(0, index = line.indexOf(" ")));
        NANB[1] = Double.parseDouble(line.substring(index + 1, index = line.indexOf(" ", index + 1)));
        NANB[2] = Double.parseDouble(line.substring(index + 1, index = line.indexOf(" ", index + 1)));
        NANB[3] = Double.parseDouble(line.substring(index + 1, index = line.indexOf(" ", index + 1)));
        NANB[4] = Double.parseDouble(line.substring(index + 1, line.length()));
        return NANB;
    }
}
