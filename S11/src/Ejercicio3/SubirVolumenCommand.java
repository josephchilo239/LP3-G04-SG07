package Ejercicio3;

public class SubirVolumenCommand implements Command {
    private Televisor tv;

    public SubirVolumenCommand(Televisor tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.subirVolumen();
    }
}
