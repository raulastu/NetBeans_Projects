package exe2;

public class NodoChar {

    private char x;
    NodoChar sig;

    public NodoChar(char x) {
        this.x = x;
    }

    public char getX() {
        return x;
    }

    public void setX(char x) {
        this.x = x;
    }

    public void setSig(NodoChar sig) {
        this.sig = sig;
    }

    public NodoChar getSig() {
        return sig;
    }

    @Override
    public String toString() {
        return x + "";
    }
}
