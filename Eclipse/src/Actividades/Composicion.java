package Actividades;

public class Composicion {
	public class Persona {
	    private int id;
	    private String nombre;
	    private String apellido;
	    private Cuenta cuenta;

	    public Persona(int id, String nombre, String apellido, int numeroCuenta) {
	        this.id = id;
	        this.nombre = nombre;
	        this.apellido = apellido;
	        this.cuenta = new Cuenta(numeroCuenta); 
	    }

	    public int getId() { return id; }
	    public void setId(int id) { this.id = id; }

	    public String getNombre() { return nombre; }
	    public void setNombre(String nombre) { this.nombre = nombre; }

	    public String getApellido() { return apellido; }
	    public void setApellido(String apellido) { this.apellido = apellido; }

	    public Cuenta getCuenta() { return cuenta; }
	    public void setCuenta(Cuenta cuenta) { this.cuenta = cuenta; }
	    @Override
	    public String toString() {
	        return "Persona [ID=" + id + ", Nombre=" + nombre + " " + apellido + "]\n" +
	               "  --> " + cuenta.toString();
	    }
	}

	public class Cuenta {
	    private int numero;
	    private double saldo;

	    public Cuenta(int numero, double saldo) {
	        this.numero = numero;
	        this.saldo = saldo;
	    }

	    public Cuenta(int numero) {
	        this(numero, 0);
	    }

	    public int getNumero() { return numero; }
	    public void setNumero(int numero) { this.numero = numero; }

	    public double getSaldo() { return saldo; }
	    public void setSaldo(double saldo) { this.saldo = saldo; }

	    @Override
	    public String toString() {
	        return "Cuenta [Número=" + numero + ", Saldo=" + saldo + "]";
	    }
	}

}
