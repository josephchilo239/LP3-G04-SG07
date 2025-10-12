package EjercicioUno;
import java.io.*;
import java.util.*;

public class Gestor {
    private List<Personaje> personajes = new ArrayList<>();
    private final String archivo = "personajes.txt";

    public Gestor() {
        cargar();
    }

    private void cargar() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                personajes.add(Personaje.fromString(linea));
            }
        } catch (IOException e) {
            System.out.println("Archivo no encontrado, se creará uno nuevo.");
        }
    }

    private void guardar() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Personaje p : personajes) {
                pw.println(p.toString());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los personajes.");
        }
    }

    public void agregar(Personaje p) {
        if (buscar(p.getNombre()) != null) {
            System.out.println("El personaje ya existe.");
            return;
        }
        personajes.add(p);
        guardar();
    }

    public void eliminar(String nombre) {
        personajes.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
        guardar();
    }

    public Personaje buscar(String nombre) {
        for (Personaje p : personajes)
            if (p.getNombre().equalsIgnoreCase(nombre)) return p;
        return null;
    }

    public void modificar(String nombre, int vida, int ataque, int defensa, int alcance) {
        Personaje p = buscar(nombre);
        if (p != null) {
            p.setVida(vida);
            p.setAtaque(ataque);
            p.setDefensa(defensa);
            p.setAlcance(alcance);
            guardar();
        }
    }

    public void mostrar() {
        personajes.forEach(System.out::println);
    }
}
