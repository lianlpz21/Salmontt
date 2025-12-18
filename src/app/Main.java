package app;

import model.Persona;
import model.Direccion;
import model.Rut;

public class Main {
    public static void main(String[] args) {


        Direccion direccion1 = new Direccion(
                "Av. Juan Soler Manfredini",
                1200,
                "Pelluco",
                "Puerto Montt",
                "Los Lagos"
        );

        Rut rut1 = new Rut(20783996,'5');

        Persona persona1 = new Persona("Lian", rut1, direccion1 );

        System.out.println(persona1);
    }
}