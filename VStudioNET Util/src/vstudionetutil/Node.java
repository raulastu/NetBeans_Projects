/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vstudionetutil;

/**
 *
 * @author rC
 */
public class Node {

    String sqlName;
    String sqlType;
    String sqlDbType;
    String sqlParamProcName;
    String csName;
    String csType;
    String csTypeNonPrimitive;
    String labelColName;
    String aspType;
    boolean isNullable = false;
    boolean insertable = true;
    boolean isPK = false;
    boolean precisioNeededInParam = false;
    boolean ScaleNeededInParam = false;
    int precision;
    int scale;

    public Node(String sqlColName, String csColName, String labelColName, String sqlType, String csharpType, String aspType, String sqlDbType, String sqlParamProcName) {
        this.sqlName = sqlColName;
        this.csName = csColName;
        this.labelColName = labelColName;
        this.sqlType = sqlType;
        this.csType = csharpType;
        this.aspType = aspType;
        this.sqlDbType = sqlDbType;
        this.sqlParamProcName = sqlParamProcName;
        this.csTypeNonPrimitive = csType;
    }

    @Override
    public String toString() {
        return sqlName + " ";
    }
}
