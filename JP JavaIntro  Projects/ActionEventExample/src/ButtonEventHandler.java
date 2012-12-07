import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ButtonEventHandler implements ActionListener {
    /**
     * Component that will contain messages about
     * events generated.
     */
    private JTextArea output;
    /**
     * Creates an ActionListener that will put messages in
     * JTextArea everytime event received.
     */
    public ButtonEventHandler( JTextArea output ) {
        this.output = output;
    }
    
    /**
     * When receives action event notification, appends
     * message to the JTextArea passed into the constructor.
     */
    public void actionPerformed( ActionEvent event ) {
        this.output.append( "Action occurred in the Button Handler: " + event + '\n' );
    }
}
 
