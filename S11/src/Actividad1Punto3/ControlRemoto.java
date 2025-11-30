package Actividad1Punto3;

class ControlRemoto {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void presionarBoton() {
        command.execute();
    }
}
