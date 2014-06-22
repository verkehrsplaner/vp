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
public class Haus extends Stadtteil {

    private int personen = 1;
    private int zeit = NICHTS;
    
    @Override
    public int getPersonen() {
        if (zeit == NICHTS || zeit == MITTAG || zeit == NACHT) {
            return 0;
        } else if (zeit == MORGEN) {
            return personen;
        } else {
            return -personen;
        }
    }

    @Override
    public Color getFarbe() {
        return Color.RED;
    }

    @Override
    public Color getDunkleFarbe() {
        return new Color(175, 0, 0);
    }

    @Override
    public void tageszeitAendern(int tageszeit) {
        zeit = tageszeit;
    }
}
