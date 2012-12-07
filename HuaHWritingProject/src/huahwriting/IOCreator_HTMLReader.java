package huahwriting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author rC
 */
public class IOCreator_HTMLReader {

    boolean ignoreQuotes = true;
    ArrayList<String[]> lines = new ArrayList<String[]>();
    String path;
    String name;

    public IOCreator_HTMLReader(Caso caso, String path, String name) throws Exception {
        this.path = path;
        this.name = name;
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String pathOut = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("\\")) + "/" + name;
        System.out.println(pathOut);
        BufferedWriter inputWriter = new BufferedWriter(new FileWriter(new File(pathOut + ".in")));
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter(new File(pathOut + ".sol")));


        String[] xx = {"", "", ""};
        int i = 0;
        for (; br.ready();) {
            String line = br.readLine();
            String inIden = "<td class=\"statText\" align=\"left\">";
            String outIden = "<td class=\"statText\" align=\"right\">";
            String inIdenBG = "<td class=\"statText\" align=\"left\" background=\"/i/steel_blue_bg.gif\">";
            String outBG = "<td class=\"statText\" align=\"right\" background=\"/i/steel_blue_bg.gif\">";
            boolean a = line.contains(inIden);
            boolean b = line.contains(outIden);
            boolean c = line.contains(inIdenBG);
            boolean d = line.contains(outBG);
            if (a || b || c || d) {

//                xx[i] = line.substring(line.indexOf(">") + 1, );
                String paramenters = "";
                int start = line.indexOf(inIden) + inIden.length();
                if (b) {
                    start = line.indexOf(outIden) + outIden.length();
                } else if (c) {
                    start = line.indexOf(inIdenBG) + inIdenBG.length();
                } else if (d) {
                    start = line.indexOf(outBG) + outBG.length();
                }
//                System.err.println(start);
                int last = line.lastIndexOf("</td>");
                while (last < 0) {
                    paramenters += line.substring(start) + "\n"; // "\n" para imprimir los parametros uno debajo de otro, " " (espacio) para imprimirlos en la misma linea, separados por un espacio.
                    line = br.readLine();
                    start = 0;
                    last = line.lastIndexOf("</td>");
                }
                paramenters += line.substring(start, last);
                paramenters = paramenters.trim();
//                System.err.println(paramenters);
                xx[i] = paramenters;
//                xx[i] = ignoreQuotes ? xx[i].replaceAll("\"", "") : xx[i];
                xx[i] = xx[i].trim();
                i++;
                if (i == xx.length) {
                    if (xx[2].equals("Passed")) {
                        String in = parseIn(caso.inType, xx[0]);
                        String out = parseOut(caso.outType, xx[1]);
                        lines.add(new String[]{in, out});
//                        System.err.println(in + " -> " + out);
                        i = 0;
                    } else {
                        System.err.println("" + xx[0] + " " + xx[1]);
                        throw new Exception();
                    }
                }
            }
        }
        inputWriter.write(lines.size() + "");
        inputWriter.newLine();
//        Collections.shuffle(lines);        // CHOCOLATEAR :P
//        lines.remove(lines.size() - 1);
        int c = 1;
        for (String[] strings : lines) {
            inputWriter.write(strings[0]);
            inputWriter.newLine();
            System.out.println("[" + strings[0] + "] - [" + strings[1] + "]");
            outputWriter.write("Caso #" + c++ + ": " + strings[1] + "");
            outputWriter.newLine();
        }

        System.out.println("Read " + lines.size() + " cases");
        br.close();
        inputWriter.close();
        outputWriter.close();
    }

    String parseIn(ParameterType inType, String val) {
        switch( inType ){
            case STRING:
                val = val.replaceAll("\"|,", "").replaceAll("\\{|\\}", "");
                return val;
            case INTARRAY:
                return val.replaceAll("\\{|\\}|,", "").trim();
            case STRINGARRAY:
//                String v = val.replaceAll("\"|\\{|\\}", "").trim().replace("arrives", "llega").replace("departs", "sale");
                val = val.replaceAll("\"", "").replaceAll(", ", ",");
                return val;
            case TWOSTRINGARRAY:
                val = val.replace("\"", "").replaceAll(",", "").trim();
                return val;
            default:
                return val;
        }
    }

    String parseOut(ParameterType outType, String val) {
        switch( outType ){
            case STRING:
                val = val.replaceAll("\"", "");
                return val;
            case INTARRAY:
                return val.replaceAll("\\{|\\}|,", "").trim();
            case STRINGARRAY:
//                String v = val.replaceAll("\"|\\{|\\}", "").trim().replace("arrives", "llega").replace("departs", "sale");
                val = val.replaceAll("\"", "").replace(", ", ",");
                return val;
            case TWOSTRINGARRAY:
                val = val.replace("\"", "").replaceAll(",", "").trim();
                return val;
            default:
                return val;
        }
    }

    public static void main(String[] args) throws Exception {
        String path = "E:/ConcursoProgramacion/ProblemsPending/BinarioMasUno/io.txt";
        String problemName = "BinarioMasUno";
        Caso caso = new Caso();
        caso.inType = ParameterType.STRING;
        caso.outType = ParameterType.STRING;
        new IOCreator_HTMLReader(caso, path, problemName);
    }
}
