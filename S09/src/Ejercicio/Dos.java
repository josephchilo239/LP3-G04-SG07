package Ejercicio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dos extends JFrame implements ActionListener {

    private JTextField txtNombre, txtDni, txtFecha;
    private JCheckBox chkAudifonos, chkManta, chkRevistas;
    private JRadioButton rbtnPrimerPiso, rbtnSegundoPiso;
    private JComboBox<String> cboOrigen, cboDestino;
    private JList<String> lstServicio;
    private JButton btnMostrar, btnLimpiar;
    private ButtonGroup grupoPiso;

    public Dos() {
        super("Compra de Pasajes - Integrantes: Drix, Karim, Diego");
        setLayout(new GridLayout(9, 2, 5, 5));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("DNI:"));
        txtDni = new JTextField();
        add(txtDni);

        add(new JLabel("Fecha de viaje:"));
        txtFecha = new JTextField("DD/MM/AAAA");
        add(txtFecha);

        add(new JLabel("Servicios opcionales:"));
        JPanel panelServicios = new JPanel();
        chkAudifonos = new JCheckBox("Audífonos");
        chkManta = new JCheckBox("Manta");
        chkRevistas = new JCheckBox("Revistas");
        panelServicios.add(chkAudifonos);
        panelServicios.add(chkManta);
        panelServicios.add(chkRevistas);
        add(panelServicios);

        add(new JLabel("Piso del viaje:"));
        JPanel panelPiso = new JPanel();
        rbtnPrimerPiso = new JRadioButton("1er piso");
        rbtnSegundoPiso = new JRadioButton("2do piso");
        grupoPiso = new ButtonGroup();
        grupoPiso.add(rbtnPrimerPiso);
        grupoPiso.add(rbtnSegundoPiso);
        panelPiso.add(rbtnPrimerPiso);
        panelPiso.add(rbtnSegundoPiso);
        add(panelPiso);

        add(new JLabel("Lugar de origen:"));
        cboOrigen = new JComboBox<>(new String[]{"Arequipa", "Lima", "Cusco", "Puno"});
        add(cboOrigen);

        add(new JLabel("Lugar de destino:"));
        cboDestino = new JComboBox<>(new String[]{"Lima", "Cusco", "Arequipa", "Tacna"});
        add(cboDestino);

        add(new JLabel("Tipo de servicio:"));
        lstServicio = new JList<>(new String[]{"Económico", "Standard", "VIP"});
        lstServicio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(lstServicio));

        btnMostrar = new JButton("Mostrar Resumen");
        btnMostrar.addActionListener(this);
        add(btnMostrar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(this);
        add(btnLimpiar);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnMostrar) {
            String nombre = txtNombre.getText();
            String dni = txtDni.getText();
            String fecha = txtFecha.getText();
            String servicios = "";
            if (chkAudifonos.isSelected()) servicios += "Audífonos ";
            if (chkManta.isSelected()) servicios += "Manta ";
            if (chkRevistas.isSelected()) servicios += "Revistas ";

            String piso = rbtnPrimerPiso.isSelected() ? "1er piso" :
                          rbtnSegundoPiso.isSelected() ? "2do piso" : "No especificado";

            String origen = (String) cboOrigen.getSelectedItem();
            String destino = (String) cboDestino.getSelectedItem();
            String servicio = lstServicio.getSelectedValue();

            JOptionPane.showMessageDialog(this,
                "=== RESUMEN DE COMPRA ===\n\n" +
                "Nombre: " + nombre + "\n" +
                "DNI: " + dni + "\n" +
                "Fecha de viaje: " + fecha + "\n" +
                "Origen: " + origen + "\n" +
                "Destino: " + destino + "\n" +
                "Piso: " + piso + "\n" +
                "Servicios adicionales: " + servicios + "\n" +
                "Tipo de servicio: " + servicio,
                "Resumen de Pasajero",
                JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource() == btnLimpiar) {
            txtNombre.setText("");
            txtDni.setText("");
            txtFecha.setText("");
            grupoPiso.clearSelection();
            chkAudifonos.setSelected(false);
            chkManta.setSelected(false);
            chkRevistas.setSelected(false);
            cboOrigen.setSelectedIndex(0);
            cboDestino.setSelectedIndex(0);
            lstServicio.clearSelection();
        }
    }

    public static void main(String[] args) {
        new Dos();
    }
}
