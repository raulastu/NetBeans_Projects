package _2008_;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;
import java.util.*;


import java.util.Scanner;

public class TrainTimeTable {

    static  class Node{
        int departure;
        int arrival;
        boolean depTaken;
        public Node(int departure, int arrival) {
            this.departure = departure;
            this.arrival = arrival;
        }

        @Override
        public String toString() {
            return departure+" "+arrival;
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:B-large-practice.in"));
//        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new File("C:/myOutput.txt"));
        int nn = Integer.parseInt(sc.nextLine());
        int c = 0;
        while (c++ < nn) {
            int turnaround = Integer.parseInt(sc.nextLine());
            int []a = readIntArray(sc.nextLine());
            int NA = a[0];
            int NB = a[1];
            Node [] NAs = new Node[NA];
            for (int i = 0; i < NA; i++) {
                String[] s = sc.nextLine().split(" ");
                int hDep = parseInt(s[0].split(":")[0]);
                int mDep = parseInt(s[0].split(":")[1]);
                int departure = hDep*60+mDep;
                int hArr = parseInt(s[1].split(":")[0]);
                int mArr = parseInt(s[1].split(":")[1]);
                int arrival = hArr*60+mArr;
                NAs[i]=new Node(departure, arrival);
            }
            Node [] NBs = new Node[NB];
            for (int i = 0; i < NB; i++) {
                String[] s = sc.nextLine().split(" ");
                int hDep = parseInt(s[0].split(":")[0]);
                int mDep = parseInt(s[0].split(":")[1]);
                int departure = hDep*60+mDep;
                int hArr = parseInt(s[1].split(":")[0]);
                int mArr = parseInt(s[1].split(":")[1]);
                int arrival = hArr*60+mArr;
                NBs[i]=new Node(departure, arrival);
            }
            sort(NAs,new Comparator() {

                public int compare(Object o1, Object o2) {
                    Node a = (Node)o1;
                    Node b = (Node)o2;
                    return (a.arrival-b.arrival);
                }
            });
            sort(NBs,new Comparator() {

                public int compare(Object o1, Object o2) {
                    Node a = (Node)o1;
                    Node b = (Node)o2;
                    return (int)(a.departure-b.departure);
                }
            });
            print(NAs);
            print(NBs);

            for (int i = 0; i < NAs.length; i++) {
                int avai = NAs[i].arrival+turnaround;
                for (int j = 0; j < NBs.length; j++) {
                    if(avai<=NBs[j].departure && !NBs[j].depTaken){
                        NBs[j].depTaken=true;
                        break;
                    }
                }
            }

            sort(NAs,new Comparator() {

                public int compare(Object o1, Object o2) {
                    Node a = (Node)o1;
                    Node b = (Node)o2;
                    return (int)(a.departure-b.departure);
                }
            });
            sort(NBs,new Comparator() {

                public int compare(Object o1, Object o2) {
                    Node a = (Node)o1;
                    Node b = (Node)o2;
                    return (int)(a.arrival-b.arrival);
                }
            });
            for (int i = 0; i < NBs.length; i++) {
                int avai = NBs[i].arrival+turnaround;
                for (int j = 0; j < NAs.length; j++) {
                    if(avai<=NAs[j].departure && !NAs[j].depTaken){
                        NAs[j].depTaken=true;
                        break;
                    }
                }
            }
            int resB = 0;
            for (int i = 0; i < NBs.length; i++) {
                if(!NBs[i].depTaken){
                    resB++;
                }
            }

            int resA = 0;
            for (int i = 0; i < NAs.length; i++) {
                if(!NAs[i].depTaken){
                    resA++;
                }
            }
            String resToShow = "Case #" + c + ": " + resA+" "+resB;
            System.err.println(resToShow);
            pw.println(resToShow);
        }
        pw.close();
    }

    private static int[] readIntArray(String sx) {
        String[] s = sx.split(" ");
        int[] res = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            res[i] = parseInt(s[i]);
        }
        return res;
    }
    
    private static long [] readLongArray(String sx) {
        String[] s = sx.split(" ");
        long [] res = new long [s.length];
        for (int i = 0; i < s.length; i++) {
            res[i] = parseInt(s[i]);
        }
        return res;
    }

    private static void print(Object... rs) {
        System.err.println(Arrays.deepToString(rs));
    }

    private static void printm(String[] a) {
        for (int i = 0; i < a.length; i++) {
            System.err.println("[" + a[i] + "]");
        }
    }

    private static void printm(char[][] a) {
        for (int i = 0; i < a.length; i++) {
            System.err.println("[" + new String(a[i]) + "]");
        }
    }

    private static void printm(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.err.println("[" + a[i][j] + "]");
            }
        }
    }
}
