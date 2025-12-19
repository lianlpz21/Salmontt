package model;

import interfaces.Registrable;

public class Cliente extends Persona implements Registrable {

    private String tipoCliente; // Ej: Empresa, Persona Natural

    public Cliente(String nombre, Rut rut, Direccion direccion, String tipoCliente) {
        super(nombre, rut, direccion);
        this.tipoCliente = tipoCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public void registrar() {
        System.out.println("Cliente registrado correctamente");
    }

    @Override
    public void mostrarDatos() {
        System.out.println(this); // usa toString()
    }

    @Override
    public void mostrarRol() {
        System.out.println("Rol: Cliente");
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() +
                "\nRUT: " + getRut() +
                "\nDirección: " + getDireccion() +
                "\nTipo de cliente: " + tipoCliente;
    }
}
