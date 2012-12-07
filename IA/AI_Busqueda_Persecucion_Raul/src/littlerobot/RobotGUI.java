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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import littlerobot.Robot.Recorrido;

public class RobotGUI extends javax.swing.JFrame {

    /** Creates new form RobotGUI */
    int xMax, yMax;
    JLabel[][] grid;
    //
    JPopupMenu menPutting;
    JMenuItem menPutBot = new JMenuItem("Robot");
    JMenuItem menPutMovil = new JMenuItem("Movil");
//    JMenuItem menPutTarget = new JMenuItem("Objetivo");
    int xSelected, ySelected;
    //
    int xBot, yBot, xMovil, yMovil;
    Recorrido movilEspacio;
    int[] inicio = {0, 0, 2, 2};
    LinkedList<Estado> lista = new LinkedList<Estado>();
    int speed = 1000;
    boolean[][] obstaculos;
    //
    JFileChooser fc = new JFileChooser();
    //
//    ImageIcon targetIcon = new ImageIcon(getClass().getResource("/littlerobot/img/target.gif"));
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
        construirLaberinto();
        refreshElementos();
    }

    public void go() {

//        System.out.println(q2.getSolution());
        Timer x = new Timer();
        x.schedule(new TimerBotRecorrido(x, panLaberinto), 0, speed / slidSpeedBot.getValue());
    }
    int turn = 0;

    class TimerBotRecorrido extends TimerTask {

        int indexLista = 0;
        Timer timer;
        JComponent comp;

        public TimerBotRecorrido(Timer timer, JComponent comp) {
            this.timer = timer;
            this.comp = comp;
        }

        @Override
        public void run() {
//            System.out.println(lista.get(indexLista));
//            for (int i = 0; i < grid.length; i++) {
//                for (int j = 0; j < grid[i].length; j++) {
//                    grid[i][j].setText("");
//                }
//            }
            prepost[0][0][0] = xBot;
            prepost[1][0][0] = yBot;
            prepost[0][1][0] = xMovil;
            prepost[1][1][0] = yMovil;
            System.err.println("turn" + turn);
//            System.err.println("movil " + xMovil + " " + yMovil);
//            System.err.println("bot " + xBot + " " + yBot);
//            System.err.println(Arrays.deepToString(obstaculos));
            boolean cancel = false;
            if (turn % 2 == 0) {
                ArrayList<Node> list = Robot.bfs(xBot, yBot, xMovil, yMovil, obstaculos);
//                System.err.println(list);
                if (list.size() == 0) {
                    cancel = true;
//                    timer.cancel();
                    System.err.println("timer.cancel()");
                } else {
                    if (list.get(1).x == xMovil && list.get(1).y == yMovil) {
                        cancel = true;
//                        timer.cancel();
                        System.err.println("timer.cancel()");
                    }
                    xBot = list.get(1).x;
                    yBot = list.get(1).y;
                }
            } else {
//                if (movilEspacio == null) {
//
//                    System.err.println(movilEspacio);
//                }
                movilEspacio = Robot.getRecorrido(xBot, yBot, xMovil, yMovil, obstaculos);
                int[] ab = Robot.whereToGo(xMovil, yMovil, xBot, yBot, obstaculos, movilEspacio);
                xMovil = ab[0];
                yMovil = ab[1];
            }

            prepost[0][0][1] = xBot;
            prepost[1][0][1] = yBot;
            prepost[0][1][1] = xMovil;
            prepost[1][1][1] = yMovil;
//                refreshElementos();
            refreshMovibles(prepost[0][0], prepost[1][0], prepost[0][1], prepost[1][1]);
            comp.revalidate();
            turn++;
            if (cancel) {
                timer.purge();
                timer.cancel();
                turn = 0;
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
//        if (xTarget <= xMax && yTarget <= yMax) {
//            grid[xTarget][yTarget].setIcon(new ImageIcon(getClass().getResource("/littlerobot/img/target.gif")));
//        }
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
                final int fi = i,  fj = j;
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
//        menPutting.add(menPutTarget);
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
//        menPutTarget.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                xTarget = xSelected;
//                yTarget = ySelected;
//                refreshElementos();
//            }
//        });

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
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("rC Bot by raúl?");

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
            .addGap(0, 169, Short.MAX_VALUE)
        );
        panLaberintoLayout.setVerticalGroup(
            panLaberintoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 174, Short.MAX_VALUE)
        );

        slidSpeedBot.setMaximum(10);
        slidSpeedBot.setMinimum(1);
        slidSpeedBot.setPaintLabels(true);
        slidSpeedBot.setValue(2);

        jLabel3.setText("Velocidad del robot");

        buttonGroup1.add(butJava);
        butJava.setSelected(true);
        butJava.setText("Java");

        jButton4.setText("add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Save");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

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
                        .addComponent(butJava))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(butBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton5)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panLaberinto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(butJava)
                        .addGap(23, 23, 23))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slidSpeedBot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panLaberinto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butBuscarActionPerformed
        System.err.println("START!!");
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
//                dox.writeInt(xTarget);
//                dox.writeInt(yTarget);
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
//                xTarget = dox.readInt();
//                yTarget = dox.readInt();
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

    class PP {
        char[] a;

        public PP(char[] a) {
            this.a = a;
        }
        
    }
    ArrayList<char[][]> al = new ArrayList<char[][]>();
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        char [][] ax = new char[obstaculos.length][obstaculos[0].length];

        for (int i = 0; i < ax.length; i++) {
            Arrays.fill(ax[i],'.');
            for (int j = 0; j < ax[i].length; j++) {
                if(obstaculos[i][j])
                    ax[i][j]='#';
            }
        }
        ax[xBot][yBot]='R';
        ax[xMovil][yMovil]='C';
        for (int i = 0; i < ax.length; i++) {
            System.err.println(new String(ax[i]));
        }
        al.add(ax);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * Para crear laberintos en archivo
     * @param evt
     */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        PrintWriter fw=null;
        try {
            fw = new PrintWriter(new File("output.txt"));
        } catch (IOException ex) {
            Logger.getLogger(RobotGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        fw.println(al.size());
        for (char[][] cses : al) {
            fw.println(cses.length+" "+cses[0].length);
            for (int i = 0; i < cses.length; i++) {
                fw.println(new String(cses[i]));
            }
        }
        fw.close();
    }//GEN-LAST:event_jButton5ActionPerformed

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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
