package cliente;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Cliente extends JFrame {

    String IP = "localhost";
    int PORT = 81;
    Socket socket;
    FrameLogin frameLogin;
    ClienteIGU clienteIGU;
    JFrame frameCliente;
    private ObjectOutputStream dos;
    private ObjectInputStream dis;
    public static String clienteName = "HuaHCoding Cliente";
    public static String adminName = "HuaHCoding Servidor";
    protected LinkedList<Object[]> problems;
    public String fileToSavePath;
    //
    protected long tiempoParaPresentarRestante;
    protected long tiempoParaPresentar;
    private Timer timerConteoSubmit;
    //
    private Cliente.Escucha escucha;
    private Properties propiedadesCliente;
    private Properties propiedadesConcurso;
    //
    protected long tiempoConcursoRestante;
    private Timer timerConcurso;
    private boolean debug = true;
    private int idProblemSubmiting;
    //
    //Status titles
    String noStartedTitle = "Concurso No Iniciado Aún";
    //Status Colors
    Color noStartedColor = new Color(161, 158, 93);
    Color inProgressColor = Color.decode("#0099ff");

    public Cliente() {
        //UIManager.setLookAndFeel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            frameLogin = new FrameLogin(clienteName);
            frameLogin.buttEntrar.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    String user = frameLogin.getTxtUser();
                    String pass = new String(frameLogin.getPass());
                    login(user, pass);
                }
            });

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startUI() {
        RCContainer.showFrame(frameLogin);
    }

    public Properties getPropertiesCliente() {
        return propiedadesCliente;
    }

    public Properties getPropiedadesConcurso() {
        return propiedadesConcurso;
    }

    public void Logout() {
        if (timerConteoSubmit != null) {
            timerConteoSubmit.cancel();
            timerConteoSubmit.purge();
        }
        if (timerConcurso != null) {
            timerConcurso.cancel();
        }

        sendToServer("logout");
        frameCliente.setVisible(false);
        frameCliente = null;
        clienteIGU = null;
        System.gc();
        new Cliente().startUI();
        System.gc();
    }

    public void login(String user, String pass) {
        try {

            //String IP = frameLogin.txtIP.getText();
            //int port = Integer.parseInt(frameLogin.txtPort.getText());
            socket = new Socket(IP, PORT);
            dos = new ObjectOutputStream(socket.getOutputStream());
            dis = new ObjectInputStream(socket.getInputStream());
            sendToServer(user);
            sendToServer(pass);
            String v = dis.readUTF();
            procesarRespuestaDeLogin(v, user);         //Leer respuesta            
        } catch (ConnectException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            showMessage("No hay Concurso disponible");
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void cambiarPassword(String current, String newPass) {
        sendToServer("pass " + current);
        sendToServer(newPass);
    }

    void setIdProblemSubmiting(int id) {
        this.idProblemSubmiting = id;
    }

    int getIdProblemSubmiting() {
        return idProblemSubmiting;
    }

    private void cargarDatosDeUsuario() {
        clienteIGU.labPts.setText(propiedadesCliente.getProperty("puntos"));
        clienteIGU.labPenalizacion.setText(propiedadesCliente.getProperty("penalizacion"));
    }

    private void cargarProblemasYUserData() {
        //publicando en el cmb los nombres de los problemas  
        cargarDatosDeUsuario();

        clienteIGU.cmbProblems.removeAllItems();
        for (int i = 0; i < problems.size(); i++) {
            clienteIGU.cmbProblems.addItem(problems.get(i)[1] + " [" + problems.get(i)[2] + "pts]");
        }

        clienteIGU.cmbProblems.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JComboBox source = (JComboBox) e.getSource();
                if (source.getSelectedIndex() != -1) {
                    if ((Boolean) (problems.get(source.getSelectedIndex())[3]) == false) {
                        clienteIGU.nombreDeArchivo = (problems.get(source.getSelectedIndex())[1] + ".in").replaceAll(" ", "");
                        clienteIGU.butGuardarInput.setText("Descargar " + clienteIGU.nombreDeArchivo);
                        clienteIGU.butGuardarInput.setEnabled(true);
                        clienteIGU.butGuardarInput.setForeground(Color.black);
                    } else {
                        clienteIGU.butGuardarInput.setText("Resuelto");
                        clienteIGU.butGuardarInput.setEnabled(false);
                        clienteIGU.butGuardarInput.setForeground(Color.green);
                    }
                    clienteIGU.labIntentosFallidos.setVisible(true);
                    int intFallidos = (Integer) (problems.get(source.getSelectedIndex())[4]);
                    if (intFallidos == 1) {
                        clienteIGU.labIntentosFallidos.setText(intFallidos + " Intento Fallido");
                    } else {
                        clienteIGU.labIntentosFallidos.setText(intFallidos + " Intentos Fallidos");
                    }
                }
            }
        });
        clienteIGU.cmbProblems.setSelectedIndex(-1);

    }

    private void processMainProperties(Properties propConcurso, Properties proConcursante) {
        idProblemSubmiting = Integer.parseInt(propiedadesCliente.getProperty("idProblemSubmiting"));
        tiempoParaPresentar = Long.parseLong(propiedadesCliente.getProperty("submitTime"));
        tiempoParaPresentarRestante = tiempoParaPresentar;
        if (propiedadesCliente.getProperty("submiting").equals("true")) {
            comenzarConteoPresentar();
        }
        clienteIGU.labContestName.setText(propConcurso.getProperty("nombre"));
        long tiempoRestanteConcurso = Long.parseLong(propConcurso.getProperty("tiempoRestante"));
        procesarEstado(propConcurso.getProperty("estado"), tiempoRestanteConcurso);

        if (propConcurso.getProperty("estado").equals("started") &&
                propiedadesCliente.getProperty("submiting").equals("true")) {
            clienteIGU.setSubmiting();
        }
    }

    private void procesarEstado(String pro, long tiempoRestante) {

        tiempoConcursoRestante = tiempoRestante;
        clienteIGU.labTiempo.setText(RCUtil.longToTime(tiempoConcursoRestante));
        if (pro.equals("opened")) {
            clienteIGU.labEstado.setText(noStartedTitle);
            clienteIGU.labEstado.setForeground(noStartedColor);
            clienteIGU.estadoInicialPresentar();
        } else if (pro.equals("started")) { // start and resume

            if (timerConcurso != null) {
                System.out.println("timerConcurso.purge();" + timerConcurso.purge());
            }
            timerConcurso = new Timer();
            timerConcurso.schedule(new CronometroCliente(), 0, 1000);
            System.out.println("PROO" + pro);
            clienteIGU.labEstado.setText("Concurso En Progreso");
            clienteIGU.estadoInicialPresentar();
            clienteIGU.labEstado.setForeground(inProgressColor);
            clienteIGU.habilitarPresentar();
            cargarProblemasYUserData();
            clienteIGU.frameSolicitarClarificacion.loadCategories();
        } else if (pro.equals("paused")) {

            clienteIGU.labEstado.setText("Concurso Pausado");
            clienteIGU.labEstado.setForeground(Color.GRAY);
            for (int i = 0; i < clienteIGU.cmbProblems.getActionListeners().length; i++) {
                clienteIGU.cmbProblems.removeActionListener(clienteIGU.cmbProblems.getActionListeners()[i]);
            }
            timerConcurso.cancel(); //%$%%%%%% put it with no context knowledge
            clienteIGU.estadoInicialPresentar();
        } else if (pro.equals("finalized")) {

            if (timerConcurso != null) {
                timerConcurso.cancel();
            }
            clienteIGU.labEstado.setForeground(Color.RED);
            clienteIGU.estadoInicialPresentar();
            clienteIGU.labEstado.setText("Concurso Finalizado, en las proximas horas los resultados serán publicados en la web");
        }

        clienteIGU.revalidate();
        clienteIGU.setVisible(false);
        clienteIGU.setVisible(true);

    }

    class CronometroCliente extends TimerTask {

        @Override
        public void run() {
            try {
                clienteIGU.labTiempo.setText(RCUtil.longToTime(tiempoConcursoRestante));
                tiempoConcursoRestante -= 1000;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "xx");
            }
        }
    }

    public void sendToServer(String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startSubmit(int id) {
        sendToServer("in " + id);
        setIdProblemSubmiting(id);
        /**
         * comienza el conteo solo la primera vez que se descarga el input
         * Esto permite descargar el input varias veces sin afectar el conteo.
         */
        if (tiempoParaPresentarRestante == tiempoParaPresentar) {
            comenzarConteoPresentar();
        }
    }

    /**
     * 
     * @param InputFilePath
     * @param sourceCodeFilePath
     */
    public void submitProblem(String yourOutputFilePath, String sourceCodeFilePath) {
        FileInputStream fis = null;
        try {
            if (!localCheckFirstLine(yourOutputFilePath)) {
                JOptionPane.showMessageDialog(null,
                        "La primera linea de su salida debe empezar con \"Caso #1: \" (sin comillas) ",
                        "HuaHCoding", JOptionPane.ERROR_MESSAGE);
                return;
            }
            File fileInput = new File(yourOutputFilePath);
            fis = new FileInputStream(fileInput);
            byte[] outputFile = new byte[(int) fileInput.length()];
            fis.read(outputFile);
            fis.close();
            sendToServer("submit " + idProblemSubmiting);
            dos.writeInt((int) fileInput.length());
            dos.flush();
            dos.write(outputFile, 0, (int) fileInput.length());
            dos.flush();
            stopSubmit();
            //send Source Code
            FileInputStream fisSC = null;
            File fileSC = new File(sourceCodeFilePath);
            fisSC = new FileInputStream(fileSC);
            byte[] srcode = new byte[(int) fileSC.length()];
            fisSC.read(srcode);
            fisSC.close();
            String st = new String(srcode);
//            System.out.println("SOURCE CODE FROM CLIENTE " + st);
            dos.writeInt((int) fileSC.length());
            dos.flush();
            dos.write(srcode, 0, (int) fileSC.length());
            dos.flush();
            timerConteoSubmit.cancel();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(fis!=null)fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean localCheckFirstLine(String file) {
        Scanner sc;
        try {
            sc = new Scanner(new File(file));
            String firstLine = sc.nextLine();
            if (firstLine.startsWith("Caso #1: ")) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    public void procesarRespuestaDeLogin(String command, String... args) {
        if (command.equals("s")) {
            success(args[0]);
        } else if (command.equals("x")) {
            showMessage("Password y/o Username Incorrecto");
        }
    }

    //Exito conectando al server    
    public void success(String user) {
        try {
            problems = (LinkedList<Object[]>) dis.readObject();
            propiedadesCliente = (Properties) dis.readObject();            //Leer Propiedades: Puesto, Penalizacion, submiting y submmitTime
            propiedadesConcurso = (Properties) dis.readObject(); //Leer propiedades de Concurso
            String[][] clarificacionData = (String[][]) dis.readObject();//Cargar Aclaraciones
            if (debug) {
                System.out.println("properties " + propiedadesConcurso);
                System.out.println("propiedadesCliente " + propiedadesCliente);
            }
            clienteIGU = new ClienteIGU(this, user, clarificacionData);
            processMainProperties(propiedadesConcurso, propiedadesCliente);
            frameCliente = new JFrame(clienteName);
            frameCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameCliente.getContentPane().add(clienteIGU);
            frameCliente.pack();
            RCContainer.centerFrame(frameCliente);
            frameCliente.setVisible(true);
            //clienteIGU.estadoInicialPresentar();
            frameLogin.setVisible(false);
            escucha = new Escucha(dis);

            //showMessage("Conectado Exitosamente");
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        //showMessage("Conectado Exitosamente");
    }

    protected void comenzarConteoPresentar() {
        if (timerConteoSubmit != null) {
            System.out.println("time.purge() " + timerConteoSubmit.purge());
        }
        timerConteoSubmit = new Timer();
        timerConteoSubmit.schedule(new ConteoPresentarTask(), 0, 1000);
    }

    /**
     * 
     */
    class ConteoPresentarTask extends TimerTask {

        @Override
        public void run() {
            clienteIGU.labTiempoPresentar.setText("" + RCUtil.longToTime(tiempoParaPresentarRestante));
            if (debug) {
                System.out.print(RCUtil.longToTime(tiempoParaPresentarRestante) + " ");
            }
            if (tiempoParaPresentarRestante <= 0) {
                JOptionPane.showMessageDialog(clienteIGU, "Tiempo para presentar execido");
                stopSubmit();
                timerConteoSubmit.cancel();
            } else {
                tiempoParaPresentarRestante -= 1000;
            }
        }
    }

    protected void stopSubmit() {
        clienteIGU.myFileChooser.cancelSelection();
        clienteIGU.estadoInicialPresentar();
        clienteIGU.habilitarPresentar();
        tiempoParaPresentarRestante = tiempoParaPresentar;
    }

    public void showMessage(String msg) {
        System.out.println(msg);
        JOptionPane.showMessageDialog(null, msg, "Mensaje de " + adminName, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Clase Oyente de mensajes del servidor
     */
    private class Escucha
            implements Runnable {

        private Thread t;
        private ObjectInputStream inStream;

        public Escucha(ObjectInputStream in) {
            this.inStream = in;
            this.t = new Thread(this);
            t.start();
        }

        public void run() {
            try {
                while (true) {
                    String cmd = inStream.readUTF();
                    if (cmd.equals("logout")) {
                        break;
                    }
                    System.out.println("incoming command " + cmd);
                    procesarUTF(cmd);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Se perdió la conexión con el servidor.", clienteName, JOptionPane.ERROR_MESSAGE);
                System.exit(0);
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void procesarUTF(String cmd) {
            try {
                if (cmd.equals("propro")) {
                    problems = (LinkedList<Object[]>) dis.readObject();
                    propiedadesCliente = (Properties) dis.readObject();
                    cargarDatosDeUsuario();
                } else if (cmd.equals("clar ok")) {
                    JOptionPane.showMessageDialog(clienteIGU, "Pregunta recibida... se contestará en breve", "Mensaje de " + adminName, JOptionPane.INFORMATION_MESSAGE);
                } else if (cmd.equals("clar data")) {
                    clienteIGU.cargarClarificaciones((String[][]) dis.readObject());
                } else if (cmd.split(" ")[0].equals("estado")) {
                    propiedadesConcurso.setProperty("estado", cmd.split(" ")[1]);
                    procesarEstado(cmd.split(" ")[1], Long.parseLong(cmd.split(" ")[2]));
                } else if (cmd.equals("clock")) {
                    clienteIGU.labTiempo.setText(inStream.readUTF());
                } else if (cmd.equals("inResp")) {
                    procesarComandoInputResponse();
                } else if (cmd.split(" ")[0].equals("submResp")) {
                    String veredicto = cmd.split(" ")[1];
                    procesarComandoSubmitResponse(veredicto, cmd);
                } else if (cmd.equals("disc")) {
                    JOptionPane.showMessageDialog(null, "Se acaba de conectar desde otro lugar/cliente.", clienteName, JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void procesarComandoInputResponse() {
            try {
                int length = dis.readInt();
                byte[] inputFile = new byte[length];
                dis.readFully(inputFile, 0, length);
                FileOutputStream fos = new FileOutputStream(new File(fileToSavePath));
                fos.write(inputFile);
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void procesarComandoSubmitResponse(String veredicto, String cmd) {
            try {
                if (veredicto.equals("o")) {
                    JOptionPane.showMessageDialog(clienteIGU, "Correcto", "Resultado de Submisión", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/img/checkmark.png")));
                } else if (veredicto.equals("x")) {
                    String cause = cmd.split(" ", 3)[2];
                    JOptionPane.showMessageDialog(clienteIGU, "Incorrecto - " + cause, "Resultado de Submisión", JOptionPane.ERROR_MESSAGE, null);
                }
                problems = (LinkedList<Object[]>) dis.readObject();
                propiedadesCliente = (Properties) dis.readObject();
                cargarDatosDeUsuario();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        new Cliente().startUI();
    }
}
