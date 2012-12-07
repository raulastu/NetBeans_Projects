
package javaapplication5;

public class Ruta {
    private String a,b;
    private int d;

    public Ruta(String a, String b, int d) {
        this.a = a;
        this.b = b;
        this.d = d;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return a+","+b+"-"+d;
    }

    @Override
    public boolean equals(Object obj) {
       Ruta o = (Ruta)obj;
       return o.getA().equals(a) && o.getB().equals(b) && o.getD()==d;
    }
   
}
