package table;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VAN BASTEM
 */
public class Table {
    String table;
    Object column;

    public Table() {
    }

    public Table(String table, Object dato) {
        this.table = table;
        this.column = dato;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Object getColumn() {
        return column;
    }

    public void setColumn(Object dato) {
        this.column = dato;
    }


}
