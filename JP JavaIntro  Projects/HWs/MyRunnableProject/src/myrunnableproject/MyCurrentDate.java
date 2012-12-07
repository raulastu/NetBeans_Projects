
package myrunnableproject;
import java.util.Date;

public class MyCurrentDate implements Runnable{
    Thread t;
    String name;
    /*MyDate date;*/
    public MyCurrentDate(String name/*,MyDate date*/) {
        t=new Thread(this,name);
        this.name=name;
        t.start();
        /*this.date=date;*/
    }

    public void run() {                    
            showNameAndDate(name);        
    }
    public synchronized static void showNameAndDate(String name){     
        for(int i=0;i<10;i++){
            try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } 
           System.out.println("Thread "+ name +": "+new Date());               
        }        
    } 
      
}
