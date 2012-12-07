package androide;

public class Nodo {

    private int x;
    private Nodo sig;

    public Nodo(int x) {
        this.x = x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }

    public Nodo getSig() {
        return sig;
    }

    @Override
    public String toString() {
        return x+"";
    }




}
