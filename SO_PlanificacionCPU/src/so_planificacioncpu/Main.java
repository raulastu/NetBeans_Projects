package so_planificacioncpu;

import Algoritmos.SJF;
import Algoritmos.AlgoritmoPlanificacion;
import Algoritmos.FIFO;
import Algoritmos.Prioridades;
import Algoritmos.RoundRobin;
import Algoritmos.SJFPreemptive;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.timer.TimerMBean;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class Main extends javax.swing.JFrame {

    ArrayList<AlgoritmoPlanificacion> planificaciones;
    private int idProcesoToAdd = 1;
    //ArrayList<Integer[]> guarda duracion y prioridad de cada instante
    HashMap<Integer, ArrayList<Integer[]>> mapHorario = new HashMap<Integer, ArrayList<Integer[]>>();
    int maxLlegada = 0;
//    Object[][] data = new Object[0][3];
    Object[][] data = new Object[0][4];
    int totalDataLenght = 0;
    JTable table;
    int segundo = 0;
    private boolean nuevoPlan = false;
    private RoundRobin rr;
    private Prioridades pr;

    public Main() {
        rr = new RoundRobin("Round Robin");
        pr = new Prioridades("Prioridades");
        planificaciones = new ArrayList<AlgoritmoPlanificacion>();
        planificaciones.add(new FIFO("FIFO"));
        planificaciones.add(new SJF("Shortest-Job-First (SJF Non-PreEmptive)"));
        planificaciones.add(new SJFPreemptive("SJF PreEmptive"));
        planificaciones.add(rr);
        planificaciones.add(pr);
//        planificaciones.add(new SJF("Shortest-Job-First (SJF Non-PreEmptive)"));
//        planificaciones.add(new SJF("Shortest-Job-First (SJF Non-PreEmptive)"));
//        planificaciones.add(new SJF("Shortest-Job-First (SJF Non-PreEmptive)"));
        initComponents();
        initMyComponents();
        //test Diapositiva
//        addRow(0, 7, 1);
//        addRow(2, 4, 2);
//        addRow(4, 1, 3);
//        addRow(5, 4, 4);

        //test roundRobin
//        addRow(0, 10, 1);
//        addRow(0, 6, 1);
//        addRow(0, 2, 2);
//        addRow(0, 4, 3);
//        addRow(0, 8, 4);

        //test prioridades
        addRow(2, 10, 3);
        addRow(3, 1, 1);
        addRow(4, 2, 3);
        addRow(1, 1, 4);
        addRow(6, 5, 2);
        Timer daemonTimer = new Timer();
        daemonTimer.schedule(new RefreshingAlgsTimer(), 0, 1000);
    }

    class RCTask extends TimerTask {

        Timer timer;

        public RCTask(Timer timer) {
            this.timer = timer;
        }

        @Override
        public void run() {
            labSegundo.setText(segundo+"");
            ArrayList<Integer[]> disparar = mapHorario.get(segundo);
            int sz = planificaciones.size();
            if (jCheckBox1.isSelected()) {
                sz--;
            }
            if (disparar!=null) {
                for (Integer[] datos : disparar) {
//                    System.out.println(durYPrioridad+"dur");
                    for (int i = 0; i<sz; i++) {
                        agregarProc(datos[0], datos[1], datos[2], i);
                    }
//                    idProcesoToAdd++;
                }
            }
            if (maxLlegada==segundo) {
                segundo = 0;
                labSegundo.setText(segundo+"");
                timer.cancel();
                jButton1.setEnabled(true);
            } else {
                jButton1.setEnabled(false);
                table.setEnabled(false);
                segundo++;
            }

            butEjecutarMacro.setEnabled(false);
        }
    }

    class RefreshingAlgsTimer extends TimerTask {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                loadAlgoritmos();
            }
        }
    }

    public void initMyComponents() {
        for (AlgoritmoPlanificacion algoritmoPlanificacion : planificaciones) {
            panAlgos.add(algoritmoPlanificacion.getGui());
            algoritmoPlanificacion.start();
        }
        loadAlgoritmos();
        validate();
        table = new JTable(new RCMOdel());
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        jScrollPane1.setViewportView(table);
        System.out.println("omg");
    }

    public void addRow(int llegada, int duracion, int prioridad) {
//        System.err.println(Arrays.deepToString(data));
        Object[][] d = Arrays.copyOf(data, data.length+1);
        data = d;
        data[data.length-1] = new Object[]{++totalDataLenght, llegada, duracion, prioridad};
//        System.err.println(Arrays.deepToString(data));
        table.revalidate();
//        table.getModel().addTableModelListener(new SimpleTableDemo());
        validate();
    }

    public void loadAlgoritmos() {
        for (AlgoritmoPlanificacion algoritmoPlanificacion : planificaciones) {
            algoritmoPlanificacion.getGui().panThread.removeAll();
            for (Proceso proceso : algoritmoPlanificacion.colaProcesos) {
                algoritmoPlanificacion.getGui().panThread.add(proceso.getProcesoGui());
            }
        }
        validate();
    }

    class RCMOdel extends AbstractTableModel {

        String[] names = new String[]{"P",
            "T. de Llegada", "Duracion", "Prioridad"};
        Class[] types = new Class[]{
            java.lang.String.class,
            java.lang.Integer.class,
            java.lang.Integer.class,
            java.lang.Integer.class
        };
        boolean[] canEdit = new boolean[]{
            false, true, true, true
        };

        public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return names[column];
        }

        @Override
        public Object getValueAt(int row, int column) {
            return data[row][column];
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return names.length;
        }

        @Override
        public void fireTableRowsUpdated(int firstRow, int lastRow) {
//            System.err.println(Arrays.deepToString(data));
            super.fireTableRowsUpdated(firstRow, lastRow);
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

    public class SimpleTableDemo implements TableModelListener {

        public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel) e.getSource();
            String columnName = model.getColumnName(column);
            Object datas = model.getValueAt(row, column);
            data[row][column] = datas;
        // Do something with the data...
        }
    }

    public void agregarProc(int idP, int duracion, int prioridad, int algoId) {
        System.out.println(idP);
        planificaciones.get(algoId).
                agregarProceso(idP, duracion, prioridad);
//        loadAlgoritmos();
    }

    public void startPrioridades() {
        Object[][] copy = Arrays.copyOf(data, data.length);

        Arrays.sort(copy, new Comparator() {

            public int compare(Object o1, Object o2) {
                Object[] da = (Object[]) o1;
                Object[] db = (Object[]) o2;
                Integer a = Integer.parseInt(""+da[3]);
                Integer b = Integer.parseInt(""+db[3]);
                return a-b;
            }
        });
        System.err.println(Arrays.deepToString(copy));
        for (int i = 0; i<copy.length; i++) {
            int id = Integer.parseInt(""+copy[i][0]);
            int duracion = Integer.parseInt(""+copy[i][2]);
            int prioridad = Integer.parseInt(""+copy[i][3]);
            pr.agregarProceso(id, duracion, prioridad);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panAlgos = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        butEjecutarMacro = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        labSegundo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        spinQuantum = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        butAgregar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        spinDuracion = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Planificacion de CPU by errechi");

        panAlgos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        panAlgos.setLayout(new java.awt.GridLayout(        planificaciones.size(), 1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Programar Procesos"));

        butEjecutarMacro.setText("Empezar");
        butEjecutarMacro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEjecutarMacroActionPerformed(evt);
            }
        });

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                        .addComponent(butEjecutarMacro))
                    .addComponent(labSegundo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butEjecutarMacro)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labSegundo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("RoundRobin Configuración"));

        jLabel1.setText("Quantum:");

        spinQuantum.setModel(new javax.swing.SpinnerNumberModel(2, 1, 999, 1));
        spinQuantum.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinQuantumStateChanged(evt);
            }
        });
        spinQuantum.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                spinQuantumAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinQuantum, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(spinQuantum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar en Caliente"));

        butAgregar.setText("Agregar");
        butAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAgregarActionPerformed(evt);
            }
        });

        jLabel3.setText("Duración:");

        spinDuracion.setModel(new javax.swing.SpinnerNumberModel(1, 1, 30, 1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(butAgregar)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(spinDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(butAgregar)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Prioridades"));

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("tiempo llegada = 0 a todos los procesos");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel6.setText("a menor número mayor prioridad");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jCheckBox1))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jCheckBox1))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Leyenda"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Tiempo de Atención");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setForeground(new java.awt.Color(255, 0, 51));
        jLabel4.setText("Tiempo de Espera");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("Tiempo de Retorno");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panAlgos, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panAlgos, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAgregarActionPerformed
        for (int i = 0; i<planificaciones.size(); i++) {
            agregarProc(idProcesoToAdd, Integer.parseInt(spinDuracion.getValue()+""), 0, i);
        }
    }//GEN-LAST:event_butAgregarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (nuevoPlan) {
            data = new Object[0][3];
            table.setEnabled(true);
            butEjecutarMacro.setEnabled(true);
            mapHorario.clear();
            table.revalidate();
            addRow(1, 1, 1);
            nuevoPlan = false;
        } else {
            addRow(1, 1, 1);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void butEjecutarMacroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEjecutarMacroActionPerformed
        if (data.length>0) {
            for (Object[] objects : data) {
                Integer id = (Integer) objects[0];
                Integer llegada = (Integer) objects[1];
                Integer duracion = (Integer) objects[2];
                Integer prioridad = (Integer) objects[3];
                ArrayList<Integer[]> datos = mapHorario.get(llegada);
                if (datos==null) {
                    datos = new ArrayList<Integer[]>();
                    datos.add(new Integer[]{id, duracion, prioridad});
                    mapHorario.put(llegada, datos);
                    maxLlegada = Math.max(llegada, maxLlegada);
                } else {
                    datos.add(new Integer[]{id, duracion, prioridad});
                }
            }
//            System.out.println(mapHorario);
            System.err.println(Arrays.deepToString(data));
            if (jCheckBox1.isSelected()) {
                startPrioridades();
            }
//            data = new Object[0][4];
//            table.revalidate();
            Timer myTimer = new Timer();
            myTimer.schedule(new RCTask(myTimer), 0, 1000);
            nuevoPlan = true;
        }
}//GEN-LAST:event_butEjecutarMacroActionPerformed

    private void spinQuantumAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_spinQuantumAncestorAdded
}//GEN-LAST:event_spinQuantumAncestorAdded

    private void spinQuantumStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinQuantumStateChanged
        int newQuantum = Integer.parseInt(""+spinQuantum.getValue());
        System.out.println(newQuantum);
        rr.setQuantum(newQuantum);
}//GEN-LAST:event_spinQuantumStateChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Main main = new Main();
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAgregar;
    private javax.swing.JButton butEjecutarMacro;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labSegundo;
    private javax.swing.JPanel panAlgos;
    private javax.swing.JSpinner spinDuracion;
    private javax.swing.JSpinner spinQuantum;
    // End of variables declaration//GEN-END:variables
}
