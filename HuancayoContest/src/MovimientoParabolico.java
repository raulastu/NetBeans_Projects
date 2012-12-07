
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;

public class MovimientoParabolico extends Problem{

    public void start() {   
        JFrame frame = new JFrame();
        PantallaMP pant = new PantallaMP();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.add(pant);
        frame.getContentPane().setBackground(Color.white);
        frame.setSize(800, 400);
        frame.setVisible(true);
        pant.goPaint();
    }

    public static void main(String[] args) {
        new MovimientoParabolico().start();
    }

    class PantallaMP extends JPanel {

        int xo = 0;
        int yo = 0;
        int x;
        int y;
        int Vy;
        int Voy = 1;
        int Vox = 1;
        int t = 0;
        int aceleracion = 3;
        String titulo = "MOVIMIENTO PARABOLICO";
  
        public PantallaMP() {
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
                    Logger.getLogger(PantallaMP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        int Vo = 50;
        int g = 10;
        int A = 60;
        int rec;
        int incrementer = 10;

        @Override
        public void paint(Graphics gp) {
 
            gp.setFont(new Font("verdana",Font.BOLD,16));
            gp.drawString(titulo, 200, 50);
            gp.fillOval(x, 300 - y, 10, 10);
            gp.setColor(new JFrame().getBackground());
            gp.drawOval(x, 300 - y, 10, 10);
            t++;
            x = x + 10;
            y = y + incrementer;
            incrementer--;
        }   
    }
}


