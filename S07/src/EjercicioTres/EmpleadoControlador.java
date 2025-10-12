package EjercicioTres;
import java.io.*;
import java.util.*;

public class EmpleadoControlador {
    private final String archivo = "empleados.txt";

    public List<Empleado> leerEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    int numero = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    double sueldo = Double.parseDouble(partes[2]);
                    empleados.add(new Empleado(numero, nombre, sueldo));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se creará uno nuevo al guardar datos.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return empleados;
    }

    public void guardarEmpleados(List<Empleado> empleados) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Empleado emp : empleados) {
                pw.println(emp.toArchivo());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar empleados: " + e.getMessage());
        }
    }

    public void agregarEmpleado(Empleado e) {
        List<Empleado> empleados = leerEmpleados();
        for (Empleado emp : empleados) {
            if (emp.getNumero() == e.getNumero()) {
                System.out.println("Ya existe un empleado con ese número.");
                return;
            }
        }
        empleados.add(e);
        guardarEmpleados(empleados);
        System.out.println("Empleado agregado correctamente.");
    }

    public Empleado buscarEmpleado(int numero) {
        for (Empleado emp : leerEmpleados()) {
            if (emp.getNumero() == numero) return emp;
        }
        return null;
    }

    public void eliminarEmpleado(int numero) {
        List<Empleado> empleados = leerEmpleados();
        boolean eliminado = empleados.removeIf(emp -> emp.getNumero() == numero);
        if (eliminado) {
            guardarEmpleados(empleados);
            System.out.println("Empleado eliminado correctamente.");
        } else {
            System.out.println("No se encontró el empleado.");
        }
    }
}
