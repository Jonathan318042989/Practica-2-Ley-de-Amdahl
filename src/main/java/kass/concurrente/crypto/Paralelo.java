package kass.concurrente.crypto;

import java.util.ArrayList;
import java.util.List;

import kass.concurrente.Main;
import kass.concurrente.constants.Constante;

public class Paralelo implements Runnable {
    private static boolean encontrado = false;
    private static int min = 0;
    private static int max = 0;

    @Override
    public void run() {
        System.out.println("Run " + Thread.currentThread().getName());
        descifrarParalelo(min, max);
    }

    public static void descifrarParalelo(int min, int max) {
        min--;
        max--;
        for (int i = min; i <= max; i++) {
            int[] posibleContra = new int[i];
            while (posibleContra[i - 1] != 26) {
                if (encontrado) {
                    return;
                }
                String contra = "" + Thread.currentThread().getName();
                // Crea un string que contiene una posible contraseña
                for (int k = 0; k < i; k++) {
                    contra = contra + Constante.ALFABETO.charAt(posibleContra[k] % 26);
                }
                boolean esContra = false;
                try {
                    esContra = Cifrar.descifraC(Constante.LLAVE, contra);
                } catch (Exception e) {
                }
                if (esContra) {
                    encontrado = true;
                    System.out.println("La contraseña es: " + contra);
                    return;
                }
                // Obtiene la siguiente posible contraseña
                if (posibleContra[0] % 26 == 25) {
                    for (int k = 0; k < i; k++) {
                        posibleContra[k]++;
                        if (posibleContra[k] % 26 != 0) {
                            break;
                        }
                    }
                } else {
                    posibleContra[0]++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Long inicio = System.nanoTime();
        min = 6;
        max = 6;
        Paralelo p = new Paralelo();
        List<Thread> hilos = new ArrayList<>();
        for (int i = 0; i < Constante.ALFABETO.length(); i++) {
            if (encontrado) {
                break;
            }
            Thread t = new Thread(p, "" + Constante.ALFABETO.charAt(i));
            t.start();
            hilos.add(t);
            if (hilos.size() == Constante.HILOS) {
                for (Thread thread : hilos) {
                    thread.join();
                }
                hilos.clear();
            }
        }

        for (Thread thread : hilos) {
            thread.join();
        }
        Long fin = System.nanoTime();
        Long total = fin - inicio;
        System.out.println("TIEMPO TOTAL: " + Main.nanoSegundoASegundo(total));
    }

}
