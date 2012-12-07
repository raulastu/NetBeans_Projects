package biblioteca;



import java.awt.*;
import javax.swing.*;

/**
 * La clase <code>LE</code> facilita la Lectura y Escritura de datos a trav�s
 * de cuadros de di�logos.
 * 
 * @author Ing. Juan Jos� Flores Cueto
 * @version 2.2
 * @Date Octubre 2006
 * @version 2.1
 * @Date Agosto 2006
 * 
 * @author Ing. Jessica Paulino Torre
 * @author Ing. Gissela Guzman Mariluz
 * @version 1.0
 * @Date Enero 2004
 * 
 * Clase LE utilizada con fines acad�micos.
 * 
 */


public class LE {

	/**
	 * Muestra un cuadro de di�logo solicitando el ingreso de un caracter.
	 * 
	 * @param mensaje
	 *            especifica la solicitud del caracter a ingresar.
	 */
	public static char leerChar(String mensaje) {
		String cadena;
		do {
			cadena = JOptionPane.showInputDialog(mensaje);
			if (cadena == null)
				mostrarError("Error... no se ha ingresado valor");
			if (cadena.length() > 1)
				mostrarError("Error... debe ingresar s�lo un caracter");
		} while (cadena == null || cadena.length() > 1);
		char c = cadena.charAt(0);
		return c;
	}

	/**
	 * Muestra un cuadro de di�logo solicitando el ingreso de un valor real
	 * double.
	 * 
	 * @param mensaje
	 *            especifica la solicitud del valor a ingresar.
	 * 
	 */
	public static double leerDouble(String mensaje) {
		String cadena = leerString(mensaje);
		try {
			return Double.parseDouble(cadena);
		} catch (NumberFormatException e) {
			mostrarError("Error... debe ingresar un n�mero ");
			return leerDouble(mensaje);
		}
	}

	/**
	 * Muestra un cuadro de di�logo solicitando el ingreso de un valor real
	 * float.
	 * 
	 * @param mensaje
	 *            especifica la solicitud del valor a ingresar.
	 */
	public static float leerFloat(String mensaje) {
		String cadena = leerString(mensaje);
		try {
			return Float.parseFloat(cadena);
		} catch (NumberFormatException e) {
			mostrarError("Error... debe ingresar un n�mero ");
			return leerFloat(mensaje);
		}
	}

	/**
	 * Muestra un cuadro de di�logo solicitando el ingreso de un valor entero
	 * int.
	 * 
	 * @param mensaje
	 *            especifica la solicitud del valor a ingresar.
	 */
	public static int leerInt(String mensaje) {
		String cadena = leerString(mensaje);
		try {
			return Integer.parseInt(cadena);
		} catch (NumberFormatException e) {
			mostrarError("Error... debe ingresar un n�mero ");
			return leerInt(mensaje);
		}
	}

	/**
	 * Muestra un cuadro de di�logo solicitando el ingreso de un valor entero
	 * long.
	 * 
	 * @param mensaje
	 *            especifica la solicitud del valor a ingresar.
	 */
	public static long leerLong(String mensaje) {
		String cadena = leerString(mensaje);
		try {
			return Long.parseLong(cadena);
		} catch (NumberFormatException e) {
			mostrarError("Error... debe ingresar un n�mero ");
			return leerLong(mensaje);
		}
	}

	/**
	 * Muestra un cuadro de di�logo solicitando el ingreso de una cadena.
	 * 
	 * @param mensaje
	 *            especifica la solicitud de la cadena a ingresar.
	 */
	public static String leerString(String mensaje) {
		String cadena;
		do {
			cadena = JOptionPane.showInputDialog(mensaje);
			if (cadena == null)
				mostrarError("Error... no se ha ingresado valor");
		} while (cadena == null);
		return cadena;
	}

	/**
	 * Muestra un cuadro de di�logo solicitando el ingreso de una clave seceta
	 * 
	 * M�todo leerClave. El m�todo leerClave de la clase LE es un m�todo
	 * incorporado en la versi�n 2.0 ha sido creado sobrecargado. Puede utilizar
	 * tres versiones. El m�todo que no recibe par�metros coloca un titulo y un
	 * mensaje por default. el m�todo que recibe un par�metro muestra el titulo
	 * especificado y un mensaje por default y el m�todo que recibe dos
	 * par�metro muestra el t�tulo y el mensaje especificado. Observe como se
	 * reusa el m�todo.
	 * 
	 * @return un string con la clave ingresada. Si cierra la ventana o cancela
	 *         retorna una cadena vacia
	 * 
	 * 
	 */

	public static String leerClave() {
		return leerClave("", "");
	}

	public static String leerClave(String titulo) {
		return leerClave(titulo, "");
	}

	public static String leerClave(String tituloVentana, String mensaje) {
		if (tituloVentana.equalsIgnoreCase("")) {
			tituloVentana = "Bienvenido";
		}
		if (mensaje.equalsIgnoreCase("")) {
			mensaje = "Ingrese su clave";
		}

		JPanel panel = new JPanel();
		JLabel etiqueta = new JLabel(mensaje);
		JPasswordField jpfClave = new JPasswordField(10);
		panel.add(etiqueta);
		panel.add(jpfClave);

		Object[] nombres = { "Clave ok", "Cancela clave" };
		int rpta = JOptionPane.showOptionDialog(null, panel, tituloVentana,
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				nombres, nombres[0]);

		if (rpta != 0) {
			return "";
		} else {
			return String.valueOf(jpfClave.getPassword());
		}
	}

	/**
	 * Muestra un cuadro de di�logo con alg�n tipo de mensaje. No presenta
	 * �cono.
	 * 
	 * @param mensaje
	 *            es el mensaje a mostrar.
	 */
	public static void mostrarResultado(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Resultado",
				JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Muestra un cuadro de di�logo con una advertencia especificada en el
	 * argumento.
	 * 
	 * @param mensaje
	 *            es la advertencia a mostrar.
	 */
	public static void mostrarAdvertencia(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Advertencia",
				JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Muestra un cuadro de di�logo con un mensaje de error especificado en el
	 * argumento.
	 * 
	 * @param mensaje
	 *            es el error a mostrar.
	 */
	public static void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Muestra un cuadro de di�logo con un mensaje de informaci�n especificado
	 * en el argumento.
	 * 
	 * M�todo mostrarInformacion: M�todo sobrecargado, creandose el segundo
	 * m�todo. El m�todo mostrarInformaci�n de la clase LE ha sido sobrecargado.
	 * Ahora Ud. puede utilizar dos versiones. El m�todo que recibe un par�metro
	 * muestra la informaci�n en forma tradicional. El m�todo que recibe cinco
	 * par�metros tienen la posibilidad de incluir un t�tulo en la ventana, un
	 * texto de titulo al interior del cuadro, mostrar la informaci�n en una
	 * �rea de texto con la posibilidad de hacer scroll cuando se necesita
	 * mostrar gran cantidad de informaci�n, cambiar la etiqueta del bot�n que
	 * se muestra en la parte inferior del cuadro y cambiar el color de fondo
	 * del �rea de texto.
	 */

	public static void mostrarInformacion(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Informaci�n",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void mostrarInformacion(String tituloVentana,
			String tituloInterno, String mensaje, String nombreBoton, Color color) {

		JPanel panel = new JPanel();
		panel.setLayout(new java.awt.BorderLayout(15,15));
		
		JLabel cabecera = new JLabel("");
		
		JTextArea muestraInformacion = new JTextArea(10, 30);
		muestraInformacion.setEditable(false);

		if (tituloVentana == null)
			tituloVentana = "Informaci�n";
		if (tituloInterno != null)
			cabecera.setText(tituloInterno);
		if (mensaje == null)
			mensaje = "No hay datos para visualizar";
		if (color != null)
			muestraInformacion.setBackground(color);

		muestraInformacion.append(mensaje);
		JScrollPane desplazaInformacion = new JScrollPane(muestraInformacion);
		
		panel.add( "North",cabecera);
        panel.add( "Center", new Canvas());
        panel.add( "South",desplazaInformacion);

		if (nombreBoton != null) {
			Object[] nombres = {nombreBoton};
			JOptionPane.showOptionDialog(null, panel, tituloVentana,
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, nombres, nombres[0]);
		} else {
			JOptionPane.showMessageDialog(null, panel, tituloVentana,
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Muestra un cuadro de di�logo con una pregunta especificada en el
	 * argumento.
	 * 
	 * @param mensaje
	 *            es la pregunta a mostrar.
	 */
	public static void mostrarPregunta(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Pregunta",
				JOptionPane.QUESTION_MESSAGE);
	}

	/**
	 * Muestra un cuadro de di�logo con una pregunta especificada en el
	 * argumento y 2 opciones de respuesta a elegir.
	 * 
	 * @param mensaje
	 *            es la pregunta a mostrar.
	 * @return el entero 0 o 1 si se presion� el bot�n con la opci�n YES o NO
	 *         respectivamente. Si se cierra este cuadro de di�logo sin
	 *         presionar alg�n bot�n, retorna el entero -1.
	 */
	public static int mostrarPreguntaOpcion2(String mensaje) {
		return JOptionPane.showOptionDialog(null, mensaje, "Pregunta",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);
	}

	/**
	 * Muestra un cuadro de di�logo con una pregunta especificada en el
	 * argumento y 3 opciones de respuesta a elegir.
	 * 
	 * @param mensaje
	 *            es la pregunta a mostrar.
	 * @return el entero 0, 1 o 2 si se presion� el bot�n con la opci�n YES, NO
	 *         o CANCEL respectivamente. Si se cierra este cuadro de di�logo sin
	 *         presionar alg�n bot�n, retorna el entero -1.
	 */
	public static int mostrarPreguntaOpcion3(String mensaje) {
		return JOptionPane.showOptionDialog(null, mensaje, "Pregunta",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, null, null);
	}

	/**
	 * Muestra un cuadro de di�logo con un combo para selecciona una opci�n de
	 * m�ltiples opciones.
	 * 
	 * @param opciones
	 *         es un arreglo de cadenas con las opciones a mostrar.
	 * @return un string con la opci�n seleccionada. Si cierra la ventana o
	 *         cancela retorna una cadena vacia.
	 * 
	 */
	public static String seleccionarOpcion(String tituloVentana,
		String mensaje, String[] opciones) {
		String rpta = (String) JOptionPane.showInputDialog(null, mensaje,
				tituloVentana, JOptionPane.QUESTION_MESSAGE, null, opciones,
				opciones[0]);
		if (rpta == null) {
			rpta = "";
		}
		return rpta;
	}

	/**
	 * Muestra un cuadro de di�logo con un combo para selecciona una opci�n de
	 * m�ltiples opciones.
	 * 
	 * @param opciones
	 *         es un arreglo de cadenas con las opciones a mostrar.
	 * @return un entero con la posici�n de la opci�n seleccionada. Si cierra 
	 *         la ventana o cancela retorna -1.
	 * 
	 */
	public static int seleccionarOpcionIndex(String tituloVentana,
		String mensaje, String[] opciones) {
		String rpta = (String) JOptionPane.showInputDialog(null, mensaje,
				tituloVentana, JOptionPane.QUESTION_MESSAGE, null, opciones,
				opciones[0]);
		if (rpta != null) {
			for (int i=0; i<opciones.length; i++) {
				if (opciones[i] == rpta) {
					return i;
				}
			}
		}	
		return -1;
	}
	
}
