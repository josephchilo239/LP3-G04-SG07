package visualizacion;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ViewFile extends JFrame {
    private JTextArea areaTexto;

    public ViewFile(String s) {
        super("Mostrando el contenido de un archivo");
        areaTexto = new JTextArea(s, 10, 40);
        add(areaTexto);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setVisible(true);
    }
}