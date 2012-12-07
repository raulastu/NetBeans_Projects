package javaapplication5;

import java.util.LinkedList;

public class Cola extends LinkedList<LinkedList<Ruta>> {

    int total = 0;

    LinkedList<Ruta> sacarMejor() {
        int min = Integer.MAX_VALUE;
        LinkedList<Ruta> selected = null;
        for (LinkedList<Ruta> lista : this) {
//            System.out.println("xx");
            int y = 0;
            for (Ruta nodo : lista) {
                y += nodo.getD();
            }
            if (y <= min) {
                selected = lista;
                min = y;
            }
        }
        assert (selected != null);
        System.out.println(selected);
        return selected;
    }
}
