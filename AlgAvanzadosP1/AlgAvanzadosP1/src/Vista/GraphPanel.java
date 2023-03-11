/**
 * Practica 1 Algoritmos Avanzados - Ing Informática UIB
 * @date 10/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=T9Bqkzi5KD4
 */
package Vista;

import Model.Model;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Clase que extiende Jpanel donde se pinta la gráfica con los tiempos de
 * ejecución de cada algoritmo para el tamaño indicado del array.
 */
public class GraphPanel extends JPanel {

    /*Variables de clase*/
    
    /*En estos ArrayList se guardarán los distintos tiempos de ejecución
    obtenidos en cada iteración*/
    private ArrayList<Long> yValues, zValues, wValues, reescalar;
    private final View view;
    private final int h;
    private final int w;
    private Model mod;
    
    /*Coordenadas de inicio y fin del rectángulo asociado al gráfico*/
    private int startX, startY, endX, endY;
    
    /*Relación entre pìxels y las unidades de ambos ejes*/
    private double unitX, unitY;
    
    /*Variables que contendrán los valores previos tanto del eje X
    como del eje Y de las distintas lineas*/
    private int prevX, prevY0,prevY1,prevY2;
    
    /*Margen para poner los datos de los ejes*/
    private final int margin = 70;
    
    /*Booleano que controla la ejecución del paintComponent*/
    private boolean running;
    
    /*Booleano para evitar que se pinten puntos extra al cambiar de algoritmo*/
    private boolean changingRescaling = false;

    public GraphPanel(int width, int height, View v, Model m) {
        this.h = height;
        this.w = width;
        this.view = v;
        this.mod = m;
        
        setBounds(20, 40, width - 60, (int) ((2 * height) / 3) - 60);
        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        setBackground(Color.WHITE);
        init();     
    }
    
    /**
     * Método encargado de inicializar las variables de clase y asignar el
     * re-escalado seleccionado.
     */
    private void init(){
        yValues = new ArrayList<>();
        zValues = new ArrayList<>();
        wValues = new ArrayList<>();
        switch (view.getSelectedAlg()) {
            case "SortAndGet":
                reescalar=yValues;
                break;
            case "Hashing":
                reescalar=zValues;
                break;
            case "Prod. Vectorial":
                reescalar=wValues;
                break;
            default:
                break;
        }
        
        startX = margin;
        startY = margin;
        endX = (w - 60) + startX - 2*margin;
        endY = ((int) ((2 * h) / 3) - 60) + startY - 2*margin;
        this.running = false;
    }
    
    /**
     * Asignamos a la variable Modelo el nuevo modelo y llamamos al método init.
     * @param m puntero al Modelo.
     */
    void resetModel(Model m) {
        this.mod = m;
        init();
    }
    
    /**
     * Método encargado repintar la gráfica con los datos actualizados.
     */
    @Override
    public void repaint() {
        if (this.getGraphics() != null) {
            paint(this.getGraphics());
        }
    }
    /**
     * A través de este método seleccionamos los valores de los tiempos
     * obtenidos y guardados en el modelo y los añadimos a los ArrayList.
     */
    public void addPoint(){
        long[] tiempos = mod.getTiempo();
        yValues.add(tiempos[0]);
        zValues.add(tiempos[1]);
        wValues.add(tiempos[2]);
    }
    
    /**
     * Método encargado de pintar en el JPanel
     * @param g componente de gráficos para pintar.
     */
    @Override
    public void paintComponent(Graphics g) {
        //dibujamos el Jpanel 
        super.paintComponent(g);
        
        //si no estamos corriendo el programa o el modelo no está inicializado,
        //return
        if(!running | mod== null) return;
       
        if(!changingRescaling){
            this.addPoint();
        }else{
            changingRescaling = false;
        }
        
        Graphics2D g2d = (Graphics2D) g;

        // Factor de escala
        // Recorremos todos los tiempos del algoritmo seleccionado
        long max = 0;
        int Size = yValues.size();
        for (int i = 0; i < Size; i++) {
            if (max < reescalar.get(i)) {
                max = reescalar.get(i);
            }
        }
        // Comprobamos el valor máximo y preparamos las variables de reescalado
        unitX = 1;
        if (Size == 0) {
            max = 1;
        } else {
            unitX = (endX - startX) / Size;
        }
        unitY = ((double) (endY - startY) / (double) max);
        
        //Parámetros
        prevX = startX;
        prevY0 = endY;
        prevY1 = endY;
        prevY2 = endY;
        
        /*Aqui recorremos los ArrayList hasta el valor que marque el indice, o
        el final. Vamos pintando las lineas cogiendo el valor actual y el 
        siguiente*/
        for (int i = 0; i < Size; i++) {

            g2d.setColor(new Color(68, 168, 74));
            g2d.setStroke(new BasicStroke(2));

            int nextX = prevX + (int)Math.round(unitX);
            int nextY0 = (int) (endY - (yValues.get(i) * unitY));
            g2d.drawLine(prevX, prevY0, nextX, nextY0);
            prevY0 = nextY0;

            g2d.setColor(Color.BLUE);
            g2d.setStroke(new BasicStroke(2));

            int nextY1 = (int) (endY - (zValues.get(i) * unitY));
            g2d.drawLine(prevX, prevY1, nextX, nextY1);
            prevY1 = nextY1;

            if(wValues.get(i) != -1){
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(2));

                int nextY2 = (int) (endY - (wValues.get(i) * unitY));
                g2d.drawLine(prevX, prevY2, nextX, nextY2);
                prevY2 = nextY2;
            }

            prevX = nextX;
        }
        
        /*Pintamos un rectangulo para tapar las medidas que se salen de la 
        gráfica por el margen superior*/
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, endX+margin, startY);
        
        //Dibujamos los ejes de la gráfica
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        int divisionesX = 14;
        int divisionesY = 10;
        double resX = ((((endX-startX)/unitX)*5000)+100)/divisionesX;
        double resY = (((endY-startY)/unitY)/1000000)/divisionesY;
        
        // Dibujamos los datos y divisores en el eje X
        for (int i = 0; i <= divisionesX; i++) { 
            g2d.drawString(Integer.toString((int)resX*i), 
                    startX+((i*(endX-startX))/divisionesX), endY+margin/2);
            g.drawLine(startX+((i*(endX-startX))/divisionesX), startY,
                    startX+((i*(endX-startX))/divisionesX), endY);
        }
        // Dibujamos los datos y divisores en el eje Y
        for (int i = 0; i <= divisionesY; i++) {
            g2d.drawString(Integer.toString((int)resY*i), 
                    startX-margin/2, endY-(i*(endY-startY))/divisionesY);
            g.drawLine(startX, endY-((i*(endY-startY))/divisionesY),
                    endX, endY-((i*(endY-startY))/divisionesY));
            
        }
        // Ponemos los títulos de los ejes y la gráfica
        g2d.drawString("Size n (elements)", endX - 100, endY + 55);
        g2d.drawString("Execution time (ms)", startX - 50, startY - 30);
        g2d.drawString("Tiempo de ejecución de los algoritmos en función del "
                + "tamaño del array", margin+(endY-startY), startY - 30);
    }

    /**
     * Modifica el valor del booleano que controla la ejecución del pintado del
     * gráfico
     * @param b booleano encargado de controlar la ejecución del GraphPanel
     */
    void setRunning(boolean b){
        this.running = b;
    }
    
    void changingRescaling(){
        this.changingRescaling = true;
    }
    
    /**
     * Método que establece el valor que permite el re escalado de la gráfica.
     */
    public void setReescalarY(){
        reescalar = yValues;
    }
    
    /**
     * Método que establece el valor que permite el re escalado de la gráfica.
     */
    public void setReescalarZ(){
        reescalar = zValues;
    }
    
    /**
     * Método que establece el valor que permite el re escalado de la gráfica.
     */
    public void setReescalarW(){
        reescalar = wValues;
    }
    
}