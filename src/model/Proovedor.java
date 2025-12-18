package model;

import interfaces.Registrable;

public class Proovedor extends Persona implements Registrable {

    private String empresa;

    public Proovedor(String nombre, Rut rut, Direccion direccion, String empresa) {
        super(nombre, rut, direccion);
        this.empresa = empresa;
    }

    @Override
    public void registrar() {
        System.out.println("Proveedor registrado correctamente");
    }

    @Override
    public void mostrarDatos() {
        System.out.println(this + "\nEmpresa: " + empresa);
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol: Proveedor");
    }
}
