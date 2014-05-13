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
    private int zugKosten;
    private int zugUnterhaltungsKosten;
    private Bahnhof[] bhfListe;
    private int bhfs;

    public Linie(){
        zugKosten = 10000;
        zugUnterhaltungsKosten = 1000;
        bhfListe = new Bahnhof[20];
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
        Spielsteuerung.geldNehmen(zugKosten);
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
            Bahnhof[] bhfHilf = new Bahnhof[bhfListe.length + 10];
            for(int i=0; i > bhfListe.length; i++){
                bhfHilf[i] = bhfListe[i];
            }
            bhfListe = bhfHilf;
            bhfListe[bhfs + 1] = bhf;
            bhfs++;
        }
    }
}

