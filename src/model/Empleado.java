package model;

import interfaces.Registrable;

public class Empleado extends Persona implements Registrable {

    private String cargo;

    public Empleado(String nombre, Rut rut, Direccion direccion, String cargo) {
        super(nombre, rut, direccion);
        this.cargo = cargo;
    }

    @Override
    public void registrar() {
        System.out.println("Empleado registrado correctamente");
    }

    @Override
    public void mostrarDatos() {
        System.out.println(this + "\nCargo: " + cargo);
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol: Empleado");
    }
}
