package app;

import interfaces.Registrable;
import model.*;

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
            System.out.println("0. Salir");
            System.out.print("Seleccione opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> registrarEmpleado();
                case 3 -> registrarProveedor();
                case 4 -> mostrarRegistros();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }

    private void registrarCliente() {
        try {
            Persona cliente = crearPersona("Cliente");
            registros.add((Registrable) cliente);
            System.out.println("Cliente registrado con éxito");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void registrarEmpleado() {
        try {
            Persona empleado = crearPersona("Empleado");
            registros.add((Registrable) empleado);
            System.out.println("Empleado registrado con éxito");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void registrarProveedor() {
        try {
            Persona proveedor = crearPersona("Proveedor");
            registros.add((Registrable) proveedor);
            System.out.println("Proveedor registrado con éxito");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private Persona crearPersona(String tipo) throws Exception {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("RUT número: ");
        String numero = scanner.nextLine();

        System.out.print("RUT dígito verificador: ");
        String dv = scanner.nextLine();

        Rut rut = new Rut(numero, dv);

        System.out.print("Calle: ");
        String calle = scanner.nextLine();

        System.out.print("Número: ");
        int numDir = Integer.parseInt(scanner.nextLine());

        System.out.print("Comuna: ");
        String comuna = scanner.nextLine();

        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();

        System.out.print("Región: ");
        String region = scanner.nextLine();

        Direccion direccion = new Direccion(calle, numDir, comuna, ciudad, region);

        switch (tipo) {
            case "Cliente":
                return new Cliente(nombre, rut, direccion, "Empresa");
            case "Empleado":
                System.out.print("Cargo: ");
                String cargo = scanner.nextLine();
                return new Empleado(nombre, rut, direccion, cargo);
            case "Proveedor":
                System.out.print("Empresa: ");
                String empresa = scanner.nextLine();
                return new Proovedor(nombre, rut, direccion, empresa);
            default:
                throw new IllegalArgumentException("Tipo inválido");
        }
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
}
