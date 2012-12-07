
public class ClasePrincipal {

    public static void main(String[] args) {
        Persona obj1 = new Persona();       // CREAMOS EL OBJETO
        obj1.verDatos();
        obj1.dni = "34534534";
        obj1.subirPeso(10);
        obj1.verDatos();

        Persona ob2 = new Persona();
        ob2.verDatos();
    }
}
