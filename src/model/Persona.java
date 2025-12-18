package model;

public abstract class Persona {

    private String nombre;
    private Rut rut;
    private Direccion direccion;

    public Persona(String nombre, Rut rut, Direccion direccion) {
        this.nombre = nombre;
        this.rut = rut;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public Rut getRut() {
        return rut;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public abstract void mostrarRol();

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                "\nRUT: " + rut +
                "\nDirección: " + direccion;
    }
}
