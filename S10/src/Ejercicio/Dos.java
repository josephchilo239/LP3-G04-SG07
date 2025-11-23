package Ejercicio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dos extends JFrame {
    private JTextField[] tfDias = new JTextField[7];
    private ChartPanel chartPanel;

    public Dos() {
        super("Temperaturas Semanales - Gráfico de Líneas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8,8));

        JPanel input = new JPanel(new GridLayout(2,7,6,6));
        String[] dias = {"Lun","Mar","Mié","Jue","Vie","Sáb","Dom"};
        for (int i = 0; i < 7; i++) {
            JPanel p = new JPanel(new BorderLayout(3,3));
            p.add(new JLabel(dias[i], SwingConstants.CENTER), BorderLayout.NORTH);
            tfDias[i] = new JTextField();
            p.add(tfDias[i], BorderLayout.CENTER);
            input.add(p);
        }
        add(input, BorderLayout.NORTH);

        JButton btnMostrar = new JButton("Mostrar Gráfico");
        add(btnMostrar, BorderLayout.SOUTH);

        chartPanel = new ChartPanel();
        add(chartPanel, BorderLayout.CENTER);

        btnMostrar.addActionListener(e -> {
            double[] temps = new double[7];
            for (int i = 0; i < 7; i++) {
                String s = tfDias[i].getText().trim();
                if (s.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingresa todas las temperaturas", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    temps[i] = Double.parseDouble(s);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Temperatura inválida en " + (i+1), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            chartPanel.setData(temps);
        });

        setVisible(true);
    }

    private static class ChartPanel extends JPanel {
        private double[] data = null;

        public void setData(double[] data) {
            this.data = data.clone();
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (data == null) {
                g.drawString("Ingrese las temperaturas y presione 'Mostrar Gráfico'", 20, 20);
                return;
            }

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int marginLeft = 60;
            int marginBottom = 50;
            int marginTop = 30;
            int usableWidth = w - marginLeft - 20;
            int usableHeight = h - marginTop - marginBottom;

            double max = data[0], min = data[0];
            for (double v : data) {
                if (v > max) max = v;
                if (v < min) min = v;
            }
            double padding = (max - min) * 0.1;
            if (padding == 0) padding = 1; 
            max += padding;
            min -= padding;

            g2.drawLine(marginLeft, marginTop, marginLeft, marginTop + usableHeight); 
            g2.drawLine(marginLeft, marginTop + usableHeight, marginLeft + usableWidth, marginTop + usableHeight);

            int steps = 5;
            for (int i = 0; i <= steps; i++) {
                int y = marginTop + usableHeight - (int) ((double)i / steps * usableHeight);
                double val = min + (double)i / steps * (max - min);
                g2.drawLine(marginLeft - 5, y, marginLeft + 5, y);
                g2.drawString(String.format("%.1f", val), 5, y + 5);
            }

            int n = data.length;
            int gap = usableWidth / (n - 1);
            int[] xs = new int[n];
            int[] ys = new int[n];
            for (int i = 0; i < n; i++) {
                xs[i] = marginLeft + i * gap;
                double norm = (data[i] - min) / (max - min);
                ys[i] = marginTop + usableHeight - (int) (norm * usableHeight);
            }

            g2.setStroke(new BasicStroke(2f));
            g2.setColor(new Color(30, 120, 180));
            for (int i = 0; i < n - 1; i++) {
                g2.drawLine(xs[i], ys[i], xs[i+1], ys[i+1]);
            }

            g2.setColor(Color.RED);
            for (int i = 0; i < n; i++) {
                g2.fillOval(xs[i]-4, ys[i]-4, 8, 8);
                g2.drawString(String.format("%.1f", data[i]), xs[i]-10, ys[i]-10);
            }

            String[] dias = {"Lun","Mar","Mié","Jue","Vie","Sáb","Dom"};
            g2.setColor(Color.BLACK);
            for (int i = 0; i < n; i++) {
                g2.drawString(dias[i], xs[i]-10, marginTop + usableHeight + 20);
            }

            g2.drawString("Temperatura (°C)", marginLeft - 50, marginTop - 8);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Dos::new);
    }
}
