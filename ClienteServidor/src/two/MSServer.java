package two;

import java.io.IOException;
import java.net.MulticastSocket;

public class MSServer {

    MulticastSocket mSocket;

    public MSServer() throws IOException {
        mSocket = new MulticastSocket(8000);
    }

    public static void main(String[] args) throws IOException {
        new MSServer();
    }
}
