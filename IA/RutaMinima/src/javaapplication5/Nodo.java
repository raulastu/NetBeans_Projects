package javaapplication5;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Nodo {

    SpinnerNumberModel spinModel = new SpinnerNumberModel(5, 0, 9, 1);
    private int x, y;
    private String nombre;
    HashMap<Nodo, JSpinner> map = new HashMap();
    private JLabel face;

    public HashMap<Nodo, JSpinner> getMap() {
        return map;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Nodo(int x, int y, String nombre) {
        this.x = x;
        this.y = y;
        this.nombre = nombre;
        this.face = new JLabel(nombre);
        face.setOpaque(true);
        face.setBackground(Color.white);
    }

    public JLabel getFace() {
        return face;
    }

    public String getNombre() {
        return nombre;
    }

    public void addRuta(Nodo dest) {
        map.put(dest, new JSpinner(spinModel));
    }
}
