package androide;

public class Lista {

    Nodo primero, ultimo;

    void addOrdenados(int x) {
        System.out.println(this);
        Nodo o = new Nodo(x);
        if (primero == null) {
            primero = ultimo = o;
        } else {
            Nodo p = primero;
            Nodo ant = p;
            while (p != null && p.getX() < x) {
                ant = p;
                p = p.getSig();
            }
            p = ant;
            if (p == ultimo) {
                ultimo.setSig(o);
                ultimo = o;
            } else if (p == primero) {
                o.setSig(p.getSig());
                primero.setSig(o);
            } else {
                o.setSig(p.getSig());
                p.setSig(o);
            }
        }
    }

    void add(int x) {
        Nodo o = new Nodo(x);
        if (primero == null) {
            primero = ultimo = o;
        } else {
            ultimo.setSig(o);
            ultimo = o;
        }
    }

    @Override
    public String toString() {
        Nodo o = primero;
        String x = "{";
        while (o != null) {
            x += o + ", ";
            o = o.getSig();
        }
        x = x.length() > 2?x.substring(0, x.length() - 2) + "}":"empty list";
        return x;
    }

    public double _prom() {
        int i = 1;
        return prom(Double.parseDouble(primero.getX() + ""), i, primero);
    }

    public double prom(double s, int i, Nodo p) {
        if (p == null) {
            return s;
        }
        double r = (s * (i - 1) + Integer.parseInt(p.getX() + "")) / i;
        return prom(r, i + 1, p.getSig());
    }

    public void balancear(Lista a, Lista b) {
        Nodo p = primero;
        while (p != null) {
            if (sum(a) < sum(b)) {
                a.add(p.getX());
            } else {
                b.add(p.getX());
            }
            p = p.getSig();
        }
    }

    static public int sum(Lista x) {
        Nodo p = x.primero;
        int s = 0;
        while (p != null) {
            s += p.getX();
            p = p.getSig();
        }
        return s;
    }

    public static void main(String[] args) {
        Lista l = new Lista();
        l.addOrdenados(100);
        l.addOrdenados(101);
        l.addOrdenados(102);
        l.addOrdenados(103);
        l.addOrdenados(104);
        l.addOrdenados(105);
        l.addOrdenados(106);
        l.addOrdenados(107);
        l.addOrdenados(108);
        l.addOrdenados(109);
        l.addOrdenados(110);
        
        System.out.println(l._prom());
        Lista a = new Lista();
        Lista b = new Lista();
        l.balancear(a, b);
        System.out.println(a + " " + sum(a));
        System.out.println(b + " " + sum(b));
    }
}
