package EjercicioTres;
import java.util.Random;

public class Jugador {
    private String nombre;
    private int salud;
    private int nivel;
    private InventarioModelo inventario;
    private Item armaEquipada;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.salud = 100;
        this.nivel = 1;
        this.inventario = new InventarioModelo();
    }

    public void equipar(Item arma) {
        if (arma != null && arma.getTipo().equalsIgnoreCase("Arma")) {
            this.armaEquipada = arma;
            System.out.println(nombre + " ha equipado " + arma.getNombre());
        } else {
            System.out.println("No se puede equipar ese objeto.");
        }
    }

    public void usarItem(String nombreItem) {
        Item item = inventario.buscarItem(nombreItem);
        if (item != null && item.getCantidad() > 0) {
            if (item.getTipo().equalsIgnoreCase("Poción")) {
                salud += 20;
                if (salud > 100) salud = 100;
                item.usarItem();
                System.out.println(nombre + " usa " + item.getNombre() + " y recupera salud. HP actual: " + salud);
            }
        } else {
            System.out.println("No tienes ese objeto o se agotó.");
        }
    }

    public void atacar(Enemigo enemigo) {
        Random r = new Random();
        int daño = 10 + r.nextInt(10);
        if (armaEquipada != null) daño += 15;
        enemigo.recibirDaño(daño);
        System.out.println(nombre + " ataca a " + enemigo.getNombre() + " causando " + daño + " de daño.");
    }

    public void recibirDaño(int daño) {
        salud -= daño;
        if (salud < 0) salud = 0;
        System.out.println(nombre + " recibe " + daño + " de daño. HP: " + salud);
    }

    public boolean estaVivo() { return salud > 0; }

    public InventarioModelo getInventario() { return inventario; }
    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
}
