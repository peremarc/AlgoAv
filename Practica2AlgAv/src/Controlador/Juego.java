/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import practica2algav.*;
import Model.*;

/**
 *
 * @author mascport
 */
public class Juego extends Thread {

    private Reina dat;
    private Practica2AlgAv prog;
    private int puestas;

    public Juego(Reina d, Practica2AlgAv n) {
        dat = d;
        prog = n;
    }

//    @Override
//    public void run() {
//        puestas = 0;
//        long tiempo = System.nanoTime();
//        ponerPieza();
//        if (puestas < dat.getDimension()) {
//            prog.notificar("ponalerta:Solución no encontrada");
//        } else {
//            tiempo = System.nanoTime() - tiempo;
//            tiempo = tiempo / 1000000;
//            String aux = "He tardado: " + tiempo + " milisegundos";
//            prog.notificar("ponalerta:" + aux);
//        }
//    }
//
//    private void ponerPieza() {
//        if (puestas < dat.getDimension()) {
//            boolean colocada = false;
//            for (int i = 0; (i < dat.getDimension()) && (!colocada); i++) {
//                for (int j = 0; (j < dat.getDimension()) && (!colocada); j++) {
//                    if (dat.hayPieza(i, j) == false) { //casilla candidata
//                        if (noMataNada(i, j)) {
//                            dat.ponPieza(i, j);
//                            puestas++;
//                            colocada = true;
//                            prog.notificar("repintar");
//                            //esperar();
//                            ponerPieza(); //******* llamada recurrente
//                            if (puestas < dat.getDimension()) { //no hemos terminado. hay que quitarla
//                                dat.quitaPieza(i, j);
//                                puestas--;
//                                colocada = false;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private boolean noMataNada(int x, int y) {
//        boolean nomata = true;
//        int h, v;
//        int movs = dat.getNumMovs();
//        for (int i = 0; i < movs && nomata; i++) {
//            h = x + dat.getMovX(i);
//            v = y + dat.getMovY(i);
//            if ((h >= 0) && (h < dat.getDimension())
//                    && (v >= 0) && (v < dat.getDimension())) { //esta en tablero
//                if (dat.hayPieza(h, v)) {  //mato otra pieza
//                    nomata = false;
//                }
//            }
//        }
//        return nomata;
//    }
//
//    private void esperar() {
//        try {
//            Thread.sleep(1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public void setDatos(Datos d) {
//        dat = d;
//    }
}
