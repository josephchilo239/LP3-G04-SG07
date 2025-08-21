package Ejercicios;
import java.util.Scanner;

abstract class Persona {
    protected String nombre;
    protected String dni;

    public Persona(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public abstract String getDescripcion();

    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
}

class Estudiante extends Persona {
    private String codigo;

    public Estudiante(String nombre, String dni, String codigo) {
        super(nombre, dni);
        this.codigo = codigo;
    }

    @Override
    public String getDescripcion() {
        return "Estudiante: " + nombre + " - Código: " + codigo;
    }
}

class Profesor extends Persona {
    private String especialidad;

    public Profesor(String nombre, String dni, String especialidad) {
        super(nombre, dni);
        this.especialidad = especialidad;
    }

    @Override
    public String getDescripcion() {
        return "Profesor: " + nombre + " - Especialidad: " + especialidad;
    }
}

interface Mostrable {
    String mostrarInfo();
}

class Curso implements Mostrable {
    private static final int CAPACIDAD_MAX = 50; 
    private static int totalCursos = 0;        
    private String nombre;
    private String categoria;
    private Profesor profesor;                 
    private Estudiante[] estudiantes;        
    private int numEstudiantes;                 

    public Curso(String nombre, String categoria, Profesor profesor) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.profesor = profesor;
        this.estudiantes = new Estudiante[CAPACIDAD_MAX];
        this.numEstudiantes = 0;
        totalCursos++;
    }

    public void inscribir(Estudiante e) {
        if (numEstudiantes < CAPACIDAD_MAX) {
            estudiantes[numEstudiantes] = e;
            numEstudiantes++;
            System.out.println("Estudiante inscrito en " + nombre);
        } else {
            System.out.println("El curso está lleno.");
        }
    }

    public static int getTotalCursos() { return totalCursos; }

    @Override
    public String mostrarInfo() {
        return "Curso: " + nombre + " (" + categoria + ") - Profesor: " +
               profesor.getNombre() + " - Estudiantes: " + numEstudiantes;
    }
}

class SistemaGestion {
    private Curso[] cursos;
    private int numCursos;

    public SistemaGestion() {
        cursos = new Curso[20];
        numCursos = 0;
    }

    public void agregarCurso(Curso curso) {
        cursos[numCursos] = curso;
        numCursos++;
    }

    public void mostrarCursos() {
        for (int i = 0; i < numCursos; i++) {
            System.out.println(cursos[i].mostrarInfo());
        }
    }
}

public class EjercicioOne {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaGestion sistema = new SistemaGestion();

        Profesor p1 = new Profesor("Juan Pérez", "12345678", "Matemáticas");
        Profesor p2 = new Profesor("Ana Torres", "87654321", "Programación");

        Curso c1 = new Curso("Álgebra", "Matemáticas", p1);
        Curso c2 = new Curso("Java Básico", "Programación", p2);
        sistema.agregarCurso(c1);
        sistema.agregarCurso(c2);

        boolean salir = false;
        while (!salir) {
            System.out.println("\n ---MENÚ CURSOS UNIVERSITARIOS---");
            System.out.println("1. Inscribir estudiante en Álgebra");
            System.out.println("2. Inscribir estudiante en Java Básico");
            System.out.println("3. Mostrar cursos disponibles");
            System.out.println("4. Mostrar cantidad total de cursos");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nombre del estudiante: ");
                    String n1 = sc.nextLine();
                    System.out.print("DNI: ");
                    String d1 = sc.nextLine();
                    System.out.print("Código: ");
                    String cod1 = sc.nextLine();
                    Estudiante e1 = new Estudiante(n1, d1, cod1);
                    c1.inscribir(e1);
                    break;
                case 2:
                    System.out.print("Nombre del estudiante: ");
                    String n2 = sc.nextLine();
                    System.out.print("DNI: ");
                    String d2 = sc.nextLine();
                    System.out.print("Código: ");
                    String cod2 = sc.nextLine();
                    Estudiante e2 = new Estudiante(n2, d2, cod2);
                    c2.inscribir(e2);
                    break;
                case 3:
                    sistema.mostrarCursos();
                    break;
                case 4:
                	System.out.println("Total de cursos creados: " + Curso.getTotalCursos());
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }
}
