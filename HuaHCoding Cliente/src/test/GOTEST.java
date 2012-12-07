package test;

import cliente.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GOTEST {

    public void testB4() {
//        goB2("JuniorX", "test");
        String l[][] = {
            {"JuniorX", "test"},
            {"Darkinget", "test"},
            {"hamlet", "test"},
            {"LifePlusPoker", "test"},
            {"smthomas", "test"},
            {"wtorresinf", "test"},
            {"extremo", "test"},
            {"Ad...", "test"},
            {"ksdepor", "test"},
            {"Agente007", "test"},
            {"capama14", "test"},
            {"Jostyn", "test"},
            {"Crhistian27", "test"},
            {"myguel", "test"},
            {"jahirzino", "test"},
            {"jonathanl", "test"},
            {"Scisor", "test"},
            {"sloo08", "test"},
            {"DaniloRJ", "test"},
            {"DejotaRicky", "test"}
        };
        for (int i = 0; i < l.length; i++) {
            goB2(l[i][0], l[i][1]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(GOTEST.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void goB2(String id, String pass) {
        Cliente cliente = new Cliente();
        cliente.fileToSavePath = "c:/testing/int.txt";

        cliente.login(id, pass);
        String src = "c:/testing/BasketsWithApples.java";
        cliente.startSubmit(6);
        cliente.submitProblem("c:testing/b2/Palitos.sol", src);
        cliente.startSubmit(7);
        cliente.submitProblem("c:testing/b2/Sec2Time.sol", src);
        cliente.startSubmit(8);
        cliente.submitProblem("c:testing/b2/NichoBits.sol", src);
        cliente.startSubmit(9);
        cliente.submitProblem("c:testing/b2/PuchosDivertidos.sol", src);
        cliente.startSubmit(10);
        cliente.submitProblem("c:testing/b2/Domino.sol", src);
//        cliente.startUI();
    }

    public void goB4(String id, String pass) {
        Cliente cliente = new Cliente();
        cliente.fileToSavePath = "c:/testing/int.txt";

        cliente.login(id, pass);
        String src = "c:/testing/BasketsWithApples.java";
        cliente.startSubmit(16);
        cliente.submitProblem("c:testing/EscapeRectangulo.sol", src);
        cliente.startSubmit(17);
        cliente.submitProblem("c:testing/Numero Espejo.sol", src);
        cliente.startSubmit(18);
        cliente.submitProblem("c:testing/MargenDeGanancia.sol", src);
        cliente.startSubmit(19);
        cliente.submitProblem("c:testing/HuaH-ROT13.sol", src);
        cliente.startSubmit(20);
        cliente.submitProblem("c:testing/rc.sol", src);
//        cliente.startUI();
    }
    void testB6(){
        Cliente cliente = new Cliente();
        cliente.fileToSavePath = "c:/int.txt";

        cliente.login("raulooo", "presenta");
        String src = "C:/Documents and Settings/rCUser.RCOMPUTER/Mis documentos/NetBeansProjects/B6Solutions/src/A.java";
        cliente.startSubmit(27);
        cliente.submitProblem("c:/out.txt", src);
    }

    public static void main(String[] args) {
//        new GOTEST().testB4();
        new GOTEST().testB6();
    }
}
