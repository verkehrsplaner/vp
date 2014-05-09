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
    private int zugkosten;
    private Bahnhof[] bhfListe;

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
        this.zuege = zuege++;
    }
    
     /**
     * einen Zug aus der Linie löschen
     */
    public void zugEntfernen() {
        this.zuege = zuege--;
    }
    
    
}

