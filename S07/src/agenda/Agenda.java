package agenda;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Agenda {

    private ArrayPersona lista;
    private FileInputStream agendaFile;
    private final int longLinea = 32;

    public Agenda() {
        this.lista = cargaContactos();
    }

    public void bucle() {
        String nombre = "";
        System.out.println("Introduzca un nombre o <Enter>");
        try {
            while (!(nombre = leeEntrada(System.in)).equals("")) {
                lista.printArray(nombre);
                System.out.println("Introduzca un nombre o <Enter>");
            }
        } catch (IOException e) {
            System.out.println("Error leyendo la entrada: " + e.getMessage());
        }
    }

    private String leeEntrada(InputStream entrada) throws IOException {
        byte chars[] = new byte[longLinea];
        int contador = 0;
        int c;
        while (contador < longLinea && (c = entrada.read()) != -1 && c != '\n') {
            chars[contador++] = (byte) c;
        }
        return new String(chars, 0, contador).trim();
    }

    private Persona cargaAgenda() throws IOException {
        String nombre = leeEntrada(agendaFile);
        if (nombre == null || nombre.isEmpty()) return null;

        String telefono = leeEntrada(agendaFile);
        String direccion = leeEntrada(agendaFile);

        return new Persona(nombre, telefono, direccion);
    }

    private ArrayPersona cargaContactos() {
        ArrayPersona directorio = new ArrayPersona();
        try {
            agendaFile = new FileInputStream("src/agenda/agenda.txt");
            Persona nuevaPersona;
            while (true) {
                nuevaPersona = cargaAgenda();
                if (nuevaPersona == null) break;
                directorio.addArray(nuevaPersona);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los contactos: " + e.getMessage());
            System.exit(1);
        }
        return directorio;
    }
}