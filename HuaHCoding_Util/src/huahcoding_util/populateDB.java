package huahcoding_util;

import admin.Concursante;
import admin.ConcursoPanel;
import admin.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.nio.cs.ext.DBCSDecoderMapping;

public class populateDB {

    LinkedList<Object[]> problemList = new LinkedList<Object[]>();
    Connection con;
    HashMap<String, String> ps = new HashMap<String, String>();

    public populateDB() {
        con = new ConexionDB().getConnection();
    }

    void go() {
        for (int i = 1; i <= 4; i++) {
            for (String key : ps.keySet()) {
                System.out.println(key);
                System.out.println(ps.get(key));
                String in = getClass().getResource("/problemSet/" + key).getPath();
                String out = getClass().getResource("/problemSet/" + ps.get(key)).getPath();
                System.out.println(in);
                System.out.println(out);
                ConcursoPanel.addProblemasToTempList(key, in, out, 10, 4);
            }
        }
        
    }

    void insertConcurso() {
        ps.put("NumerosEsperantos.in", "NumerosEsperantos.out");
        ps.put("feynman.in", "feynman.sol");
        ps.put("he.in", "he.sol");
        ps.put("hkitty.in", "hkitty.out");
        ps.put("og.in", "og.sol");
        ps.put("optic.in", "optic.out");
        go();
    }

    void insertUsers() {
        call("CALL sp__registerConcursante ('Raúl', 'Ramírez', 1, 'mail2', 'rC', 'paco')");
        call("CALL sp__registerConcursante ('pacora', 'changanaqui', 1,  'mail1', 'YouMaster','paco');");
        call("CALL sp__registerConcursante ('Wilder', 'Carreño', 1, 'mail3', 'Archon', 'paco');");
        call("CALL sp__registerConcursante ('Jonathan', 'Bastidas Angeles', 1, 'mail4','Strike', 'paco');");
        call("CALL sp__registerConcursante ('Danny', 'Morales Meneses', 1, 'mail5', 'DarkingTela', 'paco');");
        call("CALL sp__registerConcursante ('Moises Armando', 'Quineche Carreño', 1, 'mail6', 'Attakon', 'paco');");
        call("CALL sp__registerConcursante ('Ricky Pool', 'Vargas Vajonero', 2, 'mail7', 'roco', 'paco');");
    }

    void call(String callable) {
        try {
            CallableStatement cs = con.prepareCall(callable);
            cs.execute();
        } catch (SQLException ex) {
            Logger.getLogger(populateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Connection con = new ConexionDB().getConnection();
        Concursante concursante = new Concursante(con);
//        concursante.inscribirEnConcurso(idUser, idConcurso)
        new populateDB().insertUsers();        
//        new Main().call("CALL sp__registerConcursante ('Raúl Felipe', 'Ramírez Alvarado', 1, 'rc@huahcoding.netne.net', 'rChi', 'presenta')");
        ConcursoPanel.ingresarConcurso("Concurso Individual B1",
                "2009-05-29 15:00", "Laboratorio de Ingeniería", "libre", "",
                "*La fecha y hora aún no están confirmadas");
        concursante.inscribirEnConcurso("1", "1");
        concursante.inscribirEnConcurso("2", "1");
        concursante.inscribirEnConcurso("3", "1");
        concursante.inscribirEnConcurso("5", "1");
//        new Main().insertConcurso();
    }
}
