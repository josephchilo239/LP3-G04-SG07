package Ejercicios;

public class PruebaContenedor {
    public static void main(String[] args) {
        Contenedor<String, Integer> contenedor = new Contenedor<>();

        contenedor.agregarPar("Uno", 1);
        contenedor.agregarPar("Dos", 2);
        contenedor.agregarPar("Tres", 3);

        System.out.println("Par en índice 1: " + contenedor.obtenerPar(1));
        System.out.println("\nTodos los pares:");
        contenedor.mostrarPares();
    }
}
