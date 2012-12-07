package javaapplication11;

public class Par {

    int x, y;

    public Par(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Par(x,y);
    }

    @Override
    public boolean equals(Object obj) {
        Par ob = (Par) obj;
        return this.x == ob.x && this.y == ob.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}