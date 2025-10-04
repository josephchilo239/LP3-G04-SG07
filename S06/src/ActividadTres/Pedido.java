package ActividadTres;

public class Pedido {
	private String nombrePlato;
	private String tipoPlato;
	private String estado;

	public Pedido(String nombrePlato, String tipoPlato) {
		this.nombrePlato = nombrePlato;
		this.tipoPlato = tipoPlato;
		this.estado = "Pendiente";
	}

	public String getNombrePlato() {
		return nombrePlato;
	}

	public String getTipoPlato() {
		return tipoPlato;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return nombrePlato + " (" + tipoPlato + ") - Estado: " + estado;
	}
}
