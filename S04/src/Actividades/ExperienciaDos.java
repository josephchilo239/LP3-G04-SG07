package Actividades;

//Experiencia2.java
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

class CuentaBancaria2 {
	private String numeroCuenta;
	private String titular;
	private double saldo;

	public CuentaBancaria2(String numeroCuenta, String titular, double saldoInicial) {
		if (saldoInicial < 0) {
			throw new IllegalArgumentException("Saldo inicial inválido.");
		}
		this.numeroCuenta = numeroCuenta;
		this.titular = titular;
		this.saldo = saldoInicial;
	}

	public void transferir(CuentaBancaria2 destino, double monto) {
		if (destino == null) {
			throw new CuentaNoEncontradaException("La cuenta destino no existe.");
		}
		if (monto > saldo) {
			throw new SaldoInsuficienteException("Saldo insuficiente para transferir.");
		}
		saldo -= monto;
		destino.depositar(monto);
	}

	public void depositar(double monto) {
		if (monto <= 0) {
			throw new IllegalArgumentException("Depósito inválido.");
		}
		saldo += monto;
	}

	public void cerrarCuenta() throws SaldoNoCeroException {
		if (saldo != 0) {
			throw new SaldoNoCeroException("La cuenta no puede cerrarse con saldo distinto de 0.");
		}
		System.out.println("Cuenta cerrada exitosamente.");
	}
}

public class ExperienciaDos {
	public static void main(String[] args) {
		try {
			CuentaBancaria2 c1 = new CuentaBancaria2("001", "Alice", 500);
			CuentaBancaria2 c2 = new CuentaBancaria2("002", "Bob", 100);
			c1.transferir(c2, 200);
			System.out.println("Transferencia realizada.");
			c1.cerrarCuenta(); // lanza excepción porque saldo != 0
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
