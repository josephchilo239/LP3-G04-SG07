package Ejercicios;

public class Main {
    public static <F, S> void imprimirPar(Par<F, S> par) {
        System.out.println(par);
    }

    public static void main(String[] args) {
        Par<String, Integer> par1 = new Par<>("Edad", 25);
        Par<Double, Boolean> par2 = new Par<>(9.81, true);
        Par<Persona, Integer> par3 = new Par<>(new Persona("Ana", 30), 1234);

        imprimirPar(par1);
        imprimirPar(par2);
        imprimirPar(par3);
    }
}
