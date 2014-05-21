/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Felix
 */
public class SpielPanel extends javax.swing.JPanel {

    private Spielsteuerung strg;

    /**
     * Creates new form SpielPanel
     *
     * @param h
     * @param b
     * @param s
     */
    public SpielPanel(int h, int b, Spielsteuerung s) {
        initComponents();
        strg = s;
        setSize(b * 30, h * 30);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Stadtteil[][] teile = strg.getTeile();

        for (int y = 0; y < teile.length; y++) {
            for (int x = 0; x < teile[y].length; x++) {
                if (teile[x][y] != null) {
                    g2d.setColor(teile[x][y].getFarbe());
                    g2d.setStroke(new BasicStroke(1));
                    g2d.fillRect(x * 30, y * 30, 30, 30);
                    g2d.setColor(Color.YELLOW);
                    g2d.setStroke(new BasicStroke(3));
                    g2d.drawRect(x * 30, y * 30, 30, 30);
                }

            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
