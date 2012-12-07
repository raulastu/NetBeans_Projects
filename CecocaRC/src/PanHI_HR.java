
import cecocadb.DB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import rclib.RCContainer;

public class PanHI_HR extends javax.swing.JPanel {

    private JTable tableHIs;
    private JTable tableHRs;
    DB database;
    Validacion validacion;
    int id_concurso;

    public PanHI_HR(DB database, int id_concurso) {
        this.id_concurso = id_concurso;
        this.database = database;
        initComponents();
        initMyComponents();
    }

    public boolean calculate() throws SQLException {
        boolean ok = false;
        Object[] results = validacion.checkConsistenciaHI_HR(id_concurso);
        ok = (Boolean) results[0];
        if ((Boolean) results[0]) {
            labEstado.setText("Passed HI-HR test");
        } else {
            labEstado.setText("Failed HI-HR test");
            ArrayList<String[]> al = (ArrayList<String[]>) results[3];
            String[][] data = new String[al.size()][1];
            for (int i = 0; i<al.size(); i++) {
                data[i] = al.get(i);
            }
            String[] columnNames = {"Correlativo", "LitoCodigo", "Codigo Postulante", "Nro Aula"};
            tableHIs = new JTable(new HITableModel(data, columnNames));
            spHI.setViewportView(tableHIs);
            tableHIs.revalidate();
            ArrayList<String> al2 = (ArrayList<String>) results[4];
            data = new String[al2.size()][1];
            for (int i = 0; i<al2.size(); i++) {
                data[i][0] = al2.get(i);
            }
            tableHRs = new JTable(new HITableModel(data, new String[]{"LitoCodigo"}));
            spHR.setViewportView(tableHRs);
            tableHRs.revalidate();
            setVisible(false);
            setVisible(true);
//                System.err.println(fillDashes("-", guiones));
        }
        labHI.setText(results[1]+"");
        labHR.setText(results[2]+"");
        return ok;
    }

    private void initMyComponents() {
        validacion = new Validacion(database);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spHR = new javax.swing.JScrollPane();
        spHI = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labHR = new javax.swing.JLabel();
        labHI = new javax.swing.JLabel();
        labEstado = new javax.swing.JLabel();
        butVerificar = new javax.swing.JButton();

        spHR.setBorder(javax.swing.BorderFactory.createTitledBorder("Hojas de Respuestas sobrantes"));

        spHI.setBorder(javax.swing.BorderFactory.createTitledBorder("Hojas de Identificación sobrantes"));

        jLabel1.setText("Cantidad de Hojas de Indentificación:");

        jLabel2.setText("Cantidad de Hojas de Respuestas:");

        labHR.setText("jLabel3");

        labHI.setText("jLabel4");

        butVerificar.setText("Run!");
        butVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butVerificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spHR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                    .addComponent(spHI, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 319, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labHR)
                            .addComponent(labHI)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(butVerificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(butVerificar)
                    .addComponent(labEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labHI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labHR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spHI, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spHR, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {spHI, spHR});

    }// </editor-fold>//GEN-END:initComponents

    private void butVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butVerificarActionPerformed
        try {
            calculate();
        // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(PanHI_HR.class.getName()).log(Level.SEVERE, null, ex);
        }
    // TODO add your handling code here:
    }//GEN-LAST:event_butVerificarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butVerificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labEstado;
    private javax.swing.JLabel labHI;
    private javax.swing.JLabel labHR;
    private javax.swing.JScrollPane spHI;
    private javax.swing.JScrollPane spHR;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        DB database = new DB();
        int id_concurso = 1;
        RCContainer.showPanel(new PanHI_HR(database, id_concurso));
//        database.closeDB();
    }
}
