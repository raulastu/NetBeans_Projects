package replaceAndCopy;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FixMenu {

    String file = "D:/wamp4/www/HuaHCodingWeb/menu/resources/menu.js";
    String fileOut = "D:/wamp4/www/HuaHCodingWeb/forum/menu/resources/menu.js";
    String replacementsFile = "";
    HashMap<String, String> replacements = new HashMap<String, String>();

    public FixMenu() throws FileNotFoundException, IOException {

    }

    public void remplazar(String inPath, String outPath, String[] repArray) throws FileNotFoundException, IOException {
        for (int i = 0; i < repArray.length; i++) {
            String[] x = repArray[i].split(" ");
            replacements.put(x[0], x[1]);
        }
        remplazar(inPath, outPath);
    }

    /**
     * Open in, copy all to a ArrayList, close
     * open out replace and close
     * thereby can modify a single file.
     * 
     * @param inPath 
     * @param outPath
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public void remplazar(String inPath, String outPath) throws FileNotFoundException, IOException {
        file = inPath;
        fileOut = outPath;
        if (replacements.isEmpty()) {
            replacementsFile = getClass().getResource("replaceAndCopy/ReplaceAll.txt").getPath();
            System.out.println(replacementsFile);
            BufferedReader readRep = new BufferedReader(new FileReader(new File(replacementsFile)));
            while (readRep.ready()) {
                String[] line = readRep.readLine().split(" ");
                replacements.put(line[0], line[1]);
            }
            readRep.close();
        }

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
        BufferedWriter out = new BufferedWriter(new FileWriter(fileOut));
        for (int i = 0; i < text.size(); i++) {
            out.write(text.get(i));
            out.newLine();
        }
        out.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
//        JOptionPane.showMessageDialog(null, "ready?");
        
        new FixMenu().remplazar("D:/wamp4/www/HuaHCodingWWWeb/menu/resources/menu.js",
                "D:/wamp4/www/HuaHCodingWWWeb/forum/menu/resources/menu.js");
        
//        new Main().remplazar("D:/wamp/www/HuaHCodingWWWeb/home/welcome.htm",
//                "D:/wamp/www/HuaHCodingWWWeb/home/welcome.htm",
//                new String[]{
//            "á &aacute;",
//            "é &eacute;",
//            "í &iacute;",
//            "ó &oacute;",
//            "ú &uacute;"
//        });
//
//        JOptionPane.showMessageDialog(null, "done");
    }
}
