package model;

import exceptions.RutInvalidoException;
import utils.ValidadorRut;

public class Rut {
    private String numero;
    private String dv;

    public Rut(String numero, String dv) throws RutInvalidoException {
        this.numero = numero;
        this.dv = dv.toUpperCase();
        // Validación del formato
        ValidadorRut.validarFormato(numero + "-" + dv);
    }

    public String getNumero() {
        return numero;
    }

    public String getDv() {
        return dv;
    }

    @Override
    public String toString() {
        return numero + "-" + dv;
    }
}
