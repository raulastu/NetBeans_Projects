package admin;

import admin.live.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

public class ConcursoPanel extends javax.swing.JPanel {

    public static int DEFAULT_VALOR_PROBLEM = 10;
    public static int DEFAULT_PENALTY_PROBLEM = 4;
    public static long SUBMIT_TIME = 4 * 60 * 1000; // en milisegundos
    public static String APPLICATION_NAME = "RC^2 Admin";
    public static char PROBLEMCONSTANTS[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    static double versionApp = 1.0;
    static Connection con;
    static LinkedList<Object[]> problemList = new LinkedList<Object[]>();
    JTable problemaTempTable;
    private String idConcursoSelected;
    //
    PanLogins panLogins;
    ServidorConcurso servidor;
    JFileChooser chooser = new JFileChooser();
    //JFileChooser chooserIn = new JFileChooser();
    RCJTable concursoTable;
    RCJTable problemaTable;
    RCJTable concursantesConcursoTable;
    //
    private String idConcursoToAddProblems;
    JFrame owner;

    public ConcursoPanel(Connection con) {
        initComponents();
        /*@Concursantes Form*/
        panConcursantes.setLayout(new FlowLayout());
        panConcursantes.add(new PanConcursantes(con));

        /*@NuevoConcurso Form**/
        this.con = con;
        problemaTempTable = new JTable(new ProblemasTempTableModel());
        scrpProblemas.setViewportView(problemaTempTable);
        loadProblemaFormDefaults();
        loadProblemasTableDefaults();

        /*@Concursos Form**/
        //loadProblemaArrayData();        
        LinkedHashMap<String, String[]> columnsC = new LinkedHashMap<String, String[]>();
        columnsC.put("Id_Concurso", new String[]{"ID", "10"});
        columnsC.put("Nombre", new String[]{"Nombre", "30"});
        columnsC.put("Fecha", new String[]{"Fecha", "10"});
        columnsC.put("Locacion", new String[]{"Locacion", "15"});
        columnsC.put("Inscripcion", new String[]{"Inscripcion", "10"});
        columnsC.put("Premio", new String[]{"Premio", "10"});
        columnsC.put("Descripcion", new String[]{"Descripción", "60"});
        columnsC.put("total_time", new String[]{"Duracion", "20"});
        columnsC.put("left_time", new String[]{"Tiempo Restante", "20"});
        String conditionC = "Where Estado = '" + cmbEstado.getSelectedItem() + "'";

        concursoTable = new RCJTable(con, "Concurso", columnsC, conditionC);

        jscpConcurso.setViewportView(concursoTable);

        LinkedHashMap<String, String[]> columnsPT = new LinkedHashMap<String, String[]>();
        columnsPT.put("Id_Problema", new String[]{"ID", "5"});
        columnsPT.put("Nombre", new String[]{"Nombre", "100"});
        columnsPT.put("Valor", new String[]{"Valor", "10"});
        columnsPT.put("Tiempo_Penalizacion", new String[]{"Tiempo Penalizacion", "10"});
        String conditionPT = "WHERE Id_Concurso = -1";

        problemaTable = new RCJTable(con, "Problema", columnsPT, conditionPT);
        scrpPro2.setViewportView(problemaTable);
        loadTableEvents();

        LinkedHashMap<String, String[]> columns = new LinkedHashMap<String, String[]>();
        columns.put("us.username", new String[]{"Usuario", "30"});
        columns.put("us.Nombres", new String[]{"Nombres", "50"});
        columns.put("us.Apellidos", new String[]{"Apellidos", "50"});
        columns.put("es.nombre", new String[]{"Escuela", "50"});
        String tables = "Campaign camp, usuario us, escuela es";
        String condition = "WHERE us.id_usuario = camp.id_usuario " +
                "AND us.id_escuela = es.id_escuela " +
                "AND camp.Id_Concurso = -1";
        concursantesConcursoTable = new RCJTable(con, tables, columns, condition);
        scrpConcursantesConcurso.setViewportView(concursantesConcursoTable);

    }

    /**
     * 
     * @return new tabIndex Creado
     */
    public int ejecutarServidorConcurso() {
        /*ServidorConcurso y Login*/
        String idConcurso = "" + concursoTable.getValueAt(concursoTable.getSelectedRow(), 0);
        String nombreConcurso = "" + concursoTable.getValueAt(concursoTable.getSelectedRow(), 1);
        String duracion = concursoTable.getValueAt(concursoTable.getSelectedRow(), 7) + "";
        String tiempoRestate = concursoTable.getValueAt(concursoTable.getSelectedRow(), 8) + "";
        System.err.println(tiempoRestate);
        System.err.println(duracion);

        panLogins = new PanLogins(con);

        Properties propiedadesConcurso = new Properties();
        propiedadesConcurso.setProperty("nombre", nombreConcurso);
        propiedadesConcurso.setProperty("id", idConcurso);
        propiedadesConcurso.setProperty("estado", "opened");
        propiedadesConcurso.setProperty("tiempo", duracion);

        PanClarificaciones panClarificaciones = new PanClarificaciones(con, idConcurso);

        Concurso concurso = new Concurso(idConcurso, nombreConcurso, duracion, tiempoRestate, con);

        servidor = new ServidorConcurso(propiedadesConcurso, concurso, con, panLogins, panClarificaciones);

        System.err.println(servidor.concurso.getNombre());
        System.err.println(concurso.getNombre());
        PanTiempoReset panTiempoReset = new PanTiempoReset(servidor, owner);

        JTabbedPane tabLiveContest = new JTabbedPane();
        tabLiveContest.addTab("Logins", panLogins);
        LiveMonitor panLiveMonitor = new LiveMonitor(con, idConcurso, 1);
        JScrollPane scrpLiveMonitor = new JScrollPane(panLiveMonitor);
        tabLiveContest.addTab("Live Live Live", scrpLiveMonitor);
        panLiveMonitor.start();

        panClarificaciones.setServidor(servidor);
        JScrollPane scrpClarificaciones = new JScrollPane(panClarificaciones);
        tabLiveContest.addTab("Clarificaciones", scrpClarificaciones);

        JPanel panLiveContest = new JPanel(new BorderLayout());

        panTiempoReset.setBorder(BorderFactory.createBevelBorder(1));
        panLiveContest.add(panTiempoReset, BorderLayout.NORTH);
        panLiveContest.add(tabLiveContest, BorderLayout.CENTER);

        jTabbedPane1.add(nombreConcurso, panLiveContest);
        butOpenContest.setEnabled(false);
        butOpenContest.setText("concurso " + nombreConcurso + " abierto");
        System.out.println("concurso " + nombreConcurso + " abierto");
        return jTabbedPane1.getTabCount() - 1;
    }

    //@INICIO DE FORMULARIO AGREGAR
    private void loadProblemasTableDefaults() {
        problemaTempTable.getTableHeader().setReorderingAllowed(false);
        int[] columnsSize = {50, 60, 60, 10, 10};
        for (int i = 0; i < columnsSize.length; i++) {
            problemaTempTable.getColumnModel().getColumn(i).setPreferredWidth(columnsSize[i] * 2);
        }
    }

    //Agregar problemas a un concurso existente
    static public void registrarProblemas(String idConcurso) {
        for (int i = 0; i < problemList.size(); i++) {
            try {
                PreparedStatement pStat2 = con.prepareStatement("INSERT INTO Problema" +
                        "(id_concurso, Nombre, abrev, input_file, output_file, valor, Tiempo_penalizacion)" +
                        "values (?,?,?,?,?,?,?)");
                pStat2.setString(1, idConcurso);
                pStat2.setString(2, problemList.get(i)[0] + ""); //nombre
                pStat2.setString(3, PROBLEMCONSTANTS[i] + ""); //Abr
                pStat2.setBytes(4, (byte[]) problemList.get(i)[1]);
                pStat2.setBytes(5, (byte[]) problemList.get(i)[2]);
                pStat2.setInt(6, Integer.parseInt(problemList.get(i)[3] + ""));
                pStat2.setInt(7, Integer.parseInt(problemList.get(i)[4] + ""));
                pStat2.executeUpdate();
                pStat2.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConcursoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        problemList.clear();
    }

    public static void ingresarConcurso(
            String name, String fecha, String location,
            String inscripcion, String premio, String description) {
        try {
            if (con == null) {
                con = new ConexionDB().getConnection();
            }
            PreparedStatement pStat = con.prepareStatement(
                    "INSERT INTO Concurso" +
                    "(Nombre, fecha, locacion, inscripcion, premio, " +
                    "descripcion) values (?,?,?,?,?,?)");
            pStat.setString(1, name);
            if (fecha.equals("")) {
                pStat.setNull(2, java.sql.Types.DATE);
            } else {
                pStat.setString(2, fecha);
            }
            pStat.setString(3, location);
            pStat.setString(4, inscripcion);
            pStat.setString(5, premio);
            pStat.setString(6, description);
            pStat.executeUpdate();
            pStat.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(ConcursoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static private byte[] getByteFromPath(String path) {
        FileInputStream fis = null;
        byte[] bytes = null;
        try {

            File file = new File(path);
            fis = new FileInputStream(file);
            bytes = new byte[(int) file.length()];
            fis.read(bytes, 0, (int) file.length());
            fis.close();
        } catch (IOException ex) {
            Logger.getLogger(ConcursoPanel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(ConcursoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        assert bytes != null;
        return bytes;
    }

    public void loadFormDefaultValuesConcurso() {
        txtNombreConcurso.setText("");
        txtLocacion.setText("");
        txtDateTime.setText("");
        txtInscripcion.setText("");
        txtPremio.setText("");
        txtDescripcion.setText("");
    }

    public void loadProblemaFormDefaults() {
        txtNomProb.setText("");
        labIn.setText("");
        labOut.setText("");
        spinPuntaje.setValue(new Integer(DEFAULT_VALOR_PROBLEM));
        spinTiempoPen.setValue(new Integer(DEFAULT_PENALTY_PROBLEM));
    }

    public void deleteProblema() {
        if (problemaTempTable.getSelectedRow() != -1) {
            //problemaTempTable.getValueAt(problemaTempTable.getSelectedRow(), 0);
            problemList.remove(problemaTempTable.getSelectedRow());
            problemaTempTable.clearSelection();
            problemaTempTable.revalidate();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un problema en la tabla primero", APPLICATION_NAME, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    static public void addProblemasToTempList(String name, String inFilePath, String outFilePath, int val, int penalty) {
        Object[] newProblem = new Object[5];
        newProblem[0] = name;
        newProblem[1] = getByteFromPath(inFilePath);
        newProblem[2] = getByteFromPath(outFilePath);
        newProblem[3] = val + "";
        newProblem[4] = penalty + "";
        problemList.add(newProblem);
    }

    public class ProblemasTempTableModel extends AbstractTableModel {

        String[] colNames = {"Nombre", "In Source", "Out Source", "Valor", "Penalizacion"};

        @Override
        public String getColumnName(int rowIndex) {
            return colNames[rowIndex];
        }

        public int getColumnCount() {
            return colNames.length;
        }

        public int getRowCount() {
            return problemList.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            switch( columnIndex ){
                case 0:
                    return (problemList.get(rowIndex))[0];
                case 1:
                    return new String((byte[]) problemList.get(rowIndex)[1]);
                case 2:
                    return new String((byte[]) problemList.get(rowIndex)[2]);
                case 3:
                    return problemList.get(rowIndex)[3];
                case 4:
                    return problemList.get(rowIndex)[4];
                default:
                    return "--";
            }
        }
    }
    //@FIN DE FORMULARIO NUEVO CONCURSO
    //@INICIO DE FORMULARIO CONCURSOS    

    private void loadTableEvents() {
        concursoTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                concursoTableSelectEvent();
            }
        });
        concursoTable.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                concursoTableSelectEvent();
            }
        });

        problemaTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                problemaTableSelectEvent();
            }
        });
        problemaTable.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                problemaTableSelectEvent();
            }
        });
    }

    void concursoTableSelectEvent() {
        idConcursoSelected = concursoTable.getValueAt(concursoTable.getSelectedRow(), 0) + "";
        concursantesConcursoTable.setCondition("Where us.id_usuario = camp.id_usuario " +
                "AND us.id_escuela = es.id_escuela " +
                "AND camp.Id_Concurso = " + idConcursoSelected);
        concursantesConcursoTable.loadDataArray();
        problemaTable.setCondition("WHERE Id_Concurso = " + idConcursoSelected + " " +
                "ORDER BY Id_Problema");
        problemaTable.loadDataArray();
        problemaTable.clearSelection();
        txtareaInput.setText("");
        txtareaOutput.setText("");
        panTestSet.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Seleccione un Problema para ver su Test Set"));
        setVisible(false);
        setVisible(true);
    }

    void problemaTableSelectEvent() {
        try {
            int idProb = Integer.parseInt(problemaTable.getValueAt(problemaTable.getSelectedRow(), 0) + "");
            String nombreProb = problemaTable.getValueAt(problemaTable.getSelectedRow(), 1) + "";

            ResultSet rs = con.createStatement().executeQuery(
                    "SELECT input_file, output_file FROM Problema " +
                    "WHERE id_problema = " + idProb);
            panTestSet.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Test Set de " + nombreProb));
            rs.next();
            txtareaInput.setText(new String(rs.getBytes(1)));
            txtareaOutput.setText(new String(rs.getBytes(2)));
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConcursoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean crearCampaigns(int idConcurso) {
        try {
            ArrayList<Integer> idProblemsList = new ArrayList<Integer>();
            String query = "SELECT id_problema FROM problema WHERE id_concurso = '" + idConcurso + "'";
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                idProblemsList.add(rs.getInt(1));
            }
            rs.close();

            String query1 = "SELECT id_campaign FROM campaign WHERE id_concurso = '" + idConcurso + "'";
            ResultSet rs1 = con.createStatement().executeQuery(query1);
            ArrayList<Integer> idCampaingList = new ArrayList<Integer>();
            while (rs1.next()) {
                idCampaingList.add(rs1.getInt(1));
            }
            rs1.close();

            for (Integer idCampaign : idCampaingList) {
                for (Integer idProblem : idProblemsList) {
                    String queryC = "INSERT INTO campaigndetalle" +
                            "(id_campaign, id_problema) VALUES (?,?)";
                    PreparedStatement ps = con.prepareStatement(queryC);
                    ps.setInt(1, idCampaign);
                    ps.setInt(2, idProblem);
                    ps.execute();
                    ps.close();
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConcursoPanel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panConcursantes = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombreConcurso = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtDateTime = new javax.swing.JTextField();
        butInsertar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        scrpProblemas = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        txtNomProb = new javax.swing.JTextField();
        butAddProb = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        spinPuntaje = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        spinTiempoPen = new javax.swing.JSpinner();
        butExOut = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        butExIn = new javax.swing.JButton();
        labOut = new javax.swing.JLabel();
        labIn = new javax.swing.JLabel();
        butEliminarProb = new javax.swing.JButton();
        txtTotalPuntos = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtLocacion = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtInscripcion = new javax.swing.JTextField();
        txtPremio = new javax.swing.JTextField();
        butRegistrarConcurso = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        labIdConcursoToModify = new javax.swing.JLabel();
        panConcursos = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jscpConcurso = new javax.swing.JScrollPane();
        cmbEstado = new javax.swing.JComboBox();
        tabConcursoDetalle = new javax.swing.JTabbedPane();
        scrpConcursantesConcurso = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        scrpPro2 = new javax.swing.JScrollPane();
        panTestSet = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtareaInput = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtareaOutput = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        butOpenContest = new javax.swing.JButton();
        EditAddProblemsButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();

        javax.swing.GroupLayout panConcursantesLayout = new javax.swing.GroupLayout(panConcursantes);
        panConcursantes.setLayout(panConcursantesLayout);
        panConcursantesLayout.setHorizontalGroup(
            panConcursantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 833, Short.MAX_VALUE)
        );
        panConcursantesLayout.setVerticalGroup(
            panConcursantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 605, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Concursantes", panConcursantes);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setText("Nombre:"); // NOI18N

        jLabel2.setText("Fecha (aaaa/mm/dd):"); // NOI18N

        jLabel3.setText("Descripción:"); // NOI18N

        butInsertar.setText("Registrar Problemas"); // NOI18N
        butInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butInsertarActionPerformed(evt);
            }
        });

        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        scrpProblemas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Problemas Agregados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Problema a agregar"));

        butAddProb.setText("Agregar problema "+ PROBLEMCONSTANTS[problemList.size()]);
        butAddProb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAddProbActionPerformed(evt);
            }
        });

        jLabel4.setText("Nombre:"); // NOI18N

        jLabel5.setText("Out File Path:"); // NOI18N

        jLabel6.setText("Valor:"); // NOI18N

        jLabel7.setText("Tiempo Penalización por Intento Fallido :"); // NOI18N

        butExOut.setText("Examinar"); // NOI18N
        butExOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butExOutActionPerformed(evt);
            }
        });

        jLabel8.setText("minutos"); // NOI18N

        jLabel9.setText("In File Path:");

        butExIn.setText("Examinar");
        butExIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butExInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(93, 93, 93)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(spinPuntaje, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinTiempoPen, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butAddProb))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(butExIn, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(butExOut, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labIn, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                            .addComponent(labOut, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)))
                    .addComponent(txtNomProb, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNomProb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labIn, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(butExIn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(butExOut, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labOut, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(spinTiempoPen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(butAddProb)
                    .addComponent(spinPuntaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(32, 32, 32))
        );

        butEliminarProb.setText("Eliminar Problema"); // NOI18N
        butEliminarProb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEliminarProbActionPerformed(evt);
            }
        });

        txtTotalPuntos.setEnabled(false);

        jLabel16.setText("Total de Puntos:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrpProblemas, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(butEliminarProb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 538, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrpProblemas, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butEliminarProb)
                    .addComponent(txtTotalPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap())
        );

        jLabel12.setText("Locación:");

        jLabel13.setText("Inscripción:");

        jLabel14.setText("Premio:");

        butRegistrarConcurso.setText("Registrar Concurso");
        butRegistrarConcurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butRegistrarConcursoActionPerformed(evt);
            }
        });

        jLabel15.setText("Id Concurso a Modificar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreConcurso, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtPremio, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtInscripcion, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDateTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtLocacion, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(butRegistrarConcurso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labIdConcursoToModify, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(butInsertar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreConcurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtLocacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtInscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtPremio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDescripcion))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labIdConcursoToModify, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butRegistrarConcurso)
                        .addGap(8, 8, 8)))
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(butInsertar)
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Nuevo Concurso", jPanel1);

        panConcursos.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                panConcursosComponentShown(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Concursos Registrados"));

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "REGISTRATION_OPEN", "REGISTRATION_CLOSED", "IN_PROGRESS", "FINALIZED" }));
        cmbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jscpConcurso, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jscpConcurso, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabConcursoDetalle.addTab("Concursantes Inscritos", scrpConcursantesConcurso);

        panTestSet.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Test Set"));

        txtareaInput.setColumns(20);
        txtareaInput.setEditable(false);
        txtareaInput.setRows(5);
        jScrollPane1.setViewportView(txtareaInput);

        txtareaOutput.setColumns(20);
        txtareaOutput.setEditable(false);
        txtareaOutput.setRows(5);
        jScrollPane3.setViewportView(txtareaOutput);

        jLabel10.setText("Input:");

        jLabel11.setText("Output:");

        javax.swing.GroupLayout panTestSetLayout = new javax.swing.GroupLayout(panTestSet);
        panTestSet.setLayout(panTestSetLayout);
        panTestSetLayout.setHorizontalGroup(
            panTestSetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTestSetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panTestSetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                .addContainerGap())
        );
        panTestSetLayout.setVerticalGroup(
            panTestSetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTestSetLayout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrpPro2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panTestSet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(53, 53, 53))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panTestSet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrpPro2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabConcursoDetalle.addTab("Problemas", jPanel5);
        tabConcursoDetalle.addTab("Ranking", jScrollPane2);

        butOpenContest.setText("Start Servidor"); // NOI18N
        butOpenContest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butOpenContestActionPerformed(evt);
            }
        });

        EditAddProblemsButton.setText("Editar/Add Problems");
        EditAddProblemsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditAddProblemsButtonActionPerformed(evt);
            }
        });

        jButton4.setText("Crear Campaigns");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Actualizar Estadisticas");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Old/New");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panConcursosLayout = new javax.swing.GroupLayout(panConcursos);
        panConcursos.setLayout(panConcursosLayout);
        panConcursosLayout.setHorizontalGroup(
            panConcursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panConcursosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panConcursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tabConcursoDetalle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panConcursosLayout.createSequentialGroup()
                        .addGroup(panConcursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton6)
                            .addGroup(panConcursosLayout.createSequentialGroup()
                                .addComponent(EditAddProblemsButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addGap(337, 337, 337)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panConcursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(butOpenContest, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(jButton5))))
                .addContainerGap())
            .addGroup(panConcursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panConcursosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panConcursosLayout.setVerticalGroup(
            panConcursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panConcursosLayout.createSequentialGroup()
                .addGap(253, 253, 253)
                .addGroup(panConcursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butOpenContest)
                    .addComponent(EditAddProblemsButton)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(panConcursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton5))
                .addGap(3, 3, 3)
                .addComponent(tabConcursoDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(panConcursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panConcursosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(370, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Concursos", panConcursos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 828, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("tab1", jPanel4);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 828, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("tab2", jPanel6);

        jTabbedPane1.addTab("tab4", jTabbedPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void panConcursosComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panConcursosComponentShown
        concursoTable.setCondition("WHERE Estado = '" + cmbEstado.getSelectedItem() + "'");
        concursoTable.loadDataArray();
    }//GEN-LAST:event_panConcursosComponentShown

    private void cmbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoActionPerformed
        concursoTable.setCondition("WHERE Estado = '" + cmbEstado.getSelectedItem() + "'");
        concursoTable.loadDataArray();
        jscpConcurso.setVisible(false);
        jscpConcurso.setVisible(true);
    }//GEN-LAST:event_cmbEstadoActionPerformed

    private void butEliminarProbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEliminarProbActionPerformed
        deleteProblema();
        try {
            butAddProb.setText("Agregar problema " + PROBLEMCONSTANTS[problemList.size()]);
        } catch (Exception e) {
            problemList.removeLast();
            JOptionPane.showMessageDialog(null, "No es posible agregar más problemas", APPLICATION_NAME, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_butEliminarProbActionPerformed

    private void butExOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butExOutActionPerformed

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String absFile = chooser.getSelectedFile().getAbsoluteFile() + "";
            labOut.setText(absFile);
        }

}//GEN-LAST:event_butExOutActionPerformed

    private void butAddProbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAddProbActionPerformed
        addProblemasToTempList(
                txtNomProb.getText(),
                labIn.getText(),
                labOut.getText(),
                Integer.parseInt("" + spinPuntaje.getValue()),
                Integer.parseInt("" + spinTiempoPen.getValue()));
        loadProblemaFormDefaults();
        problemaTempTable.revalidate();
        try {
            butAddProb.setText("Agregar problema " + PROBLEMCONSTANTS[problemList.size()]);
        } catch (Exception e) {
            problemList.removeLast();
            JOptionPane.showMessageDialog(null, "No es posible agregar más problemas", APPLICATION_NAME, JOptionPane.ERROR_MESSAGE);
        }
        int s = 0;
        for (Object[] objects : problemList) {
            s += Integer.parseInt(objects[3] + "");
        }
        txtTotalPuntos.setText(s + "");
    }//GEN-LAST:event_butAddProbActionPerformed

    private void butInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butInsertarActionPerformed
        registrarProblemas(idConcursoSelected);
        loadFormDefaultValuesConcurso();
        problemaTempTable.revalidate();
    }//GEN-LAST:event_butInsertarActionPerformed

    private void butExInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butExInActionPerformed
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String absFile = chooser.getSelectedFile().getAbsoluteFile() + "";
            labIn.setText(absFile);
        }
    }//GEN-LAST:event_butExInActionPerformed

    private void butOpenContestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butOpenContestActionPerformed
        if (concursoTable.getSelectedRow() != -1) {
            jTabbedPane1.setSelectedIndex(ejecutarServidorConcurso());
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un Concurso");
        }
}//GEN-LAST:event_butOpenContestActionPerformed

    private void butRegistrarConcursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butRegistrarConcursoActionPerformed

        ingresarConcurso(txtNombreConcurso.getText(),
                txtDateTime.getText(),
                txtLocacion.getText(),
                txtInscripcion.getText(),
                txtPremio.getText(),
                txtDescripcion.getText());
        loadFormDefaultValuesConcurso();
        problemaTempTable.revalidate();
    }//GEN-LAST:event_butRegistrarConcursoActionPerformed

    private void EditAddProblemsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditAddProblemsButtonActionPerformed

        labIdConcursoToModify.setText(concursoTable.getDataArray()[concursoTable.getSelectedRow()][0]);
        txtNombreConcurso.setText(concursoTable.getDataArray()[concursoTable.getSelectedRow()][1]);
        txtDateTime.setText(concursoTable.getDataArray()[concursoTable.getSelectedRow()][2]);
        txtLocacion.setText(concursoTable.getDataArray()[concursoTable.getSelectedRow()][3]);
        txtInscripcion.setText(concursoTable.getDataArray()[concursoTable.getSelectedRow()][4]);
        txtPremio.setText(concursoTable.getDataArray()[concursoTable.getSelectedRow()][5]);
        txtDescripcion.setText(concursoTable.getDataArray()[concursoTable.getSelectedRow()][6]);
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_EditAddProblemsButtonActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (idConcursoSelected != null) {
            if (crearCampaigns(Integer.parseInt(idConcursoSelected))) {
                JOptionPane.showMessageDialog(null, "Se crearon las campañas exitosamente", APPLICATION_NAME, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Ya existen campañas creadas", APPLICATION_NAME, JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un concurso", APPLICATION_NAME, JOptionPane.QUESTION_MESSAGE);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            CallableStatement cs = con.prepareCall("call SP__UD_Stats");
            cs.execute();
            cs.close();
            JOptionPane.showMessageDialog(null, "Estadisticas Actualizadas ;)");
        } catch (SQLException ex) {
            Logger.getLogger(ConcursoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            CallableStatement cs = con.prepareCall("call SP__UD_CampaignOldNew(?)");
            cs.setInt(1, Integer.parseInt(idConcursoSelected));
            cs.execute();
            cs.close();//GEN-LAST:event_jButton6ActionPerformed
            JOptionPane.showMessageDialog(null, "Old/New Actualizados");
        } catch (SQLException ex) {
            Logger.getLogger(ConcursoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EditAddProblemsButton;
    private javax.swing.JButton butAddProb;
    private javax.swing.JButton butEliminarProb;
    private javax.swing.JButton butExIn;
    private javax.swing.JButton butExOut;
    private javax.swing.JButton butInsertar;
    private javax.swing.JButton butOpenContest;
    private javax.swing.JButton butRegistrarConcurso;
    private javax.swing.JComboBox cmbEstado;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JScrollPane jscpConcurso;
    private javax.swing.JLabel labIdConcursoToModify;
    private javax.swing.JLabel labIn;
    private javax.swing.JLabel labOut;
    private javax.swing.JPanel panConcursantes;
    private javax.swing.JPanel panConcursos;
    private javax.swing.JPanel panTestSet;
    private javax.swing.JScrollPane scrpConcursantesConcurso;
    private javax.swing.JScrollPane scrpPro2;
    private javax.swing.JScrollPane scrpProblemas;
    private javax.swing.JSpinner spinPuntaje;
    private javax.swing.JSpinner spinTiempoPen;
    private javax.swing.JTabbedPane tabConcursoDetalle;
    private javax.swing.JTextField txtDateTime;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtInscripcion;
    private javax.swing.JTextField txtLocacion;
    private javax.swing.JTextField txtNomProb;
    private javax.swing.JTextField txtNombreConcurso;
    private javax.swing.JTextField txtPremio;
    private javax.swing.JTextField txtTotalPuntos;
    private javax.swing.JTextArea txtareaInput;
    private javax.swing.JTextArea txtareaOutput;
    // End of variables declaration//GEN-END:variables

    public void startFrame() {
        owner = new JFrame(APPLICATION_NAME);
        owner.add(this);
        owner.pack();
        owner.setVisible(true);
        owner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        owner.setExtendedState(JFrame.MAXIMIZED_BOTH);
        PanConcursantes panC = (PanConcursantes) this.panConcursantes.getComponent(0);
//        frame.setLocation(
//                (int) (frame.getToolkit().getScreenSize().getWidth() / 2 -
//                frame.getSize().getWidth() / 2),
//                (int) (frame.getToolkit().getScreenSize().getHeight() / 2 -
//                frame.getSize().getHeight() / 2));
        panC.frameAddConcurso.addWindowListener(new WindowAdapter() {

            @Override
            public void windowActivated(WindowEvent e) {
                owner.setEnabled(false);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                owner.setEnabled(true);
            }
        });
    }

    public static void main(String[] args) {
        ConexionDB db = new ConexionDB();
        //LiveMonitor lm = new LiveMonitor(db.getConnection(), "1");
        //ShowComponent.showPanel(lm);
        ConcursoPanel concurso = new ConcursoPanel(db.getConnection());
        concurso.startFrame();

    }

    void showError(Exception ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }
}
