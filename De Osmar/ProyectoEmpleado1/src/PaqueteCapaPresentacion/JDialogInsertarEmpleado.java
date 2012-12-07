package PaqueteCapaPresentacion;
import PaqueteLogicaDelNegocio.Empleado;
import PaqueteLogicaDelNegocio.EmpleadoDAO;
import javax.swing.JOptionPane;
public class JDialogInsertarEmpleado extends javax.swing.JDialog
  {
    public JDialogInsertarEmpleado(java.awt.Frame parent, boolean modal)
      {
        super(parent, modal);
        initComponents();
      }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonReiniciar = new javax.swing.JButton();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelEmpleado_id = new javax.swing.JLabel();
        jLabelApellidos = new javax.swing.JLabel();
        jLabelNombres = new javax.swing.JLabel();
        jLabelCorreo = new javax.swing.JLabel();
        jLabelSucursal = new javax.swing.JLabel();
        jLabelCodigoDeSindicato = new javax.swing.JLabel();
        jLabelSueldoBasico = new javax.swing.JLabel();
        jTextFieldEmpleado_id = new javax.swing.JTextField();
        jTextFieldApellidos = new javax.swing.JTextField();
        jTextFieldNombres = new javax.swing.JTextField();
        jTextFieldCorreo = new javax.swing.JTextField();
        jTextFieldSucursal = new javax.swing.JTextField();
        jTextFieldCodigoDeSindicato = new javax.swing.JTextField();
        jTextFieldSueldoBasico = new javax.swing.JTextField();
        jButtonBuscarAlumno = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jButtonReinic = new javax.swing.JButton();

        jButtonReiniciar.setText("Reiniciar");
        jButtonReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReiniciarActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(450, 350));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabelTitulo.setText("Ingresar Datos De Empleados");
        getContentPane().add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 250, -1));

        jLabelEmpleado_id.setText("Codigo");
        getContentPane().add(jLabelEmpleado_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jLabelApellidos.setText("Apellidos");
        getContentPane().add(jLabelApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        jLabelNombres.setText("Nombres");
        getContentPane().add(jLabelNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jLabelCorreo.setText("Correo");
        getContentPane().add(jLabelCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        jLabelSucursal.setText("Sucursal");
        getContentPane().add(jLabelSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jLabelCodigoDeSindicato.setText("Codigo de sindicato");
        getContentPane().add(jLabelCodigoDeSindicato, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jLabelSueldoBasico.setText("sueldo Basico");
        getContentPane().add(jLabelSueldoBasico, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));
        getContentPane().add(jTextFieldEmpleado_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 90, -1));
        getContentPane().add(jTextFieldApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 220, -1));
        getContentPane().add(jTextFieldNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 210, -1));
        getContentPane().add(jTextFieldCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 90, -1));
        getContentPane().add(jTextFieldSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 90, -1));

        jTextFieldCodigoDeSindicato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodigoDeSindicatoActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldCodigoDeSindicato, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 90, -1));
        getContentPane().add(jTextFieldSueldoBasico, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 90, -1));

        jButtonBuscarAlumno.setText("Buscar");
        jButtonBuscarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarAlumnoActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonBuscarAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 90, -1));

        jButtonGuardar.setText("Aceptar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 100, -1));

        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 310, 100, -1));

        jButtonReinic.setText("Reiniciar");
        jButtonReinic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReinicActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonReinic, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
dispose();//GEN-LAST:event_jButtonSalirActionPerformed


}

private void jButtonReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReiniciarActionPerformed
// TODO add your handling code here:
limpiarJTextField();//GEN-LAST:event_jButtonReiniciarActionPerformed
}

private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
// TODO add your handling code here:EmpleadoDAO alumnoDAO=new EmpleadoDAO();
Empleado oEmpleado=new Empleado();
EmpleadoDAO alumnoDAO=new EmpleadoDAO();
oEmpleado.setEmpleado_id(Integer.parseInt(jTextFieldEmpleado_id.getText()));
oEmpleado.setApellidos(jTextFieldApellidos.getText());
oEmpleado.setNombres(jTextFieldNombres.getText());
oEmpleado.setCorreo(jTextFieldCorreo.getText());
oEmpleado.setSucursal_id(Integer.parseInt(jTextFieldSucursal.getText()));
oEmpleado.setSindicato_id(Integer.parseInt(jTextFieldCodigoDeSindicato.getText()));
oEmpleado.setSueldoBasico(Double.parseDouble(jTextFieldSueldoBasico.getText()));


if(alumnoDAO.insertarEmpleado(oEmpleado))
{	JOptionPane.showMessageDialog(null,"Operación Exitosa");
    		limpiarJTextField();
  	}
else
  	{	JOptionPane.showMessageDialog(null,"Operación Fallida");
  	}


}//GEN-LAST:event_jButtonGuardarActionPerformed


private void jTextFieldCodigoDeSindicatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodigoDeSindicatoActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_jTextFieldCodigoDeSindicatoActionPerformed

private void jButtonReinicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReinicActionPerformed
 limpiarJTextField();   // TODO add your handling code here:
}//GEN-LAST:event_jButtonReinicActionPerformed

private void jButtonBuscarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarAlumnoActionPerformed
Empleado oEmpleado=new Empleado();
EmpleadoDAO oEmpleadoDAO=new EmpleadoDAO();
oEmpleado.setEmpleado_id(Integer.parseInt(jTextFieldEmpleado_id.getText()));

if(oEmpleadoDAO.buscarEmpleado(oEmpleado)!=null)
{	JOptionPane.showMessageDialog(null,"Registro existente ...");
    		limpiarJTextField();
                jTextFieldEmpleado_id.requestFocus();
  	}
else
  	{

  JOptionPane.showMessageDialog(null,"Registro nuevo ...");
  jTextFieldEmpleado_id.requestFocus();
  	}
}//GEN-LAST:event_jButtonBuscarAlumnoActionPerformed


public void limpiarJTextField()
 		{	jTextFieldEmpleado_id.setText("");
    				jTextFieldApellidos.setText("");
    				jTextFieldNombres.setText("");
    				jTextFieldCorreo.setText("");
    				jTextFieldSucursal.setText("");
                    jTextFieldSueldoBasico.setText("");
                    jTextFieldCodigoDeSindicato.setText("");
                                jTextFieldEmpleado_id.requestFocus();
 		 }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscarAlumno;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonReinic;
    private javax.swing.JButton jButtonReiniciar;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JLabel jLabelApellidos;
    private javax.swing.JLabel jLabelCodigoDeSindicato;
    private javax.swing.JLabel jLabelCorreo;
    private javax.swing.JLabel jLabelEmpleado_id;
    private javax.swing.JLabel jLabelNombres;
    private javax.swing.JLabel jLabelSucursal;
    private javax.swing.JLabel jLabelSueldoBasico;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JTextField jTextFieldApellidos;
    private javax.swing.JTextField jTextFieldCodigoDeSindicato;
    private javax.swing.JTextField jTextFieldCorreo;
    private javax.swing.JTextField jTextFieldEmpleado_id;
    private javax.swing.JTextField jTextFieldNombres;
    private javax.swing.JTextField jTextFieldSucursal;
    private javax.swing.JTextField jTextFieldSueldoBasico;
    // End of variables declaration//GEN-END:variables
}

