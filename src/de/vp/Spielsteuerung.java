package de.vp;

/**
 *
 * @author Felix
 */
public class Spielsteuerung {

    private int depot, werkstatt, geld, anzLinien;
    private boolean[][] hatBahnhof;
    private Stadtteil[][] teile;
    private Bahnhof[][] bahnhoefe;
    private Linie[] linien;

    // ========== Anfang Spielvariablen ==========
    private final int maxMinus = -10000000;
    private final int preisZug = 1000000;
    private final int geldZugZurueck = 50000;
    private final int preisBhf = 100000;
    private final int preisLinie = 10000;
    // ========== Ende Spielvariablen ==========

    public Spielsteuerung(int hoehe, int breite) {
        depot = 0;
        werkstatt = 0;
        anzLinien = 0;
        geld = 100000000; // 100 Mio.
        hatBahnhof = new boolean[hoehe][breite];
        teile = new Stadtteil[hoehe][breite];
        bahnhoefe = new Bahnhof[hoehe][breite];
        linien = new Linie[20];
        for (int h = 0; h < hoehe; h++) {
            for (int b = 0; b < breite; b++) {
                hatBahnhof[h][b] = false;
                teile[h][b] = null;
                bahnhoefe[h][b] = null;
            }
        }
        for (int i = 0; i < linien.length; i++) {
            linien[i] = null;
        }
    }

    public boolean zugKaufen() {
        if (geld - preisZug >= maxMinus) {
            geld = geld - preisZug;
            depot++;
            return true;
        } else {
            return false;
        }
    }

    public boolean zugVerschrotten() {
        if (depot > 0) {
            geld = geld + geldZugZurueck;
            depot--;
            return true;
        } else {
            return false;
        }
    }

    public boolean zugEinstellen(Linie l) {
        if (depot > 0) {
            l.zugEinstellen();
            depot--;
            return true;
        } else {
            return false;
        }
    }

    public boolean zugInsDepot(Linie l) {
        boolean b = l.zugEntfernen();
        if (b) {
            depot++;
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 
     * @param name benötigt einen Namen
     */
    public boolean neueLinie(String name) {
        if(geld - preisLinie >= maxMinus) {
            if(linien.length > anzLinien + 1) {
                linien[anzLinien + 1] = new Linie(name);
                anzLinien++;
            }
            else {
                Linie[] hilf = new Linie[anzLinien + 10];
                for(int i=0; i > anzLinien; i++){
                    hilf[i] = linien[i];
                }
                linien = hilf;
                linien[anzLinien + 1] = new Linie(name);
                anzLinien++;
            }
            return true;
        }
        else return false;
    }
    
    public boolean linieEntfernen(Linie l){
        int x = 0;
        if(anzLinien > 0) {
            for(int i=0; i > anzLinien; i++) {
                if(linien[i].getName() == l.getName()){
                    x = i;
                }
            }
            Linie[] hilf = new Linie[anzLinien];
            for(int i=0; i > anzLinien; i++){
                hilf[i] = linien[i];
            }
            for(int i=x; i> anzLinien; i++) {
                hilf[i] = linien[i + 1];
            }
            linien = hilf;
            return true;
        }
        else return false;
    }

}
