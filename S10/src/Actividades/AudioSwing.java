package Actividades;
import javax.swing.*;
import javax.sound.sampled.*;

import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.File;

public class AudioSwing extends JFrame {

    Clip clip;

    public AudioSwing() {
        super("Reproducción de Audio");
        setSize(300, 120);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton btnReproducir = new JButton("Reproducir Audio");

        btnReproducir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File("audio.wav"); 
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        add(btnReproducir);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AudioSwing();
    }
}
