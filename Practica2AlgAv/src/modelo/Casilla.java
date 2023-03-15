/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.piezas.Caballo;
import modelo.piezas.Pieza;
import modelo.piezas.Reina;
import vista.Vista;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import modelo.piezas.Caballo;
import modelo.piezas.Pieza;
import modelo.piezas.Reina;

/**
 *
 * @author jfher
 */
public class Casilla extends JPanel {

    private int h;
    private int w;
    private JLabel nC;
    public boolean visitada;
    public boolean ocupada = false;
    private Vista vista;
    private int tamPieza;
    private int Margen = 10;
    private JLabel piezaIcon;
    private Pieza pieza;

    public Casilla(Vista v) {
        vista = v;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    onClickIz();
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    onClickDe();
                }
            }
        });
        setLayout(null);
    }

    public void setNumero(int i, Color c) {
        nC = new JLabel("" + i);
        nC.setBounds(2, 2, this.getWidth() / 2, this.getHeight() / 2);
        nC.setFont(new Font("Britannic Bold", Font.PLAIN,(int) 144/vista.getTab().getDimension()));
        nC.setForeground(c);
        add(nC);
        repaint();
    }

    public void onClickIz() {
        if ("caballo".equals(vista.piezaSelec)) {
            pieza = new Caballo();
            pintarPieza(pieza);
        } else if ("reina".equals(vista.piezaSelec)) {
            pieza = new Reina(vista.getTab().getDimension());
            pintarPieza(pieza);
        }
    }

    public void pintarPieza(Pieza p) {
        ImageIcon imgCab = new ImageIcon(p.getImagen());
        Image img = imgCab.getImage();
        Image nuevaImg = img.getScaledInstance(tamPieza, tamPieza, Image.SCALE_SMOOTH);
        imgCab.setImage(nuevaImg);
        piezaIcon = new JLabel(imgCab);
        piezaIcon.setBounds(getWidth() / 4, getHeight() / 4, imgCab.getIconWidth(), imgCab.getIconHeight());
        add(piezaIcon);
        ocupada = true;
        repaint();
    }

    public void onClickDe() {
        if (ocupada) {
            remove(piezaIcon);
            ocupada = false;
            pieza = null;
            repaint();
        }
    }

    public Pieza getPieza() {
        return pieza;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void borrar() {
        remove(nC);
        remove(piezaIcon);
        ocupada = false;
        repaint();
    }

    public void setTamPieza(int tamPieza) {
        this.tamPieza = tamPieza;
    }

}
