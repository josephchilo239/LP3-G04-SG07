package Actividades;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Cliente {
    private String nombre;
    private String contacto;

    public Cliente(String nombre, String contacto) {
        this.nombre = nombre;
        this.contacto = contacto;
    }

    public String getNombre() { return nombre; }
    public String getContacto() { return contacto; }
}

class Reserva {
    private Habitacion habitacion;
    private Cliente cliente;
    private LocalDate desde;
    private LocalDate hasta;

    public Reserva(Habitacion habitacion, Cliente cliente, LocalDate desde, LocalDate hasta) {
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.desde = desde;
        this.hasta = hasta;
    }

    public LocalDate getDesde() { return desde; }
    public LocalDate getHasta() { return hasta; }
    public Habitacion getHabitacion() { return habitacion; }
    public Cliente getCliente() { return cliente; }
}

class Habitacion {
    private String id;
    private String tipo;
    private double precioBase;
    private boolean disponible = true;
    private List<Reserva> reservas = new ArrayList<>();

    public Habitacion(String id, String tipo, double precioBase) {
        this.id = id;
        this.tipo = tipo;
        this.precioBase = precioBase;
    }

    // Verificar disponibilidad
    public boolean estaDisponible(LocalDate desde, LocalDate hasta) {
        for (Reserva r : reservas) {
            if (!(hasta.isBefore(r.getDesde()) || desde.isAfter(r.getHasta()))) {
                return false;
            }
        }
        return disponible;
    }

    // Reservar habitación
    public void reservar(Cliente cliente, LocalDate desde, LocalDate hasta) {
        if (estaDisponible(desde, hasta)) {
            Reserva reserva = new Reserva(this, cliente, desde, hasta);
            reservas.add(reserva);
            disponible = false;
            System.out.println("Reserva creada para " + cliente.getNombre());
        } else {
            System.out.println("La habitación no está disponible.");
        }
    }

    // Cancelar reserva
    public void cancelarReserva(Reserva reserva) {
        if (reservas.remove(reserva)) {
            disponible = true;
            System.out.println("Reserva cancelada para " + reserva.getCliente().getNombre());
        }
    }

    public double calcularPrecio(LocalDate desde, LocalDate hasta) {
        long dias = java.time.temporal.ChronoUnit.DAYS.between(desde, hasta);
        return dias * precioBase;
    }

    public void generarInformeOcupacion() {
        System.out.println("Informe de ocupación de habitación " + id + ":");
        for (Reserva r : reservas) {
            System.out.println("Reservada por " + r.getCliente().getNombre() +
                    " del " + r.getDesde() + " al " + r.getHasta());
        }
    }

    public String getId() { return id; }
    public String getTipo() { return tipo; }
}

public class original{
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Andry", "andry@email.com");
        Habitacion hab1 = new Habitacion("H1", "Simple", 50.0);

        LocalDate desde = LocalDate.of(2025, 9, 10);
        LocalDate hasta = LocalDate.of(2025, 9, 12);

        hab1.reservar(cliente1, desde, hasta);

        hab1.generarInformeOcupacion();

        double precio = hab1.calcularPrecio(desde, hasta);
        System.out.println("Precio total: " + precio);
    }
}
