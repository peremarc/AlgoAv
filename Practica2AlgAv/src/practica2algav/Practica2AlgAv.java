/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2algav;

import controlador.Recorregut;
import vista.Vista;

/**
 *
 * @author jfher
 */
public class Practica2AlgAv implements PerEsdeveniments{
    
//    private Model mod;    // Punter al Model del patró
    private Vista vis;    // Punter a la Vista del patró
    private Recorregut con;  // punter al Control del patró

    /*
        Construcció de l'esquema MVC
     */
    private void inicio() {
//        mod = new Model(this);
        con = null;
        vis = new Vista(600, 800, this, 8);
//        vis.mostrar();
    }
    
    public void resetCon(){
        con = null;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        (new Practica2AlgAv()).inicio();
    }
    
    /*
        Funció símple de la comunicació per Patró d'esdeveniments
     */
    @Override
    public void notificar(String s) {
        if (s.startsWith("proceso-start")) {
            if (con == null) {
                con = new Recorregut(this);
                con.notificar(s);
            }
        } 
//        else if (s.startsWith("Parar")) {
//            if (con != null) {
//                con.notificar(s);
//                con = null;
//            }
//        } else if (s.startsWith("Picat:")) {
//            s = s.substring(s.indexOf(":") + 1);
//            int x = Integer.parseInt(s.substring(0, s.indexOf(",")));
//            int y = Integer.parseInt(s.substring(s.indexOf(",") + 1));
//            mod.setXY(x, y);
//        } else if (s.startsWith("Velocitat:")) {
//            int v = Integer.parseInt(s.substring(s.indexOf(":") + 1));
//            mod.setVel(v);
//        }
    }

    public Vista getVis() {
        return vis;
    }
    
    public void setVista(Vista v){
        vis = v;
    }
    
    
    
}
