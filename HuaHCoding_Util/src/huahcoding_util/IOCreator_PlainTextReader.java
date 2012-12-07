package huahcoding_util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class IOCreator_PlainTextReader {

    private String path;
    private String name;

    public IOCreator_PlainTextReader(String path, String name) throws Exception {
        this.path = path;
        this.name = name;
        File file = new File(path);
        Scanner sc = new Scanner(file);
        String pathOut = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("\\")) + "/" + name;
        BufferedWriter inputWriter = new BufferedWriter(new FileWriter(new File(pathOut + ".in")));
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter(new File(pathOut + ".sol")));
        ArrayList<ProblemIO> lines = new ArrayList<ProblemIO>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] a = line.trim().split("\\t+");
            ProblemIO p = new ProblemIO(a[0].replace(",", "").trim(), a[1].trim());
            lines.add(p);
        }
        int c = 1;
        inputWriter.write("" + lines.size());
        inputWriter.newLine();
        for (ProblemIO io : lines) {
            inputWriter.write(io.in);
            inputWriter.newLine();
            outputWriter.write("Caso #" + c++ + ": " + io.out + "");
            outputWriter.newLine();
            System.err.println(io);
        }
        inputWriter.close();
        outputWriter.close();
    }

    public static void main(String[] args) throws Exception {
        String path = "E:/ConcursoProgramacion/CI b4 problems/Escape del Rectangulo/ioText.txt";
        String name = "EscapeRectangulo";
        new IOCreator_PlainTextReader(path, name);
    }
}
