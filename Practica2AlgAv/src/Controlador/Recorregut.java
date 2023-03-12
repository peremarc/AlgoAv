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

    private HashMap<Pieza, Point> mapa;
    private ArrayList<Pieza> piezas;
    private Tablero t;
    private Point inicio;

    public Recorregut(Practica2AlgAv p) {
        prog = p;
        piezas = new ArrayList();
        mapa = obtenerPiezas(prog.getVis().getTab());
        t = prog.getVis().getTab();
        //La primera pieza colocada es la que iniciará el movimiento y se marca como casilla cero
        inicio = new Point();
        inicio.x = mapa.get(piezas.get(0)).x;
        inicio.y = mapa.get(piezas.get(0)).y;
        t.getCasillas()[inicio.x][inicio.y].setNumero(0);
    }

    private HashMap<Pieza, Point> obtenerPiezas(Tablero t) {
        HashMap<Pieza, Point> aux = new HashMap<>();
        for (int i = 0; i < t.getDimension(); i++) {
            for (int j = 0; j < t.getDimension(); j++) {
                if (t.getCasillas()[i][j].isOcupada()) {
                    Point c = new Point(i, j);
                    Pieza p = t.getCasillas()[i][j].getPieza();
                    aux.put(p, c);
                    piezas.add(p);
                }
            }
        }
        return aux;
    }

    @Override
    public void run() {
        seguir = true;
//        while (seguir) {
            if (recorregut(t, piezas.get(0), inicio.x, inicio.y, 0)) {
                System.out.println("S'ha trobat solució");
            } else {
                System.out.println("No hi ha solució");
            }
//        }
    }
    
    private boolean recorregut(Tablero t, Pieza p, int x, int y, int k) {
        if(k==t.getDimension()*t.getDimension()-piezas.size()){ 
            return true;
        }else{
            for (int i = 0; i < p.numMov(); i++) {                
                int x1 = x+p.getMovX(i);
                int y1 = y+p.getMovY(i);
                if(t.isValid(x1,y1)){
//                    t.set(x1,y1,k+1);
                    t.getCasillas()[x1][y1].setNumero(k+1);
                    t.getCasillas()[x1][y1].pintarPieza(p);
                    if(recorregut(t,piezas.get((k+1)%piezas.size()),x1,y1,k+1)) return true;
//                    t.set(x1, y1, null); 
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
