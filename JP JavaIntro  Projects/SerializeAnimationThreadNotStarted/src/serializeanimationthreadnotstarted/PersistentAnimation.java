import java.io.*;

public class PersistentAnimation implements Serializable, Runnable {
    
    transient private Thread animator;
    private int animationSpeed;
    
    public PersistentAnimation(int animationSpeed) {
        this.animationSpeed = animationSpeed;
        animator = new Thread(this);
        animator.start();
    }
    
    public void run() {
        System.out.println("PersistentAnimation thread is started");
    }
    private void writeObject(ObjectOutputStream out)throws IOException{
        out.defaultWriteObject();                
    }
    private void readObject(ObjectInputStream in)throws IOException,ClassNotFoundException{
        in.defaultReadObject();
        startAnimation();
    }
    private void startAnimation(){
        animator=new Thread(this);
        animator.start();
    }
}
