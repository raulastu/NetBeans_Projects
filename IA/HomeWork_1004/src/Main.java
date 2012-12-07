
import javax.swing.JOptionPane;

public class Main {

    public Main() {
    }

    public static void main(String[] args) {
        int edad;
        String name = JOptionPane.showInputDialog("Please enter your name");
        String edad_s = JOptionPane.showInputDialog("Please enter your age:");
        edad = Integer.parseInt(edad_s);
        String msn = (edad > 100) ? "old" : "young";
        JOptionPane.showMessageDialog(null, "Hello " + name + " you are " + msn);
    }
}