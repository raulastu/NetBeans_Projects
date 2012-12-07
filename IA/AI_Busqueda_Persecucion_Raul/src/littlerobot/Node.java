package littlerobot;

public class Node {

    int x, y;
    int n = 1;
    Node bef;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Node(int x, int y, int n, Node bef) {
        this.x = x;
        this.y = y;
        this.n = n;
        this.bef = bef;
    }

    @Override
    public boolean equals(Object obj) {
        Node o = (Node) obj;
        return o.x == x && o.y == y;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}