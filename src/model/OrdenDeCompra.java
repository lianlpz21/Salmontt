package model;

import java.util.ArrayList;
import java.util.List;

public class OrdenDeCompra {

    private Persona cliente;
    private List<Producto> productos;
    private String numeroOrden;

    public OrdenDeCompra(Persona cliente, List<Producto> productos, String numeroOrden) {
        this.cliente = cliente;
        this.numeroOrden = numeroOrden;
        this.productos = new ArrayList<>();
    }

    // Agregar producto al array
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Orden de Compra: " + numeroOrden +
                "\nCliente:\n" + cliente +
                "\nProductos:\n" + productos +
                "\nTotal: $" + calcularTotal();
    }
}
