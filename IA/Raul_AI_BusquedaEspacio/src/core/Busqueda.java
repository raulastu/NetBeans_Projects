package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Busqueda {

    public LinkedList<LinkedList<Estado>> cola;
    public ArrayList<Estado> visitados;
    public LinkedList<Estado> lista;
    public Estado p;
    //    
    public Estado estadoFinal;
    public Estado estadoInicial;
    //
    public ArrayList<String[]> results = new ArrayList<String[]>();
    int _n = 1;
    public boolean solved = false;
    public boolean savingData = false;

    public void resolver() {
        LinkedList<Estado> newList = new LinkedList<Estado>();
        newList.add(estadoInicial);
        cola = new LinkedList<LinkedList<Estado>>();
        cola.add(newList);
        visitados = new ArrayList<Estado>();

        results = new ArrayList<String[]>();
        _n = 1;

        generarNivel();
    }

    public void generarNivel() {
        if (cola.isEmpty()) {
            System.out.println("No existe Solucion");
            results = new ArrayList<String[]>();
            solved = false;
            return;
        } else {
            lista = cola.getFirst();
            p = lista.getLast();
            // BEGIN (I) No forma parte del algoritmo, solo guarda datos para rastrear
            String[] row = savingData?new String[6]:null;
            if (savingData) {
                results.add(row);
                row[0] = "" + _n++;
                row[1] = cola + "";
                row[2] = cola.getFirst() + "";
                row[3] = cola.getFirst().getLast() + "";
                row[4] = suc(cola.getFirst().getLast()) + "";
            }
            // END (I)
            if (p.equals(estadoFinal)) {
                solved = true;
                return;
            } else {
                cola.removeFirst();
                visitados.add(p);
                for (Estado nodo : quitarVisitados(suc(p))) {
                    LinkedList<Estado> wLista = copiaDe(lista);
                    wLista.addLast(nodo);
                    cola.addLast(wLista);   // amplitud = addLast, profundidad = addFirst
                }
            }
            if (savingData) {               //
                row[5] = visitados + "";    //(I)
            }                               //
            generarNivel();
        }
    }

    LinkedList<Estado> copiaDe(LinkedList<Estado> p) {
        LinkedList<Estado> res = new LinkedList<Estado>();
        for (Estado x : p) {
            try {
                res.add((Estado) x.clone());
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }

    public LinkedList<Estado> quitarVisitados(LinkedList<Estado> p) {
        for (Iterator<Estado> it = p.iterator(); it.hasNext();) {
            Estado e = it.next();
            if (visitados.contains(e)) {
                it.remove();
            }
            for (LinkedList<Estado> linkedList : cola) {
                if (linkedList.getLast().equals(e)) {
                    it.remove();
                    break;
                }
            }
        }
        return p;
    }

    /**
     * Metodo que necesita ser SobreEscrito para cada Problema especifico de IA donde se implementar
     * @param p
     * @return
     */
    public LinkedList<Estado> suc(Estado p) {
        return null;
    }

    /*************** Métodos No Mandatorios ****************/
    public void setEstadoInicial(Estado x) {
        estadoInicial = x;
    }

    public void setEstadoFinal(Estado x) {
        estadoFinal = x;
    }

    public void showData() {

        for (String[] strings : results) {
            for (String string : strings) {
                System.out.print(string + "\t\t");
            }
            System.out.println("");
        }
    }
}
