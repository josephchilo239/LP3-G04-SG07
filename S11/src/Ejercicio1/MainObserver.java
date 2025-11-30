package Ejercicio1;

public class MainObserver {
    public static void main(String[] args) {

        GestorNotificacion gestor = new GestorNotificacion();

        Usuario u1 = new Usuario("Carlos");
        Usuario u2 = new Usuario("Ana");
        Usuario u3 = new Usuario("Luis");

        gestor.suscribir(u1);
        gestor.suscribir(u2);

        gestor.enviarNotificacion(new Notificacion("Nueva promoción: 50% en perfumes"));

        gestor.suscribir(u3);
        gestor.desuscribir(u1);

        gestor.enviarNotificacion(new Notificacion("Nueva actualización de productos"));
    }
}
