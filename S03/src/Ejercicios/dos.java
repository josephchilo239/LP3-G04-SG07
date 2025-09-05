package Ejercicios;

interface Forma {
	void dibujar();
}

class Circulo implements Forma {
	public void dibujar() {
		System.out.println("Dibujar círculo");
	}
}

class Rectangulo implements Forma {
	public void dibujar() {
		System.out.println("Dibujar rectángulo");
	}
}

class Triangulo implements Forma {
	public void dibujar() {
		System.out.println("Dibujar triángulo");
	}
}

public class dos {
	public static void main(String[] args) {
		Forma f1 = new Circulo();
		Forma f2 = new Rectangulo();
		Forma f3 = new Triangulo();

		f1.dibujar();
		f2.dibujar();
		f3.dibujar();
	}
}
