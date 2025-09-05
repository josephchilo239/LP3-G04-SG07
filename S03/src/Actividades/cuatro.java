package Actividades;

public class cuatro {
	//Problema detectado
	//Una interfaz gigante ServicioHabitacion obligaba a todas las habitaciones a implementar métodos que no usan.
	//Solución
	//Separar en interfaces pequeñas y específicas:
	interface ServicioLimpieza {
	    void solicitarLimpieza();
	}

	interface ServicioComida {
	    void solicitarComida(String pedido);
	}

	interface ServicioLavanderia {
	    void solicitarLavanderia(int kilos);
	}

	// Una Suite tiene todos los servicios
	class HabitacionSuiteConServicios implements ServicioLimpieza, ServicioComida, ServicioLavanderia {
	    public void solicitarLimpieza() { System.out.println("Limpieza programada."); }
	    public void solicitarComida(String pedido) { System.out.println("Pedido de comida: " + pedido); }
	    public void solicitarLavanderia(int kilos) { System.out.println("Lavandería para " + kilos + "kg."); }
	}

	// Una Habitación Económica solo ofrece limpieza
	class HabitacionEconomicaConServicios implements ServicioLimpieza {
	    public void solicitarLimpieza() { System.out.println("Limpieza básica programada."); }
	}
}
