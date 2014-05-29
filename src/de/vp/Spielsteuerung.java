package de.vp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import javax.swing.JPanel;

/**
 *
 * @author Felix & Nicolai
 */
public class Spielsteuerung {

    private int depot, werkstatt, neueLinien, hoehe, breite, hauszahl, zeit, strgPause, bhfs;
    private long geld;
    private boolean[][] hatBahnhof;
    private Stadtteil[][] teile;
    private Bahnhof[][] bahnhoefe;
    private Linie[] linien;
    private Timer timer, timerS;
    private GUITimer guiTimer;
    private StrgTimer strgTimer;
    private boolean feldVoll; //Für Stadtteile bauen
    private String nextAction;
    private boolean verloren;
    private int zoom;
    private SpielPanel spielPanel;
    ArrayList<String> bhfNamen;

    // ========== Anfang Spielvariablen ==========
    private final int maxMinus = -50000000;
    private final int preisZug = 1000000;
    private final int geldZugZurueck = 40000;
    private final int preisBhf = 500000;
    private final int bhfUnterhalt = 500;
    private final int preisLinie = 10000;
    //private final int preisStrecke = 100000;
    private final int reparatur = 10000;
    private final double stadtbaugeschw = 0.0; // je weniger umso mehr!
    private final int beschwerde = 50; //Kosten wenn ein Stadtteil nicht angebunden ist
    private final int betriebskosten = 1000;
    private final double hausWrschl = 0.85; // in % für die Wahrscheinlichkeit, dass ein Hausentsteht: 0% bis 50%
    private final double firmaWrschl = 0.95; // in % für die Wahrscheinlichkeit, dass eine Firma entsteht: hausWrschl bis 80% | Rest von 80% bis 100% ist Parkwahrscheinlichkeit
    // ========== Ende Spielvariablen ==========

    public Spielsteuerung(int h, int b) {
        hauszahl = 0;
        hoehe = h;
        breite = b;
        depot = 0;
        werkstatt = 0;
        neueLinien = 0;
        feldVoll = false;
        nextAction = "";
        verloren = false;
        zoom = 0;
        bhfs = 0;
        geld = 1000000; // 10 Mio
        timer = new Timer();
        timerS = new Timer();
        strgPause = 200; // Timer-Rate
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

        String[] bhfNamenTmp = {"Marienplatz", "Blumenstraße", "Graf Maxi von Krause Allee",
            "Nicolaiplatz", "Großer Imperator Felix Maurer Platz", "Christine Kaps Allee",
            "Felix der Hecker Platz", "Hofstraße", "Sonnenstraße", "Kirchplatz",
            "Javagasse", "Berglerweg", "Stiftstraße", "Unterberg", "Hauptstraße",
            "Feldweg", "Serviettenmarkt", "Kalterbach", "Bürgermeister Horst Bichler Straße",
            "Laaange Straße", "Weit-Weit-Weg", "Waschstraße", "Schnitzelstraße",
            "Platz des Bieres", "Alte Heide", "Baumhausen", "Geldweg", "Berg", "Hausen",
            "Schneiderei", "Alte Weberei", "Brauereigasse", "Färbergraben", "H-Brücke",
            "Sickergraben", "Turmstraße", "Schneckenbahn", "Rosengarten", "Humboldt-Platz",
            "Wurzelplatz", "Adlerstraße", "Flamingostraße", "Taubenweg", "Spechtweg",
            "Sperberstraße", "Schlosstraße", "Friedensstraße", "Sackgasse", "Platz der Partei",
            "Keksweg", "Börsenplatz", "Gleisweg", "Dateipfad", "Milchstraße", "Qwertzweg",
            "Holzweg", "Heringsberger Straße", "Ausfallstraße", "Bahnhofstraße", "Finkenweg",
            "Steinstraße", "Pfauenstraße", "Bergstraße", "Bürgersteig", "Schorlenplatz",
            "Saftladen", "Gullygasse", "Kassettenweg", "Egelstraße", "Wurmstraße", "Wasserweg",
            "{return null;}-Platz", "Rinderstraße", "Maulwurfstraße", "Eckpunkt", 
            "Kleiberstraße", "Paragraphenweg", "Kabelbrücke"};
        
        bhfNamen = new ArrayList<String>();
        for (int i = 0; i < bhfNamenTmp.length; i++) {
            bhfNamen.add(bhfNamenTmp[i]);
        }

        altstadt();

        timerS.scheduleAtFixedRate(strgTimer,
                0, strgPause);
    }

    /**
     * Startet die Animation des Panels
     *
     * @param panel SpielPanel, in dem die Animation läuft
     */
    public void panelStarten(JPanel panel) {
        spielPanel = (SpielPanel) panel;
        guiTimer = new GUITimer(panel);
        timer.scheduleAtFixedRate(guiTimer, 0, 20);
    }

    /**
     * Erhöht die Spielgeschwindigkeit
     */
    public void schneller() {
//        strgPause = strgPause / 2;
//        timerS.cancel();
//        timerS.purge();
//        timerS = new Timer();
//        timerS.scheduleAtFixedRate(strgTimer, 0, strgPause);
    }

    /**
     * Verringert die Spielgeschwindigkeit
     */
    public void langsamer() {
//        strgPause = strgPause / 2;
//        timerS.cancel();
//        timerS = new Timer();
//        timerS.scheduleAtFixedRate(strgTimer, 0, strgPause);
    }

    /**
     * Zoomt rein (Zoomstufe kleiner)
     */
    public void zoomIn() {
        if (zoom > 0) {
            zoom--;
            spielPanel.setZoom(zoom);
        }
    }

    /**
     * Zoomt raus (Zoomstufe größer)
     */
    public void zoomOut() {
        if (zoom < 5) {
            zoom++;
            spielPanel.setZoom(zoom);
        }
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
            if (linien.length - 1 < neueLinien + 1) {
                Linie[] hilf = new Linie[neueLinien + 10];
                for (int i = 0; i > neueLinien; i++) {
                    hilf[i] = linien[i];
                }
                linien = hilf;
            }
            linien[neueLinien] = new Linie(name, this);
            neueLinien++;
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
        if (neueLinien > 0) {
            int x = 0;
            for (int i = 0; i < neueLinien; i++) {
                if (linien[i].equals(l)) {
                    x = i;
                }
            }
            for (int i = x; i < neueLinien - 1; i++) {
                linien[i] = linien[i + 1];
            }
            linien[neueLinien - 1] = null;
            neueLinien--;
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
        if (geld - preisBhf >= maxMinus && bahnhoefe[y][x] == null && x > 0 && y > 0 && x < teile[0].length && y < teile.length && bhfNamen.size() > 0) {
            bahnhoefe[y][x] = new Bahnhof(x, y, bhfNamen.remove((int)Math.round(Math.random() * (bhfNamen.size() - 1))));
            geld = geld - preisBhf;

            // Häuser zum Bahnhof
            int minX = x - 4;
            int minY = y - 4;
            int maxX = x + 3;
            int maxY = y + 3;
            for (int h_y = minY; h_y <= maxY; h_y++) {
                for (int h_x = minX; h_x <= maxX; h_x++) {
                    if (!(h_y < 0) && !(h_x < 0) && !(h_y > teile.length - 1) && !(h_x > teile[h_y].length - 1)) {
                        if (!(h_x == minX && h_y == minY) && !(h_x == maxX && h_y == minY) && !(h_x == minX && h_y == maxY) && !(h_x == maxX && h_y == maxY)) {
                            if (!hatBahnhof[h_y][h_x] && teile[h_y][h_x] != null) {
                                hatBahnhof[h_y][h_x] = true;
                                bahnhoefe[y][x].stadtteilHinzufuegen(teile[h_y][h_x]);
                            }
                        }
                    }
                }
            }

            bhfs++;
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
                        double w = 150 * Math.random();

                        // \/ Is denn auch ein Bahnhöfchen in der Nähe?
                        if (h < teile.length - 1 && b < teile[h].length - 1) {
                            if (bahnhoefe[h][b] != null) {
                                w = w + Math.random() / 1.7;
                            }
                            if (bahnhoefe[h + 1][b] != null) {
                                w = w + Math.random() / 1.7;
                            }
                            if (bahnhoefe[h][b + 1] != null) {
                                w = w + Math.random() / 1.7;
                            }
                            if (bahnhoefe[h + 1][b + 1] != null) {
                                w = w + Math.random() / 1.7;
                            }
                        }

                        // \/ [h - 1][b]
                        if (h > 0) {
                            if (teile[h - 1][b] != null) {
                                w = w + Math.random() * 3;
                            }
                            if (teile[h - 1][b] instanceof Haus) {
                                w = w + Math.random() * 1.2;
                            }
                            if (teile[h - 1][b] instanceof Park) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h - 1][b] instanceof Firma) {
                                w = w + Math.random() * 1.5;
                            }

                        }

                        // \/ [h + 1][b]
                        if (h < teile.length - 1) {
                            if (teile[h + 1][b] != null) {
                                w = w + Math.random() * 3;
                            }
                            if (teile[h + 1][b] instanceof Haus) {
                                w = w + Math.random() * 1.2;
                            }
                            if (teile[h + 1][b] instanceof Park) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h + 1][b] instanceof Firma) {
                                w = w + Math.random() * 1.5;
                            }
                            if (h < teile.length - 2 && b > 1 && b < teile[h].length - 1 && h > 1) {
                                if (teile[h + 1][b] instanceof Haus && teile[h + 1][b + 1] == null && teile[h - 1][b] == null && teile[h - 1][b + 1] == null && teile[h - 1][b - 1] == null && teile[h + 1][b - 1] == null && teile[h + 2][b] == null) {
                                    w = w + Math.random() * 5;
                                }
                            }

                        }

                        // \/ [h][b - 1]
                        if (b > 0) {
                            if (teile[h][b - 1] != null) {
                                w = w + Math.random() * 3;
                            }
                            if (teile[h][b - 1] instanceof Haus) {
                                w = w + Math.random() * 1.2;
                            }
                            if (teile[h][b - 1] instanceof Park) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h][b - 1] instanceof Firma) {
                                w = w + Math.random() * 1.5;
                            }

                        }

                        // \/ [h][b + 1]
                        if (b < teile[h].length - 1) {
                            if (teile[h][b + 1] != null) {
                                w = w + Math.random() * 3;
                            }
                            if (teile[h][b + 1] instanceof Haus) {
                                w = w + Math.random() * 1.2;
                            }
                            if (teile[h][b + 1] instanceof Park) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h][b + 1] instanceof Firma) {
                                w = w + Math.random() * 1.5;
                            }

                        }

                        // \/ [h - 1][b - 1]
                        if (h > 0 && b > 0) {
                            if (teile[h - 1][b - 1] != null) {
                                w = w + Math.random() * 2;
                            }
                            if (teile[h - 1][b - 1] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                        }

                        // \/ [h - 1][b + 1]
                        if (h > 0 && b < teile[h].length - 1) {
                            if (teile[h - 1][b + 1] != null) {
                                w = w + Math.random() * 2;
                            }
                            if (teile[h - 1][b + 1] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                        }

                        // \/ [h + 1][b + 1]
                        if (h < teile.length - 1 && b < teile[h].length - 1) {
                            if (teile[h + 1][b + 1] != null) {
                                w = w + Math.random() * 2;
                            }
                            if (teile[h + 1][b + 1] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                        }

                        // \/ [h + 1][b - 1]
                        if (h < teile.length - 1 && b > 0) {
                            if (teile[h + 1][b - 1] != null) {
                                w = w + Math.random() * 2;
                            }
                            if (teile[h + 1][b - 1] instanceof Haus) {
                                w = w + Math.random() / 2;
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
//            System.out.println();
//            System.out.println("Nr.:" + hauszahl);
//            hauszahl++;
//            System.out.println("Generationszeit: " + milliseconds);
            if (gefunden) {
                teile[y][x] = new Haus();

                // Bahnhof suchen
                int minX = x - 3;
                int minY = y - 3;
                int maxX = x + 4;
                int maxY = y + 4;
                boolean bhfGefunden = false;
                for (int h_y = minY; h_y <= maxY && !bhfGefunden; h_y++) {
                    for (int h_x = minX; h_x <= maxX && !bhfGefunden; h_x++) {
                        if (!(h_y < 0) && !(h_x < 0) && !(h_y > bahnhoefe.length - 1) && !(h_x > bahnhoefe[h_y].length - 1)) {
                            if (!(h_x == minX && h_y == minY) && !(h_x == maxX && h_y == minY) && !(h_x == minX && h_y == maxY) && !(h_x == maxX && h_y == maxY)) {
                                if (bahnhoefe[h_y][h_x] != null) {
                                    bhfGefunden = true;
                                    hatBahnhof[y][x] = true;
                                    bahnhoefe[h_y][h_x].stadtteilHinzufuegen(teile[y][x]);
                                }
                            }
                        }
                    }
                }

//                System.out.println("+++ Haus gebaut! +++");
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
                        double w = Math.random() * 5;

                        // \/ [h - 1][b]
                        if (h > 0) {
                            if (teile[h - 1][b] != null) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h - 1][b] instanceof Firma) {
                                w = w + Math.random() * 3;
                            }
                            if (teile[h - 1][b] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                        }

                        // \/ [h + 1][b]
                        if (h < teile.length - 1) {
                            if (teile[h + 1][b] != null) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h + 1][b] instanceof Firma) {
                                w = w + Math.random() * 3;
                            }
                            if (teile[h + 1][b] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                        }

                        // \/ [h][b - 1]
                        if (b > 0) {
                            if (teile[h][b - 1] != null) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h][b - 1] instanceof Firma) {
                                w = w + Math.random() * 3;
                            }
                            if (teile[h][b - 1] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                        }

                        // \/ [h][b + 1]
                        if (b < teile[h].length - 1) {
                            if (teile[h][b + 1] != null) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h][b + 1] instanceof Firma) {
                                w = w + Math.random() * 3;
                            }
                            if (teile[h][b + 1] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                        }

                        // \/ [h - 1][b - 1]
                        if (h > 0 && b > 0) {
                            if (teile[h - 1][b - 1] != null) {
                                w = w + Math.random() / 4;
                            }
                            if (teile[h - 1][b - 1] instanceof Firma) {
                                w = w + Math.random() / 3;
                            }
                        }

                        // \/ [h - 1][b + 1]
                        if (h > 0 && b < teile[h].length - 1) {
                            if (teile[h - 1][b + 1] != null) {
                                w = w + Math.random() / 4;
                            }
                            if (teile[h - 1][b + 1] instanceof Firma) {
                                w = w + Math.random() / 3;
                            }
                        }

                        // \/ [h + 1][b + 1]
                        if (h < teile.length - 1 && b < teile[h].length - 1) {
                            if (teile[h + 1][b + 1] != null) {
                                w = w + Math.random() / 4;
                            }
                            if (teile[h + 1][b + 1] instanceof Firma) {
                                w = w + Math.random() / 3;
                            }
                        }

                        // \/ [h + 1][b - 1]
                        if (h < teile.length - 1 && b > 0) {
                            if (teile[h + 1][b - 1] != null) {
                                w = w + Math.random() / 4;
                            }
                            if (teile[h + 1][b - 1] instanceof Firma) {
                                w = w + Math.random() / 3;
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
//            System.out.println();
//            System.out.println("Nr.:" + hauszahl);
//            hauszahl++;
//            System.out.println("Generationszeit: " + milliseconds);
            if (gefunden) {
                teile[y][x] = new Firma();

                // Bahnhof suchen
                int minX = x - 3;
                int minY = y - 3;
                int maxX = x + 4;
                int maxY = y + 4;
                boolean bhfGefunden = false;
                for (int h_y = minY; h_y <= maxY && !bhfGefunden; h_y++) {
                    for (int h_x = minX; h_x <= maxX && !bhfGefunden; h_x++) {
                        if (!(h_y < 0) && !(h_x < 0) && !(h_y > bahnhoefe.length - 1) && !(h_x > bahnhoefe[h_y].length - 1)) {
                            if (!(h_x == minX && h_y == minY) && !(h_x == maxX && h_y == minY) && !(h_x == minX && h_y == maxY) && !(h_x == maxX && h_y == maxY)) {
                                if (bahnhoefe[h_y][h_x] != null) {
                                    bhfGefunden = true;
                                    hatBahnhof[y][x] = true;
                                    bahnhoefe[h_y][h_x].stadtteilHinzufuegen(teile[y][x]);
                                }
                            }
                        }
                    }
                }

//                System.out.println("+++ Firma gebaut! +++");
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
                                w = w + Math.random() * 1.6;
                            }
                        }

                        // \/ [h + 1][b]
                        if (h < teile.length - 1) {
                            if (teile[h + 1][b] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h + 1][b] instanceof Park) {
                                w = w + Math.random() * 1.6;
                            }
                        }

                        // \/ [h][b - 1]
                        if (b > 0) {
                            if (teile[h][b - 1] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h][b - 1] instanceof Park) {
                                w = w + Math.random() * 1.6;
                            }
                        }

                        // \/ [h][b + 1]
                        if (b < teile[h].length - 1) {
                            if (teile[h][b + 1] instanceof Haus) {
                                w = w + Math.random() / 2;
                            }
                            if (teile[h][b + 1] instanceof Park) {
                                w = w + Math.random() * 1.6;
                            }
                        }

                        // \/ [h - 1][b - 1]
                        if (h > 0 && b > 0) {
                            if (teile[h - 1][b - 1] instanceof Haus) {
                                w = w + Math.random();
                            }
                            if (teile[h - 1][b - 1] instanceof Park) {
                                w = w + Math.random() / 1.1;
                            }
                        }

                        // \/ [h - 1][b + 1]
                        if (h > 0 && b < teile[h].length - 1) {
                            if (teile[h - 1][b + 1] instanceof Haus) {
                                w = w + Math.random();
                            }
                            if (teile[h - 1][b + 1] instanceof Park) {
                                w = w + Math.random() / 1.1;
                            }
                        }

                        // \/ [h + 1][b + 1]
                        if (h < teile.length - 1 && b < teile[h].length - 1) {
                            if (teile[h + 1][b + 1] instanceof Haus) {
                                w = w + Math.random();
                            }
                            if (teile[h + 1][b + 1] instanceof Park) {
                                w = w + Math.random() / 1.1;
                            }
                        }

                        // \/ [h + 1][b - 1]
                        if (h < teile.length - 1 && b > 0) {
                            if (teile[h + 1][b - 1] instanceof Haus) {
                                w = w + Math.random();
                            }
                            if (teile[h + 1][b - 1] instanceof Park) {
                                w = w + Math.random() / 1.1;
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
//            System.out.println();
//            System.out.println("Nr.:" + hauszahl);
//            hauszahl++;
//            System.out.println("Generationszeit: " + milliseconds);
            if (gefunden) {
                teile[y][x] = new Park();

                // Bahnhof suchen
                int minX = x - 3;
                int minY = y - 3;
                int maxX = x + 4;
                int maxY = y + 4;
                boolean bhfGefunden = false;
                for (int h_y = minY; h_y <= maxY && !bhfGefunden; h_y++) {
                    for (int h_x = minX; h_x <= maxX && !bhfGefunden; h_x++) {
                        if (!(h_y < 0) && !(h_x < 0) && !(h_y > bahnhoefe.length - 1) && !(h_x > bahnhoefe[h_y].length - 1)) {
                            if (!(h_x == minX && h_y == minY) && !(h_x == maxX && h_y == minY) && !(h_x == minX && h_y == maxY) && !(h_x == maxX && h_y == maxY)) {
                                if (bahnhoefe[h_y][h_x] != null) {
                                    bhfGefunden = true;
                                    hatBahnhof[y][x] = true;
                                    bahnhoefe[h_y][h_x].stadtteilHinzufuegen(teile[y][x]);
                                }
                            }
                        }
                    }
                }

//                System.out.println("+++ Park gebaut! +++");
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
     * "Spiel starten" für das weitere Spiel insgesamt 68 Stadtteile
     *
     * @return
     */
    public boolean altstadt() {
        int mh = Math.round(getHoehe() / 2);
        int mb = Math.round(getBreite() / 2);
        teile[mh][mb] = new Rathaus();
        teile[mh + 1][mb] = new Park();
        teile[mh + 2][mb] = new Park();
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
        teile[mh][mb + 2] = new Haus();
        teile[mh + 2][mb + 1] = new Haus();
        teile[mh + 2][mb + 2] = new Haus();
        teile[mh + 2][mb + 3] = new Haus();
        teile[mh + 2][mb + 4] = new Haus();
        teile[mh + 1][mb - 4] = new Haus();
        teile[mh + 1][mb - 3] = new Haus();
        teile[mh + 1][mb - 2] = new Haus();
        teile[mh + 1][mb - 1] = new Haus();
        teile[mh][mb + 1] = new Haus();
        teile[mh + 1][mb + 1] = new Haus();
        teile[mh + 1][mb + 2] = new Haus();
        teile[mh + 1][mb + 3] = new Haus();
        teile[mh + 1][mb + 4] = new Haus();
        teile[mh][mb - 4] = new Haus();
        teile[mh][mb - 3] = new Haus();
        teile[mh][mb - 2] = new Haus();
        teile[mh][mb - 1] = new Haus();
        teile[mh - 1][mb] = new Haus();
        teile[mh - 2][mb] = new Haus();
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
//        System.out.println();
//        System.out.println("!! Altstadt erfolgreich gebaut !!");
//        System.out.println();
        return true;
    }

    /**
     *
     * verschiebt einen Zug für Geld von werkstatt zu depot
     *
     * @return
     */
    public boolean zugReparieren() {
        if (getWerkstatt() > 0 && geld - reparatur >= maxMinus) {
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
    public long gesamtKosten() {
        long kosten = 0;

        // \/ alle unangebundenen Stadtteile
        for (int h = 0; h < hatBahnhof.length; h++) {
            for (int b = 0; b < hatBahnhof[h].length; b++) {
                if (teile[h][b] != null && hatBahnhof[h][b] == false) {
                    kosten = kosten + beschwerde;
                }
            }
        }

        // \/ alle Linien
        for (int i = 0; i < neueLinien - 1; i++) {
            kosten = kosten + linien[i].kosten();
        }

        // \/ das was immer anfällt
        kosten = kosten + betriebskosten;

        // \/ alle Bahnhöfe
        for (int h = 0; h < bahnhoefe.length; h++) {
            for (int b = 0; b < bahnhoefe[h].length; b++) {
                if (bahnhoefe[h][b] != null) {
                    kosten = kosten + bhfUnterhalt;
                }
            }
        }
        return kosten;

    }

    /**
     * berechnet den Gesamten Gewinn
     */
    public long gesamtGewinn() {
        long gewinn = 0 - gesamtKosten();
        for (int i = 0; i < neueLinien - 1; i++) {
            gewinn = gewinn + linien[i].gewinn();
        }
        return gewinn; //genau!
    }

    /**
     *
     * @param betrag betrag wird von Geld abgezogen
     */
    public void geldNehmen(int betrag) {
        geld = geld - betrag;
    }

    /**
     *
     * kümmert sich um alles was bei Zeit ausgeführt werden soll
     *
     * @return
     */
    public boolean step() {
        if (Math.random() > stadtbaugeschw) {
            stadtteilBauen();
        }
        if (zeit == 2) {
            if (geld - gesamtGewinn() > maxMinus) {
                geld = geld + gesamtGewinn();
                zeit = 0;
            } else {
                verloren = true;
            }
        } else {
            zeit++;
        }
        return true;
    }

    /**
     * was weiß ich!
     *
     * @param x
     * @param y
     * @return
     */
    public boolean klick(int x, int y) {
        switch (nextAction) {
            case "bhf":
                neuerBahnhof(x, y);
                nextAction = "";
                break;
            default:
                break;
        }
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

    public boolean setNextAction(String a) {
        nextAction = a;
        return true;
    }

    /**
     * @return the teile
     */
    public Stadtteil[][] getTeile() {
        return teile;
    }

    /**
     * @return the bahnhoefe
     */
    public Bahnhof[][] getBahnhoefe() {
        return bahnhoefe;
    }

    public boolean[][] getHatBahnhof() {
        return hatBahnhof;
    }

    /**
     * @return the geld
     */
    public long getGeld() {
        return geld;
    }

    /**
     * @return the depot
     */
    public int getDepot() {
        return depot;
    }

    /**
     * @return the werkstatt
     */
    public int getWerkstatt() {
        return werkstatt;
    }

    /**
     * @return the verloren
     */
    public boolean isVerloren() {
        return verloren;
    }

    /**
     * @return the hoehe
     */
    public int getHoehe() {
        return hoehe;
    }

    /**
     * @return the breite
     */
    public int getBreite() {
        return breite;
    }

    public void geldCheat() {
        if (geld + 1000000 < Long.MAX_VALUE) {
            geld = geld + 1000000;
        }
    }

    public Linie[] getLinien() {
        Linie[] retLinie = new Linie[neueLinien];
        for (int i = 0; i < retLinie.length; i++) {
            retLinie[i] = linien[i];
        }
        return retLinie;
    }

    public Bahnhof[] getBahnhofListe() {
        Bahnhof[] liste = new Bahnhof[bhfs];
        int zahl = 0;
        for (int h = 0; h < bahnhoefe.length; h++) {
            for (int b = 0; b < bahnhoefe[h].length; b++) {
                if (bahnhoefe[h][b] != null) {
                    liste[zahl] = bahnhoefe[h][b];
                    zahl++;
                }
            }
        }
        return liste;
    }
    
    public Bahnhof getBahnhof(String bahnhof) {
        Bahnhof[] liste = new Bahnhof[bhfs];
        Bahnhof bhf = null;
        for(int i=0; i < getBahnhofListe().length; i++) {
            if(liste[i].getName().equals(bahnhof)) {
                bhf = liste[i];
            }
        }
        return bhf;
    }

}
