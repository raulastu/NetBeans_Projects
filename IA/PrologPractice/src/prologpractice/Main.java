package prologpractice;

public class Main {

    public long getFactorial(Integer x) {
        if (x == 1) {
            return 1;
        }
        return x * getFactorial(x - 1);
    }

    public long pow(int a, int b) {
        if (b == 0) {
            return 1;
        }
        return a * pow(a, b - 1);
    }

    public static void main(String[] args) throws Exception {
        long r = new Main().getFactorial(40);
        System.out.println(r);
        System.out.println(new Main().pow(2, 10));
    }
}
