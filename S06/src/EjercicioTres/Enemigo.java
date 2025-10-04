package EjercicioTres;
import java.util.Random;

public class Enemigo {
    private String nombre;
    private int salud;
    private int nivel;
    private String tipo;

    public Enemigo(String nombre, int nivel, String tipo) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = tipo;
        this.salud = 50 + nivel * 10;
    }

    public void atacar(Jugador jugador) {
        Random r = new Random();
        int daño = 5 + r.nextInt(15);
        jugador.recibirDaño(daño);
        System.out.println(nombre + " (" + tipo + ") ataca causando " + daño + " de daño.");
    }

    public void recibirDaño(int daño) {
        salud -= daño;
        if (salud < 0) salud = 0;
        System.out.println(nombre + " recibe " + daño + " de daño. HP: " + salud);
    }

    public boolean estaVivo() { return salud > 0; }

    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
}
