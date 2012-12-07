public class OwnCommandLineArguments {
    
    /** Creates a new instance of OwnCommandLineArguments */
    public OwnCommandLineArguments() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length<6 ||args.length>12){
            System.out.println("Enter nro of members between 3-6 (name age)");
            System.exit(0);
        }
        int sum=0;
        int avg=0;
        for(int i=1;i<args.length;i=i+2){
            sum=sum+Integer.parseInt(args[i]);
        }
        avg=sum/(args.length/2);
        System.out.println(avg);
        
    }
    
}
