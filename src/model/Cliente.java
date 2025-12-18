package model;

import interfaces.Registrable;

public class Cliente extends Persona implements Registrable {

    private String tipoCliente;

    public Cliente(String nombre, Rut rut, Direccion direccion, String tipoCliente) {
        super(nombre, rut, direccion);
        this.tipoCliente = tipoCliente;
    }

    @Override
    public void registrar() {
        System.out.println("Cliente registrado correctamente");
    }

    @Override
    public void mostrarDatos() {
        System.out.println(this);
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol: Cliente");
    }
}
