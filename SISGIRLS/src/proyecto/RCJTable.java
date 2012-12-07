package proyecto;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rCuser
 */
public class RCJTable extends JTable {

    private String[][] dataArray = new String[0][];
    private Connection con;
    private LinkedHashMap<String, String[]> columns;
    private String table;
    private String condition;
    private String[] colNames;
    int colWidths[];
    int factor = 4;
    boolean isDebug = false;

    public RCJTable(Connection con, String table, LinkedHashMap<String, String[]> columns, String condition) {
        this.con = con;
        this.table = table;
        this.columns = columns;
        this.condition = condition;
        colNames = new String[columns.size()];
        colWidths = new int[columns.size()];

        int i = 0;
        for (String[] val : columns.values()) {
            colNames[i] = val[0];
            colWidths[i++] = Integer.parseInt(val[1]);
        }
        setModel(new RCTableModel());
        loadTableDefaults();
    }

    public String[][] getDataArray() {
        return dataArray;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setColumnWidthFactor(int factor) {
        this.factor = factor;
    }

    public void loadDataArray() {
        try {
            String query = "SELECT count(*) FROM " + table + " " + condition;
            System.out.println(query);
            ResultSet rs1 = con.createStatement().executeQuery(query);
            rs1.next();
            int rows = rs1.getInt(1);
            System.out.println(rows);
            rs1.close();
            dataArray = new String[rows][columns.size()];
            String colList = "";
            for (String val : columns.keySet()) {
                colList += val + ", ";
            }
            colList = colList.substring(0, colList.length() - 2);
            query = "SELECT " + colList + " FROM " + table + " " + condition;
            if (isDebug) {
                System.out.println(query);
            }
            ResultSet rs = con.createStatement().executeQuery(
                    "SELECT " + colList + " FROM " + table + " " + condition);

            for (int i = 0; rs.next() != false; i++) {
                for (int j = 0; j < columns.size(); j++) {
                    dataArray[i][j] = rs.getString(j + 1) + "";
                }
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
        } finally {
            revalidate();
            setVisible(false);
            setVisible(true);
        }
    }

    private void loadTableDefaults() {        
        getTableHeader().setReorderingAllowed(false);
        for (int i = 0; i < colWidths.length; i++) {
            getColumnModel().getColumn(i).setPreferredWidth(colWidths[i] * factor);
        }        
        setSelectionMode(0);
    }

    public String getSelectedValue(int column) {
        return "" + getValueAt(getSelectedRow(), column);
    }

    class RCTableModel extends AbstractTableModel {

        @Override
        public String getColumnName(int column) {
            return colNames[column];
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return dataArray[rowIndex][columnIndex];
        }

        public int getRowCount() {
            return dataArray.length;
        }

        public int getColumnCount() {
            return colNames.length;
        }
    }
}
