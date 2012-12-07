
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
/*A common use of the bitwise operators
(shifts with ands to extract values and ors to add values)
is to work with multiple values that have been encoded in one int.
Bit-fields are another way to do this. For example,
let's say you have the following integer variables:
age (range 0-127), gender (range 0-1), height (range 0-128).
These can be packed and unpacked into/from one short
(two-byte integer) like this (or many similar variations).
 * */

public class Packing {

    public static void main(String[] args) throws Exception {
        int age = 10, gender = 1, height = 80;
        short packed_info;
        System.out.println("age:"+age+" gender:"+gender+" height:"+height);
        System.out.println("(age << 1) :"+(age<<1));
        System.out.println("((age << 1) | gender): "+((age<<1)|gender));
        System.out.println("(((age << 1) | gender) << 7): "+(((age<<1)|gender)<<7));
        System.out.println("((((age << 1) | gender) << 7) | height): "+(short) ((((age<<1)|gender)<<7)|height));
        // packing
        packed_info = (short) ((((age<<1)|gender)<<7)|height);
        // unpacking
        height = packed_info&127;
        gender = packed_info>>>7&1;
        age = (packed_info>>>8);
        System.out.println(gender+" ");
        System.out.println("age: "+age);
        System.out.println("height: "+height);

    }

    static String subtract(String b1, String b2) {
        return Integer.toBinaryString(binToDec(b1)-binToDec(b2));
    }

    static String add(String b1, String b2) {
        return Integer.toBinaryString(binToDec(b1)+binToDec(b2));
    }

    static void checkBin(String x) {
        try {
            if (x.replace("1", "").replace("0", "").length()>0) {
                throw new Exception("no es un binario");
            }
        } catch (Exception ex) {
            Logger.getLogger(DecToBin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static String oneC(String x) {
        return x.replace("1", "X").replace("0", "1").replace("X", "0");
    }

    static int binToDec(String x) {
        checkBin(x);
        int res = 0;
        for (int i = 0; i<x.length(); i++) {
            res += pow(2, i)*Character.getNumericValue(x.charAt(x.length()-1-i));
            ;
        }
        return res;
    }

    static void showMaxInt() {
        long max = 0;
        int max2 = 0;
        for (int i = 0; i<31; i++) {
            max += Math.pow(2, i);
            max2 += Math.pow(2, i);
        }
        System.out.println(max);
        System.out.println(max2);
    }

    static void show(int n) {
        System.out.println("dec: "+n+" bin: "+decToBin(n));
        System.out.println("dec: "+n+" bin: "+Integer.toBinaryString(n));
    }

    static String decToBin(int i) {

        if (i==0) {
            return "0";
        }
        String bin = "";
        int re = i;
        while (re>0) {
            bin = re%2+bin;
            re /= 2;
        }
        return bin;
    }
}
