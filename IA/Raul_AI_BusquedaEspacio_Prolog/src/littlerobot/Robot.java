package littlerobot;

import core.Busqueda;
import core.Estado;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import jpl.Query;

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
        int filaR = p.estadoRobot.x;
        int colR = p.estadoRobot.y;
        int filaM = p.estadoMovil.x;
        int colM = p.estadoMovil.y;
        LinkedList<Estado> res = new LinkedList<Estado>();

        /****Robot****/
        //1 mover arriba
        if (filaR - 1 >= x_min && isFree(filaR - 1, colR) && !p.contains(filaR - 1, colR)) {
            res.add(new Estado(new Estado(filaR - 1, colR), new Estado(filaM, colM)));
        }

        //2 mover Abajo
        if (filaR + 1 <= x_max && isFree(filaR + 1, colR) && (filaR + 1 != filaM || colM != colR)) {
            res.add(new Estado(new Estado(filaR + 1, colR), new Estado(filaM, colM)));
        }
        //3 mover derecha filaR, colR + 1
        if (colR + 1 <= y_max && isFree(filaR, colR + 1) && !p.contains(filaR, colR + 1)) {
            res.add(new Estado(new Estado(filaR, colR + 1), new Estado(filaM, colM)));
        }
        //4 mover izquierda (filaR, colR - 1)
        if (colR - 1 >= y_min && isFree(filaR, colR - 1) && !p.contains(filaR, colR - 1)) {
            res.add(new Estado(new Estado(filaR, colR - 1), new Estado(filaM, colM)));
        }
        /********Movil*********/
        //5 mover arriba filaM - 1, colM
        if (filaM - 1 >= x_min && isFree(filaM - 1, colM) && (filaR - 1 == filaM && colR == colM)) {
            res.add(new Estado(new Estado(filaR - 1, colR), new Estado(filaM - 1, colM)));
        }
        //6 mover abajo (filaM + 1, colM)
        if (filaM + 1 <= x_max && isFree(filaM + 1, colM) &&
                (filaR + 1 == filaM && colM == colR)) {
            res.add(new Estado(new Estado(filaR + 1, colR), new Estado(filaM + 1, colM)));
        }
        //7 mover derecha filaM, colM + 1
        if (colM + 1 <= y_max && isFree(filaM, colM + 1) &&
                (filaR == filaM && colR + 1 == colM)) {
            res.add(new Estado(new Estado(filaR, colR + 1), new Estado(filaM, colM + 1)));
        }
        //8 mover izquierda (filaM,colM-1)
        if (colM - 1 >= y_min && isFree(filaM, colM - 1) &&
                (filaR == filaM && colR - 1 == colM)) {
            res.add(new Estado(new Estado(filaR, colR - 1), new Estado(filaM, colM - 1)));
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
//        this.getClass().get
        System.err.println(System.getProperty("java.library.path"));
        String t1 = "consult('MyRCBotObst.pl')";
        Query q1 = new Query(t1);
        System.out.println(t1 + " " + (q1.hasSolution() ? "succeeded" : "failed"));
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

        String t2 = "go([" + estadoInicial.estadoRobot.x + "," +
                estadoInicial.estadoRobot.y + "," +
                estadoInicial.estadoMovil.x + "," +
                estadoInicial.estadoMovil.y + "," +
                x_max + "," + y_max + "]," + "[" +
                estadoFinal.estadoMovil.x + "," +
                estadoFinal.estadoMovil.y + "]" +
                "," + obstacles + ").";
        System.out.println(t2);
        Query q2 = new Query(t2);
        q2.allSolutions();
        Scanner sc = new Scanner(new File("MyRCBotObst.sol"));
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
                res.add(new Estado(new Estado(xb, yb), new Estado(xm, ym)));
            } while (sc.hasNextLine() && !(line = sc.nextLine().trim()).equals(""));
        }
        lista = res;
        return res;
    }

    public static void fromJava() {
        int xMax = 7, yMax = 7;
        boolean[][] obstacle = new boolean[xMax + 1][yMax + 1];
//        obstacle[2][3] = true;
        Robot x = new Robot(0, 0, xMax, yMax, obstacle);
        x.setEstadoInicial(new Estado(new Estado(1, 1), new Estado(2, 2)));
        x.setEstadoFinal(new Estado(new Estado(-1, -1), new Estado(xMax, yMax)));
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


        int xMax = 2, yMax = 2;
        boolean[][] obstacle = new boolean[xMax + 1][yMax + 1];
        obstacle[1][2] = true;
        obstacle[2][1] = true;
        Robot x = new Robot(0, 0, xMax, yMax, obstacle);
        x.setEstadoInicial(new Estado(new Estado(0, 0), new Estado(1, 1)));
        x.setEstadoFinal(new Estado(new Estado(-1, -1), new Estado(xMax, yMax)));
        LinkedList<Estado> a = x.fromProlog();
        System.out.println(a);
    }
}
