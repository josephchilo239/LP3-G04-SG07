package Ejercicios;

public class Primero {
    public static int sumaElementos(int[] arreglo) {
        int suma = 0;
        for (int num : arreglo) {
            suma += num;
        }
        return suma;
    }
    public static void main(String[] args) {
        int[] miArreglo = {1, 2, 3, 4, 5};
        System.out.println(sumaElementos(miArreglo)); 
    }
}
