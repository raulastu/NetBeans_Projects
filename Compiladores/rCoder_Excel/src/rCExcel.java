
import funciones.Funcion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;

public class rCExcel extends JFrame {

    static int ROWS = 15;
    static int COLS = 8;
    private JPanel panContRowHeader;
    private JPanel panContColHeader_Celdas;
    private JPanel panContCorner_RowHeaders;
    private JPanel panContColHeader;
    private JPanel panCeldas;
    private JPanel panelCont;
    private JPanel panelFuncion;
    private JFormattedTextField[][] celdas;
    private JFormattedTextField functionCelda = new JFormattedTextField();
    private JLabel labFunction = new JLabel("Funcion: ");
    private JLabel[] encabezadoDeCols;
    private JLabel[] encabezadoDeRows;
    private JLabel corner = new JLabel("*");
    static protected String[] letras = new String[COLS];
    HashMap<JFormattedTextField, String[]> mapCeldas = new HashMap<JFormattedTextField, String[]>();
    JPopupMenu popupMenu = new JPopupMenu("Funciones");
    boolean isChanging = false;
    boolean enFormula = false;
    boolean estaEditando = false;
    String currText = null;
    ImageIcon icon = new ImageIcon("src/images/logoFuncion.gif");
    JMenuItem[] menufuncion;
    String funcionSelected;

    static {
        for (int i = 0; i < letras.length; i++) {
            String letra = String.valueOf(Character.toChars(i + 65));
            letras[i] = letra;
        }
    }

    public rCExcel() throws Exception {
        super("Basic Excel by rC");
        panContColHeader_Celdas = new JPanel(new BorderLayout());
        panContCorner_RowHeaders = new JPanel(new BorderLayout());
        panContRowHeader = new JPanel(new GridLayout(ROWS, 1), true);
        panContColHeader = new JPanel(new GridLayout(1, COLS));
        encabezadoDeCols = new JLabel[COLS];
        panCeldas = new JPanel(new GridLayout(ROWS, COLS));
        panelCont = new JPanel(new BorderLayout());
        encabezadoDeRows = new JLabel[ROWS];
        panelFuncion = new JPanel(new FlowLayout());
        celdas = new JFormattedTextField[ROWS][COLS];

        Funcion[] funciones = new Formula().getFunctions();
        menufuncion = new JMenuItem[funciones.length];
        for (int i = 0; i < funciones.length; i++) {
            menufuncion[i] = new JMenuItem(Formula.nombreFuncion(funciones[i]), icon);
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(0);
            menufuncion[i].setBackground(Color.white);
            menufuncion[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        JMenuItem source = (JMenuItem) e.getSource();
                        JPopupMenu menu = (JPopupMenu) source.getParent();
                        JFormattedTextField textField = (JFormattedTextField) menu.getInvoker();
                        textField.getDocument().insertString(textField.getText().length(), source.getText() + "(", null);
                    } catch (BadLocationException ex) {
                        Logger.getLogger(rCExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            menufuncion[i].setToolTipText(funciones[i].getDescripcion());
        //popupMenu.add(menufuncion[i]);
        }
        popupMenu.setVisible(false);
        popupMenu.setBorder(new LineBorder(Color.black, 1, true));


        for (int i = 0; i < encabezadoDeCols.length; i++) {
            encabezadoDeCols[i] = new JLabel(letras[i]);
            encabezadoDeCols[i].setHorizontalAlignment(JLabel.CENTER);
            encabezadoDeCols[i].setBorder(BorderFactory.createEtchedBorder(1));
            panContColHeader.add(encabezadoDeCols[i]);
        }

        for (int i = 0; i < ROWS; i++) {
            encabezadoDeRows[i] = new JLabel(i + 1 + "");
            encabezadoDeRows[i].setHorizontalAlignment(JLabel.CENTER);
            encabezadoDeRows[i].setBorder(BorderFactory.createEtchedBorder(1));
            panContRowHeader.add(encabezadoDeRows[i]);
            for (int j = 0; j < COLS; j++) {
                celdas[i][j] = new JFormattedTextField();
                mapCeldas.put(celdas[i][j], new String[]{"", ""});
                setDefaultApareance(celdas[i][j]);
                setDefaultBorder(celdas[i][j]);
                agregarListeners(celdas[i][j]);
                panCeldas.add(celdas[i][j]);
            }
        }

        panContColHeader_Celdas.add(panContColHeader, BorderLayout.NORTH);
        panContColHeader_Celdas.add(panCeldas, BorderLayout.CENTER);
        corner.setBorder(BorderFactory.createEtchedBorder(1));

        panContCorner_RowHeaders.add(corner, BorderLayout.NORTH);
        panContCorner_RowHeaders.add(panContRowHeader, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        functionCelda.setColumns(40);

        panelFuncion.add(labFunction);
        panelFuncion.add(functionCelda);


        panelCont.add(panContCorner_RowHeaders, BorderLayout.WEST);
        panelCont.add(panContColHeader_Celdas, BorderLayout.CENTER);
        setSize(new Dimension(700, 500));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelFuncion, BorderLayout.NORTH);
        getContentPane().add(panelCont, BorderLayout.CENTER);
        //setRandomValues();
    }

    private void agregarListeners(JFormattedTextField celda) {
        celda.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JFormattedTextField source = (JFormattedTextField) e.getSource();
                if (source.isEditable()) {
                    actualizarMap(source);
                    procesarTexto(source);
                    mostrarValEnCelda(source);
                    mostrarBodyEnFuncionCelda(source);
                    //System.out.println("ActiON");
                    setDefaultApareance(source);
                    setFocusBorder(source);
                    isChanging = false;
                }
            }
        });

        celda.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JFormattedTextField source = (JFormattedTextField) e.getSource();
                if (e.getClickCount() == 2) {
                    setEditable(source);
                    //source.setCaretColor(Color.black);
                    //source.setBackground(Color.white);
                    if (mapCeldas.get(source) != null) {
                        source.setText(mapCeldas.get(source)[1]);
                    }
                    source.selectAll();
                }
            }
        });

        celda.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                JFormattedTextField source = (JFormattedTextField) e.getSource();
                functionCelda.setText(mapCeldas.get(source)[1]);
                setFocusBorder(source);
                isChanging = false;
                estaEditando = true;
                //System.out.println("gain" + source.isEditable() + isChanging);
            //popupMenu.setVisible(false);
            }

            public void focusLost(FocusEvent e) {
                JFormattedTextField source = (JFormattedTextField) e.getSource();
                //source.setCaretColor(Color.white);
                //enFormula = false;
                //System.out.println("FocusLost" + source.isEditable() + isChanging);
                /*if (source.isEditable() && !isChanging) {
                JOptionPane.showMessageDialog(null, "OMG");
                }*/
                if (!isChanging) {
                    setDefaultApareance(source);
                    actualizarMap(source);
                    procesarTexto(source);
                    mostrarValEnCelda(source);
                    //System.out.println("focusLost - !editing");
                }

            }
        });
        celda.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                //System.out.println("xx" + e.getKeyChar());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                JFormattedTextField source = (JFormattedTextField) e.getSource();
                if (source.isEditable()) {
                    estaEditando = true;
                    //isChanging = true;
                    currText = source.getText();
                    mostrarBodyEnFuncionCelda(currText);
                    if (e.getKeyChar() == '=' && source.getText().length() == 1) {
                        enFormula = true;
                        loadMenus("");
                        showMenu(source);
                    } else if (enFormula) {
                        if (e.getKeyChar() == '(') {
                            loadMenus("");
                            showMenu(source);
                        } else {
                            String cadena = source.getText();
                            int lastIndex = cadena.lastIndexOf("(") > cadena.lastIndexOf("=") ? cadena.lastIndexOf("(") + 1 : cadena.lastIndexOf("=") + 1;
                            String filter = cadena.substring(lastIndex, cadena.length());
                            loadMenus(filter);
                            showMenu(source);
                        }
                    }
                    estaEditando = true;
                }
            }
        });
    }

    private void showMenu(JFormattedTextField source) {
        isChanging = true;
        popupMenu.repaint();
        popupMenu.setBounds(popupMenu.getBounds());
        popupMenu.show(source, 0, source.getHeight());
        source.requestFocus();
        isChanging = true;
    }

    private void loadMenus(String filter) {
        //System.out.println("Filter: " + filter + " ");
        popupMenu = new JPopupMenu();
        popupMenu.setBorder(new LineBorder(Color.black, 1, true));
        for (int i = 0; i < menufuncion.length; i++) {
            if (menufuncion[i].getText().contains(filter.toUpperCase())) {
                popupMenu.add(menufuncion[i]);
            }
        }
    }

    private void setDefaultApareance(JFormattedTextField celda) {
        celda.setEditable(false);
        celda.setBackground(Color.white);
        celda.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        setDefaultBorder(celda);
    }

    private void setEditable(JFormattedTextField celda) {
        celda.setEditable(true);
        celda.setCaretPosition(celda.getText().length());
        //celda.setBackground(Color.white);
        celda.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        celda.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.black, 2, true),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)));
    }

    private void setFocusBorder(JFormattedTextField celda) {
        celda.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.black, 3, true),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)));
    }

    private void setDefaultBorder(JFormattedTextField source) {
        source.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(188, 220, 250)),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)));
    }

    private void actualizarMap(JFormattedTextField source) {
        if (currText != null) {
            String val = mapCeldas.get(source)[0];
            mapCeldas.put(source, new String[]{val, currText});
        }
        currText = null;
    }

    public void procesarTexto(JFormattedTextField source) {
        String body = mapCeldas.get(source)[1];
        if (Formula.esFormula(body)) {
            String resultado = "" + new Formula().iniciarFormula(body, celdas);
            mapCeldas.put(source, new String[]{resultado, body});
        } else {
            mapCeldas.put(source, new String[]{body, body});
        }
    }

    private void mostrarValEnCelda(JFormattedTextField source) {
        source.setText(mapCeldas.get(source)[0]);
    }

    private void mostrarBodyEnFuncionCelda(JFormattedTextField source) {
        functionCelda.setText(mapCeldas.get(source)[1]);
    }

    private void mostrarBodyEnFuncionCelda(String str) {
        functionCelda.setText(str);
    }

    public void setRandomValues() {
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[i].length; j++) {
                String val = (int) (Math.random() * 20) + "";
                mapCeldas.put(celdas[i][j], new String[]{val, val});
                celdas[i][j].setText(mapCeldas.get(celdas[i][j])[0]);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        new rCExcel().setVisible(true);
    }
}   
