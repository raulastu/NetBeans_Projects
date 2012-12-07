package funciones;

public class FuncionPromedio extends Funcion {

    @Override
    public double getResultado() {
        double suma = 0;
        for (int i = 0; i < getValues().size(); i++) {
            double val = getValues().get(i);
            suma = suma + val;
        }
        return suma / getValues().size();
    }

    @Override
    public String getDescripcion() {
        return "Devuelve el promedio (media aritmetica) de los argumentos";
    }
}
