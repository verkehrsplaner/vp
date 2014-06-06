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
    private Bahnhof[] bhfListe;
    private int bhfs;
    private int personen; // Personen die gerade auf der Linie unterwegs sind.
    private int auslastung;
    private int potential;
    private int gesamtLaenge, zeitFahrt, zeitStep;
    private int[] zug; //Liste von Zügen und derren Auslastung
    private Spielsteuerung strg;

    // ========== Anfang Spielvariablen ==========
    private final int preisStrecke = 100000;
    private final int bhfUnterhaltungsKosten = 500;
    private final int zugUnterhaltungsKosten = 1000;
    private final int zugKapazitaet = 350;
    private final int fahrtZeit = 2; // Fahrtzeit pro Block
    // ========== Ende Spielvariablen ==========

    public Linie(String n, Spielsteuerung s) {
        strg = s;
        bhfListe = new Bahnhof[20];
        name = n;
        gesamtLaenge = 0;
        Color[] farben = {new Color(255, 140, 0)/* Orange */, new Color(47, 255, 0)/*hellgrün*/,
            new Color(4, 115, 0)/* dunkelgrün */, new Color(212, 0, 0)/*rot*/,
            new Color(61, 77, 255)/*blau*/, new Color(151, 175, 222)/*hellblau*/,
            new Color(135, 0, 106)/*lila*/, new Color(0, 234, 255)/*türkis*/,
            new Color(117, 71, 15)/*braun*/, new Color(255, 0, 170)/*rosa*/,
            new Color(61, 77, 255)/*blau*/,};
        farbe = farben[(int) Math.round(Math.random() * (farben.length - 1))];
    }

    public Bahnhof[] getBahnhof() {
        Bahnhof[] ret = new Bahnhof[bhfs];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = bhfListe[i];
        }
        return ret;
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
        this.setZeitFahrt();
    }

    /**
     * einen Zug aus der Linie löschen
     */
    public boolean zugEntfernen() {
        if (zuege > 0) {
            zuege--;
            this.setZeitFahrt();
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
        for (int i = 0; i < bhfs; i++) {
            if (bhfListe[i].equals(bhfVor)) {
                stelle = i + 1;
            }
        }
        // Wenn am Anfang eingefügt werden soll
        if (bhfVor == null) {
            stelle = 0;
        }

        if (stelle > -1) {
            if (bhfListe.length < bhfs + 1) {
                //Bei zu kurzer Liste wird diese erweitert
                Bahnhof[] bhfHilf = new Bahnhof[bhfListe.length + 10];
                for (int i = 0; i < bhfListe.length; i++) {
                    bhfHilf[i] = bhfListe[i];
                }
                bhfListe = bhfHilf;
            }
            for (int i = bhfListe.length - 2; i >= stelle; i--) {
                bhfListe[i + 1] = bhfListe[i];
            }
            bhfListe[stelle] = bhf;
            bhfs++;
            gesamtLaenge();
            // Strecke bezahlen
            if (stelle < bhfListe.length) {
                if (bhfListe[stelle + 1] != null) {
                    strg.geldNehmen(streckeBerechnen(stelle, stelle + 1) * preisStrecke);
                }
            }
            if (stelle > 0) {
                if (bhfListe[stelle - 1] != null) {
                    strg.geldNehmen(streckeBerechnen(stelle - 1, stelle));

                }
            }
        }
        bhf.linieHinzu(this);
        this.setZeitFahrt();
    }

    /**
     *
     * @param bhf gegebener Bhf wird aus der Liste gelöscht? letzter Bhf wird
     * aus der Liste gelöscht!
     */
    public void bahnhofEntfernen(Bahnhof bhf) {
        bhf.linieWeg(this);
        int stelle = -1;
        for (int i = 0; i < bhfs; i++) {
            if (bhfListe[i].equals(bhf)) {
                stelle = i;
            }
        }
        if (stelle != -1) {
            for (int i = stelle; i < bhfListe.length - 1; i++) {
                bhfListe[i] = bhfListe[i + 1];
            }
            bhfs--;
        }
        this.setZeitFahrt();
    }

    /**
     *
     * @return gesamte Länge der Linie
     */
    public int gesamtLaenge() {
        gesamtLaenge = 0;
        for (int i = 0; i < bhfs - 1; i++) {
            gesamtLaenge += streckeBerechnen(i, i + 1);
        }
        return gesamtLaenge;
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
        int k = 0 - kosten();
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
    public int potential() {
        int k = 0;
        for (int i = 0; i < bhfs; i++) {
            if (k < kapazitaet()) {
                k = k + bhfListe[i].getBahnsteig();
            }
        }
        potential = k;
        return k;
    }

    /**
     *
     * @return auslastung
     */
    public double auslastung() {
        if (kapazitaet() == 0) {
            return 0;
        } else {
            return ((double) auslastung) / kapazitaet();
        }
    }

    public int getAuslastung() {
        return auslastung;
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

    /**
     * Berechnet die Strecke zwischen zwei gegebenen Bahnhöfen
     *
     * @param startBhf Startbahnhof
     * @param zielBhf Zielbahnhof
     * @return strecke zwischen beiden Bahnhöfen
     */
    public int streckeBerechnen(int startBhf, int zielBhf) {
        if (startBhf < bhfs && zielBhf < bhfs) {
            int strecke = 0;
            int startX = bhfListe[startBhf].getX();
            int startY = bhfListe[startBhf].getY();
            int zielX = bhfListe[zielBhf].getX();
            int zielY = bhfListe[zielBhf].getY();

            // Satz des Pythagoras (strecke = c)
            strecke = (int) Math.round(Math.sqrt(Math.pow((zielX - startX), 2) + Math.pow((zielY - startY), 2)));
            return strecke;
        } else {
            return 0;
        }
    }

    /**
     * berechnet die Gesamtfahrtzeit der Linie
     */
    public void setZeitFahrt() {
        if (zuege != 0) {
            zeitFahrt = (gesamtLaenge * fahrtZeit) / zuege;
        } else {
            zeitFahrt = -1;
        }
    }
    
    /**
     * räumt auf wenn die Linie gelöscht werden soll
     * zB allen Bahnhöfen bescheid geben
     */
    public void letzterSchritt(){
        // bei allen Bhfs die Linie löschen
        for(int i=0; i < bhfs; i++){
            bhfListe[i].linieWeg(this);
        }
    }

    /**
     *
     */
    public void step() {
        System.out.println(name + ": " + zeitFahrt);
        if (zeitFahrt <= zeitStep && bhfs > 0 && zeitFahrt > 0) {
            // Aussteigen aus Zug
            System.out.println("Ein und Aussteigen wird ausgeführt!");
            int wollenRaus = auslastung;
            int bhfsAussteigen = 0;
            for (int i = 0; i < bhfs; i++) {
                if (bhfListe[i].getBahnsteig() < 0) {
                    bhfsAussteigen++;
                }
            }
            int personenProBhfRaus = wollenRaus / bhfs;
            for (int i = 0; i < bhfs; i++) {
                if (bhfListe[i].getBahnsteig() < 0) {
                    int ausgestiegen = bhfListe[i].aussteigen(personenProBhfRaus);
                    auslastung -= ausgestiegen;
                }
            }

            // Einsteigen in Zug
            int plaetzeFrei = kapazitaet() - auslastung;
            int bhfsEinsteigen = 0;
            for (int i = 0; i < bhfs; i++) {
                if (bhfListe[i].getBahnsteig() > 0) {
                    bhfsEinsteigen++;
                }
            }
            int personenProBhfRein = plaetzeFrei / bhfs;
            for (int i = 0; i < bhfs; i++) {
                if (bhfListe[i].getBahnsteig() > 0) {
                    int eingestiegen = bhfListe[i].einsteigen(personenProBhfRein);
                    auslastung += eingestiegen;
                }
            }

            // Zeit zurücksetzen
            zeitStep = -1;
        }
        zeitStep++;

    }
    
    public void zugFahren() {
        
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
