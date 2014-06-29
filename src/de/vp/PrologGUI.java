/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
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
    private boolean gedrueckt;
    private Sound sound;

    /**
     * Creates new form PrologGUI
     * Zusätzlich wird mit Hilfe eines Timers überwacht, welche Tastaturtasten gedrückt wurden, um den Prolog mit Pfeiltasten zu steuern
     */
    public PrologGUI(int h, int b) {
        sound = new Sound();
        sound.musikAn();
        try {

            this.bild = new Image[]{
                /*1*/ImageIO.read(this.getClass().getResource("images/Prolog/1.png")),
                /*2*/ ImageIO.read(this.getClass().getResource("images/Prolog/2.png")),
                /*3*/ ImageIO.read(this.getClass().getResource("images/Prolog/3.png")),
                /*4*/ ImageIO.read(this.getClass().getResource("images/Prolog/4.png")),
                /*5*/ ImageIO.read(this.getClass().getResource("images/Prolog/5.png")),
                /*6*/ ImageIO.read(this.getClass().getResource("images/Prolog/6.png")),
                /*7*/ ImageIO.read(this.getClass().getResource("images/Prolog/7.png")),
                /*8*/ ImageIO.read(this.getClass().getResource("images/Prolog/8.png")),
                /*9*/ ImageIO.read(this.getClass().getResource("images/Prolog/9.png")),
                /*10*/ ImageIO.read(this.getClass().getResource("images/Prolog/10.png")),
                /*11*/ ImageIO.read(this.getClass().getResource("images/Prolog/11.png")),
                /*12*/ ImageIO.read(this.getClass().getResource("images/Prolog/12.png")),
                /*13*/ ImageIO.read(this.getClass().getResource("images/Prolog/13.png")),
                /*14*/ ImageIO.read(this.getClass().getResource("images/Prolog/14.png")),};

        } catch (IOException ex) {
            System.err.println("Prolog konnte nicht geladen werden!");
        }
        breite = b;
        hoehe = h;
        nummer = 0;
        gedrueckt = false;
        this.setUndecorated(true);
        initComponents();

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (!gedrueckt) {
                    if (e.getID() == KeyEvent.KEY_PRESSED) {
                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            JFrame f = new SpielGUI(hoehe, breite, sound);
                            f.setVisible(true);
                            dispose();
                            gedrueckt = true;
                        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            if (nummer - 1 >= 0) {
                                nummer--;
                                if (nummer < 1) {
                                    zurueck.setEnabled(false);
                                }
                                hintergrund.repaint();
                            }
                        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            nummer++;
                            if (nummer < bild.length) {
                                hintergrund.repaint();
                                zurueck.setEnabled(true);
                            } else {
                                JFrame f = new SpielGUI(hoehe, breite, sound);
                                f.setVisible(true);
                                dispose();
                                gedrueckt = true;
                            }
                        }
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

        hintergrund = new BildPanel();
        jLabel1 = new javax.swing.JLabel();
        vor = new javax.swing.JButton();
        zurueck = new javax.swing.JButton();
        musikButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Prolog");
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setResizable(false);

        hintergrund.setMaximumSize(getPreferredSize());
        hintergrund.setOpaque(false);
        hintergrund.setPreferredSize(new java.awt.Dimension(640, 480));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Zum Überspringen Leertaste drücken!");

        vor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/R.png"))); // NOI18N
        vor.setBorderPainted(false);
        vor.setContentAreaFilled(false);
        vor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vorActionPerformed(evt);
            }
        });

        zurueck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/L.png"))); // NOI18N
        zurueck.setBorderPainted(false);
        zurueck.setContentAreaFilled(false);
        zurueck.setEnabled(false);
        zurueck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zurueckActionPerformed(evt);
            }
        });

        musikButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/musikaus.png"))); // NOI18N
        musikButton.setBorderPainted(false);
        musikButton.setContentAreaFilled(false);
        musikButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musikButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout hintergrundLayout = new javax.swing.GroupLayout(hintergrund);
        hintergrund.setLayout(hintergrundLayout);
        hintergrundLayout.setHorizontalGroup(
            hintergrundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hintergrundLayout.createSequentialGroup()
                .addGroup(hintergrundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(hintergrundLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(musikButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(hintergrundLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(zurueck, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(vor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        hintergrundLayout.setVerticalGroup(
            hintergrundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hintergrundLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(musikButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 326, Short.MAX_VALUE)
                .addGroup(hintergrundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zurueck, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(hintergrund, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(hintergrund, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Das nächste Bild wird geladen Falls alle Bilder bereits angezeigt wurden,
     * öffnet sich eine neue GUI und das Spiel wird gestartet
     *
     * @param evt
     */
    private void vorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vorActionPerformed
        nummer++;
        if (nummer < bild.length) {
            hintergrund.repaint();
            zurueck.setEnabled(true);
        } else {
            JFrame f = new SpielGUI(hoehe, breite, sound);
            f.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_vorActionPerformed

    /**
     * Das vorherige Bild wird geladen
     *
     * @param evt
     */
    private void zurueckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zurueckActionPerformed
        if (nummer - 1 >= 0) {
            nummer--;
            if (nummer < 1) {
                zurueck.setEnabled(false);
            }
            hintergrund.repaint();
        }

    }//GEN-LAST:event_zurueckActionPerformed

    private void musikButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_musikButtonActionPerformed
        if (sound.getMusikAn()) { // Musik ausmachen
            sound.musikAus();
            musikButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/musik.png")));
        } else {
            sound.musikAn();
            musikButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/musikaus.png")));
        }
    }//GEN-LAST:event_musikButtonActionPerformed

    private class BildPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(bild[nummer], ((this.getWidth() - bild[nummer].getWidth(null)) / 2), 0, null); // Stellt die Posotion des Bildes ein
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel hintergrund;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton musikButton;
    private javax.swing.JButton vor;
    private javax.swing.JButton zurueck;
    // End of variables declaration//GEN-END:variables
}
