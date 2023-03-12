/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.BorderLayout;
import modelo.Tablero;
import modelo.piezas.Pieza;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import practica2algav.Practica2AlgAv;

/**
 *
 * @author jfher
 */
public class Vista extends JFrame implements ActionListener{

    private final int h;
    private final int w;
    private final Practica2AlgAv prog;
    private final int margenLat = 160;
    private Tablero tab;
    public String piezaSelec;
    
    private JPanel contenedor;
    private JToolBar barra;
    private JButton StartB;

    public Vista(int height, int width, Practica2AlgAv p) {
        h = height;
        w = width;
        prog = p;
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
        Tablero t = new Tablero(this);
        tab = t;
        add(t);
        
        contenedor = new JPanel();
        contenedor.setSize(margenLat - 20, getTab().getHeight());
        contenedor.setLayout(new BorderLayout());
        barra = new JToolBar();
        StartB = new JButton("START");
        StartB.addActionListener(this);
        barra.add(StartB);
        contenedor.add(BorderLayout.PAGE_START, barra);
        contenedor.setLocation(this.getWidth()-(margenLat), 10);
        add(contenedor);
        
        MenuLat mIz = new MenuLat(this);
        mIz.setLocation(10, 10);
        mIz.initC();
        
        MenuLat mDe = new MenuLat(this);
        mDe.setLocation(this.getWidth()-(margenLat), 10);
        add(mDe);
        add(mIz);
    }

    public int getMargenLat() {
        return margenLat;
    }

    public Tablero getTab() {
        return tab;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == StartB) {
            prog.notificar("proceso-start");
        }
    }

}
