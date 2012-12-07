/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class MultiplyingDividing {

    public static void main(String[] args) throws Exception {
        long a = System.currentTimeMillis();
        for (int i = 0; i<99999999; i++) {
            int x = i/2;
        }
        System.out.println(System.currentTimeMillis()-a);
        a = System.currentTimeMillis();
        for (int i = 0; i<99999999; i++) {
            int x = i>>1;
        }
        System.out.println(System.currentTimeMillis()-a);
    }
}
