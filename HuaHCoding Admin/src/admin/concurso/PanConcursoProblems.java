package admin.concurso;

import admin.RCContainer;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class PanConcursoProblems extends javax.swing.JPanel {

    public static int DEFAULT_VALOR_PROBLEM = 10;
    Concurso concurso = new Concurso();
    private JTable problemTable;
    private JTable actualIOPairTable;
    Color myYellow = new Color(255, 255, 204);
    private JFileChooser chooser = new JFileChooser();

    public PanConcursoProblems() {
        initComponents();
        problemTable = new JTable(new ProblemasTempTableModel());
        scrProblems.setViewportView(problemTable);

        actualIOPairTable = new JTable(new IOPairTableModel());
        scrIOPair.setViewportView(actualIOPairTable);

        enableProblemaForm(true);
        actualIOPairTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                int index = actualIOPairTable.getSelectedRow();
                selectRowInActualIOPairTable(index);
            }
        });


        // PROBLEMS TABLE LISTENER
        problemTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                enableProblemaForm(false);
                int index = problemTable.getSelectedRow();
                Problema problemaSeleccionado = concurso.problemList.get(index);
                concurso.tempIOPairList = problemaSeleccionado.getIoList();
                selectRowInActualIOPairTable(0);
                actualIOPairTable.setRowSelectionInterval(0, 0);
                actualIOPairTable.revalidate();
                txtNombreProblema.setText(problemaSeleccionado.getNombre());
                spinPuntaje.setValue(problemaSeleccionado.getPuntos());
                butAgregarProblema.setText("Nuevo Problema");
            }
        });
    }

    private void selectRowInActualIOPairTable(int index) {
        txtInput.setText(new String((byte[]) concurso.tempIOPairList.get(index).getInput()));
        txtOutput.setText(new String((byte[]) concurso.tempIOPairList.get(index).getOutput()));
    }

    private void enableSpinPuntaje(boolean val) {
        JFormattedTextField tf = ((JSpinner.DefaultEditor) spinPuntaje.getEditor()).getTextField();
        tf.setEditable(val);
        spinPuntaje.getComponent(0).setEnabled(val);
        spinPuntaje.getComponent(1).setEnabled(val);
        tf.setBackground(val ? Color.white : myYellow);
    }

    public class ProblemasTempTableModel extends AbstractTableModel {

        String[] colNames = {"Nombre", "Puntos", "Número de Parejas InputOutput"};

        @Override
        public String getColumnName(int rowIndex) {
            return colNames[rowIndex];
        }

        public int getColumnCount() {
            return colNames.length;
        }

        public int getRowCount() {
            return concurso.problemList.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return concurso.problemList.get(rowIndex).getNombre();
                case 1:
                    return concurso.problemList.get(rowIndex).getPuntos();
                default:
                    return concurso.problemList.get(rowIndex).getIoList().size();
            }
        }
    }

    public class IOPairTableModel extends AbstractTableModel {

        private String[] colNames = {"Numero", "Input", "Output"};

        @Override
        public String getColumnName(int rowIndex) {
            return colNames[rowIndex];
        }

        public int getColumnCount() {
            return colNames.length;
        }

        public int getRowCount() {
            return concurso.tempIOPairList.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return concurso.tempIOPairList.get(rowIndex).getNumber();
                case 1:
                    return new String((byte[]) concurso.tempIOPairList.get(rowIndex).getInput());
                default:
                    return new String((byte[]) concurso.tempIOPairList.get(rowIndex).getOutput());
            }
        }
    }

    public void enableProblemaForm(boolean val) {
        txtNombreProblema.setText("");
        spinPuntaje.setValue(new Integer(DEFAULT_VALOR_PROBLEM));
        txtNombreProblema.setBackground(val ? Color.WHITE : myYellow);
        txtNombreProblema.setEditable(val);
        butExIn.setEnabled(val);
        butExOut.setEnabled(val);
        butAgregarParejaIO.setEnabled(val);
        txtInput.setText("");
        txtOutput.setText("");
        enableSpinPuntaje(val);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreProblema = new javax.swing.JTextField();
        spinPuntaje = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        butAgregarProblema = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        butExIn = new javax.swing.JButton();
        labInFilePath = new javax.swing.JLabel();
        labOutFilePath = new javax.swing.JLabel();
        butExOut = new javax.swing.JButton();
        butAgregarParejaIO = new javax.swing.JButton();
        scrIOPair = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInput = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        scrProblems = new javax.swing.JScrollPane();
        butEliminarProblema = new javax.swing.JButton();
        butModificarProblema = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        labTotalPuntos = new javax.swing.JLabel();

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 153), 2, true));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jLabel1.setText("Nombre del Problema:");

        txtNombreProblema.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        spinPuntaje.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        jLabel6.setText("Valor:"); // NOI18N

        butAgregarProblema.setText("Agregar Problema");
        butAgregarProblema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAgregarProblemaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreProblema, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinPuntaje, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(butAgregarProblema, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreProblema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(spinPuntaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butAgregarProblema))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Agregar Input / Output"));

        butExIn.setText("Elegir Input");
        butExIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butExInActionPerformed(evt);
            }
        });

        labInFilePath.setBackground(new java.awt.Color(255, 255, 204));
        labInFilePath.setAutoscrolls(true);
        labInFilePath.setOpaque(true);

        labOutFilePath.setBackground(new java.awt.Color(255, 255, 204));
        labOutFilePath.setOpaque(true);

        butExOut.setText("Elegir Output"); // NOI18N
        butExOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butExOutActionPerformed(evt);
            }
        });

        butAgregarParejaIO.setText("Agregar Pareja I/O");
        butAgregarParejaIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAgregarParejaIOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrIOPair, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(butExIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(butExOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labOutFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labInFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(butAgregarParejaIO, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(butExIn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labInFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(butExOut, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labOutFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(butAgregarParejaIO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrIOPair, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Input"));

        txtInput.setColumns(20);
        txtInput.setRows(5);
        jScrollPane1.setViewportView(txtInput);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Output"));

        txtOutput.setColumns(20);
        txtOutput.setRows(5);
        jScrollPane2.setViewportView(txtOutput);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel4, jPanel5});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );

        butEliminarProblema.setText("Eliminar Problema");
        butEliminarProblema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEliminarProblemaActionPerformed(evt);
            }
        });

        butModificarProblema.setText("Modificar Problema");
        butModificarProblema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butModificarProblemaActionPerformed(evt);
            }
        });

        jButton5.setText("REGISTRAR PROBLEMAS!!!");

        labTotalPuntos.setBackground(new java.awt.Color(255, 255, 204));
        labTotalPuntos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labTotalPuntos.setText("TOTAL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrProblems, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labTotalPuntos)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(butModificarProblema)
                            .addComponent(butEliminarProblema)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butModificarProblema)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(butEliminarProblema)
                            .addComponent(labTotalPuntos)))
                    .addComponent(scrProblems, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void butExInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butExInActionPerformed
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String absFile = chooser.getSelectedFile().getAbsoluteFile() + "";
            labInFilePath.setText(absFile);
        }
}//GEN-LAST:event_butExInActionPerformed

    private void butExOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butExOutActionPerformed

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String absFile = chooser.getSelectedFile().getAbsoluteFile() + "";
            labOutFilePath.setText(absFile);
        }
    }//GEN-LAST:event_butExOutActionPerformed

    private void butAgregarParejaIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAgregarParejaIOActionPerformed
        concurso.addIOPairToTempList(labInFilePath.getText(), labOutFilePath.getText());
        labInFilePath.setText("");
        labOutFilePath.setText("");
        actualIOPairTable.revalidate();
    }//GEN-LAST:event_butAgregarParejaIOActionPerformed

    private void butAgregarProblemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAgregarProblemaActionPerformed
        String lab = butAgregarProblema.getText();
        if (lab.equals("Nuevo Problema")) {
            butAgregarProblema.setText("Agregar Problema");
            concurso.tempIOPairList = new ArrayList<IOPair>();
            ((AbstractTableModel) actualIOPairTable.getModel()).fireTableDataChanged();
            enableProblemaForm(true);
        } else if (lab.equals("Guardar Cambios")) {
            butAgregarProblema.setText("Nuevo Problema");
            int index = problemTable.getSelectedRow();
            String nombre = txtNombreProblema.getText();
            int pts = (Integer) spinPuntaje.getValue();
            concurso.modificarProblema(index, nombre, pts);
            ((AbstractTableModel) problemTable.getModel()).fireTableRowsUpdated(index, index);
            enableProblemaForm(false);
        } else {
            String nombre = txtNombreProblema.getText();
            int pts = (Integer) (spinPuntaje.getValue());
            concurso.agregarProblema(nombre, pts);
            problemTable.revalidate();
            labTotalPuntos.setText(concurso.getTotalPoints() + "pts");
            actualIOPairTable.revalidate();
            txtNombreProblema.setText("");
            txtInput.setText("");
            txtOutput.setText("");
            enableProblemaForm(true);
        }
    }//GEN-LAST:event_butAgregarProblemaActionPerformed

    private void butEliminarProblemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEliminarProblemaActionPerformed
        int index = problemTable.getSelectedRow();
        concurso.eliminarProblema(index);
        problemTable.revalidate();
        actualIOPairTable.revalidate();
        enableProblemaForm(true);
    }//GEN-LAST:event_butEliminarProblemaActionPerformed

    private void butModificarProblemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butModificarProblemaActionPerformed
        int index = problemTable.getSelectedRow();
        butAgregarProblema.setText("Guardar Cambios");
        enableProblemaForm(true);
        txtNombreProblema.setText(concurso.problemList.get(index).getNombre());
        spinPuntaje.setValue(concurso.problemList.get(index).getPuntos());
    }//GEN-LAST:event_butModificarProblemaActionPerformed
    //
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAgregarParejaIO;
    private javax.swing.JButton butAgregarProblema;
    private javax.swing.JButton butEliminarProblema;
    private javax.swing.JButton butExIn;
    private javax.swing.JButton butExOut;
    private javax.swing.JButton butModificarProblema;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labInFilePath;
    private javax.swing.JLabel labOutFilePath;
    private javax.swing.JLabel labTotalPuntos;
    private javax.swing.JScrollPane scrIOPair;
    private javax.swing.JScrollPane scrProblems;
    private javax.swing.JSpinner spinPuntaje;
    private javax.swing.JTextArea txtInput;
    private javax.swing.JTextField txtNombreProblema;
    private javax.swing.JTextArea txtOutput;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        PanConcursoProblems pcp = new PanConcursoProblems();
        RCContainer.showPanel(pcp);
        Concurso con = new Concurso();
        con.addIOPairToTempList("C:/problem1.in", "C:/problem1.sol");
        con.addIOPairToTempList("C:/problem2.in", "C:/problem2.sol");
        con.agregarProblema("Test", 10);
        con.addIOPairToTempList("E:/ConcursoProgramacion/CI B6/CI B6 Problems/A_CodigoPrefijo/CodigoPrefijo.in",
                "E:/ConcursoProgramacion/CI B6/CI B6 Problems/A_CodigoPrefijo/CodigoPrefijo.sol");
        con.agregarProblema("CodigoPrefijo", 10);
        pcp.concurso = con;
    }
}
