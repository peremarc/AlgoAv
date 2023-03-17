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
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import modelo.Tablero;
import modelo.piezas.Rey;
import modelo.piezas.Torre;
import practica2algav.Practica2AlgAv;

/**
 *
 * @author jfher
 */
public class MenuLat extends JPanel implements ActionListener {

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

    public void initP() {
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
        Image nuevaImg2 = img2.getScaledInstance(tamPieza, tamPieza, Image.SCALE_SMOOTH);
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

        Torre torre = new Torre();
        ImageIcon imgTorre = new ImageIcon(torre.getImagen());
        Image img3 = imgTorre.getImage();
        Image nuevaImg3 = img3.getScaledInstance(tamPieza, tamPieza, Image.SCALE_SMOOTH);
        imgTorre.setImage(nuevaImg3);
        JLabel labelTorre = new JLabel(imgTorre);
        labelTorre.setBounds(getWidth() / 4, (Margen * 5) + (2 * tamPieza),
                imgCab.getIconWidth(), imgCab.getIconHeight());
        labelTorre.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.piezaSelec = "torre";
            }
        });
        add(labelTorre);
        
        Rey rey = new Rey();
        ImageIcon imgRey = new ImageIcon(rey.getImagen());
        Image img4 = imgRey.getImage();
        Image nuevaImg4 = img4.getScaledInstance(tamPieza, tamPieza, Image.SCALE_SMOOTH);
        imgRey.setImage(nuevaImg4);
        JLabel labelRey = new JLabel(imgRey);
        labelRey.setBounds(getWidth() / 4, (Margen * 7) + (3 * tamPieza),
                imgCab.getIconWidth(), imgCab.getIconHeight());
        labelRey.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vista.piezaSelec = "rey";
            }
        });
        add(labelRey);
    }

    public void initB() {
        JButton recu = new JButton("RECURSIVO");
        recu.setBounds(10, 20, getWidth() - 20, getHeight() / 8);
        recu.setBackground(Color.BLACK);
        recu.setForeground(Color.WHITE);
        recu.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        recu.addActionListener(this);
        add(recu);
        
        JButton iter = new JButton("ITERATIVO");
        iter.setBounds(10, 40 + getHeight() / 8, getWidth() - 20, getHeight() / 8);
        iter.setBackground(Color.BLACK);
        iter.setForeground(Color.WHITE);
        iter.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        iter.addActionListener(this);
        add(iter);

        JTextField textField = new JTextField("Tamaño del tablero");
        textField.setForeground(Color.GRAY);
        textField.setBounds(10, 2*(40 + getHeight() / 8), getWidth() - 20, getHeight() / 16);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(0, 5, 0, 5)
        ));
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("Tamaño del tablero")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText("Tamaño del tablero");
                }
            }
        });
        add(textField);

        JButton changeButton = new JButton("✔");
        changeButton.setBounds(getWidth() / 2 + 14, (40 + getHeight() / 8) + getHeight() / 4, getWidth() / 3, getHeight() / 18);
        changeButton.setBackground(Color.BLACK);
        changeButton.setForeground(Color.WHITE);
        changeButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                if (!"Tamaño del tablero".equals(text)) {
                    int d = Integer.parseInt(text);
                    Practica2AlgAv p = vista.getProg();
                    p.resetCon();
                    vista.dispose();
                    Vista v = new Vista(600, 800, p, d);
                    p.setVista(v);
                }
            }
        });
        add(changeButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("RECURSIVO")) {
            vista.getProg().notificar("start-recursivo");
        } else if(ae.getActionCommand().equals("ITERATIVO")) {
            vista.getProg().notificar("start-iterativo");
        }
    }
}
