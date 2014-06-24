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
    private int zuegeRaus; // Zuege, die noch aus der Linie entfernt werden müssen
    private Bahnhof[] bhfListe;
    private int bhfs;
    private boolean gruenesLicht; // Dürfen Züge aus dem Depot fahren?
    private boolean baubar; // Darf an der Linie gebaut werden?
    private int personen; // Personen die gerade auf der Linie unterwegs sind.
    private int auslastung;
    private int potential;
    private int gesamtLaenge, zeitStep, zeitZug;
    private int[] strecke; //jede Stelle ist ein Zug wenn "[i] != -1" und die Zahl ist gleichzeitig die Personenanzahl im Zug
    private int[] streckeZurueck;
    private int[] istBhf; //sozusagen die Abschnitte der Linie
    private int depot, gewinn, kosten;
    private Spielsteuerung strg;

    // ========== Anfang Spielvariablen ==========
    private final int preisStrecke = 1000000;
    private int bhfUnterhaltungsKosten;
    private final int zugUnterhaltungsKosten = 1500;
    private final int zugKapazitaet = 350;
    private final int fahrtZeit = 2; // Fahrtzeit pro Block
    // ========== Ende Spielvariablen ==========

    public Linie(String n, Spielsteuerung s) {
        strg = s;
        bhfListe = new Bahnhof[20];
        strecke = new int[0];
        streckeZurueck = new int[0];
        istBhf = new int[0];
        name = n;
        personen = 0;
        gesamtLaenge = 0;
        gruenesLicht = false;
        bhfUnterhaltungsKosten = strg.getBhfUnterhalt();
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
    public boolean zugEinstellen() {
        if (zuege < strecke.length) {
            zuege++;
            depot++;
            this.setZeitZug();
            return true;
        } else {
            return false;
        }
    }

    /**
     * einen Zug aus der Linie löschen
     */
    public boolean zugEntfernen() {
        if (zuege - zuegeRaus > 0) {
            zuegeRaus++;
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
        if (baubar) {
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
                        strg.geldNehmen(streckeBerechnen(stelle - 1, stelle) * preisStrecke);

                    }
                }

                // Strecken bauen
            }
            bhf.linieHinzu(this);
            this.setZeitZug();
            this.streckenBauen();
        }
    }

    private void bahnhofWiedereinfuegen(Bahnhof bhf) {
        if (baubar) {
            // Wenn am Anfang eingefügt werden soll
            if (bhfListe[0] == null) {
                bhfListe[0] = bhf;
            }

            else {
                if (bhfListe.length < bhfs + 1) {
                    //Bei zu kurzer Liste wird diese erweitert
                    Bahnhof[] bhfHilf = new Bahnhof[bhfListe.length + 10];
                    for (int i = 0; i < bhfListe.length; i++) {
                        bhfHilf[i] = bhfListe[i];
                    }
                    bhfListe = bhfHilf;
                }
                bhfListe[bhfs] = bhf;
                
                streckeBerechnen(bhfs -1 , bhfs);
                
                gesamtLaenge();
                
            }
            // Strecken bauen
            bhf.linieHinzu(this);
            this.setZeitZug();
            this.streckenBauen();
        }
    }
    /**
     *
     * @param bhf gegebener Bhf wird aus der Liste gelöscht? letzter Bhf wird
     * aus der Liste gelöscht!
     */
    public void bahnhofEntfernen(Bahnhof bhf) {
        if (baubar) {
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
            this.streckenBauen();
            this.setZeitZug();
        }
    }

    private void streckenBauen() {
        int[] tmpIstBhf = new int[gesamtLaenge() + bhfs];
        int[] tmpStrecke = new int[gesamtLaenge() + bhfs];
        int[] tmpStreckeZurueck = new int[gesamtLaenge() + bhfs];

        // Alle Strecken zurücksetzen
        for (int i = 0; i < tmpIstBhf.length; i++) {
            tmpIstBhf[i] = -1;
            tmpStrecke[i] = -1;
            tmpStreckeZurueck[i] = -1;
        }
        // Bahnhöfe einfügen
        if (bhfs > 1) {
        tmpIstBhf[0] = 0;
            // Bahnhöfe durchgehen
            for (int i = 1; i < bhfs; i++) {
                // Entfernung bis zum Bahnhof
                int entf = 0;
                for (int x = 0; x < i; x++) {
                    entf += streckeBerechnen(x, x + 1);
                }
                entf += i;
                // Bahnhof nach der Entfernung einfügen
                tmpIstBhf[entf] = i;
            }
            tmpIstBhf[tmpIstBhf.length - 1] = bhfs - 1;
        }
        strecke = tmpStrecke;
        streckeZurueck = tmpStreckeZurueck;
        istBhf = tmpIstBhf;
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
        kosten = zuege * zugUnterhaltungsKosten + bhfs * bhfUnterhaltungsKosten;
        return kosten;
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
        gewinn = k;
        return gewinn;
    }
    
    /**
     * 
     * @return den aktuellen Gewinn der Linie
     */
    public int getGewinn() {
        return gewinn;
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
        if(kapazitaet() == 0){
            return 0;
        } else {
            return ((double)personen) / kapazitaet();
        }

    }

    /**
     *
     * @return maximale Kapazität
     */
    public int kapazitaet() {
        return zuege * getZugKapazitaet();
    }

    /**
     *
     * @return zuege
     */
    public int getZuege() {
        return zuege;
    }

    public boolean getGruenesLicht() {
        return gruenesLicht;
    }

    public void setGruenesLicht(boolean signal) {
        gruenesLicht = signal;
    }

    public boolean getBaubar() {
        return baubar;
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
    public void setZeitZug() {
        if (zuege != 0) {
            zeitZug = Math.round(strecke.length / zuege);
        } else {
            zeitZug = -1;
        }
    }

    /**
     * räumt auf wenn die Linie gelöscht werden soll zB allen Bahnhöfen bescheid
     * geben
     */
    public void letzterSchritt() {
        // bei allen Bhfs die Linie löschen
        for (int i = 0; i < bhfs; i++) {
            bhfListe[i].linieWeg(this);
        }
    }

    /**
     *
     */
    public void step() {
        // Baubar setzen
        if (!gruenesLicht && depot == zuege) {
            baubar = true;
        } else {
            baubar = false;
        }
        // Fahren
        if (strecke.length > 0 && !baubar) {
            // Den jeweils letzten Zug bearbeiten
            // Aussteigen
            if (streckeZurueck[streckeZurueck.length - 1] > 0) {
                personen -= bhfListe[0].allesAussteigen(streckeZurueck[streckeZurueck.length - 1]);
                streckeZurueck[streckeZurueck.length - 1] = 0;
            }
            if (strecke[strecke.length - 1] > 0) {
                personen -= bhfListe[bhfs - 1].allesAussteigen(strecke[strecke.length - 1]);
                strecke[strecke.length - 1] = 0;
            }
            // Umdrehen am Ende
            int streckeEnde = strecke[strecke.length - 1];
            if (streckeZurueck[streckeZurueck.length - 1] > -1) {
                depot++;
            }
            // Auf der Linie weiterfahren
            for (int i = strecke.length - 1; i > 0; i--) {
                strecke[i] = strecke[i - 1];
                streckeZurueck[i] = streckeZurueck[i - 1];
            }
            // Züge aus Linie
            while (zuegeRaus > 0 && depot > 0) {
                depot--;
                zuegeRaus--;
                zuege--;
                strg.zugInsDepot();
            }
            // Den ersten Zug bearbeiten
            streckeZurueck[0] = streckeEnde;
            if (depot > 0 && zeitStep >= zeitZug && gruenesLicht) {
                strecke[0] = 0;
                depot--;
                zeitStep = -1;
            } else {
                strecke[0] = -1;
            }
        }
        // Aus- / Einsteigen
        for (int i = 0; i < istBhf.length; i++) {
            // Es ist ein Bahnhof da
            if (istBhf[i] > -1) {
                // Zug am Bahnhof auf Strecke
                if (strecke[i] > -1) {
                    int raus = bhfListe[istBhf[i]].aussteigen(strecke[i]);
                    int rein = bhfListe[istBhf[i]].einsteigen(zugKapazitaet - strecke[i]);
                    personen = (personen - raus) + rein;
                    strecke[i] = (strecke[i] - raus) + rein;
                }
                // Zug am Bahnhof auf StreckeZurück
                int j = streckeZurueck.length - i - 1;
                if (streckeZurueck[j] > -1) {
                    int raus = bhfListe[istBhf[i]].aussteigen(streckeZurueck[j]);
                    int rein = bhfListe[istBhf[i]].einsteigen(zugKapazitaet - streckeZurueck[j]);
                    personen = (personen - raus) + rein;
                    streckeZurueck[j] = (streckeZurueck[j] - raus) + rein;
                }
            }
        }
        System.out.println(name + "-Personen: " + personen);
        zeitStep++;
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

    /**
     * @return the zugKapazitaet
     */
    public int getZugKapazitaet() {
        return zugKapazitaet;
    }

    /**
     *
     * @return Anzahl der Züge im depot
     */
    public int getDepot() {
        return depot;
    }

}
