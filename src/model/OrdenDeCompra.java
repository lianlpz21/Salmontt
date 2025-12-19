package model;

import java.util.ArrayList;
import java.util.List;

public class OrdenDeCompra {

    private static int contador = 1; // Generador automático

    private String numeroOrden;
    private Cliente cliente;
    private List<Producto> productos;
    private Tarjeta tarjeta;

    public OrdenDeCompra(Cliente cliente, Tarjeta tarjeta) {
        this.numeroOrden = generarNumeroOrden();
        this.cliente = cliente;
        this.tarjeta = tarjeta;
        this.productos = new ArrayList<>();
    }

    private String generarNumeroOrden() {
        return String.format("OC-%03d", contador++);
    }

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
        StringBuilder detalleProductos = new StringBuilder();

        for (Producto p : productos) {
            detalleProductos.append("- ")
                    .append(p.getNombre())
                    .append(" $")
                    .append(p.getPrecio())
                    .append("\n");
        }

        return "ORDEN DE COMPRA Nº " + numeroOrden +
                "\nCLIENTE:\n" + cliente +
                "\n\nPRODUCTOS:\n" + detalleProductos +
                "\nTOTAL: $" + String.format("%.0f", calcularTotal()) +
                "\n\nPAGO CON:\n" + tarjeta;

    }
}
