package funciones;

import java.util.LinkedList;

public class Funcion {

    private LinkedList<String> elementos = new LinkedList<String>();
    private LinkedList<Double> values = new LinkedList<Double>();
    private double resultado;
    private String descripcion;

    public Funcion() {
        resultado = 0.0;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LinkedList<String> getElementos() {
        return elementos;
    }

    public LinkedList<Double> getValues() {
        return values;
    }

    public double getResultado() {
        
        return resultado;
    }

    public void setElementos(LinkedList<String> elementos) {
        this.elementos = elementos;
    }

    public void addElement(String element) {
        elementos.add(element);
    }

    public void addValue(double element) {
        values.add(element);
    }

    public void setValues(LinkedList<Double> parseValues) {
        values = parseValues;
    }
}
