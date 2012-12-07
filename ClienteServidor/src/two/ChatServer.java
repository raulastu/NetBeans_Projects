

package two;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class ChatServer {
    public static void main(String[]args)throws Exception{
        MulticastSocket  server = new MulticastSocket(1234);
        InetAddress group = InetAddress.getByName("234.5.6.7");        
        server.setNetworkInterface(NetworkInterface.getByName("234.5.6.7"));
        server.joinGroup(group);
        boolean infinite = true;
        while(infinite){
            byte buf[]=new byte[1024];
            DatagramPacket data = new DatagramPacket(buf, buf.length);
            server.receive(data);
            String msg = new String(data.getData()).trim();
            System.out.println(msg);
        }
        server.close();
    }
}