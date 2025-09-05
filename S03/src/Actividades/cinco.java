package Actividades;

public class cinco {
	//Problema detectado
	//NotificadorReserva dependía directamente de EnviadorCorreo, lo que lo hacía rígido.
	//Solución
	//Crear interfaz CanalNotificacion.
	//Inyectar la implementación (Correo, SMS, Slack).
	interface CanalNotificacion {
	    void enviar(String destino, String mensaje);
	}

	class EnviadorCorreo implements CanalNotificacion {
	    public void enviar(String destino, String mensaje) {
	        System.out.println("Correo a " + destino + ": " + mensaje);
	    }
	}

	class EnviadorSMS implements CanalNotificacion {
	    public void enviar(String destino, String mensaje) {
	        System.out.println("SMS a " + destino + ": " + mensaje);
	    }
	}

	class NotificadorReserva {
	    private CanalNotificacion canal;

	    public NotificadorReserva(CanalNotificacion canal) {
	        this.canal = canal;
	    }

	    public void notificar(String destino, String mensaje) {
	        canal.enviar(destino, mensaje);
	    }
	}
}
