package tcioextractor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            String inIden = "<td align=\"left\" class=\"statText\">";
            String outIden = "<td align=\"right\" class=\"statText\">";
            String inIdenBG = "<td background=\"/i/steel_blue_bg.gif\" align=\"left\" class=\"statText\">";
            String outBG = "<td background=\"/i/steel_blue_bg.gif\" align=\"right\" class=\"statText\">";
            boolean a = line.contains(inIden);
            boolean b = line.contains(outIden);
            boolean c = line.contains(inIdenBG);
            boolean d = line.contains(outBG);
            if (a || b || c || d) {

//                xx[i] = line.substring(line.indexOf(">") + 1, );
                String parameters = "";
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
                    parameters += line.substring(start) + " "; // "\n" para imprimir los parametros uno debajo de otro, " " (espacio) para imprimirlos en la misma linea, separados por un espacio.
                    line = br.readLine();
                    start = 0;
                    last = line.lastIndexOf("</td>");
                }
                parameters += line.substring(start, last);
                parameters = parameters.trim();
//                System.err.println(paramenters);
                xx[i] = parameters;
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
                        System.err.println("" + xx[0] + " " + xx[1]);
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
    
    public static Object[] getInputAndOutput(String path) throws Exception{
    	ArrayList<ArrayList<Parameter>> input = new ArrayList<ArrayList<Parameter>>();
    	ArrayList<String> output = new ArrayList<String>();

        File file = new File(path);
    	BufferedReader br = new BufferedReader(new FileReader(file));
    	
    	for (; br.ready();) {
            String line = br.readLine();
            String inIden = "<td align=\"left\" class=\"statText\">";
            String inIdenBG = "<td background=\"/i/steel_blue_bg.gif\" align=\"left\" class=\"statText\">";            

            
            String outIden = "<td align=\"right\" class=\"statText\">";            
            String outBG = "<td background=\"/i/steel_blue_bg.gif\" align=\"right\" class=\"statText\">";
            String outException = "<td align=\"right\" class=\"statText\">Passed</td>";
            String outBGException = "<td background=\"/i/steel_blue_bg.gif\" align=\"right\" class=\"statText\">Passed</td>";
            
            boolean a = line.contains(inIden);
            boolean c = line.contains(inIdenBG);
            
            boolean b = line.contains(outIden);
            boolean d = line.contains(outBG);
            if (a || c) { //VALID INPUT
            	
                int start = line.indexOf(inIden) + inIden.length();
                if (c) start = line.indexOf(inIdenBG) + inIdenBG.length();
                
                ArrayList<Parameter> params = new ArrayList<Parameter>();
                
                int last = line.lastIndexOf("</td>");
                while (last < 0) {
                	if(line.endsWith(",")){ // there are more parameters
                		params.add(new Parameter(line.substring(start,line.lastIndexOf(","))));
                	}else{
                		params.add(new Parameter(line.substring(start)));
                	}
                    try {
						line = br.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    start = 0;
                    last = line.lastIndexOf("</td>");
                }
                params.add(new Parameter(line.substring(start,last).trim()));
            	input.add(params);                
            }else if((b || d) && 
            		(!line.contains(outException) && !line.contains(outBGException))
            		){ //OUTPUT
            	int start = line.indexOf(outIden) + outIden.length();
            	if (d) {
                    start = line.indexOf(outBG) + outBG.length();
                }
            	int last = line.lastIndexOf("</td>");
            	output.add(line.substring(start,last).trim());            	 
            }
        }
    	return new Object[]{input,output};
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
        String path = "H:/ConcursoProgramacion/ApplicationProjects/TCIOExtractor/cases/TWOLONG_ONELONG.html";
        String problemName = "HuaHCodingAforo";
        Caso caso = new Caso();
        caso.inType = ParameterType.STRINGARRAY;
        caso.outType = ParameterType.INTARRAY;
        new IOCreator_HTMLReader(caso, path, problemName);
    }
}
