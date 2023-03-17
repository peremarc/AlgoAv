/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import modelo.Tablero;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import practica2algav.Practica2AlgAv;

/**
 *
 * @author jfher
 */
public class Vista extends JFrame {

    private final int h;
    private final int w;
    private final Practica2AlgAv prog;
    private final int margenLat = 160;
    private Tablero tab;
    public String piezaSelec;
    private int dimensionT;

    public Vista(int height, int width, Practica2AlgAv p, int d) {
        h = height;
        w = width;
        prog = p;
        dimensionT = d;
        setSize(w + margenLat, height);
        /*Configura el cierre de la ventana al presionar la x*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*Establece la posición de la ventana en el centro de la pantalla*/
        setLocationRelativeTo(null);
        setLayout(null);
        /*Imagen del fondo del JFrame*/
        JLabel fondo;
        ImageIcon img = new ImageIcon("src/Imagenes/fondo.jpg");
        fondo = new JLabel("", img, JLabel.CENTER);
        fondo.setBounds(0, 0, getWidth(), getHeight());
        initC();
        add(fondo);
        /*Desactiva la posibilidad de reescalar la ventana*/
        setResizable(false);
        setVisible(true);
    }

    private void initC() {
        Tablero t = new Tablero(this, dimensionT);
        tab = t;
        add(t);

        MenuLat mIz = new MenuLat(this);
        mIz.setLocation(10, 10);
        mIz.initP();

        MenuLat mDe = new MenuLat(this);
        mDe.setLocation(this.getWidth() - (margenLat), 10);
        mDe.initB();

        add(mDe);
        add(mIz);
    }

    public int getMargenLat() {
        return margenLat;
    }

    public Tablero getTab() {
        return tab;
    }

    public Practica2AlgAv getProg() {
        return prog;
    }

}
