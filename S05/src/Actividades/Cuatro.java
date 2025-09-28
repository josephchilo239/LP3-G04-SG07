package Actividades;

class ExcepcionPilaLlena extends RuntimeException {
    public ExcepcionPilaLlena(String mensaje) {
        super(mensaje);
    }
}

class ExcepcionPilaVacia extends RuntimeException {
    public ExcepcionPilaVacia(String mensaje) {
        super(mensaje);
    }
}

public class Cuatro<E> {
    private final int tamanio;
    private int superior;
    private E[] elementos;

    public Cuatro(int s) {
        tamanio = s > 0 ? s : 10;
        superior = -1;
        elementos = (E[]) new Object[tamanio];
    }

    public void push(E valorAMeter) {
        if (superior == tamanio - 1) {
            throw new ExcepcionPilaLlena("La pila está llena");
        }
        elementos[++superior] = valorAMeter;
    }

    public E pop() {
        if (superior == -1) {
            throw new ExcepcionPilaVacia("Pila vacía");
        }
        return elementos[superior--];
    }

    public boolean esIgual(Cuatro<E> otraCuatro) {
        if (this.superior != otraCuatro.superior) return false;

        for (int i = 0; i <= superior; i++) {
            if (!this.elementos[i].equals(otraCuatro.elementos[i])) {
                return false;
            }
        }
        return true;
    }
}
