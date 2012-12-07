package learning;

import java.io.Console;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexTestHarness {

    static String[] topFive(TreeSet<String> tree) {
        Iterator it = tree.iterator();
        String[] top = new String[tree.size() > 5 ? 5 : tree.size()];
        for (int i = 0; i < top.length; i++) {
            top[i] = tree.first() + "";
            tree.remove(tree.first());
        }
        return top;
    }


    public static void main(String[] args) {
        TreeSet<String> tree = new TreeSet<String>();

        String s = "a...b...c...b...a";
        Pattern pat1 = Pattern.compile("(?i)\\b" + s.replace("...", "\\b.*?\\b") + "\\b");
        Matcher m = pat1.matcher(input);
        for (int i = 0; m.find(i); i=m.start()+1) {
            String str = zeroF(m.group().length()) + " " + zeroF(m.start()) + " [" + m.group() + "]";
            System.out.println(tree.add(str) + str);
        }
        pat1 = Pattern.compile("(?i)\\b" + s.replace("...", "\\b.+\\b") + "\\b");
        m = pat1.matcher(input);
        for (int i = 0; m.find(i); i=m.start()+1) {
            String str = zeroF(m.group().length()) + " " + zeroF(m.start()) + " [" + m.group() + "]";
            System.out.println(tree.add(str) + str);
        }
        for (int i =input.length();i>=0; i--) {
            m = pat1.matcher(input.substring(0, i));
            System.out.println(" gcount"+m.groupCount());
            while(m.find()){
                String str = zeroF(m.group().length()) + " " + zeroF(m.start()) + " [" + m.group() + "]";
                System.out.println(tree.add(str) + str);
            }            
        }
        System.out.println(tree);
        String[] res = topFive(tree);
        System.out.println(Arrays.toString(res));
    }
    static String zeroF(int n){
        return n<10?"0"+n:n+"";
    }
    static String input="A B C b a A C B B B B c B C A b B b a A a A b c B ";
}
