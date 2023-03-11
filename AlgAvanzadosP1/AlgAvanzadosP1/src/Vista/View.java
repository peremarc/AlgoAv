/**
 * Practica 1 Algoritmos Avanzados - Ing Informática UIB
 * @date 10/03/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url https://www.youtube.com/watch?v=T9Bqkzi5KD4
 */
package Vista;

import practica1algav.Practica1AlgAv;

import Model.Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * Clase que contiene todos los componentes de la interfaz en un JFrame que
 * permiten al usuario interactuar con la aplicación.
 */
public class View extends JFrame implements ActionListener {

    /*Variables de clase*/
    private final Practica1AlgAv prog;
    private final JButton StartB;
    private final JButton StopB;
    private final JComboBox<String> algoritmos;
    private final GraphPanel graphP;
    private final NElementos nElementos;
    private final int height;
    private final int width;
    private final int margenLeyenda = 100;
    protected final String[] alg = {"Prod. Vectorial", "SortAndGet", "Hashing"};

    public View(String s, int h, int w, Practica1AlgAv p) {
        super(s);
        prog = p;
        this.height = h;
        this.width = w;
        this.setSize(new Dimension(width + margenLeyenda, height));

        /*Imagen del fondo del JFrame*/
        JLabel fondo;
        ImageIcon img = new ImageIcon("src/Imagenes/fondoA.jpg");
        fondo = new JLabel("", img, JLabel.CENTER);
        fondo.setBounds(0, 0, getWidth(), getHeight());
        setLayout(null);

        /*Panel para reesclaado*/
        algoritmos = new JComboBox<>(alg);
        algoritmos.setLayout(null);
        algoritmos.setBounds(getWidth() / 2 - 60, ((2 * getHeight()) / 3) - 10, 120, 30);
        this.add(algoritmos);
        algoritmos.addActionListener(this);

        /*Botones*/
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(null);
        panelBotones.setBounds(20, ((2 * getHeight()) / 3) + 40, getWidth() - 60, (getHeight() / 3) - 100);
        panelBotones.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        /*Fondo translucido*/
        panelBotones.setBackground(new Color(255, 255, 255, 80));

        StartB = new JButton("START");
        StartB.addActionListener(this);
        StartB.setBounds(panelBotones.getWidth() / 4 - 40, panelBotones.getHeight() / 3 - 10, panelBotones.getWidth() / 6, panelBotones.getHeight() / 2);
        StartB.setBackground(new Color(50, 205, 50));
        StartB.setForeground(Color.WHITE);
        panelBotones.add(StartB);
        
        /*Botón de parar el algoritmo*/
        StopB = new JButton("STOP");
        StopB.addActionListener(this);
        StopB.setBounds(panelBotones.getWidth() / 2 + 100, panelBotones.getHeight() / 3 - 10, panelBotones.getWidth() / 6, panelBotones.getHeight() / 2);
        StopB.setBackground(new Color(194, 59, 34));
        StopB.setForeground(Color.WHITE);
        panelBotones.add(StopB);

        this.add(panelBotones);
        
        /*Leyenda de algoritmos*/
        Leyenda leyenda = new Leyenda(margenLeyenda, ((2 * height) / 3) - 60, this);
        this.add(leyenda);
        
        /*Indicador del tamaño del array*/
        nElementos = new NElementos(margenLeyenda, ((2 * height) / 3) - 60, this);
        this.add(nElementos);

        /*Panel sobre el que pintaremos el gráfico*/
        graphP = new GraphPanel(width - margenLeyenda, height, this, prog.getModel());
        this.add(graphP);
        this.add(fondo);

        /*Configura el cierre de la ventana al presionar la x*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        /*Establece la posición de la ventana en el centro de la pantalla*/
        setLocationRelativeTo(null); 
        /*Desactiva la posibilidad de reescalar la ventana*/
        setResizable(false);
        setVisible(true);
    }
    
    @Override
    /**
     * Este método recoge la lógica asociada a cada uno de los botones 
     * disponibles en la vista.
     * @param e Evento asociado a cada botón disponible en la vista.
     */
    public void actionPerformed(ActionEvent e) {
        /*Si presionamos el boton START*/
        if (e.getSource() == StartB) {
            /*Notificamos al main*/
            prog.notificar("proceso-start");
        /*Si presionamos el boton STOP*/
        } else if (e.getSource() == StopB) {
            /*Notificamos al main*/
            prog.notificar("proceso-stop");
        /*Si se presiona el selector de algoritmos*/
        } else if (e.getSource() == algoritmos) {
            /*En función de la selección realizada ajustaremos el escalado a la
            gráfica asociada al algoritmo escogido*/
            switch (algoritmos.getSelectedItem().toString()) {
                case "SortAndGet":
                    graphP.setReescalarY();
                    break;
                case "Hashing":
                    graphP.setReescalarZ();
                    break;
                case "Prod. Vectorial":
                    graphP.setReescalarW();
                    break;
                default:
                    System.out.println("Error: algoritmo de rescalación '" + algoritmos.getSelectedItem().toString() + "' no encontrado");
            }
            //ajustamos el reescalado y volvemos a pintar la gráfica
            this.graphP.changingRescaling();
            repaint();
        }
    }
    
    /**
     * Método que hace visible el JFrame.
     */
    public void mostrar() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Asigna el modelo m a la clase graphP
     * @param m Puntero al Modelo
     */
    public void resetModel(Model m) {
        graphP.resetModel(m);
    }

    /**
     * Modifica el valor del booleano que controla la ejecución del pintado del
     * gráfico
     * @param b booleano encargado de controlar la ejecución del GraphPanel
     */
    public void setRunning(boolean b){
        graphP.setRunning(b);
    }

    /**
     * Actualiza el valor de N en el JPanel
     * @param s Valor actual de N
     */
    public void setN(String s) {
        this.nElementos.setN(s);
    }
    
    /**
     * Devuelve el algoritmo actual seleccionado para reescalar la gráfica
     * @return String del algoritmo seleccionado
     */
    protected String getSelectedAlg() {
        return algoritmos.getSelectedItem().toString();
    }
    
    /*Con esto mostramos a través de la vista el número de elementos con el que
    se está trabajando*/
    private class NElementos extends JPanel{
        /*Variables de clase*/
        private final int width;
        private final int height;
        private final View view;
        private JLabel label;
        
        public NElementos(int w, int h, View v){
            width = w;
            height = h;
            view = v;
            setBounds(view.getWidth() - margenLeyenda * 2, 40, width + 60, 40);
            setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(Color.BLACK, 2),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            setBackground(Color.WHITE);
            initC();
        }

        private void initC() {
            setLayout(null);
            label = new JLabel("n: 0");
            label.setLocation(20, 0);
            label.setSize(width, 40);
            label.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
            label.setHorizontalAlignment(0);
            this.add(label);
            this.setVisible(true);
        }
        
        protected void setN(String s){
            this.label.setText("n: " + s);
        }
    }
    
    /*Con esta clase mostramos la leyenda asociada al gráfico*/
    private class Leyenda extends JPanel {
        /*Variables de clase*/
        private final int width;
        private final int height;
        private final View view;
        private final Color[] colores = {Color.RED, new Color(68, 168, 74), Color.BLUE};

        public Leyenda(int w, int h, View v) {
            width = w;
            height = h;
            view = v;
            setBounds(view.getWidth() - view.margenLeyenda * 2, 100, width + 60, height-60);
            setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(Color.BLACK, 2),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            setBackground(Color.WHITE);
            initC();
        }

        private void initC() {
            setLayout(null);
            JLabel label = new JLabel("Leyenda");
            label.setBounds(10, 10, getWidth() - 20, 30);
            label.setFont(new Font("Britannic Bold", Font.PLAIN, 30));
            label.setForeground(Color.BLACK);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            add(label);
            
            /*La variable y se encarga de la distancia que habrá entre cada
            label*/
            int y = 140;
            for (int i = 1; i <= 3; i++) {
                JLabel labelI = new JLabel("" + alg[i - 1]);
                labelI.setBounds(10, y, getWidth() - 20, 20);
                labelI.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
                labelI.setForeground(colores[i - 1]);
                labelI.setHorizontalAlignment((int) CENTER_ALIGNMENT);
                add(labelI);
                y += 140;
            }
        }
    }
}