package de.vp;

import java.util.Date;
import java.util.Timer;
import javax.swing.JPanel;

/**
 *
 * @author Felix & Nicolai
 */
public class Spielsteuerung {

    private int depot, werkstatt, geld, anzLinien, hoehe, breite;
    private boolean[][] hatBahnhof;
    private Stadtteil[][] teile;
    private Bahnhof[][] bahnhoefe;
    private Linie[] linien;
    private Timer timer;
    private GUITimer guiTimer;
    private StrgTimer strgTimer;

    // ========== Anfang Spielvariablen ==========
    private final int maxMinus = -10000000;
    private final int preisZug = 1000000;
    private final int geldZugZurueck = 50000;
    private final int preisBhf = 100000;
    private final int preisLinie = 10000;
    private final int reparatur = 10000;
    // ========== Ende Spielvariablen ==========

    public Spielsteuerung(int h, int b, JPanel panel) {
        hoehe = h;
        breite = b;
        depot = 0;
        werkstatt = 0;
        anzLinien = 0;
        geld = 100000000; // 100 Mio.
        timer = new Timer();
        guiTimer = new GUITimer(panel);
        strgTimer = new StrgTimer(this);
        hatBahnhof = new boolean[hoehe][breite];
        teile = new Stadtteil[hoehe][breite];
        bahnhoefe = new Bahnhof[hoehe][breite];
        linien = new Linie[20];
        for (int y = 0; y < hoehe; y++) {
            for (int x = 0; x < breite; x++) {
                hatBahnhof[y][x] = false;
                teile[y][x] = null;
                bahnhoefe[y][x] = null;
            }
        }
        for (int i = 0; i < linien.length; i++) {
            linien[i] = null;
        }
        timer.scheduleAtFixedRate(guiTimer, 0, 40);
        timer.scheduleAtFixedRate(strgTimer, 0, 8);
    }
    
    /**
     *
     * @return Die aktuelle In-Game-Zeit als Date-Objekt
     */
    public Date getTime() {
        return strgTimer.getTime();
    }

    /**
     * 
     * Fügt dem Depot einen neuen Zug hinzu
     * @return 
     */
    public boolean zugKaufen() {
        if (geld - preisZug >= maxMinus) {
            geld = geld - preisZug;
            depot++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * Entfernt einen Zug aus dem Depot
     * @return 
     */
    public boolean zugVerschrotten() {
        if (depot > 0) {
            geld = geld + geldZugZurueck;
            depot--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * Fügt der gegebenen Linie einen Zug aus dem Depot hinzu
     * @param l Linie
     * @return 
     */
    public boolean zugEinstellen(Linie l) {
        if (depot > 0) {
            l.zugEinstellen();
            depot--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * Entfernt aus gegebener Linie einen Zug ins Depot
     * @param l Linie
     * @return 
     */
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
     * @return 
     */
    public boolean neueLinie(String name) {
        if(geld - preisLinie >= maxMinus) {
            if(linien.length > anzLinien + 1) {
                linien[anzLinien + 1] = new Linie(name);
                anzLinien++;
                geld = geld - preisLinie;
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
    
    /**
     * 
     * Löscht die gegebene Linie aus der Liste linien[]
     * @param l die zu löschende Linie
     * @return 
     */
    public boolean linieEntfernen(Linie l){
        int x = 0;
        if(anzLinien > 0) {
            for(int i=0; i > anzLinien; i++) {
                if(linien[i].getName().equals(l.getName())){
                    x = i;
                }
            }
            Linie[] hilf = new Linie[anzLinien];
            for(int i=0; i > anzLinien; i++){
                hilf[i] = linien[i];
            }
            for(int i=x; i> linien.length - 1; i++) {
                hilf[i] = linien[i + 1];
            }
            hilf[linien.length] = null;
            linien = hilf;
            return true;
        }
        else return false;
    }
    
    /**
     * 
     * Ein neuer Bahnhof wird in bahnhoefe[x][y] an den gegebenen Koordinaten x & y eingesetzt
     * @param x 
     * @param y
     * @return 
     */
    private boolean neuerBahnhof(int x, int y) {
        if(geld - preisBhf >= maxMinus) {
            bahnhoefe[x][y] = new Bahnhof();
            geld = geld - preisBhf;
            return true;
        }
        else return false;
    }
    
    /**
     * 
     * der gegebene Bahnhof wird aus bahnhoefe[][] gelöscht
     * @param bhf
     * @return true
     */
    public boolean bhfEntfernen(Bahnhof bhf) {
        for (int h = 0; h < bahnhoefe.length; h++) {
            for (int b = 0; b < bahnhoefe[h].length; b++) {
                if(bahnhoefe[h][b].equals(bhf))
                    bahnhoefe[h][b] = null;
                }
            }
        return true;
    }

    /**
     * 
     * gegebener Stadtteil wird durch Zufall/Wahrscheinlichkeit an einer Stelle der Stadt eingefügt
     * @param s
     * @return 
     */
    public boolean stadtteilBauen(Stadtteil s) {
        return true;
    }
    
    /**
     * 
     * Baut automatisch die "Altstadt" der Karte - Als Ausgangssituation bei "Spiel starten" für das weitere Spiel
     * @return 
     */
    private boolean altstadt() {
        int mh = Math.round(hoehe/2);
        int mb = Math.round(breite/2);
        teile[mh][mb] = new Rathaus();
        teile[mh-1][mb] = new Park();
        teile[mh-2][mb] = new Park();
        teile[mh+4][mb-2] = new Haus();
        teile[mh+4][mb-1] = new Haus();
        teile[mh+4][mb] = new Haus();
        teile[mh+4][mb+1] = new Haus();
        teile[mh+4][mb+2] = new Haus();
        teile[mh+3][mb-3] = new Haus();
        teile[mh+3][mb-2] = new Haus();
        teile[mh+3][mb-1] = new Haus();
        teile[mh+3][mb] = new Haus();
        teile[mh+3][mb+1] = new Haus();
        teile[mh+3][mb+2] = new Haus();
        teile[mh+3][mb+3] = new Haus();
        teile[mh+2][mb-4] = new Haus();
        teile[mh+2][mb-3] = new Haus();
        teile[mh+2][mb-2] = new Haus();
        teile[mh+2][mb-1] = new Haus();
        teile[mh+2][mb] = new Haus();
        teile[mh+2][mb+1] = new Haus();
        teile[mh+2][mb+2] = new Haus();
        teile[mh+2][mb+3] = new Haus();
        teile[mh+2][mb+4] = new Haus();
        teile[mh+1][mb-4] = new Haus();
        teile[mh+1][mb-3] = new Haus();
        teile[mh+1][mb-2] = new Haus();
        teile[mh+1][mb-1] = new Haus();
        teile[mh+1][mb] = new Haus();
        teile[mh+1][mb+1] = new Haus();
        teile[mh+1][mb+2] = new Haus();
        teile[mh+1][mb+3] = new Haus();
        teile[mh+1][mb+4] = new Haus();
        teile[mh][mb-4] = new Haus();
        teile[mh][mb-3] = new Haus();
        teile[mh][mb-2] = new Haus();
        teile[mh][mb-1] = new Haus();
        teile[mh][mb+1] = new Haus();
        teile[mh][mb+2] = new Haus();
        teile[mh][mb+3] = new Haus();
        teile[mh][mb+4] = new Haus();
        teile[mh-1][mb-4] = new Haus();
        teile[mh-1][mb-3] = new Haus();
        teile[mh-1][mb-2] = new Haus();
        teile[mh-1][mb-1] = new Haus();
        teile[mh-1][mb+1] = new Haus();
        teile[mh-1][mb+2] = new Haus();
        teile[mh-1][mb+3] = new Haus();
        teile[mh-1][mb+4] = new Haus();
        teile[mh-2][mb-4] = new Haus();
        teile[mh-2][mb-3] = new Haus();
        teile[mh-2][mb-2] = new Haus();
        teile[mh-2][mb-1] = new Haus();
        teile[mh-2][mb+1] = new Haus();
        teile[mh-2][mb+2] = new Haus();
        teile[mh-2][mb+3] = new Haus();
        teile[mh-2][mb+4] = new Haus();
        teile[mh-3][mb-3] = new Haus();
        teile[mh-3][mb-2] = new Haus();
        teile[mh-3][mb-1] = new Haus();
        teile[mh-3][mb] = new Haus();
        teile[mh-3][mb+1] = new Haus();
        teile[mh-3][mb+2] = new Haus();
        teile[mh-3][mb+3] = new Haus();
        teile[mh-4][mb-2] = new Haus();
        teile[mh-4][mb-1] = new Haus();
        teile[mh-4][mb] = new Haus();
        teile[mh-4][mb+1] = new Haus();
        teile[mh-4][mb+2] = new Haus();
        return true;
    }
    
    /**
     * 
     * verschiebt einen Zug für Geld von werkstatt zu depot 
     * @return 
     */
    public boolean zugReparieren() {
        if(werkstatt != 0 && geld - reparatur >= maxMinus){
            werkstatt--;
            depot++;
            geld = geld - reparatur;
            return true;
        }
        else return false;
    }
    
    /**
     * berechnet alle Kosten, die durch Bahnhöfe, Züge, etc anfallen.
     */
    private void gesamtKosten() {
        
    }
    
    /**
     * berechnet den Gesamten Gewinn
     */
    private void gesamtGewinn() {
        
    }
    
    /**
     * 
     * ?!
     * @return 
     */
    public boolean step() {
        return true;
    }
    
    /**
     * was weiß ich!
     * @return 
     */
    public boolean klick() {
        return true;
    }
    
    public int kapazitaet(Linie l) {
        int k = 0;
        for(int i=0; i < linien.length; i++) {
            if(linien[i].equals(l)) {
                k = linien[i].kapazitaet();
            }
        }
        return k;
    }
    
}
