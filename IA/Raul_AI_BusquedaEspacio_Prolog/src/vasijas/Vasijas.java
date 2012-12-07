package vasijas;

import core.Busqueda;
import core.Estado;
import java.util.LinkedList;

public class Vasijas extends Busqueda {

    public int x_max = 4;
    public int y_max = 3;

    @Override
    public LinkedList<Estado> suc(Estado p) {
        System.out.println("suc Vasijas");
        LinkedList<Estado> res = new LinkedList<Estado>();
        if (p.x < x_max) {
            res.add(new Estado(x_max, p.y));
        }
        if (p.y < y_max) {
            res.add(new Estado(p.x, y_max));
        }
        if (p.x > 0) {
            res.add(new Estado(0, p.y));
        }
        if (p.y > 0) {
            res.add(new Estado(p.x, 0));
        }
        //pasar de x a y
        int m = (y_max - p.y) <= p.x ? (y_max - p.y) : p.x;
//        System.out.println(m);
        if (m > 0 && m <= p.x && m <= x_max) {
            res.add(new Estado(p.x - m, p.y + m));
        }
        //pasar de y a x
        int n = (x_max - p.x) <= p.y ? (x_max - p.x) : p.y;
        if (n > 0 && n <= p.y && n <= y_max) {
            res.add(new Estado(p.x + n, p.y - n));
        }
        return res;
    }

    public static void main(String[] args) {
        Vasijas b = new Vasijas();
        b.setEstadoInicial(new Estado(0, 0));
        b.setEstadoFinal(new Estado(2, -1));
        b.resolver();
        if (b.solved) {
            b.showData();
        } else {
            System.out.println("no existe solucion");
        }
    }
}
