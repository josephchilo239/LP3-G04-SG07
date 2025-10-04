package EjercicioTres;
import java.util.ArrayList;
import java.util.List;

public class InventarioModelo {
    private List<Item> items = new ArrayList<>();

    public void agregarItem(Item item) {
        items.add(item);
    }

    public Item buscarItem(String nombre) {
        for (Item i : items)
            if (i.getNombre().equalsIgnoreCase(nombre))
                return i;
        return null;
    }

    public void eliminarItem(String nombre) {
        items.removeIf(i -> i.getNombre().equalsIgnoreCase(nombre));
    }

    public List<Item> getItems() {
        return items;
    }
}
