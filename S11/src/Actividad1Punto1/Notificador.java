package Actividad1Punto1;
import java.util.ArrayList;
import java.util.List;

class Notificador {
    private List<Observer> usuarios = new ArrayList<>();

    public void suscribir(Observer usuario) {
        usuarios.add(usuario);
    }

    public void desuscribir(Observer usuario) {
        usuarios.remove(usuario);
    }

    public void notificar(String mensaje) {
        for (Observer u : usuarios) {
            u.update(mensaje);
        }
    }
}
