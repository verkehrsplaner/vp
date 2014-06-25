/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Felix, Nicolai, Christine, Maxi
 *
 */
public class PrologGUI extends javax.swing.JFrame {

    private int hoehe;
    private int breite;
    private Image[] bild;
    private int nummer;

    /**
     * Creates new form PrologGUI
     */
    public PrologGUI(int h, int b) {
        try {

            this.bild = new Image[]{
                /*1*/ImageIO.read(this.getClass().getResource("images/mayor.png")),
                /*2*/ ImageIO.read(this.getClass().getResource("images/mayor.png")),
                /*3*/ ImageIO.read(this.getClass().getResource("images/mayor.png")),
                /*4*/ ImageIO.read(this.getClass().getResource("images/mayor.png")),
                /*5*/ ImageIO.read(this.getClass().getResource("images/linien.png")),
                /*6*/ ImageIO.read(this.getClass().getResource("images/anleitung1.png")),
                /*7*/ ImageIO.read(this.getClass().getResource("images/anleitung2.png")),
                /*8*/ ImageIO.read(this.getClass().getResource("images/anleitung3.png")),
                /*9*/ ImageIO.read(this.getClass().getResource("images/anleitung4.png")),
                /*10*/ ImageIO.read(this.getClass().getResource("images/anleitung5.png")),
                /*11*/ ImageIO.read(this.getClass().getResource("images/anleitung6.png")),
                /*12*/ ImageIO.read(this.getClass().getResource("images/anleitung7.png")),
                /*13*/ ImageIO.read(this.getClass().getResource("images/anleitung8.png")),
                /*14*/ ImageIO.read(this.getClass().getResource("images/mayor.png")),};

        } catch (IOException ex) {
            System.err.println("Prolog konnte nicht geladen werden!");
        }
        breite = b;
        hoehe = h;
        nummer = 0;
        initComponents();

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        JFrame f = new SpielGUI(hoehe, breite);
                        f.setVisible(true);
                        dispose();
                    }
                }
                return false;
            }
        });

        ImageIcon icon = new ImageIcon(getClass().getResource("images/icon.png"));
        setIconImage(icon.getImage());

        getRootPane().setDefaultButton(vor);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        zurueck = new javax.swing.JButton();
        hintergrund = new BildPanel();
        vor = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Prolog");
        setMaximumSize(new java.awt.Dimension(800, 560));
        setMinimumSize(new java.awt.Dimension(800, 560));
        setPreferredSize(new java.awt.Dimension(800, 560));
        setResizable(false);

        zurueck.setText("<");
        zurueck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zurueckActionPerformed(evt);
            }
        });

        hintergrund.setMaximumSize(getPreferredSize());
        hintergrund.setOpaque(false);
        hintergrund.setPreferredSize(new java.awt.Dimension(640, 480));

        javax.swing.GroupLayout hintergrundLayout = new javax.swing.GroupLayout(hintergrund);
        hintergrund.setLayout(hintergrundLayout);
        hintergrundLayout.setHorizontalGroup(
            hintergrundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        hintergrundLayout.setVerticalGroup(
            hintergrundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        vor.setText(">");
        vor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vorActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Zum Überspringen Leertaste drücken!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hintergrund, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(zurueck)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(vor)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hintergrund, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vor)
                    .addComponent(zurueck)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void vorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vorActionPerformed
        nummer++;
        if (nummer < bild.length) {
            hintergrund.repaint();
            zurueck.setEnabled(true);
        } else {
            JFrame f = new SpielGUI(hoehe, breite);
            f.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_vorActionPerformed

    private void zurueckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zurueckActionPerformed
        if (nummer - 1 >= 0) {
            nummer--;
            hintergrund.repaint();
        }

    }//GEN-LAST:event_zurueckActionPerformed

    private class BildPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(bild[nummer], ((this.getWidth() - bild[nummer].getWidth(null)) / 2), 0, null); // Stellt die Posotion des Bildes ein
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel hintergrund;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton vor;
    private javax.swing.JButton zurueck;
    // End of variables declaration//GEN-END:variables
}
