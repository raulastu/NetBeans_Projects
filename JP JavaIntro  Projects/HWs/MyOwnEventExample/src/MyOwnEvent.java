import java.util.EventObject;
import java.util.Date;
public class MyOwnEvent extends EventObject{
    
    
    private String eventName;
    
    /** Creates a new instance of MyOwnEvent */
    public MyOwnEvent(Object source) {
        super(source);
    }
    public MyOwnEvent(Object source,String eventName) {
        super(source);
        this.eventName=eventName;
    }       
    public String getMyOwnEventName() {
        return eventName;
    }
    public void setMyEventName(String eventName) {
        this.eventName = eventName;
    }   
     public Date getCurrentTime(){        
        return new Date();
    }
    
}
