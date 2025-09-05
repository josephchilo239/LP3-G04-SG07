package Ejercicios;

abstract class Vehiculo {
	public abstract void acelerar();
}

class Coche extends Vehiculo {
	public void acelerar() {
		System.out.println("Coche acelera usando el motor.");
	}
}

class Bicicleta extends Vehiculo {
	public void acelerar() {
		System.out.println("Bicicleta acelera pedaleando.");
	}
}

public class tres {
	public static void main(String[] args) {
		Vehiculo v1 = new Coche();
		Vehiculo v2 = new Bicicleta();

		v1.acelerar();
		v2.acelerar();
	}
}
