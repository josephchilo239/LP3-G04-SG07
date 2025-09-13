package Ejercicios;

import java.io.IOException;
import java.io.InputStream;

class VocalException extends Exception {
    public VocalException(char c) {
        super("Se ingresó una vocal: " + c);
    }
}

class NumeroException extends Exception {
    public NumeroException(char c) {
        super("Se ingresó un número: " + c);
    }
}

class BlancoException extends Exception {
    public BlancoException() {
        super("Se ingresó un espacio en blanco");
    }
}

class SalidaException extends Exception {
    public SalidaException() {
        super("Se ingresó el carácter de salida (X/x). Terminando programa...");
    }
}

class LeerEntrada {
    private InputStream in;

    public LeerEntrada() {
        this.in = System.in;
    }

    public char getChar() throws IOException {
        return (char) in.read();
    }
}

public class uno {
    private LeerEntrada entrada = new LeerEntrada();

    public void procesar() throws Exception {
        char c = entrada.getChar();
        if ("aeiouAEIOU".indexOf(c) >= 0) {
            throw new VocalException(c);
        } else if (Character.isDigit(c)) {
            throw new NumeroException(c);
        } else if (Character.isWhitespace(c)) {
            throw new BlancoException();
        } else if (c == 'x' || c == 'X') {
            throw new SalidaException();
        } else {
            System.out.println("Se ingresó otro carácter: " + c);
        }
    }
    public static void main(String[] args) {
        uno app = new uno();
        System.out.println("Ingrese caracteres (X para salir):");

        while (true) {
            try {
                app.procesar();
            } catch (VocalException | NumeroException | BlancoException e) {
                System.out.println(e.getMessage());
            } catch (SalidaException e) {
                System.out.println(e.getMessage());
                break;
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }
}
