package model;

public class Rut {

    private int numero;
    private char digitoVerificador;

    public Rut(int numero, char digitoVerificador) {

        if (numero < 0 || numero > 9) {
            throw new IllegalArgumentException();
        }else {
            this.numero = numero;
            this.digitoVerificador = digitoVerificador;
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public char getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(char digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    @Override
    public String toString() {
        return numero + "-" + digitoVerificador;
    }
}
