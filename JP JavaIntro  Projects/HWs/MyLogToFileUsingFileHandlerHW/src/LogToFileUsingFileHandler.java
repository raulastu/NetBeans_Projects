import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogToFileUsingFileHandler {
    
    private static Logger logger = Logger.getLogger("loggerdemo");
    
    public static void main(String[] args) throws Exception {
        
        // Log to a file 
        logger.addHandler(new FileHandler("MyReallyOwnLog.xml"));
        logger.addHandler(new FileHandler("myOwnFileHW.txt"));
        
        // Log a INFO tracing message
        logger.info("doing stuff");
        
        try{
            System.out.println(3/0);
        } catch(Exception e){
            logger.log(Level.SEVERE,"dividing by 0");
        }
        
        logger.info("done");
    }
    
}
