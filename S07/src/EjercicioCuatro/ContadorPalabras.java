package EjercicioCuatro;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class ContadorPalabras {

    public static void main(String[] args) {
        File archivo = seleccionarArchivo();
        if (archivo == null) {
            System.out.println("No se seleccionó ningún archivo. Saliendo...");
            return;
        }

        try {
            analizarArchivo(archivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static File seleccionarArchivo() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Selecciona un archivo de texto");
        int opcion = fc.showOpenDialog(null);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivo = fc.getSelectedFile();
            if (archivo.exists() && archivo.isFile()) {
                return archivo;
            } else {
                System.out.println("Archivo inválido o no existente.");
            }
        }
        return null;
    }

    private static void analizarArchivo(File archivo) throws IOException {
        int totalLineas = 0;
        int totalPalabras = 0;
        int totalCaracteres = 0;
        Map<String, Integer> frecuencia = new HashMap<>();

        Pattern patronPalabra = Pattern.compile("[A-Za-zÁÉÍÓÚáéíóúÑñ0-9]+");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                totalLineas++;
                totalCaracteres += linea.replace("\n", "").length();

                Matcher matcher = patronPalabra.matcher(linea);
                while (matcher.find()) {
                    String palabra = matcher.group().toLowerCase();
                    totalPalabras++;
                    frecuencia.put(palabra, frecuencia.getOrDefault(palabra, 0) + 1);
                }
            }
        }

        mostrarResultados(totalLineas, totalPalabras, totalCaracteres, frecuencia);
    }

    private static void mostrarResultados(int lineas, int palabras, int caracteres, Map<String, Integer> frecuencia) {
        System.out.println("\n📊 --- RESULTADOS DEL ANÁLISIS ---");
        System.out.println("Total de líneas: " + lineas);
        System.out.println("Total de palabras: " + palabras);
        System.out.println("Total de caracteres: " + caracteres);
        System.out.printf("Promedio de palabras por línea: %.2f%n", (lineas == 0 ? 0 : (double) palabras / lineas));

        System.out.println("\n🔠 Palabras más frecuentes:");
        frecuencia.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .forEach(e -> System.out.println(e.getKey() + " → " + e.getValue() + " veces"));
    }
}
