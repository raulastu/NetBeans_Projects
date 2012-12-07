package PaqueteCapaPresentacion;
import PaqueteLogicaDelNegocio.EmpleadoDAO;
import PaqueteLogicaDelNegocio.GestiónDeReporte;
import javax.swing.JOptionPane;
public class JFrameSistemaDeMenu extends javax.swing.JFrame {
        public JFrameSistemaDeMenu() {
        initComponents();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBarPrincipal = new javax.swing.JMenuBar();
        jMenuAlumno = new javax.swing.JMenu();
        jMenuItemInsertarAlumno = new javax.swing.JMenuItem();
        jMenuItemEliminarAlumno = new javax.swing.JMenuItem();
        jMenuProceso = new javax.swing.JMenu();
        jMenuItemProcesarPromedio = new javax.swing.JMenuItem();
        jMenuReporte = new javax.swing.JMenu();
        jMenuItemReporte = new javax.swing.JMenuItem();
        jMenuItemReporte2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuSalir = new javax.swing.JMenu();
        jMenuItemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenuAlumno.setText("Empleado");

        jMenuItemInsertarAlumno.setText("Insertar");
        jMenuItemInsertarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInsertarAlumnoActionPerformed(evt);
            }
        });
        jMenuAlumno.add(jMenuItemInsertarAlumno);

        jMenuItemEliminarAlumno.setText("Eliminar");
        jMenuItemEliminarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEliminarAlumnoActionPerformed(evt);
            }
        });
        jMenuAlumno.add(jMenuItemEliminarAlumno);

        jMenuBarPrincipal.add(jMenuAlumno);

        jMenuProceso.setText("Plantilla");

        jMenuItemProcesarPromedio.setText("Calcular Plantilla");
        jMenuItemProcesarPromedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProcesarPromedioActionPerformed(evt);
            }
        });
        jMenuProceso.add(jMenuItemProcesarPromedio);

        jMenuBarPrincipal.add(jMenuProceso);

        jMenuReporte.setText("Reporte");
        jMenuReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuReporteActionPerformed(evt);
            }
        });

        jMenuItemReporte.setText("Reportar Plantilla");
        jMenuItemReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReporteActionPerformed(evt);
            }
        });
        jMenuReporte.add(jMenuItemReporte);

        jMenuItemReporte2.setText("Emitir Boleta De Pago");
        jMenuItemReporte2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReporte2ActionPerformed(evt);
            }
        });
        jMenuReporte.add(jMenuItemReporte2);

        jMenuItem1.setText("Reporte");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuReporte.add(jMenuItem1);

        jMenuBarPrincipal.add(jMenuReporte);

        jMenuSalir.setText("Salir");

        jMenuItemSalir.setText("Salir del sistema");
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        jMenuSalir.add(jMenuItemSalir);

        jMenuBarPrincipal.add(jMenuSalir);

        setJMenuBar(jMenuBarPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jMenuItemInsertarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInsertarAlumnoActionPerformed
// TODO add your handling code here:
//GEN-LAST:event_jMenuItemInsertarAlumnoActionPerformed
JFrameSistemaDeMenu frame= new JFrameSistemaDeMenu();
dialogo=new JDialogInsertarEmpleado(frame,true);
dialogo.setSize(400, 400);
dialogo.setLocationRelativeTo(frame);
dialogo.setVisible(true);
}



private void jMenuItemReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReporteActionPerformed

GestiónDeReporte oClaseReporteSinParametro=new GestiónDeReporte();//GEN-LAST:event_jMenuItemReporteActionPerformed
oClaseReporteSinParametro.ejecutarReporte("src/PaqueteCapaPresentacion/PlanillaDeEmpleados.jasper");

}


private void jMenuItemProcesarPromedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProcesarPromedioActionPerformed
int respuesta = JOptionPane.showConfirmDialog(this, "¿Esta seguro...?",//GEN-LAST:event_jMenuItemProcesarPromedioActionPerformed
                  "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
  if (respuesta == JOptionPane.YES_OPTION) 
    {
       EmpleadoDAO oAlumnoDAO=new EmpleadoDAO();
       oAlumnoDAO.procesarCalcularPlanilla();
    }
}

private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed


int respuesta = JOptionPane.showConfirmDialog(this, "¿Esta seguro...?",//GEN-LAST:event_jMenuItemSalirActionPerformed
                  "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
  if (respuesta == JOptionPane.YES_OPTION) 
    {
      dispose();
    }

}                                              

private void jMenuReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuReporteActionPerformed
// TODO add your handling code here:
//GEN-LAST:event_jMenuReporteActionPerformed
}
private void jMenuItemEliminarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarAlumnoActionPerformed
    // TODO add your handling code here:
    JFrameSistemaDeMenu frame= new JFrameSistemaDeMenu();
dialogo=new JDialogEliminarEmpleado(frame,true);
dialogo.setSize(400, 380);
dialogo.setLocationRelativeTo(frame);
dialogo.setVisible(true);

}//GEN-LAST:event_jMenuItemEliminarAlumnoActionPerformed

private void jMenuItemReporte2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReporte2ActionPerformed
   GestiónDeReporte oClaseReporteSinParametro=new GestiónDeReporte();
oClaseReporteSinParametro.ejecutarReporte("src/PaqueteCapaPresentacion/BoletaDePagos.jasper");
}//GEN-LAST:event_jMenuItemReporte2ActionPerformed

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    // TODO add your handling code here:
GestiónDeReporte oClaseReporteSinParametro=new GestiónDeReporte();
oClaseReporteSinParametro.ejecutarReporte("src/PaqueteCapaPresentacion/reporte.jasper");
}//GEN-LAST:event_jMenuItem1ActionPerformed
                                            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenuAlumno;
    private javax.swing.JMenuBar jMenuBarPrincipal;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemEliminarAlumno;
    private javax.swing.JMenuItem jMenuItemInsertarAlumno;
    private javax.swing.JMenuItem jMenuItemProcesarPromedio;
    private javax.swing.JMenuItem jMenuItemReporte;
    private javax.swing.JMenuItem jMenuItemReporte2;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenu jMenuProceso;
    private javax.swing.JMenu jMenuReporte;
    private javax.swing.JMenu jMenuSalir;
    // End of variables declaration//GEN-END:variables
private javax.swing.JDialog dialogo;
}
