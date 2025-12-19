package model;

public class Direccion {

    private String calle;
    private int numeracion;
    private String comuna;
    private String ciudad;
    private String region;

    public Direccion(String calle, int numeracion, String comuna, String ciudad, String region) {
        this.calle = calle;
        this.numeracion = numeracion;
        this.comuna = comuna;
        this.ciudad = ciudad;
        this.region = region;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(int numeracion) {
        this.numeracion = numeracion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return calle + " ," + numeracion + " ," + comuna + " ," + ciudad + " ," + region;
    }
}
