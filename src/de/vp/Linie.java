/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

import java.awt.Color;

/**
 *
 * @author Nicolai
 */
public class Linie {

    private String name;
    private Color farbe;
    private int zuege;
    private int zugUnterhaltungsKosten;
    private Bahnhof[] bhfListe;
    private int bhfs;
    private int bhfUnterhaltungsKosten;
    private int zugKapazitaet;
    private int personen; // Personen die gerade auf der Linie unterwegs sind.
    private int auslastung;

    public Linie(String n) {
        zugUnterhaltungsKosten = 1000;
        bhfListe = new Bahnhof[20];
        bhfUnterhaltungsKosten = 1000;
        name = n;
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
        this.name = name;
    }

    /**
     * @return the farbe
     */
    public Color getFarbe() {
        return farbe;
    }

    /**
     * @param farbe the farbe to set
     */
    public void setFarbe(Color farbe) {
        this.farbe = farbe;
    }

    /**
     * einen Zug hinzufügen
     */
    public void zugEinstellen() {
        zuege++;
    }

    /**
     * einen Zug aus der Linie löschen
     */
    public boolean zugEntfernen() {
        if (zuege > 0) {
            zuege--;
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param bhf gegebener Bhf wird in bhfListe eingefügt
     * @param bhfVor hinter diesem Bahnhof wird der neue bhf eingefügt
     *
     */
    public void bahnhofHinzufuegen(Bahnhof bhf, Bahnhof bhfVor) {
        int stelle = -1;
        for (int i = 0; i < bhfListe.length; i++) {
            if (bhfVor.equals(bhfListe[i])) {
                stelle = i + 1;
            }
        }
        if (stelle != -1) {
            if (bhfListe.length < bhfs + 1) {
                //Bei zu kurzer Liste wird diese erweitert
                Bahnhof[] bhfHilf = new Bahnhof[bhfListe.length + 10];
                for (int i = 0; i < bhfListe.length; i++) {
                    bhfHilf[i] = bhfListe[i];
                }

            }
            for (int i = stelle; i < bhfListe.length; i++) {
                bhfListe[i] = bhfListe[i + 1];
            }
            bhfListe[stelle] = bhf;
            bhfs++;
        }

    }

    /**
     *
     * @param bhf gegebener Bhf wird aus der Liste gelöscht? letzter Bhf wird
     * aus der Liste gelöscht!
     */
    public void bahnhofEntfernen(Bahnhof bhf) {
        bhfListe[bhfs] = null;
        bhfs--;
    }

    /**
     *
     * @return alle Kosten die für Zug- und Bahnhofsunterhalt anfallen
     */
    public int kosten() {
        return zuege * zugUnterhaltungsKosten + bhfs * bhfUnterhaltungsKosten;
    }

    /**
     *
     * @return der Gewinn der für die Ganze Linie anfällt Berechnet durch die
     * Teilgewinne jedes Bhfs
     */
    public int gewinn() {
        int k = 0;
        for (int i = 0; i < bhfs; i++) {
            k = k + bhfListe[i].gewinn();
        }
        return k;
    }

    /**
     *
     * @return Kapazität der Linie Berechnet durch alle Personen die in allen
     * Bhfs einsteigen
     */
    public int auslastung() {
        int k = 0;
        for (int i = 0; i < bhfs; i++) {
            if (k < kapazitaet()) {
                k = k + bhfListe[i].personenBerechnen();
            }
        }
        auslastung = k;
        return k;
    }

    /**
     *
     * @return maximale Kapazität
     */
    public int kapazitaet() {
        return zuege * zugKapazitaet;
    }

    /**
     *
     * @return zuege
     */
    public int getZuege() {
        return zuege;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o.getClass().equals(getClass())) {
            Linie l = (Linie) o;
            if (name == l.name) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
