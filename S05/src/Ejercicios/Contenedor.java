package Ejercicios;

import java.util.ArrayList;
import java.util.List;

public class Contenedor<F, S> {
    private List<Par<F, S>> pares;

    public Contenedor() {
        pares = new ArrayList<>();
    }

    public void agregarPar(F primero, S segundo) {
        pares.add(new Par<>(primero, segundo));
    }

    public Par<F, S> obtenerPar(int indice) {
        if (indice < 0 || indice >= pares.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        return pares.get(indice);
    }

    public List<Par<F, S>> obtenerTodosLosPares() {
        return pares;
    }

    public void mostrarPares() {
        for (Par<F, S> par : pares) {
            System.out.println(par);
        }
    }
}
