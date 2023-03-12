/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import modelo.piezas.Caballo;
import modelo.piezas.Reina;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author jfher
 */
public class MenuLat extends JPanel {

    private int Margen = 10;
    private int tamPieza = 60;
    private Vista vista;

    public MenuLat(Vista v) {
        vista = v;
        setSize(v.getMargenLat() - 20, v.getTab().getHeight());
        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        setBackground(new Color(255, 255, 255, 120));
        setLayout(null);
    }

    public void initC() {
        Caballo caballo = new Caballo();
        ImageIcon imgCab = new ImageIcon(caballo.getImagen());
        Image img = imgCab.getImage();
        Image nuevaImg = img.getScaledInstance(tamPieza, tamPieza, Image.SCALE_SMOOTH);
        imgCab.setImage(nuevaImg);
        JLabel labelCab = new JLabel(imgCab);
        labelCab.setBounds(getWidth() / 4, Margen, imgCab.getIconWidth(), imgCab.getIconHeight());
        labelCab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.piezaSelec = "caballo";
            }
        });
        add(labelCab);

        Reina reina = new Reina();
        ImageIcon imgReina = new ImageIcon(reina.getImagen());
        Image img2 = imgReina.getImage();
        Image nuevaImg2 = img2.getScaledInstance(tamPieza , tamPieza, Image.SCALE_SMOOTH);
        imgReina.setImage(nuevaImg2);
        JLabel labelReina = new JLabel(imgReina);
        labelReina.setBounds(getWidth() / 4, (Margen * 3) + tamPieza, imgCab.getIconWidth(), imgCab.getIconHeight());
        labelReina.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.piezaSelec = "reina";
            }
        });
        add(labelReina);
    }
}
