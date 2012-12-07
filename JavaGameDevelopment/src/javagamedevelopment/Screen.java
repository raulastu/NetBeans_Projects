package javagamedevelopment;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import javax.swing.JFrame;

public class Screen {

    private GraphicsDevice vc;

    public Screen() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        vc = ge.getDefaultScreenDevice();
    }

    public void setFullScreen(DisplayMode dm, JFrame window) {
        window.setUndecorated(true);
        window.setResizable(false);
        vc.setFullScreenWindow(window);

        System.err.println(vc.isDisplayChangeSupported());
        if (dm != null && vc.isDisplayChangeSupported()) {
            vc.setDisplayMode(dm);
        }
    }

    public Window getFullScreenWindow() {
        return vc.getFullScreenWindow();
    }

    public void restoreScreen() {
        Window w = vc.getFullScreenWindow();
        if (w != null) {
            w.dispose();
        }
        vc.setFullScreenWindow(null);
    }
}
