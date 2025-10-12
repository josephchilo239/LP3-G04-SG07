package visualizacion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;

public class TestFilePrueba {

    public static void main(String[] args) {
        FileInputStream file = null;
        byte[] b = new byte[2048];

        try {
            file = new FileInputStream("src/archivos/TestFile.java"); // ruta relativa
            file.read(b);
            String s = new String(b);
            new ViewFile(s);  

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        } finally {
            try {
                if (file != null) file.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar archivo: " + e.getMessage());
            }
        }
    }
}