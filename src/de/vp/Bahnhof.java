/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.vp;

/**
 *
 * @author Felix
 */
public class Bahnhof {

    private int einflussradius;
    private int fahrtKosten;
    private int X;
    private int Y;
    private Stadtteil[] teile;
    
    public Bahnhof(){
        fahrtKosten = 1;
    }
    public int gewinn() {
        return 0;
    }

    public int personenBerechnen() {
        return 0;
    }
    
}
