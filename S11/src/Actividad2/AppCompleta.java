package Actividad2;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String msg);
}

class Cliente implements Observer {
    private String nombre;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void update(String msg) {
        System.out.println(nombre + " recibió notificación: " + msg);
    }
}

class SistemaNotificaciones {
    private List<Observer> clientes = new ArrayList<>();

    public void suscribir(Observer o) {
        clientes.add(o);
    }

    public void notificar(String msg) {
        for (Observer c : clientes) {
            c.update(msg);
        }
    }
}

interface Descuento {
    double aplicar(double precio);
}

class Normal implements Descuento {
    public double aplicar(double p) { return p; }
}

class BlackFriday implements Descuento {
    public double aplicar(double p) { return p * 0.60; }
}

interface Command {
    void execute();
}

class ConfirmarCompra implements Command {
    public void execute() {
        System.out.println("Compra confirmada.");
    }
}

class CancelarCompra implements Command {
    public void execute() {
        System.out.println("Compra cancelada.");
    }
}

public class AppCompleta {
    public static void main(String[] args) {

        SistemaNotificaciones sistema = new SistemaNotificaciones();
        sistema.suscribir(new Cliente("Carlos"));
        sistema.suscribir(new Cliente("Ana"));

        sistema.notificar("Nuevo producto disponible.");

        Descuento desc = new BlackFriday();
        double precioFinal = desc.aplicar(200);
        System.out.println("Precio final: " + precioFinal);

        Command confirmar = new ConfirmarCompra();
        Command cancelar = new CancelarCompra();

        confirmar.execute();
        cancelar.execute();
    }
}
