import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int i;
        while (sc.hasNext()) {
            i = sc.nextInt();
            System.out.print((char)i);
        }
        System.err.println("xx");
    }
}