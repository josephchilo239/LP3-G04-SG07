package Ejercicios;

class Empleado {
	private String nombre;
	private double salario;
	private String departamento;

	public Empleado(String nombre, double salario, String departamento) {
		this.nombre = nombre;
		this.salario = salario;
		this.departamento = departamento;
	}

	public String getNombre() {
		return nombre;
	}

	public double getSalario() {
		return salario;
	}

	public String getDepartamento() {
		return departamento;
	}
}

class CalculadoraPago {
	public double calcularPagoMensual(Empleado empleado) {
		return empleado.getSalario() / 12;
	}
}

public class uno {
	public static void main(String[] args) {
		Empleado e = new Empleado("Joss", 12000, "IT");
		CalculadoraPago calc = new CalculadoraPago();
		System.out.println("Pago mensual de " + e.getNombre() + ": " + calc.calcularPagoMensual(e));
	}
}
