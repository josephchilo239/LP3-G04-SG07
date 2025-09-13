package Ejercicios;

import java.util.NoSuchElementException;

class RegistroEstudiantes {
    private String[] estudiantes;
    private int contador;

    public RegistroEstudiantes(int capacidad) {
        estudiantes = new String[capacidad];
        contador = 0;
    }

    public void agregarEstudiante(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        if (contador < estudiantes.length) {
            estudiantes[contador++] = nombre;
        } else {
            throw new IllegalStateException("No hay espacio para más estudiantes.");
        }
    }

    public String buscarEstudiante(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (estudiantes[i].equalsIgnoreCase(nombre)) {
                return estudiantes[i];
            }
        }
        throw new NoSuchElementException("Estudiante no encontrado: " + nombre);
    }
}

public class cuatro {
    public static void main(String[] args) {
        RegistroEstudiantes registro = new RegistroEstudiantes(3);
        try {
            registro.agregarEstudiante("Ana");
            registro.agregarEstudiante("Luis");
            registro.agregarEstudiante("");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            System.out.println("Buscando Ana: " + registro.buscarEstudiante("Ana"));
            System.out.println("Buscando Pedro: " + registro.buscarEstudiante("Pedro"));
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
