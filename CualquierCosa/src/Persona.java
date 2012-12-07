
public class Persona {

    String nombre = "n";
    int peso = 5;
    String dni = "1111";

    //METODOS
    void verDatos() {
        System.err.println("nombre: " + nombre + " peso: " + peso + " dni: " + dni);
    }

    void subirPeso(int x) {
        peso = peso + x;
    }

    public static void main(String[] arg) {
        Persona lester = new Persona();       // CREAMOS EL OBJETO
        lester.verDatos();
        lester.dni = "34534534";
        lester.subirPeso(10);
        lester.verDatos();

        Persona ob2 = new Persona();
        ob2.verDatos();
    }
}
