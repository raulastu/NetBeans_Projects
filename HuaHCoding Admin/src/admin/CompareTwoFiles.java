package admin;
/*
 * @(#)CompareTwoFiles.java	v1
 *
 * Copyright 2008 rC
 * RC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * 
 * @author rC
 * @version 1.0
 */
public class CompareTwoFiles {

    static String originalOutPath = "E:/ConcursoProgramacion/CI b1 problems/FormulaLuhn.sol";  //ruta del Output Correcto
    static String tuSalidaPath = "E:/ConcursoProgramacion/CI b1 problems/FormulaLuhn.sol"; //ruta de tu Output 

    public CompareTwoFiles() {
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
    public static boolean sonIguales(byte[] correct, byte[] yours, StringBuffer comment) throws IOException {
        return sonIguales(
                new BufferedReader(new StringReader(new String(correct))),
                new BufferedReader(new StringReader(new String(yours))), comment);
    }

    public static boolean sonIguales(BufferedReader correctReader,
            BufferedReader yourOutputhReader, StringBuffer comment) throws IOException {

        String rightLine = "", yourLine = "";
        for (int i = 1;
                (rightLine = correctReader.readLine()) != null &&
                !(rightLine = rightLine.trim()).equals("");
                i++) {
            try {
                yourLine = yourOutputhReader.readLine().trim();
                if (!yourLine.equals(rightLine)) {
                    comment = comment.append("Error en linea " + i + " esperado:[" + rightLine + "] tu salida: [" + yourLine + "]");
                    return false;
                }
            } catch (NullPointerException ex) {
                System.err.println("BEGIN Error Controlado");
                ex.printStackTrace();
                System.err.println("END Error Controlado");
                comment = comment.append("exError en linea " + i + " esperado:[" + rightLine + "] tu salida: [" + yourLine + "]");
                return false;
            }
        }
        comment.append("Solucion Correcta");
        correctReader.close();
        yourOutputhReader.close();
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

//        BufferedReader inReal = new BufferedReader(new InputStreamReader(new FileInputStream(originalOutPath)));
//        BufferedReader in2 = new BufferedReader(new InputStreamReader(new FileInputStream(tuSalidaPath)));
        String correctPath = "E:/ConcursoProgramacion/aniversario_informatica08_problems/He.sol";
        String yourPath = "E:/ConcursoProgramacion/aniversario_informatica08_problems/He.in";
        File file1 = new File(correctPath);
        File file2 = new File(yourPath);
        BufferedReader inReal = new BufferedReader(new FileReader(file1));
        BufferedReader in2 = new BufferedReader(new FileReader(file2));
        StringBuffer bf = new StringBuffer();
        System.out.println(sonIguales(inReal, in2, bf));
        System.out.println(bf);
    }
}
