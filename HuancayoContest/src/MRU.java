
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;

public class MRU extends Problem{

    

    public void start() {
        JFrame frame = new JFrame();
        Pantalla pant = new Pantalla();
        frame.add(pant);
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
        pant.goPaint();
    }

    class Pantalla extends JPanel {

        int startMovil = 10;
        String titulo = "MRU";

        public Pantalla() {
            setSize(new Dimension(400, 400));
            this.setOpaque(true);
            this.setBackground(Color.white);
        }

        public void goPaint() {
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(10);
                    repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        }

        @Override
        public void paint(Graphics gp) {
            
            gp.setFont(new Font("verdana",Font.BOLD,16));
            gp.drawString(titulo, 200, 50);
            gp.fillOval(startMovil, 150, 10, 10);
            gp.setColor(new JFrame().getBackground());
            gp.drawOval(startMovil, 150, 10, 10);
            startMovil++;
        }
    }

    public static void main(String[] args) {
        new MRU().start();
    }
}

