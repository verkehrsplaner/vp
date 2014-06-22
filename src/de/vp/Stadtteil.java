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
    
    public abstract int getPersonen();
    public abstract void tageszeitAendern(int tageszeit);

    public abstract Color getFarbe();
    public abstract Color getDunkleFarbe();
}
