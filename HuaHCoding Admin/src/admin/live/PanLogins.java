package admin.live;

import admin.CompetitorState;
import admin.HiloCliente;
import admin.RCJTable;
import admin.ServidorConcurso;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class PanLogins extends javax.swing.JPanel {

    Connection con;
    JTable table;
    String[][] data;
    Comparator comp = new Comparator() {

        public int compare(Object o1, Object o2) {
            String[] a = (String[]) o1;
            String[] b = (String[]) o2;
            return b[0].compareTo(a[0]);
        }
        };

    public PanLogins(Connection con) {
        initComponents();
        this.con = con;

        data = new String[0][7];
        table = new JTable(new TableModel());
        scrpTablaLogins.setViewportView(table);
    }

    public void putData(LinkedHashMap<String, HiloCliente> idens) {
        int i = 0;
        data = new String[idens.size()][7];
        for (String id : idens.keySet()) {
            HiloCliente hc = idens.get(id);
            data[i][0] = id;
            data[i][1] = hc.getUser();
            data[i][2] = hc.getProperties().getProperty("puntos");
            data[i][3] = hc.getProperties().getProperty("penalizacion");
            data[i][4] = hc.getProperties().getProperty("submiting");
            data[i][5] = hc.getProperties().getProperty("submitTime");
            data[i++][6] = hc.getProperties().getProperty("idProblemSubmiting");
        }
        table.revalidate();
    }

    public void putData(HashMap<String, CompetitorState> idens) {
        int i = 0;
        data = new String[idens.size()][7];
        for (String username : idens.keySet()) {
            CompetitorState co = idens.get(username);
            data[i][0] = co.isLogged() ? "LoggedIn" : "";
            data[i][1] = username;
            data[i][2] = "";
            data[i][3] = "";
            data[i][4] = co.isSubmitting() + "";
            data[i][5] = co.getSubmittingTime() + "";
            data[i++][6] = co.getIdProblemSubmitting();
        }
        Arrays.sort(data, comp);
        table.revalidate();
        table.setVisible(false);
        table.setVisible(true);
    }

    class TableModel extends AbstractTableModel {

        String[] colNames = {"Logged In", "username", "puntos", "penalizacion", "submiting", "submitTime", "idProblemSubmiting"};

        @Override
        public String getColumnName(int column) {
            return colNames[column];
        }

        public int getRowCount() {
            return data.length;
        }

        public int getColumnCount() {
            return colNames.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        scrpTablaLogins = new javax.swing.JScrollPane();

        txtConsole.setColumns(20);
        txtConsole.setRows(5);
        jScrollPane3.setViewportView(txtConsole);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrpTablaLogins, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrpTablaLogins, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane scrpTablaLogins;
    public javax.swing.JTextArea txtConsole;
    // End of variables declaration//GEN-END:variables
}

