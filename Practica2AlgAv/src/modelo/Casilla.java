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
import modelo.piezas.Pieza;
import modelo.piezas.*;

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
        nC.setFont(new Font("Britannic Bold", Font.PLAIN, (int) 144 / vista.getTab().getDimension()));
        nC.setForeground(c);
        add(nC);
        repaint();
    }

    public void onClickIz() {
        switch (vista.piezaSelec) {
            case "caballo":
                pieza = new Caballo();
                pintarPieza(pieza);
                break;
            case "reina":
                pieza = new Reina(vista.getTab().getDimension());
                pintarPieza(pieza);
                break;
            case "torre":
                pieza = new Torre(vista.getTab().getDimension());
                pintarPieza(pieza);
                break;
            case "rey":
                pieza = new Rey();
                pintarPieza(pieza);
                break;
            case "alfil":
                pieza = new Alfil(vista.getTab().getDimension());
                pintarPieza(pieza);
                break;
            case "drac":
                pieza = new Drac();
                pintarPieza(pieza);
                break;
            default:
                break;
        }
    }

    public void pintarPieza(Pieza p) {
        ImageIcon imgP = new ImageIcon(p.getImagen());
        Image img = imgP.getImage();
        Image nuevaImg = img.getScaledInstance(tamPieza, tamPieza, Image.SCALE_SMOOTH);
        imgP.setImage(nuevaImg);
        piezaIcon = new JLabel(imgP);
        piezaIcon.setBounds(getWidth() / 4, getHeight() / 4, imgP.getIconWidth(), imgP.getIconHeight());
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
