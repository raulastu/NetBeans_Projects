package so_planificacioncpu;

/**
 *
 * @author rCuser
 */
public class ProcesoGUI extends javax.swing.JPanel {

    Proceso proceso;

    public ProcesoGUI(Proceso proceso) {
        initComponents();
        this.proceso = proceso;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        labTRetorno = new javax.swing.JLabel();
        labTEspera = new javax.swing.JLabel();
        labTtranscurrido = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jProgressBar1.setPreferredSize(new java.awt.Dimension(146, 22));
        jProgressBar1.setStringPainted(true);
        add(jProgressBar1, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.BorderLayout());

        labTRetorno.setForeground(new java.awt.Color(0, 0, 255));
        labTRetorno.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labTRetorno.setPreferredSize(new java.awt.Dimension(14, 15));
        jPanel1.add(labTRetorno, java.awt.BorderLayout.PAGE_END);

        labTEspera.setForeground(new java.awt.Color(255, 0, 51));
        labTEspera.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labTEspera.setText("esp");
        labTEspera.setPreferredSize(new java.awt.Dimension(17, 15));
        jPanel1.add(labTEspera, java.awt.BorderLayout.PAGE_START);

        add(jPanel1, java.awt.BorderLayout.SOUTH);

        labTtranscurrido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labTtranscurrido.setPreferredSize(new java.awt.Dimension(4, 15));
        add(labTtranscurrido, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    public javax.swing.JProgressBar jProgressBar1;
    public javax.swing.JLabel labTEspera;
    public javax.swing.JLabel labTRetorno;
    public javax.swing.JLabel labTtranscurrido;
    // End of variables declaration//GEN-END:variables
}