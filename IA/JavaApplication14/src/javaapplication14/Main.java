package javaapplication14;

public class Main {

    public static void main(String[] args) {
        int a = 0;
        int b = 0;
        long array[] = new long[100];
        String cc[] = new String[20];
        array[0] = 0;
        array[1]=1;
        for (int i = 2; i < array.length; i++) {
            array[i] = array[i-2]+array[i-1];
        }

//        for (int i = 0; i < array.length; i++) {
//            System.out.print(" " + array[i]);
//        }
        for(int i=0;i<cc.length/2;i++){
            cc[i]="d";
            
        }
        for(int j=0;j<cc.length;j++)
        {            
            System.out.print(" "+cc[j]);
        }
    }
}
