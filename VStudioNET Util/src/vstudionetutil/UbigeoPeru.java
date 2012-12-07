package vstudionetutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;

public class UbigeoPeru {

    public UbigeoPeru() throws Exception {
        Connection c = MasterConn.getConnection();
//        Main.executeScript(c, "USE ubigeoPERU");
        BufferedReader fr = new BufferedReader(new FileReader(
                "E:/Java/DB JDBC/Data util/ubigeoPeru.txt"));
        PrintWriter pw = new PrintWriter(new File("E:/Java/DB JDBC/Data util/ubigeoPeruInserts.sql"));
        int provinces = 0, distrits = 0;
        fr.readLine();
        while (fr.ready()) {
            String[] line = fr.readLine().split("\\t");
            System.out.println(line[0] + ", " + line[1] + ", " + line[2]);
            String insert = "";
            if (line[2].equals("0")) {
                if (line[1].equals("0")) {
                    insert = "INSERT INTO departamento(nombre) values('" + line[3] + "')";
                } else {
                    provinces++;
                    insert = "INSERT INTO provincia(nombre, " +
                            "id_departamento) values('" + line[3] + "','" + line[0] + "')";
                }
            } else {
                insert = "INSERT INTO distrito(nombre, " +
                        "id_provincia) values('" + line[3] + "','" + provinces + "')";
            }
            pw.write(insert + "\n");
            distrits++;
        }
        pw.close();
        System.out.println(distrits);
    }

    public static void main(String[] args) throws Exception {
        new UbigeoPeru();
    }
}
