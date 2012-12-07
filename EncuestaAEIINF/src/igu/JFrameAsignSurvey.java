

package igu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class JFrameAsignSurvey extends javax.swing.JFrame {
    
    Statement sentencia;
    String currentUser;
    String currentAsign;
    JTextField jTxfArr[] = new JTextField[11];
    LinkedList<String[]> AsignaturasList=new LinkedList<String[]>();
    public JFrameAsignSurvey(Statement st, String currentUser) {
        this.sentencia=st;
        initComponents();
        setArr();
        handleComboBox();
        for(int i=0;i<jTxfArr.length-1;i++){
            handleJTextFields(jTxfArr[i],jTxfArr[i+1]);
        }
        handleJTextFields(jTxfq11,jButtonIngresar);
    }
    private void setArr(){
        jTxfArr[0]=jTxfq1;jTxfArr[1]=jTxfq2;
        jTxfArr[2]=jTxfq3;jTxfArr[3]=jTxfq4;
        jTxfArr[4]=jTxfq5;jTxfArr[5]=jTxfq6;
        jTxfArr[6]=jTxfq7;jTxfArr[7]=jTxfq8;
        jTxfArr[8]=jTxfq9;jTxfArr[9]=jTxfq10;
        jTxfArr[10]=jTxfq11;
    }
    private void clearAll(){
        for(int i=0;i<jTxfArr.length;i++){
            jTxfArr[i].setText("");
        }
        jCmbAsignatura.requestFocus();
    }
    
    private boolean isReady(){
        if(jCmbAsignatura.getSelectedIndex() == -1){
            jCmbAsignatura.getEditor().getEditorComponent().setBackground(Color.RED);
            return false;
        }
        
        for(int i = 0; i<jTxfArr.length;i++){
            try{
                if(Integer.parseInt(jTxfArr[i].getText())<0 || Integer.parseInt(jTxfArr[i].getText()) > 4 ){
                    return false;
                }
            }catch(java.lang.NumberFormatException nfe){
                return false;
            }
        }
        return true;
    }
    public void setCurrentUser(String current){
        this.currentUser=current;
    }
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTxfq1 = new javax.swing.JTextField();
        jCmbAsignatura = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButtonIngresar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTxfq2 = new javax.swing.JTextField();
        jTxfq3 = new javax.swing.JTextField();
        jTxfq4 = new javax.swing.JTextField();
        jTxfq7 = new javax.swing.JTextField();
        jTxfq6 = new javax.swing.JTextField();
        jTxfq5 = new javax.swing.JTextField();
        jTxfq9 = new javax.swing.JTextField();
        jTxfq8 = new javax.swing.JTextField();
        jTxfq10 = new javax.swing.JTextField();
        jTxfq11 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jCmbCycleAcad = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabelLastEnc = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setText("A\u00f1o Acad\u00e9mico: ");

        jLabel3.setText("1:");

        jLabel4.setText("2:");

        jLabel5.setText("3:");

        jLabel6.setText("4:");

        jLabel7.setText("5:");

        jLabel8.setText("6:");

        jLabel9.setText("7:");

        jTxfq1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jTxfq1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jCmbAsignatura.setEditable(true);
        jCmbAsignatura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4", "MTUIC - Metodologia del trabajo universitario y la investigacion cientifica", "CompMat - Complemento Matematico" }));

        jLabel10.setText("Asignatura: ");

        jLabel11.setText("8:");

        jLabel12.setText("9:");

        jLabel13.setText("10:");

        jLabel14.setText("11:");

        jButtonIngresar.setText("Ingresar");
        jButtonIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIngresarActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTxfq2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jTxfq2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTxfq3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jTxfq3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTxfq4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jTxfq4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTxfq7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jTxfq7.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTxfq6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jTxfq6.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTxfq5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jTxfq5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTxfq9.setFont(new java.awt.Font("Tahoma", 1, 12));
        jTxfq9.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTxfq8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jTxfq8.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTxfq10.setFont(new java.awt.Font("Tahoma", 1, 12));
        jTxfq10.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTxfq11.setFont(new java.awt.Font("Tahoma", 1, 12));
        jTxfq11.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 18));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ENCUESTA DE LA CATEDRA");

        jCmbCycleAcad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2007-2" }));

        jLabel16.setText("Ultima Encuesta Registrada:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCmbCycleAcad, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCmbAsignatura, 0, 435, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLastEnc, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonIngresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTxfq9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxfq3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxfq4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxfq5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxfq6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxfq7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxfq8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxfq10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxfq11, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTxfq1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTxfq2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 243, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel1)
                    .addComponent(jCmbCycleAcad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCmbAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTxfq1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTxfq2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxfq3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxfq4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxfq5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxfq6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxfq7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxfq8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxfq9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxfq10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxfq11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelLastEnc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void jButtonIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIngresarActionPerformed
        System.out.println(isReady());
        if(isReady()){
            String user=currentUser;            
            String selectedItem = jCmbAsignatura.getSelectedItem().toString();
            String cycle = getAsignCycleFromName(selectedItem);
            ResultSet rs;
            try {
                String query = "select mark" + cycle + " from Usuario where Id='" + user + "'";
                System.out.println(query);                        
                rs = sentencia.executeQuery(query);
                rs.next();
                int currentMark = rs.getInt(1);
                final String idEncuesta = user+(currentMark+1);
                String idAsign = getAsignIdFromName(jCmbAsignatura.getSelectedItem().toString());
                String acadYear= jCmbCycleAcad.getSelectedItem().toString();
                
                System.out.println("idAsign "+idAsign);
                System.out.println("idEncuesta "+idEncuesta);
                
                sentencia.execute("insert into EncuestaAsignatura values ('" +
                        idEncuesta + "','" + acadYear + "','" + idAsign + "',"+
                        jTxfq1.getText() + "," + jTxfq2.getText() + "," +
                        jTxfq3.getText() + "," + jTxfq4.getText() + "," +
                        jTxfq5.getText() + "," + jTxfq6.getText() + "," +
                        jTxfq7.getText() + "," + jTxfq8.getText() + "," +
                        jTxfq9.getText() + "," + jTxfq10.getText() + "," +
                        jTxfq11.getText() + ")");
                String updateQuery="update Usuario set mark"+cycle + " = " + (currentMark+1) +
                        " where Id = '" + user + "'";
                System.out.println(updateQuery);
                sentencia.execute(updateQuery);
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            JOptionPane.showMessageDialog(null,"Encuesta Registrada Correctamente con ID " + idEncuesta );
                            jLabelLastEnc.setText(idEncuesta);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });                                
                clearAll();
                //rs.close();
                //sentencia.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //sentencia.execute("insert into EncuestaAsignaturas")
        }else{
            
        }
    }//GEN-LAST:event_jButtonIngresarActionPerformed
     private String getAsignIdFromName(String asignName){
        String idSelected = "";
        for(int i=0; i < AsignaturasList.size();i++){
            if(AsignaturasList.get(i)[1].equals(asignName)){
                idSelected = AsignaturasList.get(i)[0];
                break;
            }
        }
        return idSelected;
    }
     private String getAsignCycleFromName(String asignName){
        String idSelected = "";
        for(int i=0; i < AsignaturasList.size();i++){
            if(AsignaturasList.get(i)[1].equals(asignName)){
                idSelected = AsignaturasList.get(i)[2];
                break;
            }
        }
        return idSelected;
    }    
    private void handleComboBox(){
        
        ((JTextField)jCmbAsignatura.getEditor().getEditorComponent()).addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent evt){
                JTextField source= (JTextField)evt.getSource();
                char ch=evt.getKeyChar();
                if(ch == KeyEvent.CHAR_UNDEFINED || Character.isISOControl(ch)){
                    if(evt.getKeyCode() == 10)
                        jTxfq1.requestFocus();
                    System.out.println(ch);
                    return;
                }
                int pos = source.getCaretPosition();
                String str = source.getText();
                if(str.length() == 0){
                    jCmbAsignatura.removeAllItems();
                    for(String[] arrStr : AsignaturasList){
                        jCmbAsignatura.addItem(arrStr[1]);
                    }
                    return;
                }
                
                
                jCmbAsignatura.removeAllItems();
                for(int i=0;i<AsignaturasList.size();i++){
                    String item = AsignaturasList.get(i)[1];
                    String itemForTest = item.toLowerCase();
                    if(itemForTest.startsWith(str.toLowerCase())){
                        jCmbAsignatura.addItem(item);
                    }
                }
                
                for(int i=0;i<AsignaturasList.size();i++){
                    String item = AsignaturasList.get(i)[1];
                    String itemForTest = item.toUpperCase();
                    if(itemForTest.startsWith(str.toUpperCase())){
                        source.setText(item);
                        source.setCaretPosition(item.length());
                        source.moveCaretPosition(pos);
                        jCmbAsignatura.getEditor().getEditorComponent().setBackground(Color.white);
                        break;
                    }
                }
                jCmbAsignatura.hidePopup();
                jCmbAsignatura.showPopup();
            }
        });
        jCmbAsignatura.getEditor().getEditorComponent().addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                JTextField source = (JTextField)evt.getSource();
                jCmbAsignatura.removeAllItems();
                for(String[] arrStr : AsignaturasList){
                    jCmbAsignatura.addItem(arrStr[1]);
                }
                
                source.setCaretPosition(source.getText().length());
                source.moveCaretPosition(0);
            }
            public void mouseReleased(MouseEvent evt){
                JTextField source = (JTextField)evt.getSource();
                if(jCmbAsignatura.getSelectedIndex()!=-1)
                    source.setBackground(Color.WHITE);
            }
        });
    }
    protected void loadAsignaturas(){
        jCmbAsignatura.removeAllItems();
        AsignaturasList.removeAll(AsignaturasList);
        ResultSet rs=null;
        
        try {
            rs = sentencia.executeQuery("select * from Asignatura");
            String idAsing = "";
            String nombAsign = "";
            String cycleAsign = "";
            boolean next= rs.next();
            while(next){
                idAsing = rs.getString(1);
                nombAsign = rs.getString(2);
                cycleAsign = rs.getString(3);
                AsignaturasList.add(new String[]{idAsing, nombAsign, cycleAsign});
                jCmbAsignatura.addItem(nombAsign);
                next=rs.next();
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void handleJTextFields(final JTextField curr, final JComponent next){
        curr.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e) {
            }
            
            public void focusLost(FocusEvent e) {
                String val = curr.getText();
                if( !val.equals("0") && !val.equals("1") && !val.equals("2")
                && !val.equals("3") && !val.equals("4")){
                    curr.setBackground(Color.RED);
                }else
                    curr.setBackground(Color.white);
            }
        });
        curr.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent evt){
                char ch = evt.getKeyChar();
                if( ch == KeyEvent.CHAR_UNDEFINED || Character.isISOControl(ch))
                    return;
                try{
                    int intVal= Integer.parseInt(String.valueOf(ch));
                    if( intVal >= 0 && intVal <= 4){
                        //System.out.println(evt.getKeyCode());
                        isReady(curr, next);
                    }else{
                        System.out.println(evt.getKeyCode());
                        curr.setText("");
                        curr.setBackground(Color.RED);
                    }
                }catch (NumberFormatException nfe){
                    System.out.println(evt.getKeyCode());
                    curr.setText("");
                    curr.setBackground(Color.RED);
                }
            }
            public void keyPressed(KeyEvent evt){
                char ch=evt.getKeyChar();
                if( ch == KeyEvent.CHAR_UNDEFINED || Character.isISOControl(ch))
                    return;
                curr.setText("");
            }
        });
    }
    
    public void isReady(JTextField source, JComponent next){
        if(source.getText().equals("")){
            source.setBackground(Color.RED.brighter());
        }else{
            source.setBackground(Color.white);
            next.requestFocus();
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonIngresar;
    private javax.swing.JComboBox jCmbAsignatura;
    private javax.swing.JComboBox jCmbCycleAcad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelLastEnc;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTxfq1;
    private javax.swing.JTextField jTxfq10;
    private javax.swing.JTextField jTxfq11;
    private javax.swing.JTextField jTxfq2;
    private javax.swing.JTextField jTxfq3;
    private javax.swing.JTextField jTxfq4;
    private javax.swing.JTextField jTxfq5;
    private javax.swing.JTextField jTxfq6;
    private javax.swing.JTextField jTxfq7;
    private javax.swing.JTextField jTxfq8;
    private javax.swing.JTextField jTxfq9;
    // End of variables declaration//GEN-END:variables
    
}
