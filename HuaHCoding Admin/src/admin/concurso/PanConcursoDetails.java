package admin.concurso;

import admin.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rCUser
 */
public class PanConcursoDetails extends javax.swing.JPanel {

    private Connection con;
    private boolean modificando = false;
    private String idConcursoAModificar;

    public PanConcursoDetails(Connection con) {
        this.con = con;
        initComponents();
    }

    public void loadConcursoDetails(String idConcurso) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT id_concurso, nombre, nombre_corto, fecha, locacion, " +
                    "inscripcion, premio, url_forum, descripcion " +
                    "FROM concurso WHERE id_concurso = '" + idConcurso + "'");
            rs.next();
            txtID.setText(rs.getString("id_concurso"));
            txtNombreConcurso.setText(rs.getString("nombre"));
            txtNombreCorto.setText(rs.getString("nombre_corto"));
            txtFecha.setText(rs.getString("fecha"));
            txtLocacion.setText(rs.getString("locacion"));
            txtInscripcion.setText(rs.getString("inscripcion"));
            txtPremio.setText(rs.getString("premio"));
            txtForumUrl.setText(rs.getString("url_forum"));
            txtDescripcion.setText(rs.getString("descripcion"));
            idConcursoAModificar = idConcurso;
            modificando = true;
            butGuardar.setText("Guardar Cambios");
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(PanConcursoDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarCambios(
            String nombre, String nombreCorto, String fecha, String locacion,
            String inscripcion, String premio, String forumURL,
            String descripcion) {
        try {
            String query = "";
            if (modificando) {
                query = "UPDATE Concurso set " +
                        "nombre = ?, " +
                        "nombre_corto = ?," +
                        "fecha = ?, " +
                        "locacion = ?," +
                        "inscripcion = ?," +
                        "premio = ?," +
                        "url_forum = ?," +
                        "descripcion = ? " +
                        "WHERE id_concurso = '" + idConcursoAModificar + "';";
                modificando = false;
            } else {//Insertando
                query = "INSERT INTO Concurso" +
                        "(nombre, nombre_corto, fecha, locacion, inscripcion, premio, url_forum, " +
                        "descripcion) values (?,?,?,?,?,?,?,?)";
                butGuardar.setText("Ingresar Concurso");
            }
            PreparedStatement pStat = con.prepareStatement(query);
            pStat.setString(1, nombre);
            pStat.setString(2, nombreCorto);
            if (fecha.equals("")) {
                pStat.setNull(3, java.sql.Types.DATE);
            } else {
                pStat.setString(3, fecha);
            }
            pStat.setString(4, locacion);
            pStat.setString(5, inscripcion);
            pStat.setString(6, premio);
            pStat.setString(7, forumURL);
            pStat.setString(8, descripcion);
            pStat.executeUpdate();
            pStat.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(ConcursoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadFormDefaultValuesConcurso() {
        txtNombreConcurso.setText("");
        txtNombreCorto.setText("");
        txtLocacion.setText("");
        txtFecha.setText("");
        txtInscripcion.setText("");
        txtPremio.setText("");
        txtForumUrl.setText("");
        txtDescripcion.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        butGuardar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtForumUrl = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNombreCorto = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtLocacion = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtInscripcion = new javax.swing.JTextField();
        txtPremio = new javax.swing.JTextField();
        txtNombreConcurso = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();

        butGuardar.setText("Guardar");
        butGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butGuardarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setText("Nombre:"); // NOI18N

        jLabel2.setText("Fecha (aaaa/mm/dd):"); // NOI18N

        jLabel4.setText("Forum URL:");

        jLabel3.setText("Descripción:"); // NOI18N

        jLabel5.setText("Nombre Corto:");

        jLabel12.setText("Locación:");

        jLabel14.setText("Premio:");

        jLabel13.setText("Inscripción:");

        txtID.setEditable(false);

        jLabel6.setText("ID:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtPremio, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtInscripcion, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                        .addComponent(txtNombreCorto, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtForumUrl, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtLocacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(txtNombreConcurso, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreConcurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCorto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPremio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtForumUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(butGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(butGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void butGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butGuardarActionPerformed
        guardarCambios(txtNombreConcurso.getText(),
                txtNombreCorto.getText(),
                txtFecha.getText(),
                txtLocacion.getText(),
                txtInscripcion.getText(),
                txtPremio.getText(),
                txtForumUrl.getText(),
                txtDescripcion.getText());
        loadFormDefaultValuesConcurso();
    }//GEN-LAST:event_butGuardarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtForumUrl;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtInscripcion;
    private javax.swing.JTextField txtLocacion;
    private javax.swing.JTextField txtNombreConcurso;
    private javax.swing.JTextField txtNombreCorto;
    private javax.swing.JTextField txtPremio;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        PanConcursoDetails s = new PanConcursoDetails(new ConexionDB().getConnection());
        RCContainer.showPanel(s);
        s.loadConcursoDetails("6");
    }
}
