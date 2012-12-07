package admin.live;

import admin.ConexionDB;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class LiveMonitor extends JPanel implements Runnable {

    public static int WIDTHCELL = 120;
    public static int HEIGHTCELL = 40;
//    public static String problemsTitle = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //
    private int refreshInterval = 1;
    //
    private JLabel mainHeader[];
    private String nameMainHeader[] = {"Rank", "Competidor", "Puntos", "Penalización"};
    private int widthMainHeader[] = {35, 150, 50, 100};
    private JLabel mainCell[][];
    private Connection con;
    private String idConcurso;
    private String[][] campaignData;
    private JPanel mainPanel = new JPanel();
    private JPanel mainPanelHeader = new JPanel();
    //
    private boolean[][] solvedState;
    private String[][] time;
    private int[][] wrongTries;
    private String[][] problemData;
    //
    private JPanel detallePanel = new JPanel();
    private JPanel detallePanelHeader = new JPanel();
    private CellDetail[][] cell;
    private int[] arrCampIds;
    //private int nProblems;
    int rows;
    final int columns;
    private JLabel detalleHeader[];
    private GridBagConstraints gbc = new GridBagConstraints(
            0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
    Thread t;

    //
    public LiveMonitor(
            Connection con, String idConcurso, int refreshInterval) {
        this.con = con;
        this.idConcurso = idConcurso;
        this.refreshInterval = refreshInterval;
        this.t = new Thread(this);
        rows = getRows();
        columns = getNProblems();
        cell = new CellDetail[rows][columns];


        scrp.setPreferredSize(new Dimension(600, 450));
        codeArea.setFont(new Font("courier", Font.PLAIN, 12));
        codeArea.setBackground(Color.black);
        codeArea.setForeground(Color.white);
        scrp.setViewportView(codeArea);

        loadCampaignComponents();
        loadCampaignDetalleComponents(columns);

        refreshCampaignDetalleData();
        refreshComponents();
        //        
        setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(mainPanelHeader, gbc);
        gbc.gridx = 1;
        add(detallePanelHeader, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(mainPanel, gbc);
        gbc.gridx = 1;
        add(detallePanel, gbc);
    }

    /**
     * 
     * @param interval en segundos
     */
    public void setRefreshInterval(int interval) {
        this.refreshInterval = interval;
    }

    public void start() {
        t.start();
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(refreshInterval * 500);
                campaignData = getCampaignData(rows);
                refreshCampaignDetalleData();
                refreshComponents();
                revalidate();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(LiveMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * It loads once
     */
    private void loadCampaignComponents() {
        rows = getRows();
        campaignData = getCampaignData(rows);
        mainHeader = new JLabel[4];
        mainCell = new JLabel[campaignData.length][4];
        mainPanelHeader.setLayout(new GridBagLayout());
        mainPanel.setLayout(new GridBagLayout());

        for (int i = 0; i < mainHeader.length; i++) {
            gbc.gridx = i;
            mainHeader[i] = new JLabel(nameMainHeader[i], JLabel.CENTER);
            mainHeader[i].setPreferredSize(new Dimension(widthMainHeader[i], HEIGHTCELL));
            mainHeader[i].setOpaque(true);
            mainHeader[i].setBackground(Color.white);
            mainPanelHeader.add(mainHeader[i], gbc);
        }

        //Load mainCells
        for (int i = 0; i < mainCell.length; i++) {
            gbc.gridy = i;
            for (int j = 0; j < mainCell[i].length; j++) {
                mainCell[i][j] = new JLabel();
                if (j != 1) {
                    mainCell[i][j].setHorizontalAlignment(JLabel.CENTER);
                }
                mainCell[i][j].setPreferredSize(new Dimension(widthMainHeader[j], HEIGHTCELL));
                gbc.gridx = j;
                mainCell[i][j].setOpaque(true);
                if (i % 2 == 0) {
                    mainCell[i][j].setBackground(CellDetail.myGray);
                } else {
                    mainCell[i][j].setBackground(Color.white);
                }
                mainPanel.add(mainCell[i][j], gbc);
            }
        }
    }

    private void loadCampaignDetalleComponents(int nProblems) {
        //pts de cada problema
        getProblemValues(nProblems);

        detalleHeader = new JLabel[nProblems];
        detallePanelHeader.setLayout(new GridLayout(2, nProblems));
        for (int i = 0; i < detalleHeader.length; i++) {
            detalleHeader[i] = new JLabel(
                    "Problema " + problemData[i][3] + "", JTextField.CENTER);
            detalleHeader[i].setPreferredSize(new Dimension(WIDTHCELL, HEIGHTCELL / 2));
            detalleHeader[i].setBackground(Color.white);
            detalleHeader[i].setOpaque(true);
            setBorde(detalleHeader[i]);
            detalleHeader[i].setToolTipText(problemData[i][2]);
            detallePanelHeader.add(detalleHeader[i]);
        }

        for (int i = 0; i < problemData.length; i++) {
            JLabel lab = new JLabel(problemData[i][1] + "pt", JLabel.CENTER);
            lab.setPreferredSize(new Dimension(WIDTHCELL, HEIGHTCELL / 2));
            lab.setBackground(Color.white);
            lab.setOpaque(true);
            setBorde(lab);
            detallePanelHeader.add(lab);
        }

        detallePanel.setLayout(new GridBagLayout());

        for (int i = 0; i < cell.length; i++) {
            gbc.gridy = i;
            for (int j = 0; j < cell[i].length; j++) {
                gbc.gridx = j;
                cell[i][j] = new CellDetail();
                cell[i][j].setDefault();
                if (i % 2 == 0) {
                    cell[i][j].setGray();
                }
                detallePanel.add(cell[i][j], gbc);
                setBorde(cell[i][j]);
            }
        }
    }

    private void setBorde(JComponent lab) {
        lab.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.gray));
    }

    private void getProblemValues(int nProblems) {
        problemData = new String[nProblems][4];
        try {
            PreparedStatement ps = con.prepareStatement("SELECT Id_Problema, Valor, nombre, abrev From problema " +
                    "WHERE Id_Concurso = ? " +
                    "ORDER BY Id_Problema");
            ps.setString(1, idConcurso);
            ResultSet rs1 = ps.executeQuery();
            for (int i = 0; rs1.next(); i++) {
                problemData[i][0] = rs1.getString(1);
                problemData[i][1] = rs1.getString(2);
                problemData[i][2] = rs1.getString(3);
                problemData[i][3] = rs1.getString(4);
            }
            rs1.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(LiveMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshComponents() {
        for (int i = 0; i < mainCell.length; i++) {
            for (int j = 0; j < mainCell[i].length; j++) {
                mainCell[i][j].setText(campaignData[i][j]);
            }
        }
        for (int i = 0; i < solvedState.length; i++) {
            for (int j = 0; j < solvedState[i].length; j++) {
                cell[i][j].setDefault();
                if (solvedState[i][j]) {
                    cell[i][j].setSolved(time[i][j]);
                    final int x = i;
                    final int y = j;
//                    cell[i][j].setCursor(handCursor);
                    MouseListener[] a = cell[i][j].getMouseListeners();
                    for (int k = 0; a != null && k < a.length; k++) {
                        cell[i][j].removeMouseListener(a[k]);
                    }
                    cell[i][j].addMouseListener(new MouseAdapter() {

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            showCode(arrCampIds[x], problemData[y][0], campaignData[x][1], problemData[y][2]);
                        }
                    });
                } else {
                    cell[i][j].setUnsolved();
                }
                cell[i][j].setTries(wrongTries[i][j]);
            }
        }
    }
    private JScrollPane scrp = new JScrollPane();
    private JTextArea codeArea = new JTextArea();

    public void showCode(int idCampaign, String idProblem, String coder, String problemName) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT sourcecode FROM " +
                    "CampaignDetalle WHERE Id_Campaign = ? " +
                    "AND id_problema = ? ");
            ps.setInt(1, idCampaign);
            ps.setString(2, idProblem);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String code = new String(rs.getBytes(1));
            rs.close();
            ps.close();
            codeArea.setText(code);
            JOptionPane.showMessageDialog(null, scrp, "Código fuente de " + coder + " (Problema:" + problemName + ")", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(LiveMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getNProblems() {
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM Problema Where Id_Concurso = ?");
            ps.setString(1, idConcurso);
            ResultSet rs1 = ps.executeQuery();
            rs1.next();
            n = rs1.getInt(1);
            rs1.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(LiveMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    private void refreshCampaignDetalleData() {
        try {
            solvedState = new boolean[rows][columns];
            time = new String[rows][columns];
            wrongTries = new int[rows][columns];

            for (int i = 0; i < arrCampIds.length; i++) {
                PreparedStatement ps = con.prepareStatement("SELECT Id_Problema, Solved, Tiempo_Submision, Intentos_fallidos " +
                        "FROM campaigndetalle " +
                        "WHERE Id_Campaign = ? " +
                        "ORDER BY Id_Problema");
                ps.setString(1, arrCampIds[i] + "");
                ResultSet rsCampaignDet = ps.executeQuery();
//                ResultSet rsCampaignDet = con.createStatement().executeQuery(
//                        "SELECT Id_Problema, Solved, Tiempo_Submision, Intentos_fallidos " +
//                        "FROM campaigndetalle " +
//                        "WHERE Id_Campaign = " + arrCampIds[i] + " " +
//                        "ORDER BY Id_Problema");
                for (int j = 0; rsCampaignDet.next(); j++) {
                    solvedState[i][j] = rsCampaignDet.getBoolean(2);
                    time[i][j] = rsCampaignDet.getString(3);
                    wrongTries[i][j] = rsCampaignDet.getInt(4);
                }
                rsCampaignDet.close();
                ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LiveMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getRows() {
        int rows2 = 0;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM Campaign " +
                    "WHERE Id_Concurso = ?");
            ps.setString(1, idConcurso);
            ResultSet rs = ps.executeQuery();
            rs.next();
            rows2 = rs.getInt(1);
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(LiveMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rows2;
    }

    private String[][] getCampaignData(int rows) {
        String[][] data = null;
        try {
            data = new String[rows][4];
            String query = "SELECT camp.id_Campaign, camp.puesto, cons.username, " +
                    "camp.puntos, camp.penalizacion " +
                    "FROM usuario cons, Campaign camp " +
                    "WHERE cons.id_usuario = camp.id_usuario " +
                    "AND camp.Id_Concurso = ? " +
                    "ORDER BY camp.puntos DESC, camp.penalizacion ASC, camp.id_campaign";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, idConcurso);
            ResultSet rs1 = ps.executeQuery();
            arrCampIds = new int[rows];
            for (int i = 0; rs1.next() != false; i++) {
                arrCampIds[i] = rs1.getInt(1);
                data[i][0] = rs1.getString(2);
                data[i][1] = rs1.getString(3);
                data[i][2] = rs1.getString(4);
                data[i][3] = rs1.getString(5);
            }
            rs1.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(LiveMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public static void main(String[] args) {
        ConexionDB db = new ConexionDB();
        LiveMonitor lm = new LiveMonitor(db.getConnection(), "2", 1);
        RCContainer.showPanel(lm);
        lm.start();

    //frame.getContentPane(new LiveMonitor(con, TOOL_TIP_TEXT_KEY))
    }
}
