package Ejercicio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Producto {
    private String nombre;
    private double precio;
    private int cantidadStock;
    private String categoria;

    public Producto() {}

    public Producto(String nombre, double precio, int cantidadStock, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
        this.categoria = categoria;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getCantidadStock() { return cantidadStock; }
    public void setCantidadStock(int cantidadStock) { this.cantidadStock = cantidadStock; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return "<html><b>Producto:</b> " + nombre +
               "<br/><b>Precio:</b> S/ " + String.format("%.2f", precio) +
               "<br/><b>Cantidad en stock:</b> " + cantidadStock +
               "<br/><b>Categoría:</b> " + categoria + "</html>";
    }
}

public class Uno extends JFrame {
    private JTextField tfNombre, tfPrecio, tfCantidad, tfCategoria;
    private JLabel lblSalida;
    private Producto producto;

    public Uno() {
        super("Gestión de Producto");
        producto = new Producto();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8,8));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0; form.add(new JLabel("Nombre:"), c);
        c.gridx = 1; tfNombre = new JTextField(18); form.add(tfNombre, c);

        c.gridx = 0; c.gridy = 1; form.add(new JLabel("Precio (S/):"), c);
        c.gridx = 1; tfPrecio = new JTextField(10); form.add(tfPrecio, c);

        c.gridx = 0; c.gridy = 2; form.add(new JLabel("Cantidad en stock:"), c);
        c.gridx = 1; tfCantidad = new JTextField(10); form.add(tfCantidad, c);

        c.gridx = 0; c.gridy = 3; form.add(new JLabel("Categoría:"), c);
        c.gridx = 1; tfCategoria = new JTextField(14); form.add(tfCategoria, c);

        JButton btnActualizar = new JButton("Actualizar Producto");
        c.gridx = 0; c.gridy = 4; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
        form.add(btnActualizar, c);

        add(form, BorderLayout.NORTH);

        lblSalida = new JLabel("Información del producto aparecerá aquí");
        lblSalida.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(lblSalida, BorderLayout.CENTER);

        btnActualizar.addActionListener(e -> {
            String nombre = tfNombre.getText().trim();
            String precioStr = tfPrecio.getText().trim();
            String cantidadStr = tfCantidad.getText().trim();
            String categoria = tfCategoria.getText().trim();

            if (nombre.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty() || categoria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double precio;
            int cantidad;
            try {
                precio = Double.parseDouble(precioStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio inválido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                cantidad = Integer.parseInt(cantidadStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCantidadStock(cantidad);
            producto.setCategoria(categoria);

            lblSalida.setText(producto.toString());
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Uno::new);
    }
}
