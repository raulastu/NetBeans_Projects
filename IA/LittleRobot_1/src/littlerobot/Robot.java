package littlerobot;

import core.Busqueda;
import core.Estado;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Robot extends Busqueda {

    private int x_min;
    private int y_min;
    private int x_max;
    private int y_max;
    private boolean[][] ocupados;

    public Robot(int x_min, int y_min, int x_max, int y_max, boolean[][] e) {
        this.x_min = x_min;
        this.y_min = y_min;
        this.x_max = x_max;
        this.y_max = y_max;
        this.ocupados = e;
    }

//    int[][] e;
    @Override
    public ArrayList<Estado> suc(Estado p) {
        int filaR = p.estadoRobot.x;
        int colR = p.estadoRobot.y;
        int filaM = p.estadoMovil.x;
        int colM = p.estadoMovil.y;
        ArrayList<Estado> res = new ArrayList<Estado>();

        /****Robot****/
        // mover arriba
        if (filaR - 1 >= x_min && isFree(filaR - 1, colR) && !p.contains(filaR - 1, colR)) {
            res.add(new Estado(new Estado(filaR - 1, colR), new Estado(filaM, colM)));
        }
        //mover Abajo
        if (filaR + 1 <= x_max && isFree(filaR + 1, colR) && (filaR + 1 != filaM || colM!=colR)) {
            res.add(new Estado(new Estado(filaR + 1, colR), new Estado(filaM, colM)));
        }
        //mover derecha filaR, colR + 1
        if (colR + 1 <= y_max && isFree(filaR, colR + 1) && !p.contains(filaR, colR + 1)) {
            res.add(new Estado(new Estado(filaR, colR + 1), new Estado(filaM, colM)));
        }
        //mover izquierda (filaR, colR - 1)
        if (colR - 1 >= y_min && isFree(filaR, colR - 1) && !p.contains(filaR, colR - 1)) {
            res.add(new Estado(new Estado(filaR, colR - 1), new Estado(filaM, colM)));
        }
        /********Movil*********/
        //mover arriba filaM - 1, colM
        if (filaM - 1 >= x_min && isFree(filaM - 1, colM) && (filaR - 1 == filaM && colR == colM)) {
            res.add(new Estado(new Estado(filaR - 1, colR), new Estado(filaM - 1, colM)));
        }
        //mover abajo (filaM + 1, colM)
        if (filaM + 1 <= x_max && isFree(filaM + 1, colM) &&
                (filaR + 1 == filaM && colM == colR)) {
            res.add(new Estado(new Estado(filaR + 1, colR), new Estado(filaM + 1, colM)));
        }
        //mover derecha filaM, colM + 1
        if (colM + 1 <= y_max && isFree(filaM, colM + 1) &&
                (filaR == filaM && colR + 1 == colM)) {
            res.add(new Estado(new Estado(filaR, colR + 1), new Estado(filaM, colM + 1)));
        }
        //mover izquierda (filaM,colM-1)
        if (colM - 1 >= y_min && isFree(filaM, colM - 1) &&
                (filaR == filaM && colR - 1 == colM)) {
            res.add(new Estado(new Estado(filaR, colR - 1), new Estado(filaM, colM - 1)));
        }
        return res;
    }

    private boolean isFree(int x, int y) {
        return !ocupados[x][y];
    }

    public static void main(String[] args) {
        int xMax= 6,yMax=7;
        boolean[][] obstacle = new boolean[xMax+1][yMax+1];
        obstacle[2][3] = true;

        Robot x = new Robot(1, 1, xMax, yMax, obstacle);
        x.setEstadoInicial(new Estado(new Estado(1, 1), new Estado(2, 2)));
        x.setEstadoFinal(new Estado(new Estado(-1, -1), new Estado(xMax, yMax)));
        x.resolver();
        x.savingData = true;
        if (x.solved) {
            x.showData();
            System.out.println(x.lista);
        } else {
            System.out.println("No existe solucion");
        }
    }
}
