package admin;

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

    private String[][] dataArray;
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
        setModel(new ConcursoTableModel());
        loadTableDefaults();
        loadDataArray();
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

    /**
     * Carga los datos de la base de datos al array dataArray
     * runs once
     */
    public void loadDataArray() {
        String query = "";
        try {
            query = "SELECT count(*) FROM " + table + " " + condition;
//            query = "SELECT count(*) FROM "+tabl + condition;
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs1 = ps.executeQuery();
            rs1.next();
            int rows = rs1.getInt(1);
            rs1.close();
            ps.close();

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

            ps = con.prepareStatement("SELECT " + colList + " FROM " + table + " " + condition);
            ResultSet rs = ps.executeQuery();
            for (int i = 0; rs.next() != false; i++) {
                for (int j = 0; j < columns.size(); j++) {
                    dataArray[i][j] = rs.getString(j + 1) + "";
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConcursoPanel.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(query);
            System.out.println(ex);
        } finally {
            revalidate();
        }
    }

    private void loadTableDefaults() {
        //Desactivar el reordenamiento de las columnas
        getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < colWidths.length; i++) {
            getColumnModel().getColumn(i).setPreferredWidth(colWidths[i] * factor);
        }
        //seleccion Simple de la tabla (Una sola fila a la vez)
        setSelectionMode(0);
    }

    class ConcursoTableModel extends AbstractTableModel {

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
