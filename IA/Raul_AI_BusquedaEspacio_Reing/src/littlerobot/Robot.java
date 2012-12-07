package littlerobot;

import core.Busqueda;
import core.Estado;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
//import jpl.Query;

public class Robot extends Busqueda {

    private int x_min;
    private int y_min;
    private int x_max;
    private int y_max;
    private boolean[][] ocupados;

    public Robot() {
    }

    public Robot(int x_min, int y_min, int x_max, int y_max, boolean[][] e) {
        this.x_min = x_min;
        this.y_min = y_min;
        this.x_max = x_max;
        this.y_max = y_max;
        this.ocupados = e;
    }

//    int[][] e;
    @Override
    public LinkedList<Estado> suc(Estado p) {
        int xb = p.x;
        int yb = p.y;
        int xm = p.xm;
        int ym = p.ym;
        LinkedList<Estado> res = new LinkedList<Estado>();

        /****Robot****/
        //1 mover arriba
        if (xb > x_min && isFree(xb - 1, yb) &&
                !p.contains(xb - 1, yb) &&
                !visitados[xb - 1][yb][xm][ym]) {
            res.add(new Estado(xb - 1, yb, xm, ym));
        }

        //2 mover Abajo
        if (xb < x_max && isFree(xb + 1, yb) && (xb + 1 != xm || ym != yb) &&
                !visitados[xb + 1][yb][xm][ym]) {
            res.add(new Estado(xb + 1, yb, xm, ym));
        }
        //3 bot mover derecha filaR, colR + 1
        if (yb < y_max && isFree(xb, yb + 1) && !p.contains(xb, yb + 1) &&
                !visitados[xb][yb + 1][xm][ym]) {
            res.add(new Estado(xb, yb + 1, xm, ym));
        }
        //4  bot mover izquierda (filaR, colR - 1)
        if (yb > y_min && isFree(xb, yb - 1) && !p.contains(xb, yb - 1) &&
                !visitados[xb][yb - 1][xm][ym]) {
            res.add(new Estado(xb, yb - 1, xm, ym));
        }
        /********Movil*********/
        //5 mover arriba filaM - 1, colM
        if (xm > x_min && isFree(xm - 1, ym) && (xb - 1 == xm && yb == ym) &&
                !visitados[xb - 1][yb][xm - 1][ym]) {
            res.add(new Estado(xb - 1, yb, xm - 1, ym));
        }
        //6 mover abajo (filaM + 1, colM)
        if (xm < x_max && isFree(xm + 1, ym) &&
                (xb + 1 == xm && ym == yb) &&
                !visitados[xb + 1][yb][xm + 1][ym]) {
            res.add(new Estado(xb + 1, yb, xm + 1, ym));
        }
        //7 mover derecha filaM, colM + 1
        if (ym < y_max && isFree(xm, ym + 1) &&
                (xb == xm && yb + 1 == ym) &&
                !visitados[xb][yb + 1][xm][ym + 1]) {
            res.add(new Estado(xb, yb + 1, xm, ym + 1));
        }
        //8 mover izquierda (filaM,colM-1)
        if (ym > y_min && (xb == xm && yb - 1 == ym) &&
                isFree(xm, ym - 1) &&
                !visitados[xb][yb - 1][xm][ym - 1]) {
            res.add(new Estado(xb, yb - 1, xm, ym - 1));
        }
        return res;
    }

    private boolean isFree(int x, int y) {
        return !ocupados[x][y];
    }

    /** From Prolog
     *
     * @param args
     */
    public LinkedList<Estado> fromProlog() throws FileNotFoundException {
        String t1 = "consult('E:/Prolog/MyRCBotObst.pl')";
//        Query q1 = new Query(t1);
//        System.out.println(t1 + " " + (q1.hasSolution()?"succeeded":"failed"));
//--------------------------------------------------

        String obstacles = "[";
        for (int i = 0; i < ocupados.length; i++) {
            for (int j = 0; j < ocupados[i].length; j++) {
                if (ocupados[i][j]) {
                    obstacles += "[" + i + "," + j + "], ";
                }
            }
        }
        if (!obstacles.equals("[")) {
            obstacles = obstacles.substring(0, obstacles.length() - 2);
        }
        obstacles += "]";
//        System.out.println(obstacles);

        String t2 = "go([" + estadoInicial.x + "," +
                estadoInicial.y + "," +
                estadoInicial.xm + "," +
                estadoInicial.ym + "," +
                x_max + "," + y_max + "]," + "[" +
                estadoFinal.xm + "," +
                estadoFinal.ym + "]" +
                "," + obstacles + ").";
        System.out.println(t2);
//        Query q2 = new Query(t2);
//        q2.allSolutions();
        Scanner sc = new Scanner(new File("E:/Prolog/MyRCBotObst.sol"));
        LinkedList<Estado> res = new LinkedList<Estado>();
        String line = sc.nextLine();
        if (line.equals("No hay solucion")) {
            solved = false;
        } else {
            solved = true;
            do {
                String[] var = line.split(",");
                int xb = Integer.parseInt(var[0].trim());
                int yb = Integer.parseInt(var[1].trim());
                int xm = Integer.parseInt(var[2].trim());
                int ym = Integer.parseInt(var[3].trim());
                res.add(new Estado(xb, yb, xm, ym));
            } while (sc.hasNextLine() && !(line = sc.nextLine().trim()).equals(""));
        }
        lista = res;
        return res;
    }

    public static void fromJava() {
        int xMax = 6, yMax = 6;
        boolean[][] obstacle = new boolean[xMax + 1][yMax + 1];
//        obstacle[2][3] = true;
        Robot x = new Robot(0, 0, xMax, yMax, obstacle);
        x.setEstadoInicial(new Estado(1, 1, 2, 2));
        x.setEstadoFinal(new Estado(-1, -1, xMax, yMax));
//        x.savingData = true;
        x.resolver();
        if (x.solved) {
            x.showData();
            System.out.println(x.lista);
        } else {
            System.out.println("No existe solucion");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
//        int xMax = 2, yMax = 2;
//        boolean[][] obstacle = new boolean[xMax + 1][yMax + 1];
//        obstacle[1][2] = true;
//        obstacle[2][1] = true;
//        Robot x = new Robot(0, 0, xMax, yMax, obstacle);
//        x.setEstadoInicial(new Estado(0, 0, 1, 1));
//        x.setEstadoFinal(new Estado(-1, -1, xMax, yMax));
//        LinkedList<Estado> a = x.fromProlog();
//        System.out.println(a);
        fromJava();
    }
}
