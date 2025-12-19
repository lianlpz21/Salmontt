package utils;

import exceptions.RutInvalidoException;

public class ValidadorRut {
    public static void validarFormato(String rutCompleto) throws RutInvalidoException {
        if (rutCompleto == null || !rutCompleto.contains("-")) {
            throw new RutInvalidoException("RUT debe contener un guion (-).");
        }

        String[] partes = rutCompleto.split("-");
        if (partes.length != 2) {
            throw new RutInvalidoException("Formato incorrecto. Debe ser número-DV (12345678-5).");
        }

        String numero = partes[0];
        String dv = partes[1].toUpperCase();

        if (!numero.matches("\\d{7,8}")) {
            throw new RutInvalidoException("La parte numérica del RUT debe tener 7 u 8 dígitos.");
        }

        if (!dv.matches("[1-9K]")) {
            throw new RutInvalidoException("El dígito verificador debe ser 1-9 o K.");
        }
    }
}
