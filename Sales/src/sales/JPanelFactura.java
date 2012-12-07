
package sales;

import java.awt.Color;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import sales.queryTableModel;

public class JPanelFactura extends javax.swing.JPanel {
    
    ResultSet rsFact;
    Statement sentencia;
    JTable tableDetalle;
    JPanelSelProd panSelProd;
    boolean thereIsClient=false;
    boolean thereIsAtleastAProduct=false;
    ArrayList<Object[]> list;
    Object[][] objs=new Object[0][5];
    public JPanelFactura(Statement st) {
        this.sentencia=st;
        initComponents();
        panSelProd = new JPanelSelProd(sentencia);
        testButReg();
        tableDetalle=new JTable();
        jScrollPane1.setViewportView(tableDetalle);
        jtfCodClie.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                jButtonReg.setEnabled(false);
            }
        });
        jButtonReg.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){                
                try {
                    sentencia.execute("begin sp_insert_factura('" + jtfIGV.getText() + "'," +
                            jtfCodClie.getText() + ", "+ jtfIDvend.getText()+ ");end;");                
                    for(int i=0;i<objs.length;i++){
                        sentencia.execute("begin sp_insert_detalle('" + objs[i][0] + "'," + objs[i][3]+");end;");
                    }
                    JOptionPane.showMessageDialog(null,"Factura registrada correctamente");
                    setDefault();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }                
            }
        });
        jButtonAddProd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                JButton src=(JButton)evt.getSource();
                JPopupMenu pop=new JPopupMenu();
                pop.add(panSelProd);
                pop.show(src,0,0);
                
            }
        });
        panSelProd.jButtonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[][] newobjs= new Object[objs.length+1][5];
                System.arraycopy(objs,0,newobjs,0,objs.length);
                objs = newobjs;
                objs[objs.length-1][0] = panSelProd.jtfCodigo.getText();
                objs[objs.length-1][1] = panSelProd.jtfNombre.getText();
                objs[objs.length-1][2] = panSelProd.jtfPrecio.getText();
                objs[objs.length-1][3] = panSelProd.jSpinnerCantidad.getValue();
                objs[objs.length-1][4] = panSelProd.jtfImporte.getText();
                String[] coln=new String[]{"CodProd", "Descripcion Producto", "Precio Unit.", "Cantidad", "Importe"};
                Class<?>[] types =new Class<?>[]{String.class, String.class, String.class, String.class, String.class};
                tableDetalle.setModel(new queryTableModel(objs,coln,types));
                tableDetalle.getColumnModel().getColumn(0).setPreferredWidth(30);
                tableDetalle.getColumnModel().getColumn(1).setPreferredWidth(150);
                tableDetalle.getColumnModel().getColumn(2).setPreferredWidth(30);
                tableDetalle.getColumnModel().getColumn(3).setPreferredWidth(30);
                tableDetalle.getColumnModel().getColumn(4).setPreferredWidth(30);
                loadCompute();
            }
        });
        
        
        
        jButtonClie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt){
                try {
                    ResultSet rsCliente = sentencia.executeQuery("select nombclie from vi_cliente where idcliente = '" + jtfCodClie.getText()+"'");
                    if(rsCliente.next()){
                        jtfNombClie.setText(rsCliente.getString(1));
                        thereIsClient=true;
                        testButReg();
                    }else{
                        jtfNombClie.setText("NO EXISTE CLIENTE");
                        thereIsClient=false;
                        testButReg();
                    }
                    
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "carácter en codigo de cliente incorrecto");
                    ex.printStackTrace();
                }
                
            }
        });
        
    }
    
   
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jtfIGV = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfTotal = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfNombClie = new javax.swing.JTextField();
        jtfCodClie = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButtonClie = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtfFecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtfImpuesto = new javax.swing.JTextField();
        jtfSubTotal = new javax.swing.JTextField();
        jtfIDvend = new javax.swing.JTextField();
        jButtonReg = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jtfID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButtonEliminar = new javax.swing.JButton();
        jButtonAddProd = new javax.swing.JButton();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel2.setText("Fecha:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Cliente"));
        jLabel3.setText("Nombre:");

        jLabel4.setText("Cod:");

        jButtonClie.setText("!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(8, 8, 8)
                .addComponent(jtfCodClie, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonClie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfNombClie, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jtfNombClie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonClie)
                    .addComponent(jtfCodClie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setText("IGV:");

        jLabel7.setText("ID vendedor:");

        jLabel5.setText("Sub Total:");

        jLabel8.setText("Impuesto:");

        jLabel6.setText("Total:");

        jButtonReg.setText("Registrar");

        jButtonCancel.setText("Cancelar");

        jLabel1.setText("ID Factura:");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Detalle"));

        jButtonEliminar.setText("Eliminar Factura");

        jButtonAddProd.setText("Agregar Producto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfID, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfIDvend, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtfIGV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonReg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                        .addComponent(jButtonEliminar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonAddProd, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jtfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jtfIDvend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddProd, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(14, 14, 14)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEliminar)
                    .addComponent(jButtonReg)
                    .addComponent(jButtonCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    public void setDefault(){
        jtfCodClie.setText("");
        jtfNombClie.setText("");
        jtfFecha.setText("");
        jtfSubTotal.setText("");
        jtfTotal.setText("");
        jtfImpuesto.setText("");
        objs = new Object[0][4];
        
        tableDetalle=new JTable();
        jScrollPane1.setViewportView(tableDetalle);
        
        thereIsAtleastAProduct = false;
        thereIsClient = false;
        testButReg();
    }
    public void testButReg(){
        if(thereIsClient & thereIsAtleastAProduct){
            jButtonReg.setEnabled(true);
        } else
            jButtonReg.setEnabled(false);
    }
    public void inRS(ResultSet rs, Statement st){
        this.rsFact=rs;
        try {
            rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        load();
    }
    public void modeAdmin(){
        jButtonReg.setVisible(false);
        jButtonCancel.setVisible(false);
        jButtonEliminar.setVisible(true);
        jButtonClie.setVisible(false);
        jButtonAddProd.setVisible(false);
    }
    public void modeVendedor(){
        jButtonReg.setVisible(true);
        jButtonCancel.setVisible(true);
        jButtonEliminar.setVisible(false);
        jButtonClie.setVisible(true);
        jButtonAddProd.setVisible(true);
    }
    public void ingresarNuevo(){
        ResultSet rsFecha;
        ResultSet rsIdVend;
        ResultSet rsIGV;
        String igv="";
        String fecha ="";
        String idVend="";
        try {
            rsIGV = sentencia.executeQuery("select * from igv");
            rsIGV.next();
            igv = rsIGV.getString(1);
            rsIGV.close();
            rsFecha = sentencia.executeQuery("select sysdate from dual");
            rsFecha.next();
            fecha=rsFecha.getString(1);
            rsFecha.close();
            rsIdVend = sentencia.executeQuery("select * from currentuser");
            rsIdVend.next();
            idVend=rsIdVend.getString(1);
            rsIdVend.close();
                     
            
            jtfID.setEnabled(false);
            
            jtfFecha.setText(fecha);
            jtfFecha.setDisabledTextColor(Color.BLACK);
            jtfFecha.setEnabled(false);
            
            jtfIDvend.setText(idVend);
            jtfIDvend.setEnabled(false);
            jtfIDvend.setDisabledTextColor(Color.BLACK);
            
            jtfIGV.setEnabled(false);
            jtfIGV.setDisabledTextColor(Color.BLACK);
            jtfIGV.setText(igv);
            
            jtfSubTotal.setEnabled(false);
            jtfSubTotal.setDisabledTextColor(Color.BLUE);
            jtfImpuesto.setEnabled(false);
            jtfImpuesto.setDisabledTextColor(Color.BLACK);
            jtfTotal.setEnabled(false);
            jtfTotal.setDisabledTextColor(Color.BLACK);
            jtfNombClie.setEnabled(false);
            jtfNombClie.setDisabledTextColor(Color.BLACK);
            
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    public void loadCompute(){
        
        double subtotal=0;
        for(int i=0;i<objs.length;i++){
            subtotal += Double.parseDouble(""+objs[i][4]);
        }
        String impuesto = ""+subtotal*0.19;
        impuesto = impuesto.substring(0, impuesto.indexOf(".")+2);
        
        String stotal = ""+subtotal;
        stotal = stotal.substring(0, stotal.indexOf(".")+2);
        String total =""+ (subtotal*1.19);
        total = total.substring(0, total.indexOf(".")+2);
        jtfSubTotal.setText(stotal);
        jtfImpuesto.setText(impuesto);
        jtfTotal.setText(total);
        
        
        thereIsAtleastAProduct = true;           
        testButReg();
    }
    public void load(){
        try {
            String idFactura = rsFact.getString(1);
            jtfID.setText(idFactura);
            jtfFecha.setText(rsFact.getString(2));
            jtfIGV.setText(rsFact.getString(3));
            jtfSubTotal.setText(rsFact.getString(4));
            jtfTotal.setText(rsFact.getString(5));
            String codCliente = rsFact.getString(6);
            jtfCodClie.setText(codCliente);
            jtfIDvend.setText(rsFact.getString(7));
            String subtract= String.valueOf(rsFact.getDouble(5)-(rsFact.getDouble(4)));
            jtfImpuesto.setText(subtract.substring(0,subtract.indexOf(".")+3));
            
            ResultSet rsnomb=sentencia.executeQuery("select nombclie from vi_cliente where idcliente = " + codCliente);
            rsnomb.next();
            jtfNombClie.setText(rsnomb.getString(1));
            rsnomb.close();
            ResultSet rsprodusN=sentencia.executeQuery("select count(idfactura) from detalle where idfactura = " + idFactura);
            rsprodusN.next();
            int n=rsprodusN.getInt(1);
            rsprodusN.close();
            
            ResultSet rsprodus=sentencia.executeQuery("select det.idproducto, prod.nombprod, det.precprod, det.cantidad, det.importe from detalle det, producto prod where prod.idproducto = det.idproducto and idfactura = " + idFactura);
            
            Class<?> types[] = new Class<?>[]{String.class, String.class, Double.class, Integer.class, Double.class};
            String [] coln=new String[]{"Cod Prod", "Descripcion Producto", "Precio Unit", "Cantidad", "Importe"};
            tableDetalle.setModel(new queryTableModel(rC.toArrObjForCliente(rsprodus,n,coln.length,types),coln,types));
            tableDetalle.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableDetalle.getColumnModel().getColumn(1).setPreferredWidth(300);
            tableDetalle.getColumnModel().getColumn(2).setPreferredWidth(80);
            tableDetalle.getColumnModel().getColumn(3).setPreferredWidth(80);
            tableDetalle.getColumnModel().getColumn(4).setPreferredWidth(90);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddProd;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonClie;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonReg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jtfCodClie;
    private javax.swing.JTextField jtfFecha;
    private javax.swing.JTextField jtfID;
    private javax.swing.JTextField jtfIDvend;
    private javax.swing.JTextField jtfIGV;
    private javax.swing.JTextField jtfImpuesto;
    private javax.swing.JTextField jtfNombClie;
    private javax.swing.JTextField jtfSubTotal;
    private javax.swing.JTextField jtfTotal;
    // End of variables declaration//GEN-END:variables
    
}
