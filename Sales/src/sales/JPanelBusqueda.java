package sales;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import sales.queryTableModel;
import sales.tableModels.facturaTM;
import sales.tableModels.productoTM;
import sales.tableModels.vendedorTM;

public class JPanelBusqueda extends javax.swing.JPanel {
    
    String[] clienteCols=new String[]{"idcliente", "nombclie", "direclie"};
    String[] vendedorCols=new String[]{"idvendedor", "nombvend", "apatvend", "amatvend"};
    String[] productoCols=new String[]{"idproducto", "nombprod", "precprod", "stocprod"}; 
    String[] facturaCols=new String[]{"idfactura", "fechfact", "totafact", "idcliente", "idvendedor"};
    
    Statement sentencia;
    JTable tableResultados=new JTable();
    String selectedTable;
    public JPanelBusqueda(Statement st ) {
        sentencia=st;
       
        initComponents();
        tableResultados.setSize(new Dimension(100,100));
        jButtonAdv.setVisible(false);
        jScrollPane1.setViewportView(tableResultados);
        
        jComboBoxTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox source=(JComboBox)e.getSource();
                if(source.getSelectedItem().toString().equals("cliente")){                    
                    jComboBoxColumn.removeAllItems();
                    for(int i=0;i<clienteCols.length;i++){
                        jComboBoxColumn.addItem(clienteCols[i]);
                    }                       
                    jButtonAdv.setVisible(false);
                }                
                if(source.getSelectedItem().toString().equals("vendedor")){
                    jComboBoxColumn.removeAllItems();
                    for(int i=0;i<vendedorCols.length;i++){
                        jComboBoxColumn.addItem(vendedorCols[i]);
                    }
                    jButtonAdv.setVisible(false);
                }
                
                if(source.getSelectedItem().toString().equals("producto")){
                    jComboBoxColumn.removeAllItems();
                    for(int i=0;i<productoCols.length;i++){
                        jComboBoxColumn.addItem(productoCols[i]);
                    }                    
                    jButtonAdv.setVisible(false);
                }
                
                if(source.getSelectedItem().toString().equals("factura")){
                    jComboBoxColumn.removeAllItems();
                    for(int i=0;i<facturaCols.length;i++){
                        jComboBoxColumn.addItem(facturaCols[i]);
                    }                    
                    jButtonAdv.setVisible(true);
                }
                
            }
        });
        jButtonBuscar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                ResultSet rs;
                LinkedList<Cliente> clientes=new LinkedList<Cliente>();
                try {         
                    String condition="";
                    selectedTable = jComboBoxTable.getSelectedItem().toString();
                    
                    if(jTextFieldValue.getText().equals(""))
                        condition="";
                    else
                        condition=" where " + jComboBoxColumn.getSelectedItem().toString() + 
                                " like '" + jTextFieldValue.getText()+"' or " + 
                                jComboBoxColumn.getSelectedItem().toString() + " = '" + 
                                jTextFieldValue.getText()+ "'";
                    
                    System.out.println("condition " + condition);
                    if(selectedTable.equals("Seleccione una Tabla")){
                        jLabelAdvice.setText("Seleccione una tabla");
                        jLabelAdvice.setForeground(Color.RED);
                        throw new NullPointerException();
                    }    
                    
                    String queryN = "select count(" +
                            jComboBoxColumn.getSelectedItem().toString() + ") from vi_" +
                            selectedTable + condition;
                    System.out.println("queryN "+queryN);
                    ResultSet rsN=sentencia.executeQuery(queryN);
                    
                    rsN.next();
                    int n=rsN.getInt(1);
                    jLabelNroRes.setText(""+n);
                    String query="select * from vi_" + selectedTable + 
                            condition;
                    
                    rs = sentencia.executeQuery(query); 
                    Object[][] objrs=null;
                    String columnNamesSelected[]=null;
                    
                    int [] colSizes = null;
                    Class<?> clases[]=null;
                    if(selectedTable.equals("cliente")){
                        clases = new Class<?>[]{String.class, String.class, String.class};
                        colSizes=new int[]{10,100,100};
                        objrs=rC.toArrObjForCliente(rs,n,clienteCols.length,clases);
                        columnNamesSelected=clienteCols;
                    }                        
                    else if(selectedTable.equals("vendedor")){
                        clases = new Class<?>[]{String.class, String.class, String.class, String.class};
                        colSizes=new int[]{10,80,80,80};
                        objrs=rC.toArrObjForCliente(rs,n,vendedorCols.length,clases);
                        columnNamesSelected=vendedorCols;                    
                    }                        
                    else if(selectedTable.equals("producto")){
                        clases = new Class<?>[]{String.class, String.class, Double.class, Integer.class};
                        colSizes=new int[]{10,150,10,10};
                        objrs=rC.toArrObjForCliente(rs,n,productoCols.length,clases);                        
                        columnNamesSelected=productoCols;                      
                    }                        
                    else if(selectedTable.equals("factura")){
                        clases = new Class<?>[]{String.class, String.class, String.class, String.class, String.class};
                        colSizes=new int[]{10,130,50,10,10};
                        objrs=rC.toArrObjForCliente(rs,n,facturaCols.length,clases);
                        columnNamesSelected=facturaCols;    
                    }   
                                       
                    
                    tableResultados.setModel(new queryTableModel(objrs,columnNamesSelected,clases));
                    
                    tableResultados.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
                    
                    for(int i=0;i<colSizes.length;i++){
                        System.out.println(colSizes[i]);
                        tableResultados.getColumnModel().getColumn(i).setPreferredWidth(colSizes[i]);
                    }
                    
                    jLabelAdvice.setText("");
                    
                        
                    
                } catch (NullPointerException npe){
                    
                } catch (SQLException ex) {
                    jLabelAdvice.setText("carácter no valido");
                    jLabelAdvice.setForeground(Color.RED);
                    ex.printStackTrace();
                } 
                Cliente.show(clientes);
                
            }            
        });
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jButtonAdv = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jComboBoxTable = new javax.swing.JComboBox();
        jComboBoxColumn = new javax.swing.JComboBox();
        jTextFieldValue = new javax.swing.JTextField();
        jButtonBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jLabelNroRes = new javax.swing.JLabel();
        jLabelAdvice = new javax.swing.JLabel();

        jButtonAdv.setText("Avanzado >");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Criterios de B\u00fasqueda"));
        jComboBoxTable.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione una Tabla", "cliente", "vendedor", "producto", "factura" }));

        jComboBoxColumn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Columna", "Item 2", "Item 3", "Item 4" }));

        jTextFieldValue.setText("Value");

        jButtonBuscar.setText("Buscar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxColumn, 0, 348, Short.MAX_VALUE)
                    .addComponent(jComboBoxTable, 0, 348, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldValue, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBuscar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jComboBoxTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxColumn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jTextFieldValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 2, true), "Resultados"));

        jLabel1.setText("Resultados encontrados: ");

        jLabelNroRes.setFont(new java.awt.Font("Tahoma", 1, 14));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jLabelAdvice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelNroRes, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addComponent(jButtonAdv)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdv)
                    .addComponent(jLabel1)
                    .addComponent(jLabelNroRes, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAdvice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton jButtonAdv;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JComboBox jComboBoxColumn;
    protected javax.swing.JComboBox jComboBoxTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAdvice;
    private javax.swing.JLabel jLabelNroRes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldValue;
    // End of variables declaration//GEN-END:variables
    /*public static void main(String[]args){
        Console.run(new JPanelBusqueda(null));
    }*/
}
