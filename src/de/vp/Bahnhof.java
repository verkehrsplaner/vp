/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

/**
 *
 * @author Nicolai & Felix
 */
public class Bahnhof {


    private int X;
    private int Y;
    private Stadtteil[] teile; //Liste der Stadtteile die von dem Bahnhof abhängig sind
    private int stadtteile;
    private int einsteigen, aussteigen, eingestiegen, ausgestiegen;
    private int bahnsteig; // Leute die auf einen Zug warten
    private int kasse; // Eingenommenes Geld für abgeholte Personen
    private Linie[] anschlussLinien; //Wie viele Linien bedienen diesen Bahnhof
    private int anzahlLinien;
    private String name;
    private Spielsteuerung strg;
    
    // ========== Anfang Spielvariablen ==========
    private final int fahrtKosten = 12;
    // ========== Ende Spielvariablen ==========
    
    /**
     * Erzeugt einen neuen Bahnhof
     * @param x X-Koordinate des Bahnhofs
     * @param y Y-Koordinate des Bahnhofs
     * @param n Name des Bahnhofs
     */
    public Bahnhof(int x, int y, String n) {
        X = x;
        Y = y;
        name = n;
        teile = new Stadtteil[50];
        anschlussLinien = new Linie[10];
    }

    /**
     * @return Kosten für eine Fahrt
     */
    public int getFahrtKosten() {
        return fahrtKosten;
    }

    /**
     * @return X-Position des Bahnhofes
     */
    public int getX() {
        return X;
    }

    /**
     * @return Y-Position des Bahnhofes
     */
    public int getY() {
        return Y;
    }
    
    /**
     * Gibt alle Linien zurück, an die der Bahnhof angechlossen ist
     * @return Alle angeschlossenen Liniem
     */
    public Linie[] getLinien() {
        Linie[] linien = new Linie[anzahlLinien];
        for (int i = 0; i < linien.length;i++) {
            linien[i] = anschlussLinien[i];
        }
        return linien;
    }

    /**
     *
     * @return gesamter Gewinn des Bahnhofs berechnet durch alle Stadtteile im
     * Radius
     */
    public int gewinn() {
        int x = kasse;
        kasse = 0;
        return x;
    }

    /**
     * berechnet alle personen zusammen die einsteigen
     * @return alle personen die einsteigen
     */
    public int einsteigenBerechnen() {
        int x = 0;
        for (int i = 0; i < teile.length; i++) {
            if (teile[i] != null && teile[i].getPersonen() > 0) {
                x = x + teile[i].getPersonen();
            }
        }
        setEinsteigen(einsteigen + x);
        //System.out.println(einsteigen);
        return x;
    }

    /**
     * berechnet alle personen zusammen die aussteigen
     * @return alle personen die aussteigen
     */
    public int aussteigenBerechnen() {
        int x = 0;
        for (int i = 0; i < teile.length; i++) {
            if (teile[i] != null && teile[i].getPersonen() < 0) {
                x = x + teile[i].getPersonen();
            }
        }
        setAussteigen(aussteigen + x);
        //System.out.println(aussteigen);
        return x;
    }

    /**
     * überprüft ob eine Linie Leute abholen würde,
     * wenn ja dann: aussteigenBerechnen() und einsteigenBerechnen()
     */
    public void bahnsteigFuellen() {
        if(anzahlLinien > 0) {
        boolean ok = false;
        for(int i = 0; i < anzahlLinien; i++) {
            if(anschlussLinien[i].getGruenesLicht()) {
                ok = true;
            }
        }
        if (ok) {
            einsteigenBerechnen();
            aussteigenBerechnen();
            //System.out.println("Bahnhof " + name + " gefüllt!");
        }
        }
    }

    /**
     * Personen steigen in Zug ein
     * @param frei freie Sitzplätze im Zug
     * @return eingestiege Personen
     */
    public int einsteigen(int frei) {
        eingestiegen = 0;
        if (frei > 0 && anzahlLinien > 0) {
            // Wenn mehrere Linien den Bahnhof bedienen
            int einsteigenProLinie = Math.round(einsteigen / anzahlLinien);
            if (frei > einsteigenProLinie) {
                eingestiegen = einsteigenProLinie;
                setEinsteigen(einsteigen - einsteigenProLinie);
            } else if (einsteigenProLinie > frei) {
                eingestiegen = frei;
                setEinsteigen(einsteigen - frei);
            }
        } else {
            eingestiegen = 0;
        }

        //System.out.println(eingestiegen + " in " + name + " eingestiegen!");
        setKasse(kasse + eingestiegen * fahrtKosten);
        return eingestiegen;
    }

    /**
     * Personen steigen aus Zug aus
     * @param personen wie viele Personen in der Linie sitzen
     * @return Personen, die Aussteigen wollen
     */
    public int aussteigen(int personen) {
        ausgestiegen = 0;
        if (personen > 0 && anzahlLinien > 0) {
            int wollenAussteigen = 0;
            //Aussteigen wegen überfüllung
            if (personen > 320) {
                wollenAussteigen += (personen - 420);
            }
            // Wenn man umsteigen kann
            if (anzahlLinien > 1) {
                wollenAussteigen = (anzahlLinien - 1) * 25;
            }
            // Weil der Bahnhof Leute haben will
            if (aussteigen < 0) {
                if (personen > -aussteigen) {
                    wollenAussteigen += personen + aussteigen;
                } else {
                    wollenAussteigen += personen;
                }
            }
            
            // wollenAussteigen nicht zu groß machen 
            if (wollenAussteigen > personen) {
                wollenAussteigen = personen;
            }

            if (wollenAussteigen < -aussteigen) {
                ausgestiegen = wollenAussteigen;
                setAussteigen(aussteigen + wollenAussteigen);
            } else {
                ausgestiegen = wollenAussteigen;
                setEinsteigen(einsteigen + wollenAussteigen + aussteigen);
                setAussteigen(0);
            }

        } else {
            ausgestiegen = 0;
        }
        //System.out.println(ausgestiegen + " in " + name + " ausgestiegen!");
        return ausgestiegen;
    }

    /**
     * alle Personen die in dem Zug sind der gerade im Bahnhof ist müssen aussteigen
     * @param personen ausgestiegene Personen
     * @return ausgestiegene Personen
     */
    public int allesAussteigen(int personen) {
        if(aussteigen + personen < 0) {
            setAussteigen(aussteigen + personen);
        } else {
            setEinsteigen(einsteigen + personen + aussteigen);
            setAussteigen(0);
        }
        return personen;
    }

    /**
     * Stadtteil in teile[]
     * @param s Stadtteil wird in die Liste des Bahnhofs hinzugefügt
     */
    public void stadtteilHinzufuegen(Stadtteil s) {
        if (stadtteile + 1 > teile.length - 1) {
            Stadtteil[] hilf = new Stadtteil[teile.length + 10];
            for (int i = 0; i < teile.length; i++) {
                hilf[i] = teile[i];
            }
            teile = hilf;
        }
        teile[stadtteile + 1] = s;
        stadtteile++;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o.getClass().equals(getClass())) {
            Bahnhof bhf = (Bahnhof) o;
            if (X == bhf.X && Y == bhf.Y) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * @return Den Namen des Bahnhofes
     */
    public String getName() {
        return name;
    }

    /**
     * @return Wartende aus dem Bahnsteig
     */
    public int getBahnsteig() {
        return bahnsteig;
    }

    /**
     * @return Anzahl der angeschlossenen Linien
     */
    public int getAnzahlLinien() {
        return anzahlLinien;
    }

    /**
     * Fügt eine neue angeschlossenen Linie hinzu
     * @param l die Linie die den Bahnhof ab jetzt erreichen soll
     */
    public void linieHinzu(Linie l) {
        if (anzahlLinien < anschlussLinien.length) {
            anschlussLinien[anzahlLinien] = l;
        } else {
            Linie[] li = new Linie[anzahlLinien + 10];
            for (int i = 0; i < anschlussLinien.length; i++) {
                li[i] = anschlussLinien[i];
            }
            anschlussLinien = li;
        }
        anzahlLinien++;
    }

    /**
     * Entfernt eine Linie aus den Anschlüssen
     * @param l die Linie die nicht mehr den Bhf ansteuert
     */
    public void linieWeg(Linie l) {
        if (anzahlLinien > 0) {
            int x = 0;
            for (int i = 0; i < anzahlLinien; i++) {
                if (l.equals(anschlussLinien[i])) {
                } else {
                    x = i;
                }
            }
            for (int q = x; q < anzahlLinien - 1; q++) {
                anschlussLinien[q] = anschlussLinien[q + 1];
            }
            anschlussLinien[anzahlLinien] = null;
            anzahlLinien--;
        } //böaaa
    }

    /**
     * räumt auf wenn die Linie gelöscht werden soll
     */
    public void letzterSchritt() {
        if (anzahlLinien > 0) {
            // alle Linien verabschieden sich von dem Bhf
            for (int i = 0; i < anzahlLinien + 1; i++) {
                anschlussLinien[i].bahnhofEntfernen(this);
            }
        }
    }

    /**
     * @return Personen, die eingestiegen sind
     */
    public int getEingestiegen() {
        return eingestiegen;
    }

    /**
     * @return Personen, die ausgestiegen sind
     */
    public int getAusgestiegen() {
        return ausgestiegen;
    }

    /**
     * @return Personen, die einsteigen wollen
     */
    public int getEinsteigen() {
        return einsteigen;
    }

    /**
     * @return Personen, die aussteigen wollen
     */
    public int getAussteigen() {
        return aussteigen;
    }

    /**
     * @return Die Kasse des Bahnhofes
     */
    public int getKasse() {
        return kasse;
    }

    /**
     * Setzt die Anzahl der Personen, die einsteigen wollen 
     * @param einsteigen Personen, die einsteigen wollen
     */
    public void setEinsteigen(int einsteigen) {
        this.einsteigen = einsteigen;
    }

    /**
     * Setzt die Anzahl der Personen, die aussteigen wollen 
     * @param aussteigen Personen, die aussteigen wollen
     */
    public void setAussteigen(int aussteigen) {
        this.aussteigen = aussteigen;
    }

    /**
     * Setzt die Kasse des Bahnhofes
     * @param kasse Die neue Kasse
     */
    public void setKasse(int kasse) {
        this.kasse = kasse;
    }

}
