package contestsucks;

public class Main {

    class OMG {

        int x = 1;
        String s = "def";
    }

    public Main() {
        String ab = "xxx";
        doSomething(ab);
        System.err.println(ab);
        OMG om = new OMG();
        doSomething2(om);
        doSomething3(om);
        System.err.println(om.x);
        System.err.println(om.s);
    }

    public void doSomething(String a) {
        a = "asda";
    }

    private void doSomething2(OMG a) {
        a.x = 2;
    }

    private void doSomething3(OMG a) {
        a.s = "nondef";
    }

    public static void main(String[] args) {
        new Main();
    }
}
