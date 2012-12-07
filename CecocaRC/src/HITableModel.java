
import javax.swing.table.AbstractTableModel;

class HITableModel extends AbstractTableModel {

    String[][] data;
    String[] columns;

    public HITableModel(String[][] data, String[] columns) {
        this.data = data;
        this.columns = columns;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public int getColumnCount() {
        return columns.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
}