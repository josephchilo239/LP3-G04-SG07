package Actividad1Punto2;

public class MainStrategy {
    public static void main(String[] args) {
        CalculadoraPrecio calc = new CalculadoraPrecio();

        calc.setEstrategia(new SinDescuento());
        System.out.println("Sin descuento: " + calc.calcular(100));

        calc.setEstrategia(new Descuento10());
        System.out.println("10% descuento: " + calc.calcular(100));

        calc.setEstrategia(new Descuento20());
        System.out.println("20% descuento: " + calc.calcular(100));
    }
}
