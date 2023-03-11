/**
 * Practica 1 Algoritmos Avanzados - Ing Informática UIB
 * @date 10/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=T9Bqkzi5KD4
 */
package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase donde se almacenan los datos de la aplicación.
 */
public class Model {
    
    /*Variables de clase*/
    /*Lista de tiempos que ha tardado cada algoritmo en cada iteración*/
    private final ArrayList<long[]> tiempos;
    
    /*Buffer de tiempos para suavizar las medidas con la media de los tiempos
    anteriores y posteriores*/
    private final ArrayList<long[]> bufferSuavizado;
    
    /*Cantidad de medidas que se guardan para hacer la media suavizada*/
    private static final int SUAVIZADO = 5;
    
    /*Array para almacenar el tiempo de los tres algoritmos en la iteración 
    actual*/
    private long[] tiempoActual;
    
    /*Array de N elementos (Integers) */
    private int[] elementos;
    
    /*Tamaño del array de elementos (Integers)*/
    private int nElementos;

    public Model() {
        nElementos = 100;
        tiempoActual = new long[3];
        tiempos = new ArrayList<>();
        bufferSuavizado = new ArrayList<>();
        
        elementos = new int[nElementos];
        rellenarElementos();
    }
    
    /**
     * Cada iteración aumenta en 5000 el número de elementos con los que se 
     * trabaja.
     */
    public void newIteration() {
        nElementos+=5000;
        tiempoActual = new long[3];

        elementos = new int[nElementos];
        rellenarElementos();
    }
    
    /**
     * Método que rellena el array de elementos con valores aleatorios entre 0
     * y 500.
     */
    private void rellenarElementos() {
        Random rnd = new Random();
        for (int i = 0; i < elementos.length; i++) {
            elementos[i] = rnd.nextInt(500) + 1;
        }
    }

    /**
     * Método que guarda el Tiempo de Ejecución 't' del Algoritmo y lo guarda en
     * el indice 'i' correspondiente de la variable tiempoActual.
     * @param i indice del algoritmo.
     * @param t tiempo de Ejecución del algoritmo.
     */
    public void setTiempo(int i, long t) {
        tiempoActual[i] = t;
    }

    /**
     * Método que guarda el array de Tiempos de Ejecución de los algoritmos de la
     * iteración actual en el arrayList de tiempos después de suavizarlos con 
     * la cantidad de medidas que hay en el buffer de suavizado, calculando la 
     * media entre la siguiente a pintar y las SUAVIZADO siguientes medidas que
     * todavía no se han pintado.
     */
    public void saveTiempo() {
        // Las primeras medidas van al buffer de suavizado
        if (bufferSuavizado.size() < SUAVIZADO) {
            bufferSuavizado.add(tiempoActual);
        }
        else {
            long[] aux;
            for (int i = 0; i < SUAVIZADO; i++) {
                aux = bufferSuavizado.get(SUAVIZADO-i-1); 
                for (int j = 0; j < tiempoActual.length-1; j++) {
                    tiempoActual[j] += aux[j]; 
                }
            }
            for (int i = 0; i < tiempoActual.length-1; i++) {
                tiempoActual[i] = tiempoActual[i]/((SUAVIZADO)+1);
            }
        }
        
        // Añadimos el elemento que llegó primero al buffer y ponemos el 
        // actual suavizado en la posición del último.
        if (SUAVIZADO > 0) {
            tiempos.add(bufferSuavizado.remove(0)); 
            bufferSuavizado.add(tiempoActual);
        }
        else {
            tiempos.add(tiempoActual);
        }
    }

    /**
     * Función que devuelve el último tiempo guardado en el arrayList de tiempos.
     * @return último tiempo guardado.
     */
    public long[] getTiempo() {
        return tiempos.get(tiempos.size() - 1);
    }

    /**
     * Función que retorna el array de elementos de la iteración actual
     * @return array de elementos
     */
    public int[] getElementos() {
        return elementos;
    }
    
    /**
     * Función que retorna el tamaño del array de elementos de la iteración 
     * actual.
     * @return N 
     */
    public int getIteration(){
        return this.nElementos;
    }
    
}
