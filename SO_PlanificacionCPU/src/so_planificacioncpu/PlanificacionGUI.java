package so_planificacioncpu;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class PlanificacionGUI extends javax.swing.JPanel {

    /** Creates new form PlanificacionGUI */
    String title;

    public PlanificacionGUI(String title) {
        this.title = title;
        setBorderColor(new Color(109, 178, 204));
        initComponents();
    }

    public void setTiempoEsperaMedio(double tiempo) {
        labTiempoEsperaMedio.setText(tiempo+"");
    }

    public void setTiempoRetornoMedio(double tiempo) {
        labTiempoRetornoMedio.setText(tiempo+"");
    }

    public void setEstado(Estado estado) {
        switch(estado){
            case Esperando:setBorderColor(new Color(204,204,0));break;
            case Procesando:setBorderColor(Color.green);break;
        }        
    }

    public void setBorderColor(Color color){
        setBorder(javax.swing.BorderFactory.createTitledBorder(
                new LineBorder(Color.BLACK, 2, true),
                title, TitledBorder.CENTER, TitledBorder.TOP, new Font("Verdana", Font.BOLD, 12), color));
    }

    public void setTiempoTranscurrido(int time) {
        laBTiempoTranscurrido.setText(time+"");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spaneThread = new javax.swing.JScrollPane();
        panThread = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labTiempoEsperaMedio = new javax.swing.JLabel();
        laBTiempoTranscurrido = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labTiempoRetornoMedio = new javax.swing.JLabel();

        spaneThread.setViewportView(panThread);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Tiempo Medio");

        labTiempoEsperaMedio.setForeground(new java.awt.Color(255, 0, 51));
        labTiempoEsperaMedio.setText(" ");

        laBTiempoTranscurrido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel4.setText("tiempo transcurrido:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Tiempo Medio:");

        labTiempoRetornoMedio.setForeground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spaneThread, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labTiempoEsperaMedio, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labTiempoRetornoMedio, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(laBTiempoTranscurrido, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(spaneThread, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labTiempoEsperaMedio))
                    .addComponent(jLabel4)
                    .addComponent(laBTiempoTranscurrido, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labTiempoRetornoMedio, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    protected javax.swing.JLabel laBTiempoTranscurrido;
    protected javax.swing.JLabel labTiempoEsperaMedio;
    protected javax.swing.JLabel labTiempoRetornoMedio;
    protected javax.swing.JPanel panThread;
    private javax.swing.JScrollPane spaneThread;
    // End of variables declaration//GEN-END:variables
}
