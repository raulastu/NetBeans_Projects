package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Busqueda {

    public ArrayList<ArrayList<Estado>> cola;
    public boolean[][][][] visitados;
    public ArrayList<Estado> lista;
    public Estado p;
    //    
    public Estado estadoFinal = new Estado(2, -1);
    public Estado estadoInicial;
    //
    public ArrayList<String[]> results = new ArrayList<String[]>();
    int _n = 1;
    public boolean solved = false;
    public boolean savingData = false;

    public void resolver() {
        ArrayList<Estado> newList = new ArrayList<Estado>();
        newList.add(estadoInicial);
        cola = new ArrayList<ArrayList<Estado>>();
        cola.add(newList);
        visitados = new boolean[20][20][20][20];
        results = new ArrayList<String[]>();
        _n = 1;
        generarNivel();
    }

    public void generarNivel() {
        if (cola.isEmpty()) {
            System.out.println("No existe Solucion");
            solved = false;
            return;
        }
        lista = cola.get(0);         // amplitud = getFirst, profundidad = getLast
        p = lista.get(lista.size()-1);
//             System.out.println("cola = " + cola);
//             System.out.println("lista = " + lista);
        if (p.equals(estadoFinal)) {
            solved = true;
            return;
        } else {
            cola.remove(0);    // amplitud = getFirst, profundidad = getLast
            visitados[p.estadoRobot.x][p.estadoRobot.y][p.estadoMovil.x][p.estadoMovil.y] = true;
//                System.out.println("visitados = " + visitados);
            for (Estado nodo : quitarVisitados(suc(p))) {
                ArrayList<Estado> wLista = copyOf(lista);
                wLista.add(nodo);      // amplitud o i
                cola.add(wLista);
            }
        }
        generarNivel();
    }

    ArrayList<Estado> copyOf(ArrayList<Estado> p) {
        ArrayList<Estado> res = new ArrayList<Estado>();
        for (Estado x : p) {
            try {
                res.add((Estado) x.clone());
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }

    public ArrayList<Estado> quitarVisitados(ArrayList<Estado> p) {
        for (Iterator<Estado> it = p.iterator(); it.hasNext();) {
            Estado est = it.next();
            if (visitados[est.estadoRobot.x][est.estadoRobot.y][est.estadoMovil.x][est.estadoMovil.y]) {
//                System.out.println("quited");
                it.remove();
            }
        }
        return p;
    }

    void verVisitados() {
        for (int i = 0; i < visitados.length; i++) {
            for (int j = 0; j < visitados[i].length; j++) {
                for (int k = 0; k < visitados[j].length; k++) {
                    for (int l = 0; l < visitados.length; l++) {
                        if (visitados[i][j][k][l]) {
                            System.out.println(i + "," + j + ":" + k + "," + l + ",");
                        }
                    }
                }
            }
        }
    }
    // Necesita ser SobreEscrito para cada Problema especifico de IA

    public ArrayList<Estado> suc(Estado p) {
        System.out.println("x");
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
