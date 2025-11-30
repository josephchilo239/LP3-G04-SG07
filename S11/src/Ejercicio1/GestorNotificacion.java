package Ejercicio1;
import java.util.ArrayList;
import java.util.List;

public class GestorNotificacion {

    private List<Observer> usuarios = new ArrayList<>();

    public void suscribir(Observer usuario) {
        usuarios.add(usuario);
    }

    public void desuscribir(Observer usuario) {
        usuarios.remove(usuario);
    }

    public void enviarNotificacion(Notificacion notificacion) {
        for (Observer usuario : usuarios) {
            usuario.recibirNotificacion(notificacion);
        }
    }
}
