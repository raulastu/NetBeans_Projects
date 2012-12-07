import java.io.Serializable;

public class SimpleBean implements Serializable{
    
    private final String name = "SimpleBean";
    private int size;
    
    public String getName() {
        return this.name;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public void setSize( int size ) {
        this.size = size;
    }
    
}