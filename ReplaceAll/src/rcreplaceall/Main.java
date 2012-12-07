package rcreplaceall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    String file = "";
    String replacementsFile = "ReplaceAll.txt";
    HashMap<String, String> replacements;

    public Main() throws FileNotFoundException, IOException {
        BufferedReader readRep = new BufferedReader(new FileReader(new File(replacementsFile)));
        while (readRep.ready()) {
            String[] line = readRep.readLine().split(" ");
            replacements.put(line[0], line[0]);
        }
        readRep.close();
        BufferedReader read = new BufferedReader(new FileReader(new File(file)));
        ArrayList<String> text = new ArrayList<String>();
        while (read.ready()) {
            text.add(read.readLine());
        }
        read.close();
        for (String oldChar : replacements.keySet()) {
            for (int i = 0; i < text.size(); i++) {
                text.set(i, text.get(i).replace(oldChar, replacements.get(oldChar)));
            }
        }
        read.close();
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < text.size(); i++) {
            out.write(text.get(i));
            out.newLine();
        }
        out.close();
    }

    public static void main(String[] args) {

    }
}
