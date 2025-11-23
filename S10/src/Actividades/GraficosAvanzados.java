package Actividades;
import javax.swing.*;
import java.awt.*;

public class GraficosAvanzados extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.BLUE);
        g2.drawRect(20, 20, 100, 60);

        g2.setColor(Color.RED);
        g2.fillOval(150, 20, 80, 80);

        g2.setColor(Color.GREEN);
        g2.rotate(Math.toRadians(30), 200, 200);
        g2.fillRect(170, 170, 120, 40);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gráficos en Swing");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GraficosAvanzados());
        frame.setVisible(true);
    }
}
