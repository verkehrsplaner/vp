/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Hecker & Maxi
 */
public class MenuGUI extends JDialog {

    private Sound sound;
    private SpielPanel panel;
    Spielsteuerung strg;

    /**
     * Creates new form EinstellungsGUI
     */
    public MenuGUI(Sound s, SpielPanel p, Spielsteuerung ss) {
        sound = s;
        panel = p;
        strg = ss;
        initComponents();

        ImageIcon icon = new ImageIcon(getClass().getResource("images/icon.png"));
        setIconImage(icon.getImage());
        boolean pause = strg.getPause();
        if (pause == true) {
            jToggleButton1.setSelected(true);
        }
        else {
            jToggleButton1.setSelected(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hMusik = new javax.swing.JCheckBox();
        soundeffekte = new javax.swing.JCheckBox();
        Menü = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        spielBeenden = new javax.swing.JButton();
        spielSpeichern = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        blinken = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menü");
        setAlwaysOnTop(true);
        setMinimumSize(getPreferredSize());
        setResizable(false);

        hMusik.setSelected(sound.getMusikAn());
        hMusik.setText("Hintergrundmusik");
        hMusik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hMusikActionPerformed(evt);
            }
        });

        soundeffekte.setText("Soundeffekte");
        soundeffekte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soundeffekteActionPerformed(evt);
            }
        });

        Menü.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Menü.setText("Menü");

        spielBeenden.setText("Spiel beenden");
        spielBeenden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spielBeendenActionPerformed(evt);
            }
        });

        spielSpeichern.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/speichern transparent.png"))); // NOI18N
        spielSpeichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spielSpeichernActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Spiel pausieren");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        blinken.setSelected(panel.getBlinken());
        blinken.setText("Nicht versorgte Bahnhöfe blinken");
        blinken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blinkenActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Atmosphäre");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(Menü)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spielSpeichern, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spielBeenden, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addGap(118, 118, 118))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(hMusik)
                        .addComponent(soundeffekte)
                        .addComponent(blinken)))
                .addGap(144, 144, 144))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Menü)
                .addGap(64, 64, 64)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hMusik)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(soundeffekte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blinken)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spielSpeichern, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addComponent(spielBeenden, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void soundeffekteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soundeffekteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soundeffekteActionPerformed

    private void spielBeendenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spielBeendenActionPerformed
        Object[] options = {"Ja", "Nein"};
        int s = JOptionPane.showOptionDialog(null, "Möchtest du das Spiel wirklich beenden?", "Spiel Beenden",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
        // System.out.println(s);
        if (s == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_spielBeendenActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if (jToggleButton1.isSelected()) {
            strg.setPause(true);
        } else {
            strg.setPause(false);
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void spielSpeichernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spielSpeichernActionPerformed

    }//GEN-LAST:event_spielSpeichernActionPerformed

    private void hMusikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hMusikActionPerformed
        if (hMusik.isSelected() == true) {
            sound.musikAn();
        } else {
            sound.musikAus();
        }
    }//GEN-LAST:event_hMusikActionPerformed

    private void blinkenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blinkenActionPerformed
        if (blinken.isSelected() == true) {
            panel.setBlinken(true);
        } else {
            panel.setBlinken(false);
        }
    }//GEN-LAST:event_blinkenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Menü;
    public javax.swing.JCheckBox blinken;
    private javax.swing.JCheckBox hMusik;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JCheckBox soundeffekte;
    private javax.swing.JButton spielBeenden;
    private javax.swing.JButton spielSpeichern;
    // End of variables declaration//GEN-END:variables
}
