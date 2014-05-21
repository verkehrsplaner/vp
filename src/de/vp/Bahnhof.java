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
    private int personen;
    private String name;
    private String[] bhfNamen = {"Marienplatz", "Blumenstraße", "Graf Maxi von Krause Allee", 
                              "Nicolaiplatz", "Großer Imperator Felix Maurer Platz", "Christine Kaps Alle",
                              "Felix der Hecker Platz", "Hofstraße", "Sonnenstraße" ,"Kirchplatz",
                              "Javagasse", "Berglerweg", "Stiftstraße", "Unterberg", "Hauptstraße",
                              "Feldweg", "Serviettenmarkt", "Kalter Bach", "Bürgermeister Horst Bichler Straße",
                              "Laaange Straße", "Weit-Weit-Weg", "Waschstraße", "Schnitzelstraße",
                              "Platz des Bieres", "Alte Heide", "Baum" , "Geldweg", "Berg", "Hausen",
                              "Schneiderei", "Alte Weberei", "Brauereigasse", "Färbergraben", "H-Brücke",
                              ""};

    public Bahnhof(int x, int y) {
        X = x;
        Y = y;
        fahrtKosten = 3;
        teile = new Stadtteil[100];
        name = "Test";
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

}
