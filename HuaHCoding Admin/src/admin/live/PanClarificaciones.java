package admin.live;

import admin.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.LinkedHashMap;

public class PanClarificaciones extends javax.swing.JPanel {

    public RCJTable tableClarificaciones;
    private Connection con;
    String idConcurso;
    private ServidorConcurso servidor;
    private FrameQAClar frameQAClar;

    public PanClarificaciones(Connection con, String idConcurso) {
        this.con = con;
        this.idConcurso = idConcurso;
        initComponents();
        iniciarMisComponentes();
    }

    public void setServidor(ServidorConcurso servidor) {
        this.servidor = servidor;
        frameQAClar = new FrameQAClar(servidor);
    }

    private void iniciarMisComponentes() {
        LinkedHashMap<String, String[]> columns = new LinkedHashMap<String, String[]>();
        columns.put("id_clarificacion", new String[]{"ID", "8"});
        columns.put("username", new String[]{"Usuario", "30"});
        columns.put("category", new String[]{"Categoría", "40"});
        columns.put("asked", new String[]{"Preguntado", "40"});
        columns.put("question", new String[]{"Pregunta", "100"});
        columns.put("answered", new String[]{"Respondido", "40"});
        columns.put("answer", new String[]{"Respuesta", "100"});
        String condition = "WHERE Id_Concurso = " + idConcurso;
        tableClarificaciones = new RCJTable(con, "Clarificacion", columns, condition);
        scrpTable.setViewportView(tableClarificaciones);
        tableClarificaciones.getDataArray();
        tableClarificaciones.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    verClarificacion();
                }
            }
        });

    }

    private void verClarificacion() {
        int id = Integer.parseInt("" + tableClarificaciones.getValueAt(tableClarificaciones.getSelectedRow(), 0));
        String user = tableClarificaciones.getValueAt(tableClarificaciones.getSelectedRow(), 1) + "";
        String cat = tableClarificaciones.getValueAt(tableClarificaciones.getSelectedRow(), 2) + "";
        String pregunta = tableClarificaciones.getValueAt(tableClarificaciones.getSelectedRow(), 4) + "";
        String respuesta = tableClarificaciones.getValueAt(tableClarificaciones.getSelectedRow(), 6) + "";
        frameQAClar.show(id, cat, user, pregunta, respuesta);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrpTable = new javax.swing.JScrollPane();
        verClariicacion = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        verClariicacion.setText("Ver y Responder Clarificación");
        verClariicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verClariicacionActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrpTable, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(verClariicacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrpTable, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verClariicacion)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void verClariicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verClariicacionActionPerformed
        verClarificacion();
}//GEN-LAST:event_verClariicacionActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int id = Integer.parseInt("" + tableClarificaciones.getValueAt(tableClarificaciones.getSelectedRow(), 0));
        servidor.eliminarClarificacion(id);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane scrpTable;
    private javax.swing.JButton verClariicacion;
    // End of variables declaration//GEN-END:variables
}
