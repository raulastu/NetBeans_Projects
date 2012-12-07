/*
 * JPanelControlViews.java
 *
 * Created on 4 de diciembre de 2007, 02:20 AM
 */

package cooperacionmisc;

import java.awt.Font;
import java.awt.event.*;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.plaf.ButtonUI;

public class JPanelControlViews extends javax.swing.JPanel {
    
    private javax.swing.JMenuItem menResaltarPeso;
    private javax.swing.JMenuItem menResaltarCapacidad;
    private javax.swing.JMenuItem menResaltarRecurso;
    private javax.swing.JMenuItem menResaltarPesoCoop;
    private javax.swing.JMenuItem menResaltarAmbos;  
    
    private javax.swing.ButtonGroup butGroup=new javax.swing.ButtonGroup();
    public JPanelControlViews() {
        
        initComponents();
        menResaltarPeso = new javax.swing.JMenuItem("Peso");
        menResaltarCapacidad = new javax.swing.JMenuItem("Capacidad");
        menResaltarRecurso = new javax.swing.JMenuItem("Recursos");
        menResaltarPesoCoop = new javax.swing.JMenuItem("Peso-Coop");
        menResaltarAmbos = new javax.swing.JMenuItem("Ambos");
        
        jPopupMenuPuntosDebiles.add(new javax.swing.JCheckBox("Peso"));
        jPopupMenuPuntosDebiles.add(new javax.swing.JCheckBox("Capacidad"));
        jPopupMenuPuntosDebiles.add(new javax.swing.JCheckBox("Recursos"));
        jPopupMenuPuntosDebiles.add(new javax.swing.JCheckBox("Peso-Coop"));
        
        jPopupMenuPuntosFuertes.add(new javax.swing.JCheckBox("Peso"));
        jPopupMenuPuntosFuertes.add(new javax.swing.JCheckBox("Capacidad"));
        jPopupMenuPuntosFuertes.add(new javax.swing.JCheckBox("Recursos"));
        jPopupMenuPuntosFuertes.add(new javax.swing.JCheckBox("Peso-Coop"));
        
        javax.swing.JMenuItem menAsc=new javax.swing.JMenu("Ascendente");
        javax.swing.JMenuItem menDesc=new javax.swing.JMenu("Descendente");
        
        //menAsc.setFont(new Font("san-serif",Font.BOLD,14));
        
        javax.swing.JMenuItem menTotales=new javax.swing.JCheckBoxMenuItem("Totales");
        javax.swing.JMenuItem menPorcentajes=new javax.swing.JCheckBoxMenuItem("Porcentajes");
        
        javax.swing.JMenuItem menAscTotales=new javax.swing.JRadioButtonMenuItem("Totales");
        javax.swing.JMenuItem menAscPercents=new javax.swing.JRadioButtonMenuItem("Porcentajes");
        javax.swing.JMenuItem menDescTotales=new javax.swing.JRadioButtonMenuItem("Totales");
        javax.swing.JMenuItem menDescPercents=new javax.swing.JRadioButtonMenuItem("Porcentajes");
        
        menAsc.add(menAscTotales);
        menAsc.add(menAscPercents);        
        menDesc.add(menDescTotales);
        menDesc.add(menDescPercents);
        butGroup.add(menAscTotales);      
        butGroup.add(menDescTotales);      
        butGroup.add(menAscPercents);      
        butGroup.add(menDescPercents);  
        
        jPopupMenuOrdenar.add(menAsc);
        jPopupMenuOrdenar.add(menDesc);
        jPopupMenuOrdenar.add(new javax.swing.JSeparator());        
        javax.swing.JMenuItem menDefault = new javax.swing.JMenuItem("Por Defecto");
        
        javax.swing.JMenuItem menVerPercents=new javax.swing.JCheckBoxMenuItem("Porcentajes");
        
        javax.swing.JMenuItem menVerExpandido=new javax.swing.JMenuItem("Expandido");
        javax.swing.JMenuItem menVerContraido=new javax.swing.JMenuItem("Contra�do");
        jPopupMenuVer.add(menVerPercents);        
        jPopupMenuVer.add(new javax.swing.JSeparator());
        jPopupMenuVer.add(menVerExpandido);
        jPopupMenuVer.add(menVerContraido);
        
        
        menDefault.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                menDefaultActionPerformed(evt);
            }
        });
        
        
        jPopupMenuOrdenar.add(menDefault);
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPopupMenuPuntosDebiles = new javax.swing.JPopupMenu();
        jPopupMenuPuntosFuertes = new javax.swing.JPopupMenu();
        jPopupMenuOrdenar = new javax.swing.JPopupMenu();
        jPopupMenuVer = new javax.swing.JPopupMenu();
        jToolBar2 = new javax.swing.JToolBar();
        jTButAmenazas = new javax.swing.JToggleButton();
        jTButDebilidades = new javax.swing.JToggleButton();
        jButPuntosDebiles = new javax.swing.JButton();
        jButPuntosFuertes = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonVer = new javax.swing.JButton();
        jButtonOrdenar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jToolBar2.setEnabled(false);
        jTButAmenazas.setText("Fortalezas");
        jTButAmenazas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTButAmenazasActionPerformed(evt);
            }
        });

        jToolBar2.add(jTButAmenazas);

        jTButDebilidades.setText("Debilidades");
        jTButDebilidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTButDebilidadesActionPerformed(evt);
            }
        });

        jToolBar2.add(jTButDebilidades);

        jButPuntosDebiles.setText("Puntos D\u00e9biles");
        jButPuntosDebiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButPuntosDebilesActionPerformed(evt);
            }
        });

        jToolBar2.add(jButPuntosDebiles);

        jButPuntosFuertes.setText("Puntos Fuertes");
        jButPuntosFuertes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButPuntosFuertesActionPerformed(evt);
            }
        });

        jToolBar2.add(jButPuntosFuertes);

        jToolBar1.setEnabled(false);
        jButtonVer.setText("Ver");
        jButtonVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerActionPerformed(evt);
            }
        });

        jToolBar1.add(jButtonVer);

        jButtonOrdenar.setText("Orden");
        jButtonOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrdenarActionPerformed(evt);
            }
        });

        jToolBar1.add(jButtonOrdenar);

        jButton2.setText("Ver Extendido");
        jToolBar1.add(jButton2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerActionPerformed
        jPopupMenuVer.setVisible(true);
        jPopupMenuVer.show(jButtonVer,0,jButtonVer.getHeight());        
    }//GEN-LAST:event_jButtonVerActionPerformed
    
    private void menDefaultActionPerformed(java.awt.event.ActionEvent evt){
        butGroup.clearSelection();
    }
    private void jTButDebilidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTButDebilidadesActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jTButDebilidadesActionPerformed

    private void jTButAmenazasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTButAmenazasActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jTButAmenazasActionPerformed

    private void jButtonOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrdenarActionPerformed
        jPopupMenuOrdenar.setVisible(true);
        jPopupMenuOrdenar.show(jButtonOrdenar,0,jButtonOrdenar.getHeight());
      
    }//GEN-LAST:event_jButtonOrdenarActionPerformed

    private void jButPuntosFuertesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButPuntosFuertesActionPerformed
        jPopupMenuPuntosFuertes.setVisible(true);        
        jPopupMenuPuntosFuertes.show(jButPuntosFuertes,0,jButPuntosFuertes.getHeight());
    }//GEN-LAST:event_jButPuntosFuertesActionPerformed

    private void jButPuntosDebilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButPuntosDebilesActionPerformed
        jPopupMenuPuntosDebiles.setVisible(true);        
        jPopupMenuPuntosDebiles.show(jButPuntosDebiles,0,jButPuntosDebiles.getHeight());
        
    }//GEN-LAST:event_jButPuntosDebilesActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButPuntosDebiles;
    private javax.swing.JButton jButPuntosFuertes;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonOrdenar;
    private javax.swing.JButton jButtonVer;
    private javax.swing.JPopupMenu jPopupMenuOrdenar;
    private javax.swing.JPopupMenu jPopupMenuPuntosDebiles;
    private javax.swing.JPopupMenu jPopupMenuPuntosFuertes;
    private javax.swing.JPopupMenu jPopupMenuVer;
    private javax.swing.JToggleButton jTButAmenazas;
    private javax.swing.JToggleButton jTButDebilidades;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    // End of variables declaration//GEN-END:variables
    public static void main(String[]args){
        Console.run(new JPanelControlViews());
    }
}

