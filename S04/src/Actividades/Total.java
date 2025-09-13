package Actividades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

public class Total {
	public static void main(String[] args) {
		RepositorioCuentas repo = new RepositorioCuentas();

		System.out.println("=== EXPERIMENTAL: Creación de cuentas válidas e inválidas ===");
		try {
			CuentaBancaria c1 = new CuentaBancaria("001", "Alice", 500.0);
			repo.agregarCuenta(c1);
			System.out.println("Cuenta creada: " + c1);
		} catch (IllegalArgumentException ex) {
			System.out.println("Error al crear cuenta: " + ex.getMessage());
		}

		try {
			CuentaBancaria cInvalid = new CuentaBancaria("002", "Bob", -100.0);
			repo.agregarCuenta(cInvalid);
		} catch (IllegalArgumentException ex) {
			System.out.println("Creación inválida detectada: " + ex.getMessage());
		}

		System.out.println("\n=== DEPOSITOS Y RETIROS (validaciones) ===");
		CuentaBancaria alice = repo.obtener("001");
		try {
			alice.depositar(200.0);
			System.out.println("Depósito OK. Saldo Alice: " + alice.getSaldo());
			alice.depositar(-50.0);
		} catch (IllegalArgumentException ex) {
			System.out.println("Depósito inválido: " + ex.getMessage());
		}

		try {
			alice.retirar(100.0);
			System.out.println("Retiro OK. Saldo Alice: " + alice.getSaldo());
			alice.retirar(1000.0);
		} catch (SaldoInsuficienteException | IllegalArgumentException ex) {
			System.out.println("Error en retiro: " + ex.getMessage());
		}

		System.out.println("\n=== TRANSFERENCIAS Y CUENTA NO ENCONTRADA ===");
		CuentaBancaria charlie = new CuentaBancaria("003", "Charlie", 300.0);
		repo.agregarCuenta(charlie);

		try {
			repo.transferir("001", "003", 50.0);
			System.out.println("Transferencia 001->003 OK.");
			System.out.println("Saldo 001: " + repo.obtener("001").getSaldo());
			System.out.println("Saldo 003: " + repo.obtener("003").getSaldo());

			repo.transferir("001", "999", 10.0);
		} catch (CuentaNoEncontradaException | SaldoInsuficienteException | LimiteCreditoExcedidoException ex) {
			System.out.println("Transferencia fallida: " + ex.getMessage());
		}

		System.out.println("\n=== CIERRE DE CUENTAS ===");
		try {
			repo.obtener("001").cerrarCuenta();
		} catch (SaldoNoCeroException ex) {
			System.out.println("No se puede cerrar 001: " + ex.getMessage());
		}

		try {
			CuentaBancaria c3 = repo.obtener("003");
			c3.retirar(c3.getSaldo());
			c3.cerrarCuenta();
			System.out.println("Cuenta 003 cerrada correctamente.");
			repo.removerCuenta("003");
		} catch (Exception ex) {
			System.out.println("Error cerrando 003: " + ex.getMessage());
		}

		System.out.println("\n=== CUENTA CREDITO (límites y propagación) ===");
		CuentaCredito cred = new CuentaCredito("004", "Diana", 100.0, 200.0);
		repo.agregarCuenta(cred);
		try {
			cred.retirar(250.0);
			System.out.println("Retiro con crédito OK. Saldo 004: " + cred.getSaldo());
			cred.retirar(200.0);
		} catch (LimiteCreditoExcedidoException ex) {
			System.out.println("Límite de crédito excedido: " + ex.getMessage());
		} catch (IllegalArgumentException ex) {
			System.out.println("Error en retiro (cuenta crédito): " + ex.getMessage());
		}

		System.out.println("\n=== GENERACIÓN DE REPORTES (try-with-resources) ===");
		try {
			ReporteTransacciones.generarReporte(repo.obtener("001"), "reporte_001.txt");
			System.out.println("Reporte generado: reporte_001.txt");
		} catch (IOException | HistorialVacioException ex) {
			System.out.println("Error generando reporte 001: " + ex.getMessage());
		}

		try {
			CuentaBancaria empty = new CuentaBancaria("005", "Eve", 0.0);
			repo.agregarCuenta(empty);
			ReporteTransacciones.generarReporte(empty, "reporte_005.txt");
		} catch (HistorialVacioException ex) {
			System.out.println("Reporte vacío detectado: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("IO error: " + ex.getMessage());
		}

		try {
			ReporteTransacciones.leerReporte("archivo_inexistente.txt");
		} catch (FileNotFoundException ex) {
			System.out.println("Lectura fallida (archivo no existe): " + ex.getMessage());
		}

		System.out.println("\n=== FIN DE PRUEBAS ===");
	}
}

class CuentaBancaria {
	private final String numeroCuenta;
	private final String titular;
	protected double saldo;
	protected final List<String> historial;

	public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
		if (numeroCuenta == null || numeroCuenta.trim().isEmpty()) {
			throw new IllegalArgumentException("Número de cuenta inválido.");
		}
		if (titular == null || titular.trim().isEmpty()) {
			throw new IllegalArgumentException("Titular inválido.");
		}
		if (saldoInicial < 0) {
			throw new IllegalArgumentException("Saldo inicial no puede ser negativo.");
		}
		this.numeroCuenta = numeroCuenta;
		this.titular = titular;
		this.saldo = saldoInicial;
		this.historial = new ArrayList<>();
		if (saldoInicial > 0) {
			historial.add(String.format("Saldo inicial: %.2f", saldoInicial));
		}
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public String getTitular() {
		return titular;
	}

	public double getSaldo() {
		return saldo;
	}

	public List<String> getHistorial() {
		return Collections.unmodifiableList(historial);
	}

	public void depositar(double monto) {
		if (monto <= 0) {
			throw new IllegalArgumentException("El monto a depositar debe ser positivo.");
		}
		saldo += monto;
		historial.add(String.format("Depósito: +%.2f -> Saldo: %.2f", monto, saldo));
	}

	public void retirar(double monto) throws SaldoInsuficienteException {
		if (monto <= 0) {
			throw new IllegalArgumentException("El monto a retirar debe ser positivo.");
		}
		if (monto > saldo) {
			throw new SaldoInsuficienteException(
					"Saldo insuficiente para retirar " + monto + ". Saldo actual: " + saldo);
		}
		saldo -= monto;
		historial.add(String.format("Retiro: -%.2f -> Saldo: %.2f", monto, saldo));
	}

	public void cerrarCuenta() throws SaldoNoCeroException {
		if (saldo != 0.0) {
			throw new SaldoNoCeroException("No se puede cerrar la cuenta. Saldo debe ser cero. Saldo actual: " + saldo);
		}
		historial.add("Cuenta cerrada.");
	}

	@Override
	public String toString() {
		return String.format("Cuenta[%s, Titular=%s, Saldo=%.2f]", numeroCuenta, titular, saldo);
	}
}

class CuentaCredito extends CuentaBancaria {
	private final double limiteCredito;

	public CuentaCredito(String numeroCuenta, String titular, double saldoInicial, double limiteCredito) {
		super(numeroCuenta, titular, saldoInicial);
		if (limiteCredito < 0) {
			throw new IllegalArgumentException("Límite de crédito no puede ser negativo.");
		}
		this.limiteCredito = limiteCredito;
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}

	@Override
	public void retirar(double monto) throws LimiteCreditoExcedidoException {
		if (monto <= 0) {
			throw new IllegalArgumentException("El monto a retirar debe ser positivo.");
		}
		double disponible = this.saldo + limiteCredito;
		if (monto > disponible) {
			throw new LimiteCreditoExcedidoException(
					String.format("Retiro de %.2f excede saldo + límite. Disponible: %.2f", monto, disponible));
		}
		this.saldo -= monto;
		historial.add(String.format("Retiro con crédito: -%.2f -> Saldo: %.2f", monto, saldo));
	}
}

class RepositorioCuentas {
	private final Map<String, CuentaBancaria> cuentas = new HashMap<>();

	public void agregarCuenta(CuentaBancaria cuenta) {
		cuentas.put(cuenta.getNumeroCuenta(), cuenta);
	}

	public CuentaBancaria obtener(String numeroCuenta) {
		return cuentas.get(numeroCuenta);
	}

	public void removerCuenta(String numeroCuenta) {
		cuentas.remove(numeroCuenta);
	}

	public void transferir(String numeroOrigen, String numeroDestino, double monto)
			throws CuentaNoEncontradaException, SaldoInsuficienteException, LimiteCreditoExcedidoException {
		CuentaBancaria origen = cuentas.get(numeroOrigen);
		CuentaBancaria destino = cuentas.get(numeroDestino);
		if (origen == null) {
			throw new CuentaNoEncontradaException("Cuenta origen no encontrada: " + numeroOrigen);
		}
		if (destino == null) {
			throw new CuentaNoEncontradaException("Cuenta destino no encontrada: " + numeroDestino);
		}
		if (origen instanceof CuentaCredito) {
			((CuentaCredito) origen).retirar(monto);
		} else {
			origen.retirar(monto);
		}
		destino.depositar(monto);
	}
}

class ReporteTransacciones {
	public static void generarReporte(CuentaBancaria cuenta, String nombreArchivo)
			throws IOException, HistorialVacioException {
		List<String> historial = cuenta.getHistorial();
		if (historial.isEmpty()) {
			throw new HistorialVacioException("No hay transacciones para la cuenta " + cuenta.getNumeroCuenta());
		}
		try (PrintWriter pw = new PrintWriter(new File(nombreArchivo))) {
			pw.println("Reporte de transacciones - Cuenta: " + cuenta.getNumeroCuenta());
			pw.println("Titular: " + cuenta.getTitular());
			pw.println("Saldo actual: " + cuenta.getSaldo());
			pw.println("---- Historial ----");
			for (String linea : historial) {
				pw.println(linea);
			}
		}
	}

	public static void leerReporte(String nombreArchivo) throws FileNotFoundException {
		File f = new File(nombreArchivo);
		try (Scanner sc = new Scanner(f)) {
			System.out.println("Contenido del archivo " + nombreArchivo + ":");
			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
		}
	}
}

// Excepciones personalizadas
class SaldoInsuficienteException extends RuntimeException {
	public SaldoInsuficienteException(String msg) {
		super(msg);
	}
}

class CuentaNoEncontradaException extends RuntimeException {
	public CuentaNoEncontradaException(String msg) {
		super(msg);
	}
}

class SaldoNoCeroException extends Exception {
	public SaldoNoCeroException(String msg) {
		super(msg);
	}
}

class LimiteCreditoExcedidoException extends Exception {
	public LimiteCreditoExcedidoException(String msg) {
		super(msg);
	}
}

class HistorialVacioException extends Exception {
	public HistorialVacioException(String msg) {
		super(msg);
	}
}
