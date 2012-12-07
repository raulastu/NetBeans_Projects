
public class MyOwnListenerImpl implements MyOwnEVentListenerInterface{
    
    /** Creates a new instance of MyOwnListenerImpl */
    public void algoPaso(MyOwnEvent event){
        System.out.println(event.getMyOwnEventName() + " Ocurred " +
                event.getCurrentTime());        
    }    
}
