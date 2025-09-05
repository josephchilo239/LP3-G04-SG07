package Actividades;

public class tres {
	//Problema detectado
	//Las subclases de Habitacion podrían romper el contrato de la clase base (ejemplo: no aceptar check-in, cambiar precio de forma incoherente).
	//Solución
	//Asegurarse que todas las subclases respeten el contrato de Habitacion.
	//Si un método no aplica, lanzar UnsupportedOperationException.
	abstract class Habitacion {
	    public abstract double calcularPrecio(LocalDate desde, LocalDate hasta);
	}

	class HabitacionSuite extends Habitacion {
	    @Override
	    public double calcularPrecio(LocalDate desde, LocalDate hasta) {
	        long noches = ChronoUnit.DAYS.between(desde, hasta);
	        return noches * 200 + 50; // cumple contrato de calcularPrecio
	    }
	}

	class HabitacionDormitorioCompartido extends Habitacion {
	    @Override
	    public double calcularPrecio(LocalDate desde, LocalDate hasta) {
	        long noches = ChronoUnit.DAYS.between(desde, hasta);
	        return noches * 50; // también respeta contrato
	    }
	}
}
