package exe2;

public class Lista {

    NodoChar primero, ultimo;

    public void add(char x) {
        NodoChar o = new NodoChar(x);
        if (primero == null) {
            primero = ultimo = o;
        } else {
            ultimo.setSig(o);
            ultimo = o;
        }
    }

    public void deleteRep() {
        NodoChar p = primero;
        while (p != null) {
            NodoChar q = p.getSig();
            while (q != null) {
                if (q.getX() == p.getX()) {
                    System.out.println(q.getX());
                    delete(q);
                }
                q = q.getSig();
            }
            p = p.getSig();
        }
    }

    public void delete(NodoChar x) {
        NodoChar p = primero;
        NodoChar ante = p;
        while (x != p) {
            ante = p;
            p = p.getSig();
        }
        if (p == primero) {
            primero = p.getSig();
        } else if (p == ultimo) {
            System.out.println("x");
            ante.setSig(null);
            ultimo = ante;
        } else {
            ante.setSig(p.getSig());
        }
    }

    @Override
    public String toString() {
        NodoChar p = primero;
        String res = "{";
        while (p != ultimo) {
            res += p + " ";
            p = p.getSig();
        }
        res += ultimo;
        return res;
    }

    public void reverse() {
        NodoChar p = primero;
        NodoChar a = p;
        NodoChar t = primero;
        primero = ultimo;
        ultimo = t;
        NodoChar b = p;
        NodoChar c = p;
        while (c != null) {
            a = c;
            b = a.getSig();
            c = b.getSig();
            a.setSig(b);
            c = c.getSig();
        }
    }

    public void _rev() {

        NodoChar x = primero;
        reverse(primero, primero.sig);
        primero = ultimo;
        ultimo = x;
    }

    public void reverse(NodoChar q, NodoChar p) {
        if (p == null) {
            return;
        }
        reverse(p, p.getSig());
        p.setSig(q);
    }

    public void div(Lista a, Lista b) {
        NodoChar p = primero;
        int i = 0;
        while (p != null) {
            if (i % 2 == 0) {
                a.add(p.getX());
            } else {
                b.add(p.getX());
            }
            i++;
            p = p.getSig();
        }
    }

    public int longRec() {
        return longitudRec(primero);
    }

    public int longitudRec(NodoChar p) {
        if (p == null) {
            return 0;
        }
        return 1 + longitudRec(p.getSig());
    }

    public double _prom() {
        int i = 1;
        return prom(Double.parseDouble(primero.getX()+""), i, primero);
    }

    public double prom(double s, int i, NodoChar p) {
        if (p == null) {
            return s;
        }
        double r = (s * (i-1) + Integer.parseInt(p.getX() + "")) / i;
        return prom(r, i + 1, p.getSig());
    }

    public static void main(String[] args) {
        Lista list = new Lista();
        list.add('1');
        list.add('1');
        list.add('1');
        list.add('1');
        list.add('1');
        list.add('2');
        list.add('2');
        list.add('2');
        list.add('2');
        list.add('2');
//        list.deleteRep();
//        System.out.println(list);
//        list._rev();
//        System.out.println(list);
//        list.add('3');
//        System.out.println(list);
//        list._rev();
//        System.out.println(list);

        ///
        Lista a = new Lista();
        Lista b = new Lista();
        list.div(a, b);
        System.out.println(list);
        System.out.println(a);
        System.out.println(b);
        System.out.println(list.longRec());
        System.out.println(list._prom());        
    }
}
