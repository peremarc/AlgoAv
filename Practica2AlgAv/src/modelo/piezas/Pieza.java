/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.piezas;

import java.awt.Color;

/**
 *
 * @author jfher
 */
public abstract class Pieza {

    protected int movx[];
    protected int movy[];
    protected String nombre;
    protected String imagen;
    protected boolean afectadimension = false;
    protected Color colorP;

    public Color getColor() {
        return colorP;
    }

    public void setColor(Color c) {
        colorP = c;
    }

    public boolean afectaDimension() {
        return afectadimension;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public int getNumMovs() {
        return movx.length;
    }

    public int getMovX(int i) {
        return movx[i];
    }

    public int getMovY(int i) {
        return movy[i];
    }

    public int numMov() {
        return movx.length;
    }
}
