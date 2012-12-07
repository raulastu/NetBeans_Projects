import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.JComponent;

/**
 * Bean with a simple property "title"
 * and an indexed property "lines".
 */
public class MyBeanIndexedProperties
        extends JComponent
        implements Serializable {
    
    private String title;
    private String[] lines = new String[10];
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle( String title ) {
        this.title = title;
    }
    
    public String[] getLines() {
        return this.lines.clone();
    }
    
    public String getLines( int index ) {
        return this.lines[index];
    }
    
    public void setLines( String[] lines ) {
        this.lines = lines;
    }
    
    public void setLines( int index, String line ) {
        this.lines[index] = line;
    }
    
    protected void paintComponent( Graphics g ) {
        g.setColor( getForeground() );
        
        int height = g.getFontMetrics().getHeight();
        paintString( g, this.title, height );
        
        if ( this.lines != null ) {
            int step = height;
            for ( String line : this.lines )
                paintString( g, line, height += step );
        }
    }
    
    private void paintString( Graphics g, String str, int height ) {
        if ( str != null )
            g.drawString( str, 0, height );
    }
}