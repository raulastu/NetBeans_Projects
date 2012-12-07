package core;

public class Estado {

    public int x = -2, y = -2;
    public int xm = -2, ym = -2;
    // constructor para un Estado de dos variables x,y usado para el problema de las vasijas

    public Estado(int x, int y, int xm, int ym) {
        this.x = x;
        this.y = y;
        this.xm = xm;
        this.ym = ym;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Estado(x, y, xm, ym);
    }

    @Override
    public boolean equals(Object obj) {
        Estado otherEstado = (Estado) obj;
        int a = otherEstado.x;
        int b = otherEstado.y;
        int c = otherEstado.xm;
        int d = otherEstado.ym;
        return (x * a < 0 || x == a) &&
                (y * b < 0 || y == b) &&
                (xm * c < 0 || xm == c) &&
                (ym * d < 0 || ym == d);
    }

    public boolean equals(int a, int b, int c, int d) {
        return (a * x < 0 || x == a) &&
                (y * b < 0 || y == b) &&
                (xm * c < 0 || xm == c) &&
                (ym * d < 0 || ym == d);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + " - " + xm + "," + ym + ")";
    }

    public boolean contains(int inX, int inY) {
        return (x == inX && y == inY) || (inX == xm && inY == ym);
    }

    public void setVars(int x, int y) {
        this.x = x;
        this.y = y;
    }
}