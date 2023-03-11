/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author jfher
 */
public class Tablero extends JPanel {

    private final Vista vista;
    private Casilla[][] casillas = new Casilla[8][8];

    public Tablero(Vista v) {
        vista = v;
        setBounds(vista.getMargenLat() + 10, 10,
                vista.getWidth() - (2 * vista.getMargenLat() + 20), vista.getHeight() - 50);
        setLayout(null);
        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 4),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        initC();
    }

    private void initC() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Casilla c = new Casilla(vista);
                c.setBounds(j * (getWidth() / 8) + 2, i * (getHeight() / 8) + 3, getWidth() / 8,
                        getHeight() / 8);
                if ((i + j) % 2 == 0) {
                    c.setBackground(Color.WHITE);
                    c.setNumero(i + j);
                } else {
                    c.setBackground(Color.BLACK);
                    c.setNumero(i + j);
                }
                casillas[i][j] = c;
                add(c);
            }
        }
    }
}
