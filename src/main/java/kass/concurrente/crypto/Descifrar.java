package kass.concurrente.crypto;

import kass.concurrente.constants.Constante;

public class Descifrar {

    /**
     * Metodo constructor
     */
    private Descifrar(){}
    
    /**
     * Decifra una llave de forma secuencial
     * @param min Mínimo de letras que tiene la contraseña
     * @param max Máximo de letras que tiene la contraseña
     */
    public static void descifrarSecuential(int min, int max){
        for(int i = min; i <= max; i++){
            int[] posibleContra = new int[i];
            while(posibleContra[i-1] != 26){
                String contra = "";
                //Crea un string que contiene una posible contraseña
                for(int k = 0; k < i; k++){
                    contra = contra + Constante.ALFABETO.charAt(posibleContra[k]%26);
                    if(k == 0){
                        contra = contra.toUpperCase();
                    }
                }
                boolean esContra = false;
                try{
                    esContra = Cifrar.descifraC(Constante.LLAVE, contra);
                }catch(Exception e){
                }
                if(esContra){
                    System.out.println("La contraseña es: " + contra);
                    return;
                }
                //Obtiene la siguiente posible contraseña
                if(posibleContra[0]%26 == 25){
                    for(int k = 0; k < i; k++){
                        posibleContra[k]++;
                        if(posibleContra[k]%26 != 0){
                            break;
                        }
                    }
                }else{
                    posibleContra[0]++;
                }
            }
        }
    }
}
