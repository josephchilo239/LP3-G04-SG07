package Ejercicio3;

public class MainCommand {

    public static void main(String[] args) {

        Televisor tv = new Televisor();
        ControlRemoto control = new ControlRemoto();

        control.setCommand(new EncenderCommand(tv));
        control.presionarBoton();

        control.setCommand(new SubirVolumenCommand(tv));
        control.presionarBoton();

        control.setCommand(new CambiarCanalCommand(tv, 12));
        control.presionarBoton();

        control.setCommand(new BajarVolumenCommand(tv));
        control.presionarBoton();

        control.setCommand(new ApagarCommand(tv));
        control.presionarBoton();
    }
}
