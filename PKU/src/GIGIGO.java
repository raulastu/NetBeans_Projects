

import java.util.Scanner;

public class GIGIGO {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        while (sc.hasNext()) {
            int x=sc.nextInt();
            long ix=1;
            int ixx=1;
            long i=1;
            long di=0;
            for (i = 1; true; i+=ixx) {
                if(i>=x){
                    break;
                }
                if(ixx%10==0)
                    di++;
                ixx++;
                ix+=1+di;
            }
            System.out.println("ix = " + ix);
            System.out.println("i = " + i);
            System.out.println(ixx-(i-x));
        }
        sc.close();
    }
}
