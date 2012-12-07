package gui;


import javax.swing.JProgressBar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FilosofoGUI.java
 *
 * Created on Feb 3, 2009, 8:25:17 PM
 */
/**
 *
 * @author rCuser
 */
public class FilosofoGUI extends javax.swing.JPanel {

    /** Creates new form FilosofoGUI */
    public FilosofoGUI() {
        initComponents();
    }
    public void setCurrent(JProgressBar curr){
       prComiendo.setStringPainted(false);
       prEsperando.setStringPainted(false);
       prPensando.setStringPainted(false);
       curr.setStringPainted(true);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        prPensando = new javax.swing.JProgressBar();
        prEsperando = new javax.swing.JProgressBar();
        prComiendo = new javax.swing.JProgressBar();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        setPreferredSize(new java.awt.Dimension(75, 58));

        prPensando.setForeground(new java.awt.Color(51, 255, 0));
        prPensando.setString("pensando");
        prPensando.setStringPainted(true);

        prEsperando.setString("esperando");
        prEsperando.setStringPainted(true);

        prComiendo.setForeground(new java.awt.Color(255, 0, 0));
        prComiendo.setString("comiendo");
        prComiendo.setStringPainted(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(prEsperando, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
            .addComponent(prPensando, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
            .addComponent(prComiendo, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(prPensando, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prEsperando, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prComiendo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JProgressBar prComiendo;
    public javax.swing.JProgressBar prEsperando;
    public javax.swing.JProgressBar prPensando;
    // End of variables declaration//GEN-END:variables

}