package Ejercicios;

class DivisionPorCeroException extends Exception {
    public DivisionPorCeroException() {
        super("Error: división por cero.");
    }
}

class Calculadora {
    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) throws DivisionPorCeroException {
        if (b == 0) {
            throw new DivisionPorCeroException();
        }
        return a / b;
    }
}

public class dos {
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        try {
            System.out.println("5 + 3 = " + calc.sumar(5, 3));
            System.out.println("10 / 0 = " + calc.dividir(10, 0));
        } catch (DivisionPorCeroException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Argumento inválido: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Error aritmético: " + e.getMessage());
        }
    }
}
