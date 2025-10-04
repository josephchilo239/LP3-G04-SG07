package ActividadDos;
import java.util.List;

public class PedidoControlador {
    private PedidoModelo modelo;
    private PedidoVista vista;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitar("Selecciona una opción");

            switch (opcion) {
                case "1":
                    String nombre = vista.solicitar("Nombre del plato");
                    String tipo = vista.solicitar("Tipo de plato");
                    modelo.agregarPedido(new Pedido(nombre, tipo));
                    vista.mostrarMensaje("Pedido agregado correctamente.");
                    break;

                case "2":
                    vista.mostrarPedidos(modelo.getPedidos());
                    break;

                case "3":
                    String eliminar = vista.solicitar("Nombre del pedido a eliminar");
                    modelo.eliminarPedido(eliminar);
                    vista.mostrarMensaje("Pedido eliminado si existía.");
                    break;

                case "4":
                    String viejo = vista.solicitar("Nombre del pedido a actualizar");
                    String nuevo = vista.solicitar("Nuevo nombre del pedido");
                    modelo.actualizarPedido(viejo, nuevo);
                    vista.mostrarMensaje("Pedido actualizado.");
                    break;

                case "5":
                    String texto = vista.solicitar("Ingrese nombre o tipo a buscar");
                    List<Pedido> resultados = modelo.buscarPorNombreOTipo(texto);
                    vista.mostrarPedidos(resultados);
                    break;

                case "6":
                    vista.mostrarMensaje("Total de pedidos: " + modelo.contarPedidos());
                    break;

                case "7":
                    vista.mostrarMensaje("Saliendo...");
                    break;

                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        } while (!opcion.equals("7"));

        vista.cerrarScanner();
    }
}
