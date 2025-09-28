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

public class Pila<E> {
    private final int tamanio;
    private int superior;
    private E[] elementos;

    public Pila(int s) {
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

    public boolean contains(E elemento) {
        for (int i = superior; i >= 0; i--) {
            if (elementos[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }
}
