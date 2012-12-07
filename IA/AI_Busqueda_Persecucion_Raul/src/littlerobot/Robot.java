package littlerobot;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Robot {

    static int[][] dir = {{-1, 0, 1, 0, 0}, {0, 1, 0, 0, -1}, {1, 0, 0, -1, 0}, {0, 0, -1, 0, 1}}; //uldr
    static int[][] djr = {{0, 1, 0, -1, 0}, {1, 0, -1, 0, 0}, {0, -1, 0, 0, 1}, {-1, 0, 0, 1, 0}};
    static int[][] dib = {{-1, 0, 1, 0}, {0, -1, 0, 1}, {1, 0, -1, 0}, {0, 1, 0, -1}}; //uldr
    static int[][] djb = {{0, 1, 0, -1}, {-1, 0, 1, 0}, {0, -1, 0, 1}, {1, 0, -1, 0}};
    static int[][][] diRB = {dir, dib};
    static int[][][] djRB = {djr, djb};
    static int[] di5 = {-1, 0, 1, 0, 0}; //urdl
    static int[] dj5 = {0, 1, 0, -1, 0};
    static int[] di = {-1, 0, 1, 0}; //urdl
    static int[] dj = {0, 1, 0, -1};
    static String[] c = {"up", "right", "down", "left"};

    static ArrayList<Node> bfs(int x, int y, int x1, int y1, boolean[][] obstaculos) {
        boolean[][] memo = new boolean[obstaculos.length][obstaculos[0].length];
        Queue<ArrayList<Node>> q = new LinkedList<ArrayList<Node>>();
        ArrayList<Node> li = new ArrayList<Node>();
        li.add(new Node(x, y));
        q.add(li);
        while (!q.isEmpty()) {
            ArrayList<Node> l = q.poll();
            Node p = l.get(l.size() - 1);
            if (p.x == x1 && p.y == y1) {
                return l;
            }
            int r = (int) (Math.random() * 4);
            for (int i = 0; i < 4; i++) {
                int X = p.x + dib[r][i];
                int Y = p.y + djb[r][i];
                if (X >= 0 && X < memo.length && Y >= 0 && Y < memo[X].length &&
                        !obstaculos[X][Y] && !memo[X][Y]) {
                    memo[X][Y] = true;
                    ArrayList<Node> wl = new ArrayList<Node>(l);
                    wl.add(new Node(X, Y));
                    q.add(wl);
                }
            }
        }
        return new ArrayList<Node>();
    }

    static ArrayList<Node> bfsMovil(int x, int y, int x1, int y1, boolean[][] obtaculos) {
        boolean[][] memo = new boolean[obtaculos.length][obtaculos[0].length];
        Queue<ArrayList<Node>> q = new LinkedList<ArrayList<Node>>();
        ArrayList<Node> li = new ArrayList<Node>();
        li.add(new Node(x, y));
        q.add(li);
        while (!q.isEmpty()) {
            ArrayList<Node> l = q.poll();
            Node p = l.get(l.size() - 1);
            if (p.x == x1 && p.y == y1) {
                return l;
            }
            for (int i = 0; i < 4; i++) {
                int X = p.x + di5[i], Y = p.y + dj5[i];
                if (X >= 0 && X < memo.length && Y >= 0 && Y < memo[X].length &&
                        !obtaculos[X][Y] && !memo[X][Y]) {
                    memo[X][Y] = true;
                    ArrayList<Node> wl = new ArrayList<Node>(l);
                    wl.add(new Node(X, Y));
                    q.add(wl);
                }
            }
        }
        return new ArrayList<Node>();
    }
    int[] ii = {0, -1, -1, -1, 0, 1, 1, 1};
    int[] jj = {-1, -1, 0, 1, 1, 1, 0, -1};

    static int[] whereToGo(int x, int y, int xBot, int yBot, boolean[][] obst, Recorrido espacio) {
        int len = 3;
        Node sel = new Node(x, y);

        int matIndex = 1;
//        if (espacio.estado.equals("ciclico")) {
//            matIndex = 1;
//        }
        int rand = (int) (Math.random() * 4);
        print(diRB[matIndex][rand]);
        for (int i = 0; i < diRB[matIndex][rand].length; i++) {
            int X = x + diRB[matIndex][rand][i];
            int Y = y + djRB[matIndex][rand][i];
//                    System.err.println(X + " " + Y + " ");
            if (X >= 0 && X < obst.length &&
                    Y >= 0 && Y < obst[X].length &&
                    !obst[X][Y] && espacio.contains(X, Y)) {
                ArrayList<Node> list = Robot.bfsMovil(X, Y, xBot, yBot, obst);
//                        System.err.println(i + " " + list);
                if (list.size() >= len) {
                    len = list.size();
                    sel = new Node(X, Y);
                }
            }
        }
        return new int[]{sel.x, sel.y};
    }

    protected static Recorrido getRecorrido(int xBot, int yBot, int xMov, int yMov, boolean obstaculos[][]) {

        ArrayList<Recorrido> recs = new ArrayList<Recorrido>();
        Node curr = new Node(xMov, yMov);
        for (int i = 0; i < 4; i++) {
            int X = xMov + di[i];
            int Y = yMov + dj[i];
            if (X >= 0 && X < obstaculos.length && Y >= 0 &&
                    Y < obstaculos[X].length && !obstaculos[X][Y]) {
                boolean[][] visitados = new boolean[obstaculos.length][obstaculos[0].length];
                Recorrido rec = new Recorrido();
                rec.addNode(curr);
                Node suc = new Node(X, Y, 2, curr);
                rec.addNode(suc);
                visitados[xMov][yMov] = true;
                visitados[X][Y] = true;
                flood(rec, new Node(xMov, yMov), suc, i, obstaculos, visitados);
                /**
                 *
                 */
                if (rec.estado.equals("ciclico")) {
                    rec.totalPoints.removeAll(rec.totalPoints);
                    for (Iterator<Cycle> it = rec.cycles.iterator(); it.hasNext();) {
                        Cycle cycle = it.next();
                        ArrayList<Node> pathBotToNearest = bfs(xBot, yBot, cycle.nearest.x, cycle.nearest.y, obstaculos);
                        if (pathBotToNearest.size() <= cycle.nearestSize) {
                            it.remove();
                        } else {
                            rec.totalPoints.addAll(cycle.wayToNearest);
                            rec.totalPoints.addAll(cycle.cycle);
                            rec.lowestLen = Math.min(rec.lowestLen, cycle.nearestSize);
                        }
                    }
                    if (rec.cycles.size() >= 1) {
                        recs.add(rec);
                    }
                } else {
                    recs.add(rec);
                }
//                recs.add(rec);
            }
        }
        int ix = 0;
        for (Recorrido recorrido : recs) {
            System.err.println(ix++ + " " + recorrido);
        }
        for (Recorrido recorrido : recs) {
            if (recorrido.estado.equals("ciclico")) {
//                if (recorrido.cycles.size() >= 1) {
                return recorrido;
//                }
            }
        }
        for (Iterator<Recorrido> it = recs.iterator(); it.hasNext();) {
            Recorrido recorrido = it.next();
            if (recorrido.totalPoints.contains(new Node(xBot, yBot))) {
                it.remove();
                break;
            }
        }
        Recorrido res = new Recorrido();
        res.addNode(new Node(xMov, yMov, 0, null));
        for (Recorrido recorrido : recs) {
            if (recorrido.lastNodeOfLongestPath.n > res.lastNodeOfLongestPath.n) {
                res = recorrido;
            }
        }
        return res;
    }

    static void flood(Recorrido recorrido, Node start, Node bef, int from, boolean[][] obstaculos, boolean[][] memo) {
        for (int k = 0; k < 4; k++) {
            if ((from + 2) % 4 == k) {
                continue;   // no retroceder
            }
            int X = bef.x + di[k];
            int Y = bef.y + dj[k];
            if (X >= 0 && X < memo.length && Y >= 0 &&
                    Y < memo[X].length && !obstaculos[X][Y]) {
                Node nod = new Node(X, Y, bef.n + 1, bef);
                if (!memo[X][Y]) {
                    memo[X][Y] = true;
                    recorrido.addNode(nod);
                    flood(recorrido, start, nod, k, obstaculos, memo);
                } else {
                    /**
                     **
                     * */
                    ArrayList<Node> cycleArr = new ArrayList<Node>();
                    Node temp = bef;
//                    System.err.println(recorrido);
//                    System.err.println(bef);
//                    System.err.println(nod);
                    boolean cntinue = false;
                    while (!temp.equals(nod)) {
                        cycleArr.add(temp);
                        temp = temp.bef;
                        if (temp == null) {
                            cntinue = true;
                            break;
                        }
                    }
                    if (cntinue) {
                        continue;
                    }

                    boolean joint = false;
                    ArrayList<Node> way = bfs(start.x, start.y, nod.x, nod.y, obstaculos);
                    Cycle cycle = new Cycle(cycleArr, nod, way);
                    cycle.nearestSize = way.size();
                    for (Cycle cycle1 : recorrido.cycles) {
                        if (!Collections.disjoint(cycle1, cycle)) {
                            joint = true;
                            cycle1.cycle.addAll(cycle);
                            if (cycle.nearestSize < cycle1.nearestSize) { // update cycle
                                cycle1.nearest = nod;
                                cycle1.nearestSize = cycle.nearestSize;
                            }
                        }
                    }
                    if (!joint) {
                        recorrido.cycles.add(cycle);
                    }
                    /**
                     *
                     */
                    recorrido.estado = "ciclico";
                }
            }
        }
    }

    public static class Cycle extends ArrayList<Node> {

        ArrayList<Node> cycle;
        Node nearest;
        int nearestSize;
        ArrayList<Node> wayToNearest;
        boolean valid = true;

        public Cycle(ArrayList<Node> cycle, Node nearest, ArrayList<Node> wayToNearest) {
            this.cycle = cycle;
            this.nearest = nearest;
            this.wayToNearest = wayToNearest;
            this.nearestSize = wayToNearest.size();
        }

        void setNearest(Node n) {
        }
    }

    public static class Recorrido {

        ArrayList<Node> totalPoints;
        String estado = "nociclico";
        Node lastNodeOfLongestPath;
        Node nearestNodePathCycled;
        ArrayList<Cycle> cycles;
        int lowestLen = 0;

        public Recorrido() {
            totalPoints = new ArrayList<Node>();
            cycles = new ArrayList<Cycle>();
        }

        @Override
        public String toString() {
            return estado + " " + totalPoints.toString();
        }

        boolean contains(int x, int y) {
            return totalPoints.contains(new Node(x, y));
        }

        void addNode(int x, int y, Node bef) {
            Node nod = new Node(x, y, bef.n + 1, bef);
            addNode(nod);
        }

        void addNode(Node nod) {
            if (lastNodeOfLongestPath == null) {
                lastNodeOfLongestPath = nod;
            } else {
                if (lastNodeOfLongestPath.n < nod.n) {
                    lastNodeOfLongestPath = nod;
                }
            }
            totalPoints.add(nod);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
    }

    private static void print(Object... rs) {
        print("", rs);
    }

    private static void print(String msg, Object... rs) {
        String x = Arrays.deepToString(rs);
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
