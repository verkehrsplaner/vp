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
public abstract class Stadtteil {

    public static final int MORGEN = 0;
    public static final int MITTAG = 1;
    public static final int ABEND = 2;
    public static final int NACHT = 3;
    public static final int NICHTS = 4;
    
    /**
     * Personen, die von diesem Stadtteil aus in den Bahnhof gehen, abhängig von der Tageszeit
     * @return Personen für den Bahnhof
     */
    public abstract int getPersonen();
    
    /**
     * Ändert die Tageszeit in diesem Stadtteil
     * @param tageszeit Die Neue Tageszeit
     */
    public abstract void tageszeitAendern(int tageszeit);

    /**
     * @return Die Farbe des Stadtteils
     */
    public abstract Color getFarbe();
    /**
     * @return Die dunkle Farbe des Stadtteils
     */
    public abstract Color getDunkleFarbe();
}
