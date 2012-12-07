package cormenpractice;


import java.util.*;
import static java.lang.Math.*;

public class Ex1_2_3 {

    public Ex1_2_3() {
        for (long i = 2; true; i++) {
            double a = 100*i*i;
            double b = pow(2,i);
            if(b>a){
                System.out.println(i+" : "+a+" : "+b);
                break;
            }
            System.out.println(i+" : "+a+" : "+b);
        }
    }

    public static void main(String[] args) {
        new Ex1_2_3();
    }
}
