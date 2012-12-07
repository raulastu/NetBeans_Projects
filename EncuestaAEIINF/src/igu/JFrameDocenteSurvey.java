package igu;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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


public class JFrameDocenteSurvey extends javax.swing.JFrame {
    
    Statement sentencia;
    String currentUser;
    String currentAsign;
    JTextField jTxfArr[] = new JTextField[10];
    LinkedList<String[]> DocentesList=new LinkedList<String[]>();
    LinkedList<String[]> AsignOfDocenteList=new LinkedList<String[]>();
    public JFrameDocenteSurvey(Statement st, String currentUser) {
        this.sentencia=st;
        initComponents();
        handleComboBox();
        setArr();
        for(int i=0;i<jTxfArr.length-1;i++){
            handleJTextFields(jTxfArr[i],jTxfArr[i+1]);
        }
        handleJTextFields(jTxfq10,jButtonIngresar);
        
    }
    private void setArr(){
        jTxfArr[0]=jTxfq1;jTxfArr[1]=jTxfq2;
        jTxfArr[2]=jTxfq3;jTxfArr[3]=jTxfq4;
        jTxfArr[4]=jTxfq5;jTxfArr[5]=jTxfq6;
        jTxfArr[6]=jTxfq7;jTxfArr[7]=jTxfq8;
        jTxfArr[8]=jTxfq9;jTxfArr[9]=jTxfq10;
    }
    private void clearAll(){
        for(int i=0;i<jTxfArr.length;i++){
            jTxfArr[i].setText("");
        }
        jCmbDocente.requestFocus();
    }
    
    private boolean isReadyCmbAsign(){
        if(jCmbDocente.getSelectedIndex() == -1){
            jCmbDocente.getEditor().getEditorComponent().setBackground(Color.RED);
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
        jCmbDocente = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButtonIngresar = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jTxfq2 = new javax.swing.JTextField();
        jTxfq3 = new javax.swing.JTextField();
        jTxfq4 = new javax.swing.JTextField();
        jTxfq7 = new javax.swing.JTextField();
        jTxfq6 = new javax.swing.JTextField();
        jTxfq5 = new javax.swing.JTextField();
        jTxfq9 = new javax.swing.JTextField();
        jTxfq8 = new javax.swing.JTextField();
        jTxfq10 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jCmbCycleAcad = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabelLastEnc = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jCmbAsignOfDocente = new javax.swing.JComboBox();

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

        jCmbDocente.setEditable(true);

        jLabel10.setText("Docente:");

        jLabel11.setText("8:");

        jLabel12.setText("9:");

        jLabel13.setText("10:");

        jButtonIngresar.setText("Ingresar");
        jButtonIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIngresarActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancelar");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
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

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 18));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ENCUESTA DEL DOCENTE");

        jCmbCycleAcad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2007-2" }));

        jLabel16.setText("Ultima Encuesta Registrada:");

        jLabel15.setText("Asignatura:");

        jCmbAsignOfDocente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Asignaturas de Docente Seleccionado" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
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
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTxfq1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTxfq2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 243, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCmbCycleAcad, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLastEnc, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonIngresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(376, 376, 376))
                    .addComponent(jCmbDocente, 0, 435, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addComponent(jCmbAsignOfDocente, 0, 435, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCmbCycleAcad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCmbDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCmbAsignOfDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelLastEnc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed
    
    private void jButtonIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIngresarActionPerformed
        System.out.println(isReadyCmbAsign());
        if(isReadyCmbAsign()){
            String user=currentUser;
            String cycle = getAsignCycleFromName(jCmbAsignOfDocente.getSelectedItem().toString());
            System.out.println("cycle" + cycle);
            ResultSet rs = null;
            try {
                String query = "select mark" + cycle + " from Usuario where Id='" + user + "'";
                System.out.println(query);
                rs = sentencia.executeQuery(query);
                rs.next();
                int currentMark = rs.getInt(1);
                String idEncuesta = user+(currentMark+1);
                String cycleAcad = jCmbCycleAcad.getSelectedItem().toString();
                String idDocente = getDocenteIdFromName(jCmbDocente.getSelectedItem().toString());
                String idAsign = getAsignIdFromName(jCmbAsignOfDocente.getSelectedItem().toString());
                
                
                System.out.println("idAsign "+idAsign);
                System.out.println("idEncuesta "+idEncuesta);
                
                sentencia.execute("insert into EncuestaDocente values ('" +
                        idEncuesta + "','" + cycleAcad + "','" +
                        idDocente + "',' " + idAsign + "'," +
                        jTxfq1.getText() + "," + jTxfq2.getText() + "," +
                        jTxfq3.getText() + "," + jTxfq4.getText() + "," +
                        jTxfq5.getText() + "," + jTxfq6.getText() + "," +
                        jTxfq7.getText() + "," + jTxfq8.getText() + "," +
                        jTxfq9.getText() + "," + jTxfq10.getText()+ ")");
                String updateQuery="update Usuario set mark"+cycle + " = " + (currentMark+1) +
                        " where Id = '" + user + "'";
                System.out.println(updateQuery);
                sentencia.execute(updateQuery);
                JOptionPane.showMessageDialog(null,"Encuesta Registrada Correctamente con ID " + idEncuesta );
                jLabelLastEnc.setText(idEncuesta);
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
    private String getDocenteIdFromName(String docenteName){
        String idSelected = "";
        for(int i=0; i < DocentesList.size();i++){
            if(DocentesList.get(i)[1].equals(docenteName)){
                idSelected =  DocentesList.get(i)[0];
                break;
            }
        }
        return idSelected;
    }
    
    private String getAsignIdFromName(String asignName){
        String idSelected = "";
        for(int i=0; i < AsignOfDocenteList.size();i++){
            if(AsignOfDocenteList.get(i)[1].equals(asignName)){
                idSelected = AsignOfDocenteList.get(i)[0];
                break;
            }
        }
        return idSelected;
    }
    private String getAsignCycleFromName(String asignName){
        String idSelected = "";
        for(int i=0; i < AsignOfDocenteList.size();i++){
            if(AsignOfDocenteList.get(i)[1].equals(asignName)){
                idSelected = AsignOfDocenteList.get(i)[2];
                break;
            }
        }
        return idSelected;
    }    
    
    private void searchAsigns(){
        if(jCmbDocente.getSelectedIndex() != -1){
            String idDocente = getDocenteIdFromName(jCmbDocente.getSelectedItem().toString());
            System.out.println("idDocente" + idDocente);
            ResultSet rs=null;
            ResultSet rsAsigns = null;
            try {
                rs = sentencia.executeQuery("select IdAsignatura, NombAsign, Ciclo from [2007-2], Asignatura where Asignatura.id = [2007-2].idAsignatura and idDocente = " + idDocente);
                boolean next = rs.next();
                jCmbAsignOfDocente.removeAllItems();
                String asignName;
                String asignId;
                String asignCycle;
                while(next){
                    asignId= rs.getString(1);
                    asignName = rs.getString(2);
                    asignCycle = rs.getString(3);
                    AsignOfDocenteList.add(new String[]{asignId, asignName, asignCycle});
                    jCmbAsignOfDocente.addItem(asignName);
                    next = rs.next();
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else{
            System.out.println("ELSE");
        }
        
    }
    private void handleComboBox(){
        jCmbDocente.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                searchAsigns();
            }
        });
        
        ((JTextField)jCmbDocente.getEditor().getEditorComponent()).addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                searchAsigns();
            }
        });
        ((JTextField)jCmbDocente.getEditor().getEditorComponent()).addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent evt){
                LinkedHashSet<String> temporalList = new LinkedHashSet<String>();
                JTextField source= (JTextField)evt.getSource();
                char ch=evt.getKeyChar();
                if(ch == KeyEvent.CHAR_UNDEFINED || Character.isISOControl(ch)){
                    if(evt.getKeyCode() == 10){
                        jTxfq1.requestFocus();
                        searchAsigns();
                    }
                    System.out.println(ch);
                    return;
                }
                int pos = source.getCaretPosition();
                String str = source.getText();
                if(str.length() == 0){
                    jCmbDocente.removeAllItems();
                    for(String[] arrStr : DocentesList){
                        jCmbDocente.addItem(arrStr[1]);
                    }
                    return;
                }
                
                jCmbDocente.removeAllItems();
                for(int i=0;i<DocentesList.size();i++){
                    String item = DocentesList.get(i)[1];
                    String itemForTest = item.toLowerCase();
                    if(itemForTest.startsWith(str.toLowerCase())){
                        jCmbDocente.addItem(item);
                    }
                }
                
                for(int i=0;i<DocentesList.size();i++){
                    String item = DocentesList.get(i)[1];
                    String itemForTest = item.toUpperCase();
                    if(itemForTest.startsWith(str.toUpperCase())){
                        source.setText(item);
                        source.setCaretPosition(item.length());
                        source.moveCaretPosition(pos);
                        jCmbDocente.getEditor().getEditorComponent().setBackground(Color.white);
                        break;
                    }
                }
                jCmbDocente.hidePopup();
                jCmbDocente.showPopup();
            }
        });
        jCmbDocente.getEditor().getEditorComponent().addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                JTextField source = (JTextField)evt.getSource();
                jCmbDocente.removeAllItems();
                for(String[] arrStr : DocentesList){
                    jCmbDocente.addItem(arrStr[1]);
                }
                source.setCaretPosition(source.getText().length());
                source.moveCaretPosition(0);
            }
            public void mouseReleased(MouseEvent evt){
                JTextField source = (JTextField)evt.getSource();
                if(jCmbDocente.getSelectedIndex()!=-1)
                    source.setBackground(Color.WHITE);
            }
        });
    }
    protected void loadDocentes(){
        jCmbDocente.removeAllItems();
        DocentesList.removeAll(DocentesList);
        ResultSet rs=null;
        
        try {
            rs = sentencia.executeQuery("select * from Docente");
            String idDocente = "";
            String apePatDocente = "";
            String apeMatDocente = "";
            String nombDocente = "";
            boolean next= rs.next();
            while(next){
                idDocente = rs.getString(1);
                apePatDocente = rs.getString(2);
                apeMatDocente = rs.getString(3);
                nombDocente = rs.getString(4);
                final String totalNombre = apePatDocente + " " + apeMatDocente + " " + nombDocente;
                System.out.println(totalNombre);
                DocentesList.add(new String[]{idDocente, totalNombre});
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            jCmbDocente.addItem(totalNombre);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
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
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonIngresar;
    private javax.swing.JComboBox jCmbAsignOfDocente;
    private javax.swing.JComboBox jCmbCycleAcad;
    private javax.swing.JComboBox jCmbDocente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
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
