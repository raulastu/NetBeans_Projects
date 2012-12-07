
import cecocadb.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import rclib.RCContainer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanPostulante.java
 *
 * Created on Feb 8, 2009, 12:38:32 AM
 */
/**
 *
 * @author rCuser
 */
public class PanDatosPostulante extends javax.swing.JPanel {

    private String codiPostulante;
    private int idConcurso;
    private DB database;

    public PanDatosPostulante(DB database, int idConcurso, String codiPostulante) throws SQLException {
        this.database = database;
        this.idConcurso = idConcurso;
        this.codiPostulante = codiPostulante;
        initComponents();
        cargarDatosPostulante();
    }

    private void cargarDatosPostulante() throws SQLException {
        ResultSet rs = database.crSt().executeQuery(
                "SELECT "+
                "p.ape_paterno, "+
                "p.ape_materno, "+
                "p.nombres, "+
                "p.prom_colegio, "+
                "s.nominacion, m.nombre, e.nombre "+
                "FROM " +
                "   postulante p, " +
                "   sede s, " +
                "   modalidad m, " +
                "   escuela e, " +
                "   concurso c "+
                "WHERE c.id_sede = s.id_sede AND "+
                "p.id_escuela = e.id_escuela AND "+
                "c.id_modalidad = m.id_modalidad AND "+
                "p.id_concurso = '"+idConcurso+"' AND "+
                "p.codi_postulante ='"+codiPostulante+"';");
        boolean ok = rs.next();
        assert ok;
        txtCodigo.setText(codiPostulante);
        txtApeNombres.setText(rs.getString(1)+" "+rs.getString(2)+", "+rs.getString(3));
        txtPromedio.setText(rs.getDouble(4)+"");
        txtSede.setText(rs.getString(5));
        txtModalidad.setText(rs.getString(6));
        txtEscuela.setText(rs.getString(7));
        rs.close();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtApeNombres = new javax.swing.JTextField();
        txtSede = new javax.swing.JTextField();
        txtModalidad = new javax.swing.JTextField();
        txtEscuela = new javax.swing.JTextField();
        txtPromedio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        jLabel1.setText("Código:");

        jLabel2.setText("Apellidos y Nombres:");

        jLabel3.setText("Promedio Notas Colegio:");

        jLabel4.setText("Sede");

        jLabel5.setText("Modalidad:");

        jLabel6.setText("Escuela a la que postula:");

        jLabel7.setText("Foto");
        jLabel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtModalidad, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addComponent(txtEscuela, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addComponent(txtPromedio, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addComponent(txtSede, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApeNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtApeNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtEscuela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtApeNombres;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEscuela;
    private javax.swing.JTextField txtModalidad;
    private javax.swing.JTextField txtPromedio;
    private javax.swing.JTextField txtSede;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) throws SQLException {
        DB database = new DB();
        RCContainer.showPanel(new PanDatosPostulante(database, 1, "0-0 0"));
    }
}
