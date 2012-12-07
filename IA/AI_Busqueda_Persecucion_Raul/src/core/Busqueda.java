package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Busqueda {

    public LinkedList<LinkedList<Estado>> cola;
    public boolean[][][][] visitados;
    public LinkedList<Estado> lista;
    public Estado p;
    //    
    public Estado estadoFinal;
    public Estado estadoInicial;
    //
    public ArrayList<String[]> results = new ArrayList<String[]>();
    int _n = 1;
    public boolean solved = false;
    public boolean debugin = false;

    public void resolver() {
        LinkedList<Estado> newList = new LinkedList<Estado>();
        newList.add(estadoInicial);
        cola = new LinkedList<LinkedList<Estado>>();
        cola.add(newList);
        visitados = new boolean[10][10][10][10];

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
            String[] row = debugin?new String[6]:null;
            if (debugin) {
                results.add(row);
                row[0] = "" + _n++;
                row[1] = cola + "";
                row[2] = cola.getFirst() + "";
                row[3] = cola.getFirst().getLast() + "";
                row[4] = suc(cola.getFirst().getLast()) + "";
            }
            // END (I)
            if (p.xm == estadoFinal.xm && p.ym == estadoFinal.ym) {
                solved = true;
                return;
            } else {
                cola.removeFirst();
//                visitados[p.x][p.y][p.xm][p.ym] = true;
                for (Estado nodo : suc(p)) {
                    visitados[nodo.x][nodo.y][nodo.xm][nodo.ym] = true;
                    LinkedList<Estado> wLista = copiaDe(lista);
                    wLista.addLast(nodo);
                    cola.addLast(wLista);   // amplitud = addLast, profundidad = addFirst
                }
            }
            if (debugin) {               //
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
