
import cliente.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

public class GOTEST extends TestCase {

    public void testB4() {
        goB4("JuniorX", "test");
        goB4("Darkinget", "test");
        goB4("wtorresinf", "test");
        goB4("LifePlusPoker", "test");
        goB4("smthomas", "test");
    }

    public void goB4(String id, String pass) {
        Cliente cliente = new Cliente();
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
        cliente.startUI();
    }

//    public void sendProblem(Cliente cliente, int idProblem) {
//        cliente.submitProblem("c:testing/Numero Espejo.sol", "c:/testing/BasketsWithApples.java");
//    }
}
