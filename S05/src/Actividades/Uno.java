package Actividades;

class InvalidSubscriptException extends RuntimeException {
	public InvalidSubscriptException(String mensaje) {
		super(mensaje);
	}
}

public class Uno {

	public static <E> void imprimirArreglo(E[] arregloEntrada) {
		for (E elemento : arregloEntrada) {
			System.out.printf("%s ", elemento);
		}
		System.out.println();
	}

	public static <E> int imprimirArreglo(E[] arregloEntrada, int subIndiceInferior, int subIndiceSuperior) {
		if (subIndiceInferior < 0 || subIndiceSuperior >= arregloEntrada.length
				|| subIndiceSuperior <= subIndiceInferior) {
			throw new InvalidSubscriptException("Índices inválidos para el arreglo.");
		}

		int contador = 0;
		for (int i = subIndiceInferior; i <= subIndiceSuperior; i++) {
			System.out.printf("%s ", arregloEntrada[i]);
			contador++;
		}
		System.out.println();
		return contador;
	}

	public static void main(String[] args) {
		Integer[] arregloInteger = { 1, 2, 3, 4, 5, 6 };
		Double[] arregloDouble = { 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7 };
		Character[] arregloCharacter = { 'H', 'O', 'L', 'A' };

		System.out.println("Arreglo Integer completo:");
		imprimirArreglo(arregloInteger);

		System.out.println("\nArreglo Double de índice 2 a 5:");
		imprimirArreglo(arregloDouble, 2, 5);

		System.out.println("\nArreglo Character completo:");
		imprimirArreglo(arregloCharacter);
	}
}
