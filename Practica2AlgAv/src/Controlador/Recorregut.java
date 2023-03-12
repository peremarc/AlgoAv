/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import modelo.Tablero;
import modelo.piezas.Pieza;
import modelo.piezas.Reina;
import practica2algav.*;

/**
 *
 * @author mascport
 */
public class Recorregut extends Thread implements PerEsdeveniments {

    private Practica2AlgAv prog;
    private boolean seguir;

    private ArrayList<Pieza> piezas;
    private ArrayList<ArrayList<Point>> itineraris;
    private Tablero t;
    private Point inicio;

    public Recorregut(Practica2AlgAv p) {
        prog = p;
        t = prog.getVis().getTab();
        piezas = new ArrayList();
        itineraris = new ArrayList<>();
        obtenerPiezas(t);

        //La primera pieza colocada es la que iniciará el movimiento y se marca como casilla cero
        inicio = new Point();
        inicio.x = itineraris.get(0).get(0).x;
        inicio.y = itineraris.get(0).get(0).y;
        t.getCasillas()[inicio.x][inicio.y].setNumero(0);
    }

    private void obtenerPiezas(Tablero t) {

        for (int i = 0; i < t.getDimension(); i++) {
            for (int j = 0; j < t.getDimension(); j++) {
                if (t.getCasillas()[i][j].isOcupada()) {
                    Point c = new Point(i, j);
                    Pieza p = t.getCasillas()[i][j].getPieza();
                    piezas.add(p);
                    ArrayList<Point> itinerari = new ArrayList<>();
                    itinerari.add(c);
                    itineraris.add(itinerari);
                }
            }
        }
    }

    @Override
    public void run() {
        seguir = true;
//        while (seguir) {
        if (recorregut(t, piezas.get(0), 0)) {
            System.out.println("S'ha trobat solució");
        } else {
            System.out.println("No hi ha solució");
        }
//        }
    }

    private boolean recorregut(Tablero t, Pieza p, int k) {
        if (k == t.getDimension() * t.getDimension() - piezas.size()) {
            return true;
        } else {
            for (int i = 0; i < p.numMov(); i++) {
                int size = itineraris.get(piezas.indexOf(p)).size();
                Point pAux = new Point();
                pAux = itineraris.get(piezas.indexOf(p)).get(size - 1);
                int x1 = pAux.x + p.getMovX(i);
                int y1 = pAux.y + p.getMovY(i);
                Point pAux2 = new Point(x1,y1);
                if (t.isValid(x1, y1)) {
                    itineraris.get(piezas.indexOf(p)).add(pAux2);
                    t.getCasillas()[x1][y1].setNumero(k + 1);
                    t.getCasillas()[x1][y1].pintarPieza(p);
                    if (recorregut(t, piezas.get((k + 1) % piezas.size()), k + 1)) {
                        return true;
                    }
                    itineraris.get(piezas.indexOf(p)).remove(size);
                    t.getCasillas()[x1][y1].borrar();
                }
            }
            return false;
        }
    }

    private void espera(long m, int n) {
        try {
            Thread.sleep(m, n);
        } catch (Exception e) {
            MeuError.informaError(e);
        }
    }

    @Override
    public void notificar(String s) {
        if (s.startsWith("Parar")) {
            seguir = false;
        } else if (s.startsWith("proceso-start")) {
            this.start();
        }
    }

}
