package de.vp;

/**
 *
 * @author Felix
 */
public class Spielsteuerung {
    
    private int depot, werkstatt, geld;
    private boolean[][] hatBahnhof;
    private Stadtteil[][] teile;
    private Bahnhof[][] bahnhoefe;
    
    // ========== Anfang Spielvariablen ==========
    private final int maxMinus = -10000000;
    private final int preisZug = 1000000;
    private final int geldZugZurueck = 50000;
    // ========== Ende Spielvariablen ==========
    
    public Spielsteuerung(int hoehe, int breite) {
        depot = 0;
        werkstatt = 0;
        geld = 100000000; // 100 Mio.
        hatBahnhof = new boolean[hoehe][breite];
        teile = new Stadtteil[hoehe][breite];
        bahnhoefe = new Bahnhof[hoehe][breite];
        for (int h = 0; h < hoehe; h++) {
            for (int b = 0; b < breite; b++) {
                hatBahnhof[h][b] = false;
                teile[h][b] = null;
                bahnhoefe[h][b] = null;
            }
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
    
}
