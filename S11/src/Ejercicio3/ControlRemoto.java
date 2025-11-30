package Ejercicio3;

public class ControlRemoto {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void presionarBoton() {
        command.execute();
    }
}
