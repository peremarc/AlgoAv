/**
 * Practica 1 Algoritmos Avanzados - Ing Informática UIB
 * @date 10/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=T9Bqkzi5KD4
 */
package practica1algav;

import Model.Model;
import Vista.View;
import Controller.Controller;
import mesurament.Mesurament;

/**
 * Clase principal desde donde se inicia el programa.
 */
public class Practica1AlgAv implements InterfazPrincipal{

    /*Punteros del patrón MVC*/
    private Model mod;
    private View view;
    private Controller controller;

    public static void main(String[] args) {
        Mesurament.mesura(); // Obtenemos el ratio de nuestro computador
        (new Practica1AlgAv()).inicio();
    }
    
    /**
     * Inicializamos las distintas componentes.
     */
    public void inicio(){
        mod = new Model();
        controller = null;
        /*Pasamos puntero del main a la Vista*/
        view = new View("Practica 1", 1000, 1600, this);
        view.mostrar();
    }
    
    /**
     * Método encargado de la comunicación entre componentes.
     * @param s String que contiene la acción a realizar.
     */
    @Override
    public void notificar(String s) {
        /*Si se pulsa el botón START*/
        if("proceso-start".equals(s)){
            /*Si el controlador aun no ha sido inicializado*/
            if(controller == null){
                /*Creamos los datos*/
                mod = new Model();
                /*Pasamos puntero del modelo a la Vista*/
                view.resetModel(mod);
                /*Ponemos a true la variable que controla la ejecución del paint
                component de la clase GraphPanel*/
                view.setRunning(true);
                /*Inicializamos el controlador con un puntero al main, y
                arrancamos su ejecución*/
                controller = new Controller(this);
                controller.start();
            }
        /*Si se pulsa el botón STOP*/
        } else if("proceso-stop".equals(s)) {
            /*Si ya se ha inicializado el controlador*/
            if(controller != null){
                /*Llamada a metodo que detiene el Thread del controlador*/
                controller.parar();
                controller = null;
            }
        /*Cuando el controlador notifica que todo esta listo para el repaint de
        la Vista*/
        } else if(s.contains("draw:")){
            /*Método que actualiza la variable nElementos que contiene el número 
            de elementos con los que trabajan los algoritmos*/
            view.setN(s.substring(s.indexOf(":") + 1));
            view.repaint();
        }
    }
    
    public Model getModel() {
        return mod;
    }
    
}
