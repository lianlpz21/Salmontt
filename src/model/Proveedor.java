package model;

import interfaces.Registrable;

public class Proveedor extends Persona implements Registrable {

    private String empresa;

    public Proveedor(String nombre, Rut rut, Direccion direccion, String empresa) {
        super(nombre, rut, direccion);
        this.empresa = empresa;
    }

    @Override
    public void registrar() {
        System.out.println("Proveedor registrado correctamente");
    }

    @Override
    public void mostrarDatos() {
        System.out.println(this);
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol: Proveedor");
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() +
                "\nRUT: " + getRut() +
                "\nDirección: " + getDireccion() +
                "\nEmpresa: " + empresa;
    }
}
