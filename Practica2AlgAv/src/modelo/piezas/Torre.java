/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.piezas;

/**
 *
 * @author mascport
 */
public class Torre extends Pieza {

    public Torre() {
        afectadimension = true; //se mueve en dimensión tablero
        nombre = this.getClass().getName();
        imagen = "src/imagenes/torre.png";
        movx = new int[0];
        movy = new int[0];
    }

    public Torre(int d) {
        afectadimension = true; //se mueve en dimensión tablero
        nombre = this.getClass().getName();
        imagen = "src/imagenes/torre.png";
        movx = new int[(d - 1) * 4];
        movy = new int[(d - 1) * 4];
        int pos = 0;
        for (int i = -(d - 1); i < d; i++) {
            if (i != 0) {
                movx[pos] = 0; // vertical
                movy[pos++] = i; //vertical
                movx[pos] = i; // horizontal
                movy[pos++] = 0; //horizontal            
            }
        }
    }
}
