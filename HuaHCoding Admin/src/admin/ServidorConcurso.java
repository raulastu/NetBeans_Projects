package admin;

import admin.live.PanClarificaciones;
import admin.live.PanLogins;
import java.awt.Color;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Properties;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class ServidorConcurso implements Runnable {

    int port = 81;
    Thread t;
    public Connection con;
    private ServerSocket serverSocket;
    public LinkedHashMap<String, HiloCliente> concursantesList = new LinkedHashMap<String, HiloCliente>();
    //
    private PanLogins panLogins;
    public PanClarificaciones panClarificaciones;
    //
    public Properties propiedadesConcurso;
    public Concurso concurso;
    private HashMap<String, byte[]> problemsOutputs;
    private Timer timer;
    private boolean isDebug = false;
    public HashMap<String, CompetitorState> mapSubmitState;
    public static String IDENTIFICADOR = "username";

    public ServidorConcurso(Properties propiedadesConcurso, Concurso concurso,
            Connection dbCon, PanLogins panMonitor, PanClarificaciones panClarificaciones) {
        this.propiedadesConcurso = propiedadesConcurso;
        this.concurso = concurso;
        this.panClarificaciones = panClarificaciones;
        this.panLogins = panMonitor;
        this.con = dbCon;
        this.t = new Thread(this);
        mapSubmitState = new HashMap<String, CompetitorState>();
        loadConcursantesSubmitState();
        loadCorrectProblemsOutputArray();
        try {
            serverSocket = new ServerSocket(port, 50);
        } catch (IOException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        reloadCompetitorsList();
        t.start();
    }

    public void run() {
        try {
            while (true) {
                Socket unCliente = serverSocket.accept();
                validateLogin(unCliente);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carga los Estados de Submit de cada usuario
     */
    private void loadConcursantesSubmitState() {
        try {
//            String idConcurso = propiedadesConcurso.getProperty("id");
            String idConcurso = concurso.id;
            PreparedStatement ps = con.prepareStatement(
                    "SELECT LOWER(co." + IDENTIFICADOR + ") " +
                    "FROM Campaign ca, usuario co " +
                    "WHERE ca.id_usuario = co.id_usuario AND " +
                    "ca.Id_Concurso = ?");
            ps.setString(1, idConcurso);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String user = rs.getString(1);
                mapSubmitState.put(user, new CompetitorState(user, false, ConcursoPanel.SUBMIT_TIME, "-1"));
            }
//            System.out.println(mapSubmitState);
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Properties getProperties() {
        return propiedadesConcurso;
    }

    class CronometroConcurso extends TimerTask {

        private JLabel labTiempo;
        private JCheckBox chkAutoStop;

        public CronometroConcurso(JLabel labTiempo, JCheckBox chkAutoStop) {
            this.labTiempo = labTiempo;
            this.chkAutoStop = chkAutoStop;
        }

        @Override
        public void run() {
            labTiempo.setText(RCUtil.longToTime(concurso.leftTime));
            if (concurso.leftTime <= 0) {
                if (chkAutoStop.isSelected()) {
                    finalizarConcurso();
                    timer.cancel();
                } else {
                    concurso.leftTime -= 1000;
                }
                labTiempo.setForeground(Color.red);
            } else {
                reloadCompetitorsList();
                concurso.leftTime -= 1000;
            }
            concurso.updateTimes();
        }
    }

    class CronometroSubmit extends TimerTask {

        String user;
        Timer timer;
        String idProblema;

        public CronometroSubmit(String user, Timer timer, String idProblema) {
            this.user = user;
            this.timer = timer;
            this.idProblema = idProblema;
        }

        @Override
        public void run() {
            long time = Long.parseLong("" + mapSubmitState.get(user).getSubmittingTime());
            System.out.print(RCUtil.longToTime(time));
            time -= 1000;
            if (time <= 0) {//tiempo agotado
                mapSubmitState.get(user).setSubmittingTime(ConcursoPanel.SUBMIT_TIME);
                mapSubmitState.get(user).setSubmitting(false);
                // registrar intento fallido
                registrarIntento(getCampaignMetaData(user), idProblema, false, null);
                HiloCliente hilo = concursantesList.get(user);
                if (hilo != null) {
                    hilo.myProblems = getProblemsProperties(hilo.idCampaign);
                    hilo.updateMyProperties();
                    hilo.sendUTF("propro");
                    hilo.sendUnsharedObject(hilo.myProblems);
                    hilo.sendUnsharedObject(hilo.myProperties);
                    logMessage(user + "- tiempo de presentacion agotada");
                }
                timer.cancel();
            } else {
                mapSubmitState.get(user).setSubmittingTime(time);
            }
        }
    }

    /**
     * Retorna un arreglo de 4 enteros:
     *  - Duracion del concurso Horas, Min
     *  - Tiempo Restante Horas, Min
     * @return
     */
    public void comenzarConcurso(JLabel labTiempo, JCheckBox chkAutoStop) {
        timer = new Timer();
        timer.schedule(new CronometroConcurso(labTiempo, chkAutoStop), 0, 1000);
        //duracionConcurso = 1000 * 15;
        propiedadesConcurso.setProperty("estado", "started");
        concurso.estado = "started";
        propiedadesConcurso.setProperty("tiempoRestante", concurso.leftTime + "");
        for (String st : concursantesList.keySet()) {
            concursantesList.get(st).sendUTF("estado started " + concurso.leftTime);
        }
    }

    public void pause() {
        timer.cancel();
        propiedadesConcurso.setProperty("estado", "paused");
        concurso.estado = "paused";
        propiedadesConcurso.setProperty("tiempoRestante", concurso.leftTime + "");
        for (String st : concursantesList.keySet()) {
            concursantesList.get(st).sendUTF("estado paused " + concurso.leftTime);
        }
    }

    public void resume(JLabel labTiempo, JCheckBox chkAutoStop) {
        timer.purge();
        timer = new Timer();
        timer.schedule(new CronometroConcurso(labTiempo, chkAutoStop), 0, 1000);
        propiedadesConcurso.setProperty("estado", "started");
        concurso.estado = "started";
        propiedadesConcurso.setProperty("tiempoRestante", concurso.leftTime + "");
        for (String st : concursantesList.keySet()) {
            concursantesList.get(st).sendUTF("estado started " + concurso.leftTime);
        }
    }

    public void finalizarConcurso() {
        timer.cancel();
        propiedadesConcurso.setProperty("estado", "finalized");
        concurso.estado = "finalized";
        propiedadesConcurso.setProperty("tiempoRestante", concurso.leftTime + "");
        for (String st : concursantesList.keySet()) {
            concursantesList.get(st).sendUTF("estado finalized " + concurso.leftTime);
        }
    //serverSocket.close();
    }

    public void validateLogin(Socket client) {
        try {
            ObjectOutputStream dos = new ObjectOutputStream(client.getOutputStream());
            dos.flush();
            ObjectInputStream dis = new ObjectInputStream(client.getInputStream());
            String incomingIden = (String) dis.readUTF().toLowerCase();
            String incomingPass = (String) dis.readUTF();
            //check if contestant exists
            String query = "SELECT pass FROM usuario c, Campaign cam " +
                    "WHERE LOWER(" + IDENTIFICADOR + ") = ?" +
                    " AND c.id_usuario = cam.id_usuario " +
                    " AND pass = MD5(?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, incomingIden);
            ps.setString(2, incomingPass);
            ResultSet rs1 = ps.executeQuery();
            if (rs1.next()) { //succefull
                String dbPass = rs1.getString(1);
                if (isDebug) {
                    String ms = "user:" + incomingIden + " pass " + dbPass + " entry " + incomingPass;
                    System.out.println("rCdebug: " + ms);
                    logMessage(ms);
                }
                //(I) check if Contestant is registered for contest                
                PreparedStatement ps2 = con.prepareStatement(
                        "SELECT pass FROM usuario c, Campaign cam " +
                        "WHERE LOWER(" + IDENTIFICADOR + ") = ? " +
                        "AND c.id_usuario = cam.id_usuario " +
                        "AND cam.id_concurso = ? ");
                ps2.setString(1, incomingIden);
                ps2.setString(2, concurso.id);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) { // (I) real successful, contestant is registered for contest,
                    if (concursantesList.containsKey(incomingIden)) {//check if contestant is already connected
                        concursantesList.get(incomingIden).sendUTF("disc");
                    }
//                    HashMap<String, byte[]> probOutputs = new HashMap<String, byte[]>();
//                    for (String key : problemsOutputs.keySet()) {
//                        probOutputs.put(key, problemsOutputs.get(key));
//                    }
                    concursantesList.put(incomingIden, new HiloCliente(this,
                            incomingIden, client, problemsOutputs, dos, dis));
                } else {
                    dos.writeUTF("c"); // Concurstante no inscrito en el Concurso
                    dos.close();
                }
                rs2.close();
                ps2.close();
            } else {
                //wrong Username or Password
                dos.writeUTF("x");
                dos.close();
                logMessage("Password y/o Username Incorrecto  " + incomingIden);
            }
            rs1.close();
            ps.close();
            dos.flush();
        } catch (Exception ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logMessage(String message) {
        System.err.println(message);
        panLogins.txtConsole.append(message + "\n");
    }

    public void reloadCompetitorsList() {
        for (CompetitorState competitorState : mapSubmitState.values()) {
            competitorState.setLogged(false);
        }
        for (String x : concursantesList.keySet()) {
            mapSubmitState.get(x).setLogged(true);
        }
        panLogins.putData(mapSubmitState);
//        panLogins.putData(concursantesList);
    }

    public void setTimes(long totalTime, long leftTime) {
        concurso.totalTime = totalTime;
        concurso.leftTime = leftTime;
        concurso.updateTimes();
    //panTiempoReset.labTiempo.setText(longToTime(duracionConcurso));
    }

    private void loadCorrectProblemsOutputArray() {
        try {
            problemsOutputs = new HashMap<String, byte[]>();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT Id_Problema, output_file FROM Problema WHERE " +
                    "Id_Concurso = ?");
            ps.setString(1, concurso.id);
//            propiedadesConcurso.getProperty("id")
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String idProblema = rs.getString(1);
                byte[] data = rs.getBytes(2);
                problemsOutputs.put(idProblema, data);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //@TODO

    public synchronized void registrarIntento(int idCmp, String idProblema, boolean result, byte[] sourcecode) {
        try {
            PreparedStatement pstat = con.prepareCall(
                    "CALL SP__UD_CAMPAIGNDETALLE(?,?,?,?,?)");
            pstat.setInt(1, idCmp);
            pstat.setString(2, idProblema);
            pstat.setString(3, RCUtil.longToTime(concurso.totalTime - concurso.leftTime));
            pstat.setBoolean(4, result);
//            if (sourcecode != null) {
//                String st = new String(sourcecode);
////                System.out.println("SOURCE CODE \n:" + st);
//            }
            pstat.setBytes(5, sourcecode);
            pstat.executeUpdate();
            pstat.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    synchronized public LinkedList<Object[]> getProblemsProperties(int idCampaign) {
        LinkedList<Object[]> problems = new LinkedList<Object[]>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT p.Id_Problema, p.nombre, p.valor, cd.solved, cd.intentos_Fallidos " +
                    "FROM CampaignDetalle cd, Problema p " +
                    "WHERE cd.Id_problema = p.Id_Problema AND " +
                    "cd.Id_Campaign = ? " +
                    "ORDER BY p.Id_Problema");
            ps.setInt(1, idCampaign);
            ResultSet rs = ps.executeQuery();

            for (int i = 0; rs.next(); i++) {
                problems.add(new Object[]{rs.getString(1),
                            ConcursoPanel.PROBLEMCONSTANTS[i] + " - " + rs.getString(2),
                            rs.getInt(3),
                            rs.getBoolean(4),
                            rs.getInt(5)
                        });
            }

            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        assert problems.size() > 0; //Si falla aca Faltan Copiar CampaignDetalle
        return problems;
    }

    synchronized public void insertarClarificacion(String user, String categoria, String pregunta) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Clarificacion(Id_concurso, username, category, asked, question) " +
                    "VALUES (?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(propiedadesConcurso.getProperty("id")));
            ps.setString(2, user);
            ps.setString(3, categoria);
            ps.setString(4, RCUtil.longToTime(concurso.totalTime - concurso.leftTime));
            ps.setString(5, pregunta);
            ps.executeUpdate();
            ps.close();
            sendClarData();
        } catch (SQLException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    synchronized public void updateClarificacion(int idClarificacion, String respuesta) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Clarificacion " +
                    "SET answered = ?, " +
                    "answer = ? " +
                    "WHERE Id_clarificacion = " + idClarificacion);

            ps.setString(1, RCUtil.longToTime(concurso.totalTime - concurso.leftTime));
            ps.setString(2, respuesta);
            ps.executeUpdate();
            ps.close();
            sendClarData();
        } catch (SQLException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarClarificacion(int id) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Clarificacion " +
                    "WHERE id_clarificacion = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            sendClarData();
        } catch (SQLException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    synchronized public void sendClarData() {
        panClarificaciones.tableClarificaciones.loadDataArray();
        for (HiloCliente object : concursantesList.values()) {
            object.sendUTF("clar data");
            object.sendUnsharedObject(panClarificaciones.tableClarificaciones.getDataArray());
        }
    }

    synchronized public void startSubmitCount(String user, String idProblema) {
        Timer myTimer = new Timer();
        myTimer.schedule(new CronometroSubmit(user, myTimer, idProblema), 0, 1000);
        mapSubmitState.get(user).setSubmitting(true);
        mapSubmitState.get(user).setSubmittingTime(ConcursoPanel.SUBMIT_TIME);
        mapSubmitState.get(user).setIdProblemSubmitting(idProblema);
        mapSubmitState.get(user).setTimer(myTimer);
    }

    protected void stopSubmisionCount(String user) {
        mapSubmitState.get(user).getTimer().cancel();
        mapSubmitState.get(user).getTimer().purge();
        mapSubmitState.get(user).setSubmitting(false);
        mapSubmitState.get(user).setSubmittingTime(ConcursoPanel.SUBMIT_TIME);
    }

    // testing if this need to be synchronized
    synchronized public int getCampaignMetaData(String iden) {
        int idCamp = -1;
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT cm.Id_Campaign " +
                    "FROM Campaign cm, usuario co " +
                    "WHERE cm.id_usuario = co.id_usuario AND " +
                    "LOWER(co." + IDENTIFICADOR + ") = ? " +
                    "AND cm.Id_Concurso = ?");
            ps.setString(1, iden);
            ps.setString(2, concurso.id);
            ResultSet rsGetIdCampaign = ps.executeQuery();
            rsGetIdCampaign.next();
            idCamp = rsGetIdCampaign.getInt(1);
            rsGetIdCampaign.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        assert idCamp != -1;
        return idCamp;
    }

    public String getProblemName(String id) {
        String name = "";
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT nombre, abrev FROM Problema WHERE id_Problema = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            name = rs.getString(1);
            name = rs.getString(2);
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        assert !name.equals("");    // Problems name plus abrev can't be empty!!
        return name;
    }

    /**
     * 
     */
    enum Estado {

        SUBMITING, NOSUB;
    }
}
