package gui;


import gui.Estado;


/**
 *
 * @author rCuser
 */
public class FilosofoValuesPanel extends javax.swing.JPanel {

    /** Creates new form FilosofoValuesPanel */
    public FilosofoValuesPanel(int pensar, int esperar, int comer, Estado estado) {
        initComponents();
        spPensar.setValue(pensar);
        spEsperar.setValue(esperar);
        spComer.setValue(comer);
        cmbEstado.setSelectedItem(estado.toString());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spPensar = new javax.swing.JSpinner();
        spEsperar = new javax.swing.JSpinner();
        spComer = new javax.swing.JSpinner();
        cmbEstado = new javax.swing.JComboBox();

        spPensar.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));

        spEsperar.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));

        spComer.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PENSANDO", "ESPERANDO", "COMIENDO" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(spPensar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spEsperar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spComer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(spPensar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(spEsperar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(spComer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox cmbEstado;
    public javax.swing.JSpinner spComer;
    public javax.swing.JSpinner spEsperar;
    public javax.swing.JSpinner spPensar;
    // End of variables declaration//GEN-END:variables
}
