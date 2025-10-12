package serializacion;
import java.io.Serializable;

public class Persona implements Serializable {
    private String nif;
    private String nombre;
    private int edad;

    public Persona(String nif, String nombre, int edad) {
        this.nif = nif;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Persona() {
        // Constructor vacío
    }

    public String getNif() { return nif; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }

    @Override
    public String toString() {
        return nif + "\t" + nombre + "\t" + edad;
    }
}