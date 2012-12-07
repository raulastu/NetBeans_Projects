package cormenpractice;

import static java.lang.Math.*;

public class Pr1_1 {

    public Pr1_1() {
        int t = 1000*60*60*24*30*12*100;
        System.out.println("1 "+pow(E, t));
        System.out.println("2 "+pow(t, 2));
        System.out.println("3 "+t);
        System.out.println("5 "+sqrt(t));
        System.out.println("6 "+pow(t, 1/(double)3));
        System.out.println("7 "+log10(t)/(log10(2)));
        System.out.println("8 "+getN(t));

    }
    public long getN(long t){
        long res = 0;
        for (long i = 0; true; i++) {
            if(getFactorial(i)>t){
                res = i-1;
                break;
            }
        }
        return res;
    }
    public long getFactorial(long x){
        if(x<=1)
            return 1;
        return getFactorial(x-1)*x;
    }

    public static void main(String[] args) {
        new Pr1_1();
    }
}
