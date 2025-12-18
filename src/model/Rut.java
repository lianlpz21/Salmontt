package model;

import exceptions.RutInvalidoException;

public class Rut {

    private String numero;
    private String digitoVerificador;

    public Rut(String numero, String digitoVerificador) throws RutInvalidoException {
        if (!validar(numero, digitoVerificador)) {
            throw new RutInvalidoException("RUT inválido: " + numero + "-" + digitoVerificador);
        }
        this.numero = numero;
        this.digitoVerificador = digitoVerificador;
    }

    private boolean validar(String numero, String dv) {
        return numero != null && dv != null && dv.length() == 1;
    }

    public String getRutCompleto() {
        return numero + "-" + digitoVerificador;
    }

    @Override
    public String toString() {
        return getRutCompleto();
    }
}
