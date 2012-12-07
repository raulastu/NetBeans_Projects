package igu;

import encuestaaeiinf.DB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JFrameMain extends javax.swing.JFrame {

    Connection cn;
    Statement st = null;
    JFrameLogin frameLogin;
    JFrameAsignaturas frameAsign;
    JFrameNewDocente frameDocente;
    JFrameAsignSurvey frameAsignSurvey;
    JFrameDocenteSurvey frameDocenteSurvey;
    String currentUser;

    public JFrameMain() throws Exception {
        initComponents();
        startConn();

        frameLogin = new JFrameLogin(st);
        frameAsign = new JFrameAsignaturas(st);
        frameDocente = new JFrameNewDocente(st);

        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evt) {
                endConn();
            }
        });
        frameLogin.jButtonEnter.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                String user = frameLogin.jTxfUser.getText();
                String password = String.valueOf(frameLogin.jPassword.getPassword());

                try {

                    ResultSet rs = st.executeQuery("select Password from Usuario where Id = '" + user + "'");
                    if (rs.next()) {
                        String realPassword = rs.getString(1);
                        System.out.println(realPassword);
                        System.out.println(password);
                        if (realPassword.equals(password)) {
                            frameAsignSurvey.setCurrentUser(user);
                            frameDocenteSurvey.setCurrentUser(user);
                            setVisible(true);
                            frameLogin.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Password ó User incorrecto");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "User incorrecto");
                    }


                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        frameAsignSurvey = new JFrameAsignSurvey(st, currentUser);
        frameDocenteSurvey = new JFrameDocenteSurvey(st, currentUser);
        frameLogin.setVisible(true);
        frameLogin.jButtonExit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                endConn();
                System.exit(0);
            }
        });
        frameLogin.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evt) {
                endConn();
            }
        });
    }

    public void startConn() {
        try {
            cn = new DB().getConnection();
            st = cn.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void endConn() {
        try {
            st.close();
            cn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.exit(0);

    }

    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonEncDocente = new javax.swing.JButton();
        jButtonEncCatedra = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButtonNewDocente = new javax.swing.JButton();
        jButtonNewAsign = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Ingresar Encuestas"));
        jButtonEncDocente.setText("Docente");
        jButtonEncDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEncDocenteActionPerformed(evt);
            }
        });

        jButtonEncCatedra.setText("Catedra");
        jButtonEncCatedra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEncCatedraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonEncDocente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addComponent(jButtonEncCatedra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButtonEncCatedra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEncDocente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Ingresar Actores"));
        jButtonNewDocente.setText("Docentes");
        jButtonNewDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewDocenteActionPerformed(evt);
            }
        });

        jButtonNewAsign.setText("Asignaturas");
        jButtonNewAsign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewAsignActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonNewDocente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNewAsign, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButtonNewDocente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNewAsign)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEncDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEncDocenteActionPerformed
        frameDocenteSurvey.setVisible(true);
        frameDocenteSurvey.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameDocenteSurvey.loadDocentes();
    }//GEN-LAST:event_jButtonEncDocenteActionPerformed

    private void jButtonNewDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewDocenteActionPerformed
        frameDocente.setVisible(true);
        frameDocente.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }//GEN-LAST:event_jButtonNewDocenteActionPerformed

    private void jButtonNewAsignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewAsignActionPerformed
        frameAsign.setVisible(true);
        frameAsign.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }//GEN-LAST:event_jButtonNewAsignActionPerformed

    private void jButtonEncCatedraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEncCatedraActionPerformed
        frameAsignSurvey.setVisible(true);
        frameAsignSurvey.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameAsignSurvey.loadAsignaturas();
    }//GEN-LAST:event_jButtonEncCatedraActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        endConn();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new JFrameMain();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonEncCatedra;
    private javax.swing.JButton jButtonEncDocente;
    private javax.swing.JButton jButtonNewAsign;
    private javax.swing.JButton jButtonNewDocente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
