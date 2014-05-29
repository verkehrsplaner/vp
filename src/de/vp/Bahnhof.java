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
    private int personen;
    private String name;
    private Spielsteuerung strg;
    private String[] bhfNamen = {"Marienplatz", "Blumenstraße", "Graf Maxi von Krause Allee",
        "Nicolaiplatz", "Großer Imperator Felix Maurer Platz", "Christine Kaps Allee",
        "Felix der Hecker Platz", "Hofstraße", "Sonnenstraße", "Kirchplatz",
        "Javagasse", "Berglerweg", "Stiftstraße", "Unterberg", "Hauptstraße",
        "Feldweg", "Serviettenmarkt", "Kalter Bach", "Bürgermeister Horst Bichler Straße",
        "Laaange Straße", "Weit-Weit-Weg", "Waschstraße", "Schnitzelstraße",
        "Platz des Bieres", "Alte Heide", "Baum", "Geldweg", "Berg", "Hausen",
        "Schneiderei", "Alte Weberei", "Brauereigasse", "Färbergraben", "H-Brücke",
        "Sickergraben", "Turmstraße", "Schneckenbahn", "Rosengarten", "Humboldt-Platz"};

    public Bahnhof(int x, int y) {
        X = x;
        Y = y;
        fahrtKosten = 3;
        teile = new Stadtteil[30];
        name = bhfNamen[(int) Math.round(Math.random() * (bhfNamen.length - 1))];
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
        int x = 0;
        for (int i = 0; i < teile.length; i++) {
            if (teile[i].getPersonen() > 0) {
                x = x + teile[i].getPersonen() * getFahrtKosten();
            }
        }
        return x;
    }

    /**
     *
     * @return alle personen zusammen die ein und aussteigen
     */
    public int personenBerechnen() {
        int x = 0;
        for (int i = 0; i < teile.length; i++) {
            x = x + teile[i].getPersonen();
        }
        personen = personen + x;
        return x;
    }

    public int einsteigen() {
        return 0;
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

}
