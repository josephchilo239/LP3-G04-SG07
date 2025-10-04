package EjercicioTres;
import java.util.Random;

public class CombateControlador {
    private Jugador jugador;
    private Enemigo enemigo;
    private CombateVista vista;

    public CombateControlador(Jugador jugador, Enemigo enemigo, CombateVista vista) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.vista = vista;
    }

    public void iniciarCombate() {
        vista.mostrarMensaje("¡Comienza el combate entre " + jugador.getNombre() + " y " + enemigo.getNombre() + "!");

        while (jugador.estaVivo() && enemigo.estaVivo()) {
            vista.mostrarEstado(jugador, enemigo);
            String accion = vista.leerAccion();

            switch (accion) {
                case "1": // Atacar
                    jugador.atacar(enemigo);
                    break;

                case "2": // Usar objeto
                    vista.mostrarInventario(jugador.getInventario().getItems());
                    String nombreItem = vista.leerTexto("¿Qué objeto deseas usar?");
                    jugador.usarItem(nombreItem);
                    break;

                case "3": // Pasar turno
                    vista.mostrarMensaje(jugador.getNombre() + " espera su turno...");
                    break;

                default:
                    vista.mostrarMensaje("Opción no válida.");
            }

            // Turno enemigo si sigue vivo
            if (enemigo.estaVivo()) {
                if (new Random().nextBoolean()) {
                    enemigo.atacar(jugador);
                } else {
                    vista.mostrarMensaje(enemigo.getNombre() + " duda y no ataca este turno.");
                }
            }
        }

        if (jugador.estaVivo()) vista.mostrarMensaje("¡" + jugador.getNombre() + " ha ganado!");
        else vista.mostrarMensaje("El enemigo " + enemigo.getNombre() + " ha vencido...");
    }
}
