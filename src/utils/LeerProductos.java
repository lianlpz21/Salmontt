package utils;

import model.Producto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LeerProductos {

    public static List<Producto> cargarProductos(String rutaArchivo) {
        List<Producto> productos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                String codigo = datos[0];
                String nombre = datos[1];
                double precio = Double.parseDouble(datos[2]);
                int stock = Integer.parseInt(datos[3]);

                Producto producto = new Producto(codigo, nombre, precio, stock);
                productos.add(producto);
            }

        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }

        return productos;
    }
}
