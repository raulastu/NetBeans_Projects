package cliente;


public class FrameSolicitarClarificacion extends javax.swing.JFrame {

    Cliente cliente;

    public FrameSolicitarClarificacion(Cliente cliente) {
        super(Cliente.clienteName);
        initComponents();
        this.cliente = cliente;
        loadCategories();
    }

    public void loadCategories() {
        cmbCategoria.removeAllItems();
        cmbCategoria.addItem("General");
        if (cliente.getPropiedadesConcurso().getProperty("estado").equals("started")) {
            for (int i = 0; i < cliente.problems.size(); i++) {
                cmbCategoria.addItem(cliente.problems.get(i)[1]);
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbCategoria = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPregunta = new javax.swing.JTextArea();
        butEnviarClar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        labCounter = new javax.swing.JLabel();

        setAlwaysOnTop(true);

        jLabel1.setText("Categoría:");

        txtPregunta.setColumns(20);
        txtPregunta.setRows(5);
        txtPregunta.setWrapStyleWord(true);
        txtPregunta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPreguntaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtPregunta);

        butEnviarClar.setText("Enviar");
        butEnviarClar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEnviarClarActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        labCounter.setText("200");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbCategoria, 0, 276, Short.MAX_VALUE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(butEnviarClar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(139, 139, 139)
                            .addComponent(jButton2))
                        .addComponent(labCounter)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(butEnviarClar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void txtPreguntaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPreguntaKeyReleased
        labCounter.setText((200 - txtPregunta.getText().length()) + "");
        if (200 - txtPregunta.getText().length() <= 0) {
            //jTextArea1.setEditable(false);
            txtPregunta.setText(txtPregunta.getText().substring(0, 199));
        }
}//GEN-LAST:event_txtPreguntaKeyReleased

    private void butEnviarClarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEnviarClarActionPerformed
        String msg = "clar," + cmbCategoria.getSelectedItem() + "," + txtPregunta.getText();
        cliente.sendToServer(msg);
        cmbCategoria.setSelectedIndex(0);
        txtPregunta.setText("");
        this.setVisible(false);
}//GEN-LAST:event_butEnviarClarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton butEnviarClar;
    protected javax.swing.JComboBox cmbCategoria;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labCounter;
    private javax.swing.JTextArea txtPregunta;
    // End of variables declaration//GEN-END:variables
}