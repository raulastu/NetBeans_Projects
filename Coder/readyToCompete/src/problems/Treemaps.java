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
import java.util.HashMap;
import java.util.TreeMap;

public class Treemaps {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileNameIn = "treemaps.in";
        String path = "E:/Coder/practice/";
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path + fileNameIn)));
        String fileNameOut = fileNameIn.substring(0, fileNameIn.indexOf(".in")) + ".out";
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + fileNameOut)));
        int nroCasos = 1;
        for (;;) {
            int ints[] = getInts(in.readLine(), 3);
            if (ints[0] == 0 && ints[1] == 0 && ints[2] == 0) {
                break;
            }
            double W = ints[0];
            double H = ints[1];
            int L = ints[2];
            int root = 0;
            DirProperties nodo = new DirProperties(0, 0, 0, 0, 0, H, W, vertical);
            nodo.elements = new HashMap<Integer, DirProperties>();
            DirProperties raiz = nodo;
            HashMap map = new HashMap<Integer, DirProperties>();
            for (int i = 0; i < L; i++) {
                String values[] = getStrings(in.readLine(), 3);
                int ID = Integer.parseInt(values[0]);
                int parentID = Integer.parseInt(values[1]);
                double size = Double.parseDouble(values[2]);
                nodo.elements.put(ID, new DirProperties(ID, parentID, size, 0, 0, H, W, vertical));
            }
            buildTree(raiz);
            //showTree(raiz);
            actualizarOrientacion(raiz);
            actualizarTree(raiz);
            raiz.size = totalSize;
            setCoordenadas(raiz);
            //showTree(raiz);
            //System.out.println(totalSize);
            out.write("Case #" + nroCasos++ + ":");
            out.newLine();
            for (DirProperties nodoShow : treeMap.values()) {
                out.write(nodoShow.ID + ": " +
                        nodoShow.x + " " +
                        parsePrecision(nodoShow.y, 5) + " " +
                        parsePrecision(nodoShow.w, 5) + " " +
                        parsePrecision(nodoShow.h, 5));
                out.newLine();
            }
            storeSize = 0.0;
            totalSize = 0.0;
            treeMap = new TreeMap<Integer, DirProperties>();
            vertical = true;
            out.newLine();
        }
        in.close();
        out.close();
    }
    static Double storeSize = 0.0;
    static Double totalSize = 0.0;
    static TreeMap<Integer, DirProperties> treeMap = new TreeMap<Integer, DirProperties>();
    static boolean vertical = true;

    static void buildTree(DirProperties nodo) {
        Integer ids[] = new Integer[nodo.elements.keySet().size()];
        ids = nodo.elements.keySet().toArray(ids);
        for (int i = 0; i < ids.length; i++) {
            int parent = nodo.elements.get(ids[i]).parent;
            if (parent != 0) {
                searchAndPut(nodo, parent, ids[i], nodo.elements.get(ids[i]));
                nodo.elements.remove(ids[i]);
            }
        }
    }

    static void searchAndPut(DirProperties root, int parent, int ID, DirProperties newd) {
        if (root.elements == null) {
            return;
        }
        for (int id : root.elements.keySet()) {
            if (id == parent) {
                if (root.elements.get(id).elements == null) {
                    root.elements.get(id).elements = new HashMap<Integer, Treemaps.DirProperties>();
                }
                root.elements.get(id).elements.put(ID, newd);
            }
            searchAndPut(root.elements.get(id), parent, ID, newd);
        }
    }

    static DirProperties searchParent(DirProperties nodo, int ID) {
        if (nodo.elements.get(ID) != null) {
            return nodo.elements.get(ID);
        }
        for (int id : nodo.elements.keySet()) {
            searchParent(nodo.elements.get(id), ID);
        }
        return null;
    }

    static void actualizarOrientacion(DirProperties nodo) {
        if (nodo.elements == null) {
            return;
        } else {
            vertical = !vertical;
            for (int id : nodo.elements.keySet()) {
                nodo.elements.get(id).vert = vertical;
                actualizarOrientacion(nodo.elements.get(id));
            }

        }
    }

    static void showDir(DirProperties nodo) {
        for (DirProperties x : nodo.elements.values()) {
            System.out.println(x.ID + " " + x.parent + " " + x.size);
        }
    }

    static void showTree(DirProperties nodo) {
        if (nodo.elements == null) {
            return;
        } else {
            for (int id : nodo.elements.keySet()) {
                //System.out.println("size" + raiz.elements.keySet().size());
                showTree(nodo.elements.get(id));
                System.out.println(nodo.elements.get(id).ID + " " + nodo.elements.get(id).parent +
                        " " + nodo.elements.get(id).size);
            }
        }
    }

    static void showFilesOfTree(DirProperties nodo) {
        if (nodo.elements == null) {
            System.out.println(nodo.ID + ": " + nodo.x + " " + nodo.y + " " + nodo.w + " " + nodo.h);
            return;
        } else {
            Integer[] sortedArr = new Integer[2];
            sortedArr = nodo.elements.keySet().toArray(sortedArr);
            for (int i = 0; i < sortedArr.length; i++) {
                for (int j = 0; j < sortedArr.length; j++) {
                    if (sortedArr[i] < sortedArr[j]) {
                        int temp = sortedArr[i];
                        sortedArr[i] = sortedArr[j];
                        sortedArr[j] = temp;
                    }
                }
            }
            for (int i = 0; i < sortedArr.length; i++) {
                showFilesOfTree(nodo.elements.get(sortedArr[i]));
            }
        }
    }

    static double actualizarTree(DirProperties nodo) {
        if (nodo.elements == null) {
            storeSize += nodo.size;
            return nodo.size;
        }
        Integer[] ints = new Integer[nodo.elements.keySet().size()];
        ints = nodo.elements.keySet().toArray(ints);
        nodo.size = 0.0;
        for (int i = 0; i < ints.length; i++) {
            nodo.size = nodo.size + actualizarTree(nodo.elements.get(ints[i]));
        /*if (i == ints.length - 1) {
        nodo.size = storeSize;
        //storeSize = 0.0;
        }*/
        }
        totalSize += storeSize;
        storeSize = 0.0;
        return nodo.size;
    }

    static void setCoordenadas(DirProperties nodo) {
        if (nodo.elements == null) {
            return;
        } else {
            Integer[] sortedArr = new Integer[nodo.elements.keySet().size()];
            sortedArr = nodo.elements.keySet().toArray(sortedArr);
            for (int i = 0; i < sortedArr.length; i++) {
                for (int j = 0; j < sortedArr.length; j++) {
                    if (sortedArr[i] < sortedArr[j]) {
                        int temp = sortedArr[i];
                        sortedArr[i] = sortedArr[j];
                        sortedArr[j] = temp;
                    }
                }
            }
            double acumulado = 0.0;
            for (int i = 0; i < sortedArr.length; i++) {
                boolean vert = nodo.vert;
                double size = nodo.elements.get(sortedArr[i]).size;
                double sizeTotalDir = nodo.size;
                if (vert) {
                    nodo.elements.get(sortedArr[i]).x = nodo.x + acumulado;
                    nodo.elements.get(sortedArr[i]).y = nodo.y;
                    acumulado += (size / sizeTotalDir) * nodo.w;
                    nodo.elements.get(sortedArr[i]).w = (size / sizeTotalDir) * nodo.w;
                    nodo.elements.get(sortedArr[i]).h = nodo.h;
                } else {
                    nodo.elements.get(sortedArr[i]).x = nodo.x;
                    nodo.elements.get(sortedArr[i]).y = nodo.y + acumulado;
                    acumulado = acumulado + (size / sizeTotalDir) * nodo.h;
                    nodo.elements.get(sortedArr[i]).w = nodo.w;
                    nodo.elements.get(sortedArr[i]).h = (size / sizeTotalDir) * nodo.h;
                }
                if (nodo.elements.get(sortedArr[i]).ID < 0) {
                    treeMap.put(nodo.elements.get(sortedArr[i]).ID, nodo.elements.get(sortedArr[i]));
                }
                setCoordenadas(nodo.elements.get(sortedArr[i]));
            }
        }
    }

    static class DirProperties {

        int ID;
        int parent;
        double size;
        double x, y, h, w;
        HashMap<Integer, DirProperties> elements;
        boolean vert;

        public DirProperties(int ID, int parent, double size, double xP, double yP, double hP, double wP, boolean vert) {
            //elements = new HashMap<Integer, DirProperties>();
            this.ID = ID;
            this.parent = parent;
            this.size = size;
            this.x = xP;
            this.y = yP;
            this.h = hP;
            this.w = wP;
            this.vert = vert;
        }
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
        double decimal = value - (float)entero;
        decimal = decimal * Math.pow(10, precision);
        decimal = Math.round(decimal);
        decimal = decimal / Math.pow(10, precision);
        x = ((precision + 2) - String.valueOf(decimal).length());
        String ceros = "";
        for (int i = 0; i < x; i++) {
            ceros += "0";
        }
        float rpta = ((float) entero + (float) decimal);
        return String.valueOf(rpta) + ceros;
    }
}
