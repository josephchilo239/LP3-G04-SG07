package Ejercicio1;

public class Usuario implements Observer {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void recibirNotificacion(Notificacion notificacion) {
        System.out.println("Usuario: " + nombre + " recibió -> " + notificacion.getMensaje());
    }
}
