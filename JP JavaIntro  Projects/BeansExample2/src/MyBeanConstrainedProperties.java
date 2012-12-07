import java.io.Serializable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * Bean with constrained properties.
 */
public class MyBeanConstrainedProperties
        extends JComponent
        implements Serializable {
    private String title;
    private String[] lines = new String[10];
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport( this );
    private final VetoableChangeSupport vcs = new VetoableChangeSupport( this );
    
    public String getTitle() {
        return this.title;
    }
    /**
     * This method was modified to throw the PropertyVetoException
     * if some vetoable listeners reject the new title value
     */
    public void setTitle( String title )
    throws PropertyVetoException {
        String old = this.title;
        this.vcs.fireVetoableChange( "title", old, title );
        this.title = title;
        this.pcs.firePropertyChange( "title", old, title );
    }
    
    public String[] getLines() {
        return this.lines.clone();
    }
    
    public String getLines( int index ) {
        return this.lines[index];
    }
    /**
     * This method throws the PropertyVetoException
     * if some vetoable listeners reject the new lines value
     */
    public void setLines( String[] lines )
    throws PropertyVetoException {
        String[] old = this.lines;
        this.vcs.fireVetoableChange( "lines", old, lines );
        this.lines = lines;
        this.pcs.firePropertyChange( "lines", old, lines );
    }
    
    public void setLines( int index, String line )
    throws PropertyVetoException {
        String old = this.lines[index];
        this.vcs.fireVetoableChange( "lines", old, line );
        this.lines[index] = line;
        this.pcs.fireIndexedPropertyChange( "lines", index, old, line );
    }
    
    public void addPropertyChangeListener( PropertyChangeListener listener ) {
        this.pcs.addPropertyChangeListener( listener );
    }
    
    public void removePropertyChangeListener( PropertyChangeListener listener ) {
        this.pcs.removePropertyChangeListener( listener );
    }
    /**
     * Registration of the VetoableChangeListener
     */
    public void addVetoableChangeListener( VetoableChangeListener listener ) {
        this.vcs.addVetoableChangeListener( listener );
    }
    
    public void removeVetoableChangeListener( VetoableChangeListener listener ) {
        this.vcs.addVetoableChangeListener( listener );
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