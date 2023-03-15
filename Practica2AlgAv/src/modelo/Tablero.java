/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import vista.Vista;
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
    private int dim = 8;
    private Casilla[][] casillas;

    public Tablero(Vista v) {
        vista = v;
        casillas = new Casilla[dim][dim];
        setBounds(vista.getMargenLat() + 10, 10,
                vista.getWidth() - (2 * vista.getMargenLat() + 20), vista.getHeight() - 50);
        setLayout(null);
        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 4),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        setBackground(Color.black);
        initC();
    }

    private void initC() {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                Casilla c = new Casilla(vista);
                c.setBounds(j * (getWidth() / dim) + 2, i * (getHeight() / dim) + 3, getWidth() / dim,
                        getHeight() / dim);
                c.setTamPieza(getWidth() / (dim*2));
                if ((i + j) % 2 == 0) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(Color.BLACK);
                }
                casillas[i][j] = c;
                add(c);
            }
        }
    }

    public boolean isValid(int col, int row) {
        if ((col < 0) || (col >= dim) || (row < 0) || (row >= dim)) {
            return false;
        }
        if (getCasillas()[col][row].isOcupada()) {
            return false;
        }
        return true;
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public int getDimension() {
        return dim;
    }

}
