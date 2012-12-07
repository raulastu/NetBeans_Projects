
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import java.io.*;
import java.nio.*;
import java.net.*;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.logging.*;

public class Server {

    ServerSocket server;
    SocketChannel channel;
    HashMap<String, HiloCliente> clientesSet = new HashMap<String, Server.HiloCliente>();

    public Server() throws Exception {
        server = new ServerSocket(8000);
        ThreadGroup tGroup = new ThreadGroup("clientes");
        System.out.println("Server Montado y esperando conexiones");
        for (int i = 0; i < 100; i++) {
            Socket cliente = server.accept();
            DataInputStream input = new DataInputStream(new BufferedInputStream(cliente.getInputStream()));
            String nickname = input.readUTF();
            clientesSet.put(nickname, new HiloCliente(cliente, nickname, System.out));
            System.out.println("Conexion establecida con cliente " + "[" + i + "]" + " nickname :" + nickname);
        }
    }

    class HiloCliente implements Runnable {

        String user;
        Thread hilo;
        Socket cliente;
        DataOutputStream output;
        DataInputStream input;
        PrintStream serverOut;
        HashMap<String, DataOutputStream> outputList = new HashMap<String, DataOutputStream>();

        public HiloCliente(Socket cliente, String id, PrintStream out) throws IOException {
            this.serverOut = out;
            this.user = id;
            this.cliente = cliente;
            output = new DataOutputStream(new BufferedOutputStream(cliente.getOutputStream()));
            input = new DataInputStream(new BufferedInputStream(cliente.getInputStream()));
            this.hilo = new Thread(this);
            hilo.start();
        }

        public void run() {
            try {
                while (true) {
                    String inputString = input.readUTF();
                    if (inputString.charAt(0) == '/') {
                        String elements[] = inputString.split(" ");
                        String command = elements[0];
                        if (command.equals("/c") || command.equals("/connect")) {
                            if (elements.length == 1) {
                                System.out.println("Insuficientes parametros para comando");
                                output.writeUTF("Insuficientes parametros para comando");
                            } else {
                                String id = elements[1];
                                privateChat(id);
                            }
                        }
                    } else {
                        readMessage(inputString);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void privateChat(String userDestino) throws IOException {
            DataOutputStream dos = new DataOutputStream(clientesSet.get(userDestino).cliente.getOutputStream());
            outputList.put(userDestino, dos);
            System.out.println(user + " conectado con " + userDestino);
        }

        private void readMessage(String mensaje) throws IOException {
            String msg = user + " dice: " + mensaje;
            serverOut.println(msg);
            serverOut.flush();
            for (DataOutputStream dos : outputList.values()) {
                dos.writeUTF(msg);
                dos.flush();
            }

        }
    }

    public static void main(String[] args) throws Exception {
        new Server();
    }
}
