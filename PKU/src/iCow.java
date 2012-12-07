
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Collections.*;

import java.util.Scanner;

public class iCow {

    static class Node {

        int i, R;

        public Node(int x, int y) {
            this.i = x;
            this.R = y;
        }

        @Override
        public String toString() {
            return i + " " + R;
        }

        @Override
        public boolean equals(Object obj) {
            Node b = (Node) obj;
            return b.i == i && b.R == R;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int n = parseInt(line[0]);
        int t = parseInt(line[1]);
        Node[] songs = new Node[n];
        int c = 0;
        while (c < n) {
            songs[c] = new Node((c++ + 1), parseInt(sc.nextLine()));
        }
        Comparator comp = new Comparator() {

            public int compare(Object o1, Object o2) {
                Node a = (Node) o1;
                Node b = (Node) o2;
                if (a.R == b.R) {
                    return b.i - a.i;
                }
                return a.R - b.R;
            }
        };
        for (int i = 0; i < t; i++) {
            sort(songs, comp);
//            print(songs);
            Node nod = songs[songs.length - 1];
            System.out.println(nod.i);
            int perNod = nod.R / (songs.length - 1);
//            System.err.println(perNod);
            int r = nod.R % (songs.length - 1);
            if(nod.i<=r)r++;
            for (int j = 0; j < songs.length; j++) {
                if (songs[j].i != nod.i) {
                    songs[j].R += perNod;
                    if (songs[j].i <= r) {
                        songs[j].R++;
                    }
                }
            }
            nod.R = 0;
        }
    }

    private static void print(Object... rs) {
        print("", rs);
    }

    private static void print(String msg, Object... rs) {
        String x = deepToString(rs);
        if (x.indexOf("[[") == 0) {
            x = x.substring(1, x.length() - 1);
        }
        System.err.println(msg + " " + x);
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
}
