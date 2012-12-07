
package sales;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.table.*;
import sales.Cliente;
public class queryTableModel extends AbstractTableModel{
    Object[][] obj;
    String[] columnName;
    Class<?> clases[];
    public queryTableModel(Object[][] obj,String[] coln, Class<?> []clases){     
        this.obj=obj;
        this.columnName=coln;
        this.clases=clases;
    }

    public int getRowCount() {
        return obj.length;
    }

    public int getColumnCount() {
        return columnName.length;
    }
    
    public String getColumnName(int i){
        return columnName[i];
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        return obj[rowIndex][columnIndex];
    }

    public Class<?> getColumnClass(int columnIndex) {
        return clases[columnIndex];
    }
    
}
