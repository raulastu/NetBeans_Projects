package javagamedevelopment;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Main extends JFrame {

    public static void main(String[] args) {
        DisplayMode dm = new DisplayMode(800, 600, 16, 60);
        Main frame = new Main();
        frame.run(dm);
    }

    public void run(DisplayMode dm) {
        setBackground(Color.PINK);
        setForeground(Color.WHITE);
//        setVisible(true);

        setFont(new Font("Arial", Font.PLAIN, 24));
        Screen s = new Screen();
        try {
            s.setFullScreen(dm, this);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
            }
        } finally {
            s.restoreScreen();
        }
    }

    @Override
    public void paint(Graphics g) {
        if (g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        g.drawString("this is gonna be awesome", 200, 200);
    }
}
