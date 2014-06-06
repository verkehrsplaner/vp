package de.vp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Maxi
 */
public class SpielPanel extends javax.swing.JPanel {

    private Spielsteuerung strg;
    private MenuGUI menu;
    private SpielGUI gui;
    private Listener list;
    private int zoom;
    private int[] pixel = {32, 16, 8, 4, 2, 1};
    private int[] dicke = {3, 2, 1, 0, 0, 0};
    private int[] radius = {20, 16, 10, 6, 4, 2};
    private int[] bahnhofdicke = {2, 2, 1, 1, 0, 0};
    private int[] liniendicke = {7, 5, 4, 4, 3, 2};
    private int blink;
    private boolean blinken;

    /**
     * Creates new form SpielPanel
     *
     * @param h Höhe
     * @param b Breite
     * @param s Spielsteuerung
     */
    public SpielPanel(int h, int b, Spielsteuerung s, SpielGUI g) {
        initComponents();
        strg = s;
        gui = g;
        blink = 0;
        blinken = true;
        zoom = 0;
        list = new Listener(strg);
        this.setFocusable(true);
        this.addMouseListener(list);
        this.addKeyListener(list);
        setSize(b * pixel[zoom] + 40, h * pixel[zoom] + 40);
        this.setPreferredSize(this.getSize());
    }

    public void setZoom(int z) {
        gui.saveScrollValues();
        list.setZoom(z);
        this.setSize(strg.getBreite() * pixel[z] + 40, strg.getHoehe() * pixel[z] + 40);
        this.setPreferredSize(this.getSize());
        this.repaint();
        if (z < zoom) {
            gui.zoomIn();
        }
        if (z > zoom) {
            gui.zoomOut();
        }
        zoom = z;
    }

    public void setBlinken(boolean b) {
        blinken = b;
    }

    public boolean getBlinken() {
        return blinken;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //Beiger Hintergrund
        g2d.setColor(new Color(192, 219, 154));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        //Schwarzer Rahmen um Spielfeld
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect(20, 20, strg.getBreite() * pixel[zoom], strg.getHoehe() * pixel[zoom]);

        //Häuser malen
        Stadtteil[][] teile = strg.getTeile();
        Bahnhof[][] bhf = strg.getBahnhoefe();
        boolean[][] hatBhf = strg.getHatBahnhof();

        for (int y = 0; y < teile.length; y++) {
            for (int x = 0; x < teile[y].length; x++) {
                if (teile[y][x] != null) {
                    g2d.setColor(teile[y][x].getFarbe());
                    if (!hatBhf[y][x]) {
                        if (blink < 15 && blinken) {        //Hier wird die Blinkrate weiter eingestellt
                            g2d.setColor(teile[y][x].getFarbe());
                        } else {
                            g2d.setColor(teile[y][x].getDunkleFarbe());
                        }
                    }
                    g2d.setStroke(new BasicStroke(1));
                    g2d.fillRect(x * pixel[zoom] + 20, y * pixel[zoom] + 20, pixel[zoom], pixel[zoom]);
                    if (dicke[zoom] > 0) {
                        g2d.setColor(Color.YELLOW);
                        g2d.setStroke(new BasicStroke(dicke[zoom]));
                        g2d.drawRect(x * pixel[zoom] + 20, y * pixel[zoom] + 20, pixel[zoom], pixel[zoom]);
                    }
                }

            }

        }
        blink++;
        if (blink >= 30) {       //Blinkrate, abhänig von kommender if-Funktion
            blink = 0;
        }

        //Linien Zeichnen
        Linie[] linien = strg.getLinien();

        for (int i = 0; i < linien.length; i++) {

            Bahnhof[] linienBahnhof = linien[i].getBahnhof();
            g2d.setColor(linien[i].getFarbe());
            g2d.setStroke(new BasicStroke(liniendicke[zoom]));
            for (int j = 0; j < linienBahnhof.length - 1; j++) {
                g2d.drawLine(linienBahnhof[j].getX() * pixel[zoom] + 20, linienBahnhof[j].getY() * pixel[zoom] + 20, linienBahnhof[j + 1].getX() * pixel[zoom] + 20, linienBahnhof[j + 1].getY() * pixel[zoom] + 20);
            }

        }

        // Aktuelle Linie oben zeichnen
        int aktLinie = gui.getLinienNr();
        if (aktLinie >= 0) {
            Bahnhof[] linienBahnhof = linien[aktLinie].getBahnhof();
            g2d.setColor(linien[aktLinie].getFarbe());
            g2d.setStroke(new BasicStroke(liniendicke[zoom]));
            for (int j = 0; j < linienBahnhof.length - 1; j++) {
                g2d.drawLine(linienBahnhof[j].getX() * pixel[zoom] + 20, linienBahnhof[j].getY() * pixel[zoom] + 20, linienBahnhof[j + 1].getX() * pixel[zoom] + 20, linienBahnhof[j + 1].getY() * pixel[zoom] + 20);
            }
        }

        //Bahnhöfe malen
        for (int y = 0; y < bhf.length; y++) {
            for (int x = 0; x < bhf[y].length; x++) {
                if (bhf[y][x] != null) {
                    g2d.setColor(Color.WHITE);
                    g2d.setStroke(new BasicStroke(1));
                    g2d.fillOval(x * pixel[zoom] - radius[zoom] / 2 + 20, y * pixel[zoom] - radius[zoom] / 2 + 20, radius[zoom], radius[zoom]);
                    if (bahnhofdicke[zoom] > 0) {
                        g2d.setColor(Color.BLACK);
                        g2d.setStroke(new BasicStroke(bahnhofdicke[zoom]));
                        g2d.drawOval(x * pixel[zoom] - radius[zoom] / 2 + 20, y * pixel[zoom] - radius[zoom] / 2 + 20, radius[zoom], radius[zoom]);
                    }
                    if (zoom < 3) {
                        g2d.setFont(new Font("Arial", Font.BOLD, 16));
                        g2d.drawString(bhf[y][x].getName(), x * pixel[zoom] - 10, y * pixel[zoom] + 5);
                    }
                    if (zoom < 3) {
                        g2d.setFont(new Font("Arial", Font.BOLD, 16));
                        g2d.drawString(Integer.toString(bhf[y][x].getBahnsteig()), x * pixel[zoom], y * pixel[zoom] + 50);
                    }
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
