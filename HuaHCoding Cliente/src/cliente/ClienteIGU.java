package cliente;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JFileChooser;

/*
 * ClienteIGU.java
 *
 * Created on November 28, 2008, 1:34 PM
 */
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class ClienteIGU extends javax.swing.JPanel {

    String login;
    Cliente cliente;
    public JFileChooser myFileChooser;
    String nombreDeArchivo;
    static long maxFileSizeToSubmit = 1024 * 512;
    //lab
    private String[][] clarificacionData;
    private JTable tablaClarificaciones;
    String[] columnNameClars = {"Usuario", "Categoria", "Preguntado", "Pregunta", "Respondido", "Respuesta"};
    int[] columnClarsWidth = {10, 18, 5, 60, 5, 60};
    //
    FrameSolicitarClarificacion frameSolicitarClarificacion;
    FrameVerClarificacion frameVerClarificacion;

    public ClienteIGU(Cliente cliente, String login, String[][] clarificacionData) {
        this.cliente = cliente;
        this.login = login;
        this.clarificacionData = RCUtil.deleteCol(clarificacionData, 0, 6);
        initComponents();
        myFileChooser = new JFileChooser();
        myFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        myFileChooser.setApproveButtonText("Guardar");

        frameSolicitarClarificacion = new FrameSolicitarClarificacion(cliente);
        RCContainer.centerFrame(frameSolicitarClarificacion);
        frameVerClarificacion = new FrameVerClarificacion();
        RCContainer.centerFrame(frameVerClarificacion);
        frameVerClarificacion.setAlwaysOnTop(true);
        //
        tablaClarificaciones = new JTable(new ClarificacionTableModel());
        tablaClarificaciones.setSelectionMode(0);
        tablaClarificaciones.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    verClarificacion();
                }
            }
        });
        tablaClarificaciones.getTableHeader().setReorderingAllowed(false);

        scrpClars.setViewportView(tablaClarificaciones);
        for (int i = 0; i < columnClarsWidth.length; i++) {
            tablaClarificaciones.getColumnModel().getColumn(i).setPreferredWidth(columnClarsWidth[i]);
        }

    }

    public void estadoInicialPresentar() {
        cmbProblems.setEnabled(false);
        butGuardarInput.setText("Escoga un Problema");
        labIntentosFallidos.setVisible(false);
        butGuardarInput.setEnabled(false);
        labTiempoPresentar.setVisible(false);
        labTiempoPresentarLab.setVisible(false);
        butPresentar.setEnabled(false);
        panPresentar.setVisible(false);
        txtOutputFile.setText("");
        txtCodigoFuente.setText("");
        cmbProblems.setSelectedIndex(-1);
    }

    public void habilitarPresentar() {
        cmbProblems.setEnabled(true);
    }

    public void isReadyToShowSubmit() {
        if (!txtOutputFile.getText().equals("") &&
                !txtCodigoFuente.getText().equals("")) {
            butPresentar.setEnabled(true);
        }
    }

    public void cargarClarificaciones(String[][] clarificaciones) {
        clarificacionData = RCUtil.deleteCol(clarificaciones, 0, 6);
        tablaClarificaciones.revalidate();
        revalidate();
        setVisible(false);
        setVisible(true);
    }

    public void verClarificacion() {
        if (tablaClarificaciones.getSelectedRow() != -1) {
            String user = tablaClarificaciones.getValueAt(tablaClarificaciones.getSelectedRow(), 0) + "";
            String pregunta = tablaClarificaciones.getValueAt(tablaClarificaciones.getSelectedRow(), 3) + "";
            String respuesta = tablaClarificaciones.getValueAt(tablaClarificaciones.getSelectedRow(), 5) + "";
            frameVerClarificacion.verClarificacion(user, pregunta, respuesta);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una clarificaciòn para verla", Cliente.clienteName, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class ClarificacionTableModel extends AbstractTableModel {

        @Override
        public String getColumnName(int column) {
            return columnNameClars[column];
        }

        public int getRowCount() {
            return clarificacionData.length;
        }

        public int getColumnCount() {
            return columnNameClars.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return clarificacionData[rowIndex][columnIndex];
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labTiempo = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        cmbProblems = new javax.swing.JComboBox();
        panPresentar = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        butSeleccionarTuSalida = new javax.swing.JButton();
        butSeleccionarTuCodigoFuente = new javax.swing.JButton();
        butPresentar = new javax.swing.JButton();
        txtOutputFile = new javax.swing.JTextField();
        txtCodigoFuente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        butGuardarInput = new javax.swing.JButton();
        labTiempoPresentar = new javax.swing.JLabel();
        labTiempoPresentarLab = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labIntentosFallidos = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        scrpClars = new javax.swing.JScrollPane();
        butAsk = new javax.swing.JButton();
        butVerAsk = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        passCurrent = new javax.swing.JPasswordField();
        passNew = new javax.swing.JPasswordField();
        passReNew = new javax.swing.JPasswordField();
        butCambiar = new javax.swing.JButton();
        butLogout = new javax.swing.JButton();
        labEstado = new javax.swing.JLabel();
        labContestName = new javax.swing.JLabel();
        labUser = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labPts = new javax.swing.JLabel();
        labPenalizacion = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        labTiempo.setText("00:00:00");

        jPanel1.setFocusable(false);
        jPanel1.setOpaque(false);

        panPresentar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel6.setText("tu archivo de salida:");

        jLabel7.setText("tu código fuente:");

        butSeleccionarTuSalida.setText("Explorar");
        butSeleccionarTuSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSeleccionarTuSalidaActionPerformed(evt);
            }
        });

        butSeleccionarTuCodigoFuente.setText("Explorar");
        butSeleccionarTuCodigoFuente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSeleccionarTuCodigoFuenteActionPerformed(evt);
            }
        });

        butPresentar.setText("Presentar");
        butPresentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butPresentarActionPerformed(evt);
            }
        });

        txtOutputFile.setBackground(new java.awt.Color(255, 255, 204));
        txtOutputFile.setEditable(false);

        txtCodigoFuente.setBackground(new java.awt.Color(255, 255, 204));
        txtCodigoFuente.setEditable(false);

        javax.swing.GroupLayout panPresentarLayout = new javax.swing.GroupLayout(panPresentar);
        panPresentar.setLayout(panPresentarLayout);
        panPresentarLayout.setHorizontalGroup(
            panPresentarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPresentarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panPresentarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panPresentarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panPresentarLayout.createSequentialGroup()
                        .addComponent(butSeleccionarTuSalida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOutputFile, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
                    .addGroup(panPresentarLayout.createSequentialGroup()
                        .addComponent(butSeleccionarTuCodigoFuente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoFuente, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panPresentarLayout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(butPresentar, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                .addGap(148, 148, 148))
        );
        panPresentarLayout.setVerticalGroup(
            panPresentarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPresentarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panPresentarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(butSeleccionarTuSalida)
                    .addComponent(txtOutputFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panPresentarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(butSeleccionarTuCodigoFuente)
                    .addComponent(txtCodigoFuente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(butPresentar)
                .addContainerGap())
        );

        jLabel9.setText("Estado :");

        butGuardarInput.setText("Escoga un Problema");
        butGuardarInput.setBorderPainted(false);
        butGuardarInput.setOpaque(false);
        butGuardarInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butGuardarInputActionPerformed(evt);
            }
        });

        labTiempoPresentar.setFont(new java.awt.Font("Tahoma", 1, 11));
        labTiempoPresentar.setText("00:00");

        labTiempoPresentarLab.setText("tiempo para presentar:");

        jLabel1.setText("Problema:");

        labIntentosFallidos.setText("Intentos fallidos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panPresentar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel1))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbProblems, javax.swing.GroupLayout.Alignment.TRAILING, 0, 556, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(butGuardarInput)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labIntentosFallidos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                                .addComponent(labTiempoPresentarLab)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labTiempoPresentar)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProblems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butGuardarInput)
                    .addComponent(labTiempoPresentar)
                    .addComponent(jLabel9)
                    .addComponent(labTiempoPresentarLab)
                    .addComponent(labIntentosFallidos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panPresentar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(" Presentar", new javax.swing.ImageIcon(getClass().getResource("/img/checkmark.png")), jPanel1); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        butAsk.setText("Solicitar Clarificación");
        butAsk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAskActionPerformed(evt);
            }
        });

        butVerAsk.setText("Ver Clarificación");
        butVerAsk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butVerAskActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrpClars, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(butVerAsk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 381, Short.MAX_VALUE)
                        .addComponent(butAsk)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrpClars, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butVerAsk)
                    .addComponent(butAsk))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Clarificaciones", jPanel2);

        jPanel4.setEnabled(false);

        jLabel2.setText("Password Actual:");

        jLabel3.setText("Nuevo Password:");

        jLabel4.setText("Nuevo Password Confirmado:");

        passCurrent.setEnabled(false);

        passNew.setEnabled(false);

        passReNew.setEnabled(false);

        butCambiar.setText("Cambiar Password");
        butCambiar.setEnabled(false);
        butCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCambiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(butCambiar)
                    .addComponent(passReNew, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                    .addComponent(passCurrent, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                    .addComponent(passNew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(passCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(passReNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(butCambiar)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Configuraciones", jPanel4);

        butLogout.setMnemonic('S');
        butLogout.setText("Logout");
        butLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butLogoutActionPerformed(evt);
            }
        });

        labEstado.setText("Estado");

        labContestName.setFont(new java.awt.Font("Verdana", 1, 18));
        labContestName.setText("Nombre del Concurso");

        labUser.setText("Coder: "+ login);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("penalización");

        jLabel8.setText("pts");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labPts, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labPenalizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labPenalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labPts, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel10.setText("Tiempo Restante:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labContestName, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                                .addComponent(labEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labTiempo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(labUser))
                            .addComponent(butLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labContestName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(labEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(labTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(butLogout)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    private void butGuardarInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butGuardarInputActionPerformed

        myFileChooser.setSelectedFile(new File(nombreDeArchivo));
        myFileChooser.setDialogTitle("Guardar Archivo de Entrada");
        myFileChooser.setApproveButtonMnemonic(71);
        myFileChooser.setApproveButtonMnemonic('G');
        myFileChooser.setApproveButtonText("Guardar");
        //System.out.println((char) myFileChooser.getApproveButtonMnemonic());
        if (myFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println(myFileChooser.getSelectedFile().getAbsolutePath());
            if (myFileChooser.getSelectedFile().exists()) {
                int opt = JOptionPane.showConfirmDialog(this,
                        "Archivo " + myFileChooser.getSelectedFile().getAbsoluteFile() +
                        " existe, ¿Desea Sobreescribir?");
                if (opt == JOptionPane.OK_OPTION) {
                    cliente.fileToSavePath = myFileChooser.getSelectedFile().getAbsolutePath();
                    int id = Integer.parseInt("" + cliente.problems.get(cmbProblems.getSelectedIndex())[0]);
                    saveInput(id);
                }
            } else {
                cliente.fileToSavePath = myFileChooser.getSelectedFile().getAbsolutePath();
                int id = Integer.parseInt("" + cliente.problems.get(cmbProblems.getSelectedIndex())[0]);
                saveInput(id);
            }
        }
}//GEN-LAST:event_butGuardarInputActionPerformed

    private void saveInput(int id) {
        setSubmiting();
        cliente.startSubmit(id);
    }

    public void setSubmiting() {
        cmbProblems.setEnabled(false);
        labTiempoPresentarLab.setVisible(true);
        labTiempoPresentar.setVisible(true);
        panPresentar.setVisible(true);
    }

    private void butPresentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butPresentarActionPerformed
        String out = txtOutputFile.getText();
        String sourceCode = txtCodigoFuente.getText();
        if (out.equals(sourceCode)) {
            JOptionPane.showMessageDialog(null, "El output y código fuente no pueden ser el mismo",
                    Cliente.clienteName, JOptionPane.ERROR_MESSAGE);
        } else {
            cliente.submitProblem(out, sourceCode);
        }
    }//GEN-LAST:event_butPresentarActionPerformed

    private void butSeleccionarTuSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSeleccionarTuSalidaActionPerformed
        myFileChooser.setDialogTitle(Cliente.clienteName + "- Seleccionar Input File");
        myFileChooser.setApproveButtonText("Seleccionar tu Output File");
        myFileChooser.setSelectedFile(new File(""));
        if (myFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            //myFileChooser.getSelectedFile().;
            if (myFileChooser.getSelectedFile().length() < maxFileSizeToSubmit) {
                txtOutputFile.setText(myFileChooser.getSelectedFile().getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(this, "Archivo demasiado grande, Verifique si el archivo es el correcto");
            }
            isReadyToShowSubmit();
        }
}//GEN-LAST:event_butSeleccionarTuSalidaActionPerformed

    private void butSeleccionarTuCodigoFuenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSeleccionarTuCodigoFuenteActionPerformed
        myFileChooser.setDialogTitle(Cliente.clienteName + "- Seleccionar Codigo Fuente");
        myFileChooser.setApproveButtonText("Seleccionar Codigo Fuente");
        myFileChooser.setSelectedFile(new File(""));
        if (myFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (myFileChooser.getSelectedFile().length() < maxFileSizeToSubmit) {
                txtCodigoFuente.setText(myFileChooser.getSelectedFile().getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(this, "Archivo demasiado grande, Verifique si el archivo es el correcto");
            }
            isReadyToShowSubmit();
        }
}//GEN-LAST:event_butSeleccionarTuCodigoFuenteActionPerformed

    private void butAskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAskActionPerformed
        frameSolicitarClarificacion.setVisible(true);
    }//GEN-LAST:event_butAskActionPerformed

    private void butVerAskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butVerAskActionPerformed
        verClarificacion();
    }//GEN-LAST:event_butVerAskActionPerformed

    private void butCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCambiarActionPerformed
        String current = String.valueOf(passCurrent.getPassword());
        String newPass = String.valueOf(passNew.getPassword());
        String reNewPass = String.valueOf(passReNew.getPassword());
        if (!newPass.equals(reNewPass)) {
            JOptionPane.showMessageDialog(this, "Los campos Nuevo Password y Nuevo Password Confirmado no son iguales");
        } else {
            cliente.cambiarPassword(current, newPass);
        }
        passCurrent.setText("");
        passNew.setText("");
        passReNew.setText("");
    }//GEN-LAST:event_butCambiarActionPerformed

    private void butLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butLogoutActionPerformed
        cliente.Logout();
}//GEN-LAST:event_butLogoutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAsk;
    private javax.swing.JButton butCambiar;
    public javax.swing.JButton butGuardarInput;
    private javax.swing.JButton butLogout;
    public javax.swing.JButton butPresentar;
    public javax.swing.JButton butSeleccionarTuCodigoFuente;
    public javax.swing.JButton butSeleccionarTuSalida;
    private javax.swing.JButton butVerAsk;
    public javax.swing.JComboBox cmbProblems;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JLabel labContestName;
    public javax.swing.JLabel labEstado;
    public javax.swing.JLabel labIntentosFallidos;
    public javax.swing.JLabel labPenalizacion;
    public javax.swing.JLabel labPts;
    public javax.swing.JLabel labTiempo;
    public javax.swing.JLabel labTiempoPresentar;
    public javax.swing.JLabel labTiempoPresentarLab;
    private javax.swing.JLabel labUser;
    public javax.swing.JPanel panPresentar;
    private javax.swing.JPasswordField passCurrent;
    private javax.swing.JPasswordField passNew;
    private javax.swing.JPasswordField passReNew;
    private javax.swing.JScrollPane scrpClars;
    private javax.swing.JTextField txtCodigoFuente;
    private javax.swing.JTextField txtOutputFile;
    // End of variables declaration//GEN-END:variables
}
