import java.util.EventObject;

public class MyEvent extends EventObject {
    
    /** Creates a new instance of MyEvent */
    public MyEvent(Object source) {
        super(source);
    }
    
    public MyEvent(Object source, String myEventName) {
        super(source);
        this.myEventName = myEventName;
    }
    
    private String myEventName;

    public String getMyEventName() {
        return myEventName;
    }

    public void setMyEventName(String myEventName) {
        this.myEventName = myEventName;
    }
    
}
