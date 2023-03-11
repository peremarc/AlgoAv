/**
 * Practica 1 Algoritmos Avanzados - Ing Informática UIB
 * @date 10/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=T9Bqkzi5KD4
 */
package Controller;

import Model.Model;
import java.util.HashMap;

import practica1algav.Practica1AlgAv;

/**
 * Clase encargado de realizar las operaciones con los datos obtenidos del 
 * modelo.
 */
public class Controller extends Thread {
    
    // Punteros del patrón MVC
    private final Practica1AlgAv programa;
    private final Model model;
    
    // Variable booleana para controlar el hilo de ejecución
    private boolean seguir;

    // Tamaño de la n a partir de la cual deja de hacer el producto vectorial
    private final int MAX_PRODUCT_VECT = 70000;

    public Controller(Practica1AlgAv p) {
        this.programa = p;
        this.model = p.getModel();
        this.seguir = false;
    }

    @Override
    /**
     * Hilo de ejecución del controlador en el que se realizan los cálculos de
     * los tres algoritmos en secuencia hasta que se detiene mediante el método
     * parar().
     */
    public void run() {
        long tiempo;
        this.seguir = true;
            //Preparamos Datos

        while (seguir) {
            model.newIteration();

            tiempo = System.nanoTime();
            sortAndGet(model.getElementos());
            model.setTiempo(0, System.nanoTime() - tiempo);

            tiempo = System.nanoTime();
            hashing(model.getElementos());
            model.setTiempo(1, System.nanoTime() - tiempo);

            
            if(model.getIteration() < MAX_PRODUCT_VECT){
                tiempo = System.nanoTime();
                producteVectorial(model.getElementos());
                model.setTiempo(2, System.nanoTime() - tiempo);
            } else {
                model.setTiempo(2, -1);
            }

            model.saveTiempo();
            programa.notificar("draw:" + model.getIteration());
            
        }
    }

    /**
     * Método que detiene los cálculos de los algoritmos.
     */
    public void parar() {
        this.seguir = false;
    }

    /**
     * Método que busca la moda de un array de enteros mediante la ordenación
     * de un array y la búsqueda del elemento más frecuente con el método freq;
     * Debido a que utiliza mergeSort: O(n*logn).
     * @param arr array en el que se busca la moda.
     * @return elemento con mayor frecuencia de aparición en el array.
     */
    public int sortAndGet(int[] arr) {
        int[] res = arr.clone();
        Sorting.mergeSort(res);
        
        // Buscamos el elemento con mayor frecuencia de aparición
        int lastNum = arr[0], moda = arr[0], maxRepeticiones = 1, repeticiones = 1;  
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == lastNum) {
                repeticiones++;
                if (repeticiones > maxRepeticiones) {
                    moda = arr[i];
                    maxRepeticiones = repeticiones;
                }
            }
            else {
                lastNum = arr[i];
                repeticiones = 1;
            }
        }
        return moda;
    }

    /**
     * Método que busca la moda de un array de enteros mediante una tabla Hash;
     * O(n).
     * @param arr array en el que se busca la moda.
     * @return elemento con mayor frecuencia de aparición en el array.
     */
    public int hashing(int[] arr) {
        HashMap<Integer, Integer> mapa = new HashMap<>();
        for (int x = 0; x < arr.length; x++) {
            int numero = arr[x];
            if (mapa.containsKey(numero)) {
                mapa.put(numero, mapa.get(numero) + 1);
            } else {
                mapa.put(numero, 1);
            }
        }
        int modaH = 0, mayorH = 0;
        for (HashMap.Entry<Integer, Integer> entry : mapa.entrySet()) {
            if (entry.getValue() > mayorH) {
                mayorH = entry.getValue();
                modaH = entry.getKey();
            }
        }
        return modaH;
    }
    
    /**
     * Algoritmo que hace la multiplicación entre un vector que hace de matriz
     * fila y matriz columna; O(n^2).
     * @param arr vector que se va a multiplicar por si mismo.
     * @return resultado de la multipliación.
     */
    public int[] producteVectorial(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                res[i] += arr[i] * arr[j];
            }
        }
        return res;
    }

}
