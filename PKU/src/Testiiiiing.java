
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Testiiiiing {

    class Node {

        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Node)) {
                return false;
            }
            Node b = (Node) obj;
            return b.x == this.x && b.y == this.y;
        }

        @Override
        public int hashCode() {
            return 300 * x + 150 * y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }

    public void solve() {
        Node a = new Node(1, 1);
        Set<Node> hs = new CopyOnWriteArraySet<Node>();
        hs.add(a);
        System.err.println(hs.add(new Node(1, 1)));
        System.err.println(hs);
        System.err.println(hs.contains(a));
//        System.err.println(a.equals(new Node(1, 1)));

        ArrayList<Node> al = new ArrayList<Node>();
        al.add(a);
        System.err.println(al.contains(new Node(1, 1)));

    }

    public static void main(String[] args) {
        new Testiiiiing().solve();
    }
}
