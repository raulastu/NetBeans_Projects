public class JavaTwoDimensionArray {
    
    /** Creates a new instance of JavaTwoDimensionArray */
    public JavaTwoDimensionArray() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Declare and create two dimensional int array whose size is 10 by 5
        int[][][] ages = new int[10][5][5];
        
        // Display the number of rows and columns
        System.out.println("ages.length = " + ages.length);
        System.out.println("ages[1].length = " + ages[1].length);
        
        // Display the value of each entry in the array
        for( int i=0; i<ages.length; i++ ){
             System.out.println("\nStarting row " + i);
            for( int j=0; j<ages[i].length; j++ ){
                 for(int k=0; k<ages[i][j].length; k++){
                    ages[i][j][k] = 1000+i+j+ k;
                    System.out.print( ages[i][j][k] + " " );     
                 }                
            }
        }
    }  
}