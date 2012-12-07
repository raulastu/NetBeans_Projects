import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.JComponent;

/**
 * Bean with a simple property "title".
 */
public class MyBeanJComponent
        extends JComponent
        implements Serializable {
    private String title;
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle( String title ) {
        this.title = title;
    }
    
    protected void paintComponent( Graphics g ) {
        g.setColor( getForeground() );
        
        int height = g.getFontMetrics().getHeight();
        if ( this.title != null )
            g.drawString(this.title, 0, height );
    }
}