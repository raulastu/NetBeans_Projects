public class JavaArray {
    
    /** Creates a new instance of JavaArray */
    public JavaArray() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Declare and create int array whose size is 10
        int[] ages = new int[10];
        
        // Display the value of each entry in the array
        for(int i=0;i<ages.length;i++){
            ages[i]=100+i;
        }            
        for( int i=0; i<ages.length; i++ ){
            System.out.print( ages[i] );
        }
    }
    
}
