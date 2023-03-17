/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
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
    private String tipo;

    private ArrayList<Pieza> piezas;
    private ArrayList<ArrayList<Point>> itineraris;
    private Tablero t;
    private Point inicio;

    public Recorregut(Practica2AlgAv p, String tipo) {
        prog = p;
        this.tipo = tipo;
        t = prog.getVis().getTab();
        piezas = new ArrayList();
        itineraris = new ArrayList<>();
        obtenerPiezas(t);

        //La primera pieza colocada es la que iniciará el movimiento y se marca como casilla cero
        for (int i = 0; i < piezas.size(); i++) {
            inicio = new Point();
            inicio.x = itineraris.get(i).get(0).x;
            inicio.y = itineraris.get(i).get(0).y;
            t.getCasillas()[inicio.x][inicio.y].setNumero(i, piezas.get(i).getColor());
        }

    }

    private void obtenerPiezas(Tablero t) {
        Random rnd = new Random();
        for (int i = 0; i < t.getDimension(); i++) {
            for (int j = 0; j < t.getDimension(); j++) {
                if (t.getCasillas()[i][j].isOcupada()) {
                    Point c = new Point(i, j);
                    Pieza p = t.getCasillas()[i][j].getPieza();
                    p.setColor(new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
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
        boolean solucionat = false;
        if (tipo.contentEquals("start-recursivo")) {
            solucionat = recorregutRecursiu(t, piezas.get(0), 0);
        } else if (tipo.contentEquals("start-iterativo")) {
            solucionat = recorregutIteratiu(t, piezas, itineraris);
        }

        if (solucionat) {
            System.out.println("S'ha trobat solució");
        } else {
            System.out.println("No hi ha solució");
        }
    }

    private boolean recorregutRecursiu(Tablero t, Pieza p, int k) {
        if (k == t.getDimension() * t.getDimension() - piezas.size()) {
            return true;
        } else {
            for (int i = 0; i < p.numMov(); i++) {
                int size = itineraris.get(piezas.indexOf(p)).size();
                Point pAux = new Point();
                pAux = itineraris.get(piezas.indexOf(p)).get(size - 1);
                int x1 = pAux.x + p.getMovX(i);
                int y1 = pAux.y + p.getMovY(i);
                Point pAux2 = new Point(x1, y1);
                if (t.isValid(x1, y1)) {
                    itineraris.get(piezas.indexOf(p)).add(pAux2);
                    t.getCasillas()[x1][y1].setNumero(k + piezas.size(), p.getColor());
                    t.getCasillas()[x1][y1].pintarPieza(p);
                    if (recorregutRecursiu(t, piezas.get((k + 1) % piezas.size()), k + 1)) {
                        return true;
                    }
                    itineraris.get(piezas.indexOf(p)).remove(size);
                    t.getCasillas()[x1][y1].borrar();
                }
            }
            return false;
        }
    }

    private boolean recorregutRecursiu1(Tablero m, Pieza p, int k) {
        int max = m.getDimension() * m.getDimension() - piezas.size();

        for (int i = 0; i < p.numMov(); i++) {
            int size = itineraris.get(piezas.indexOf(p)).size();
            Point pAux = itineraris.get(piezas.indexOf(p)).get(size - 1);
            int x1 = pAux.x + p.getMovX(i);
            int y1 = pAux.y + p.getMovY(i);
            Point pAux2 = new Point(x1, y1);

            if (m.isValid(pAux2.x, pAux2.y) && k == max - 1) {
                itineraris.get(piezas.indexOf(p)).add(pAux2);
                t.getCasillas()[x1][y1].setNumero(k + piezas.size(), p.getColor());
                t.getCasillas()[x1][y1].pintarPieza(p);
                return true;
            } else if (m.isValid(pAux2.x, pAux2.y) && k < max - 1) {
                itineraris.get(piezas.indexOf(p)).add(pAux2);
                t.getCasillas()[x1][y1].setNumero(k + piezas.size(), p.getColor());
                t.getCasillas()[x1][y1].pintarPieza(p);
                if (recorregutRecursiu1(m, piezas.get((k + 1) % piezas.size()), k + 1)) {
                    return true;
                }
                itineraris.get(piezas.indexOf(p)).remove(size);
                t.getCasillas()[x1][y1].borrar();
            }
        }
        return false;
    }

    private boolean recorregutIteratiu(Tablero m, ArrayList<Pieza> piezas, ArrayList<ArrayList<Point>> itineraris) {
        int max = m.getDimension() * m.getDimension() - piezas.size();

        //array solució
        int t[] = new int[max];
        for (int i = 0; i < t.length; i++) {
            t[i] = -1;
        }

        //init node
        int k = 0; //nivell profunditat node que s'està processant

        while (k >= 0) { //encara no hem recorregut tot l'arbre
            int torn = k % piezas.size();
            Pieza p = piezas.get(torn);
            int size = itineraris.get(torn).size();
            if (t[k] < p.numMov() - 1) { //encara no s'ha processat tots els valors pel node k
                t[k]++; //processar node k

                Point pAux = itineraris.get(torn).get(size - 1);
                int x1 = pAux.x + p.getMovX(t[k]);
                int y1 = pAux.y + p.getMovY(t[k]);
                Point pAux2 = new Point(x1, y1);
                if (m.isValid(pAux2.x, pAux2.y) && (k == t.length - 1)) { //solució
                    itineraris.get(torn).add(pAux2);
                    m.getCasillas()[x1][y1].setNumero(k + piezas.size(), p.getColor());
                    m.getCasillas()[x1][y1].pintarPieza(p);
                    return true;
                } else if (m.isValid(pAux2.x, pAux2.y) && (k < t.length - 1)) { //possibilitat solució
                    itineraris.get(torn).add(pAux2);
                    m.getCasillas()[x1][y1].setNumero(k + piezas.size(), p.getColor());
                    m.getCasillas()[x1][y1].pintarPieza(p);
                    k++; //Passam al següent nivell;
                }
            } else { //s'han processat tots els valors pel node k
                //inicialitzar el node k
                t[k] = -1;

                //tornar al nivell anterior
                k--;
                if (k < 0) {
                    return false;
                }
                torn = k % piezas.size();
                p = piezas.get(torn);
                size = itineraris.get(torn).size();
                m.getCasillas()[itineraris.get(torn).get(size - 1).x][itineraris.get(torn).get(size - 1).y].borrar();
                itineraris.get(torn).remove(size - 1);

            }

        }

        return false;
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
           // seguir = false;
        } else if (s.startsWith("start-recursivo")) {
            this.start();
        }
    }

}
