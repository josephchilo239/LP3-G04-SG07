package Actividades;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Persona {
    private String nombre;
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}

public class BindingManual extends JFrame {

    public BindingManual() {
        super("Binding de Datos Manual");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        Persona persona = new Persona();

        JTextField txtNombre = new JTextField(15);
        JLabel lblResultado = new JLabel("Nombre: ");

        JButton btnActualizar = new JButton("Actualizar Modelo");

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                persona.setNombre(txtNombre.getText());
                lblResultado.setText("Nombre: " + persona.getNombre());
            }
        });

        add(new JLabel("Ingrese nombre:"));
        add(txtNombre);
        add(btnActualizar);
        add(lblResultado);

        setVisible(true);
    }

    public static void main(String[] args) {
        new BindingManual();
    }
}
