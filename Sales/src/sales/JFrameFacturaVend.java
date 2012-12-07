package sales;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
public class JFrameFacturaVend extends JFrame {    
    JPanelFactura panFactura;
    Statement sentencia;
    public JFrameFacturaVend(Statement st) {
        this.sentencia = st;
        initComponents();
        setTitle("Factura-Venta - rC");
        
    }
    private void initComponents(){
        panFactura = new JPanelFactura(sentencia);
        panFactura.modeVendedor();
        getContentPane().add(panFactura);
        setPreferredSize(new Dimension(516,500));
        pack();
        //setVisible(true);
    }
    
}
