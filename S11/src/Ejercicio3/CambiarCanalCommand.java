package Ejercicio3;

public class CambiarCanalCommand implements Command {
    private Televisor tv;
    private int canal;

    public CambiarCanalCommand(Televisor tv, int canal) {
        this.tv = tv;
        this.canal = canal;
    }

    @Override
    public void execute() {
        tv.cambiarCanal(canal);
    }
}
