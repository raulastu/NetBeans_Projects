package funciones;


public class FuncionProducto extends Funcion{

    
    @Override
    public double getResultado() {
        double producto = 1;
        for (double x : getValues()) {
            producto *= x;
        }
        return producto;        
    }
    
        
}
