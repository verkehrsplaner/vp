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

    @Override
    public int getPersonen() {
        return 10;
    }

    @Override
    public Color getFarbe() {
        return Color.RED;
    }

    @Override
    public Color getDunkleFarbe() {
        return new Color(175, 0, 0);
    }
}
