package Actividades;
public class Contador {
    private int valor;
    private static int acumulador = 0;
    public static final int VALOR_INICIAL = 10;

    private static int nContadores = 0;
    private static int ultimoContador = 0;

    public Contador(int valor) {
        this.valor = valor;
        acumulador += valor;
        nContadores++;
        ultimoContador = valor;
    }

    public Contador() {
        this(Contador.VALOR_INICIAL); 
    }

    public void inc() {
        this.valor++;
        acumulador++;
    }

    public int getValor() { return valor; }
    public static int getAcumulador() { return acumulador; }
    public static int getNContadores() { return nContadores; }
    public static int getUltimoContador() { return ultimoContador; }

    public class ContadorTest {
    public static void main(String[] args) {
        Contador c1 = new Contador(5);
        Contador c2 = new Contador();
        c1.inc();
        c2.inc();

        System.out.println("c1 = " + c1.getValor());
        System.out.println("c2 = " + c2.getValor());
        System.out.println("Acumulador = " + Contador.getAcumulador());
        System.out.println("N° contadores = " + Contador.getNContadores());
        System.out.println("Último valor inicial = " + Contador.getUltimoContador());
    }
    }
}

//Respuestas resumidas 

//a.1) Sí funciona igual, pero usar this.acumulador es incorrecto conceptualmente.
//a.2) Sí funciona igual, Contador.acumulador es más claro para atributos estáticos.
//a.3) Sí, valor++ y this.valor++ son equivalentes.

//b) c1=6, c2=11 (si VALOR_INICIAL=10), acumulador suma todo → correcto.
//c) No error, private solo cambia visibilidad, se accede con getter.
//d) No pasa nada, en Java int estático inicia en 0.
//e) Se agrega VALOR_INICIAL y un constructor que lo usa.
//f) this(Contador.VALOR_INICIAL) llama a otro constructor de la clase.
//g) En el test se crean objetos con ambos constructores para comprobar acumulador.
//h) new Contador(...) crearía otro objeto distinto, no inicializa el actual.
//i) Si no suma al acumulador, se rompe el registro global.
//j) Se añaden nContadores y ultimoContador, actualizados en el constructor.
