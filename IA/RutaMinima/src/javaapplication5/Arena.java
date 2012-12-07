package javaapplication5;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import rclib.RCContainer;

public class Arena extends javax.swing.JPanel {

    ArrayList<Ruta> al = new ArrayList<Ruta>();
    ArrayList<Nodo> nodos = new ArrayList<Nodo>();
    Nodo from;
    JLabel start;
    JLabel end;

    /** Creates new form Arena */
    public Arena() {
        initComponents();

        Nodo[] nn = {
            new Nodo(20, 200, "Huacho"),
            new Nodo(200, 400, "Bca"),
            new Nodo(500, 200, "A"),
            new Nodo(500, 40, "B"),
            new Nodo(150, 60, "C"),
            new Nodo(600, 10, "End")
        };
        for (Nodo nodo : nn) {
            nodos.add(nodo);
        }
        for (Nodo nodo : nodos) {
            setMouseEvent(nodo);
        }

        nn[0].addRuta(nn[1]);
        nn[0].addRuta(nn[4]);
        nn[1].addRuta(nn[2]);
        nn[3].addRuta(nn[5]);
        nn[4].addRuta(nn[3]);
        nn[2].addRuta(nn[5]);
        initRComponents();

    }

    void initRComponents() {
        for (Nodo nodo : nodos) {
            panArena.add(nodo.getFace(),
                    new AbsoluteConstraints(nodo.getX(), nodo.getY(), -1, -1));
        }
        buildEvents();
    }

    void buildEvents() {
        panArena.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (butPonerNodos.isSelected()) {
                    System.out.println("XXX");
                    String name = "";
                    boolean ok = false;
                    while (name.equals("") || !ok) {
                        name = JOptionPane.showInputDialog("Ingrese el nombre del nodo (unico)");
                        if (name == null) {
                            ok = false;
                            break;
                        }
                        ok = true;
                        for (Nodo ruta : nodos) {
                            if (ruta.getNombre().equals(name)) {
                                ok = false;
                                break;
                            }
                        }
                    }
                    if (ok) {
                        Nodo newNodo = new Nodo(e.getX(), e.getY(), name);
                        nodos.add(newNodo);
                        setMouseEvent(newNodo);
                        panArena.add(newNodo.getFace(),
                                new AbsoluteConstraints(e.getX(), e.getY(), -1, -1));
                        panArena.validate();
                    }
                    refreshRutas();
                }
            }
        });
    }

    void setMouseEvent(final Nodo nodo) {
        nodo.getFace().addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                JLabel myFace = (JLabel) e.getSource();

                if (butSetStart.isSelected()) {
                    if (start != null) {
                        start.setBorder(null);
                    }
                    start = myFace;
                    start.setBorder(new LineBorder(Color.BLACK, 3));
                } else if (butSetEnd.isSelected()) {
                    if (end != null) {
                        end.setBorder(null);
                    }
                    end = myFace;
                    end.setBorder(new LineBorder(Color.BLACK, 3));
                } else {
                    if (from == null) {
                        from = nodo;
                        myFace.setBackground(Color.red);
                    } else if (from == nodo) {
                        from = null;
                        myFace.setBackground(Color.white);
                    } else {
                        from.addRuta(nodo);
                        from.getFace().setBackground(Color.white);
                        myFace.setBackground(Color.white);
                        drawRuta(from, nodo);
                        refreshRutas();
                        from = null;
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel src = (JLabel) e.getSource();
                src.setBorder(new LineBorder(Color.black, 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JLabel src = (JLabel) e.getSource();
                src.setBorder(null);
            }
        });
    }

    private void refreshRutas() {
        for (Nodo origen : nodos) {
            for (Nodo destino : origen.getMap().keySet()) {
                drawRuta(origen, destino);
            }
        }
//        panArena.validate();
    }

    private void drawRuta(Nodo desde, Nodo hasta) {
        panArena.getGraphics();
        desde.getX();
        desde.getY();
        hasta.getX();
        hasta.getY();
        panArena.getGraphics().drawLine(
                desde.getX(), desde.getY(), hasta.getX(), hasta.getY());

        int xMark = (hasta.getX() - desde.getX()) / 7 + desde.getX();
        int yMark = (hasta.getY() - desde.getY()) / 7 + desde.getY();

        panArena.add(desde.getMap().get(hasta),
                new AbsoluteConstraints(xMark, yMark, -1, -1));
        panArena.validate();
    }

    private void drawSolRuta(Nodo from, Nodo nodo) {
        panArena.getGraphics().drawLine(
                from.getX(), from.getY(), nodo.getX(), nodo.getY());

        panArena.getGraphics().drawLine(
                from.getX() - 1, from.getY() - 1, nodo.getX() - 1, nodo.getY() - 1);
        panArena.getGraphics().drawLine(
                from.getX() + 1, from.getY() + 1, nodo.getX() + 1, nodo.getY() + 1);

        int xMark = (nodo.getX() - from.getX()) / 7 + from.getX();
        int yMark = (nodo.getY() - from.getY()) / 7 + from.getY();

        panArena.validate();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panArena = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        butPonerNodos = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        butSetStart = new javax.swing.JToggleButton();
        butSetEnd = new javax.swing.JToggleButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panArena.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panArena.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(panArena, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 800, 440));

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        butPonerNodos.setText("Poner Nodos");
        butPonerNodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butPonerNodosActionPerformed(evt);
            }
        });

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("BuscarRuta");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(butSetStart);
        butSetStart.setText("Set Start");
        butSetStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSetStartActionPerformed(evt);
            }
        });

        buttonGroup1.add(butSetEnd);
        butSetEnd.setText("Set End");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(butPonerNodos)
                .addGap(30, 30, 30)
                .addComponent(butSetStart, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(butSetEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(299, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butPonerNodos)
                    .addComponent(butSetStart)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(butSetEnd))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 800, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void butPonerNodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butPonerNodosActionPerformed
        if (butPonerNodos.isSelected()) {
            panArena.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            panArena.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_butPonerNodosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        refreshRutas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ArrayList<Ruta> rutas = new ArrayList<Ruta>();
        for (Nodo desde : nodos) {
            for (Nodo hasta : desde.getMap().keySet()) {
                int distance = Integer.parseInt(desde.getMap().get(hasta).getValue() + "");

                rutas.add(new Ruta(desde.getNombre(), hasta.getNombre(), distance));
            }
        }
        System.out.println(rutas);
        if (start != null && end != null) {
            RutaMinima a = new RutaMinima(rutas.toArray(new Ruta[rutas.size()]),
                    start.getText(), end.getText());
            if (a.solved) {
                String st = a.lista.get(0).getB();
                for (Ruta r : a.lista) {
                    Nodo desde = null;
                    Nodo hasta = null;
                    for (Nodo nodo : nodos) {
                        if (nodo.getNombre().equals(st)) {
                            desde = nodo;
                        }
                        if (nodo.getNombre().equals(r.getB())) {
                            hasta = nodo;
                        }
                    }
                    assert (desde != null && hasta != null);
                    drawSolRuta(desde, hasta);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No ha establecido Start / End");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void butSetStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSetStartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_butSetStartActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton butPonerNodos;
    private javax.swing.JToggleButton butSetEnd;
    private javax.swing.JToggleButton butSetStart;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panArena;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        Arena a = new Arena();
        RCContainer.showPanel(a);
        a.refreshRutas();

    }
}
