package Ejercicios;

class Numero {
    private double valor;

    public void setValor(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo: " + valor);
        }
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}

public class tres {
    public static void main(String[] args) {
        Numero num = new Numero();
        try {
            num.setValor(10);
            System.out.println("Valor: " + num.getValor());
            num.setValor(-5); // lanza excepción
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
