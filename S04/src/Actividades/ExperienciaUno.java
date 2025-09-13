package Actividades;

//Experiencia1.java
class SaldoInsuficienteException extends RuntimeException {
	public SaldoInsuficienteException(String msg) {
		super(msg);
	}
}

class CuentaBancaria {
	private String numeroCuenta;
	private String titular;
	private double saldo;

	public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
		if (saldoInicial < 0) {
			throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
		}
		this.numeroCuenta = numeroCuenta;
		this.titular = titular;
		this.saldo = saldoInicial;
	}

	public void depositar(double monto) {
		if (monto <= 0) {
			throw new IllegalArgumentException("El depósito debe ser positivo.");
		}
		saldo += monto;
	}

	public void retirar(double monto) {
		if (monto <= 0) {
			throw new IllegalArgumentException("El retiro debe ser positivo.");
		}
		if (monto > saldo) {
			throw new SaldoInsuficienteException("Saldo insuficiente.");
		}
		saldo -= monto;
	}

	public double getSaldo() {
		return saldo;
	}
}

public class ExperienciaUno {
	public static void main(String[] args) {
		try {
			CuentaBancaria c1 = new CuentaBancaria("001", "Alice", 500);
			c1.depositar(200);
			c1.retirar(100);
			System.out.println("Saldo final: " + c1.getSaldo());
			c1.retirar(1000); // lanza excepción
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
