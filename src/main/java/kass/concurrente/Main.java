package kass.concurrente;

import kass.concurrente.constants.Constante;
import kass.concurrente.crypto.Cifrar;
import kass.concurrente.crypto.Descifrar;

/**
 * Clase Principal
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Long inicio = System.nanoTime();
        System.out.println(Cifrar.descifraC(Constante.LLAVE, Constante.CONTRASENNA));
        Long fin = System.nanoTime();
        Long total = fin - inicio;
        System.out.println("TIEMPO TOTAL: " + nanoSegundoASegundo(total));
        System.out.println("Practica 2");
        Descifrar.descifrarSecuential(6, 6);
        fin = System.nanoTime();
        total = fin - inicio;
        System.out.println("TIEMPO TOTAL: " + nanoSegundoASegundo(total));
    }

    public static double nanoSegundoASegundo(Long tiempo) {
        return tiempo * 1.0 * Math.pow(10, -9);
    }
}
/*
 * _ _ _ _
 * A 27 27 27
 */