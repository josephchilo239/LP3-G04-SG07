package Ejercicio;
import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;

public class Cuatro extends JFrame {

    private Clip clip;
    private long pausePosition = 0;

    public Cuatro() {
        super("Reproductor - Reproducir / Pausar / Reanudar");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(360, 120);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 12, 18));

        JButton btnPlay = new JButton("Reproducir");
        JButton btnPause = new JButton("Pausar");
        JButton btnResume = new JButton("Reanudar");

        add(btnPlay);
        add(btnPause);
        add(btnResume);

        String audioName = "musica.wav";

        btnPlay.addActionListener(e -> {
            try {
                if (clip != null && clip.isOpen()) {
                    clip.stop();
                    clip.close();
                }

                var url = getClass().getResource("/Ejercicio/" + audioName);

                if (url == null) {
                    JOptionPane.showMessageDialog(this,
                            "No se encontró el archivo: " + audioName,
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                clip = AudioSystem.getClip();
                clip.open(ais);
                clip.setMicrosecondPosition(0);
                clip.start();
                pausePosition = 0;

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al reproducir: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnPause.addActionListener(e -> {
            if (clip != null && clip.isRunning()) {
                pausePosition = clip.getMicrosecondPosition();
                clip.stop();
            }
        });

        btnResume.addActionListener(e -> {
            if (clip != null) {
                try {
                    clip.setMicrosecondPosition(pausePosition);
                    clip.start();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                            "No se puede reanudar: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (clip != null) {
                    clip.stop();
                    clip.close();
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Cuatro::new);
    }
}
