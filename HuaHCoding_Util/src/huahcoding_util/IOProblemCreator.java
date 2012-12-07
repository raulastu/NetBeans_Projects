package huahcoding_util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOProblemCreator {

    boolean ignoreQuotes = true; // ignore ""
    String path = "E:/ConcursoProgramacion/CI b2 problems/VerifyCreditCard_396_2e.txt";

    public IOProblemCreator(String path) throws FileNotFoundException, IOException {
        this.path=path;
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter inputWriter = new BufferedWriter(new FileWriter(new File(file.getAbsolutePath() + ".in")));
        BufferedWriter writerOutput = new BufferedWriter(new FileWriter(new File(file.getAbsolutePath() + ".sol")));
        int nro = 0;
        for (int i = 1; br.ready(); i++) {
            br.readLine();
            nro = i;
        }
        br.close();
        br = new BufferedReader(new FileReader(file));
        inputWriter.write("" + nro);
        inputWriter.newLine();
        for (int i = 1; br.ready(); i++) {
            String io[] = br.readLine().split(" ", 2);
            String inLine = io[0].trim();
            if (ignoreQuotes) {
                inLine = inLine.replaceAll("\"", "");
            }
            inputWriter.write(inLine);
            inputWriter.newLine();
            String outLine = "Caso #" + i + ": " + io[1].trim();
            //String outLine = io[1].trim();
            writerOutput.write(outLine);
            writerOutput.newLine();
            System.out.println(inLine + " \t" + outLine);
        }
        br.close();
        inputWriter.close();
        writerOutput.close();
    }

    public static void main(String[] args) throws Exception {
        new IOProblemCreator("E:/ConcursoProgramacion/CI b4 problems/Escape del Rectangulo/io.txt");
        
    }
}
