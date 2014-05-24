package de.vp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Maxi
 */
public class SpielPanel extends javax.swing.JPanel {

    private Spielsteuerung strg;
    Listener list;

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
        list = new Listener(strg);
        this.setFocusable(true);
        this.addMouseListener(list);
        this.addKeyListener(list);
        setSize(b * 30, h * 30);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Stadtteil[][] teile = strg.getTeile();
        Bahnhof[][] bhf = strg.getBahnhoefe();

        for (int y = 0; y < teile.length; y++) {
            for (int x = 0; x < teile[y].length; x++) {
                if (teile[y][x] != null) {
                    g2d.setColor(teile[y][x].getFarbe());
                    g2d.setStroke(new BasicStroke(1));
                    g2d.fillRect(x * 30, y * 30, 30, 30);
                    g2d.setColor(Color.YELLOW);
                    g2d.setStroke(new BasicStroke(3));
                    g2d.drawRect(x * 30, y * 30, 30, 30);
                }

            }

        }
        for (int y = 0; y < bhf.length; y++) {
            for (int x = 0; x < bhf[y].length; x++) {
                if (bhf[y][x] != null) {
                    g2d.setColor(Color.WHITE);
                    g2d.setStroke(new BasicStroke(1));
                    g2d.fillOval(x * 30 - 10, y * 30 - 10, 20, 20);
                    g2d.setColor(Color.BLACK);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawOval(x * 30 - 10, y * 30 - 10, 20, 20);
                }
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
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
