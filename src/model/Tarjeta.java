package model;

public class Tarjeta {

    private String numero;
    private String tipo; // Crédito o Débito
    private String banco;

    public Tarjeta(String numero, String tipo, String banco) {
        this.numero = numero;
        this.tipo = tipo;
        this.banco = banco;
    }

    public String getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public String getBanco() {
        return banco;
    }

    @Override
    public String toString() {
        return "Tarjeta [" +
                "Tipo: " + tipo +
                ", Banco: " + banco +
                ", Número: ****" + numero.substring(numero.length() - 4) +
                "]";
    }
}
