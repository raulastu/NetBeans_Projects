
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;

public class MRU3 extends Problem{

    class Pantalla extends JPanel {

        int x = 10;
        int x2 = 30;
        String titulo = "MRU 3 - Un movil con mas velocidad";

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
            //1er movil
            
            gp.setFont(new Font("verdana",Font.BOLD,16));
            gp.drawString(titulo, 200, 50);
            gp.fillOval(x, 150, 10, 10);
            gp.setColor(new JFrame().getBackground());
            gp.drawOval(x, 150, 10, 10);
            x++;
            //2do movil
            gp.setColor(Color.BLACK);
            gp.fillOval(x2, 150, 10, 10);
            gp.setColor(new JFrame().getBackground());
            gp.drawOval(x2, 150, 10, 10);
            x2++;    

        }
    }

    public void start() {
        JFrame frame = new JFrame();
        Pantalla pant = new Pantalla();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(pant);
        frame.getContentPane().setBackground(Color.white);
        frame.setSize(400, 400);
        frame.setVisible(true);
        pant.goPaint();
    }

    public static void main(String[] args) {
        new MRU3().start();
    }
}


