package Ejercicios;

public class cuatro {
	public static void main(String[] args) {
		IImprimir impresora = new Impresora();
		impresora.imprimir();

		ImpresoraMultifuncional multifuncional = new ImpresoraMultifuncional();
		multifuncional.imprimir();
		multifuncional.escanear();
	}
}

interface IImprimir {
	void imprimir();
}

interface IEscanear {
	void escanear();
}

class Impresora implements IImprimir {
	@Override
	public void imprimir() {
		System.out.println("Imprimiendo documento...");
	}
}

class ImpresoraMultifuncional implements IImprimir, IEscanear {
	@Override
	public void imprimir() {
		System.out.println("Imprimiendo documento...");
	}

	@Override
	public void escanear() {
		System.out.println("Escaneando documento...");
	}
}
