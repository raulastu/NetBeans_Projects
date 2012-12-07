package compiladores2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InfijoToSufijoFixeadoByRC {

    static int maximo = 80;
    static int tamanoPila = 100;

    static class Pila {

        int top;
        float[] items = new float[tamanoPila];
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Ingrese infijo: ");
        BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
        String strInfijo = is.readLine();
        char[] infijo = strInfijo.toCharArray();
        char[] postfijo = new char[infijo.length];
        postfija(infijo, postfijo);
        System.out.println("La cadena Postfija es: ");
        for (int i = 0; i < strInfijo.length(); i++) {
            System.out.print(postfijo[i]);
        }
        System.out.println();
        System.out.println("Resultado: " + eval(postfijo));
    }

    public static void postfija(char infijo[], char postfijo[]) {
        int j = 0;
        char simbolo;
        Pila ps = new Pila();
        inicio(ps);
        for (int i = 0; i < infijo.length; i++) {
            simbolo = infijo[i];
            if (!esOperador(simbolo)) {
                postfijo[j++] = simbolo;
            } else {
                if (operadorEsMasMenos(simbolo)) {
                    while (!vacio(ps)) {
                        postfijo[j++] = quitar(ps);
                    }
                    agregarAPila(ps, simbolo);
                } else {
                    agregarAPila(ps, simbolo);
                }
            }
        }
        while (!vacio(ps)) {
            postfijo[j++] = quitar(ps);
        }
    //postfijo[j] = '\0';
    }

    static boolean pilaTienePlusOrMinus(Pila ps) {
        for (int i = 0; i < ps.top; i++) {
            if (operadorEsMasMenos((char) ps.items[i])) {
                return true;
            }
        }
        return false;
    }

    static boolean operadorEsMasMenos(char operador) {
        if (operador == '*') {
            return false;
        } else if (operador == '/') {
            return false;
        } else if (operador == '^') {
            return false;
        }
        return true;
    }

    static boolean esOperador(char simbolo) {
        char[] sArr = new char[]{'+', '-', '*', '/', '^', '(', ')'};
        for (char s : sArr) {
            if (simbolo == s) {
                return true;
            }
        }
        return false;
    }

    static boolean vacio(Pila pila) {
        if (pila.top == -1) {
            return true;
        } else {
            return false;
        }
    }

    static char quitar(Pila ps) {
        if (vacio(ps)) {
            System.out.println("Pila vacia");
            System.exit(0);
        }
        return ((char) ps.items[ps.top--]);
    }

    static void agregarAPila(Pila ps, float x) {
        if (ps.top == tamanoPila - 1) {
            System.out.println("La pila esta llena");
            System.exit(0);
        } else {
            ps.items[++(ps.top)] = x;
        }
    }

    static void inicio(Pila pila) {
        pila.top = -1;
    }

    static float eval(char postfijo[]) {
        Pila ps = new Pila();
        float op1, op2, x, y;
        char c;
        inicio(ps);
        for (int i = 0; i < postfijo.length; i++) {
            c = postfijo[i++];
            if (!esOperador(c)) {
                x = c - '0';
            agregarAPila(ps, x);
            } else {
                op2 = quitar(ps);
                op1 = quitar(ps);
                y = valor(op1, op2, c);
            agregarAPila(ps, y);
            }
        }
        return quitar(ps);
    }

    static float valor(float op1, float op2, char c) {
        float y = 0;
        switch (c) {
            case '+':
                y = (op1 + op2);
                break;
            case '-':
                y = (op1 - op2);
                break;
            case '*':
                y = (op1 * op2);
                break;
            case '/':
                y = (op1 / op2);
                break;
            case '^':
                y = ((float) Math.pow(op1, op2));
                break;
            default:
                break;
        }
        return y;
    }
}
