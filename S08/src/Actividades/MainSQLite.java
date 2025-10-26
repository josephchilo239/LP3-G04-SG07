package Actividades;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class MainSQLite {
	public static void main(String[] args) {

		Connection con = null;

		try {
			Class.forName("org.sqlite.JDBC");

			con = DriverManager.getConnection("jdbc:sqlite:basedatos_practica.db");
			System.out.println("Conexión establecida con SQLite.");

			String crearTabla = "CREATE TABLE IF NOT EXISTS alumnos (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "nombre TEXT NOT NULL, " + "edad INTEGER)";
			Statement st = con.createStatement();
			st.execute(crearTabla);
			System.out.println("Tabla 'alumnos' creada o verificada correctamente.\n");

			con.setAutoCommit(false); 
			String sqlInsert = "INSERT INTO alumnos (nombre, edad) VALUES (?, ?)";
			PreparedStatement psInsert = con.prepareStatement(sqlInsert);

			psInsert.setString(1, "Carlos");
			psInsert.setInt(2, 20);
			psInsert.executeUpdate();

			psInsert.setString(1, "María");
			psInsert.setInt(2, 22);
			psInsert.executeUpdate();

			psInsert.setString(1, "Jorge");
			psInsert.setInt(2, 19);
			psInsert.executeUpdate();

			System.out.println("Inserción de registros completada.\n");

			String sqlSelect = "SELECT * FROM alumnos";
			ResultSet rs = st.executeQuery(sqlSelect);

			System.out.println("LISTA DE ALUMNOS:");
			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("id") + " | Nombre: " + rs.getString("nombre") + " | Edad: "
						+ rs.getInt("edad"));
			}

			String sqlUpdate = "UPDATE alumnos SET edad = ? WHERE nombre = ?";
			PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
			psUpdate.setInt(1, 23);
			psUpdate.setString(2, "María");
			int filasAct = psUpdate.executeUpdate();
			System.out.println("\nRegistros actualizados: " + filasAct);

			String sqlDelete = "DELETE FROM alumnos WHERE nombre = ?";
			PreparedStatement psDelete = con.prepareStatement(sqlDelete);
			psDelete.setString(1, "Jorge");
			int filasElim = psDelete.executeUpdate();
			System.out.println("Registros eliminados: " + filasElim + "\n");

			con.commit();
			System.out.println("Transacción confirmada correctamente.\n");

			ResultSet rsFinal = st.executeQuery("SELECT * FROM alumnos");
			System.out.println("DATOS FINALES EN LA TABLA:");
			while (rsFinal.next()) {
				System.out.println("ID: " + rsFinal.getInt("id") + " | Nombre: " + rsFinal.getString("nombre")
						+ " | Edad: " + rsFinal.getInt("edad"));
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			try {
				if (con != null) {
					con.rollback(); 
					System.out.println("Cambios revertidos por error en la transacción.");
				}
			} catch (SQLException ex) {
				System.out.println("Error al hacer rollback: " + ex.getMessage());
			}
		} finally {
			try {
				if (con != null) {
					con.close();
					System.out.println("\nConexión cerrada correctamente.");
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar conexión: " + e.getMessage());
			}
		}
	}
}
