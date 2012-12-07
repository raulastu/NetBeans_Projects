import java.util.*;

public class SetExample {
  public static void main(String args[]) {
    Set set = new HashSet();
    set.add("Bernadine");
    set.add("Elizabeth");
    set.add("Gene");
    set.add("Elizabeth");
    set.add("Clara");
    System.out.println(set);
    Set sortedSet = new TreeSet(set);
    System.out.println(sortedSet);
  }
}
