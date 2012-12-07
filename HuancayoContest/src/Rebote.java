
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;

public class Rebote extends Problem {

    public void start() {
        JFrame frame = new JFrame();
        Pantalla pant = new Pantalla();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(pant);
        frame.getContentPane().setBackground(Color.white);
        frame.setSize(800, 400);
        frame.setVisible(true);
        pant.goPaint();
    }

    public static void main(String[] args) {
        new Rebote().start();
    }

    class Pantalla extends JPanel {

        int x = 0;
        int y = 0;
        int incrementer = 10;
        String titulo = "Rebotes";

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
        int[] primerRebote = {0, 50};
        //altura = 110;
        int H = 110;
        int totalRebote = 0;

        @Override
        public void paint(Graphics gp) {
            //1er movil
            gp.fill3DRect(0, 100, H, 10, false);
            gp.fill3DRect(100, 100, 10, H, false);
            gp.fill3DRect(100, 200, H, 10, false);
            gp.fill3DRect(200, 200, 10, H, false);
            gp.fill3DRect(200, 300, H, 10, false);

            gp.setFont(new Font("verdana",Font.BOLD,16));
            gp.drawString(titulo, 100, 50);
            gp.setFont(new Font("verdana",Font.BOLD,12));
            switch (x) {
                case 140:
                    incrementer = 10;
                    break;
                case 280:
                    incrementer = 10;
                    break;
            }

            gp.fillOval(10 + x, 100 - y, 10, 10);
            gp.setColor(new JFrame().getBackground());
            gp.drawOval(10 + x, 100 - y, 10, 10);
            x = x + 5;
            y = y + incrementer;
            totalRebote += Math.abs(incrementer);
            if (incrementer == 0) {
                gp.setColor(Color.black);
                //Altura rebote
                System.out.println(totalRebote);
                gp.drawString(" Altura Rebote = " + totalRebote, x + 200, y);
            }

            incrementer--;
        }
    }
}

