package gui;

import java.awt.Dimension;
import java.util.*;
import Filosofos.*;

/**
 *
 * @author raul ramirez
 */
public class CenaGui extends javax.swing.JPanel {

    /** Creates new form Panel */
    Cena cena;
    int r;
    int nroFilosofos;

    public CenaGui(Cena c) {
        this.cena = c;
        nroFilosofos = cena.tamaño();
        r = nroFilosofos*25;
        initComponents();
        int lado = 2*r+111;
        setPreferredSize(new Dimension(lado, lado));
        initMyFilosofos();
    }

    void initMyFilosofos() {
        int[][] coors = getXY();
        Filosofo p = cena.primero;
        int i = 0;
        do {
            int[] is = coors[i++];
            int mx = is[0]-p.gui.getPreferredSize().width/2;
            int my = is[1]-p.gui.getPreferredSize().height/2;
            add(p.gui);
            System.out.println(p.gui.getPreferredSize());
            p.gui.setBounds(mx, my, p.gui.getPreferredSize().width, p.gui.getPreferredSize().height);
            p = p.der;
        } while (p!=cena.primero);
        System.out.println(Arrays.deepToString(coors));
    }

    public int[][] getXY() {
        int xc = this.getPreferredSize().width/2;
        int yc = this.getPreferredSize().height/2;
        int[][] coors = new int[nroFilosofos][2];
        double a = 2*Math.PI/nroFilosofos;
        for (int i = 0; i<coors.length; i++) {
            coors[i][0] = (int) (xc+r*Math.sin(a*i));
            coors[i][1] = (int) (yc+r*Math.cos(a*i));
        }
        System.out.println("xc = "+xc);
        System.out.println("yc = "+yc);
        return coors;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(680, 680));
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
//    public static void main(String[] args) {
//        Cena c = new Cena();
//        for (int i = 0; i<10; i++) {
//            c.ingresar(Estado.COMIENDO, 1, 2, 6);
//        }
//        RCContainer.showPanel(new CenaGui(c));
//
////        for (int i = 1; i<1000; i++) {
////            FilosofoGUI[] a = new FilosofoGUI[i];
////            int rs =new MyPanel(a).initMyFilosofos();
//////            System.out.println(i +" "+rs);
////            assert i == rs:i+" "+rs;
//////            RCContainer.showPanel();
////        }
//    }
}
