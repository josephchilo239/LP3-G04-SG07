package Actividades;

//Experiencia4.java
import java.io.*;
import java.util.*;

class HistorialVacioException extends Exception {
	public HistorialVacioException(String msg) {
		super(msg);
	}
}

class ReporteTransacciones {
	public static void generarReporte(List<String> historial, String archivo)
			throws IOException, HistorialVacioException {
		if (historial.isEmpty()) {
			throw new HistorialVacioException("No hay transacciones registradas.");
		}

		try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
			for (String linea : historial) {
				pw.println(linea);
			}
		}
	}

	public static void leerReporte(String archivo) throws IOException {
		try (Scanner sc = new Scanner(new File(archivo))) {
			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
		}
	}
}

public class ExperienciaCuatro {
	public static void main(String[] args) {
		try {
			List<String> historial = new ArrayList<>();
			historial.add("Depósito de 100");
			historial.add("Retiro de 50");

			ReporteTransacciones.generarReporte(historial, "reporte.txt");
			System.out.println("Reporte generado con éxito.");

			ReporteTransacciones.leerReporte("reporte.txt");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
