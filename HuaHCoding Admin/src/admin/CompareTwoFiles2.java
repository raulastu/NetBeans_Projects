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
import java.util.Scanner;

/**
 * 
 * @author rC
 * @version 2.0
 */
public class CompareTwoFiles2 {

    static String originalOutPath = "E:/ConcursoProgramacion/CI b1 problems/FormulaLuhn.sol";  //ruta del Output Correcto
    static String tuSalidaPath = "E:/ConcursoProgramacion/CI b1 problems/FormulaLuhn.sol"; //ruta de tu Output 

    public CompareTwoFiles2() {
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
    public static boolean sonIguales(byte[] correct, byte[] yours, StringBuffer comment) {
        return sonIguales(
                new Scanner(new StringReader(new String(correct))),
                new Scanner(new StringReader(new String(yours))), comment);
    }

    public static boolean sonIguales(Scanner rightAnswer,
            Scanner yourAnswer, StringBuffer comment) {

        String rightLine = "", yourLine = "";
        for (int i = 1; rightAnswer.hasNextLine(); i++) {
            rightLine = rightAnswer.nextLine().trim();
            if (rightLine.equals("")) {
                break;
            }
            if (!yourAnswer.hasNextLine()) {
                comment = comment.append("Error en linea " + i + " esperado:[" + rightLine + "] tu salida no existe (Tu archivo es más corto que el correcto)");
                return false;
            } else {
                yourLine = yourAnswer.nextLine().trim();
                if (!yourLine.equals(rightLine)) {
                    comment = comment.append("Error en linea " + i + " esperado:[" + rightLine + "] tu salida: [" + yourLine + "]");
                    return false;
                }
            }
        }
        comment.append("Solución Correcta");
        rightAnswer.close();
        yourAnswer.close();
        return true;
    }

    public static boolean sonReallyIguales(byte[] correct, byte[] yours, StringBuffer comment) {
        return sonReallyIguales(
                new Scanner(new StringReader(new String(correct))),
                new Scanner(new StringReader(new String(yours))), comment);
    }

    public static boolean sonReallyIguales(Scanner rightAnswer,
            Scanner yourAnswer, StringBuffer comment) {

        String rightLine = "", yourLine = "";
        for (int i = 1; rightAnswer.hasNextLine(); i++) {
            rightLine = rightAnswer.nextLine().trim();
            if (rightLine.equals("")) {
                break;
            }
            if (!yourAnswer.hasNextLine()) {
                comment = comment.append("Error en linea " + i + " esperado:[" + rightLine + "] tu salida no existe (Tu archivo es más corto que el correcto)");
                return false;
            } else {
                yourLine = yourAnswer.nextLine().trim();
                if (!yourLine.equals(rightLine)) {
                    comment = comment.append("Error en linea " + i + " esperado:[" + rightLine + "] tu salida: [" + yourLine + "]");
                    return false;
                }
            }
        }
        if (yourAnswer.hasNextLine() && !yourAnswer.nextLine().trim().equals("")) {
            return false;
        }
        comment.append("Solución Correcta");
        rightAnswer.close();
        yourAnswer.close();
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        Scanner rightAnswer = new Scanner("E:\\NetBeans_Projects\\HuaHCoding Admin\\test\\admin\\correctSolution_A.sol");
        Scanner yourAnswer = new Scanner("E:\\NetBeans_Projects\\HuaHCoding Admin\\test\\admin\\yourCorrectSolution.sol");
        StringBuffer comment = new StringBuffer();
        boolean expResult = true;
        boolean result = CompareTwoFiles2.sonIguales(rightAnswer, yourAnswer, comment);
        System.out.println(result);
    }
}
