package sales;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class JFrameLogin extends javax.swing.JFrame {
    Statement sentencia;
    UserType user;
    public JFrameLogin(Statement st) {
        sentencia=st;
        initComponents();
        this.setTitle("Login - rC");
        jrbAdmin.setSelected(true);
        jtfID.setText("adm");
        tpfPass.setText("1a2a3a");
        jButtonEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt){
                String id = jtfID.getText();
                ResultSet rs=null;
                String query = "";
                try {
                    if(jrbVendedor.isSelected()){
                        query = "select password from vendedor where idvendedor = '" + id+ "'";
                        
                        user=UserType.VENDEDOR;
                    } else if(jrbAdmin.isSelected()){
                        query = "select pass from admin where idadmin = '" + id + "'";
                        user=UserType.ADMIN;
                    }
                    
                    System.out.println(query);
                    rs=sentencia.executeQuery(query);
                    rs.next();
                    String pass=rs.getString(1);
                    System.out.println(pass.toCharArray());
                    System.out.println(tpfPass.getPassword());
                    boolean iwales=true;
                    
                    
                    for(int i=0;i<pass.toCharArray().length;i++){
                        if(pass.toCharArray()[i]!=tpfPass.getPassword()[i])
                            iwales=false;
                    }
                    
                    if(iwales){
                        if(user==UserType.ADMIN)
                            new BusquedaOverall(sentencia);
                        else if(user==UserType.VENDEDOR){
                            System.out.println(sentencia.execute(
                                    "begin sp_update_currentuser('" + id +"');end;"));
                            JFrameMenuVendedor menu = new JFrameMenuVendedor(id);
                            menu.setVisible(true);
                            setVisible(false);
                            final JFrameFacturaVend factura = new JFrameFacturaVend(sentencia);
                            menu.jButton1.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent evt){
                                    factura.setVisible(true);
                                    factura.panFactura.ingresarNuevo();
                                }
                            });
                        }
                    } else
                        JOptionPane.showMessageDialog(null,"wrong password");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Usuario no existente");
                    //ex.printStackTrace();
                } catch  (java.lang.ArrayIndexOutOfBoundsException aioobe){
                    JOptionPane.showMessageDialog(null,"wrong password");
                }
                
            }
        });
        
    }
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        jrbVendedor = new javax.swing.JRadioButton();
        jrbAdmin = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tpfPass = new javax.swing.JPasswordField();
        jtfID = new javax.swing.JTextField();
        jButtonEnter = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        buttonGroup1.add(jrbVendedor);
        jrbVendedor.setText("Vendedor");
        jrbVendedor.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jrbVendedor.setMargin(new java.awt.Insets(0, 0, 0, 0));

        buttonGroup1.add(jrbAdmin);
        jrbAdmin.setText("Admin");
        jrbAdmin.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jrbAdmin.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 2, true));
        jLabel1.setText("ID:");

        jLabel2.setText("Password:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpfPass, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(jtfID, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tpfPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonEnter.setText("ENTER!");
        jButtonEnter.getAccessibleContext().setAccessibleParent(tpfPass);

        jButtonExit.setText("EXIT!");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jrbVendedor)
                .addGap(44, 44, 44)
                .addComponent(jrbAdmin)
                .addContainerGap(85, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(159, Short.MAX_VALUE)
                .addComponent(jButtonEnter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExit)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbAdmin)
                    .addComponent(jrbVendedor))
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEnter)
                    .addComponent(jButtonExit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonExitActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonEnter;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jrbAdmin;
    private javax.swing.JRadioButton jrbVendedor;
    private javax.swing.JTextField jtfID;
    private javax.swing.JPasswordField tpfPass;
    // End of variables declaration//GEN-END:variables
    
}
