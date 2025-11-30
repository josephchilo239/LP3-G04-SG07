package Actividad1Punto3;

class EncenderCommand implements Command {
    private Televisor tv;
    public EncenderCommand(Televisor tv) { this.tv = tv; }
    public void execute() { tv.encender(); }
}

class ApagarCommand implements Command {
    private Televisor tv;
    public ApagarCommand(Televisor tv) { this.tv = tv; }
    public void execute() { tv.apagar(); }
}

class SubirVolumenCommand implements Command {
    private Televisor tv;
    public SubirVolumenCommand(Televisor tv) { this.tv = tv; }
    public void execute() { tv.subirVolumen(); }
}

class BajarVolumenCommand implements Command {
    private Televisor tv;
    public BajarVolumenCommand(Televisor tv) { this.tv = tv; }
    public void execute() { tv.bajarVolumen(); }
}

class CambiarCanalCommand implements Command {
    private Televisor tv;
    private int canal;

    public CambiarCanalCommand(Televisor tv, int canal) {
        this.tv = tv;
        this.canal = canal;
    }

    public void execute() { tv.cambiarCanal(canal); }
}
