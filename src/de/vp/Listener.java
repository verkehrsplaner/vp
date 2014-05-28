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
    private Spielsteuerung strg;
    private double[] pixel = {32.0, 16.0, 8.0, 4.0, 2.0};
    private int zoom;
    
    public Listener(Spielsteuerung s) {
        strg = s;
        zoom = 0;
    }
    
    public void setZoom(int z) {
        zoom = z;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        e.getComponent().requestFocusInWindow();
        int x = e.getX() - 20;
        int y = e.getY() - 20;
        double f_x = x / pixel[zoom];
        double f_y = y / pixel[zoom];
        strg.klick((int)Math.round(f_x), (int)Math.round(f_y));
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Taste gedrückt! " + e.getKeyCode() + ", Minus: " + KeyEvent.VK_PLUS);
        if (e.getKeyCode() == KeyEvent.VK_B) {
            System.out.println("B gedrückt");
            strg.setNextAction("bhf");
        }
        // Das zweite ist für Maxis Mac
        if (e.getKeyCode() == KeyEvent.VK_PLUS || e.getKeyCode() == 93) {
            System.out.println("+ gedrückt");
            strg.zoomIn();
        }
        if (e.getKeyCode() == KeyEvent.VK_MINUS || e.getKeyCode() == 47) {
            System.out.println("- gedrückt");
            strg.zoomOut();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
