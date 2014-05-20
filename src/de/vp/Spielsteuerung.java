package de.vp;

import java.awt.Color;
import java.awt.Color;
import java.awt.Color;
import java.util.Date;
import java.util.Timer;
import javax.swing.JPanel;

/**
 *
 * @author Felix & Nicolai
 */
public class Spielsteuerung {

    // ========== Testbereich ==========
    private TestTimer testTimer;
    // ==========             ==========

    private int depot, werkstatt, geld, anzLinien, hoehe, breite, hauszahl;
    private boolean[][] hatBahnhof;
    private Stadtteil[][] teile;
    private Bahnhof[][] bahnhoefe;
    private Linie[] linien;
    private Timer timer;
    private GUITimer guiTimer;
    private StrgTimer strgTimer;
    private boolean feldVoll; //Für Stadtteile bauen

    // ========== Anfang Spielvariablen ==========
    private final int maxMinus = -10000000;
    private final int preisZug = 1000000;
    private final int geldZugZurueck = 50000;
    private final int preisBhf = 100000;
    private final int preisLinie = 10000;
    private final int reparatur = 10000;
    private final double hausWrschl = 0.65; // in % für die Wahrscheinlichkeit, dass ein Hausentsteht: 0% bis 50%
    private final double firmaWrschl = 0.9; // in % für die Wahrscheinlichkeit, dass eine Firma entsteht: hausWrschl bis 80% | Rest von 80% bis 100% ist Parkwahrscheinlichkeit
    // ========== Ende Spielvariablen ==========

    public Spielsteuerung(int h, int b, JPanel panel) {
        hauszahl = 0;
        hoehe = h;
        breite = b;
        depot = 0;
        werkstatt = 0;
        anzLinien = 0;
        feldVoll = false;
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
        altstadt();
        timer.scheduleAtFixedRate(guiTimer, 0, 40);
        timer.scheduleAtFixedRate(strgTimer, 0, 8);
        testTimer = new TestTimer(this);
        timer.scheduleAtFixedRate(testTimer, 0, 500);
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
     *
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
     *
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
     *
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
     *
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
        if (geld - preisLinie >= maxMinus) {
            if (linien.length < anzLinien + 1) {
                Linie[] hilf = new Linie[anzLinien + 10];
                for (int i = 0; i > anzLinien; i++) {
                    hilf[i] = linien[i];
                }
                linien = hilf;
            }
            linien[anzLinien + 1] = new Linie(name);
            anzLinien++;
            geld = geld - preisLinie;
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * Löscht die gegebene Linie aus der Liste linien[]
     *
     * @param l die zu löschende Linie
     * @return
     */
    public boolean linieEntfernen(Linie l) {
        if (anzLinien > 0) {
            int x = 0;
            for (int i = 0; i > anzLinien; i++) {
                if (linien[i].equals(l)) {
                    x = i;
                }
            }
            for (int i = x; i < anzLinien - 1; i++) {
                linien[i] = linien[i + 1];
            }
            linien[anzLinien - 1] = null;
            anzLinien--;
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * Ein neuer Bahnhof wird in bahnhoefe[x][y] an den gegebenen Koordinaten x
     * & y eingesetzt
     *
     * @param x
     * @param y
     * @return
     */
    private boolean neuerBahnhof(int x, int y) {
        if (geld - preisBhf >= maxMinus && bahnhoefe[x][y] == null) {
            bahnhoefe[x][y] = new Bahnhof(x, y);
            geld = geld - preisBhf;
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * der gegebene Bahnhof wird aus bahnhoefe[][] gelöscht
     *
     * @param bhf
     * @return true
     */
    public boolean bhfEntfernen(Bahnhof bhf) {
        for (int h = 0; h < bahnhoefe.length; h++) {
            for (int b = 0; b < bahnhoefe[h].length; b++) {
                if (bahnhoefe[h][b].equals(bhf)) {
                    bahnhoefe[h][b] = null;
                }
            }
        }
        return true;
    }

    /**
     *
     * Ein Stadtteil wird zufällig generiert
     *
     * @return true, wenn ein Haus gebaut werden konnte
     */
    public boolean stadtteilBauen() {
        double z = Math.random();
        if (z < hausWrschl) {
            return hausBauen();
        } else if (z < firmaWrschl) {
            return firmaBauen();
        } else {
            return parkBauen();
        }
    }

    /**
     * Ein Haus wird an einer zufälligen Position auf der Karte gebaut
     *
     * @return true, wenn das Haus gebaut werden konnte
     */
    public boolean hausBauen() {
        boolean gefunden = false;
        double wv = 0.0;   //Wahrscheinlichkeit des  besten Vorgängers
        int x = 0;
        int y = 0;
        if (!feldVoll) {
            long start = System.nanoTime();
            for (int h = 0; h < teile.length; h++) {
                for (int b = 0; b < teile[h].length; b++) {
                    if (teile[h][b] == null) {
                        // \/ Standartzufälligkeit
                        double w = Math.random();

                        // \/ [h - 1][b]
                        if (h > 0) {
                            if (teile[h - 1][b] != null) {
                                w = w + Math.random();
                            }
                            if (teile[h - 1][b] instanceof Haus) {
                                w = w + Math.random();
                            }
                            if (teile[h - 1][b] instanceof Park) {
                                w = w + Math.random() / 2;
                            }
                        }

                        // \/ [h + 1][b]
                        if (h < teile.length - 1) {
                            if (teile[h + 1][b] != null) {
                                w = w + Math.random();
                            }
                            if (teile[h + 1][b] instanceof Haus) {
                                w = w + Math.random();
                            }
                            if (teile[h + 1][b] instanceof Park) {
                                w = w + Math.random() / 2;
                            }
                        }

                        // \/ [h][b - 1]
                        if (b > 0) {
                            if (teile[h][b - 1] != null) {
                                w = w + Math.random();
                            }
                            if (teile[h][b - 1] instanceof Haus) {
                                w = w + Math.random();
                            }
                            if (teile[h][b - 1] instanceof Park) {
                                w = w + Math.random() / 2;
                            }
                        }

                        // \/ [h][b + 1]
                        if (b < teile[h].length - 1) {
                            if (teile[h][b + 1] != null) {
                                w = w + Math.random();
                            }
                            if (teile[h][b + 1] instanceof Haus) {
                                w = w + Math.random();
                            }
                            if (teile[h][b + 1] instanceof Park) {
                                w = w + Math.random() / 2;
                            }
                        }

                        // \/ [h - 1][b - 1]
                        if (h > 0 && b > 0) {
                            if (teile[h - 1][b - 1] != null) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h - 1][b - 1] instanceof Haus) {
                                w = w + Math.random();
                            }
                        }

                        // \/ [h - 1][b + 1]
                        if (h > 0 && b < teile[h].length - 1) {
                            if (teile[h - 1][b + 1] != null) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h - 1][b + 1] instanceof Haus) {
                                w = w + Math.random();
                            }
                        }

                        // \/ [h + 1][b + 1]
                        if (h < teile.length - 1 && b < teile[h].length - 1) {
                            if (teile[h + 1][b + 1] != null) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h + 1][b + 1] instanceof Haus) {
                                w = w + Math.random();
                            }
                        }

                        // \/ [h + 1][b - 1]
                        if (h < teile.length - 1 && b > 0) {
                            if (teile[h + 1][b - 1] != null) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h + 1][b - 1] instanceof Haus) {
                                w = w + Math.random();
                            }
                        }

                        // \/ eine gute Position gefunden
                        if (w > wv) {
                            y = h;
                            x = b;
                            gefunden = true;
                            wv = w;
                        }
                    }
                }

            }
            long end = System.nanoTime();
            long milliseconds = (end - start) / 1000000;
            System.out.println();
            System.out.println("Nr.:" + hauszahl);
            hauszahl++;
            System.out.println("Generationszeit: " + milliseconds);
            if (gefunden) {
                teile[y][x] = new Haus();
                System.out.println("+++ Haus gebaut! +++");
                return true;
            } else {
                feldVoll = true;
                System.out.println("! Feld voll !");
                return false;
            }
        }
        return false;
    }

    /**
     * Eine Firma wird an einer zufälligen Position auf der Karte gebaut
     *
     * @return true, wenn die Firma gebaut werden konnte
     */
    public boolean firmaBauen() {
        boolean gefunden = false;
        double wv = 0.0;   //Wahrscheinlichkeit des  besten Vorgängers
        int x = 0;
        int y = 0;
        if (!feldVoll) {
            long start = System.nanoTime();
            for (int h = 0; h < teile.length; h++) {
                for (int b = 0; b < teile[h].length; b++) {
                    if (teile[h][b] == null) {
                        // \/ Standartzufälligkeit
                        double w = Math.random();

                        // \/ [h - 1][b]
                        if (h > 0) {
                            if (teile[h - 1][b] != null) {
                                w = w + Math.random();
                            }
                            if (teile[h - 1][b] instanceof Firma) {
                                w = w + Math.random();
                            }
                            if (teile[h - 1][b] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                        }

                        // \/ [h + 1][b]
                        if (h < teile.length - 1) {
                            if (teile[h + 1][b] != null) {
                                w = w + Math.random();
                            }
                            if (teile[h + 1][b] instanceof Firma) {
                                w = w + Math.random();
                            }
                            if (teile[h + 1][b] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                        }

                        // \/ [h][b - 1]
                        if (b > 0) {
                            if (teile[h][b - 1] != null) {
                                w = w + Math.random();
                            }
                            if (teile[h][b - 1] instanceof Firma) {
                                w = w + Math.random();
                            }
                            if (teile[h][b - 1] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                        }

                        // \/ [h][b + 1]
                        if (b < teile[h].length - 1) {
                            if (teile[h][b + 1] != null) {
                                w = w + Math.random();
                            }
                            if (teile[h][b + 1] instanceof Firma) {
                                w = w + Math.random();
                            }
                            if (teile[h][b + 1] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                        }

                        // \/ [h - 1][b - 1]
                        if (h > 0 && b > 0) {
                            if (teile[h - 1][b - 1] != null) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h - 1][b - 1] instanceof Firma) {
                                w = w + Math.random();
                            }
                        }

                        // \/ [h - 1][b + 1]
                        if (h > 0 && b < teile[h].length - 1) {
                            if (teile[h - 1][b + 1] != null) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h - 1][b + 1] instanceof Firma) {
                                w = w + Math.random();
                            }
                        }

                        // \/ [h + 1][b + 1]
                        if (h < teile.length - 1 && b < teile[h].length - 1) {
                            if (teile[h + 1][b + 1] != null) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h + 1][b + 1] instanceof Firma) {
                                w = w + Math.random();
                            }
                        }

                        // \/ [h + 1][b - 1]
                        if (h < teile.length - 1 && b > 0) {
                            if (teile[h + 1][b - 1] != null) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h + 1][b - 1] instanceof Firma) {
                                w = w + Math.random();
                            }
                        }

                        // \/ eine gute Position gefunden
                        if (w > wv) {
                            y = h;
                            x = b;
                            gefunden = true;
                            wv = w;
                        }
                    }
                }

            }
            long end = System.nanoTime();
            long milliseconds = (end - start) / 1000000;
            System.out.println();
            System.out.println("Nr.:" + hauszahl);
            hauszahl++;
            System.out.println("Generationszeit: " + milliseconds);
            if (gefunden) {
                teile[y][x] = new Haus();
                System.out.println("+++ Firma gebaut! +++");
                return true;
            } else {
                feldVoll = true;
                System.out.println("Feld voll!");
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Ein Park wird an einer zufälligen Position auf der Karte gebaut
     *
     * @return true, wenn der Park gebaut werden konnte
     */
    private boolean parkBauen() {
        boolean gefunden = false;
        double wv = 0.0;   //Wahrscheinlichkeit des  besten Vorgängers
        int x = 0;
        int y = 0;
        if (!feldVoll) {
            long start = System.nanoTime();
            for (int h = 0; h < teile.length; h++) {
                for (int b = 0; b < teile[h].length; b++) {
                    if (teile[h][b] == null) {
                        // \/ Standartzufälligkeit
                        double w = Math.random();

                        // \/ [h - 1][b]
                        if (h > 0) {
                            if (teile[h - 1][b] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h - 1][b] instanceof Park) {
                                w = w + Math.random();
                            }
                        }

                        // \/ [h + 1][b]
                        if (h < teile.length - 1) {
                            if (teile[h + 1][b] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h + 1][b] instanceof Park) {
                                w = w + Math.random();
                            }
                        }

                        // \/ [h][b - 1]
                        if (b > 0) {
                            if (teile[h][b - 1] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h][b - 1] instanceof Park) {
                                w = w + Math.random();
                            }
                        }

                        // \/ [h][b + 1]
                        if (b < teile[h].length - 1) {
                            if (teile[h][b + 1] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h][b + 1] instanceof Park) {
                                w = w + Math.random();
                            }
                        }

                        // \/ [h - 1][b - 1]
                        if (h > 0 && b > 0) {
                            if (teile[h - 1][b - 1] instanceof Haus) {
                                w = w + Math.random();
                            }
                        }

                        // \/ [h - 1][b + 1]
                        if (h > 0 && b < teile[h].length - 1) {
                            if (teile[h - 1][b + 1] instanceof Haus) {
                                w = w + Math.random();
                            }
                        }

                        // \/ [h + 1][b + 1]
                        if (h < teile.length - 1 && b < teile[h].length - 1) {
                            if (teile[h + 1][b + 1] instanceof Haus) {
                                w = w + Math.random();
                            }
                        }

                        // \/ [h + 1][b - 1]
                        if (h < teile.length - 1 && b > 0) {
                            if (teile[h + 1][b - 1] instanceof Haus) {
                                w = w + Math.random();
                            }
                        }

                        // \/ eine gute Position gefunden
                        if (w > wv) {
                            y = h;
                            x = b;
                            gefunden = true;
                            wv = w;
                        }
                    }
                }

            }
            long end = System.nanoTime();
            long milliseconds = (end - start) / 1000000;
            System.out.println();
            System.out.println("Nr.:" + hauszahl);
            hauszahl++;
            System.out.println("Generationszeit: " + milliseconds);
            if (gefunden) {
                teile[y][x] = new Haus();
                System.out.println("+++ Park gebaut! +++");
                return true;
            } else {
                feldVoll = true;
                System.out.println("Feld voll!");
                return false;
            }
        }
        return false;
    }

    /**
     *
     * Baut automatisch die "Altstadt" der Karte - Als Ausgangssituation bei
     * "Spiel starten" für das weitere Spiel
     * insgesamt 68 Stadtteile
     *
     * @return
     */
    public boolean altstadt() {
        int mh = Math.round(hoehe / 2);
        int mb = Math.round(breite / 2);
        teile[mh][mb] = new Rathaus();
        teile[mh - 1][mb] = new Park();
        teile[mh - 2][mb] = new Park();
        teile[mh + 4][mb - 2] = new Haus();
        teile[mh + 4][mb - 1] = new Haus();
        teile[mh + 4][mb] = new Haus();
        teile[mh + 4][mb + 1] = new Haus();
        teile[mh + 4][mb + 2] = new Haus();
        teile[mh + 3][mb - 3] = new Haus();
        teile[mh + 3][mb - 2] = new Haus();
        teile[mh + 3][mb - 1] = new Haus();
        teile[mh + 3][mb] = new Haus();
        teile[mh + 3][mb + 1] = new Haus();
        teile[mh + 3][mb + 2] = new Haus();
        teile[mh + 3][mb + 3] = new Haus();
        teile[mh + 2][mb - 4] = new Haus();
        teile[mh + 2][mb - 3] = new Haus();
        teile[mh + 2][mb - 2] = new Haus();
        teile[mh + 2][mb - 1] = new Haus();
        teile[mh + 2][mb] = new Haus();
        teile[mh + 2][mb + 1] = new Haus();
        teile[mh + 2][mb + 2] = new Haus();
        teile[mh + 2][mb + 3] = new Haus();
        teile[mh + 2][mb + 4] = new Haus();
        teile[mh + 1][mb - 4] = new Haus();
        teile[mh + 1][mb - 3] = new Haus();
        teile[mh + 1][mb - 2] = new Haus();
        teile[mh + 1][mb - 1] = new Haus();
        teile[mh + 1][mb] = new Haus();
        teile[mh + 1][mb + 1] = new Haus();
        teile[mh + 1][mb + 2] = new Haus();
        teile[mh + 1][mb + 3] = new Haus();
        teile[mh + 1][mb + 4] = new Haus();
        teile[mh][mb - 4] = new Haus();
        teile[mh][mb - 3] = new Haus();
        teile[mh][mb - 2] = new Haus();
        teile[mh][mb - 1] = new Haus();
        teile[mh][mb + 1] = new Haus();
        teile[mh][mb + 2] = new Haus();
        teile[mh][mb + 3] = new Haus();
        teile[mh][mb + 4] = new Haus();
        teile[mh - 1][mb - 4] = new Haus();
        teile[mh - 1][mb - 3] = new Haus();
        teile[mh - 1][mb - 2] = new Haus();
        teile[mh - 1][mb - 1] = new Haus();
        teile[mh - 1][mb + 1] = new Haus();
        teile[mh - 1][mb + 2] = new Haus();
        teile[mh - 1][mb + 3] = new Haus();
        teile[mh - 1][mb + 4] = new Haus();
        teile[mh - 2][mb - 4] = new Haus();
        teile[mh - 2][mb - 3] = new Haus();
        teile[mh - 2][mb - 2] = new Haus();
        teile[mh - 2][mb - 1] = new Haus();
        teile[mh - 2][mb + 1] = new Haus();
        teile[mh - 2][mb + 2] = new Haus();
        teile[mh - 2][mb + 3] = new Haus();
        teile[mh - 2][mb + 4] = new Haus();
        teile[mh - 3][mb - 3] = new Haus();
        teile[mh - 3][mb - 2] = new Haus();
        teile[mh - 3][mb - 1] = new Haus();
        teile[mh - 3][mb] = new Haus();
        teile[mh - 3][mb + 1] = new Haus();
        teile[mh - 3][mb + 2] = new Haus();
        teile[mh - 3][mb + 3] = new Haus();
        teile[mh - 4][mb - 2] = new Haus();
        teile[mh - 4][mb - 1] = new Haus();
        teile[mh - 4][mb] = new Haus();
        teile[mh - 4][mb + 1] = new Haus();
        teile[mh - 4][mb + 2] = new Haus();
        return true;
    }

    /**
     *
     * verschiebt einen Zug für Geld von werkstatt zu depot
     *
     * @return
     */
    public boolean zugReparieren() {
        if (werkstatt >= 0 && geld - reparatur >= maxMinus) {
            werkstatt--;
            depot++;
            geld = geld - reparatur;
            return true;
        } else {
            return false;
        }
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
     *
     * @return
     */
    public boolean step() {
        return true;
    }

    /**
     * was weiß ich!
     *
     * @return
     */
    public boolean klick() {
        return true;
    }

    public int kapazitaet(Linie l) {
        int k = 0;
        for (int i = 0; i < linien.length; i++) {
            if (linien[i].equals(l)) {
                k = linien[i].kapazitaet();
            }
        }
        return k;
    }

}
