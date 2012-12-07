package littlerobot;

import core.Estado;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class RobotGUI extends javax.swing.JFrame {

    /** Creates new form RobotGUI */
    int xMax, yMax;
    JLabel[][] grid;
    //
    JPopupMenu menPutting;
    JMenuItem menPutBot = new JMenuItem("Robot");
    JMenuItem menPutMovil = new JMenuItem("Movil");
    JMenuItem menPutTarget = new JMenuItem("Objetivo");
    int xSelected, ySelected;
    //
    int xBot, yBot, xMovil, yMovil, xTarget, yTarget;
    int[] inicio = new int[4];
    ArrayList<Estado> lista = new ArrayList<Estado>();
    int speed = 500;
    boolean[][] obstaculos;
    //
    JFileChooser fc = new JFileChooser();

    public RobotGUI() {
        initComponents();
        initRComponents();
        xMax = 3;
        yMax = 3;
        xBot = 0;
        yBot = 0;
        xMovil = 1;
        yMovil = 1;
        xTarget = 2;
        yTarget = 2;
        construirLaberinto();
        dibujarElementos();
    }

    public void go() {
        Robot bot = new Robot(1, 1, xMax, yMax, obstaculos);
        bot.setEstadoInicial(new Estado(new Estado(xBot + 1, yBot + 1), new Estado(xMovil + 1, yMovil + 1)));
        bot.setEstadoFinal(new Estado(new Estado(-1, -1), new Estado(xTarget + 1, yTarget + 1)));
        bot.resolver();
        System.out.println(bot.lista);
        if (bot.solved) {
            lista = bot.lista;
            inicio[0] = xBot;
            inicio[1] = yBot;
            inicio[2] = xMovil;
            inicio[3] = yMovil;
            Timer x = new Timer();
            x.schedule(new TimerBotRecorrido(bot.lista.size(), x, panLaberinto), 0, speed);
        } else {
            JOptionPane.showMessageDialog(null, "?");
        }
    }

    class TimerBotRecorrido extends TimerTask {

        int indexLista = 0;
        int listSize;
        Thread t;
        Timer timer;
        JComponent comp;

        public TimerBotRecorrido(int listSize, Timer timer, JComponent comp) {
            this.listSize = listSize;
            this.timer = timer;
            this.comp = comp;
            t = new Thread(this);
            t.start();
        }

        @Override
        public void run() {
            if (listSize == indexLista) {
                timer.cancel();
            } else {
                System.out.println(lista.get(indexLista));
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        grid[i][j].setText("");
                    }
                }
                xBot = lista.get(indexLista).estadoRobot.x - 1;
                yBot = lista.get(indexLista).estadoRobot.y - 1;
                xMovil = lista.get(indexLista).estadoMovil.x - 1;
                yMovil = lista.get(indexLista).estadoMovil.y - 1;
                dibujarElementos();
                comp.revalidate();
                indexLista++;
            }
        }
    }

    void dibujarElementos() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].setText("");
                grid[i][j].setIcon(null);
            }
        }
        for (int i = 1; i < obstaculos.length; i++) {
            for (int j = 1; j < obstaculos[i].length; j++) {
                if (obstaculos[i][j]) {
                    grid[i - 1][j - 1].setBackground(Color.black);
                } else {
                    grid[i - 1][j - 1].setBackground(Color.white);
                }
            }
        }
        if (xTarget < xMax && yTarget < yMax) {
            grid[xTarget][yTarget].setText("X");
        }
        if (xBot < xMax && yBot < yMax) {
            grid[xBot][yBot].setIcon(new ImageIcon(getClass().getResource("/littlerobot/img/dragoon.gif")));
        }
        if (xMovil < xMax && yMovil < yMax) {
            grid[xMovil][yMovil].setIcon(new ImageIcon(getClass().getResource("/littlerobot/img/cart.png")));
        }
    }

    void construirLaberinto() {
        grid = new JLabel[xMax][yMax];
        obstaculos = new boolean[xMax + 1][yMax + 1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new JLabel();
                grid[i][j].setOpaque(true);
                grid[i][j].setHorizontalAlignment(JLabel.CENTER);
                grid[i][j].setBackground(Color.white);
                grid[i][j].setBorder(BorderFactory.createBevelBorder(1));
            }
        }
        panLaberinto.removeAll();
        panLaberinto.setLayout(new GridLayout(xMax, yMax));
        for (JLabel[] jLabels : grid) {
            for (JLabel jLabel : jLabels) {
                panLaberinto.add(jLabel);
            }
        }
        getContentPane().setVisible(false);
        getContentPane().setVisible(true);
        construirEventos();
    }

    void construirEventos() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                final int fi = i, fj = j;
                grid[i][j].addMouseListener(new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            xSelected = fi;
                            ySelected = fj;
                            menPutting.setLocation(e.getXOnScreen(), e.getYOnScreen());
                            menPutting.setVisible(true);
                        } else {
                            obstaculos[fi + 1][fj + 1] = !obstaculos[fi + 1][fj + 1];
                            dibujarElementos();
                        }
                    }
                });
            }
        }
    }

    private void initRComponents() {
        menPutting = new JPopupMenu("rC bot");
        menPutting.add(menPutBot);
        menPutting.add(menPutMovil);
        menPutting.add(menPutTarget);
        menPutBot.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                xBot = xSelected;
                yBot = ySelected;
                dibujarElementos();
            }
        });
        menPutMovil.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                xMovil = xSelected;
                yMovil = ySelected;
                dibujarElementos();
            }
        });
        menPutTarget.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                xTarget = xSelected;
                yTarget = ySelected;
                dibujarElementos();
            }
        });

        // spins Listeners
        ChangeListener changeListener = new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                xMax = Integer.parseInt(spinX.getValue() + "");
                yMax = Integer.parseInt(spinY.getValue() + "");
                construirLaberinto();
//                xBot = (xBot > xMax - 1 || yBot > yMax - 1)?-1:xBot;
//                xMovil = (xMovil > xMax - 1 || yMovil > yMax - 1)?-1:xMovil;
//                xTarget = (xTarget > xMax - 1 || yTarget > yMax - 1)?-1:xTarget;
//                System.out.println(xBot+","+yBot);
//                System.out.println(xMovil+","+yMovil);
//                System.out.println(xTarget+","+yTarget);
                dibujarElementos();
            }
        };
        spinX.addChangeListener(changeListener);
        spinY.addChangeListener(changeListener);

        FileFilter ff = new FileFilter() {

            @Override
            public boolean accept(File f) {
                String extension = f.getPath();
                extension = extension.substring(extension.lastIndexOf(".") + 1, extension.length());
                if (extension.equals("rCBot")) {
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return ".rcbot";
            }
        };
        fc.setFileFilter(ff);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        spinX = new javax.swing.JSpinner();
        spinY = new javax.swing.JSpinner();
        butBuscar = new javax.swing.JButton();
        butForward = new javax.swing.JButton();
        butBackward = new javax.swing.JButton();
        panLaberinto = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("rC Bot");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Configuración"));

        jLabel1.setText("x");

        jLabel2.setText("y");

        spinX.setModel(new javax.swing.SpinnerNumberModel(3, 2, 20, 1));

        spinY.setModel(new javax.swing.SpinnerNumberModel(3, 2, 20, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinX, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {spinX, spinY});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(spinX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(spinY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        butBuscar.setText("Buscar");
        butBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butBuscarActionPerformed(evt);
            }
        });

        butForward.setText(">>");
        butForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butForwardActionPerformed(evt);
            }
        });

        butBackward.setText("<<");
        butBackward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butBackwardActionPerformed(evt);
            }
        });

        panLaberinto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        javax.swing.GroupLayout panLaberintoLayout = new javax.swing.GroupLayout(panLaberinto);
        panLaberinto.setLayout(panLaberintoLayout);
        panLaberintoLayout.setHorizontalGroup(
            panLaberintoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );
        panLaberintoLayout.setVerticalGroup(
            panLaberintoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );

        jButton1.setText("Inicio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Open");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panLaberinto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, 0, 0, Short.MAX_VALUE)
                            .addComponent(butBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(butBackward)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(butForward))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2)
                                .addComponent(jButton3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(butBuscar)
                            .addComponent(butForward)
                            .addComponent(butBackward)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panLaberinto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butBuscarActionPerformed
        go();
    }//GEN-LAST:event_butBuscarActionPerformed

    private void butForwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butForwardActionPerformed
        speed /= 2;
    }//GEN-LAST:event_butForwardActionPerformed

    private void butBackwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butBackwardActionPerformed
        speed *= 2;
    }//GEN-LAST:event_butBackwardActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        xBot = inicio[0];
        yBot = inicio[1];
        xMovil = inicio[2];
        yMovil = inicio[3];
        dibujarElementos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            fc.getSelectedFile();
            try {
                DataOutputStream dox = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fc.getSelectedFile())));
                dox.writeInt(xMax);
                dox.writeInt(yMax);
                dox.writeInt(xBot);
                dox.writeInt(yBot);
                dox.writeInt(xMovil);
                dox.writeInt(yMovil);
                dox.writeInt(xTarget);
                dox.writeInt(yTarget);
                for (int i = 0; i < obstaculos.length; i++) {
                    for (int j = 0; j < obstaculos[i].length; j++) {
                        if (obstaculos[i][j]) {
                            dox.writeInt(i);
                            dox.writeInt(j);
                        }
                    }
                }
                dox.close();
            } catch (IOException ex) {
                Logger.getLogger(RobotGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            fc.getSelectedFile();
            try {
                DataInputStream dox = new DataInputStream(new BufferedInputStream(new FileInputStream(fc.getSelectedFile())));
                xMax = dox.readInt();
                yMax = dox.readInt();
                xBot = dox.readInt();
                yBot = dox.readInt();
                xMovil = dox.readInt();
                yMovil = dox.readInt();
                xTarget = dox.readInt();
                yTarget = dox.readInt();
                construirLaberinto();
                obstaculos = new boolean[xMax + 1][yMax + 1];
                while (true) {
                    try {
                        obstaculos[dox.readInt()][dox.readInt()] = true;
                    } catch (EOFException e) {
                        break;
                    }
                }
                dox.close();
                dibujarElementos();
            } catch (IOException ex) {
                Logger.getLogger(RobotGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RobotGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butBackward;
    private javax.swing.JButton butBuscar;
    private javax.swing.JButton butForward;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panLaberinto;
    private javax.swing.JSpinner spinX;
    private javax.swing.JSpinner spinY;
    // End of variables declaration//GEN-END:variables
}
