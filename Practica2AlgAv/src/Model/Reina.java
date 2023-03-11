/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author jfher
 */
public class Reina extends Pieza {

    public Reina() {
        afectadimension = true; //se mueve en dimensión tablero
        imagen = "src/Imagenes/reina.png";
        movx = new int[0];
        movy = new int[0];
        nombre = this.getClass().getName();
    }

    public Reina(int d) {
        afectadimension = true; //se mueve en dimensión tablero
        nombre = this.getClass().getName();
        imagen = "src/Imagenes/reina.png";
        movx = new int[(d - 1) * 4 * 2];
        movy = new int[(d - 1) * 4 * 2];
        int pos = 0;
        for (int i = -(d - 1); i < d; i++) {
            if (i != 0) {
                movx[pos] = 0; // vertical
                movy[pos++] = i; //vertical
                movx[pos] = i; // horizontal
                movy[pos++] = 0; //horizontal
                movx[pos] = i; //   oblicuo 1
                movy[pos++] = i; //    oblicuo1
                movx[pos] = -i; //   oblicuo 2
                movy[pos++] = i; //    oblicuo2               
            }
        }
    }
}
