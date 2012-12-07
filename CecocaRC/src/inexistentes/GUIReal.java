package inexistentes;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author rCuser
 */
public class GUIReal extends javax.swing.JPanel {

    public GUIReal(String codiPost, String aula, String nombres) {
        initComponents();
        txtCodiPost.setText(codiPost);
        txtAula.setText(aula);
        txtNombres.setText(nombres);
    }

    public void mark(Color color) {
        txtCodiPost.setBackground(color);
        txtAula.setBackground(color);
        txtNombres.setBackground(color);
    }

    public void addMAdapter(MouseAdapter ma) {
        addMouseAdapter(ma);
        addMouseAdapter(new resaltEfect());
    }

    public void addMouseAdapter(MouseAdapter ma) {
        this.addMouseListener(ma);
        txtCodiPost.addMouseListener(ma);
        txtAula.addMouseListener(ma);
        txtNombres.addMouseListener(ma);
    }

    public void select() {
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        txtCodiPost.setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.red));
        txtAula.setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.red));
        txtNombres.setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.red));
    }

    public void deselect() {
        JTextField b = new JTextField();
        txtCodiPost.setBorder(b.getBorder());
        txtAula.setBorder(b.getBorder());
        txtNombres.setBorder(b.getBorder());
    }

    class resaltEfect extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            select();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCodiPost = new javax.swing.JTextField();
        txtAula = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCodiPost.setBackground(new java.awt.Color(255, 255, 255));
        txtCodiPost.setEditable(false);
        txtCodiPost.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCodiPost.setText("CODI_POST");
        add(txtCodiPost, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 30));

        txtAula.setBackground(new java.awt.Color(255, 255, 255));
        txtAula.setEditable(false);
        txtAula.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAula.setText("1");
        add(txtAula, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 40, 30));

        txtNombres.setBackground(new java.awt.Color(255, 255, 255));
        txtNombres.setEditable(false);
        txtNombres.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombres.setText("VALDERRAMA DE LOS SAMANAMUD JUAN OSCAR ALBERTO");
        txtNombres.setPreferredSize(new java.awt.Dimension(400, 23));
        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });
        add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 360, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtNombresActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField txtAula;
    private javax.swing.JTextField txtCodiPost;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
}
