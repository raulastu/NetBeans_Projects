package funciones;



public class FuncionSuma extends Funcion {
    
    @Override
    public double getResultado() {
        double suma = 0.0;
        for (double x : getValues()) {
            suma += x;
        }
        return suma;
    }

    @Override
    public String getDescripcion() {
        return "Suma todos los numeros en un rago de celdas";
    }
    
}
