
import java.io.*;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    Socket client;
    SocketChannel canal;
    HiloLectorMsg hilo;

    public Client() throws Exception {
        client = new Socket("127.0.0.1", 8000);
        DataOutputStream dis = new DataOutputStream(client.getOutputStream());
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        DataInputStream input = new DataInputStream(client.getInputStream());
        hilo = new HiloLectorMsg(System.out, input);
        System.out.println("user:");
        String nickname = console.readLine();
        dis.writeUTF(nickname);
        System.out.println("conectado como " + nickname);
        String msg = "";
        while (true) {
            System.out.printf("%s", ">");
            String inputString = console.readLine();
            if (msg.equals("exit")) {
                break;
            }
            dis.writeUTF(inputString);
            dis.flush();
        }
    }

    class HiloLectorMsg implements Runnable {

        PrintStream out;
        Thread hilo;
        DataInputStream in;

        public HiloLectorMsg(PrintStream out, DataInputStream in) {
            this.out = out;
            this.in = in;
            hilo = new Thread(this);
            hilo.start();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String msg = in.readUTF();
                    out.println(msg);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Client();
    }
}
