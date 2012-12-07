package javaplusprolog;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jpl.Query;

public class Main {

    static String file = "PrologFile.pl";

    public static int suma(int a, int b) {
        String t1 = "consult('" + file + "')";
        Query q1 = new Query(t1);
        q1.hasSolution();
//        System.err.println(t1 + " " + (q1.hasSolution() ? "succeeded" : "failed"));
        String str = "sum(" + a + ", " + b + ", X).";
        Query q2 = new Query(str);
        int result = Integer.parseInt(q2.oneSolution().get("X") + "");
        return result;
    }

    public static boolean pertenece(String a, ArrayList<String> list) {
        String t1 = "consult('" + file + "')";
        Query q1 = new Query(t1);
        q1.hasSolution();
//        System.err.println(t1 + " " + (q1.hasSolution() ? "succeeded" : "failed"));
        String stringList = listToString(list);
        String str = "pertenece(" + a + ", " + stringList + " ).";
        Query q2 = new Query(str);
        return q2.hasSolution();
    }

    ArrayList<String> concatenar(ArrayList<String> a, ArrayList<String> b) {
        String t1 = "consult('" + file + "')";
        Query q1 = new Query(t1);
        q1.hasSolution();
        // extraer elementos de los ArrayList y ponerlos en Formato Prolog #######
        String aList = listToString(a);
        String bList = listToString(b);
        // ######################################################################
        String str = "concatenar(" + aList + ", " + bList + ", X).";
        Query q2 = new Query(str);
        //        q2.allSolutions();
        String result = q2.oneSolution().get("X") + "";
        ArrayList<String> al = parse(result);
        return al;
    }

    public static boolean esPrefijo(ArrayList<String> a, ArrayList<String> b) {
        String t1 = "consult('" + file + "')";
        Query q1 = new Query(t1);
        q1.hasSolution();
        // extraer elementos de los ArrayList y ponerlos en Formato Prolog #######
        String aList = listToString(a);
        String bList = listToString(b);
        // ######################################################################
        String str = "esprefijo(" + aList + "," + bList + ").";
        Query q2 = new Query(str);
        return q2.hasSolution();
    }

    public static boolean esSufijo(ArrayList<String> a, ArrayList<String> b) {
        String t1 = "consult('" + file + "')";
        Query q1 = new Query(t1);
        // extraer elementos de los ArrayList y ponerlos en Formato Prolog #######
        String aList = listToString(a);
        String bList = listToString(b);
        // ######################################################################
        String str = "essufijo(" + aList + "," + bList + ").";
        Query q2 = new Query(str);
        return q2.hasSolution();
    }

    static String listToString(ArrayList<String> list) {
        String res = "[";
        for (String string : list) {
            res += string + ", ";
        }
        res = res.length() >= 2 ? res.substring(0, res.length() - 2) : "";
        return res + "]";
    }

    static ArrayList<String> parse(String prologResult) {
        Pattern pat = Pattern.compile("\\b\\d+|\\w+\\b");
        Matcher mat = pat.matcher(prologResult);
        ArrayList<String> al = new ArrayList<String>();
        while (mat.find()) {
            al.add(mat.group());
        }
        return al;
    }

    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<String>();
        al.add("1");
        al.add("2");
        al.add("4");
        al.add("10");

        //comenzar sum
        System.err.println("suma : " + suma(34, 34));
        //fin sum

        //####### pertenece
        System.err.println(pertenece("11", al));
        // fin pertenece ##################

        // <concatenar>
        ArrayList<String> al2 = new ArrayList<String>();
        al2.add("4");
        al2.add("10");
        ArrayList<String> res = new Main().concatenar(al, al2);
        System.err.println("concatenar :" + res);
        //</concatenar>

        // <es prefijo>
        ArrayList<String> al3 = new ArrayList<String>();
        al3.add("1");
        al3.add("2");
        al3.add("4");
        System.err.println("'al3' Es Prefijo de 'al': " + esPrefijo(al3, al));
        // </es prefijo>

        // <es sufijo>
        System.err.println("'al2' Es Sufijo de 'al': " + esSufijo(al2, al));
    // </es sufijo>
    }
}
