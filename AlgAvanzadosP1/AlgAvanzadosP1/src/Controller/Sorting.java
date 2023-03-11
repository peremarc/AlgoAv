/**
 * Practica 1 Algoritmos Avanzados - Ing Informática UIB
 * @date 10/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=T9Bqkzi5KD4
 */
package Controller;

import java.util.Arrays;

/**
 * Clase con los métodos utilizados para ordenar enteros.
 */
public class Sorting {
    
    // **************************** SELECTION SORT ****************************
    /**
     * Método de ordenación selectionSort: O(n^2); Busca el elemento más pequeño
     * del array y lo coloca al prncipio, avanza al siguiente elemento y desde
     * esa posición busca el siguiente más pequeño y lo pone a continuación.
     * @param arr array de enteros que se quiere ordenar.
     */
    public static void selectionSort(int[] arr) {
        final int N = arr.length;
        int jmin;
        int min;
        
        for (int i = 0; i < N - 1; i++) {
            min = arr[i];
            jmin = i;
            for (int j = i + 1; j < N; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    jmin = j;
                }
            }
            arr[jmin] = arr[i];
            arr[i] = min;
        }
    }

    // ****************************** QUICK SORT ******************************
    /**
     * Método que inicializa la ordenación con el método quicksort: O(n*log(n)).
     * @param arr array de enteros que se quiere ordenar.
     */
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
    }
    
    /**
     * Método de ordenación quickSort: O(n*log(n)); Divide el array en dos
     * partes en cada llamada a partir de un pivote (split) a la izquierda del 
     * cual los elementos son más pequeños y a la derecha más grandes (método 
     * partition), una vez que el array tiene un solo elemento, recorre el
     * árbol de particiones para obtener el array ordenado.
     * array sólo tiene un elemento, monta el array 
     * @param arr array de enteros que se quiere ordenar.
     * @param low posición del primer elemento de la porción a ordenar.
     * @param high posición del último elemento de la porción a ordenar.
     */
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int split = partition(arr, low, high);
            quickSort(arr, low, split - 1);
            quickSort(arr, split + 1, high);
        }
    }
    
    /**
     * Método partition del método quicksort; A partir del pivote (elegido 
     * aleatoriamente el último elemento de la porción) pone a la izquierda
     * los elementos más pequeños que el pivote y a la derecha los más grandes.
     * @param arr array de enteros que se quiere ordenar.
     * @param low posición del primer elemento de la porción a partir.
     * @param high posición del último elemento de la porción a partir.
     * @return posición del pivote después de hacer la partición.
     */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int split = low;
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                swap(arr, split, j);
                split++;
            }
        }
        swap(arr, split, high);
        return split;
    }

    /**
     * Método para intercambiar dos enteros de un array.
     * @param a array donde se encuentran los enteros a intercambiar.
     * @param i posición del primer entero.
     * @param j posición del segundo entero.
     */
    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // ****************************** MERGE SORT ******************************
    /**
     * Método que inicializa la ordenación con el método mergeSort: O(n*log(n)).
     * @param arr array de enteros que se quiere ordenar.
     */
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length-1);
    }
    
    /**
     * Método de ordenación mergeSort: O(n*log(n)); Divide el array de elementos
     * hasta tener arrays de un solo elemento y recorre el árbol ordenando los 
     * elementos de los arrays en las hojas, poniendo primero en el array 
     * superior el entero más pequeño de los dos hijos hasta que los dos arrays
     * están vacios y el padre está ordenado.
     * @param arr array de enteros que se quiere ordenar.
     * @param l
     * @param r 
     */
    private static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    /**
     * Método que fusiona los dos arrays de forma ordenada.
     * @param arr arr array de enteros que se quiere ordenar.
     * @param l posición del primer elemento.
     * @param m posición del elemento por el que se hace la división en dos 
     * arrays.
     * @param r posición del útlimo elemento.
     */
    private static void merge(int[] arr, int l, int m, int r) {
        // Crear los subarrays lArr i rArr
        int lArr[] = new int[m - l + 1];
        int rArr[] = new int[r - m];
        // Rellenar los subarrays lArr i rArr (a partir de arr)
        for (int i = 0; i < lArr.length; i++) {
            lArr[i] = arr[l + i];
        }
        for (int j = 0; j < rArr.length; j++) {
            rArr[j] = arr[m + 1 + j];
        }
        // Merge
        int i = 0, j = 0;
        int k = l;
        while (i < lArr.length && j < rArr.length) {
            if (lArr[i] < rArr[j]) {
                arr[k] = lArr[i];
                i++;
            } else {
                arr[k] = rArr[j];
                j++;
            }
            k++;
        }
        // Elementos restantes
        while (i < lArr.length) {
            arr[k] = lArr[i];
            i++;
            k++;
        }
        while (j < rArr.length) {
            arr[k] = rArr[j];
            j++;
            k++;
        }
    }
    
    // ****************************** RADIX SORT ******************************
    /**
     * Método de ordenación radixSort O(n*m), donde n es el tamaño del array y m
     * la cantidad de dígitos (en base 10) del elemento más grande del array;
     * El método RadixSort ordena los enteros por digitos, primero categoriza 
     * los números por el primer digito, vuelve a organizar el array según el 
     * primer digito (poniendo primero los que acaban por 0, luego 1...) y pasa
     * al siguiente dígito hasta que se han procesado todos los digitos del
     * entero más grande y el array queda ordenado.
     * @param arr array de enteros que se quiere ordenar.
     */
    public static void radixSort(int[] arr) {
        int max = getMax(arr);

        // Realiza el ordenamiento para cada dígito. El número de pasos 
        // depende de la cantidad de digitos del entero más grande.
        for (int exponente = 1; max / exponente > 0; exponente *= 10) {
            countSort(arr, exponente);
        }
    }

    /**
     * Función que devuelve el número más grande del arr.
     * @param arr array de enteros.
     * @return número más grande de arr.
     */
    private static int getMax(int arr[]) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * Método de Ordenación que ordena los enteros de arr por el digito indicado
     * en exp.
     * @param arr array de enteros que se quiere ordenar.
     * @param exp exponente para poner en la útlima posición de un elemento el
     * digito por el que se va a ordenar.
     */
    private static void countSort(int arr[], int exp) {
        int aux[] = new int[arr.length];
        int posNextNum[] = new int[10];
        Arrays.fill(posNextNum, 0);

        // Calculamos la nueva posición del último número de cada digito en arr
        for (int i = 0; i < arr.length; i++) {
            posNextNum[(arr[i] / exp) % 10]++;
        }

        // Restamos uno para no salirnos del array
        for (int i = 1; i < 10; i++) {
            posNextNum[i] += posNextNum[i - 1];
        }

        // Colocamos los enteros en sus nuevas posiciones. Del fin al principio
        // posicionamos el primer digito que encontremos al final de la 
        // secuencia de esos mismos dígitos y restamos 1 a postNextNum para el 
        // siguiente.
        for (int i = arr.length - 1; i >= 0; i--) {
            aux[posNextNum[(arr[i] / exp) % 10] - 1] = arr[i];
            posNextNum[(arr[i] / exp) % 10]--;
        }

        // Copiamos el array ordenado al array original
        System.arraycopy(aux, 0, arr, 0, arr.length);
    }

}
