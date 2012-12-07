package tc_html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.JOptionPane;

public class Manager {
	
	private ArrayList<ArrayList<Parameter>> input;
	private ArrayList<String> output;
	
	BufferedWriter inputWriter;
	BufferedWriter outputWriter;
    String path;
    String name;
    
    public Manager(String path, String name)throws Exception {
        this.path = path;
        this.name = name;
        File file = new File(path);
        String pathOut = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("\\")) + "/" + name;
        System.out.println(pathOut);
        try {
			inputWriter = new BufferedWriter(new FileWriter(new File(pathOut + "_in.txt")));
			outputWriter = new BufferedWriter(new FileWriter(new File(pathOut + "_out.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
		start();
	}
	
    public void start() throws Exception{
    	Object[] ob = IOCreator_HTMLReader.getInputAndOutput(path);
    	input = (ArrayList<ArrayList<Parameter>>)ob[0];
    	output = (ArrayList<String>)ob[1];
    	File file = new File(path);    	
    	printInput("$0 $1");
    	printOutput("Case #$#: $0");
    }

//	String parse1 = "$0 $1";
//	String parse2 = "$0n$1";
//	String parse3 = "$0,$1";
//	String parse4 = "$0$1";
	public void printInput(String parse){
		try {			
			ArrayList<String> parsedInput = parseInput(parse,input);
			for (String string : parsedInput) {
				inputWriter.write(string);
				System.err.println(string);
				inputWriter.newLine();				
			}
			inputWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void printOutput(String parse){
		try {
			ArrayList<String> parsedOutput = parseOutput(parse,output);
			for (String string : parsedOutput) {
				outputWriter.write(string);
				System.err.println(string);
				outputWriter.newLine();				
			}
			outputWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	String parse1 = "$0 $1";
//	String parse2 = "$0n$1";
//	String parse3 = "$0,$1";
//	String parse4 = "$0$1";
	ArrayList<String> parseInput(String parse, ArrayList<ArrayList<Parameter>> paramenterInput){
		ArrayList<String> resList = new ArrayList<String>();

		
//		String pparse=parse1;
		resList.add(""+paramenterInput.size());
		for (ArrayList<Parameter> input : paramenterInput) {
			String res = "";
			for (int i = 0; i < parse.length(); i++) {
				char w = parse.charAt(i);
				switch (w) {
				case '$':
					int parameterIndex = Integer.parseInt(parse.charAt(i+1)+"");
					res+=input.get(parameterIndex);
					i++;
					break;
				case 'n':
					res+="\n";
					break;				
				default:
					res+=w;
					break;
				}
			}
			resList.add(res);
		}
		return resList;		
	}
	
	/**
	 * 
	 * @param parse $0 -> output, $# -> case number
	 * @param paramenterInput
	 * @return
	 */
	ArrayList<String> parseOutput(String parse, ArrayList<String> paramenterInput){
		ArrayList<String> resList = new ArrayList<String>();

//		String pparse=parse1;
		int counter=1;
		for (String outLine : paramenterInput) {
			String res = "";
			for (int i = 0; i < parse.length(); i++) {
				char w = parse.charAt(i);
				switch (w) {
				case '$':
					if(parse.charAt(i+1)=='#'){
						res+=counter;
					}else{
						int parameterIndex = Integer.parseInt(parse.charAt(i+1)+"");
						res+=outLine;
					}
					i++;
					break;	
				default:
					res+=w;
					break;
				}
			}
			counter++;
			resList.add(res);
		}
		return resList;		
	}
	
	public static void main(String[] args) throws Exception{
		String path = "H:/ConcursoProgramacion/ApplicationProjects/TCIOExtractor/cases/TWOLONG_ONELONG.html";
        String problemName = "HuaHCodingAforo";
		Manager man = new Manager(path, problemName);
	}
}
