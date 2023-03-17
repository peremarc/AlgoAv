/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.piezas;

/**
 *
 * @author jfher
 */
public class Alfil extends Pieza {

    public Alfil() {
        afectadimension = true; //se mueve en dimensión tablero
        imagen = "src/imagenes/alfil.png";
        movx = new int[0];
        movy = new int[0];
        nombre = this.getClass().getName();
    }

    public Alfil(int d) {
        afectadimension = true; //se mueve en dimensión tablero
        nombre = this.getClass().getName();
        imagen = "src/Imagenes/alfil.png";
        movx = new int[(d - 1) * 4 * 2];
        movy = new int[(d - 1) * 4 * 2];
        int pos = 0;
        for (int i = -(d - 1); i < d; i++) {
            if (i != 0) {
                movx[pos] = i; //   oblicuo 1
                movy[pos++] = i; //    oblicuo1
                movx[pos] = -i; //   oblicuo 2
                movy[pos++] = i; //    oblicuo2               
            }
        }
    }
}
