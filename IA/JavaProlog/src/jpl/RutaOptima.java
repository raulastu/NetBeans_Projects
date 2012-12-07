package jpl;

import java.util.Hashtable;

public class RutaOptima {

    public static void main(String argv[]) {
        String t1 = "consult('E:/Prolog/MyRCBot2Test.pl')";
        Query q1 = new Query(t1);
        System.out.println(t1 + " " + (q1.hasSolution()?"succeeded":"failed"));
//--------------------------------------------------
        String t2 = "go([0,0,1,1,2,2],[2,2],L).";
        Query q2 = new Query(t2);
//        q2.allSolutions();
        System.out.println(q2.goal());
        for (Hashtable hashtable : q2.allSolutions()) {
            System.out.println(hashtable);
        }
        System.out.println();
//        for (Hashtable hashtable : q2.allSolutions()) {
////            System.out.println("x"+hashtable+"x");
//        }
//        System.out.println(q2.getSolution());

    }
}