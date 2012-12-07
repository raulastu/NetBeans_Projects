
import javax.swing.JOptionPane;

public class NewClassfor {

    public static void main(String[] args) {
        int x = Integer.parseInt(JOptionPane.showInputDialog("Ingresa un numero"));
        int y = Integer.parseInt(JOptionPane.showInputDialog("Ingrese otro numero"));
        int s = 0;
        for (int i = x; true;i++) {
            System.out.print(" " + i);
            s = s + i;
            if(i==y)
                break;
        }
        System.out.println("\nMultiplicacion 1ro*ultimo");
        System.out.println("x = " + x);
        System.out.println(y);
        System.out.println(x * y);
        System.out.println(s);
        System.out.println((y * (y + 1) / 2) - (x * (x - 1) / 2));
    }
}
