package noutilYet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashSet;

public class Interseccion2Rectas {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //DataInputStream in= new DataInputStream((System.in));        
        System.out.println("Ingrese el numero: x1 ");
        long time1 = new Date().getTime();
        System.out.println();
        float x1_A = 1;
        float y1_A = 1;
        float x2_A = 5;
        float y2_A = 5;
        float x1_B = 4;
        float y1_B = 1;
        float x2_B = 3;
        float y2_B = 1;
        System.out.println(new Date().getTime() - time1);
        HashSet<float[]> setA = new HashSet<float[]>();
        HashSet<float[]> setB = new HashSet<float[]>();
        float mA = (x1_A - x2_A) / (y1_A - y2_A);
        float bA = y1_A - x1_A * mA;

        if (x1_A < x2_A) {
            for (float i = x1_A; i <= x2_A; i=i+(float)0.1) {
                setA.add(new float[]{i, i* mA + bA});
            }
        } else {
            for (float i = x2_A; i <= x1_A; i=i+(float)0.1) {
                setA.add(new float[]{i, i * mA + bA});
            }
        }

        float mB = (x1_B - x2_B) / (y1_B - y2_B);
        float bB = y1_B - x1_B * mB;

        if (x1_B < x2_B) {
            for (float i = x1_B; i <= x2_B; i+=(float)(0.1)) {
                setB.add(new float[]{i, i * mB + bB});
            }
        } else {
            for (float i = x2_B; i <= x1_B; i=i+(float)0.1) {
                setB.add(new float[]{i, i * mB + bB});
            }
        }
        HashSet<float[]> setAB = new HashSet<float[]>();
        for (float[] xyA : setA) {
            for (float xyB[] : setB) {
                if (xyA[0] == xyB[0] && xyA[1] == xyB[1]) {
                    setAB.add(new float[]{xyA[0], xyB[1]});
                }
            }
        }
        System.out.println("Puntos de Interseccion: " + setAB.size());

        for (float[] ab : setAB) {
            System.out.println(ab[0]+","+ab[1]);
        }
    }
}
