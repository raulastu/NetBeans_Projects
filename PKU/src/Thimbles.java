
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rcuser
 */
public class Thimbles {
//    public static int go(int i, int []s){
//
//    }
     private static void print(Object... rs) {
        System.err.println(Arrays.deepToString(rs));
    }
    public static void left(int i, int[]s){
        int x = s[i];
        for (int j = 0; j <i; j++) {
            if(s[j]>x){
                for (int k = i; k >j; k--) {
                    s[k]=s[k-1];
                }
                s[j]=x;
                break;
            }
        }
    }
    public static void right(int i, int[]s){
        int x = s[i];
        for (int j = i+1; j <s.length; j++) {
            if(s[j]>x){
                for (int k = i; k >j; k--) {
                    s[k]=s[k-1];
                }
                s[j]=x;
                break;
            }
        }
    }
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int c=0;
        String [] s = new String[n];
        while(c<n){
            s[c++]=br.readLine();
        }
        String []a = {"1","",""};
        for (int i = 0; i < s.length; i++) {
            String[]xx = s[i].split("-");
            int p1=Integer.parseInt((xx[0]));
            int p2=Integer.parseInt(xx[1]);

            String t = a[p1-1];
            a[p1-1]=a[p2-1];
            a[p2-1]=t;
        }
        for (int i = 0; i < a.length; i++) {
            if(a[i].equals("1"))
                System.out.println(i+1);
        }
//        for (int i = 0; i < s.length; i++) {
//            for (int j = 0; j <=i; j++) {
//                if(s[j]>s[i]){
//
//                }
//            }
//        }
    }
}
