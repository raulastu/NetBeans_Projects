
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
public class InsertSort {
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
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine());
//        int c=0;
//        int [] s = new int[n];
//        while(c++<n){
//            s[c]=Integer.parseInt(br.readLine());
//        }
        int [] xx= {1,2,3,10,11,20,3};

        for (int i = 0; i < xx.length; i++) {
            left(i,xx);
        }
        print(xx);
//        for (int i = 0; i < s.length; i++) {
//            for (int j = 0; j <=i; j++) {
//                if(s[j]>s[i]){
//                    
//                }
//            }
//        }
    }
}
