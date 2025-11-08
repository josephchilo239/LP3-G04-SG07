package Actividades;
import javax.swing.*;
import java.awt.*;

public class Flow extends JFrame {
    public Flow() {
        super("FlowLayout - Integrantes: Drix, Karim, Diego");
        setLayout(new FlowLayout()); 
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JButton("Botón 1"));
        add(new JButton("Botón 2"));
        add(new JButton("Botón 3"));
        add(new JButton("Botón 4"));
        add(new JButton("Botón 5"));
        add(new JButton("Botón 6"));

        setVisible(true);
    }

    public static void main(String[] args) {
        new Flow();
    }
}
