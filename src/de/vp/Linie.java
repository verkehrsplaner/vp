/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.vp;

import java.awt.Color;

/**
 *
 * @author Felix
 */
public class Linie {
    
    private String name;
    private Color farbe;
    private int zuege;
    private int zugUnterhaltungsKosten;
    private Bahnhof[] bhfListe;
    private int bhfs;
    private int bhfUnterhaltungsKosten;

    public Linie(){
        zugUnterhaltungsKosten = 1000;
        bhfListe = new Bahnhof[20];
        bhfUnterhaltungsKosten = 1000;
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
     * einen Zug hinzufügen/kaufen
     */
    public void zugEinstellen() {
        zuege++;
    }
    
     /**
     * einen Zug aus der Linie löschen
     */
    public void zugEntfernen() {
        zuege--;
    }
    
    /**
     * 
     * @param bhf gegebener Bhf wird in bhfListe eingefügt
     */
    public void bahnhofHinzufuegen(Bahnhof bhf){
        if(bhfListe.length > bhfs + 1){
            bhfListe[bhfs + 1] = bhf;
            bhfs++;
        }
        else{
            //Bei zu kurzer Liste wird diese erweitert
            Bahnhof[] bhfHilf = new Bahnhof[bhfListe.length + 10];
            for(int i=0; i > bhfListe.length; i++){
                bhfHilf[i] = bhfListe[i];
            }
            bhfListe = bhfHilf;
            bhfListe[bhfs + 1] = bhf;
            bhfs++;
        }
    }
    
    /**
     * 
     * @param bhf gegebener Bhf wird aus der Liste gelöscht?
     * letzter Bhf wird aus der Liste gelöscht!
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
        return zuege*zugUnterhaltungsKosten+bhfs*bhfUnterhaltungsKosten;
    }
    
    public int gewinn() {
        int k = 0;
        for(int i=0; i>bhfs; i++){
            k = k+bhfListe[i].gewinn();
        }
        return k;
    }
}

