package Siete;
import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;

public class DemoJFileChooser extends JFrame {
    private final JTextArea areaSalida;
    public DemoJFileChooser() throws IOException {
        super("Demo de JFileChooser");
        areaSalida = new JTextArea();
        add(new JScrollPane(areaSalida));
        analizarRuta();
    }
    private void analizarRuta() throws IOException {
        Path ruta = obtenerRutaArchivoDirectorio();

        if (ruta != null && Files.exists(ruta)) {
            StringBuilder builder = new StringBuilder();

            builder.append(String.format("Nombre: %s%n", ruta.getFileName()));
            builder.append(String.format("Es un directorio: %s%n", Files.isDirectory(ruta) ? "Sí" : "No"));
            builder.append(String.format("Es ruta absoluta: %s%n", ruta.isAbsolute() ? "Sí" : "No"));
            builder.append(String.format("Fecha última modificación: %s%n", Files.getLastModifiedTime(ruta)));
            builder.append(String.format("Tamaño: %s bytes%n", Files.size(ruta)));
            builder.append(String.format("Ruta absoluta: %s%n", ruta.toAbsolutePath()));

            if (Files.isDirectory(ruta)) {
                builder.append("\nContenido del directorio:\n");
                try (DirectoryStream<Path> flujoDirectorio = Files.newDirectoryStream(ruta)) {
                    for (Path p : flujoDirectorio) {
                        builder.append(String.format("%s%n", p.getFileName()));
                    }
                }
            }

            areaSalida.setText(builder.toString());
        } else {
            JOptionPane.showMessageDialog(this, "El archivo o directorio no existe.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Path obtenerRutaArchivoDirectorio() {
        JFileChooser selectorArchivos = new JFileChooser();
        selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int resultado = selectorArchivos.showOpenDialog(this);

        if (resultado != JFileChooser.APPROVE_OPTION) {
            System.exit(0);
        }

        return selectorArchivos.getSelectedFile().toPath();
    }
}