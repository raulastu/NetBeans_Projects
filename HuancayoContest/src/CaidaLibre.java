
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;

public class CaidaLibre extends Problem {

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

    public static void main(String[] args) {
        new CaidaLibre().start();
    }

    class Pantalla extends JPanel {

        int xo = 0;
        int yo = 0;
        int x;
        int y;
        int Vy;
        int Voy = 1;
        int Vox = 1;
        int t = 0;
        int aceleracion = 3;
        String titulo = "CAIDA LIBRE";

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
        int Vo = 50;
        int g = 10;
        int A = 60;
        int rec;
        int incrementer = 0;

        @Override
        public void paint(Graphics gp) {

            gp.setColor(Color.black);
            
            gp.setFont(new Font("verdana",Font.BOLD,16));
            gp.drawString(titulo, 200, 50);
            gp.fillOval(x, 150, 10, 10);
            gp.setColor(new JFrame().getBackground());
            gp.drawOval(x, 150, 10, 10);
            x++;
            if (x > 100 && x % 10 == 0) {
                gp.setColor(Color.black);
                gp.fillOval(x, 150 - y, 10, 10);
                gp.setColor(new JFrame().getBackground());
                gp.drawOval(x, 150 - y, 10, 10);
                y = y + incrementer;
                incrementer--;
            }

        }
    }
}


