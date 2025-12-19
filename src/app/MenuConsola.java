package app;

import interfaces.Registrable;
import model.*;
import utils.LeerProductos;
import utils.ValidadorRut;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuConsola {

    private Scanner scanner;
    private List<Registrable> registros;

    public MenuConsola() {
        scanner = new Scanner(System.in);
        registros = new ArrayList<>();
    }

    public void iniciar() {
        int opcion;

        do {
            System.out.println("\n--- SISTEMA SALMONTT ---");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Registrar Empleado");
            System.out.println("3. Registrar Proveedor");
            System.out.println("4. Mostrar registros");
            System.out.println("5. Mostrar productos");
            System.out.println("6. Crear orden de compra");
            System.out.println("0. Salir");
            System.out.print("Seleccione opción: ");

            String input = scanner.nextLine().trim();
            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                opcion = -1; // opción inválida
            }

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> registrarEmpleado();
                case 3 -> registrarProveedor();
                case 4 -> mostrarRegistros();
                case 5 -> mostrarProductos();
                case 6 -> crearOrdenCompra();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }

    private void registrarCliente() {
        try {
            Persona cliente = crearPersona("Cliente");
            registros.add(cliente);
            cliente.registrar();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void registrarEmpleado() {
        try {
            Persona empleado = crearPersona("Empleado");
            registros.add(empleado);
            empleado.registrar();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void registrarProveedor() {
        try {
            Persona proveedor = crearPersona("Proveedor");
            registros.add(proveedor);
            proveedor.registrar();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private Persona crearPersona(String tipo) throws Exception {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("RUT completo (formato 12345678-5): ");
        String rutCompleto = scanner.nextLine().trim().toUpperCase();
        rutCompleto = rutCompleto.replaceAll("[\\s\u00A0]", ""); // limpiar espacios invisibles

        ValidadorRut.validarFormato(rutCompleto);

        String[] partesRut = rutCompleto.split("-");
        String numero = partesRut[0];
        String dv = partesRut[1];

        Rut rut = new Rut(numero, dv);

        System.out.print("Calle: ");
        String calle = scanner.nextLine().trim();

        System.out.print("Número: ");
        int numDir = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Comuna: ");
        String comuna = scanner.nextLine().trim();

        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine().trim();

        System.out.print("Región: ");
        String region = scanner.nextLine().trim();

        Direccion direccion = new Direccion(calle, numDir, comuna, ciudad, region);

        switch (tipo) {
            case "Cliente" -> {
                System.out.print("Tipo de cliente (Persona / Empresa ): ");
                String tipoCliente = scanner.nextLine().trim();
                return new Cliente(nombre, rut, direccion, tipoCliente);
            }
            case "Empleado" -> {
                System.out.print("Cargo: ");
                String cargo = scanner.nextLine().trim();
                return new Empleado(nombre, rut, direccion, cargo);
            }
            case "Proveedor" -> {
                System.out.print("Empresa: ");
                String empresaP = scanner.nextLine().trim();
                return new Proveedor(nombre, rut, direccion, empresaP);
            }
            default -> throw new IllegalArgumentException("Tipo inválido");
        }
    }

    private void crearOrdenCompra() {

        // Buscar clientes registrados
        List<Cliente> clientes = new ArrayList<>();

        for (Registrable r : registros) {
            if (r instanceof Cliente) {
                clientes.add((Cliente) r);
            }
        }

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        // Mostrar clientes
        System.out.println("\n--- CLIENTES ---");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNombre());
        }

        System.out.print("Seleccione cliente: ");
        int opcionCliente = scanner.nextInt();
        scanner.nextLine();

        Cliente clienteSeleccionado = clientes.get(opcionCliente - 1);

        // Crear tarjeta
        System.out.print("Número de tarjeta: ");
        String numeroTarjeta = scanner.nextLine();

        System.out.print("Tipo (Crédito/Débito): ");
        String tipoTarjeta = scanner.nextLine();

        System.out.print("Banco: ");
        String banco = scanner.nextLine();

        Tarjeta tarjeta = new Tarjeta(numeroTarjeta, tipoTarjeta, banco);


        OrdenDeCompra orden = new OrdenDeCompra(clienteSeleccionado, tarjeta);


        // Cargar productos desde archivo
        List<Producto> productos = LeerProductos.cargarProductos("src/data/productos.txt");

        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles.");
            return;
        }

        boolean seguir = true;

        while (seguir) {
            System.out.println("\n--- PRODUCTOS ---");
            for (int i = 0; i < productos.size(); i++) {
                System.out.println((i + 1) + ". " + productos.get(i).getNombre() +
                        " $" + String.format("%.0f", productos.get(i).getPrecio()));

            }

            System.out.print("Seleccione producto (0 para terminar): ");
            int opcionProducto = scanner.nextInt();
            scanner.nextLine();

            if (opcionProducto == 0) {
                seguir = false;
            } else {
                Producto p = productos.get(opcionProducto - 1);
                orden.agregarProducto(p);
                System.out.println("Producto agregado.");
            }
        }

        // Mostrar orden
        System.out.println("\n--- ORDEN GENERADA ---");
        System.out.println(orden);
    }


    private void mostrarRegistros() {
        System.out.println("\n--- REGISTROS ---");
        for (Registrable r : registros) {
            r.mostrarDatos();
            if (r instanceof Persona) {
                ((Persona) r).mostrarRol();
            }
            System.out.println("--------------");
        }
    }

    private void mostrarProductos() {
        System.out.println("\n--- PRODUCTOS ---");

        List<Producto> productos = LeerProductos.cargarProductos("src/data/productos.txt");

        for (Producto p : productos) {
            System.out.println(p);
            System.out.println("--------------");
        }
    }
}
