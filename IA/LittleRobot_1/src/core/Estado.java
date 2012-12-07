package core;

public class Estado {

    public int x = -2, y = -2;

    public Estado(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Estado estadoRobot;
    public Estado estadoMovil;

    public Estado(Estado x, Estado y) {
        estadoRobot = x;
        estadoMovil = y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        if (estadoRobot != null && estadoMovil != null) {
            return new Estado((Estado) estadoRobot.clone(), (Estado) estadoMovil.clone());
        }
        return new Estado(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        Estado otherEstado = (Estado) obj;

        if (otherEstado.estadoRobot != null && estadoRobot != null &&
                otherEstado.estadoMovil != null && estadoMovil != null) {
            boolean c1 = estadoRobot.equals(otherEstado.estadoRobot);
            boolean c2 = estadoMovil.equals(otherEstado.estadoMovil);
            return c1 && c2;
        }

        boolean first = (this.x == -1 ? true : (otherEstado.x == -1 ? true : this.x == otherEstado.x));
        boolean second = (this.y == -1 ? true : (otherEstado.y == -1 ? true : this.y == otherEstado.y));
        return first && second;
    }

    @Override
    public String toString() {
        if (estadoRobot != null && estadoMovil != null) {
            return estadoRobot.toString() + ";" + estadoMovil.toString();
        }
        return "(" + x + "," + y + ")";
    }

    public boolean contains(int inX, int inY) {
        if (estadoRobot != null && estadoMovil != null) {
            boolean c1 = estadoRobot.contains(inX, inY);
            boolean c2 = estadoMovil.contains(inX, inY);
            return c1 || c2;
        }
        return x == inX && y == inY;
    }

    public void setVars(int x, int y) {
        this.x = x;
        this.y = y;
    }
}