package Actividades;

public class Coche {
    private String marca;
    private String modelo;
    private int anioFabricacion;
    private double precio;
    private int velocidadActual;
    private boolean encendido;

    public Coche() {
        this.marca = "Desconocida";
        this.modelo = "Genérico";
        this.anioFabricacion = 2000;
        this.precio = 10000;
        this.velocidadActual = 0;
        this.encendido = false;
    }

    public Coche(String marca, String modelo, int anioFabricacion, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.precio = precio;
        this.velocidadActual = 0;
        this.encendido = false;
    }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAnioFabricacion() { return anioFabricacion; }
    public void setAnioFabricacion(int anioFabricacion) { this.anioFabricacion = anioFabricacion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getVelocidadActual() { return velocidadActual; }

    public boolean isEncendido() { return encendido; }

    public boolean aplicarDescuento(double descuento) {
        if (anioFabricacion < 2010) {
            precio -= precio * (descuento / 100);
            return true; 
        }
        return false; 
    }

    public void encender() {
        if (!encendido) {
            encendido = true;
            System.out.println("El coche " + modelo + " está encendido.");
        } else {
            System.out.println("El coche ya estaba encendido.");
        }
    }

    public void apagar() {
        if (encendido) {
            encendido = false;
            velocidadActual = 0;
            System.out.println("El coche " + modelo + " se ha apagado.");
        } else {
            System.out.println("El coche ya estaba apagado.");
        }
    }

    public void acelerar(int incremento) {
        if (encendido) {
            velocidadActual += incremento;
            System.out.println("El coche " + modelo + " acelera a " + velocidadActual + " km/h.");
        } else {
            System.out.println("No se puede acelerar, el coche está apagado.");
        }
    }

    public void frenar(int decremento) {
        if (encendido) {
            if (velocidadActual - decremento >= 0) {
                velocidadActual -= decremento;
            } else {
                velocidadActual = 0;
            }
            System.out.println("El coche " + modelo + " frena a " + velocidadActual + " km/h.");
        } else {
            System.out.println("No se puede frenar, el coche está apagado.");
        }
    }

    public void mostrarDatos() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Año de fabricación: " + anioFabricacion);
        System.out.println("Precio: $" + precio);
        System.out.println("Velocidad actual: " + velocidadActual + " km/h");
        System.out.println("Encendido: " + (encendido ? "Sí" : "No"));
        System.out.println("-------------------------------");
    }
    public class EjemploCoche {
        public static void main(String[] args) {
            Coche cocheDeportivo = new Coche("Ferrari", "F8", 2009, 250000);
            Coche cocheTodoTerreno = new Coche("Toyota", "Land Cruiser", 2015, 75000);

            cocheDeportivo.mostrarDatos();
            cocheTodoTerreno.mostrarDatos();

            if (cocheDeportivo.aplicarDescuento(10)) {
                System.out.println("Se aplicó descuento al coche deportivo.");
            } else {
                System.out.println("No se aplicó descuento al coche deportivo.");
            }

            if (cocheTodoTerreno.aplicarDescuento(10)) {
                System.out.println("Se aplicó descuento al coche todoterreno.");
            } else {
                System.out.println("No se aplicó descuento al coche todoterreno.");
            }

            cocheDeportivo.encender();
            cocheTodoTerreno.encender();

            cocheDeportivo.acelerar(100);
            cocheDeportivo.frenar(30);

            cocheTodoTerreno.acelerar(80);
            cocheTodoTerreno.frenar(50);

            cocheDeportivo.apagar();
            cocheTodoTerreno.apagar();

            cocheDeportivo.mostrarDatos();
            cocheTodoTerreno.mostrarDatos();
        }
    }
}
