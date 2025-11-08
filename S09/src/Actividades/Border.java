package Actividades;
import javax.swing.*;
import java.awt.*;

public class Border extends JFrame {

    public Border() {
        super("BorderLayout - Integrantes: Drix, Karim, Diego");
        setLayout(new BorderLayout()); 
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JButton("Norte"), BorderLayout.NORTH);
        add(new JButton("Sur"), BorderLayout.SOUTH);
        add(new JButton("Este"), BorderLayout.EAST);
        add(new JButton("Oeste"), BorderLayout.WEST);
        add(new JButton("Centro"), BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Border();
    }
}
