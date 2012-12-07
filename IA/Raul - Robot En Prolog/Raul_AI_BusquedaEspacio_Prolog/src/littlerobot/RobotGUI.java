package littlerobot;

import core.Estado;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import jpl.Query;

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
    int[] inicio = {0, 0, 2, 2};
    LinkedList<Estado> lista = new LinkedList<Estado>();
    int speed = 1000;
    boolean[][] obstaculos;
    //
    JFileChooser fc = new JFileChooser();
    //
    ImageIcon targetIcon = new ImageIcon(getClass().getResource("/littlerobot/img/target.gif"));
    ImageIcon botIcon = new ImageIcon(getClass().getResource("/littlerobot/img/dragoon.gif"));
    ImageIcon movIcon = new ImageIcon(getClass().getResource("/littlerobot/img/cart.png"));
    //
    int[][][] prepost = new int[2][2][2];

    public RobotGUI() {
        initComponents();
        initRComponents();
        xMax = 2;
        yMax = 2;
        xBot = 0;
        yBot = 0;
        xMovil = 1;
        yMovil = 1;
        xTarget = 2;
        yTarget = 2;
        construirLaberinto();
        refreshElementos();
    }

    public void go() {

//        System.out.println(q2.getSolution());

        Robot bot = new Robot(0, 0, xMax, yMax, obstaculos);
        bot.setEstadoInicial(new Estado(new Estado(xBot, yBot), new Estado(xMovil, yMovil)));
        bot.setEstadoFinal(new Estado(new Estado(-1, -1), new Estado(xTarget, yTarget)));

        if (butJava.isSelected()) {
            bot.resolver();
        } else {
            try {
                bot.fromProlog();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RobotGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println(bot.lista);
        if (bot.solved) {
            lista = bot.lista;
            inicio[0] = xBot;
            inicio[1] = yBot;
            inicio[2] = xMovil;
            inicio[3] = yMovil;
            Timer x = new Timer();
            x.schedule(new TimerBotRecorrido(bot.lista.size(), x, panLaberinto), 0, speed / slidSpeedBot.getValue());
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
                prepost[0][0][0] = xBot;
                prepost[1][0][0] = yBot;
                prepost[0][1][0] = xMovil;
                prepost[1][1][0] = yMovil;
                xBot = lista.get(indexLista).estadoRobot.x;
                yBot = lista.get(indexLista).estadoRobot.y;
                xMovil = lista.get(indexLista).estadoMovil.x;
                yMovil = lista.get(indexLista).estadoMovil.y;
                prepost[0][0][1] = xBot;
                prepost[1][0][1] = yBot;
                prepost[0][1][1] = xMovil;
                prepost[1][1][1] = yMovil;
//                refreshElementos();
                refreshMovibles(prepost[0][0], prepost[1][0], prepost[0][1], prepost[1][1]);
                comp.revalidate();
                indexLista++;
            }
        }
    }

    void refreshMovibles(int[] xb, int[] yb, int[] xm, int[] ym) {
        grid[xm[0]][ym[0]].setIcon(null);
        grid[xm[1]][ym[1]].setIcon(movIcon);
        grid[xb[0]][yb[0]].setIcon(null);
        grid[xb[1]][yb[1]].setIcon(botIcon);

    }

    void refreshElementos() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].setIcon(null);
            }
        }
        for (int i = 0; i < obstaculos.length; i++) {
            for (int j = 0; j < obstaculos[i].length; j++) {
                if (obstaculos[i][j]) {
                    grid[i][j].setBackground(Color.black);
                } else {
                    grid[i][j].setBackground(Color.white);
                }
            }
        }
        if (xTarget <= xMax && yTarget <= yMax) {
            grid[xTarget][yTarget].setIcon(new ImageIcon(getClass().getResource("/littlerobot/img/target.gif")));
        }
        if (xBot <= xMax && yBot <= yMax) {
            grid[xBot][yBot].setIcon(new ImageIcon(getClass().getResource("/littlerobot/img/dragoon.gif")));
        }
        if (xMovil <= xMax && yMovil <= yMax) {
            grid[xMovil][yMovil].setIcon(new ImageIcon(getClass().getResource("/littlerobot/img/cart.png")));
        }
    }

    void construirLaberinto() {
        grid = new JLabel[xMax + 1][yMax + 1];
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
        panLaberinto.setLayout(new GridLayout(xMax + 1, yMax + 1));
        for (JLabel[] jLabels : grid) {
            for (JLabel jLabel : jLabels) {
                panLaberinto.add(jLabel);
            }
        }
        System.out.println(panLaberinto.getPreferredSize());
        int w = 160 + (yMax + 1) * 53;
        int h = 130 + (xMax + 1) * 54;
        this.setSize(Math.max(400, w), Math.max(350, h));
        getContentPane().validate();
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
                            obstaculos[fi][fj] = !obstaculos[fi][fj];
                            refreshElementos();
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
                refreshElementos();
            }
        });
        menPutMovil.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                xMovil = xSelected;
                yMovil = ySelected;
                refreshElementos();
            }
        });
        menPutTarget.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                xTarget = xSelected;
                yTarget = ySelected;
                refreshElementos();
            }
        });

        // spins Listeners
        ChangeListener changeListener = new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                xMax = Integer.parseInt(spinX.getValue() + "") - 1;
                yMax = Integer.parseInt(spinY.getValue() + "") - 1;
                construirLaberinto();
                refreshElementos();
            }
        };
        spinX.addChangeListener(changeListener);
        spinY.addChangeListener(changeListener);

        FileFilter ff = new FileFilter() {

            @Override
            public boolean accept(File f) {
                String extension = f.getPath();
                extension = extension.substring(extension.lastIndexOf(".") + 1, extension.length());
                if (extension.equals("rcbot")) {
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return ".rcbot";
            }
        };
        slidSpeedBot.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
            }
        });
        fc.setFileFilter(ff);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        spinX = new javax.swing.JSpinner();
        spinY = new javax.swing.JSpinner();
        butBuscar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        panLaberinto = new javax.swing.JPanel();
        slidSpeedBot = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        butJava = new javax.swing.JRadioButton();
        butProlog = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("rC Bot");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Configuración"));

        jLabel1.setText("x");

        jLabel2.setText("y");

        spinX.setModel(new javax.swing.SpinnerNumberModel(3, 2, 12, 1));

        spinY.setModel(new javax.swing.SpinnerNumberModel(3, 2, 12, 1));

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

        butBuscar.setText("Buscar!");
        butBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butBuscarActionPerformed(evt);
            }
        });

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

        panLaberinto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        javax.swing.GroupLayout panLaberintoLayout = new javax.swing.GroupLayout(panLaberinto);
        panLaberinto.setLayout(panLaberintoLayout);
        panLaberintoLayout.setHorizontalGroup(
            panLaberintoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 141, Short.MAX_VALUE)
        );
        panLaberintoLayout.setVerticalGroup(
            panLaberintoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        slidSpeedBot.setMaximum(10);
        slidSpeedBot.setMinimum(1);
        slidSpeedBot.setPaintLabels(true);
        slidSpeedBot.setValue(2);

        jLabel3.setText("Velocidad del robot");

        buttonGroup1.add(butJava);
        butJava.setSelected(true);
        butJava.setText("Java");

        buttonGroup1.add(butProlog);
        butProlog.setText("Prolog");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slidSpeedBot, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(butJava)
                            .addComponent(butProlog)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(butBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panLaberinto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(butJava)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butProlog))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slidSpeedBot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(panLaberinto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butBuscarActionPerformed
        go();
    }//GEN-LAST:event_butBuscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        xBot = inicio[0];
        yBot = inicio[1];
        xMovil = inicio[2];
        yMovil = inicio[3];
        refreshElementos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            fc.getSelectedFile();
            try {
                DataOutputStream dox = new DataOutputStream(new BufferedOutputStream(
                        new FileOutputStream(fc.getSelectedFile() + ".rcbot")));
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
                DataInputStream dox = new DataInputStream(new BufferedInputStream(
                        new FileInputStream(fc.getSelectedFile())));
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
                refreshElementos();
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
    private javax.swing.JButton butBuscar;
    private javax.swing.JRadioButton butJava;
    private javax.swing.JRadioButton butProlog;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panLaberinto;
    private javax.swing.JSlider slidSpeedBot;
    private javax.swing.JSpinner spinX;
    private javax.swing.JSpinner spinY;
    // End of variables declaration//GEN-END:variables
}
