/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RutaMinima {

    Cola cola = new Cola();
    ArrayList<Ruta> visitados = new ArrayList<Ruta>();
    Ruta[] espacio;
    public LinkedList<Ruta> lista;
    public boolean solved = false;
    String fin;
    String start;

    public RutaMinima(Ruta[] espacio, String start, String end) {
        System.out.println("Espacio:");
        for (Ruta ruta : espacio) {
            System.out.print(ruta);
        }
        System.out.println("\nStart = "+start+" End ="+end);

        this.espacio = espacio;
        this.start = start;
        this.fin = end;
        Ruta estadoInicial = new Ruta(".", start, 0);
        LinkedList<Ruta> paCola = new LinkedList<Ruta>();
        paCola.add(estadoInicial);
        cola.add(paCola);
        visitados.add(estadoInicial);
        generalNivel();
    }

    public void generalNivel() {

        if (cola.isEmpty()) {
            solved = false;
            System.out.println("No existe solucion");
            return;
        }
        lista = cola.sacarMejor();
        Ruta p = lista.getLast();

        System.out.println("cola " + cola);
        System.out.println("lista " + lista);
        System.out.println("p = " + p);

        if (p.getB().equals(fin)) {
            solved = true;
            System.out.println(lista);
            int cc = 0;
            for (Ruta nodo : lista) {
                cc += nodo.getD();
            }
            System.out.println("longitud" + cc);
            return;
        }
        //general nivel
        cola.remove(cola.sacarMejor());
        System.out.println("cola" + cola);
        System.out.println("suc" + suc(p));
        for (Ruta nodo : suc(p)) {
            LinkedList<Ruta> w_lista = getClon(lista);
            w_lista.addLast(nodo);
            System.out.println("w_lista = " + w_lista);
            if (!visitados.contains(nodo)) {
                cola.addLast(w_lista);
                Ruta toVis = new Ruta(nodo.getA(), nodo.getB(), longitud(w_lista));
                visitados.add(toVis);
            } else {
                if (longitud(w_lista) <= sacarMejorVisitados(p)) {
                    cola.addLast(w_lista);
                    actualizarVisitados(nodo, longitud(w_lista));
                }
            }
        }
        generalNivel();
    }

    void actualizarVisitados(Ruta p, int newValue) {
        for (Ruta nodo : visitados) {
            if (nodo.getB().equals(p.getB())) {
                nodo.setD(newValue);
            }
        }
    }

    int sacarMejorVisitados(Ruta p) {
        for (Ruta nodo : visitados) {
            if (nodo.getB().equals(p.getB())) {
                return nodo.getD();
            }
        }
        assert (true);
        return -1;
    }

    int longitud(List<Ruta> x) {
        int c = 0;
        for (Ruta nodo : x) {
            c += nodo.getD();
        }
        return c;
    }

    LinkedList<Ruta> suc(Ruta p) {
        LinkedList<Ruta> suc = new LinkedList<Ruta>();
        for (Ruta nodo : espacio) {
            if (p.getB().equals(nodo.getA())) {
                suc.add(new Ruta(nodo.getA(), nodo.getB(), nodo.getD()));
            }
        }
        return suc;
    }

    LinkedList<Ruta> getClon(LinkedList<Ruta> l) {
        LinkedList<Ruta> res = new LinkedList<Ruta>();
        for (Ruta nodo : l) {
            res.add(new Ruta(nodo.getA(), nodo.getB(), nodo.getD()));
        }
        return res;
    }

    public static void main(String[] args) {
        Ruta[] espacio = {
//            new Ruta(".", "a", 0),
            new Ruta("a", "b", 3),
            new Ruta("a", "c", 2),
            new Ruta("b", "d", 4),
            new Ruta("b", "c", 1),
            new Ruta("c", "d", 2),
            new Ruta("c", "e", 2),
            new Ruta("d", "e", 2),
//            new Ruta("d", "f", 4),
//            new Ruta("e", "f", 2)
        };
        new RutaMinima(espacio, "a", "e");
    }
}
