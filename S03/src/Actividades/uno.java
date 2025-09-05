package Actividades;

public class uno {
	//Problema detectado
	//La clase Habitacion tenía demasiadas responsabilidades:
	//Verificar disponibilidad.
	//Cambiar estado (reservada, disponible).
	//Calcular precios.
	//Generar informes de ocupación.
	//Esto la convierte en una clase God Object (difícil de mantener).
	//Solución
	//Separar responsabilidades:
	//Habitacion → se encarga de sus datos básicos y estado.
	//GestorDisponibilidadHabitacion → se encarga de gestionar reservas y disponibilidad.
	class Habitacion {
	    private String id;
	    private String tipo;
	    private double precioBase;
	    private EstadoHabitacion estado;
	    private GestorDisponibilidadHabitacion gestorDisponibilidad;

	    public Habitacion(String id, String tipo, double precioBase, GestorDisponibilidadHabitacion gestor) {
	        this.id = id;
	        this.tipo = tipo;
	        this.precioBase = precioBase;
	        this.estado = EstadoHabitacion.DISPONIBLE;
	        this.gestorDisponibilidad = gestor;
	    }

	    public void marcarReservada() { this.estado = EstadoHabitacion.RESERVADA; }
	    public void marcarDisponible() { this.estado = EstadoHabitacion.DISPONIBLE; }

	    public boolean estaDisponible(LocalDate desde, LocalDate hasta) {
	        return gestorDisponibilidad.estaDisponible(this, desde, hasta);
	    }
	}
	class GestorDisponibilidadHabitacion {
	    private List<Reserva> reservas = new ArrayList<>();

	    public boolean estaDisponible(Habitacion habitacion, LocalDate desde, LocalDate hasta) {
	        for (Reserva r : reservas) {
	            if (r.getHabitacion().equals(habitacion) && fechasSeSolapan(desde, hasta, r.getDesde(), r.getHasta())) {
	                return false;
	            }
	        }
	        return true;
	    }

	    private boolean fechasSeSolapan(LocalDate d1, LocalDate f1, LocalDate d2, LocalDate f2) {
	        return d1.isBefore(f2) && d2.isBefore(f1);
	    }

	    public void registrarReserva(Reserva reserva) {
	        reservas.add(reserva);
	    }
	}
}
