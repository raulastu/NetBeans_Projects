package cormenpractice;

import java.util.*;
import static java.lang.Math.*;
import static java.lang.String.*;
import static java.util.Arrays.*;
import static java.util.Arrays.*;

public class Sorting {

    public Sorting() {
    }

    void insertionSort(int[] A) {
        for (int j = 1; j < A.length; j++) {
            int key = A[j];
            int i = j - 1;
            while (i >= 0 && A[i] < key) {
                A[i + 1] = A[i];
                i = i - 1;
            }
            A[i + 1] = key;
        }
    }

    void selectionSort(int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    int t = A[i];
                    A[i] = A[j];
                    A[j] = t;
                }
            }
        }
    }

    void noobSort(int[] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    int x = A[i];
                    A[i] = A[j];
                    A[j] = x;
                }
            }
        }
    }
    //INCOMPLETO

    void mergeSort(int A[], int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            System.out.println(p + " " + q + " " + r);
            mergeSort(A, p, q);
            mergeSort(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    void merge(int[] A, int p, int q, int r) {
        int n1 = q - p;
        int n2 = r - q;
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];
        for (int i = 0; i < n1; i++) {
            L[i] = A[p + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = A[q + j];
        }
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        int i = 0, j = 0;
        for (int k = p; k < r; k++) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {
//        int[] a = new int[100000];
//        for (int i = 0; i < a.length; i++) {
//            a[i] = i;
//        }
////        new Sorting().insertionSort(a);
//        int[] a = {31, 41, 59, 26, 41, 58, 1, 100, 3, 23, 54, 21};
        int[] a = {5, 2, 4, 7, 1, 3, 2, 6};
        long time = System.currentTimeMillis();
//        new Sorting().selectionSort(a);
        new Sorting().mergeSort(a, 0, a.length - 1);
        System.out.println(System.currentTimeMillis() - time + "s");

//        time = System.currentTimeMillis();
//        new Sorting().noobSort(a);
//        System.out.println(System.currentTimeMillis() - time + "s");

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
