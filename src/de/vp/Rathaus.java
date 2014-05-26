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
public class Rathaus extends Stadtteil {

    @Override
    public int getPersonen() {
        return -10;
    }

    @Override
    public Color getFarbe() {
        return new Color(75, 206, 250);
    }
    
    @Override
    public Color getDunkleFarbe() {
        return Color.BLACK;
    }

}
