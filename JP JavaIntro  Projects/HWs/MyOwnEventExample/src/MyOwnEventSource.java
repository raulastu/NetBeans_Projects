import java.io.Serializable;
import java.util.*;

public class MyOwnEventSource implements Serializable{
    
   
    ArrayList a1=new ArrayList();
    
     /** Creates a new instance of MyOwnEventSource */
    public MyOwnEventSource() {
    }
    public void addMyOwnListenerImpl(MyOwnListenerImpl impl){
        a1.add(impl);
    }
    public void removeMyOwnListenerImpl(MyOwnListenerImpl impl){
        a1.remove(impl);
    }
    public void triggerSomethingEvent(String eventName){      
        
        for (Object item: a1){            
            MyOwnEVentListenerInterface m1 = (MyOwnEVentListenerInterface)item;
            m1.algoPaso(new MyOwnEvent(this,eventName));
        }
    }
}
