/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 

package de.vp;

/**
 *
 * @author Nicolai
 */
public class Bahnhof {

    private int fahrtKosten;
    private int X;
    private int Y;
    private Stadtteil[] teile;
    private int personen;
    
    public Bahnhof(){
        fahrtKosten = 3;
        teile = new Stadtteil[100];
    }
    
    /**
     * 
     * @return gesamter Gewinn des Bahnhofs
     * berechnet durch alle Stadtteile im Radius
     */
    public int gewinn() {
        int x = 0;
        for(int i=0; i>teile.length; i++){
            if(teile[i].getPersonen() > 0){
            x = x + teile[i].getPersonen()*fahrtKosten;
            }
        }
        return x;
    }

    /**
     * 
     * @return alle personen zusammen die ein und aussteigen
     */
    public int personenBerechnen() {
        int x = 0;
        for(int i=0; i>teile.length; i++){
            x = x + teile[i].getPersonen();
        }
        personen = personen + x;
        return x;
    }

    public int einsteigen() {
        return 0;
    }
    /**
     * @return the fahrtKosten
     */
    public int getFahrtKosten() {
        return fahrtKosten;
    }

    /**
     * @return the X
     */
    public int getX() {
        return X;
    }

    /**
     * @param X the X to set
     */
    public void setX(int X) {
        this.X = X;
    }

    /**
     * @return the Y
     */
    public int getY() {
        return Y;
    }

    /**
     * @param Y the Y to set
     */
    public void setY(int Y) {
        this.Y = Y;
    }
    
}
