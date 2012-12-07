package gui;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author rC
 */
public class RCContainer {

    public static void centerFrame(JFrame frame) {
        frame.setLocation(
                (int) (frame.getToolkit().getScreenSize().getWidth() / 2 -
                frame.getSize().getWidth() / 2),
                (int) (frame.getToolkit().getScreenSize().getHeight() / 2 -
                frame.getSize().getHeight() / 2));
    }

    public static void showFrame(JFrame frame2) {
        JScrollPane scr = new JScrollPane();

        scr.setVerifyInputWhenFocusTarget(true);
        //panel.setPreferredSize(panel.getSize());        

        frame2.getContentPane().add(scr);
        //frame2.setSize(800, 600);
        //frame2.getContentPane().setLayout(new FlowLayout());
        frame2.pack();
        frame2.setLocation(
                (int) (frame2.getToolkit().getScreenSize().getWidth() / 2 -
                frame2.getSize().getWidth() / 2),
                (int) (frame2.getToolkit().getScreenSize().getHeight() / 2 -
                frame2.getSize().getHeight() / 2));
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);
    }

    public static void showPanel(JPanel panel) {
        JFrame frame = new JFrame("RCena de los Filosofos - by rC");
        JScrollPane scr = new JScrollPane();

        scr.setViewportView(panel);
        scr.setVerifyInputWhenFocusTarget(true);
        //panel.setPreferredSize(panel.getSize());        

        frame.getContentPane().add(scr);
        frame.setSize(800, 600);
        //frame2.getContentPane().setLayout(new FlowLayout());
        frame.pack();
        frame.setLocation(
                (int) (frame.getToolkit().getScreenSize().getWidth() / 2 -
                frame.getSize().getWidth() / 2),
                (int) (frame.getToolkit().getScreenSize().getHeight() / 2 -
                frame.getSize().getHeight() / 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     *
     * @param panel
     * @param alwaysOnTop
     */
    public static void showPanel(JPanel panel, boolean alwaysOnTop) {
        JFrame frame = new JFrame(panel.getClass().getCanonicalName());
        frame.setAlwaysOnTop(alwaysOnTop);
        JScrollPane scr = new JScrollPane();

        scr.setViewportView(panel);
        scr.setVerifyInputWhenFocusTarget(true);
        //panel.setPreferredSize(panel.getSize());        

        frame.getContentPane().add(scr);
        frame.setSize(800, 600);
        //frame2.getContentPane().setLayout(new FlowLayout());
        frame.pack();
        frame.setLocation(
                (int) (frame.getToolkit().getScreenSize().getWidth() / 2 -
                frame.getSize().getWidth() / 2),
                (int) (frame.getToolkit().getScreenSize().getHeight() / 2 -
                frame.getSize().getHeight() / 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
