package table;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VAN BASTEM
 */
public class Column {

    private boolean isAutoIncrement;
    private String nameColumn;
    private String typeSql;
    private String columnSize;

    public Column() {
    }

    public Column(boolean isAutoIncrement, String nameColumn, String typeSql, String columnSize) {
        this.isAutoIncrement = isAutoIncrement;
        this.nameColumn = nameColumn;
        this.typeSql = typeSql;
        this.columnSize = columnSize;
    }

    public String getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(String columnSize) {
        this.columnSize = columnSize;
    }

    public boolean isIsAutoIncrement() {
        return isAutoIncrement;
    }

    public void setIsAutoIncrement(boolean isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }

    public String getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(String nameColumn) {
        this.nameColumn = nameColumn;
    }

    public String getTypeSql() {
        return typeSql;
    }

    public void setTypeSql(String typeSql) {
        this.typeSql = typeSql;
    }



}
