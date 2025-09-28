package Actividades;

public class PruebaCuatro {
    public static void main(String[] args) {
    	Cuatro<Integer> Cuatro1 = new Cuatro<>(5);
    	Cuatro<Integer> Cuatro2 = new Cuatro<>(5);

    	Cuatro1.push(1);
    	Cuatro1.push(2);
    	Cuatro1.push(3);

    	Cuatro2.push(1);
    	Cuatro2.push(2);
    	Cuatro2.push(3);

        System.out.println("¿Son iguales? " + Cuatro1.esIgual(Cuatro2));

        Cuatro2.pop();
        Cuatro2.push(4);

        System.out.println("¿Son iguales después del cambio? " + Cuatro1.esIgual(Cuatro2));
    }
}
