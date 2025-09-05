package Actividades;

public class dos {
	//Problema detectado
	//La clase Reserva tenía la lógica de cancelación interna → difícil de extender si cambian las reglas.
	//Solución
	//Crear la interfaz PoliticaCancelacion.
	//Implementar políticas distintas sin tocar Reserva.
	interface PoliticaCancelacion {
	    boolean puedeCancelar(Reserva reserva, LocalDate fechaSolicitud);
	    double calcularPenalizacion(Reserva reserva, LocalDate fechaSolicitud);
	}

	class PoliticaCancelacionFlexible implements PoliticaCancelacion {
	    public boolean puedeCancelar(Reserva r, LocalDate fechaSolicitud) {
	        return fechaSolicitud.isBefore(r.getDesde().minusDays(1));
	    }
	    public double calcularPenalizacion(Reserva r, LocalDate f) { return 0; }
	}

	class PoliticaCancelacionModerada implements PoliticaCancelacion {
	    public boolean puedeCancelar(Reserva r, LocalDate fechaSolicitud) {
	        return fechaSolicitud.isBefore(r.getDesde().minusDays(3));
	    }
	    public double calcularPenalizacion(Reserva r, LocalDate f) { return r.getMontoPagado() * 0.5; }
	}

	class PoliticaCancelacionEstricta implements PoliticaCancelacion {
	    public boolean puedeCancelar(Reserva r, LocalDate f) { return false; }
	    public double calcularPenalizacion(Reserva r, LocalDate f) { return r.getMontoPagado(); }
	}
	class Reserva {
	    private PoliticaCancelacion politica;
	    private double montoPagado;
	    private LocalDate desde;

	    public Reserva(PoliticaCancelacion politica, double monto, LocalDate desde) {
	        this.politica = politica;
	        this.montoPagado = monto;
	        this.desde = desde;
	    }

	    public CancelacionResultado cancelar(LocalDate fechaSolicitud) {
	        if (politica.puedeCancelar(this, fechaSolicitud)) {
	            double penal = politica.calcularPenalizacion(this, fechaSolicitud);
	            return new CancelacionResultado(true, "Cancelada con penalización de " + penal);
	        }
	        return new CancelacionResultado(false, "No se puede cancelar según política.");
	    }

	    public double getMontoPagado() { return montoPagado; }
	    public LocalDate getDesde() { return desde; }
	}

}
