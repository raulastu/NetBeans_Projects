
/*
 * Inexistente.java
 *
 * Created on Feb 9, 2009, 10:10:11 PM
 */
package inexistentes;

import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author rCuser
 */
public class GUIInexistente extends javax.swing.JPanel {

    public GUIInexistente(int correlativo, String codPost, String aulaAnt, String aulaSig) {
        initComponents();
        this.txtCorrelativo.setText(correlativo+"");
        this.txtCodiPost.setText(codPost);
        this.txtAulaAnt.setText(aulaAnt);
        this.txtAulaSig.setText(aulaSig);
    }

    public void mark(Color color) {
        txtCorrelativo.setBackground(color);
        txtCodiPost.setBackground(color);
        txtAulaAnt.setBackground(color);
        txtAulaSig.setBackground(color);
    }

    /** Creates new form Inexistente */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCorrelativo = new javax.swing.JTextField();
        txtCodiPost = new javax.swing.JTextField();
        txtAulaAnt = new javax.swing.JTextField();
        txtAulaSig = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCorrelativo.setBackground(new java.awt.Color(255, 255, 255));
        txtCorrelativo.setEditable(false);
        txtCorrelativo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCorrelativo.setText("10000");
        add(txtCorrelativo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 30));

        txtCodiPost.setBackground(new java.awt.Color(255, 255, 255));
        txtCodiPost.setEditable(false);
        txtCodiPost.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCodiPost.setText("01234567891111");
        add(txtCodiPost, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 120, 30));

        txtAulaAnt.setBackground(new java.awt.Color(255, 255, 255));
        txtAulaAnt.setEditable(false);
        txtAulaAnt.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtAulaAnt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAulaAnt.setText("1");
        txtAulaAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAulaAntActionPerformed(evt);
            }
        });
        add(txtAulaAnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 40, 30));

        txtAulaSig.setBackground(new java.awt.Color(255, 255, 255));
        txtAulaSig.setEditable(false);
        txtAulaSig.setFont(new java.awt.Font("Tahoma", 0, 14));
        txtAulaSig.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAulaSig.setText("100");
        add(txtAulaSig, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 40, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void txtAulaAntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAulaAntActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtAulaAntActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField txtAulaAnt;
    private javax.swing.JTextField txtAulaSig;
    private javax.swing.JTextField txtCodiPost;
    private javax.swing.JTextField txtCorrelativo;
    // End of variables declaration//GEN-END:variables
}
