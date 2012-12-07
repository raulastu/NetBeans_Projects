/*
 * @(#)CompareTwoFiles.java	v1
 *
 * Copyright 2008 rC
 * RC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;

/**
 * 
 * @author Raúl F. Ramírez - rC
 * @version 1.0
 */
public class CompareTwoFiles {

    public CompareTwoFiles() {

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileNameRpta = "C:/omg.in";
        String path2 = "C:/omg.in";

        //BufferedReader inReal = new BufferedReader(new InputStreamReader(new FileInputStream(fileNameRpta)));
        //BufferedReader in2 = new BufferedReader(new InputStreamReader(new FileInputStream(path2)));
        File file1 = new File(fileNameRpta);
        File file2 = new File(path2);
        FileInputStream fisRpta = new FileInputStream(file1);
        FileInputStream fis2 = new FileInputStream(file2);
        byte[] bRpta = new byte[(int) file1.length()];
        byte[] b2 = new byte[(int) file2.length()];
        fisRpta.read(bRpta);
        fis2.read(b2);
        fisRpta.close();
        fis2.close();
        BufferedReader inReal = new BufferedReader(new StringReader(new String(bRpta)));
        BufferedReader in2 = new BufferedReader(new StringReader(new String(b2)));

        System.out.println("Son Iguales: " + sonIgualesTrim(inReal, in2, ""));
        inReal.close();
    }

    /**
     * compara si dos output files guardados en arrays de bytes son iguales.
     * Dos outputs son iguales si y solo si todas las lineas del array correct estan
     * contenidas en las primeras lineas del array yours
     * 
     * @param correct array de bytes de la respuesta correcta
     * @param yours array de bytes del output del concursante
     * @param comment String donde se guardará algun comentario
     *                  cuando los arrays no sean iguales
     * @return true si los arreglos de bytes son iguales, false otherwise.
     * @throws java.io.IOException
     */
    public static boolean sonIgualesTrim(byte[] correct, byte[] yours, String comment) throws IOException {

        String strRpta = new String(correct);
        String str2 = new String(yours);
        BufferedReader inReal = new BufferedReader(new StringReader(strRpta));
        BufferedReader in2 = new BufferedReader(new StringReader(str2));
        return sonIgualesTrim(inReal, in2, comment);

    }

    public static boolean sonIgualesTrim(BufferedReader inRpta, BufferedReader in2, String comment) throws IOException {
        boolean iguales = true;
        int line = 0;
        while (inRpta.ready()) {
            String lineRpta = "";
            String line2 = "";
            try {
                lineRpta = inRpta.readLine();
                line2 = in2.readLine();
                if (lineRpta == null) {
                    break;
                }
                if (!line2.equals(lineRpta)) {
                    System.out.println(comment = "Linea " + line + " esperado:[" + lineRpta + "] tu salida: [" + line2 + "]");
                    iguales = false;
                    break;
                }
            } catch (NullPointerException ex) {
                System.out.println(comment = "Error en Linea " + line + " esperado:[" + lineRpta + "] tu salida: [" + line2 + "]");
                iguales = false;
                break;
            }
            line++;
        }
        return iguales;
    }
}
