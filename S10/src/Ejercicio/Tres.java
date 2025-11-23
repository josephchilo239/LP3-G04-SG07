package Ejercicio;
import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Tres extends JFrame {

    public Tres() {
        super("Reproductor de Efectos de Sonido");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 140);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 16, 16));

        JButton btnAplausos = new JButton("Aplausos");
        JButton btnCampana = new JButton("Campana");
        JButton btnExplosion = new JButton("Explosión");

        add(btnAplausos);
        add(btnCampana);
        add(btnExplosion);

        String fileAplausos = "aplausos.wav";
        String fileCampana = "campana.wav";
        String fileExplosion = "explosion.wav";

        btnAplausos.addActionListener(e -> playSound(fileAplausos));
        btnCampana.addActionListener(e -> playSound(fileCampana));
        btnExplosion.addActionListener(e -> playSound(fileExplosion));

        setVisible(true);
    }

    private void playSound(String fileName) {
        SwingUtilities.invokeLater(() -> {
            try {
                var url = getClass().getResource("/Ejercicio/" + fileName);

                if (url == null) {
                    JOptionPane.showMessageDialog(this, 
                        "No se encontró el archivo: " + fileName,
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();

                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error al reproducir: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Tres::new);
    }
}
