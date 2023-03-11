/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Model.Caballo;
import Model.Pieza;
import Model.Reina;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
    private int tamPieza = 50;
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

    public void setNumero(int i) {
        nC = new JLabel("" + i);
        nC.setBounds(4, 4, this.getWidth() / 7, this.getHeight() / 8);
        nC.setFont(new Font("Britannic Bold", Font.PLAIN, 12));
        nC.setForeground(Color.GREEN);
        add(nC);
    }

    public void onClickIz() {
        if ("caballo".equals(vista.piezaSelec)) {
            Caballo caballo = new Caballo();
            pieza = caballo;
            ImageIcon imgCab = new ImageIcon(caballo.getImagen());
            Image img = imgCab.getImage();
            Image nuevaImg = img.getScaledInstance(tamPieza, tamPieza, Image.SCALE_SMOOTH);
            imgCab.setImage(nuevaImg);
            piezaIcon = new JLabel(imgCab);
            piezaIcon.setBounds(getWidth() / 4, Margen, imgCab.getIconWidth(), imgCab.getIconHeight());
            add(piezaIcon);
            repaint();
            vista.piezaSelec = "";
            ocupada = true;
        } else if ("reina".equals(vista.piezaSelec)) {
            Reina reina = new Reina();
            pieza = reina;
            ImageIcon imgReina = new ImageIcon(reina.getImagen());
            Image img = imgReina.getImage();
            Image nuevaImg = img.getScaledInstance(tamPieza, tamPieza, Image.SCALE_SMOOTH);
            imgReina.setImage(nuevaImg);
            piezaIcon = new JLabel(imgReina);
            piezaIcon.setBounds(getWidth() / 4, Margen, imgReina.getIconWidth(), imgReina.getIconHeight());
            add(piezaIcon);
            repaint();
            vista.piezaSelec = "";
            ocupada = true;
        }
    }

    public void onClickDe() {
        if (ocupada) {
            remove(piezaIcon);
            ocupada = false;
            pieza = null;
            repaint();
        }
    }

}
