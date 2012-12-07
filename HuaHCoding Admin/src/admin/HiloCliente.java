package admin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloCliente implements Runnable {

    private Thread t;
    private Socket socket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private String user;
    public int idCampaign;
    private HashMap<String, byte[]> notClonedCorrectOutputs;
    public LinkedList<Object[]> myProblems;
    Properties myProperties;
    //
    ServidorConcurso servidor;

    public HiloCliente(ServidorConcurso servidor,
            String id, Socket cliente, HashMap<String, byte[]> correctOutputs,
            ObjectOutputStream outStream, ObjectInputStream inStream) {
        this.servidor = servidor;
        this.myProperties = new Properties();
        this.outStream = outStream;
        this.inStream = inStream;
        this.user = id;
        this.t = new Thread(this);
        this.socket = cliente;
        this.notClonedCorrectOutputs = correctOutputs;
        this.idCampaign = servidor.getCampaignMetaData(user);
        try {
            CallableStatement rsRated = servidor.con.prepareCall("CALL SP__UD_rateCampaign(?);");
            rsRated.setInt(1, idCampaign);
            System.out.println("Rated now ");
            rsRated.execute();
            rsRated.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        this.myProblems = servidor.getProblemsProperties(idCampaign);
        t.start();
    }

    protected void updateMyProperties() {
        try {
            PreparedStatement ps = servidor.con.prepareStatement(
                    "SELECT puntos, penalizacion " +
                    "FROM Campaign WHERE Id_Campaign = ?");
            ps.setInt(1, idCampaign);
            ResultSet rsGetIdCampaign = ps.executeQuery();

            rsGetIdCampaign.next();

            myProperties.setProperty("puntos", rsGetIdCampaign.getInt(1) + "");
            myProperties.setProperty("penalizacion", rsGetIdCampaign.getString(2) + "");
            myProperties.setProperty("submiting", servidor.mapSubmitState.get(user).isSubmitting() + "");
            myProperties.setProperty("submitTime", servidor.mapSubmitState.get(user).getSubmittingTime() + "");
            myProperties.setProperty("idProblemSubmiting", servidor.mapSubmitState.get(user).getIdProblemSubmitting() + "");
            rsGetIdCampaign.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        sendUTF("s");           //succeful
        servidor.logMessage("Usuario conectado exitosamente : " + user);
        sendUnsharedObject(myProblems);             // Enviar problemas
        updateMyProperties();
        sendUnsharedObject(myProperties);       //enviar propiedades
        servidor.propiedadesConcurso.setProperty("tiempoRestante", "" + servidor.concurso.leftTime);
        sendUnsharedObject(servidor.propiedadesConcurso);    // Enviar propiedades del concurso y de la Campaña del usuario                       
        sendUnsharedObject(servidor.panClarificaciones.tableClarificaciones.getDataArray());
        servidor.reloadCompetitorsList();
        try {
            while (true) {
                String command = inStream.readUTF();
                servidor.logMessage(user + " request: " + command);
                if (command.equals("logout")) {
                    sendUTF("logout");
                    break;
                }
                processCommand(command);
            }
        } catch (SocketException ex) {
            servidor.logMessage("Conección Cerrada: " + user);
        } catch (Exception ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
            servidor.concursantesList.remove(user);
            servidor.reloadCompetitorsList();
        }
        servidor.logMessage("Logout: " + user);
        servidor.concursantesList.remove(user);
        servidor.reloadCompetitorsList();
    }

    synchronized void sendInputFile(String idProblema) {
        try {
            PreparedStatement ps = servidor.con.prepareStatement(
                    "SELECT input_file FROM Problema WHERE Id_Problema = ?");
            ps.setString(1, idProblema);
            ResultSet rs = ps.executeQuery();
            rs.next();
            byte source[] = rs.getBytes(1);
            rs.close();
            ps.close();
            outStream.writeUTF("inResp");
            outStream.flush();
            outStream.writeInt(source.length);
            outStream.flush();
            outStream.write(source, 0, source.length);
            outStream.flush();
            boolean isSubmiting = servidor.mapSubmitState.get(user).isSubmitting();
            if (!isSubmiting) {
                servidor.startSubmitCount(user, idProblema);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void processCommand(String request) {
        if (request.split(" ")[0].equals("in")) {
            String idProblema = request.split(" ")[1];
            sendInputFile(idProblema);
        } else if (request.split(" ")[0].equals("submit")) {
            String idProblema = request.split(" ")[1];
            procesarSubmision(idProblema);
        } else if (request.split(",")[0].equals("clar")) {
            String categoria = request.split(",")[1];
            String pregunta = request.split(",", 3)[2];
            servidor.insertarClarificacion(user, categoria, pregunta);
            sendUTF("clar ok");
        }
    }

    synchronized private void procesarSubmision(String idProblema) {
        try {
            int length = inStream.readInt();
            byte[] concursanteOutput = new byte[length];
            inStream.readFully(concursanteOutput);

            int lengthsc = inStream.readInt();
            byte[] sourcecode = new byte[lengthsc];
            inStream.readFully(sourcecode);

            byte[] correctOutput = notClonedCorrectOutputs.get(idProblema);
            System.out.println("idProblema = " + idProblema);
            System.err.println(notClonedCorrectOutputs);
            assert (correctOutput != null);
            StringBuffer comment = new StringBuffer();
            boolean answer = CompareTwoFiles.sonIguales(
                    correctOutput, concursanteOutput, comment);
            servidor.registrarIntento(idCampaign, idProblema, answer, sourcecode);
            if (answer) {
                sendUTF("submResp o");
            } else {
                sendUTF("submResp x " + comment);
            }
            servidor.stopSubmisionCount(user);

            myProblems = servidor.getProblemsProperties(idCampaign);
            updateMyProperties();
            sendUnsharedObject(myProblems);
            sendUnsharedObject(myProperties);
            String veredicto = answer ? "Correct" : "Incorrect";
            servidor.logMessage(user + " respuesta al intento del problema " +
                    servidor.getProblemName(idProblema) + ": " + veredicto + " - Comment: " + comment);
        } catch (IOException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    synchronized void sendUTF(String command) {
        try {
            outStream.writeUTF(command);
            outStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    synchronized void sendUnsharedObject(Object ob) {
        try {
            outStream.writeUnshared(ob);
            outStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServidorConcurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // get Methods

    public String getUser() {
        return user;
    }

    public Properties getProperties() {
        return myProperties;
    }
}
