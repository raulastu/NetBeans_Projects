public class MyEventExample {
    
    public static void main(String[] args) {
        
        // Create an event source
        MyEventSource s1 = new MyEventSource();
        
        // Register an event listener to the event source
        s1.addMyEventListener( new MyEventListenerImpl() );
       
        
        // Triger an event
        s1.triggerSomethingEvent("I won the lottery!");
        
        // Trigger another event
        s1.triggerSomethingEvent("She said Yes!");
        
    }
    
}