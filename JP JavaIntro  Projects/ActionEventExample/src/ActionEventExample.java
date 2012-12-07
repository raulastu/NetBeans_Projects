import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class ActionEventExample {
    
    public static void main(String[] args) {
        
        JFrame frame = new JFrame( "Button Handler" );
        JTextArea area = new JTextArea( 6, 80 );
        JButton button = new JButton( "Fire Event" );
        
        // Register an ActionListener object to the event source
        button.addActionListener( new ButtonEventHandler( area ) );
        
        frame.add( button, BorderLayout.NORTH );
        frame.add( area, BorderLayout.CENTER );
        frame.pack();
        frame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        frame.setLocationRelativeTo( null );
        frame.setVisible( true );
    }
    
}
