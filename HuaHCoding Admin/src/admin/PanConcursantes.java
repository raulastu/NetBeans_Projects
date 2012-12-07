package admin;

import admin.live.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author  rCuser
 */
public class PanConcursantes extends javax.swing.JPanel {

    private RCJTable tableConcursante;
    private RCJTable tableCampaign;
    private Connection con;
    //
    private String campaignUser = "";
    private String idUsuario = "";
    JFrame frameAddConcurso;
    private RCJTable tableAddConcurso;
    Concursante concursante;

    public PanConcursantes(Connection con) {
//        JFrame.setDefaultLookAndFeelDecorated(true);
        initComponents();
        this.con = con;
        concursante = new Concursante(con);
        initMyComponents();
        loadFormDefaults();
        //g.getParent();
    }

    public void initMyComponents() {

        // Tabla Concursantes
//        cmbSemester.setModel(new javax.swing.DefaultComboBoxModel(new Integer[]{1, 2, 3, 4, 5, 7, 8, 9, 10}));

        LinkedHashMap<String, String[]> columns = new LinkedHashMap<String, String[]>();
        columns.put("us.id_usuario", new String[]{"ID", "6"});
        columns.put("us.username", new String[]{"Usuario", "29"});
        columns.put("us.nombres", new String[]{"Nombres", "25"});
        columns.put("us.apellidos", new String[]{"Apellidos", "30"});
        columns.put("es.nombre", new String[]{"Escuela/Instituto", "30"});
//        columns.put("Ciclo", new String[]{"Semestre", "5"});        
        columns.put("us.email", new String[]{"E-mail", "29"});
        String where = "WHERE us.id_escuela = es.id_escuela";
        tableConcursante = new RCJTable(con, " usuario us, escuela es ", columns, where);
        scrollConcursanteTable.setViewportView(tableConcursante);

        //Tabla Campañas
        LinkedHashMap<String, String[]> campaignColumns = new LinkedHashMap<String, String[]>();
        campaignColumns.put("con.Id_Concurso", new String[]{"Id Concurso", "10"});
        campaignColumns.put("con.Nombre", new String[]{"Nombre Concurso", "40"});
        campaignColumns.put("con.fecha", new String[]{"Fecha", "30"});
        campaignColumns.put("cam.Puntos", new String[]{"Puntos", "10"});
        campaignColumns.put("cam.Puesto", new String[]{"Rank", "10"});

        tableCampaign = new RCJTable(con, "Campaign cam, Concurso con", campaignColumns,
                "Where cam.Id_Concurso=con.Id_Concurso " +
                "AND cam.id_usuario = -1");
        scrpCampaignTable.setViewportView(tableCampaign);

        //Evento para ver las campañas del usuario seleccionado
        tableConcursante.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                performChangeTableConcursantes();
            }
        });
        tableConcursante.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                performChangeTableConcursantes();
            }
        });

        //Dialog de Inscripcion a nuevo concurso
        frameAddConcurso = new JFrame(ConcursoPanel.APPLICATION_NAME + " - Concursos disponibles para ");
        LinkedHashMap<String, String[]> columns2 = new LinkedHashMap<String, String[]>();
        columns2.put("Id_Concurso", new String[]{"ID", "4"});
        columns2.put("Nombre", new String[]{"Nombre", "20"});
        columns2.put("Fecha", new String[]{"Fecha", "10"});
        columns2.put("Descripcion", new String[]{"Descripcion", "50"});

        tableAddConcurso = new RCJTable(con, "Concurso", columns2, "");

        tableAddConcurso.setColumnWidthFactor(6);
        JScrollPane scrpConcurso = new JScrollPane(tableAddConcurso);
        scrpConcurso.setPreferredSize(new Dimension(600, 200));
        frameAddConcurso.getContentPane().add(scrpConcurso);
        JButton buttRegContest = new JButton("Inscribir en concurso");
        frameAddConcurso.getContentPane().add(buttRegContest);
        frameAddConcurso.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        frameAddConcurso.pack();
        frameAddConcurso.setAlwaysOnTop(true);
        frameAddConcurso.setResizable(false);
        buttRegContest.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (tableAddConcurso.getSelectedRow() != -1) {
                    String idConcurso = tableAddConcurso.getValueAt(tableAddConcurso.getSelectedRow(), 0) + "";
                    concursante.inscribirEnConcurso(idUsuario, idConcurso);
                    tableAddConcurso.loadDataArray();
                    tableCampaign.loadDataArray();
                } else {
                    JOptionPane.showMessageDialog(frameAddConcurso, "No hay concurso seleccionado",
                            ConcursoPanel.APPLICATION_NAME, JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void performChangeTableConcursantes() {
        String id = tableConcursante.getValueAt(tableConcursante.getSelectedRow(), 0) + "";
        String condition = "WHERE cam.Id_Concurso=con.Id_Concurso " +
                "AND cam.id_usuario = " + id;
        tableCampaign.setCondition(condition);
        tableCampaign.loadDataArray();
        campaignUser = tableConcursante.getValueAt(tableConcursante.getSelectedRow(), 1) + "";
        scrpCampaignTable.setBorder(javax.swing.BorderFactory.createTitledBorder(
                new LineBorder(new Color(0, 0, 0), 1, true), "Campaña de " + campaignUser));
        butEnroll.setEnabled(true);
    }

    private void loadFormDefaults() {
        txtNames.setText("");
        txtSurnames.setText("");
        txtFrom.setSelectedIndex(-1);
        txtEmail.setText("");
        txtUser.setText("");
        pass1.setText("");
        pass2.setText("");
        txtNames.grabFocus();
    }

    public boolean matchPassword() {
        return Arrays.equals(pass1.getPassword(), pass2.getPassword());
    }

    public void showError(SQLException ex) {
        JOptionPane.showMessageDialog(null, ex, "error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarConcursosDisponibles() {
        frameAddConcurso.setTitle(ConcursoPanel.APPLICATION_NAME + " - Concursos disponibles para " + campaignUser);
        idUsuario = tableConcursante.getValueAt(tableConcursante.getSelectedRow(), 0) + "";
        String condition = "WHERE Estado = 'REGISTRATION_OPEN' " +
                "AND Id_Concurso NOT IN " +
                "(SELECT Id_Concurso FROM CAMPAIGN WHERE id_usuario = " + idUsuario + ")";
        tableAddConcurso.setCondition(condition);
        tableAddConcurso.loadDataArray();
        frameAddConcurso.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollConcursanteTable = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtSurnames = new javax.swing.JTextField();
        txtNames = new javax.swing.JTextField();
        buttRegistrar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pass1 = new javax.swing.JPasswordField();
        pass2 = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtFrom = new javax.swing.JComboBox();
        scrpCampaignTable = new javax.swing.JScrollPane();
        butEnroll = new javax.swing.JButton();

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setText("Nombres:");

        jLabel2.setText("Apellidos:");

        jLabel3.setText("Escuela/Instituto:");

        buttRegistrar.setText("Registrar");
        buttRegistrar.setEnabled(false);
        buttRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttRegistrarActionPerformed(evt);
            }
        });

        jLabel5.setText("e-mail:");

        jLabel6.setText("Contraseña:");

        jLabel7.setText("Usuario:");

        jLabel8.setText("Ver. Contraseña");

        txtFrom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "UNJFSC - Ing. Informática", "UNJFSC - Ing. de Sistemas", "Otro" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNames, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(txtSurnames, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(txtFrom, 0, 248, Short.MAX_VALUE)
                            .addComponent(pass2, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(pass1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(buttRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSurnames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(pass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(pass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(buttRegistrar)
                .addContainerGap())
        );

        scrpCampaignTable.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), campaignUser.equals("")?"Campaña":"Campaña de " + campaignUser));

        butEnroll.setText("Registrar en Concurso");
        butEnroll.setEnabled(false);
        butEnroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEnrollActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrpCampaignTable, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(butEnroll)
                            .addComponent(scrollConcursanteTable, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollConcursanteTable, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(butEnroll, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrpCampaignTable, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    private void buttRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttRegistrarActionPerformed
        try {
            if (txtFrom.getSelectedIndex() != -1) {
                if (matchPassword()) {
                    concursante.registrarConcursante(
                            txtUser.getText(),
                            String.valueOf(pass1.getPassword()),
                            txtNames.getText(),
                            txtSurnames.getText(),
                            txtFrom.getSelectedIndex() + 1,
                            txtEmail.getText());
                    tableConcursante.loadDataArray();
                    loadFormDefaults();
                } else {
                    JOptionPane.showMessageDialog(null, "No coiciden passwords", ConcursoPanel.APPLICATION_NAME, JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please choose school", ConcursoPanel.APPLICATION_NAME, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            System.out.println("butRegistrar" + ex.getMessage());
            Logger.getLogger(PanConcursantes.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_buttRegistrarActionPerformed

    private void butEnrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEnrollActionPerformed
        mostrarConcursosDisponibles();
    }//GEN-LAST:event_butEnrollActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butEnroll;
    private javax.swing.JButton buttRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField pass1;
    private javax.swing.JPasswordField pass2;
    private javax.swing.JScrollPane scrollConcursanteTable;
    private javax.swing.JScrollPane scrpCampaignTable;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JComboBox txtFrom;
    private javax.swing.JTextField txtNames;
    private javax.swing.JTextField txtSurnames;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
