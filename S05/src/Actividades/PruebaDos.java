package Actividades;

public class PruebaDos {
    public static void main(String[] args) {
        Pila<Integer> pila = new Pila<>(5);

        pila.push(10);
        pila.push(20);
        pila.push(30);

        System.out.println("¿La pila contiene 20? " + pila.contains(20));
        System.out.println("¿La pila contiene 50? " + pila.contains(50));
    }
}
