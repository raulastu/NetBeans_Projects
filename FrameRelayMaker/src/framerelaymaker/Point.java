package framerelaymaker;

public class Point {

    String name;
    String ipadd;
    String serialPort;

    public Point(String name, String ipadd) {
        this.name = name;
        this.ipadd = ipadd;
        serialPort = "2/0";
    }

    public Point(String name, String ipadd, String serialPort) {
        this.name = name;
        this.ipadd = ipadd;
        this.serialPort = serialPort;
    }
}
