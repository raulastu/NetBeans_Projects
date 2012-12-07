import java.io.Serializable;

public class MyBean  implements Serializable {
    
    /** Creates a new instance of MyBean */
    public MyBean() {
    }
    
    /**
     * Holds value of property title.
     */
    private String title;
    
    /**
     * Getter for property title.
     * @return Value of property title.
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * Setter for property title.
     * @param title New value of property title.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
}
