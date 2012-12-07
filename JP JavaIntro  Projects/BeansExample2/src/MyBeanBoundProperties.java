import java.awt.Graphics;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.swing.JComponent;

/**
 * Bean with bound properties.
 */
public class MyBeanBoundProperties
        extends JComponent
        implements Serializable {
    
    private String title;
    private String[] lines = new String[10];
    
    private final PropertyChangeSupport pcs 
                    = new PropertyChangeSupport( this );
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle( String title ) {
        String old = this.title;
        this.title = title;
        this.pcs.firePropertyChange( "title", old, title );
    }
    
    public String[] getLines() {
        return this.lines.clone();
    }
    
    public String getLines( int index ) {
        return this.lines[index];
    }
    
    public void setLines( String[] lines ) {
        String[] old = this.lines;
        this.lines = lines;
        this.pcs.firePropertyChange( "lines", old, lines );
    }
    
    public void setLines( int index, String line ) {
        String old = this.lines[index];
        this.lines[index] = line;
        this.pcs.fireIndexedPropertyChange( "lines", index, old, lines );
    }
    
    public void addPropertyChangeListener( PropertyChangeListener listener ) {
        this.pcs.addPropertyChangeListener( listener );
    }
    
    public void removePropertyChangeListener( PropertyChangeListener listener ) {
        this.pcs.removePropertyChangeListener( listener );
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