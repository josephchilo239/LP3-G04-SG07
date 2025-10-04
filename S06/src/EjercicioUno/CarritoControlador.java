package EjercicioUno;
import java.util.List;

public class CarritoControlador {
    private CarritoModelo modelo;
    private CarritoVista vista;

    public CarritoControlador(CarritoModelo modelo, CarritoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerTexto("Selecciona una opción");

            switch (opcion) {
                case "1":
                    String n = vista.leerTexto("Nombre del producto");
                    double p = vista.leerNumero("Precio");
                    modelo.agregarProducto(new Producto(n, p));
                    vista.mostrarMensaje("Producto agregado.");
                    break;

                case "2":
                    vista.mostrarProductos(modelo.getCarrito());
                    break;

                case "3":
                    String elim = vista.leerTexto("Producto a eliminar");
                    modelo.eliminarProducto(elim);
                    vista.mostrarMensaje("Producto eliminado si existía.");
                    break;

                case "4":
                    double desc = vista.leerNumero("Descuento (0.10 para 10%)");
                    double envio = vista.leerNumero("Costo de envío");
                    double total = modelo.calcularTotal(desc, envio);
                    vista.mostrarMensaje("Total a pagar: S/ " + total);
                    break;

                case "5":
                    modelo.realizarCompra();
                    vista.mostrarMensaje("Compra realizada exitosamente.");
                    break;

                case "6":
                    List<List<Producto>> historial = modelo.getHistorial();
                    if (historial.isEmpty()) vista.mostrarMensaje("Sin historial.");
                    else {
                        int i = 1;
                        for (List<Producto> compra : historial) {
                            System.out.println("Compra #" + i++);
                            vista.mostrarProductos(compra);
                        }
                    }
                    break;

                case "7":
                    vista.mostrarMensaje("Saliendo...");
                    break;

                default:
                    vista.mostrarMensaje("Opción inválida.");
            }
        } while (!opcion.equals("7"));
    }
}
