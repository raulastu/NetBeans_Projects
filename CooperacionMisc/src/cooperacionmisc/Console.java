package cooperacionmisc;
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.*;
public class Console {
	//Crear un string título a partir del nombre de la clase:
	public static String título(Object o) {
		String t = o. getClass ( ) . toString ( ) ;
		// Eliminar la palabra "class":
		if(t. indexOf ("class")!=-1)
			t = t. substring (6) ;
		return t;
	}
	public static void setupClosing(JFrame frame) {
		// La solución de JDK 1.2 como una
		// clase interna anónima:
		frame.addWindowListener(new WindowAdapter(){
			public void windowclosing (WindowEvent e) {
				System.exit(0) ;
			}
			});
			// La solución mejorada del JDK 1.3:
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE) ;
	}
	public static void run(JFrame frame/*, int width, int height*/) {
		setupClosing(frame);
		/*frame.setSize(width, height);*/
		frame.pack();
		frame.setVisible(true) ;
	}
	public static void run(JApplet applet, int width, int height) {
		JFrame frame = new JFrame (título (applet) ) ;
		setupClosing(frame);
		frame. getContentPane ( ) . add (applet) ;
		frame. setSize (width, height) ;
		applet. init ( ) ;
		applet. start ( ) ;
		frame.setVisible(true);
	}
	public static void run(JPanel panel/*, int width, int height*/) {
            System.out.println(panel.getPreferredSize());
		JFrame frame = new JFrame (título(panel) ) ;
		setupClosing(frame) ;
		frame.getContentPane ( ) . add (panel) ;
		//frame.setSize (width, height) ;
		frame.setLayout(new FlowLayout());
		frame.setVisible(true) ;
		frame.setAlwaysOnTop(true);
		frame.pack();
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
} ///:~
		