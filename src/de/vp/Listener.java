/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Felix
 */
public class Listener extends MouseAdapter implements KeyListener {
    Spielsteuerung strg;
    
    public Listener(Spielsteuerung s) {
        strg = s;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        double f_x = x / 30.0;
        double f_y = y / 30.0;
        strg.klick((int)Math.round(f_x), (int)Math.round(f_y));
        System.out.println("Klick: " + x + ", " + y + "; Feld: " + Math.round(f_x) + ", " + Math.round(f_y));
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
