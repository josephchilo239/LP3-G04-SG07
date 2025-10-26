package Ejercicios;
import java.sql.*;
import java.util.Scanner;

public class Uno {
    private static final String DB_URL = "jdbc:sqlite:alumnos.db";
    private static final String CLAVE_SEGURIDAD = "admin123";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(DB_URL)) {
            Class.forName("org.sqlite.JDBC");
            crearTabla(con);
            mostrarMenu(con);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void crearTabla(Connection con) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS alumnos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                edad INTEGER,
                carrera TEXT
            )
            """;
        con.createStatement().execute(sql);
    }

    private static void mostrarMenu(Connection con) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== MENÚ DE ALUMNOS ===");
            System.out.println("1. Insertar");
            System.out.println("2. Mostrar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int op = sc.nextInt(); sc.nextLine();

            switch (op) {
                case 1 -> insertar(con, sc);
                case 2 -> mostrar(con);
                case 3 -> actualizar(con, sc);
                case 4 -> eliminar(con, sc);
                case 5 -> salir = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void insertar(Connection con, Scanner sc) throws SQLException {
        System.out.print("Nombre: "); String nombre = sc.nextLine();
        System.out.print("Edad: "); int edad = sc.nextInt(); sc.nextLine();
        System.out.print("Carrera: "); String carrera = sc.nextLine();
        System.out.print("Ingrese clave de confirmación: "); String clave = sc.nextLine();

        con.setAutoCommit(false);
        if (clave.equals(CLAVE_SEGURIDAD)) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO alumnos(nombre, edad, carrera) VALUES(?, ?, ?)");
            ps.setString(1, nombre);
            ps.setInt(2, edad);
            ps.setString(3, carrera);
            ps.executeUpdate();
            con.commit();
            System.out.println("Registro insertado correctamente.");
        } else {
            con.rollback();
            System.out.println("Clave incorrecta. Cambios revertidos.");
        }
        con.setAutoCommit(true);
    }

    private static void mostrar(Connection con) throws SQLException {
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM alumnos");
        System.out.println("\nLISTA DE ALUMNOS:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") +
                    " | Nombre: " + rs.getString("nombre") +
                    " | Edad: " + rs.getInt("edad") +
                    " | Carrera: " + rs.getString("carrera"));
        }
    }

    private static void actualizar(Connection con, Scanner sc) throws SQLException {
        System.out.print("ID del alumno a actualizar: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Nueva carrera: "); String carrera = sc.nextLine();
        System.out.print("Ingrese clave de confirmación: "); String clave = sc.nextLine();

        con.setAutoCommit(false);
        if (clave.equals(CLAVE_SEGURIDAD)) {
            PreparedStatement ps = con.prepareStatement("UPDATE alumnos SET carrera=? WHERE id=?");
            ps.setString(1, carrera);
            ps.setInt(2, id);
            ps.executeUpdate();
            con.commit();
            System.out.println("Registro actualizado correctamente.");
        } else {
            con.rollback();
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
        con.setAutoCommit(true);
    }

    private static void eliminar(Connection con, Scanner sc) throws SQLException {
        System.out.print("ID del alumno a eliminar: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Ingrese clave de confirmación: "); String clave = sc.nextLine();

        con.setAutoCommit(false);
        if (clave.equals(CLAVE_SEGURIDAD)) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM alumnos WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            con.commit();
            System.out.println("Registro eliminado correctamente.");
        } else {
            con.rollback();
            System.out.println("Clave incorrecta. Cambios revertidos.");
        }
        con.setAutoCommit(true);
    }
}
