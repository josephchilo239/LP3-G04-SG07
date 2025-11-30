package Ejercicio3;

public class Televisor {
	
    private boolean encendido = false;
    private int volumen = 10;
    private int canal = 5;

    public void encender() {
        encendido = true;
        System.out.println("TV encendida");
    }

    public void apagar() {
        encendido = false;
        System.out.println("TV apagada");
    }

    public void subirVolumen() {
        volumen++;
        System.out.println("Volumen actual: " + volumen);
    }

    public void bajarVolumen() {
        volumen--;
        System.out.println("Volumen actual: " + volumen);
    }

    public void cambiarCanal(int nuevoCanal) {
        canal = nuevoCanal;
        System.out.println("Canal cambiado a: " + canal);
    }
}
