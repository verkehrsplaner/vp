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
    private Listener list;
    private int zoom;
    private int[] pixel = {30, 16, 8};
    /**
     * Creates new form SpielPanel
     *
     * @param h Höhe
     * @param b Breite
     * @param s Spielsteuerung
     */
    public SpielPanel(int h, int b, Spielsteuerung s) {
        initComponents();
        strg = s;
        zoom = 0;
        list = new Listener(strg);
        this.setFocusable(true);
        this.addMouseListener(list);
        this.addKeyListener(list);
        setSize(b * pixel[zoom] + 40, h * pixel[zoom] + 40);
        this.setPreferredSize(this.getSize());
    }
    
    public void setZoom(int z) {
        zoom = z;
        setSize(strg.getBreite() * pixel[zoom] + 40, strg.getHoehe() * pixel[zoom] + 40);
        this.setPreferredSize(this.getSize());
        this.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //Beiger Hintergrund
        g2d.setColor(new Color(192, 219, 154));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        

        //Häuser malen
        Stadtteil[][] teile = strg.getTeile();
        Bahnhof[][] bhf = strg.getBahnhoefe();
        boolean[][] hatBhf = strg.getHatBahnhof();

        for (int y = 0; y < teile.length; y++) {
            for (int x = 0; x < teile[y].length; x++) {
                if (teile[y][x] != null) {
                    g2d.setColor(teile[y][x].getFarbe());
                    if (!hatBhf[y][x]) {
                        g2d.setColor(teile[y][x].getDunkleFarbe());
                    }
                    g2d.setStroke(new BasicStroke(1));
                    g2d.fillRect(x * pixel[zoom] + 20, y * pixel[zoom] + 20, pixel[zoom], pixel[zoom]);
                    g2d.setColor(Color.YELLOW);
                    g2d.setStroke(new BasicStroke(3));
                    g2d.drawRect(x * pixel[zoom] + 20, y * pixel[zoom] + 20, pixel[zoom], pixel[zoom]);
                }

            }

        }

        //Bahnhöfe malen
        for (int y = 0; y < bhf.length; y++) {
            for (int x = 0; x < bhf[y].length; x++) {
                if (bhf[y][x] != null) {
                    g2d.setColor(Color.WHITE);
                    g2d.setStroke(new BasicStroke(1));
                    g2d.fillOval(x * pixel[zoom] - 10 + 20, y * pixel[zoom] - 10 + 20, 20, 20);
                    g2d.setColor(Color.BLACK);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawOval(x * pixel[zoom] - 10 + 20, y * pixel[zoom] - 10 + 20, 20, 20);
                }
            }
        }
        
        //Schwarzer Rahmen um Spielfeld
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect(20, 20, this.getWidth() - 40, this.getHeight() - 40);

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
