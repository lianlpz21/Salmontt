package app;

import interfaces.Registrable;
import model.*;
import utils.LeerProductos;
import utils.ValidadorRut;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuConsola {

    // Scanner para leer datos desde la consola
    private Scanner scanner;

    // Lista polimórfica para almacenar clientes, empleados y proveedores
    private List<Registrable> registros;

    // Constructor del menú
    public MenuConsola() {
        scanner = new Scanner(System.in);
        registros = new ArrayList<>();
    }

    // Metodo principal que muestra el menú y controla la navegación
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

            // Leer opción como texto para evitar errores
            String input = scanner.nextLine().trim();
            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                opcion = -1; // Opción inválida
            }

            // Ejecutar acción según la opción seleccionada
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

    // Registrar un cliente
    private void registrarCliente() {
        try {
            Persona cliente = crearPersona("Cliente");
            registros.add(cliente);
            cliente.registrar();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Registrar un empleado
    private void registrarEmpleado() {
        try {
            Persona empleado = crearPersona("Empleado");
            registros.add(empleado);
            empleado.registrar();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Registrar un proveedor
    private void registrarProveedor() {
        try {
            Persona proveedor = crearPersona("Proveedor");
            registros.add(proveedor);
            proveedor.registrar();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Crear una persona según el tipo solicitado
    private Persona crearPersona(String tipo) throws Exception {

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();

        // Ingreso y validación del RUT
        System.out.print("RUT completo (formato 12345678-5): ");
        String rutCompleto = scanner.nextLine().trim().toUpperCase();
        rutCompleto = rutCompleto.replaceAll("[\\s\u00A0]", "");

        ValidadorRut.validarFormato(rutCompleto);

        String[] partesRut = rutCompleto.split("-");
        String numero = partesRut[0];
        String dv = partesRut[1];

        Rut rut = new Rut(numero, dv);

        // Ingreso de dirección
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

        // Crear objeto según el tipo de persona
        switch (tipo) {
            case "Cliente" -> {
                System.out.print("Tipo de cliente (Persona / Empresa): ");
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

    // Crear una orden de compra
    private void crearOrdenCompra() {

        // Obtener solo los clientes registrados
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

        // Mostrar clientes disponibles
        System.out.println("\n--- CLIENTES ---");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNombre());
        }

        System.out.print("Seleccione cliente: ");
        int opcionCliente = scanner.nextInt();
        scanner.nextLine();

        Cliente clienteSeleccionado = clientes.get(opcionCliente - 1);

        // Crear tarjeta de pago
        System.out.print("Número de tarjeta: ");
        String numeroTarjeta = scanner.nextLine();

        System.out.print("Tipo (Crédito/Débito): ");
        String tipoTarjeta = scanner.nextLine();

        System.out.print("Banco: ");
        String banco = scanner.nextLine();

        Tarjeta tarjeta = new Tarjeta(numeroTarjeta, tipoTarjeta, banco);

        // Crear orden de compra
        OrdenDeCompra orden = new OrdenDeCompra(clienteSeleccionado, tarjeta);

        // Cargar productos desde archivo TXT
        List<Producto> productos = LeerProductos.cargarProductos("src/data/productos.txt");

        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles.");
            return;
        }

        boolean seguir = true;

        // Selección de productos
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

        // Mostrar la orden generada
        System.out.println("\n--- ORDEN GENERADA ---");
        System.out.println(orden);
    }

    // Mostrar todos los registros del sistema
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

    // Mostrar productos cargados desde el archivo
    private void mostrarProductos() {
        System.out.println("\n--- PRODUCTOS ---");

        List<Producto> productos = LeerProductos.cargarProductos("src/data/productos.txt");

        for (Producto p : productos) {
            System.out.println(p);
            System.out.println("--------------");
        }
    }
}
