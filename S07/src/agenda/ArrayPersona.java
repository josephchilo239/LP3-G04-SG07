package agenda;

public class ArrayPersona {

    private Persona arreglo[];
    private final int max = 128;
    private int tamano = 0;

    public ArrayPersona() {
        this.arreglo = new Persona[this.max];
    }

    public void addArray(Persona persona) {
        if (this.tamano == max) {
            System.out.println("Se alcanzó el tamaño máximo del arreglo");
            System.exit(1);
        }
        this.arreglo[this.tamano++] = persona;
    }

    public void printArray(String nombre) {
        boolean encontrado = false;
        for (int i = 0; i < this.tamano; i++) {
            if (this.arreglo[i].getNombre().equalsIgnoreCase(nombre)) {
                System.out.println(this.arreglo[i]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró a " + nombre);
        }
    }
}