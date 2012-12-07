package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class CompareTwoFiles {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String path = "E:/Coder/practice/";
        
        String fileNameRpta = "treemapsRpta.out";
        String fileNameIn2 = "treemaps.out";

        BufferedReader inReal = new BufferedReader(new InputStreamReader(new FileInputStream(path + fileNameRpta)));
        BufferedReader in2 = new BufferedReader(new InputStreamReader(new FileInputStream(path + fileNameIn2)));

        System.out.println("Son Iguales: " + sonIgualesTrim(inReal, in2));

        in2.close();
        inReal.close();
    }

    public static boolean sonIgualesTrim(BufferedReader inRpta, BufferedReader in2) throws IOException {
        boolean iguales = true;
        int line = 0;
        while (inRpta.ready()) {
            String lineRpta = "";
            String line2 = "";
            try {
                lineRpta = inRpta.readLine();
                line2 = in2.readLine();
                if (!lineRpta.equals(line2)) {
                    System.out.println("Line:" + line + " = " + lineRpta + " <> " + line2);
                    iguales = false;
                    break;
                }
            } catch (NullPointerException ex) {
                System.out.println("Line:" + line + " = " + lineRpta + " <> " + line2);
                iguales = false;
                break;
            }
            line++;
        }
        return iguales;
    }
}
