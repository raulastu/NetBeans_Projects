package admin.concurso;

import admin.CompareTwoFiles2;
import admin.ConcursoPanel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Concurso {

    ArrayList<Problema> problemList = new ArrayList<Problema>();
    ArrayList<IOPair> tempIOPairList = new ArrayList<IOPair>();

    void agregarProblema(String nombre, int pts) {
        if (tempIOPairList.size() == 0) {
            JOptionPane.showMessageDialog(null, "debe haber al menos una pareja de Input/Output");
            return;
        }
        if (nombre.equals("")) {
            JOptionPane.showMessageDialog(null, "Nombre de problema No válido");
            return;
        }
        Problema problema = new Problema(nombre, pts, tempIOPairList);
        problemList.add(problema);
        tempIOPairList = new ArrayList<IOPair>();
    }

    void modificarProblema(int index, String nombre, int pts) {
        problemList.get(index).setNombre(nombre);
        problemList.get(index).setPuntos(pts);
    }

    protected void eliminarProblema(int index) {
        problemList.remove(index);
        tempIOPairList = new ArrayList<IOPair>();
    }

    protected int getTotalPoints() {
        int res = 0;
        for (Problema problema : problemList) {
            res += problema.getPuntos();
        }
        return res;
    }

    protected void addIOPairToTempList(String inFilePath, String outFilePath) {
        if (inFilePath.equals("")) {
            JOptionPane.showMessageDialog(null, "Eliga Input");
            return;
        }
        if (outFilePath.equals("")) {
            JOptionPane.showMessageDialog(null, "Eliga Output");
            return;
        }
        if (inFilePath.equals(outFilePath)) {
            JOptionPane.showMessageDialog(null, "Input y Output no pueden ser el mismo archivo");
            return;
        }
        byte[] input = getBytesFromPath(inFilePath);
        byte[] output = getBytesFromPath(outFilePath);
        for (IOPair iOPair : tempIOPairList) {
            boolean areInputsEq = CompareTwoFiles2.sonReallyIguales(iOPair.getInput(), input, new StringBuffer());
            if (areInputsEq) {
                JOptionPane.showMessageDialog(null, "Ya existe el Input en la lista");
                return;
            }
            boolean areOutputEq = CompareTwoFiles2.sonIguales(iOPair.getOutput(), output, new StringBuffer());
            if (areOutputEq) {
                JOptionPane.showMessageDialog(null, "Ya existe el Output en la lista");
                return;
            }
        }
        int nro = tempIOPairList.size() + 1;
        IOPair p = new IOPair(nro, input, output);
        tempIOPairList.add(p);
    }

    static private byte[] getBytesFromPath(String path) {
        FileInputStream fis = null;
        byte[] bytes = null;
        try {
            File file = new File(path);
            fis = new FileInputStream(file);
            bytes = new byte[(int) file.length()];
            fis.read(bytes, 0, (int) file.length());
            fis.close();
        } catch (IOException ex) {
            Logger.getLogger(ConcursoPanel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(ConcursoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        assert bytes != null;
        return bytes;
    }
}
