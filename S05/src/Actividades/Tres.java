package Actividades;

public class Tres {

	public static <T> boolean esIgualA(T a, T b) {
		if (a == null || b == null) {
			return a == b;
		}
		return a.equals(b);
	}

	public static void main(String[] args) {
		System.out.println(esIgualA(5, 5));
		System.out.println(esIgualA("Hola", "Mundo"));
		System.out.println(esIgualA(null, null));
		System.out.println(esIgualA(10, null));
		System.out.println(esIgualA("Java", "Java"));
	}
}
