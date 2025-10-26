package Ejercicios;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

class Alumno {
    private int id;
    private String nombre;
    private int edad;
    private String carrera;

    public Alumno(int id, String nombre, int edad, String carrera) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.carrera = carrera;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getCarrera() { return carrera; }

    @Override
    public String toString() {
        return "ID: " + id + " | " + nombre + " | " + edad + " años | " + carrera;
    }
}

class GestorAlumnos {
    private List<Alumno> lista = new ArrayList<>();

    public void cargarDesdeBD(Connection con) throws SQLException {
        lista.clear();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM alumnos");
        while (rs.next()) {
            lista.add(new Alumno(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("edad"),
                    rs.getString("carrera")
            ));
        }
    }

    public void consultar(String campoMostrar, String campoCond, String valorCond,
                          boolean ascendente, String campoOrden, int limite) {

        List<Alumno> resultado = lista.stream()
                .filter(a -> {
                    if (campoCond == null || valorCond == null || campoCond.isEmpty()) return true;
                    return switch (campoCond.toLowerCase()) {
                        case "nombre" -> a.getNombre().equalsIgnoreCase(valorCond);
                        case "carrera" -> a.getCarrera().equalsIgnoreCase(valorCond);
                        case "edad" -> Integer.toString(a.getEdad()).equals(valorCond);
                        default -> true;
                    };
                })
                .sorted((a, b) -> {
                    int comp = 0;
                    switch (campoOrden.toLowerCase()) {
                        case "nombre" -> comp = a.getNombre().compareToIgnoreCase(b.getNombre());
                        case "edad" -> comp = Integer.compare(a.getEdad(), b.getEdad());
                        case "carrera" -> comp = a.getCarrera().compareToIgnoreCase(b.getCarrera());
                    }
                    return ascendente ? comp : -comp;
                })
                .limit(limite > 0 ? limite : lista.size())
                .collect(Collectors.toList());

        System.out.println("\n📋 Resultados de la consulta:\n");
        for (Alumno a : resultado) {
            switch (campoMostrar.toLowerCase()) {
                case "nombre" -> System.out.println(a.getNombre());
                case "edad" -> System.out.println(a.getEdad());
                case "carrera" -> System.out.println(a.getCarrera());
                default -> System.out.println(a);
            }
        }
    }
}

public class Dos {
    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection("jdbc:sqlite:alumnos.db")) {
            Class.forName("org.sqlite.JDBC");

            GestorAlumnos gestor = new GestorAlumnos();
            gestor.cargarDesdeBD(con);

            gestor.consultar("todo", "carrera", "Ingenieria de Sistemas", true, "nombre", 5);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
