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

    private int fahrtKosten;
    private int X;
    private int Y;
    private Stadtteil[] teile;
    private int stadtteile;
    private int personen, einsteigen, aussteigen, eingestiegen, ausgestiegen;
    private int bahnsteig; // Leute die auf einen Zug warten
    private int kasse; // Eingenommenes Geld für abgeholte Personen
    private Linie[] anschlussLinien; //Wie viele Linien bedienen diesen Bahnhof
    private int anzahlLinien;
    private String name;
    private Spielsteuerung strg;

    /**
     *
     * @param x
     * @param y
     * @param n Name des Bahnhofs
     */
    public Bahnhof(int x, int y, String n) {
        X = x;
        Y = y;
        name = n;
        fahrtKosten = 5;
        teile = new Stadtteil[30];
        anschlussLinien = new Linie[10];
    }

    /**
     * @return the fahrtKosten
     */
    public int getFahrtKosten() {
        return fahrtKosten;
    }

    /**
     * @return the X
     */
    public int getX() {
        return X;
    }

    /**
     * @return the Y
     */
    public int getY() {
        return Y;
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
     *
     * @return alle personen zusammen die ein und aussteigen
     */
    public int einsteigenBerechnen() {
        int x = 0;
        for (int i = 0; i < teile.length; i++) {
            if (teile[i] != null && teile[i].getPersonen() > 0) {
                x = x + teile[i].getPersonen();
            }
        }
        einsteigen += x;
        System.out.println(einsteigen);
        return x;
    }

    public int aussteigenBerechnen() {
        int x = 0;
        for (int i = 0; i < teile.length; i++) {
            if (teile[i] != null && teile[i].getPersonen() < 0) {
                x = x + teile[i].getPersonen();
            }
        }
        aussteigen += x;
        System.out.println(aussteigen);
        return x;
    }

    public void bahnsteigFuellen() {
        if (anzahlLinien > 0) {
            einsteigenBerechnen();
            aussteigenBerechnen();
            System.out.println("Bahnhof " + name + " gefüllt!");
        }
    }

    /**
     *
     * @param frei freie Sitzplätze im Zug
     * @return eingestiege Personen
     */
    public int einsteigen(int frei) {
        int f = frei;
        eingestiegen = 0;
        int x = 0;
        if (f > 0) {
            if (anzahlLinien == 1) {
                // Wenn nur eine Linie den Bahnhof bedient
                if (frei > einsteigen) {
                    eingestiegen = einsteigen;
                    einsteigen = 0;
                } else {
                    eingestiegen = frei;
                    einsteigen -= frei;
                }
            } else {
                // Wenn mehrere Linien den Bahnhof bedienen
                x = Math.round(einsteigen * anzahlLinien);
                if (frei > x) {
                    eingestiegen = x;
                    einsteigen -= x;
                } else if (x > frei) {
                    eingestiegen = frei;
                    einsteigen -= frei;
                }
            }
        } else {
            eingestiegen = 0;
        }

        System.out.println(eingestiegen + " in " + name + " eingestiegen!");
        kasse += eingestiegen * fahrtKosten;
        return eingestiegen;
    }

    /**
     *
     * @param personen wie viele Personen in der Linie sitzen
     * @return Personen, die Aussteigen wollen
     */
    public int aussteigen(int personen) {
        ausgestiegen = 0;
        int p = personen;
        int x = 0;
        if (p > 0) {
            if (anzahlLinien == 1) {
                // Wenn nur eine Linie den Bahnhof bedient
                if (p < aussteigen) {
                    ausgestiegen = p;
                    aussteigen += (-1) * p;
                } else {
                    ausgestiegen = aussteigen;
                    aussteigen = 0;
                }
            }
            if (anzahlLinien > 1) {
                // Wenn mehrere Linien den Bahnhof bedienen
                x = personen;
                if (p > 250) {
                    //Aussteigen wegen überfüllung
                    x = x + (p - 250);
                }
                if (p < aussteigen) {
                    ausgestiegen = p;
                    aussteigen += (-1) * p;
                } else {
                    ausgestiegen = aussteigen;
                    aussteigen = 0;
                }
            }
        } else {
            ausgestiegen = 0;
        }
        System.out.println(ausgestiegen + " in " + name + " ausgestiegen!");
        return ausgestiegen;
    }

    public int allesAussteigen(int personen) {
        einsteigen += personen;
        return personen;
    }

    /**
     *
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        name = name;
    }

    /**
     * @return the bahnsteig
     */
    public int getBahnsteig() {
        return bahnsteig;
    }

    /**
     * @return the anzahlLinien
     */
    public int getAnzahlLinien() {
        return anzahlLinien;
    }

    /**
     *
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
     *
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
        }
    }

    /**
     * räumt auf wenn die Linie gelöscht werden soll
     */
    public void letzterSchritt() {
        // alle Linien verabschieden sich von dem Bhf
        for (int i = 0; i < anzahlLinien + 1; i++) {
            anschlussLinien[i].bahnhofEntfernen(this);
        }
    }

    /**
     * @return the eingestiegen
     */
    public int getEingestiegen() {
        return eingestiegen;
    }

    /**
     * @return the ausgestiegen
     */
    public int getAusgestiegen() {
        return ausgestiegen;
    }

    /**
     * @return the einsteigen
     */
    public int getEinsteigen() {
        return einsteigen;
    }

    /**
     * @return the aussteigen
     */
    public int getAussteigen() {
        return aussteigen;
    }

}
