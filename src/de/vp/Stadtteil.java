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

    public abstract int getPersonen();
    public abstract void tageszeitAendern(boolean nacht);

    public abstract Color getFarbe();
    public abstract Color getDunkleFarbe();
}
