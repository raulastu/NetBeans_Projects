import java.io.*;

public class FilterInputOutputStream {
    
    public static void main(String[] args) throws IOException {

       Adler32 inChecker = new Adler32();
       Adler32 outChecker = new Adler32();
       ChangeToUpperCaseInputStream in = null;
       ChangeToUpperCaseOutputStream out = null;

       try {
           in = new ChangeToUpperCaseInputStream(new FileInputStream("farrago.txt"),inChecker);
           out = new ChangeToUpperCaseOutputStream(new FileOutputStream("outagain.txt"),outChecker);
       } catch (FileNotFoundException e) {
           System.err.println("CheckedIOTest: " + e);
           System.exit(-1);
       } catch (IOException e) {
           System.err.println("CheckedIOTest: " + e);
           System.exit(-1);
       }

       int c;       
       while ((c = in.read()) != -1){
           System.out.print(Character.toChars(c)); 
           out.write(c);            
       }
       System.out.println("Input stream check sum: " +
			  inChecker.getValue());
       System.out.println("Output stream check sum: " +
			  outChecker.getValue());    
        
       in.close();
       out.close();     
       
       /** Comprobation*/       
       System.out.println();
       FileReader test=new FileReader(new File("outagain.txt"));       
       while ((c = test.read()) != -1){
           System.out.print(Character.toChars(c)); 
       }
       test.close();
       
    }
}
