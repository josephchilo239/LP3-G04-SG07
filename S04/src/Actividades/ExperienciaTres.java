package Actividades;

//Experiencia3.java
class LimiteCreditoExcedidoException extends Exception {
	public LimiteCreditoExcedidoException(String msg) {
		super(msg);
	}
}

class CuentaCredito extends CuentaBancaria2 {
	private double limiteCredito;

	public CuentaCredito(String numeroCuenta, String titular, double saldoInicial, double limiteCredito) {
		super(numeroCuenta, titular, saldoInicial);
		this.limiteCredito = limiteCredito;
	}

	@Override
	public void transferir(CuentaBancaria2 destino, double monto) throws LimiteCreditoExcedidoException {
		if (destino == null) {
			throw new CuentaNoEncontradaException("Cuenta destino no encontrada.");
		}
		if (monto > (getSaldo() + limiteCredito)) {
			throw new LimiteCreditoExcedidoException("Se excedió el límite de crédito.");
		}
		super.transferir(destino, monto);
	}
}

public class ExperienciaTres {
	public static void main(String[] args) {
		try {
			CuentaCredito c1 = new CuentaCredito("001", "Alice", 100, 200);
			CuentaBancaria2 c2 = new CuentaBancaria2("002", "Bob", 50);

			c1.transferir(c2, 250); // permitido
			System.out.println("Transferencia con crédito realizada.");

			c1.transferir(c2, 200); // excede límite → excepción
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
