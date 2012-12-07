package javaapplication6;

public class MiClase {

    int x;

    public MiClase() {
    }

    public void a(int arra[]) {
        int s = 0;
        for (int i = 0; i < arra.length; i++) {
            s = arra[i] + s;
        }
        System.out.println(s);
    }

    public void misuma(int a, int b) {
        System.out.println(a + b);
    }

    public void hola() {
        System.out.println("Estoy dentro del metdo llamado HOLA");
    }

    public void ab() {
        String a[] = new String[10];
        String b[] = new String[a.length];
        String a2[] = {"1", "2"};
        int s = 0;
        a[0] = 0 + "";
        a[1] = 1 + "";

        for (int j = 0; j < a.length; j++) {
            a[j] = j * j + "";
        }

        System.out.print("b= ");
        for (int k = 0; k < b.length; k++) {
            b[k] = a[k];
            System.out.print(b[k] + ",");

        }
        System.out.println("\n");
        System.out.print("a= ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");

        }
        System.out.println("");
    }
}
