package Actividades;
import java.io.*;
import java.util.Scanner;

public class Tres {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FileOutputStream fos = null;
        DataOutputStream salida = null;
        try {
            System.out.print("Número de filas: ");
            int filas;
            do {
                filas = sc.nextInt();
            } while (filas <= 0);
            System.out.print("Número de columnas: ");
            int columnas;
            do {
                columnas = sc.nextInt();
            } while (columnas <= 0);
            double[][] matriz = new double[filas][columnas];
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    System.out.print("matriz[" + i + "][" + j + "] = ");
                    matriz[i][j] = sc.nextDouble();
                }
            }
            fos = new FileOutputStream("matriz.dat");
            salida = new DataOutputStream(fos);
            salida.writeInt(filas);
            salida.writeInt(columnas);
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    salida.writeDouble(matriz[i][j]);
                }
            }
            System.out.println("✅ Matriz guardada correctamente en 'matriz.dat'.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: no se pudo crear el archivo. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de escritura: " + e.getMessage());
        } finally {
            try {
                if (salida != null) salida.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }
}