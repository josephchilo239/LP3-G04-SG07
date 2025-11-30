package Actividad1Punto1;

public class MainObserver {
    public static void main(String[] args) {
        Notificador notificador = new Notificador();

        Usuario u1 = new Usuario("Carlos");
        Usuario u2 = new Usuario("Ana");
        Usuario u3 = new Usuario("Luis");

        notificador.suscribir(u1);
        notificador.suscribir(u2);
        notificador.suscribir(u3);

        notificador.notificar("Nueva promoción disponible!");
    }
}
