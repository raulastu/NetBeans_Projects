
import javax.swing.JOptionPane;

public class NewClass {

    public static void main(String[] args) {

        int x;
        String mg = JOptionPane.showInputDialog("Ingresa un numero");
        x = Integer.parseInt(mg);
        int y;
        y=Integer.parseInt(JOptionPane.showInputDialog("Ingrese otro numero"));

        while (x <= y) {
            System.out.print(" "+x);
            x++;
        }
    }
}
