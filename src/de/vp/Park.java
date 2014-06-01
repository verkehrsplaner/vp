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
public class Park extends Stadtteil {

    @Override
    public int getPersonen() {
        return -8;
    }

    @Override
    public Color getFarbe() {
        return new Color(13, 158, 17);
    }
    
     @Override
    public Color getDunkleFarbe() {
        return new Color(13, 88, 17);
    }

}
