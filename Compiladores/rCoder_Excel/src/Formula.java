
import funciones.Funcion;
import funciones.FuncionSuma;
import funciones.FuncionPromedio;
import funciones.FuncionProducto;
import java.util.LinkedList;
import javax.swing.JFormattedTextField;

public class Formula {

    Funcion[] functions = {
        new FuncionSuma(),
        new FuncionProducto(),
        new FuncionPromedio(),
    };

    public Funcion[] getFunctions() {
        return functions;
    }

    public Formula() {

    }

    public String iniciarFormula(String cadenaTotal, JFormattedTextField[][] celdas) {
        if (esFormula(cadenaTotal)) {
            if (esFormulaCorrecta(cadenaTotal)) {
                String mainFuncion = cadenaTotal.substring(1, cadenaTotal.length());
                Funcion f = parseFunction(mainFuncion);
                double rpta = 0.0;
                try {
                    rpta = resolverFormula(f, celdas);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return "#¿ERROR?";
                }
                showInfoFuncion(f);
                return rpta + "";
            } else {
                return "#¿NOMBRE?";
            }
        } else {
            return cadenaTotal;
        }
    }

    public static void showInfoFuncion(Funcion f) {
        System.out.print(nombreFuncion(f.getClass().getName()) + " : ");
        System.out.print(" elementos: " + f.getElementos());
        System.out.print(" n: " + f.getElementos().size());
        System.out.print(" values: " + f.getValues());
        System.out.println(" resultado: " + f.getResultado());
    }

    public double resolverFormula(Funcion f, JFormattedTextField[][] celdas) throws IllegalAccessException, InstantiationException {
        for (String item : f.getElementos()) {
            double doubleItem = 0.0;
            if (esFuncion(item)) {
                Funcion newF = parseFunction(item);
                doubleItem = resolverFormula(newF, celdas);
                showInfoFuncion(newF);
            } else if (esRango(item)) {
                /*Funcion fx = f.getClass().newInstance();
                fx.setValues(parseValues(item, celdas));                
                doubleItem = fx.getResultado();*/
                LinkedList<Double> list = parseValues(item, celdas);
                for (int i = 0; i < list.size(); i++) {
                    f.addValue(list.get(i));
                }
                continue;
            } else if (esCelda(item)) {
                doubleItem = Double.parseDouble(parseElement(item, celdas));
            } else {
                doubleItem = Double.parseDouble(item);
            }
            f.addValue(doubleItem);
        }
        return f.getResultado();
    }

    public static boolean esFormula(String cadena) {
        if (cadena.length() > 0 && cadena.charAt(0) == '=') {
            return true;
        }
        return false;
    }

    public Funcion parseFunction(String cadena) {
        String nombreFuncion = cadena.substring(0, cadena.indexOf("(")).toUpperCase();
        String elemtns = cadena.substring(cadena.indexOf("(") + 1, cadena.length() - 1);
        LinkedList<String> elemtnsList = parseElements(elemtns);
        Funcion f = null;
        for (int i = 0; i < functions.length; i++) {
            String name = nombreFuncion(functions[i].getClass().getName());
            if (name.toUpperCase().equals(nombreFuncion.toUpperCase())) {
                try {
                    f = functions[i].getClass().newInstance();
                    f.setElementos(elemtnsList);

                } catch (Exception ex) {
                    System.out.println(ex.getStackTrace().toString());
                }
            }
        }
        return f;
    }

    public LinkedList<String> parseElements(String strElementos) {
        String aElement = "";
        LinkedList<String> elementsList = new LinkedList<String>();
        int isParentPared = 0;
        for (int i = 0; i < strElementos.length(); i++) {
            char charAt = strElementos.charAt(i);
            if (charAt == '(') {
                isParentPared++;
            }
            if (charAt == ')') {
                isParentPared--;
            }
            if (charAt == ';' && isParentPared == 0) {
                elementsList.add(aElement);
                aElement = "";
                isParentPared = 0;
            } else {
                aElement += charAt;
            }
        }
        elementsList.add(aElement);
        return elementsList;
    }

    public LinkedList<String> parseRango(String rango, JFormattedTextField[][] fields) {
        LinkedList<String> elements = new LinkedList<String>();

        String celdaInicial = rango.substring(0, rango.indexOf(":"));
        String letraInicial = celdaInicial.substring(0, 1);
        int nroInicial = Integer.parseInt(celdaInicial.substring(letraInicial.length(), celdaInicial.length())) - 1;

        String celdaFinal = rango.substring(rango.indexOf(":") + 1, rango.length());
        String letraFinal = celdaFinal.substring(0, 1);
        int nroFinal = Integer.parseInt(celdaFinal.substring(letraFinal.length(), celdaFinal.length())) - 1;

        int columnInicial = letraInicial.charAt(0) - 65;
        int columnFinal = letraFinal.charAt(0) - 65;
        for (int j = columnInicial; j <= columnFinal; j++) {
            for (int i = nroInicial; i <= nroFinal; i++) {
                elements.add(fields[i][j].getText());
            }
        }
        return elements;
    }

    public LinkedList<Double> parseValues(String rango, JFormattedTextField[][] fields) {
        LinkedList<Double> elements = new LinkedList<Double>();

        String celdaInicial = rango.substring(0, rango.indexOf(":"));
        String letraInicial = celdaInicial.substring(0, 1);
        int nroInicial = Integer.parseInt(celdaInicial.substring(letraInicial.length(), celdaInicial.length())) - 1;

        String celdaFinal = rango.substring(rango.indexOf(":") + 1, rango.length());
        String letraFinal = celdaFinal.substring(0, 1);
        int nroFinal = Integer.parseInt(celdaFinal.substring(letraFinal.length(), celdaFinal.length())) - 1;

        int columnInicial = letraInicial.charAt(0) - 65;
        int columnFinal = letraFinal.charAt(0) - 65;
        for (int j = columnInicial; j <= columnFinal; j++) {
            for (int i = nroInicial; i <= nroFinal; i++) {
                elements.add(Double.parseDouble(fields[i][j].getText()));
            }
        }
        return elements;
    }

    private String parseElement(String celda, JFormattedTextField[][] fields) {
        String letraInicial = celda.substring(0, 1);
        int j = letraInicial.charAt(0) - 65;
        int i = Integer.parseInt(celda.substring(letraInicial.length(), celda.length())) - 1;
        return fields[i][j].getText();
    }

    public boolean esFuncion(String item) {
        try {
            item = item.substring(0, item.indexOf("("));
            boolean f = false;
            for (int i = 0; i < functions.length; i++) {
                String name = nombreFuncion(functions[i].getClass().getName());
                if (name.toUpperCase().equals(item.toUpperCase())) {
                    f = true;
                }
            }
            return f;
        } catch (StringIndexOutOfBoundsException ie) {
            return false;
        }
    }

    public static String nombreFuncion(String nombreEntero) {
        return nombreEntero.replaceAll("funciones.Funcion", "");
    }

    public static String nombreFuncion(Funcion f) {
        return f.getClass().getName().replaceAll("funciones.Funcion", "").toUpperCase();
    }

    private boolean esFormulaCorrecta(String formula) {
        int nro = 0;
        if (nroParentesisDerecho(formula) == nroParentesisIzquierdo(formula) && nroParentesisDerecho(formula) != 0) {
            return true;
        }
        return false;
    }

    private boolean esDatoCorrecto(String dato) {
        if (esDouble(dato) || esCelda(dato)) {
            return true;
        }
        return false;
    }

    private boolean esRango(String item) {
        if (item.contains(":")) {
            String celdaInicial = item.substring(0, item.indexOf(":"));
            String celdaFinal = item.substring(item.indexOf(":") + 1, item.length());
            if (esCelda(celdaInicial) && esCelda(celdaFinal)) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean esDouble(String nro) {
        try {
            Double.parseDouble(nro);
            return true;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    private boolean esCelda(String celda) {
        try {
            String letraIn = celda.substring(0, 1);
            boolean esLetra = false;
            for (String letra : rCExcel.letras) {
                if (letraIn.equals(letra)) {
                    esLetra = true;
                }
            }
            int parteNumero = Integer.parseInt(celda.substring(1, celda.length()));
            boolean esNumero = false;
            if (1 <= parteNumero && parteNumero <= rCExcel.ROWS) {
                esNumero = true;
            }
            return esLetra && esNumero;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    public int nroParentesisDerecho(String formula) {
        int count = 0;
        for (int i = 0; i < formula.length(); i++) {
            if (formula.charAt(i) == ')') {
                count++;
            }
        }
        return count;
    }

    public int nroParentesisIzquierdo(String formula) {
        int count = 0;
        for (int i = 0; i < formula.length(); i++) {
            if (formula.charAt(i) == '(') {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new Formula().iniciarFormula("7", null));
        System.out.println(new Formula().iniciarFormula("=SUMA(34;34;SUMA(3;3))", null));
    }
}
