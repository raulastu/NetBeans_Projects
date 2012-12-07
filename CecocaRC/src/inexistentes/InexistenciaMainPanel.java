package inexistentes;

import buqueda.PanBusqueda;
import cecocadb.DB;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import rclib.RCContainer;

/**
 *
 * @author rCuser
 */
public class InexistenciaMainPanel extends javax.swing.JPanel {

    PanInexistencia panInex;
    DB database;
    private PanBusqueda panBusqueda;
    JButton butAgregar = new JButton("Agregar");
    private JFrame frameAgregar;

    public InexistenciaMainPanel(DB database) throws SQLException {
        this.database = database;
        initComponents();
//        spaneContainer.setViewportView(panContainer);
        initMyComponents();
    }

    private void initMyComponents() throws SQLException {
        panBusqueda = new PanBusqueda(database);
        panInex = new PanInexistencia(database, 1, this);
        jTextPane1.setText(panInex.ine.comment);
        panContainer.add(panInex);

        butAgregar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String codiPostulante = panBusqueda.table.getSelectedValue(0);
                String aula = panBusqueda.table.getSelectedValue(4);
                String nombreCompleto = panBusqueda.table.getSelectedValue(1)+" "+
                        panBusqueda.table.getSelectedValue(2)+" "+panBusqueda.table.getSelectedValue(3);
                panInex.addReal(codiPostulante, aula, nombreCompleto);
                //
                frameAgregar.setVisible(false);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spaneContainer = new javax.swing.JScrollPane();
        panContainer = new javax.swing.JPanel();
        butUp = new javax.swing.JButton();
        butDown = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();

        spaneContainer.setViewportView(panContainer);

        butUp.setText("up");
        butUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butUpActionPerformed(evt);
            }
        });

        butDown.setText("down");
        butDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butDownActionPerformed(evt);
            }
        });

        jButton3.setText("Agregar Real");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jTextPane1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("jButton1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spaneContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 378, Short.MAX_VALUE)
                        .addComponent(butUp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butDown)
                        .addGap(233, 233, 233)
                        .addComponent(jButton3))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butUp)
                    .addComponent(butDown)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spaneContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                .addGap(58, 58, 58))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void butUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butUpActionPerformed
        int grupo = panInex.getSelectedRealGrupo();
        int index = panInex.getSelectedRealIndex();
        int newIndex = panInex.getGrupos()[grupo].swapUpReal(index);
        panInex.cargarReales();
        panInex.cargarRelaciones();
        panInex.select(grupo, newIndex);
        validate();
    }//GEN-LAST:event_butUpActionPerformed

    private void butDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butDownActionPerformed
        int grupo = panInex.getSelectedRealGrupo();
        int index = panInex.getSelectedRealIndex();
        int newIndex = panInex.getGrupos()[grupo].swapDownReal(index);
        panInex.cargarReales();
        panInex.cargarRelaciones();
        panInex.select(grupo, newIndex);
        validate();
    }//GEN-LAST:event_butDownActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (frameAgregar==null) {
            frameAgregar = new JFrame("Agregar un Postulante Real");
            frameAgregar.getContentPane().setLayout(new BorderLayout());
            frameAgregar.getContentPane().add(panBusqueda, BorderLayout.NORTH);
            JPanel panButton = new JPanel(new FlowLayout());
            panButton.add(butAgregar);
            frameAgregar.getContentPane().add(panButton, BorderLayout.SOUTH);
            frameAgregar.pack();
            frameAgregar.setVisible(true);
        } else {
            frameAgregar.setVisible(true);
        }
//        RCContainer.showPanel(panContainerBusqueda, true);
//        RCContainer.showPanel(panBusqueda, true);//GEN-HEADEREND:event_jButton3ActionPerformed
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton butDown;
    public javax.swing.JButton butUp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel panContainer;
    private javax.swing.JScrollPane spaneContainer;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) throws SQLException {
        DB database = new DB();
        RCContainer.showPanel(new InexistenciaMainPanel(database));
//        database.closeDB();
    }
}
