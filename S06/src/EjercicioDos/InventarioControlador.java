package EjercicioDos;

public class InventarioControlador {
	private InventarioModelo modelo;
	private InventarioVista vista;

	public InventarioControlador(InventarioModelo modelo, InventarioVista vista) {
		this.modelo = modelo;
		this.vista = vista;
	}

	public void iniciar() {
		String opcion;
		do {
			System.out.println("\n=== GESTIÓN DE INVENTARIO ===");
			System.out.println("1. Agregar Item");
			System.out.println("2. Eliminar Item");
			System.out.println("3. Ver Inventario");
			System.out.println("4. Ver Detalles");
			System.out.println("5. Buscar Item");
			System.out.println("6. Salir");

			opcion = vista.leerTexto("Selecciona una opción");

			switch (opcion) {
			case "1":
				String n = vista.leerTexto("Nombre");
				String t = vista.leerTexto("Tipo");
				int c = vista.leerEntero("Cantidad");
				String d = vista.leerTexto("Descripción");
				modelo.agregarItem(new Item(n, c, t, d));
				vista.mostrarMensaje("Item agregado con éxito.");
				break;

			case "2":
				String del = vista.leerTexto("Nombre del item a eliminar");
				modelo.eliminarItem(del);
				vista.mostrarMensaje("Item eliminado.");
				break;

			case "3":
				vista.mostrarInventario(modelo.obtenerItems());
				break;

			case "4":
				String det = vista.leerTexto("Nombre del item");
				vista.mostrarDetallesItem(modelo.buscarItem(det));
				break;

			case "5":
				String buscar = vista.leerTexto("Nombre del item");
				vista.mostrarDetallesItem(modelo.buscarItem(buscar));
				break;

			case "6":
				vista.mostrarMensaje("Saliendo...");
				break;

			default:
				vista.mostrarMensaje("Opción inválida.");
			}
		} while (!opcion.equals("6"));
	}
}
