
public class MyOwnEventExample {
    
    public MyOwnEventExample() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyOwnEventSource eventSource=new MyOwnEventSource();
        eventSource.addMyOwnListenerImpl(new MyOwnListenerImpl());
        
        eventSource.triggerSomethingEvent("OMG");
        eventSource.triggerSomethingEvent("I <3 Sang");
        eventSource.triggerSomethingEvent("xD");
    }
    
}
