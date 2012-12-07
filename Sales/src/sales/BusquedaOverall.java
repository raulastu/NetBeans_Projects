

package sales;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class BusquedaOverall extends JFrame{
    JPanelBusqueda panBusqueda;
    JPanelFactura panFactura;
    Statement sentencia;
    public BusquedaOverall(Statement sent) {
        panBusqueda = new JPanelBusqueda(sent);
        panFactura = new JPanelFactura(sent);
        panFactura.setVisible(false);
        panFactura.modeAdmin();
        this.sentencia=sent;
        initComponents();
        
        panBusqueda.tableResultados.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e) {
                if(panBusqueda.jComboBoxTable.getSelectedItem().toString().equals("factura")){
                    int Row=panBusqueda.tableResultados.getSelectedRow();
                    String i=panBusqueda.tableResultados.getValueAt(Row,0).toString();
                    ResultSet rs = null;
                    try {
                        String query = "select * from " + panBusqueda.selectedTable + " where idfactura = " + i;
                        System.out.println(query);
                        rs = sentencia.executeQuery(query);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    panFactura.inRS(rs,sentencia);
                }
            }
        });
        panBusqueda.jButtonAdv.addComponentListener(new ComponentListener(){
            public void componentResized(ComponentEvent e) {
            }
            
            public void componentMoved(ComponentEvent e) {
            }
            
            public void componentShown(ComponentEvent e) {
            }
            
            public void componentHidden(ComponentEvent evt) {                
                JButton source = (JButton)evt.getSource();
                setPreferredSize(new Dimension(440,520));
                pack();
                panFactura.setVisible(false);
                source.setText("Avanzado >");
            }
            
        });
        panBusqueda.jButtonAdv.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                JButton source = (JButton)evt.getSource();
                if(source.getText().equals("Avanzado >")){
                    setPreferredSize(new Dimension(970,520));
                    pack();
                    panFactura.setVisible(true);
                    source.setText("Avanzado <");
                }else{
                    setPreferredSize(new Dimension(440,520));
                    pack();
                    panFactura.setVisible(false);
                    source.setText("Avanzado >");
                }
            }
        });
    }
    public void initComponents(){
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        getContentPane().add(panBusqueda);
        getContentPane().add(panFactura);
        setPreferredSize(new Dimension(440,520));
        this.pack();
        this.setTitle("Busqueda Overall - rC");
        setVisible(true);
        //getContentPane().add()
    }
    
}
