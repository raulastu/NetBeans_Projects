package inexistentes;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author rCuser
 */
public class GUIRelacion extends javax.swing.JPanel {

    Color checkColor = new java.awt.Color(162, 235, 160);
    Color uncheckColor = new java.awt.Color(170, 95, 48);
    boolean lock = false;

    public GUIRelacion() {
        initComponents();
        jLabel1.setVisible(false);
        jLabel1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (lock) {
                    unlock();
                } else {
                    lock();
                }
                lock = !lock;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel1.setBorder(new LineBorder(Color.BLACK));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel1.setBorder(null);
            }
        });
    }

    private void lock() {
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checkmark.png"))); // NOI18N
    }

    private void unlock() {
        jLabel1.setIcon(null);
    }

    public void check() {
        setBackground(checkColor);
        setVisible(false);
        setVisible(true);
        jLabel1.setVisible(true);
    }

    public void uncheck() {
        setBackground(uncheckColor);
        setVisible(false);
        setVisible(true);
        jLabel1.setVisible(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(170, 95, 48));
        setPreferredSize(new java.awt.Dimension(78, 30));
        setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(jLabel1);
        jLabel1.setBounds(0, 0, 30, 30);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}