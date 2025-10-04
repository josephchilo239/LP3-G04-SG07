package EjercicioTres;

public class MainCombate {
    public static void main(String[] args) {
        Jugador jugador = new Jugador("Héroe");
        jugador.getInventario().agregarItem(new Item("Espada de Acero", 1, "Arma", "Arma poderosa que inflige gran daño"));
        jugador.getInventario().agregarItem(new Item("Poción de Vida", 3, "Poción", "Restaura 20 puntos de salud"));

        jugador.equipar(jugador.getInventario().buscarItem("Espada de Acero"));

        Enemigo enemigo = new Enemigo("Orco Guerrero", 2, "Bestia");

        CombateVista vista = new CombateVista();
        CombateControlador controlador = new CombateControlador(jugador, enemigo, vista);
        controlador.iniciarCombate();
    }
}
