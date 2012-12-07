package expocursos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Main {

    void go() {
        JFileChooser jFileChooser = new JFileChooser();
        int s = jFileChooser.showOpenDialog(null);
        String read = "";
        String save = "";
        if (s==JFileChooser.APPROVE_OPTION) {
            read = jFileChooser.getSelectedFile().getAbsolutePath();
        }
//        JFileChooser jFileChooserS = new JFileChooser();
//        s = jFileChooserS.showSaveDialog(null);
//        if (s==JFileChooser.APPROVE_OPTION) {
//            save = jFileChooserS.getSelectedFile().getAbsolutePath();
//        }
//        assert !read.equals("")&&!save.equals("");
        try {
            String outPath = "C:/adoreu.txt";
            String[] links = create(read, outPath);
            openURLS(10, links);
            JOptionPane.showMessageDialog(null, "Processed "+links.length+" links. File was created on "+outPath);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "fck", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void openURLS(int interval, String[] links) {
        for (int i = 0; i<links.length; i++) {
            if (i%interval==0) {
                String msg = "URLs Abiertos:"+(i)+", Quedan :"+(links.length-i);
                JOptionPane.showMessageDialog(null, msg);
            }
            BareBonesBrowserLaunch.openURL(links[i]);
        }
        JOptionPane.showMessageDialog(null, "Finalized "+links.length+" URLs");
    }

    String[] create(String fileread, String saveto) throws Exception {
        BufferedWriter fw = new BufferedWriter(new FileWriter(new File(saveto)));

        File fi = new File(fileread);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(fi);
        doc.getDocumentElement().normalize();
        System.out.println("Root element "+doc.getDocumentElement().getNodeName());
        NodeList nodeLst = doc.getElementsByTagName("contact");
        System.out.println("Information of contactlist");
        System.out.println(nodeLst.getLength());
        int nroLinks = nodeLst.getLength()/10+((nodeLst.getLength()%10==0) ? 0 : 1);
        String[] list = new String[nroLinks];
        Arrays.fill(list, "http://www.expo-studyabroad.com/sp/expo/PromocaoGravarIndicacao.asp?CodigoUsuarioMalaDireta=383896&");
        int ix = -1;
        for (int i = 0; i<nodeLst.getLength(); i++) {
            Node node = nodeLst.item(i);
            String contact = node.getChildNodes().item(0).getNodeValue();
            if (i%10==0) {
                ix++;
            }
            String nombre = contact.substring(0, contact.indexOf("@"));
            list[ix] += "nomeIndicado"+(i%10+1)+"="+nombre+"&"+"emailIndicado"+(i%10+1)+"="+contact+"&";
        }
        for (int i = 0; i<list.length; i++) {
            list[i] += "liregulamento=1";
            fw.write(list[i]);
            fw.newLine();
            System.out.println(list[i]);
        }
        System.out.println(list.length);
        fw.close();
//            System.out.println(Arrays.deepToString(list));        
        return list;
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Main().go();
//        new Main().create("D:/Contactos para raulbrood (hotmail).ctt");
    }
}
