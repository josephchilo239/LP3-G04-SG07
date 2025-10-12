package EjercicioDos;
import java.io.*;
import java.util.*;

public class Gestor {
    private List<Personaje> personajes = new ArrayList<>();
    private final String archivo = "personajes.txt";
    private Random rand = new Random();

    public Gestor() {
        cargar();
        if (personajes.isEmpty()) {
            cargarAleatorios();
            guardar();
        }
    }
    private void cargar() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null)
                personajes.add(Personaje.fromString(linea));
        } catch (IOException e) {
            System.out.println("Archivo no encontrado, se creará uno nuevo.");
        }
    }

    private void guardar() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Personaje p : personajes)
                pw.println(p.toString());
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
        System.out.println("Personaje agregado correctamente.");
    }

    public void eliminar(String nombre) {
        boolean eliminado = personajes.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
        if (eliminado) {
            guardar();
            System.out.println("Personaje eliminado.");
        } else {
            System.out.println("No encontrado.");
        }
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
            System.out.println("Personaje modificado correctamente.");
        }
    }

    public void actualizarAtributo(String nombre, String atributo, int valor) {
        Personaje p = buscar(nombre);
        if (p == null) {
            System.out.println("No se encontró el personaje.");
            return;
        }
        switch (atributo.toLowerCase()) {
            case "vida" -> p.setVida(valor);
            case "ataque" -> p.setAtaque(valor);
            case "defensa" -> p.setDefensa(valor);
            case "alcance" -> p.setAlcance(valor);
            default -> System.out.println("Atributo no válido.");
        }
        guardar();
        System.out.println("Atributo actualizado correctamente.");
    }

    public void filtrarPor(String atributo) {
        personajes.sort(Comparator.comparingInt(p -> switch (atributo.toLowerCase()) {
            case "vida" -> p.getVida();
            case "ataque" -> p.getAtaque();
            case "defensa" -> p.getDefensa();
            case "alcance" -> p.getAlcance();
            default -> 0;
        }));
        mostrar();
    }

    private void cargarAleatorios() {
        String[] nombres = {"Caballero", "Guerrero", "Arquero"};
        for (String n : nombres) {
            int v = rand.nextInt(3) + 2;
            int a = rand.nextInt(3) + 2;
            int d = rand.nextInt(3) + 1;
            int al = rand.nextInt(5) + 2;
            personajes.add(new Personaje(n, v, a, d, al));
        }
        System.out.println("Se cargaron personajes aleatorios.");
    }

    public void estadisticas() {
        if (personajes.isEmpty()) return;
        double vidaProm = personajes.stream().mapToInt(Personaje::getVida).average().orElse(0);
        double ataqueProm = personajes.stream().mapToInt(Personaje::getAtaque).average().orElse(0);
        double defensaProm = personajes.stream().mapToInt(Personaje::getDefensa).average().orElse(0);
        double alcanceProm = personajes.stream().mapToInt(Personaje::getAlcance).average().orElse(0);

        System.out.println("\n--- ESTADÍSTICAS ---");
        System.out.printf("Total de personajes: %d%n", personajes.size());
        System.out.printf("Vida promedio: %.2f | Ataque: %.2f | Defensa: %.2f | Alcance: %.2f%n",
                vidaProm, ataqueProm, defensaProm, alcanceProm);
    }

    public void importarDesdeArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Personaje nuevo = Personaje.fromString(linea);
                if (buscar(nuevo.getNombre()) == null)
                    personajes.add(nuevo);
            }
            guardar();
            System.out.println("Importación completada.");
        } catch (IOException e) {
            System.out.println("Error al importar archivo.");
        }
    }

    public void subirNivel(String nombre) {
        Personaje p = buscar(nombre);
        if (p != null) {
            p.subirNivel();
            guardar();
            System.out.println(p.getNombre() + " ha subido a nivel " + p.getNivel());
        } else System.out.println("No encontrado.");
    }

    public void mostrar() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes.");
            return;
        }
        System.out.println("\n--- LISTA DE PERSONAJES ---");
        personajes.forEach(System.out::println);
    }
}
