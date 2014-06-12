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
    private double[] pixel = {32.0, 16.0, 8.0, 4.0, 2.0, 1.0};
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
        strg.klick((int) Math.round(f_x), (int) Math.round(f_y));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_B) {
            strg.setNextAction("bhf");
        } else if (e.getKeyCode() == KeyEvent.VK_J) {
            strg.langsamer();
        } else if (e.getKeyCode() == KeyEvent.VK_L) {
            strg.schneller();
        } else if (e.getKeyCode() == KeyEvent.VK_K || e.getKeyCode() == KeyEvent.VK_P) {
            strg.setPause(!strg.getPause());
        } else if (e.getKeyCode() == KeyEvent.VK_C) {
            strg.geldCheat();
        } else if (e.getKeyCode() == KeyEvent.VK_PLUS || e.getKeyCode() == 93 || e.getKeyCode() == KeyEvent.VK_X) { // Das zweite ist für Maxis Mac und insgesamt gehts hier um Zoomen
            strg.zoomIn();
        } else if (e.getKeyCode() == KeyEvent.VK_MINUS || e.getKeyCode() == 47 || e.getKeyCode() == KeyEvent.VK_Y) {
            strg.zoomOut();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
