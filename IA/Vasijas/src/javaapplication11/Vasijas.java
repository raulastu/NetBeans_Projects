package javaapplication11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Vasijas {

    LinkedList<LinkedList<Par>> cola;
    LinkedList<Par> visitados = new LinkedList<Par>();
    ArrayList<String[]> results = new ArrayList<String[]>();
    //
    int x_max = 4;
    int y_max = 3;
    // -1 significa no definido
    int x_target = 2;
    int y_target = -1;
    //
    boolean solved = false;
    int n = 1;

    void resolver() {
        cola = new LinkedList<LinkedList<Par>>();
        LinkedList<Par> x = new LinkedList<Par>();
        x.add(new Par(0, 0));
        cola.add(x);
        visitados = new LinkedList<Par>();
        results = new ArrayList<String[]>();
        n = 1;
        generarNivel();
    }

    void generarNivel() {
        if (cola.isEmpty()) {
            System.out.println("No existe Solucion");
            results = new ArrayList<String[]>();
            solved = false;
            return;
        } else {
            // guardar datos
            String[] row = new String[6];
            results.add(row);
            row[0] = "" + n++;
            row[1] = cola + "";
            row[2] = cola.getFirst() + "";
            row[3] = cola.getFirst().getLast() + "";
            row[4] = suc(cola.getFirst().getLast()) + "";
            //
            LinkedList<Par> lista = cola.getFirst();
            Par p = lista.getLast();
            if ((x_target == -1 ? true : p.x == x_target) &&
                    (y_target == -1 ? true : p.y == y_target)) {
                solved = true;
                return;
            } else {
                cola.removeFirst();
                visitados.addLast(p);
                for (Par nodo : quitarVisitados(suc(p))) {
                    LinkedList<Par> wLista = copyOf(lista);
                    wLista.addLast(nodo);
                    cola.addLast(wLista);
                }
            }
            row[5] = visitados + "";
            generarNivel();
        }
    }

    LinkedList<Par> copyOf(LinkedList<Par> p) {
        LinkedList<Par> res = new LinkedList<Par>();
        for (Par x : p) {
            res.add(x);
        }
        return res;
    }

    LinkedList<Par> quitarVisitados(LinkedList<Par> p) {
        for (Iterator<Par> it = p.iterator(); it.hasNext();) {
            if (visitados.contains(it.next())) {
                it.remove();
            }
        }
        return p;
    }

    public LinkedList<Par> suc(Par p) {
        LinkedList<Par> res = new LinkedList<Par>();
        if (p.x < x_max) {
            res.add(new Par(x_max, p.y));
        }
        if (p.y < y_max) {
            res.add(new Par(p.x, y_max));
        }
        if (p.x > 0) {
            res.add(new Par(0, p.y));
        }
        if (p.y > 0) {
            res.add(new Par(p.x, 0));
        }
        //pasar de x a y
        int m = (y_max - p.y) <= p.x ? (y_max - p.y) : p.x;
//        System.out.println(m);
        if (m > 0 && m <= p.x && m <= x_max) {
            res.add(new Par(p.x - m, p.y + m));
        }
        //pasar de y a x
        int n = (x_max - p.x) <= p.y ? (x_max - p.x) : p.y;
        if (n > 0 && n <= p.y && n <= y_max) {
            res.add(new Par(p.x + n, p.y - n));
        }
        return res;
    }
//    public static void main(String[] args) {
//        new Vasijas();
//    }
}
