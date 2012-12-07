public class MyEventListenerImpl implements MyEventListenerInterface {
    
    public void somethingHappened(MyEvent event ){
        System.out.println("Event " + event.getMyEventName() + 
                " Occurred at " + new java.util.Date());
    }

}